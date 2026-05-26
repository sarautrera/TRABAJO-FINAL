// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.queue;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.util.NoSuchElementException;

// Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
public interface IQueue<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void enqueue(T dato);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T dequeue() throws NoSuchElementException;
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean isEmpty();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T peek() throws NoSuchElementException;
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int size();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void clear();
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
