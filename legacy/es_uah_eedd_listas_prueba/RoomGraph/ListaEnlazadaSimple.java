// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.RoomGraph;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
class ListaEnlazadaSimple<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T nodo;
    //El padre es el anterior al nodo
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T padre;
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    ListaEnlazadaSimple<T> siguiente;
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public ListaEnlazadaSimple(T nodo, T padre){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.nodo=nodo;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.padre=padre;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.siguiente=null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
