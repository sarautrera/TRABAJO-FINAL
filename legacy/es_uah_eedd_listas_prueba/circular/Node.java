// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.circular;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class Node<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T value;
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    Node<T> next;
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    Node<T> prev; //Necesaria para hacerla doble circular

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    Node(T value){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.value=value;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.next = this; // Por defecto se apunta a sÃ­ mismo
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.prev = this;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
