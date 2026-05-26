/*
 * Resumen del fichero: Conserva una implementacion antigua de Node usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.simple;

public class Node<T> {
    T value;
    Node<T> next;

    Node(T value){
        this.value = value;
        this.next =null;
    }
}
