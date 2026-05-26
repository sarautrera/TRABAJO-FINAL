/*
 * Resumen del fichero: Conserva una implementacion antigua de MyLinkedList usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.simple;

public class MyLinkedList<T> implements IList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(T dato) {
        Node<T> newNode = new Node<>(dato);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int indice, T dato) {
        if (indice < 0 || indice > size) {
            throw new IndexOutOfBoundsException();
        }
        if (indice == 0) {
            Node<T> newNode = new Node<>(dato);
            newNode.next = head;
            head = newNode;
            if (tail == null) {
                tail = head;
            }
            size++;
        }
        else if(indice==size){
            add(dato);
        }
        else{
            Node<T> newNode=new Node<>(dato);
            Node<T> prev=head;
            for(int i=0;i<indice-1; i++){
                prev=prev.next;
            }
            newNode.next=prev.next;
            prev.next=newNode;
            size++;
        }
    }
    public Node<T> getHead(){
        return this.head;
    }
    public void addAll(MyLinkedList<T> another){
        if (another == null || another.isEmpty()) {
            return;
        }

        Node<T> current = another.getHead();
        while (current != null) {
            this.add(current.value);
            current = current.next;
        }
    }

    public T get(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> aux = head;
        for (int i = 0; i < indice; i++) {
            aux = aux.next;
        }
        return aux.value;
    }
    @Override
    public T remove(T dato) {
        Node<T> act = head;
        Node<T> ant =null;
        while (act != null) {
            if (act.value.equals(dato)) {
                if (ant == null) {
                    head = act.next;
                } else {
                    ant.next = act.next;
                    if (ant.next == null) {
                        tail = ant;
                    }
                }
                size--;
                return act.value;
            }
            ant = act;
            act = act.next;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public MiIterador<T> getIterador() {
        return new MiIterador<>(this.head);
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) return null;
        return head.value;
    }


    @Override
    public T getLast() {
        if (isEmpty()) return null;
        return tail.value;
    }

    @Override
    public T remove(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Ãndice fuera de rango: " + indice);
        }

        T valorDel;

        if (indice == 0) {
            valorDel = head.value;
            head = head.next;
            if (head == null) {
                tail = null;
            }
        }
        else {
            Node<T> prev = head;
            for (int i = 0; i < indice - 1; i++) {
                prev = prev.next;
            }

            valorDel = prev.next.value;
            prev.next = prev.next.next;

            if (prev.next == null) {
                tail = prev;
            }
        }

        size--;
        return valorDel;
    }
    @Override
    public boolean contains(T dato) {
        Node<T> aux = head;
        while (aux != null) {
            if (aux.value.equals(dato)) return true;
            aux = aux.next;
        }
        return false;
    }
}
