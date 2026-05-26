// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica.sistemas;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IGraph;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Enemy;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Player;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Room;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class PathFinder {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final IGraph<Integer> roomGraph;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public PathFinder(IGraph<Integer> roomGraph) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (roomGraph == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El grafo de habitaciones no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.roomGraph = roomGraph;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<int[]> getReachableCells(Room room, Player player) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (player == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El jugador no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return MatrixBFS.reachableCells(room, player.getRow(), player.getCol(), player.getSpeed());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<int[]> getAttackTargets(Room room, Player player) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (room == null || player == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("Habitacion y jugador son obligatorios");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<int[]> targets = new MyLinkedList<>();
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < room.getEnemies().size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Enemy enemy = room.getEnemies().get(i);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (enemy.isAlive() && room.isAdjacent(player.getRow(), player.getCol(), enemy.getRow(), enemy.getCol())) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                targets.add(new int[]{enemy.getRow(), enemy.getCol()});
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return targets;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getDistanceToNearestDoor(Room room, Player player) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (room == null || player == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("Habitacion y jugador son obligatorios");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IList<int[]> doors = room.getDoors();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int bestDistance = -1;
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < doors.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int[] door = doors.get(i);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int distance = getDistanceToDoor(room, player, door[0], door[1]);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (distance >= 0 && (bestDistance == -1 || distance < bestDistance)) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                bestDistance = distance;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return bestDistance;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getMinRoomsToExit(int currentRoomId, int exitRoomId) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return roomGraph.shortestDistance(currentRoomId, exitRoomId);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<Integer> getPathToExit(int currentRoomId, int exitRoomId) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return roomGraph.shortestPath(currentRoomId, exitRoomId);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private int getDistanceToDoor(Room room, Player player, int doorRow, int doorCol) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int directDistance = MatrixBFS.distance(room, player.getRow(), player.getCol(), doorRow, doorCol);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (directDistance >= 0) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return directDistance;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int bestAdjacent = -1;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        bestAdjacent = bestDistanceToAdjacent(room, player, doorRow - 1, doorCol, bestAdjacent);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        bestAdjacent = bestDistanceToAdjacent(room, player, doorRow + 1, doorCol, bestAdjacent);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        bestAdjacent = bestDistanceToAdjacent(room, player, doorRow, doorCol - 1, bestAdjacent);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        bestAdjacent = bestDistanceToAdjacent(room, player, doorRow, doorCol + 1, bestAdjacent);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return bestAdjacent < 0 ? -1 : bestAdjacent + 1;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private int bestDistanceToAdjacent(Room room, Player player, int row, int col, int currentBest) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!room.isInside(row, col) || !room.getCell(row, col).isWalkable()) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return currentBest;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int distance = MatrixBFS.distance(room, player.getRow(), player.getCol(), row, col);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (distance >= 0 && (currentBest == -1 || distance < currentBest)) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return distance;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return currentBest;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
