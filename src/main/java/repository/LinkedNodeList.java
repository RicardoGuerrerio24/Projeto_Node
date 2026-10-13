package repository;

import model.Node;

/**
 * Lista ligada simples criada manualmente para guardar os nós de uma figura.
 * Não usa ArrayList, LinkedList nem outra coleção da biblioteca Java.
 *
 * Importante: NodeEntry é o elo da LISTA de armazenamento. Os campos
 * previous/next da classe Node são as ligações GEOMÉTRICAS da figura.
 */
public final class LinkedNodeList {
    private static final class NodeEntry {
        private final Node value;
        private NodeEntry nextEntry;

        private NodeEntry(Node value) {
            this.value = value;
        }
    }

    private NodeEntry first;
    private NodeEntry last;
    private int size;

    public void add(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Não é possível inserir um nó null.");
        }

        NodeEntry newEntry = new NodeEntry(node);
        if (first == null) {
            first = newEntry;
            last = newEntry;
        } else {
            last.nextEntry = newEntry;
            last = newEntry;
        }
        size++;
    }

    public Node get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice de nó inválido: " + index);
        }

        NodeEntry currentEntry = first;
        for (int i = 0; i < index; i++) {
            currentEntry = currentEntry.nextEntry;
        }
        return currentEntry.value;
    }

    public boolean contains(Node node) {
        NodeEntry currentEntry = first;
        while (currentEntry != null) {
            if (currentEntry.value == node) {
                return true;
            }
            currentEntry = currentEntry.nextEntry;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
