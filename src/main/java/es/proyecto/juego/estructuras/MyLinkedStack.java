/*
 * Resumen del fichero: Implementa una pila enlazada para apilar y desapilar elementos.
 */
package es.proyecto.juego.estructuras;

public class MyLinkedStack<T> implements IStack<T> {
    private Node<T> top;
    private int size;

    @Override
    public void push(T element) {
        Node<T> node = new Node<>(element);
        node.next = top;
        top = node;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila esta vacia");
        }
        T value = top.value;
        top = top.next;
        size--;
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila esta vacia");
        }
        return top.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }
}
