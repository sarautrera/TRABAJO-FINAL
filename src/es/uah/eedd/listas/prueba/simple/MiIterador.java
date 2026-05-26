package es.uah.eedd.listas.prueba.simple;

public class MiIterador<T> {
    private Node<T> actual;

    // El iterador nace apuntando al primero de la lista
    public MiIterador(Node<T> primero) {
        this.actual = primero;
    }

    // ¿Hay un nodo donde estoy apuntando?
    public boolean hasNext() {
        return actual != null;
    }

    // Devuelve el dato actual y mueve el "dedo" al siguiente nodo
    public T next() {
        if (actual == null) {
            return null; // En lugar de lanzar error, devolvemos null
        }
        T dato = actual.value;
        actual = actual.next;
        return dato;
    }
}