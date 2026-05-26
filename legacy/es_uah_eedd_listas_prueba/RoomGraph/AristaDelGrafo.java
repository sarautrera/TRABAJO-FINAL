/*
 * Resumen del fichero: Conserva una implementacion antigua de AristaDelGrafo usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.RoomGraph;

public class AristaDelGrafo<T> {
    String predicado;
    T objeto;
    AristaDelGrafo<T> siguiente;

    public AristaDelGrafo(String predicado, T objeto){
        this.predicado=predicado;
        this.objeto=objeto;
        this.siguiente=null;
    }
}
