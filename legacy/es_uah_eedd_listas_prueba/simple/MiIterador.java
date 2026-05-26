/*
 * Resumen del fichero: Conserva una implementacion antigua de MiIterador usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.simple;

public class MiIterador<T> {
    private Node<T> actual;

    public MiIterador(Node<T> primero) {
        this.actual = primero;
    }

    public boolean hasNext() {
        return actual != null;
    }

    public T next() {
        if (actual == null) {
            return null;
        }
        T dato = actual.value;
        actual = actual.next;
        return dato;
    }
}
