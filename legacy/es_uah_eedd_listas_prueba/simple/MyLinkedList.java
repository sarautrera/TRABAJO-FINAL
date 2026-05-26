// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.simple;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class MyLinkedList<T> implements IList<T> {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Node<T> head;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Node<T> tail;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int size = 0;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public MyLinkedList() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.head = null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.tail = null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.size = 0;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void add(T dato) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> newNode = new Node<>(dato);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (head == null) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            head = newNode;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail = newNode;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } else {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail.next = newNode;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail = newNode;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        size++;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    //AÃ±adir acorde a un indice
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void add(int indice, T dato) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (indice < 0 || indice > size) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IndexOutOfBoundsException();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        //Si es el principio
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (indice == 0) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Node<T> newNode = new Node<>(dato);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            newNode.next = head;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            head = newNode;
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (tail == null) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                tail = head;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            size++;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        //Usamos el metodo add(T) qe es o(1)
        // Comentario de estudiante: aqui se hace la alternativa si el if no se cumple.
        else if(indice==size){
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            add(dato);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        //Si es en medio
        // Comentario de estudiante: aqui se hace la alternativa si el if no se cumple.
        else{
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Node<T> newNode=new Node<>(dato);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Node<T> prev=head;
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for(int i=0;i<indice-1; i++){
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                prev=prev.next;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            newNode.next=prev.next;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            prev.next=newNode;
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            size++;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Node<T> getHead(){
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return this.head;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void addAll(MyLinkedList<T> another){
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (another == null || another.isEmpty()) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> current = another.getHead();
        // Comentario de estudiante: aqui empieza un bucle while.
        while (current != null) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            this.add(current.value);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            current = current.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T get(int indice) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (indice < 0 || indice >= size) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IndexOutOfBoundsException();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> aux = head;
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < indice; i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            aux = aux.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return aux.value;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T remove(T dato) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> act = head;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> ant =null;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (act != null) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (act.value.equals(dato)) {
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (ant == null) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    head = act.next;//Eso significa que act es el primer elemento de la lista
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                } else {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    ant.next = act.next;
                    // Comentario de estudiante: aqui se comprueba una condicion con if.
                    if (ant.next == null) {
                        // Comentario de estudiante: aqui se guarda o actualiza un valor.
                        tail = ant;
                    // Comentario de estudiante: aqui se cierra un bloque de codigo.
                    }
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                size--;
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return act.value;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            ant = act;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            act = act.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isEmpty() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return head == null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getSize() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return size;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public MiIterador<T> getIterador() {
        // Creamos un nuevo iterador y le damos el punto de partida (primero)
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return new MiIterador<>(this.head);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void clear() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        head = null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T getFirst() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isEmpty()) return null;
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return head.value;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }


    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T getLast() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isEmpty()) return null;
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return tail.value; // Coste O(1)
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T remove(int indice) {
        // 1. Validar Ã­ndice
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (indice < 0 || indice >= size) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IndexOutOfBoundsException("Ãndice fuera de rango: " + indice);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        T valorDel;

        // 2. CASO ESPECIAL: Eliminar el primero (head)
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (indice == 0) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            valorDel = head.value;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            head = head.next;
            // Si la lista se queda vacÃ­a, tail tambiÃ©n debe ser null
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (head == null) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                tail = null;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // 3. CASO GENERAL: Eliminar en medio o al final
        // Comentario de estudiante: aqui se hace la alternativa si el if no se cumple.
        else {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Node<T> prev = head;
            // Buscamos el nodo anterior al que queremos borrar
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int i = 0; i < indice - 1; i++) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                prev = prev.next;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            valorDel = prev.next.value;
            // Saltamos el nodo a eliminar
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            prev.next = prev.next.next;

            // Si hemos eliminado el Ãºltimo, actualizamos el puntero tail
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (prev.next == null) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                tail = prev;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        size--;
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return valorDel;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean contains(T dato) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> aux = head;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (aux != null) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (aux.value.equals(dato)) return true;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            aux = aux.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return false;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
