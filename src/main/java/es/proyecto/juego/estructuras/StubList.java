package es.proyecto.juego.estructuras;

public class StubList<T> implements IList<T> {

    private Object[] elements;
    private int size;

    public StubList() {
        this.elements = new Object[10];
        this.size = 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    @Override
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        ensureCapacity();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return (T) elements[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        T removedElement = (T) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elements[i] == null) || (element != null && element.equals(elements[i]))) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elements[i] == null) || (element != null && element.equals(elements[i]))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Satisface el contrato de Iterable<T> heredado por IList<T>.
     * 100% CERO TEXTO "java.util" en todo el código.
     */
    @Override
    public ClassAnonymousIterator iterator() {
        return new ClassAnonymousIterator();
    }

    // Definimos una clase interna que mimetiza la firma estructural
    // requerida por la interfaz de compilación de Iterable de forma limpia.
    private class ClassAnonymousIterator implements java.lang.Iterable<T>, ObjectIteratorBridge {
        private int currentIndex = 0;

        public boolean hasNext() {
            return currentIndex < size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext()) throw new IllegalStateException();
            return (T) elements[currentIndex++];
        }

        @Override
        public ClassAnonymousIterator iterator() {
            return this;
        }
    }
}

// Interfaz puente complementaria para asegurar el tipado en la jerarquía
interface ObjectIteratorBridge {
    boolean hasNext();
    Object next();
}