package es.proyecto.juego.logica;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.MyGraph;
import es.proyecto.juego.estructuras.MyLinkedList;
import es.proyecto.juego.logica.entidades.Enemy;
import es.proyecto.juego.logica.entidades.Player;
import es.proyecto.juego.logica.excepciones.DoorLockedException;
import es.proyecto.juego.logica.excepciones.GameAlreadyOverException;
import es.proyecto.juego.logica.excepciones.InvalidAttackException;
import es.proyecto.juego.logica.excepciones.InvalidMoveException;
import es.proyecto.juego.logica.items.Item;
import es.proyecto.juego.logica.items.Potion;
import es.proyecto.juego.logica.mundo.Cell;
import es.proyecto.juego.logica.mundo.CellType;
import es.proyecto.juego.logica.mundo.Room;
import es.proyecto.juego.logica.sistemas.CombatSystem;
import es.proyecto.juego.logica.sistemas.EventLog;
import es.proyecto.juego.logica.sistemas.PathFinder;
import es.proyecto.juego.logica.sistemas.TurnManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameEngineImpl implements IGameEngine {
    private static final int DEFAULT_MAX_TURNS = 50;
    private static final int START_ROOM_ID = 0;
    private static final int EXIT_ROOM_ID = 1;

    private IList<Room> rooms;
    private MyGraph<Integer> roomGraph;
    private Player player;
    private Room currentRoom;
    private TurnManager turnManager;
    private PathFinder pathFinder;
    private EventLog eventLog;
    private boolean gameOver;
    private boolean victory;

    @Override
    public void loadConfig(String jsonPath) throws IOException {
        validateJsonFile(jsonPath);
        newGame();
        eventLog.add("Configuracion cargada temporalmente desde " + jsonPath);
    }

    @Override
    public void newGame() {
        rooms = new MyLinkedList<>();
        roomGraph = new MyGraph<>();
        turnManager = new TurnManager(DEFAULT_MAX_TURNS);
        eventLog = new EventLog();
        gameOver = false;
        victory = false;

        Room entrance = createEntranceRoom();
        Room exitRoom = createExitRoom();
        rooms.add(entrance);
        rooms.add(exitRoom);

        roomGraph.addEdge(START_ROOM_ID, EXIT_ROOM_ID);
        roomGraph.addEdge(EXIT_ROOM_ID, START_ROOM_ID);

        currentRoom = entrance;
        currentRoom.setVisited(true);
        player = new Player("Heroe", 100, 3, 10, 3, 5, 3, new MyLinkedList<Item>());
        pathFinder = new PathFinder(roomGraph);

        eventLog.add("Nueva partida iniciada en " + currentRoom.getName());
    }

    @Override
    public void loadGame(String jsonPath) throws IOException {
        validateJsonFile(jsonPath);
        newGame();
        eventLog.add("Partida cargada temporalmente desde " + jsonPath);
    }

    @Override
    public void saveGame(String jsonPath) throws IOException {
        ensureGameStarted();
        FileWriter writer = new FileWriter(jsonPath);
        try {
            writer.write(buildTemporarySaveJson());
        } finally {
            writer.close();
        }
        eventLog.add("Partida guardada temporalmente en " + jsonPath);
    }

    @Override
    public boolean movePlayer(int targetRow, int targetCol) {
        ensureGameStarted();
        ensureGameIsActive();
        if (!turnManager.canMove()) {
            throw new InvalidMoveException("El movimiento ya fue usado o no es turno del jugador");
        }
        if (!currentRoom.isInside(targetRow, targetCol)) {
            throw new InvalidMoveException("Destino fuera de la habitacion: " + targetRow + ", " + targetCol);
        }
        if (player.getRow() == targetRow && player.getCol() == targetCol) {
            throw new InvalidMoveException("El jugador ya esta en la celda destino");
        }

        IList<int[]> reachableCells = pathFinder.getReachableCells(currentRoom, player);
        if (!containsCell(reachableCells, targetRow, targetCol)) {
            throw new InvalidMoveException("La celda destino no es alcanzable este turno");
        }

        int previousRow = player.getRow();
        int previousCol = player.getCol();
        player.setPosition(targetRow, targetCol);
        turnManager.markMovementUsed();
        eventLog.add("Jugador movido de (" + previousRow + ", " + previousCol + ") a ("
                + targetRow + ", " + targetCol + ")");
        return true;
    }

    @Override
    public boolean attack(int targetRow, int targetCol) {
        ensureGameStarted();
        ensureGameIsActive();
        if (!turnManager.canAct()) {
            throw new InvalidAttackException("La accion ya fue usada o no es turno del jugador");
        }
        if (!currentRoom.isInside(targetRow, targetCol)) {
            throw new InvalidAttackException("Objetivo fuera de la habitacion: " + targetRow + ", " + targetCol);
        }
        if (!containsCell(pathFinder.getAttackTargets(currentRoom, player), targetRow, targetCol)) {
            throw new InvalidAttackException("No hay enemigo atacable en la celda indicada");
        }

        Enemy enemy = currentRoom.getEnemyAt(targetRow, targetCol);
        if (enemy == null || !enemy.isAlive()) {
            throw new InvalidAttackException("No hay enemigo vivo en la celda indicada");
        }

        int damage = CombatSystem.calculateDamage(player.getEffectiveAttack(), enemy.getEffectiveDefense());
        enemy.takeDamage(damage);
        turnManager.markActionUsed();

        if (enemy.isAlive()) {
            eventLog.add("Jugador ataca a " + enemy.getName() + " e inflige " + damage + " de dano");
        } else {
            currentRoom.removeEnemy(enemy);
            eventLog.add("Jugador derrota a " + enemy.getName() + " con " + damage + " de dano");
        }
        return true;
    }

    @Override
    public boolean useItem(int inventoryIndex) {
        ensureGameStarted();
        ensureGameIsActive();
        if (!turnManager.canAct()) {
            throw new IllegalStateException("La accion ya fue usada o no es turno del jugador");
        }
        if (inventoryIndex < 0 || inventoryIndex >= player.getInventory().size()) {
            throw new IndexOutOfBoundsException("Indice de inventario fuera de rango: " + inventoryIndex);
        }

        Item item = player.getInventoryItem(inventoryIndex);
        player.useInventoryItem(inventoryIndex);
        turnManager.markActionUsed();
        eventLog.add("Jugador usa " + item.getName());
        return true;
    }

    @Override
    public boolean pickItem(int row, int col) {
        ensureGameStarted();
        ensureGameIsActive();
        if (!turnManager.canAct()) {
            throw new IllegalStateException("La accion ya fue usada o no es turno del jugador");
        }
        if (!currentRoom.isInside(row, col)) {
            throw new InvalidMoveException("Item fuera de la habitacion: " + row + ", " + col);
        }
        if (!currentRoom.isAdjacent(player.getRow(), player.getCol(), row, col)) {
            throw new InvalidMoveException("El item debe estar en una celda adyacente");
        }
        if (!currentRoom.getCell(row, col).hasItem()) {
            throw new InvalidMoveException("No hay item en la celda indicada");
        }

        Item item = currentRoom.takeItem(row, col);
        player.addItem(item);
        turnManager.markActionUsed();
        eventLog.add("Jugador recoge " + item.getName());
        return true;
    }

    @Override
    public boolean openDoor(int row, int col) {
        ensureGameStarted();
        ensureGameIsActive();
        if (!turnManager.canAct()) {
            throw new IllegalStateException("La accion ya fue usada o no es turno del jugador");
        }
        if (!currentRoom.isInside(row, col)) {
            throw new InvalidMoveException("Puerta fuera de la habitacion: " + row + ", " + col);
        }
        if (!currentRoom.isAdjacent(player.getRow(), player.getCol(), row, col)) {
            throw new InvalidMoveException("La puerta debe estar en una celda adyacente");
        }

        Cell door = currentRoom.getCell(row, col);
        if (!door.isDoor()) {
            throw new InvalidMoveException("La celda indicada no es una puerta");
        }
        if (door.isDoorLocked() && !player.hasKeyForDoor(door.getDoorTargetId())) {
            throw new DoorLockedException("La puerta esta bloqueada y falta la llave necesaria");
        }

        door.setDoorOpen(true);
        turnManager.markActionUsed();

        if (door.isExteriorExit()) {
            victory = true;
            gameOver = true;
            eventLog.add("Victoria: el jugador ha abierto la salida exterior");
            return true;
        }

        Room targetRoom = findRoomById(door.getDoorTargetId());
        Room previousRoom = currentRoom;
        currentRoom = targetRoom;
        currentRoom.setVisited(true);
        placePlayerAtRoomEntry(currentRoom);
        eventLog.add("Jugador pasa de " + previousRoom.getName() + " a " + currentRoom.getName());
        turnManager.endPlayerTurn();
        updateDefeatState();
        return true;
    }

    @Override
    public void endTurn() {
        ensureGameStarted();
        ensureGameIsActive();

        eventLog.add("Termina el turno del jugador");
        runEnemyTurns();
        turnManager.endPlayerTurn();
        updateDefeatState();
    }

    @Override
    public IGameState getState() {
        ensureGameStarted();
        return new GameStateSnapshot(
                player,
                currentRoom,
                turnManager,
                pathFinder,
                eventLog,
                currentRoom.getId(),
                EXIT_ROOM_ID,
                gameOver,
                victory
        );
    }

    @Override
    public IList<int[]> getReachableCells() {
        ensureGameStarted();
        if (gameOver || !turnManager.canMove()) {
            return new MyLinkedList<int[]>();
        }
        return pathFinder.getReachableCells(currentRoom, player);
    }

    @Override
    public IList<int[]> getAttackTargets() {
        ensureGameStarted();
        if (gameOver || !turnManager.canAct()) {
            return new MyLinkedList<int[]>();
        }
        return pathFinder.getAttackTargets(currentRoom, player);
    }

    private Room createEntranceRoom() {
        Room room = new Room(START_ROOM_ID, "Entrada", 6, 7, new MyLinkedList<Enemy>());
        room.configureDoor(0, 3, EXIT_ROOM_ID, false, false);
        room.placeEnemy(new Enemy("Enemigo basico", 20, 2, 5, 2, 3, 2));
        room.placeItem(1, 5, new Potion("Pocion", 20));
        return room;
    }

    private Room createExitRoom() {
        Room room = new Room(EXIT_ROOM_ID, "Sala final", 5, 5, new MyLinkedList<Enemy>());
        room.configureDoor(4, 2, -1, false, true);
        return room;
    }

    private void ensureGameStarted() {
        if (player == null || currentRoom == null || turnManager == null || pathFinder == null || eventLog == null) {
            throw new IllegalStateException("La partida no esta iniciada. Llama primero a newGame().");
        }
    }

    private void validateJsonFile(String jsonPath) throws IOException {
        if (jsonPath == null || jsonPath.length() == 0) {
            throw new IOException("La ruta JSON no puede estar vacia");
        }
        String content = readTextFile(jsonPath);
        if (content.length() == 0 || content.charAt(0) != '{') {
            throw new IOException("El fichero no parece un JSON valido: " + jsonPath);
        }
    }

    private String readTextFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder builder = new StringBuilder();
        try {
            String line = reader.readLine();
            while (line != null) {
                builder.append(line.trim());
                line = reader.readLine();
            }
        } finally {
            reader.close();
        }
        return builder.toString();
    }

    private String buildTemporarySaveJson() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("  \"version\": \"temporal-track-b\",\n");
        builder.append("  \"timestampMillis\": ").append(System.currentTimeMillis()).append(",\n");
        builder.append("  \"turnoActual\": ").append(turnManager.getTurnCount()).append(",\n");
        builder.append("  \"turnosRestantes\": ").append(turnManager.getTurnsLeft()).append(",\n");
        builder.append("  \"gameOver\": ").append(gameOver).append(",\n");
        builder.append("  \"victory\": ").append(victory).append(",\n");
        builder.append("  \"jugador\": {\n");
        builder.append("    \"vidaActual\": ").append(player.getHp()).append(",\n");
        builder.append("    \"vidaMaxima\": ").append(player.getMaxHp()).append(",\n");
        builder.append("    \"habitacionActual\": ").append(currentRoom.getId()).append(",\n");
        builder.append("    \"fila\": ").append(player.getRow()).append(",\n");
        builder.append("    \"col\": ").append(player.getCol()).append(",\n");
        builder.append("    \"ataque\": ").append(player.getEffectiveAttack()).append(",\n");
        builder.append("    \"defensa\": ").append(player.getEffectiveDefense()).append(",\n");
        builder.append("    \"inventarioSize\": ").append(player.getInventory().size()).append("\n");
        builder.append("  },\n");
        builder.append("  \"habitacion\": {\n");
        builder.append("    \"id\": ").append(currentRoom.getId()).append(",\n");
        builder.append("    \"nombre\": \"").append(escapeJson(currentRoom.getName())).append("\",\n");
        builder.append("    \"enemigosVivos\": ").append(countAliveEnemies(currentRoom)).append("\n");
        builder.append("  },\n");
        builder.append("  \"ultimoEvento\": \"").append(escapeJson(eventLog.getLastEvent())).append("\"\n");
        builder.append("}\n");
        return builder.toString();
    }

    private int countAliveEnemies(Room room) {
        int count = 0;
        for (int i = 0; i < room.getEnemies().size(); i++) {
            if (room.getEnemies().get(i).isAlive()) {
                count++;
            }
        }
        return count;
    }

    private String escapeJson(String text) {
        StringBuilder escaped = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);
            if (current == '"' || current == '\\') {
                escaped.append('\\');
            }
            escaped.append(current);
        }
        return escaped.toString();
    }

    private void ensureGameIsActive() {
        if (gameOver) {
            throw new GameAlreadyOverException("La partida ya ha terminado");
        }
    }

    private boolean containsCell(IList<int[]> cells, int row, int col) {
        for (int i = 0; i < cells.size(); i++) {
            int[] cell = cells.get(i);
            if (cell[0] == row && cell[1] == col) {
                return true;
            }
        }
        return false;
    }

    private Room findRoomById(int roomId) {
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (room.getId() == roomId) {
                return room;
            }
        }
        throw new IllegalStateException("Habitacion destino no encontrada: " + roomId);
    }

    private void placePlayerAtRoomEntry(Room room) {
        int preferredRow = room.getRows() - 1;
        int preferredCol = room.getCols() / 2;
        if (room.getCell(preferredRow, preferredCol).getType() == CellType.EMPTY) {
            player.setPosition(preferredRow, preferredCol);
            return;
        }

        for (int row = 0; row < room.getRows(); row++) {
            for (int col = 0; col < room.getCols(); col++) {
                if (room.getCell(row, col).getType() == CellType.EMPTY) {
                    player.setPosition(row, col);
                    return;
                }
            }
        }
        throw new IllegalStateException("La habitacion destino no tiene celda de entrada libre");
    }

    private void runEnemyTurns() {
        for (int i = 0; i < currentRoom.getEnemies().size(); i++) {
            Enemy enemy = currentRoom.getEnemies().get(i);
            if (!enemy.isAlive()) {
                continue;
            }
            if (enemy.isAdjacentTo(player.getRow(), player.getCol())) {
                int damage = enemy.attack(player);
                eventLog.add(enemy.getName() + " ataca al jugador e inflige " + damage + " de dano");
                if (!player.isAlive()) {
                    return;
                }
            } else {
                int previousRow = enemy.getRow();
                int previousCol = enemy.getCol();
                if (enemy.moveOneStepToward(player, currentRoom)) {
                    eventLog.add(enemy.getName() + " se mueve de (" + previousRow + ", " + previousCol
                            + ") a (" + enemy.getRow() + ", " + enemy.getCol() + ")");
                }
            }
        }
    }

    private void updateDefeatState() {
        if (!player.isAlive()) {
            gameOver = true;
            victory = false;
            eventLog.add("Derrota: el jugador ha muerto");
            return;
        }
        if (turnManager.isTimeUp()) {
            gameOver = true;
            victory = false;
            eventLog.add("Derrota: se han agotado los turnos");
        }
    }

    private static final class GameStateSnapshot implements IGameState {
        private final Player player;
        private final Room currentRoom;
        private final TurnManager turnManager;
        private final PathFinder pathFinder;
        private final EventLog eventLog;
        private final int currentRoomId;
        private final int exitRoomId;
        private final boolean gameOver;
        private final boolean victory;

        private GameStateSnapshot(Player player, Room currentRoom, TurnManager turnManager,
                                  PathFinder pathFinder, EventLog eventLog, int currentRoomId,
                                  int exitRoomId, boolean gameOver, boolean victory) {
            this.player = player;
            this.currentRoom = currentRoom;
            this.turnManager = turnManager;
            this.pathFinder = pathFinder;
            this.eventLog = eventLog;
            this.currentRoomId = currentRoomId;
            this.exitRoomId = exitRoomId;
            this.gameOver = gameOver;
            this.victory = victory;
        }

        @Override
        public int getPlayerRow() {
            return player.getRow();
        }

        @Override
        public int getPlayerCol() {
            return player.getCol();
        }

        @Override
        public int getPlayerHp() {
            return player.getHp();
        }

        @Override
        public int getPlayerMaxHp() {
            return player.getMaxHp();
        }

        @Override
        public int getPlayerSpeed() {
            return player.getSpeed();
        }

        @Override
        public int getPlayerAttack() {
            return player.getEffectiveAttack();
        }

        @Override
        public int getPlayerDefense() {
            return player.getEffectiveDefense();
        }

        @Override
        public IList<Item> getInventory() {
            return player.getInventory();
        }

        @Override
        public Room getCurrentRoom() {
            return currentRoom;
        }

        @Override
        public int getTurnCount() {
            return turnManager.getTurnCount();
        }

        @Override
        public int getTurnsLeft() {
            return turnManager.getTurnsLeft();
        }

        @Override
        public int getMinRoomsToExit() {
            return pathFinder.getMinRoomsToExit(currentRoomId, exitRoomId);
        }

        @Override
        public int getDistanceToNearestDoor() {
            return pathFinder.getDistanceToNearestDoor(currentRoom, player);
        }

        @Override
        public IList<Integer> getPathToExit() {
            return pathFinder.getPathToExit(currentRoomId, exitRoomId);
        }

        @Override
        public IList<String> getEventLog() {
            return eventLog.getEvents();
        }

        @Override
        public String getLastEvent() {
            return eventLog.getLastEvent();
        }

        @Override
        public boolean isGameOver() {
            return gameOver;
        }

        @Override
        public boolean isVictory() {
            return victory;
        }
    }
}
