/*
 * Resumen del fichero: Conserva una implementacion antigua de IGraph usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.RoomGraph;

import es.uah.eedd.listas.prueba.matrix.Matrix;
import es.uah.eedd.listas.prueba.queue.Queue;

public interface IGraph<T> {
    void add(T sujeto, String predicado, T objeto);
    Nodo<T> getSujeto(T sujeto);
    Queue<T> minCamino(T a, T b);
    ListaEnlazadaSimple<T> agregarALista(ListaEnlazadaSimple<T> lista, T nodo, T padre);
    boolean estaEnLista(ListaEnlazadaSimple<T> lista, T dato);
    void cargarArchivo(String nombreArchivo);
    Queue<T> buscarFisicoMismaCiudad(T fisicoObjetivo);
    void imprimirGrafo();
    Queue<T> listarLugaresNacimientoNobelistas();
    Matrix getAsMatrix();

}
