// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.RoomGraph;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class Nodo<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T sujeto;
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    Nodo<T> siguiente;
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    AristaDelGrafo<T> listaAristas;
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Nodo(T sujeto){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.sujeto=sujeto;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.siguiente=null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.listaAristas=null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
