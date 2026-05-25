package es.proyecto.juego.estructuras;

/**
 * Interfaz personalizada para las operaciones de una lista genérica (<T>).
 * Al extender de Iterable<T>, obliga a cualquier lista a ofrecer un iterador.
 */
public interface IList<T> extends Iterable<T> {

    // Añade un elemento al final de la lista.
    void add(T element);

    // Inserta un elemento en una posición específica.
    void add(int index, T element);

    // Obtiene el elemento de la posición indicada sin borrarlo.
    T get(int index);

    // Elimina y devuelve el elemento de la posición indicada.
    T remove(int index);

    // Busca un elemento específico y lo elimina.
    boolean remove(T element);

    // Devuelve la cantidad de elementos.
    int size();

    // Comprueba si está vacía.
    boolean isEmpty();

    // Verifica si un elemento existe en la lista.
    boolean contains(T element);
}