package es.uah.eedd.listas.prueba.circular;


public class MyCircularLinkedList<T> implements ICircularList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private Node<T> current = null; // Para el sistema de turnos
    private int size = 0;

    @Override
    public void add(T dato) {
        Node<T> newNode = new Node<>(dato);
        //CASO A: La lista esta vacia
        if (isEmpty()) {
            head = newNode;
            tail=newNode;
            current=newNode;//actual
            newNode.next=newNode; // Se apunta a sí mismo (el círculo de uno)
            newNode.prev=newNode; // El anterior de sí mismo es él mismo
            //CASO B: La lista tiene elementos
        } else {
            newNode.prev = tail;// Conectamos el nuevo con el que era el último
            newNode.next = head; // El nuevo apunta al primero(ultimo.siguiente=primero)

            tail.next = newNode;
            head.prev = newNode;// El primero ahora tiene al nuevo como anterior

            tail = newNode;
        }
        size++;
    }

    @Override
    public void remove(T dato) {
        if (isEmpty()) return;

        Node<T> temp = head; // Este es el primero
        boolean found = false;//Inicializamos en false

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
                    tail = temp.prev;//El tail es el anterior
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
            throw new RuntimeException("Lista vacía");
        }
        current = current.next;
        return current.value;
    }
    @Override
    public T previous() {
        if (isEmpty()) {
            throw new RuntimeException("Lista vacía");
        }
        current = current.prev;
        return current.value;
    }
    @Override
    public T getCurrent() {
        if (isEmpty()) {
            throw new RuntimeException("Lista vacía");
        }
        return current.value;
    }

    @Override
    public int size() {
        return size;

    }
}
