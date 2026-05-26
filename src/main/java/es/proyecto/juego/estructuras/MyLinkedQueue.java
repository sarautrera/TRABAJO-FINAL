// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.estructuras;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class MyLinkedQueue<T> implements IQueue<T> {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Node<T> head;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Node<T> tail;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int size;

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void enqueue(T element) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> node = new Node<>(element);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (head == null) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            head = node;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail = node;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } else {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail.next = node;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail = node;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        size++;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T dequeue() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isEmpty()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("La cola esta vacia");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        T value = head.value;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        head = head.next;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        size--;
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (size == 0) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail = null;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return value;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T peek() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isEmpty()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("La cola esta vacia");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return head.value;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isEmpty() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return size == 0;
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

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    private static final class Node<T> {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final T value;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private Node<T> next;

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private Node(T value) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.value = value;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
