package es.uah.eedd.listas.prueba.stack;

public interface IStack<T> {
    void push(T dato);
    T pop();
    boolean isEmpty();
    T peek();
    int size();
    void clear();
}
