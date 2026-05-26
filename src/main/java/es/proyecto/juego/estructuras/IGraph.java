// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.estructuras;

// Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
public interface IGraph<T> {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void addNode(T node);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void addEdge(T from, T to);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void addEdge(T from, T to, int weight);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void addUndirectedEdge(T first, T second);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void addUndirectedEdge(T first, T second, int weight);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    IList<T> getNeighbors(T node);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    IList<T> bfs(T start);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    IList<T> shortestPath(T from, T to);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int shortestDistance(T from, T to);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean contains(T node);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int nodeCount();
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
