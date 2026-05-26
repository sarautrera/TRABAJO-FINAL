// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.estructuras;

// Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
public interface IQueue<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void enqueue(T element);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T dequeue();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T peek();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean isEmpty();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int size();
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
