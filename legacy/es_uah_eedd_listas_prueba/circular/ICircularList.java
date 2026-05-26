/*
 * Resumen del fichero: Conserva una implementacion antigua de ICircularList usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.circular;

public interface ICircularList<T> {
    void add(T dato);
    void remove(T dato);
    boolean isEmpty();
    T next();
    T previous();
    T getCurrent();
    int size();
}
