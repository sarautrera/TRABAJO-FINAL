// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.simple;

// Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
public interface IList<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void add(T dato);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T get(int dato);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean isEmpty();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getSize();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    MiIterador<T> getIterador();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void clear();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T getFirst();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T getLast();
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean contains(T dato);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T remove(int index);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T remove(T dato);
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void add(int indice, T dato);
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
