/*
 * Resumen del fichero: Conserva una implementacion antigua de IStack usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.stack;

public interface IStack<T> {
    void push(T dato);
    T pop();
    boolean isEmpty();
    T peek();
    int size();
    void clear();
}
