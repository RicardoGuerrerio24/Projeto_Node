package model;

/**
 * Um dos três objetos principais do domínio.
 * Representa uma ligação dirigida entre dois objetos Node.
 */
public final class Connection {
    private final Node from;
    private final Node to;

    public Connection(Node from, Node to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Uma ligação requer dois nós não nulos.");
        }
        this.from = from;
        this.to = to;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    @Override
    public String toString() {
        return from.getId() + " -> " + to.getId();
    }
}
