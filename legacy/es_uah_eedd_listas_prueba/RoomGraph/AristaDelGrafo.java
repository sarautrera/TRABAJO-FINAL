// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.RoomGraph;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class AristaDelGrafo<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    String predicado;
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    T objeto;
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    AristaDelGrafo<T> siguiente;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public AristaDelGrafo(String predicado, T objeto){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.predicado=predicado;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.objeto=objeto;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.siguiente=null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
