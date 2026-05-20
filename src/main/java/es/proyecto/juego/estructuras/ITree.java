package es.proyecto.juego.estructuras;

public interface ITree<T extends Comparable<T>> {
    void insert(T element);

    boolean search(T element);

    IList<T> inOrder();

    IList<T> toList();

    int height();
}
