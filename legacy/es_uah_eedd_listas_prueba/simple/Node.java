// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.simple;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class Node<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T value;
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    Node<T> next;

    //Creamos vagon, el NODO
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    Node(T value){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.value = value;//Variable con la direcciÃ³n del dato
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.next =null;//Variable con la direccion de otros nodos
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    //Si no ponemos ni public ni private las clases de un mismo paquete pueden acceder a sus variables con (.)
    //Si pusieramos private entonces deberiamos crear los metodos getSiguiente() y setSitguiente()
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
