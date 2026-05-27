/*
 * Resumen del fichero: Implementa un arbol binario de busqueda generico usando nodos propios.
 */
package es.proyecto.juego.estructuras;

public class MyBST<T extends Comparable<T>> implements ITree<T> {
    private Node<T> root;
    private int size;

    @Override
    public void insert(T element) {
        validateElement(element);
        if (root == null) {
            root = new Node<T>(element);
            size = 1;
            return;
        }
        if (insert(root, element)) {
            size++;
        }
    }

    @Override
    public boolean search(T element) {
        validateElement(element);
        Node<T> current = root;
        while (current != null) {
            int comparison = element.compareTo(current.value);
            if (comparison == 0) {
                return true;
            }
            current = comparison < 0 ? current.left : current.right;
        }
        return false;
    }

    @Override
    public IList<T> inOrder() {
        MyLinkedList<T> result = new MyLinkedList<T>();
        fillInOrder(root, result);
        return result;
    }

    @Override
    public IList<T> toList() {
        return inOrder();
    }

    @Override
    public int height() {
        return height(root);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean insert(Node<T> current, T element) {
        int comparison = element.compareTo(current.value);
        if (comparison == 0) {
            return false;
        }
        if (comparison < 0) {
            if (current.left == null) {
                current.left = new Node<T>(element);
                return true;
            }
            return insert(current.left, element);
        }
        if (current.right == null) {
            current.right = new Node<T>(element);
            return true;
        }
        return insert(current.right, element);
    }

    private void fillInOrder(Node<T> current, IList<T> result) {
        if (current == null) {
            return;
        }
        fillInOrder(current.left, result);
        result.add(current.value);
        fillInOrder(current.right, result);
    }

    private int height(Node<T> current) {
        if (current == null) {
            return 0;
        }
        int leftHeight = height(current.left);
        int rightHeight = height(current.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private void validateElement(T element) {
        if (element == null) {
            throw new IllegalArgumentException("El elemento del arbol no puede ser null");
        }
    }

    private static final class Node<T> {
        private final T value;
        private Node<T> left;
        private Node<T> right;

        private Node(T value) {
            this.value = value;
        }
    }
}
