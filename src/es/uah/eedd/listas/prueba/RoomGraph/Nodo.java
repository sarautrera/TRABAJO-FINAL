package es.uah.eedd.listas.prueba.RoomGraph;

public class Nodo<T> {
    T sujeto;
    Nodo<T> siguiente;
    AristaDelGrafo<T> listaAristas;
    public Nodo(T sujeto){
        this.sujeto=sujeto;
        this.siguiente=null;
        this.listaAristas=null;
    }
}
