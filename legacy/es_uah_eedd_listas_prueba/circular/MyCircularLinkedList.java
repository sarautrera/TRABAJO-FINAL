// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.circular;


// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class MyCircularLinkedList<T> implements ICircularList<T> {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Node<T> head = null;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Node<T> tail = null;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Node<T> current = null; // Para el sistema de turnos
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int size = 0;

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void add(T dato) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> newNode = new Node<>(dato);
        //CASO A: La lista esta vacia
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isEmpty()) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            head = newNode;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail=newNode;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            current=newNode;//actual
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            newNode.next=newNode; // Se apunta a sÃ­ mismo (el cÃ­rculo de uno)
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            newNode.prev=newNode; // El anterior de sÃ­ mismo es Ã©l mismo
            //CASO B: La lista tiene elementos
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } else {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            newNode.prev = tail;// Conectamos el nuevo con el que era el Ãºltimo
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            newNode.next = head; // El nuevo apunta al primero(ultimo.siguiente=primero)

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail.next = newNode;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            head.prev = newNode;// El primero ahora tiene al nuevo como anterior

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail = newNode;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        size++;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void remove(T dato) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isEmpty()) return;

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> temp = head; // Este es el primero
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        boolean found = false;//Inicializamos en false

        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < size; i++) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (temp.value.equals(dato)) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                found = true;
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                break;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            temp = temp.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (found) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (size==1) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                head = null;
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                tail=null;
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                current=null;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                temp.prev.next = temp.next;
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                temp.next.prev = temp.prev;

                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (temp == head) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    head = temp.next;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (temp == tail) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    tail = temp.prev;//El tail es el anterior
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (temp == current) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    current = temp.next;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        size--;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isEmpty() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return size==0;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T next() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isEmpty()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new RuntimeException("Lista vacÃ­a");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        current = current.next;
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return current.value;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T previous() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isEmpty()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new RuntimeException("Lista vacÃ­a");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        current = current.prev;
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return current.value;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T getCurrent() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isEmpty()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new RuntimeException("Lista vacÃ­a");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return current.value;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int size() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return size;

    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
