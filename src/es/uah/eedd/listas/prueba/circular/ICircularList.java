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
