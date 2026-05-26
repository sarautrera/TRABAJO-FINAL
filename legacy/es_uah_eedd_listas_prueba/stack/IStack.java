// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.stack;

// Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
public interface IStack<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void push(T dato);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T pop();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean isEmpty();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T peek();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int size();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void clear();
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
