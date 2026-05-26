// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.circular;

// Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
public interface ICircularList<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void add(T dato);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void remove(T dato);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean isEmpty();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T next();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T previous();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T getCurrent();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int size();
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
