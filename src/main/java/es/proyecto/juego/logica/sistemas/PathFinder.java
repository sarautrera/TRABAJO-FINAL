/*
 * Resumen del fichero: Agrupa calculos de movimiento y ataque usando la habitacion actual y el grafo.
 */
package es.proyecto.juego.logica.sistemas;

import es.proyecto.juego.estructuras.IGraph;
import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.MyLinkedList;
import es.proyecto.juego.logica.entidades.Enemy;
import es.proyecto.juego.logica.entidades.Player;
import es.proyecto.juego.logica.mundo.Room;

public class PathFinder {
    private final IGraph<Integer> roomGraph;

    public PathFinder(IGraph<Integer> roomGraph) {
        if (roomGraph == null) {
            throw new IllegalArgumentException("El grafo de habitaciones no puede ser null");
        }
        this.roomGraph = roomGraph;
    }

    public IList<int[]> getReachableCells(Room room, Player player) {
        if (player == null) {
            throw new IllegalArgumentException("El jugador no puede ser null");
        }
        return MatrixBFS.reachableCells(room, player.getRow(), player.getCol(), player.getSpeed());
    }

    public IList<int[]> getAttackTargets(Room room, Player player) {
        if (room == null || player == null) {
            throw new IllegalArgumentException("Habitacion y jugador son obligatorios");
        }
        MyLinkedList<int[]> targets = new MyLinkedList<>();
        for (int i = 0; i < room.getEnemies().size(); i++) {
            Enemy enemy = room.getEnemies().get(i);
            if (enemy.isAlive() && room.isAdjacent(player.getRow(), player.getCol(), enemy.getRow(), enemy.getCol())) {
                targets.add(new int[]{enemy.getRow(), enemy.getCol()});
            }
        }
        return targets;
    }

    public int getDistanceToNearestDoor(Room room, Player player) {
        if (room == null || player == null) {
            throw new IllegalArgumentException("Habitacion y jugador son obligatorios");
        }
        IList<int[]> doors = room.getDoors();
        int bestDistance = -1;
        for (int i = 0; i < doors.size(); i++) {
            int[] door = doors.get(i);
            int distance = getDistanceToDoor(room, player, door[0], door[1]);
            if (distance >= 0 && (bestDistance == -1 || distance < bestDistance)) {
                bestDistance = distance;
            }
        }
        return bestDistance;
    }

    public int getMinRoomsToExit(int currentRoomId, int exitRoomId) {
        return roomGraph.shortestDistance(currentRoomId, exitRoomId);
    }

    public IList<Integer> getPathToExit(int currentRoomId, int exitRoomId) {
        return roomGraph.shortestPath(currentRoomId, exitRoomId);
    }

    private int getDistanceToDoor(Room room, Player player, int doorRow, int doorCol) {
        int directDistance = MatrixBFS.distance(room, player.getRow(), player.getCol(), doorRow, doorCol);
        if (directDistance >= 0) {
            return directDistance;
        }

        int bestAdjacent = -1;
        bestAdjacent = bestDistanceToAdjacent(room, player, doorRow - 1, doorCol, bestAdjacent);
        bestAdjacent = bestDistanceToAdjacent(room, player, doorRow + 1, doorCol, bestAdjacent);
        bestAdjacent = bestDistanceToAdjacent(room, player, doorRow, doorCol - 1, bestAdjacent);
        bestAdjacent = bestDistanceToAdjacent(room, player, doorRow, doorCol + 1, bestAdjacent);
        return bestAdjacent < 0 ? -1 : bestAdjacent + 1;
    }

    private int bestDistanceToAdjacent(Room room, Player player, int row, int col, int currentBest) {
        if (!room.isInside(row, col) || !room.getCell(row, col).isWalkable()) {
            return currentBest;
        }
        int distance = MatrixBFS.distance(room, player.getRow(), player.getCol(), row, col);
        if (distance >= 0 && (currentBest == -1 || distance < currentBest)) {
            return distance;
        }
        return currentBest;
    }
}
