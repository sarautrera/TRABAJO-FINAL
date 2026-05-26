/*
 * Resumen del fichero: Implementa una lista circular sencilla para recorrer elementos por turnos.
 */
package es.proyecto.juego.estructuras;

public class MyCircularList<T> {
    private Node<T> tail;
    private Node<T> current;
    private int size;

    public void add(T element) {
        Node<T> node = new Node<>(element);
        if (tail == null) {
            node.next = node;
            tail = node;
            current = node;
        } else {
            node.next = tail.next;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T current() {
        ensureNotEmpty();
        return current.value;
    }

    public T next() {
        ensureNotEmpty();
        current = current.next;
        return current.value;
    }

    public boolean removeCurrent() {
        if (isEmpty()) {
            return false;
        }
        if (size == 1) {
            tail = null;
            current = null;
            size = 0;
            return true;
        }

        Node<T> previous = tail;
        while (previous.next != current) {
            previous = previous.next;
        }
        previous.next = current.next;
        if (current == tail) {
            tail = previous;
        }
        current = current.next;
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("La lista circular esta vacia");
        }
    }

    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }
}
