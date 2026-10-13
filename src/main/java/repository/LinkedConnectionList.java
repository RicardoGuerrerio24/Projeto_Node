package repository;

import model.Connection;

/**
 * Estrutura auxiliar: lista ligada simples de objetos Connection.
 * A lista é implementada manualmente, sem usar LinkedList ou ArrayList.
 */
public final class LinkedConnectionList {
    private static final class ConnectionEntry {
        private final Connection value;
        private ConnectionEntry nextEntry;

        private ConnectionEntry(Connection value) {
            this.value = value;
        }
    }

    private ConnectionEntry first;
    private ConnectionEntry last;
    private int size;

    public void add(Connection connection) {
        if (connection == null) {
            throw new IllegalArgumentException("Não é possível inserir uma ligação null.");
        }

        ConnectionEntry newEntry = new ConnectionEntry(connection);
        if (first == null) {
            first = newEntry;
            last = newEntry;
        } else {
            last.nextEntry = newEntry;
            last = newEntry;
        }
        size++;
    }

    public Connection get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice de ligação inválido: " + index);
        }

        ConnectionEntry current = first;
        for (int i = 0; i < index; i++) {
            current = current.nextEntry;
        }
        return current.value;
    }

    public int size() {
        return size;
    }
}
