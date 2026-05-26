// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.estructuras;

// Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
public interface IList<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void add(T element);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void add(int index, T element);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T get(int index);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T remove(int index);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean remove(T element);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int size();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean isEmpty();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean contains(T element);
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
