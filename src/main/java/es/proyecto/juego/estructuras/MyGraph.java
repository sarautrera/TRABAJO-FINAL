package es.proyecto.juego.estructuras;

public class MyGraph<T> implements IGraph<T> {
    private GraphNode<T> firstNode;
    private int nodeCount;

    @Override
    public void addNode(T node) {
        if (contains(node)) {
            return;
        }
        GraphNode<T> graphNode = new GraphNode<>(node);
        graphNode.next = firstNode;
        firstNode = graphNode;
        nodeCount++;
    }

    @Override
    public void addEdge(T from, T to) {
        addEdge(from, to, 1);
    }

    @Override
    public void addEdge(T from, T to, int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("El peso no puede ser negativo");
        }
        addNode(from);
        addNode(to);
        GraphNode<T> fromNode = findNode(from);
        if (!hasEdge(fromNode, to)) {
            Edge<T> edge = new Edge<>(to, weight);
            edge.next = fromNode.firstEdge;
            fromNode.firstEdge = edge;
        }
    }

    @Override
    public IList<T> getNeighbors(T node) {
        GraphNode<T> graphNode = findExistingNode(node);
        MyLinkedList<T> neighbors = new MyLinkedList<>();
        Edge<T> edge = graphNode.firstEdge;
        while (edge != null) {
            neighbors.add(edge.to);
            edge = edge.next;
        }
        return neighbors;
    }

    @Override
    public IList<T> bfs(T start) {
        findExistingNode(start);
        MyLinkedList<T> visited = new MyLinkedList<>();
        MyLinkedQueue<T> pending = new MyLinkedQueue<>();

        visited.add(start);
        pending.enqueue(start);

        while (!pending.isEmpty()) {
            T current = pending.dequeue();
            GraphNode<T> currentNode = findNode(current);
            Edge<T> edge = currentNode.firstEdge;
            while (edge != null) {
                if (!visited.contains(edge.to)) {
                    visited.add(edge.to);
                    pending.enqueue(edge.to);
                }
                edge = edge.next;
            }
        }

        return visited;
    }

    @Override
    public IList<T> shortestPath(T from, T to) {
        findExistingNode(from);
        findExistingNode(to);
        SearchResult<T> result = shortestSearch(from, to);
        MyLinkedList<T> path = new MyLinkedList<>();
        if (result.targetRecord == null) {
            return path;
        }

        PathRecord<T> current = result.targetRecord;
        while (current != null) {
            path.add(0, current.value);
            current = current.previous;
        }
        return path;
    }

    @Override
    public int shortestDistance(T from, T to) {
        findExistingNode(from);
        findExistingNode(to);
        SearchResult<T> result = shortestSearch(from, to);
        return result.targetRecord == null ? -1 : result.targetRecord.distance;
    }

    @Override
    public boolean contains(T node) {
        return findNode(node) != null;
    }

    @Override
    public int nodeCount() {
        return nodeCount;
    }

    private SearchResult<T> shortestSearch(T from, T to) {
        MyLinkedList<PathRecord<T>> records = new MyLinkedList<>();
        addRecordsForAllNodes(records);
        PathRecord<T> startRecord = findRecord(records, from);
        startRecord.distance = 0;

        while (true) {
            PathRecord<T> currentRecord = findUnvisitedWithSmallestDistance(records);
            if (currentRecord == null) {
                break;
            }
            currentRecord.visited = true;
            if (equalsValue(currentRecord.value, to)) {
                return new SearchResult<>(currentRecord);
            }

            GraphNode<T> currentNode = findNode(currentRecord.value);
            Edge<T> edge = currentNode.firstEdge;
            while (edge != null) {
                PathRecord<T> nextRecord = findRecord(records, edge.to);
                if (!nextRecord.visited && currentRecord.distance + edge.weight < nextRecord.distance) {
                    nextRecord.distance = currentRecord.distance + edge.weight;
                    nextRecord.previous = currentRecord;
                }
                edge = edge.next;
            }
        }

        return new SearchResult<>(null);
    }

    private void addRecordsForAllNodes(MyLinkedList<PathRecord<T>> records) {
        GraphNode<T> current = firstNode;
        while (current != null) {
            records.add(new PathRecord<>(current.value, null, Integer.MAX_VALUE));
            current = current.next;
        }
    }

    private PathRecord<T> findUnvisitedWithSmallestDistance(IList<PathRecord<T>> records) {
        PathRecord<T> smallest = null;
        for (int i = 0; i < records.size(); i++) {
            PathRecord<T> candidate = records.get(i);
            if (!candidate.visited && candidate.distance != Integer.MAX_VALUE
                    && (smallest == null || candidate.distance < smallest.distance)) {
                smallest = candidate;
            }
        }
        return smallest;
    }

    private PathRecord<T> findRecord(IList<PathRecord<T>> records, T value) {
        for (int i = 0; i < records.size(); i++) {
            PathRecord<T> record = records.get(i);
            if (equalsValue(record.value, value)) {
                return record;
            }
        }
        return null;
    }

    private GraphNode<T> findExistingNode(T value) {
        GraphNode<T> node = findNode(value);
        if (node == null) {
            throw new IllegalArgumentException("Nodo no encontrado: " + value);
        }
        return node;
    }

    private GraphNode<T> findNode(T value) {
        GraphNode<T> current = firstNode;
        while (current != null) {
            if (equalsValue(current.value, value)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private boolean hasEdge(GraphNode<T> fromNode, T to) {
        Edge<T> edge = fromNode.firstEdge;
        while (edge != null) {
            if (equalsValue(edge.to, to)) {
                return true;
            }
            edge = edge.next;
        }
        return false;
    }

    private boolean equalsValue(T first, T second) {
        return first == null ? second == null : first.equals(second);
    }

    private static final class GraphNode<T> {
        private final T value;
        private Edge<T> firstEdge;
        private GraphNode<T> next;

        private GraphNode(T value) {
            this.value = value;
        }
    }

    private static final class Edge<T> {
        private final T to;
        private final int weight;
        private Edge<T> next;

        private Edge(T to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static final class PathRecord<T> {
        private final T value;
        private PathRecord<T> previous;
        private int distance;
        private boolean visited;

        private PathRecord(T value, PathRecord<T> previous, int distance) {
            this.value = value;
            this.previous = previous;
            this.distance = distance;
        }
    }

    private static final class SearchResult<T> {
        private final PathRecord<T> targetRecord;

        private SearchResult(PathRecord<T> targetRecord) {
            this.targetRecord = targetRecord;
        }
    }
}
