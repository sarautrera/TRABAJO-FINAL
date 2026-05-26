/*
 * Resumen del fichero: Conserva una implementacion antigua de MyCircularLinkedList usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.circular;


public class MyCircularLinkedList<T> implements ICircularList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private Node<T> current = null;
    private int size = 0;

    @Override
    public void add(T dato) {
        Node<T> newNode = new Node<>(dato);
        if (isEmpty()) {
            head = newNode;
            tail=newNode;
            current=newNode;
            newNode.next=newNode;
            newNode.prev=newNode;
        } else {
            newNode.prev = tail;
            newNode.next = head;

            tail.next = newNode;
            head.prev = newNode;

            tail = newNode;
        }
        size++;
    }

    @Override
    public void remove(T dato) {
        if (isEmpty()) return;

        Node<T> temp = head;
        boolean found = false;

        for (int i = 0; i < size; i++) {
            if (temp.value.equals(dato)) {
                found = true;
                break;
            }
            temp = temp.next;
        }

        if (found) {
            if (size==1) {
                head = null;
                tail=null;
                current=null;
            } else {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;

                if (temp == head) {
                    head = temp.next;
                }
                if (temp == tail) {
                    tail = temp.prev;
                }
                if (temp == current) {
                    current = temp.next;
                }
            }
        }
        size--;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public T next() {
        if (isEmpty()) {
            throw new RuntimeException("Lista vacÃ­a");
        }
        current = current.next;
        return current.value;
    }
    @Override
    public T previous() {
        if (isEmpty()) {
            throw new RuntimeException("Lista vacÃ­a");
        }
        current = current.prev;
        return current.value;
    }
    @Override
    public T getCurrent() {
        if (isEmpty()) {
            throw new RuntimeException("Lista vacÃ­a");
        }
        return current.value;
    }

    @Override
    public int size() {
        return size;

    }
}
