/*
 * Resumen del fichero: Implementa una lista enlazada simple generica sin usar colecciones de Java.
 */
package es.proyecto.juego.estructuras;

public class MyLinkedList<T> implements IList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T element) {
        Node<T> node = new Node<>(element);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        validateAddIndex(index);
        if (index == size) {
            add(element);
            return;
        }

        Node<T> node = new Node<>(element);
        if (index == 0) {
            node.next = head;
            head = node;
        } else {
            Node<T> previous = nodeAt(index - 1);
            node.next = previous.next;
            previous.next = node;
        }
        size++;
    }

    @Override
    public T get(int index) {
        return nodeAt(index).value;
    }

    @Override
    public T remove(int index) {
        validateElementIndex(index);
        Node<T> removed;
        if (index == 0) {
            removed = head;
            head = head.next;
            if (size == 1) {
                tail = null;
            }
        } else {
            Node<T> previous = nodeAt(index - 1);
            removed = previous.next;
            previous.next = removed.next;
            if (removed == tail) {
                tail = previous;
            }
        }
        size--;
        return removed.value;
    }

    @Override
    public boolean remove(T element) {
        Node<T> previous = null;
        Node<T> current = head;
        while (current != null) {
            if (equalsValue(current.value, element)) {
                unlink(previous, current);
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T element) {
        Node<T> current = head;
        while (current != null) {
            if (equalsValue(current.value, element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    private void unlink(Node<T> previous, Node<T> current) {
        if (previous == null) {
            head = current.next;
        } else {
            previous.next = current.next;
        }
        if (current == tail) {
            tail = previous;
        }
        size--;
    }

    private Node<T> nodeAt(int index) {
        validateElementIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void validateElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + index);
        }
    }

    private void validateAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + index);
        }
    }

    private boolean equalsValue(T first, T second) {
        return first == null ? second == null : first.equals(second);
    }

    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }
}
