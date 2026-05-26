/*
 * Resumen del fichero: Conserva una implementacion antigua de ListaEnlazadaSimple usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.RoomGraph;

class ListaEnlazadaSimple<T> {
    T nodo;
    T padre;
    ListaEnlazadaSimple<T> siguiente;
    public ListaEnlazadaSimple(T nodo, T padre){
        this.nodo=nodo;
        this.padre=padre;
        this.siguiente=null;
    }

}
