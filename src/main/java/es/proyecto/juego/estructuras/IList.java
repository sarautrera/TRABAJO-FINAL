/*
 * Resumen del fichero: Declara las operaciones comunes de una lista propia del proyecto.
 */
package es.proyecto.juego.estructuras;

public interface IList<T> {
    void add(T element);

    void add(int index, T element);

    T get(int index);

    T remove(int index);

    boolean remove(T element);

    int size();

    boolean isEmpty();

    boolean contains(T element);
}
