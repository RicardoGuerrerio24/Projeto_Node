package view;

import model.Node;
import model.Shape;

/**
 * Utilitários para demonstrar o percurso dos nós e das arestas.
 * Não é usada qualquer coleção da biblioteca: o acesso faz-se pelas listas manuais.
 */
public final class ShapeNavigator {
    private ShapeNavigator() {
        // Classe de utilitários: não deve ser instanciada.
    }

    public static void printShape(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Não é possível navegar uma forma null.");
        }

        System.out.println("\n=== " + shape.getType() + " ===");
        System.out.println("Nós: " + shape.getNodeCount() + " | Ligações: " + shape.getConnectionCount());
        TerminalShapeRenderer.print(shape);

        System.out.println("Ligações guardadas na lista ligada:");
        if (shape.getConnectionCount() == 0) {
            System.out.println("  (nenhuma ligação recta)");
        }
        for (int i = 0; i < shape.getConnectionCount(); i++) {
            System.out.println("  " + shape.getConnection(i));
        }

        System.out.println("\nVizinhos de cada nó (previous | current | next):");
        for (int i = 0; i < shape.getNodeCount(); i++) {
            Node current = shape.getNode(i);
            System.out.println("  " + nodeId(current.getPrevious())
                    + " | " + nodeId(current)
                    + " | " + nodeId(current.getNext()));
        }

        printFollowingNext(shape);
    }

    /**
     * Segue explicitamente Node.next. Em polígonos fechados, há limite de
     * getNodeCount() passos para não entrar num ciclo infinito ao voltar ao início.
     */
    public static void printFollowingNext(Shape shape) {
        System.out.println("\nPercurso seguindo next:");
        Node current = shape.getStartNode();

        if (current == null) {
            System.out.println("  A forma não contém nós; current = null.");
            return;
        }

        for (int step = 0; step < shape.getNodeCount() && current != null; step++) {
            System.out.println("  passo " + step + ": current = " + current.getId()
                    + ", previous = " + nodeId(current.getPrevious())
                    + ", next = " + nodeId(current.getNext()));
            current = current.getNext();
        }

        if (current == null) {
            System.out.println("  Fim do percurso: next não existe, logo current passa a null.");
        } else {
            System.out.println("  Após " + shape.getNodeCount()
                    + " passos, next volta a " + current.getId()
                    + " (ciclo fechado da figura).");
        }
    }

    private static String nodeId(Node node) {
        return node == null ? "null" : node.getId();
    }
}
