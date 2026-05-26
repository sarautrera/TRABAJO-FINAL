/*
 * Resumen del fichero: Conserva una implementacion antigua de Node usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.circular;

public class Node<T> {
    T value;
    Node<T> next;
    Node<T> prev;

    Node(T value){
        this.value=value;
        this.next = this;
        this.prev = this;
    }
}
