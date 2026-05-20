package es.proyecto.juego.estructuras;

public interface IQueue<T> {
    void enqueue(T element);

    T dequeue();

    T peek();

    boolean isEmpty();

    int size();
}
