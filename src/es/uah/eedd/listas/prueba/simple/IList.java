package es.uah.eedd.listas.prueba.simple;

public interface IList<T> {
    void add(T dato);
    T get(int dato);
    boolean isEmpty();
    int getSize();
    MiIterador<T> getIterador();
    void clear();
    T getFirst();
    T getLast();
    boolean contains(T dato);
    T remove(int index);
    T remove(T dato);
    void add(int indice, T dato);
}