package es.uah.eedd.listas.prueba.simple;

public class Node<T> {
    T value;
    Node<T> next;

    //Creamos vagon, el NODO
    Node(T value){
        this.value = value;//Variable con la dirección del dato
        this.next =null;//Variable con la direccion de otros nodos
    }
    //Si no ponemos ni public ni private las clases de un mismo paquete pueden acceder a sus variables con (.)
    //Si pusieramos private entonces deberiamos crear los metodos getSiguiente() y setSitguiente()
}
