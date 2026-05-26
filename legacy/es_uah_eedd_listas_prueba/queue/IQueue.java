/*
 * Resumen del fichero: Conserva una implementacion antigua de IQueue usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.queue;
import java.util.NoSuchElementException;

public interface IQueue<T> {
    void enqueue(T dato);
    T dequeue() throws NoSuchElementException;
    boolean isEmpty();
    T peek() throws NoSuchElementException;
    int size();
    void clear();
}
