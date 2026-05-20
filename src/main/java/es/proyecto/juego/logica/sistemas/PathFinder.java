package es.proyecto.juego.logica.sistemas;

import es.proyecto.juego.estructuras.IGraph;
import es.proyecto.juego.estructuras.IList;

public class PathFinder {
    private final IGraph<Integer> roomGraph;

    public PathFinder(IGraph<Integer> roomGraph) {
        this.roomGraph = roomGraph;
    }

    public int getMinRoomsToExit(int currentRoomId, int exitRoomId) {
        return roomGraph.shortestDistance(currentRoomId, exitRoomId);
    }

    public IList<Integer> getPathToExit(int currentRoomId, int exitRoomId) {
        return roomGraph.shortestPath(currentRoomId, exitRoomId);
    }
}
