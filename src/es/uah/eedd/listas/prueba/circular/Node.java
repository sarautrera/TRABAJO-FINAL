package es.uah.eedd.listas.prueba.circular;

public class Node<T> {
    T value;
    Node<T> next;
    Node<T> prev; //Necesaria para hacerla doble circular

    Node(T value){
        this.value=value;
        this.next = this; // Por defecto se apunta a sí mismo
        this.prev = this;
    }
}
