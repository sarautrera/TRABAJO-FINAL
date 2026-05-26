// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.RoomGraph;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.uah.eedd.listas.prueba.matrix.Matrix;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.uah.eedd.listas.prueba.queue.Queue;

// Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
public interface IGraph<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void add(T sujeto, String predicado, T objeto);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    Nodo<T> getSujeto(T sujeto);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    Queue<T> minCamino(T a, T b);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    ListaEnlazadaSimple<T> agregarALista(ListaEnlazadaSimple<T> lista, T nodo, T padre);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean estaEnLista(ListaEnlazadaSimple<T> lista, T dato);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void cargarArchivo(String nombreArchivo);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    Queue<T> buscarFisicoMismaCiudad(T fisicoObjetivo);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void imprimirGrafo();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    Queue<T> listarLugaresNacimientoNobelistas();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    Matrix getAsMatrix();

// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
