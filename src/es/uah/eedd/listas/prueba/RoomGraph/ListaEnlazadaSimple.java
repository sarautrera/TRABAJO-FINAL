package es.uah.eedd.listas.prueba.RoomGraph;

class ListaEnlazadaSimple<T> {
    T nodo;
    //El padre es el anterior al nodo
    T padre;
    ListaEnlazadaSimple<T> siguiente;
    public ListaEnlazadaSimple(T nodo, T padre){
        this.nodo=nodo;
        this.padre=padre;
        this.siguiente=null;
    }

}
