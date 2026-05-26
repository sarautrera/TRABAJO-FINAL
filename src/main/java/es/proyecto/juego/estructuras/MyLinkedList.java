// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.estructuras;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class MyLinkedList<T> implements IList<T> {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Node<T> head;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Node<T> tail;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int size;

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void add(T element) {
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
    public void add(int index, T element) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validateAddIndex(index);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (index == size) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            add(element);
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> node = new Node<>(element);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (index == 0) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            node.next = head;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            head = node;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } else {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Node<T> previous = nodeAt(index - 1);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            node.next = previous.next;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            previous.next = node;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        size++;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T get(int index) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return nodeAt(index).value;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T remove(int index) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validateElementIndex(index);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        Node<T> removed;
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (index == 0) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            removed = head;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            head = head.next;
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (size == 1) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                tail = null;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } else {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Node<T> previous = nodeAt(index - 1);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            removed = previous.next;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            previous.next = removed.next;
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (removed == tail) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                tail = previous;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        size--;
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return removed.value;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean remove(T element) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> previous = null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> current = head;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (current != null) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (equalsValue(current.value, element)) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                unlink(previous, current);
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return true;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            previous = current;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            current = current.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return false;
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
    public boolean contains(T element) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> current = head;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (current != null) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (equalsValue(current.value, element)) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return true;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            current = current.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return false;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void clear() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        head = null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        tail = null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        size = 0;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void unlink(Node<T> previous, Node<T> current) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (previous == null) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            head = current.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } else {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            previous.next = current.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (current == tail) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            tail = previous;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        size--;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private Node<T> nodeAt(int index) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validateElementIndex(index);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node<T> current = head;
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < index; i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            current = current.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return current;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void validateElementIndex(int index) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (index < 0 || index >= size) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + index);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void validateAddIndex(int index) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (index < 0 || index > size) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + index);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private boolean equalsValue(T first, T second) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return first == null ? second == null : first.equals(second);
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
