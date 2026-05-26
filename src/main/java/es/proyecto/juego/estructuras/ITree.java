// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.estructuras;

// Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
public interface ITree<T extends Comparable<T>> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void insert(T element);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean search(T element);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    IList<T> inOrder();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    IList<T> toList();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int height();
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
