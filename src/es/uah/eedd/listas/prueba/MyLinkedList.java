package es.uah.eedd.listas.prueba;

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

    //Añadir acorde a un indice
    @Override
    public void add(int indice, T dato) {
        if (indice < 0 || indice > size) {
            throw new IndexOutOfBoundsException();
        }
        //Si es el principio
        if (indice == 0) {
            Node<T> newNode = new Node<>(dato);
            newNode.next = head;
            head = newNode;
            if (tail == null) {
                tail = head;
            }
            size++;
        }
        //Usamos el metodo add(T) qe es o(1)
        else if(indice==size){
            add(dato);
        }
        //Si es en medio
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
                    head = act.next;//Eso significa que act es el primer elemento de la lista
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
        // Creamos un nuevo iterador y le damos el punto de partida (primero)
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
        return tail.value; // Coste O(1)
    }

    @Override
    public T remove(int indice) {
        // 1. Validar índice
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }

        T valorDel;

        // 2. CASO ESPECIAL: Eliminar el primero (head)
        if (indice == 0) {
            valorDel = head.value;
            head = head.next;
            // Si la lista se queda vacía, tail también debe ser null
            if (head == null) {
                tail = null;
            }
        }
        // 3. CASO GENERAL: Eliminar en medio o al final
        else {
            Node<T> prev = head;
            // Buscamos el nodo anterior al que queremos borrar
            for (int i = 0; i < indice - 1; i++) {
                prev = prev.next;
            }

            valorDel = prev.next.value;
            // Saltamos el nodo a eliminar
            prev.next = prev.next.next;

            // Si hemos eliminado el último, actualizamos el puntero tail
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