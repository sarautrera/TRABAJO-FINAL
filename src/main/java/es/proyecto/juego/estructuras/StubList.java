// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.estructuras;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class StubList<T> implements IList<T> {

    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Object[] elements;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int size;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public StubList() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.elements = new Object[10];
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.size = 0;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void ensureCapacity() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (size == elements.length) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Object[] newElements = new Object[elements.length * 2];
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int i = 0; i < size; i++) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                newElements[i] = elements[i];
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            elements = newElements;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void add(T element) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureCapacity();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        elements[size++] = element;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void add(int index, T element) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (index < 0 || index > size) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IndexOutOfBoundsException();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureCapacity();
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = size; i > index; i--) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            elements[i] = elements[i - 1];
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        elements[index] = element;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        size++;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @SuppressWarnings("unchecked")
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T get(int index) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (index < 0 || index >= size) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IndexOutOfBoundsException();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return (T) elements[index];
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @SuppressWarnings("unchecked")
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T remove(int index) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (index < 0 || index >= size) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IndexOutOfBoundsException();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        T removedElement = (T) elements[index];
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = index; i < size - 1; i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            elements[i] = elements[i + 1];
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        elements[--size] = null;
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return removedElement;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean remove(T element) {
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < size; i++) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if ((element == null && elements[i] == null) || (element != null && element.equals(elements[i]))) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                remove(i);
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return true;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
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
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < size; i++) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if ((element == null && elements[i] == null) || (element != null && element.equals(elements[i]))) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return true;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return false;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
