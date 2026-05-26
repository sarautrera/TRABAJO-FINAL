/*
 * Resumen del fichero: Declara las operaciones basicas que debe ofrecer un grafo usado por el proyecto.
 */
package es.proyecto.juego.estructuras;

public interface IGraph<T> {
    void addNode(T node);

    void addEdge(T from, T to);

    void addEdge(T from, T to, int weight);

    void addUndirectedEdge(T first, T second);

    void addUndirectedEdge(T first, T second, int weight);

    IList<T> getNeighbors(T node);

    IList<T> bfs(T start);

    IList<T> shortestPath(T from, T to);

    int shortestDistance(T from, T to);

    boolean contains(T node);

    int nodeCount();
}
