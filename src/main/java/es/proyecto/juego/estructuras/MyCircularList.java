// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.estructuras;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class MyCircularList<T> {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Node<T> tail;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Node<T> current;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int size;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void add(T element) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> node = new Node<>(element);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (tail == null) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            node.next = node;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail = node;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            current = node;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } else {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            node.next = tail.next;
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

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T current() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureNotEmpty();
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return current.value;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T next() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureNotEmpty();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        current = current.next;
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return current.value;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean removeCurrent() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isEmpty()) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return false;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (size == 1) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail = null;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            current = null;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            size = 0;
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return true;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> previous = tail;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (previous.next != current) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            previous = previous.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        previous.next = current.next;
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (current == tail) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail = previous;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        current = current.next;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        size--;
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int size() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return size;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isEmpty() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return size == 0;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void ensureNotEmpty() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isEmpty()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("La lista circular esta vacia");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
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
