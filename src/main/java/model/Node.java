package model;

/**
 * Representa um ponto/vértice de uma figura.
 *
 * previous e next representam as ligações geométricas da navegação,
 * e não os ponteiros internos usados pela classe LinkedNodeList.
 */
public final class Node {
    private final String id;
    private final double x;
    private final double y;

    private Node previous;
    private Node next;

    public Node(String id, double x, double y) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("O identificador do nó é obrigatório.");
        }
        this.id = id;
        this.x = x;
        this.y = y;
        // Por omissão, um nó ainda não tem vizinhos: previous e next são null.
        this.previous = null;
        this.next = null;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Node getPrevious() {
        return previous;
    }

    public Node getNext() {
        return next;
    }

    /* Métodos com acesso de pacote: apenas as classes da estrutura podem ligar nós. */
    void setPrevious(Node previous) {
        this.previous = previous;
    }

    void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return id + "(" + x + ", " + y + ")";
    }
}
