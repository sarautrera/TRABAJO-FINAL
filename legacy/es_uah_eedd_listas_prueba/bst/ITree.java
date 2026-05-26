// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.bst;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.uah.eedd.listas.prueba.simple.MyLinkedList;

// Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
public interface ITree<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean isEmpty();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void add(T nuevo);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    MyLinkedList<T> getSubArbolIzquierda();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    MyLinkedList<T> getSubArbolDerecha();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean isArbolHomogeneo();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean isArbolCompleto();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean isArbolCasiCompleto();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int contarNodos();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean compararPosiciones(int indice, int numNodos);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    MyLinkedList<T> getListaDatosNivel(int nivel);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    MyLinkedList<T> getCamino(T valorBuscado);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getAltura();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getGrado();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    MyLinkedList<T> getListaOrdenCentral();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    MyLinkedList<T> getListaPreOrden();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    MyLinkedList<T> getListaPostOrden();
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
