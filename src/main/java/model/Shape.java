package model;

import repository.LinkedConnectionList;
import repository.LinkedNodeList;

/**
 * Um dos três objetos principais do domínio.
 *
 * Uma Shape representa qualquer uma das únicas três formas permitidas. O tipo
 * não é uma subclasse: é o valor ShapeType. Assim, o modelo fica centrado nos
 * três objetos pedidos: Node, Connection e Shape.
 */
public final class Shape {
    private final ShapeType type;
    private final LinkedNodeList nodes = new LinkedNodeList();
    private final LinkedConnectionList connections = new LinkedConnectionList();
    private double radius;

    public Shape(ShapeType type) {
        if (type == null) {
            throw new IllegalArgumentException("O tipo da forma não pode ser null.");
        }
        this.type = type;
    }

    public ShapeType getType() {
        return type;
    }

    /** Usado pela estratégia do círculo para guardar a medida da forma. */
    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("O raio tem de ser positivo.");
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    /** Insere um objeto Node na lista ligada de nós desta Shape. */
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * Cria um objeto Connection e actualiza também previous/next dos Nodes.
     */
    public void connect(Node from, Node to) {
        if (!nodes.contains(from) || !nodes.contains(to)) {
            throw new IllegalArgumentException("Os nós têm de pertencer à mesma forma.");
        }
        if (from.getNext() != null || to.getPrevious() != null) {
            throw new IllegalStateException("Cada nó só pode ter uma ligação anterior e uma seguinte.");
        }

        from.setNext(to);
        to.setPrevious(from);
        connections.add(new Connection(from, to));
    }

    public int getNodeCount() {
        return nodes.size();
    }

    public int getConnectionCount() {
        return connections.size();
    }

    public Node getNode(int index) {
        return nodes.get(index);
    }

    public Connection getConnection(int index) {
        return connections.get(index);
    }

    public Node getStartNode() {
        return nodes.isEmpty() ? null : nodes.get(0);
    }

    /** Valida a regra da forma e impede qualquer tipo diferente dos três. */
    public void validateStructure() {
        switch (type) {
            case TRIANGLE -> requireCounts(3, 3);
            case RECTANGLE -> requireCounts(4, 4);
            case CIRCLE -> {
                requireCounts(1, 0);
                Node center = getStartNode();
                if (center.getPrevious() != null || center.getNext() != null) {
                    throw new IllegalStateException("O nó de referência do círculo não tem ligações.");
                }
            }
        }
    }

    private void requireCounts(int expectedNodes, int expectedConnections) {
        if (getNodeCount() != expectedNodes || getConnectionCount() != expectedConnections) {
            throw new IllegalStateException(type + " requer " + expectedNodes
                    + " nós e " + expectedConnections + " ligações.");
        }
    }
}
