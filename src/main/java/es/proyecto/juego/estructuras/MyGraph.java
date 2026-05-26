// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.estructuras;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class MyGraph<T> implements IGraph<T> {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private GraphNode<T> firstNode;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int nodeCount;

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void addNode(T node) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (contains(node)) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GraphNode<T> graphNode = new GraphNode<>(node);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        graphNode.next = firstNode;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        firstNode = graphNode;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        nodeCount++;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void addEdge(T from, T to) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        addEdge(from, to, 1);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void addEdge(T from, T to, int weight) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (weight < 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El peso no puede ser negativo");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        addNode(from);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        addNode(to);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GraphNode<T> fromNode = findNode(from);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!hasEdge(fromNode, to)) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Edge<T> edge = new Edge<>(to, weight);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            edge.next = fromNode.firstEdge;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            fromNode.firstEdge = edge;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void addUndirectedEdge(T first, T second) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        addUndirectedEdge(first, second, 1);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void addUndirectedEdge(T first, T second, int weight) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        addEdge(first, second, weight);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        addEdge(second, first, weight);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<T> getNeighbors(T node) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GraphNode<T> graphNode = findExistingNode(node);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<T> neighbors = new MyLinkedList<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Edge<T> edge = graphNode.firstEdge;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (edge != null) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            neighbors.add(edge.to);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            edge = edge.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return neighbors;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<T> bfs(T start) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        findExistingNode(start);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<T> visited = new MyLinkedList<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedQueue<T> pending = new MyLinkedQueue<>();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        visited.add(start);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        pending.enqueue(start);

        // Comentario de estudiante: aqui empieza un bucle while.
        while (!pending.isEmpty()) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            T current = pending.dequeue();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            GraphNode<T> currentNode = findNode(current);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Edge<T> edge = currentNode.firstEdge;
            // Comentario de estudiante: aqui empieza un bucle while.
            while (edge != null) {
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (!visited.contains(edge.to)) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    visited.add(edge.to);
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    pending.enqueue(edge.to);
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                edge = edge.next;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return visited;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<T> shortestPath(T from, T to) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        findExistingNode(from);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        findExistingNode(to);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        SearchResult<T> result = shortestSearch(from, to);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<T> path = new MyLinkedList<>();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (result.targetRecord == null) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return path;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        PathRecord<T> current = result.targetRecord;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (current != null) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            path.add(0, current.value);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            current = current.previous;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return path;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int shortestDistance(T from, T to) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        findExistingNode(from);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        findExistingNode(to);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        SearchResult<T> result = shortestSearch(from, to);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return result.targetRecord == null ? -1 : result.targetRecord.distance;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean contains(T node) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return findNode(node) != null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int nodeCount() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return nodeCount;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private SearchResult<T> shortestSearch(T from, T to) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<PathRecord<T>> records = new MyLinkedList<>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        addRecordsForAllNodes(records);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        PathRecord<T> startRecord = findRecord(records, from);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        startRecord.distance = 0;

        // Comentario de estudiante: aqui empieza un bucle while.
        while (true) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            PathRecord<T> currentRecord = findUnvisitedWithSmallestDistance(records);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (currentRecord == null) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                break;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            currentRecord.visited = true;
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (equalsValue(currentRecord.value, to)) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return new SearchResult<>(currentRecord);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            GraphNode<T> currentNode = findNode(currentRecord.value);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Edge<T> edge = currentNode.firstEdge;
            // Comentario de estudiante: aqui empieza un bucle while.
            while (edge != null) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                PathRecord<T> nextRecord = findRecord(records, edge.to);
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (!nextRecord.visited && currentRecord.distance + edge.weight < nextRecord.distance) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    nextRecord.distance = currentRecord.distance + edge.weight;
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    nextRecord.previous = currentRecord;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                edge = edge.next;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return new SearchResult<>(null);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void addRecordsForAllNodes(MyLinkedList<PathRecord<T>> records) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GraphNode<T> current = firstNode;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (current != null) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            records.add(new PathRecord<>(current.value, null, Integer.MAX_VALUE));
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            current = current.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private PathRecord<T> findUnvisitedWithSmallestDistance(IList<PathRecord<T>> records) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        PathRecord<T> smallest = null;
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < records.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            PathRecord<T> candidate = records.get(i);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (!candidate.visited && candidate.distance != Integer.MAX_VALUE
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    && (smallest == null || candidate.distance < smallest.distance)) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                smallest = candidate;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return smallest;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private PathRecord<T> findRecord(IList<PathRecord<T>> records, T value) {
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < records.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            PathRecord<T> record = records.get(i);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (equalsValue(record.value, value)) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return record;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private GraphNode<T> findExistingNode(T value) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GraphNode<T> node = findNode(value);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (node == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("Nodo no encontrado: " + value);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return node;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private GraphNode<T> findNode(T value) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GraphNode<T> current = firstNode;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (current != null) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (equalsValue(current.value, value)) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return current;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            current = current.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private boolean hasEdge(GraphNode<T> fromNode, T to) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Edge<T> edge = fromNode.firstEdge;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (edge != null) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (equalsValue(edge.to, to)) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return true;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            edge = edge.next;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return false;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private boolean equalsValue(T first, T second) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return first == null ? second == null : first.equals(second);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    private static final class GraphNode<T> {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final T value;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private Edge<T> firstEdge;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private GraphNode<T> next;

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private GraphNode(T value) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.value = value;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    private static final class Edge<T> {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final T to;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final int weight;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private Edge<T> next;

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private Edge(T to, int weight) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.to = to;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.weight = weight;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    private static final class PathRecord<T> {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final T value;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private PathRecord<T> previous;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private int distance;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private boolean visited;

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private PathRecord(T value, PathRecord<T> previous, int distance) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.value = value;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.previous = previous;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.distance = distance;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    private static final class SearchResult<T> {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final PathRecord<T> targetRecord;

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private SearchResult(PathRecord<T> targetRecord) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.targetRecord = targetRecord;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
