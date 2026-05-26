// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyGraph;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Enemy;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Player;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.DoorLockedException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.GameAlreadyOverException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.InvalidAttackException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.InvalidMoveException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.InventoryFullException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Armor;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Item;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Key;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Potion;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Weapon;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Cell;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.CellType;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Room;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.sistemas.CombatSystem;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.sistemas.EventLog;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.sistemas.PathFinder;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.sistemas.TurnManager;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.persistencia.GameSave;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.persistencia.LevelConfig;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.BufferedReader;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.FileReader;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.FileWriter;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.IOException;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class GameEngineImpl implements IGameEngine {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private static final int DEFAULT_MAX_TURNS = 50;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private static final int START_ROOM_ID = 0;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private static final int EXIT_ROOM_ID = 1;

    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private IList<Room> rooms;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private MyGraph<Integer> roomGraph;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Player player;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Room currentRoom;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private TurnManager turnManager;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private PathFinder pathFinder;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private EventLog eventLog;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private boolean gameOver;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private boolean victory;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int exitRoomId = EXIT_ROOM_ID;

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void loadConfig(String jsonPath) throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        LevelConfig.GameConfig config = new LevelConfig().load(jsonPath);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        applyConfig(config);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        eventLog.add("Configuracion cargada desde " + jsonPath);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void newGame() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        rooms = new MyLinkedList<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        roomGraph = new MyGraph<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        turnManager = new TurnManager(DEFAULT_MAX_TURNS);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        eventLog = new EventLog();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        gameOver = false;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        victory = false;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        exitRoomId = EXIT_ROOM_ID;

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room entrance = createEntranceRoom();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room exitRoom = createExitRoom();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        rooms.add(entrance);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        rooms.add(exitRoom);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        roomGraph.addUndirectedEdge(START_ROOM_ID, EXIT_ROOM_ID);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        currentRoom = entrance;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        currentRoom.setVisited(true);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        player = new Player("Heroe", 100, 3, 10, 3, 5, 3, new MyLinkedList<Item>());
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        pathFinder = new PathFinder(roomGraph);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        eventLog.add("Nueva partida iniciada en " + currentRoom.getName());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void loadGame(String jsonPath) throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameSave.SaveData saveData = new GameSave().load(jsonPath);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        newGame();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        currentRoom = findRoomById(saveData.habitacionActual);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        currentRoom.setVisited(true);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.setPosition(saveData.fila, saveData.col);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (saveData.vidaActual < player.getHp()) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            player.takeDamage(player.getHp() - saveData.vidaActual);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < saveData.turnoActual; i++) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            turnManager.endRound();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        updateDefeatState();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        eventLog.add("Partida cargada desde " + jsonPath);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void saveGame(String jsonPath) throws IOException {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameStarted();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        FileWriter writer = new FileWriter(jsonPath);
        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write(buildTemporarySaveJson());
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } finally {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.close();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        eventLog.add("Partida guardada temporalmente en " + jsonPath);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean movePlayer(int targetRow, int targetCol) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameStarted();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameIsActive();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!turnManager.canMove()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidMoveException("El movimiento ya fue usado o no es turno del jugador");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!currentRoom.isInside(targetRow, targetCol)) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidMoveException("Destino fuera de la habitacion: " + targetRow + ", " + targetCol);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (player.getRow() == targetRow && player.getCol() == targetCol) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidMoveException("El jugador ya esta en la celda destino");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IList<int[]> reachableCells = pathFinder.getReachableCells(currentRoom, player);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!containsCell(reachableCells, targetRow, targetCol)) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidMoveException("La celda destino no es alcanzable este turno");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int previousRow = player.getRow();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int previousCol = player.getCol();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.setPosition(targetRow, targetCol);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        turnManager.markMovementUsed();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        eventLog.add("Jugador movido de (" + previousRow + ", " + previousCol + ") a ("
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                + targetRow + ", " + targetCol + ")");
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean attack(int targetRow, int targetCol) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameStarted();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameIsActive();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!turnManager.canAct()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidAttackException("La accion ya fue usada o no es turno del jugador");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!currentRoom.isInside(targetRow, targetCol)) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidAttackException("Objetivo fuera de la habitacion: " + targetRow + ", " + targetCol);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!containsCell(pathFinder.getAttackTargets(currentRoom, player), targetRow, targetCol)) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidAttackException("No hay enemigo atacable en la celda indicada");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Enemy enemy = currentRoom.getEnemyAt(targetRow, targetCol);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (enemy == null || !enemy.isAlive()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidAttackException("No hay enemigo vivo en la celda indicada");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int damage = CombatSystem.calculateDamage(player.getEffectiveAttack(), enemy.getEffectiveDefense());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        enemy.takeDamage(damage);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        turnManager.markActionUsed();

        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (enemy.isAlive()) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            eventLog.add("Jugador ataca a " + enemy.getName() + " e inflige " + damage + " de dano");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } else {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            currentRoom.removeEnemy(enemy);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            eventLog.add("Jugador derrota a " + enemy.getName() + " con " + damage + " de dano");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean useItem(int inventoryIndex) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameStarted();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameIsActive();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!turnManager.canAct()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("La accion ya fue usada o no es turno del jugador");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (inventoryIndex < 0 || inventoryIndex >= player.getInventory().size()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IndexOutOfBoundsException("Indice de inventario fuera de rango: " + inventoryIndex);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Item item = player.getInventoryItem(inventoryIndex);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.useInventoryItem(inventoryIndex);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        turnManager.markActionUsed();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        eventLog.add("Jugador usa " + item.getName());
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean pickItem(int row, int col) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameStarted();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameIsActive();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!turnManager.canAct()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("La accion ya fue usada o no es turno del jugador");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!currentRoom.isInside(row, col)) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidMoveException("Item fuera de la habitacion: " + row + ", " + col);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!currentRoom.isAdjacent(player.getRow(), player.getCol(), row, col)) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidMoveException("El item debe estar en una celda adyacente");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!currentRoom.getCell(row, col).hasItem()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidMoveException("No hay item en la celda indicada");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (player.isInventoryFull()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InventoryFullException("El inventario esta lleno");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Item item = currentRoom.takeItem(row, col);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.addItem(item);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        turnManager.markActionUsed();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        eventLog.add("Jugador recoge " + item.getName());
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean openDoor(int row, int col) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameStarted();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameIsActive();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!turnManager.canAct()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("La accion ya fue usada o no es turno del jugador");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!currentRoom.isInside(row, col)) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidMoveException("Puerta fuera de la habitacion: " + row + ", " + col);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!currentRoom.isAdjacent(player.getRow(), player.getCol(), row, col)) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidMoveException("La puerta debe estar en una celda adyacente");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Cell door = currentRoom.getCell(row, col);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!door.isDoor()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InvalidMoveException("La celda indicada no es una puerta");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (door.isDoorLocked() && !player.hasKeyForDoor(door.getDoorTargetId())) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new DoorLockedException("La puerta esta bloqueada y falta la llave necesaria");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        door.setDoorOpen(true);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        turnManager.markActionUsed();

        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (door.isExteriorExit()) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            victory = true;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            gameOver = true;
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            eventLog.add("Victoria: el jugador ha abierto la salida exterior");
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return true;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room targetRoom = findRoomById(door.getDoorTargetId());
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room previousRoom = currentRoom;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        currentRoom = targetRoom;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        currentRoom.setVisited(true);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        placePlayerAtRoomEntry(currentRoom);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        eventLog.add("Jugador pasa de " + previousRoom.getName() + " a " + currentRoom.getName());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        turnManager.endPlayerTurn();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        updateDefeatState();
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void endTurn() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameStarted();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameIsActive();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        eventLog.add("Termina el turno del jugador");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        runEnemyTurns();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        turnManager.endPlayerTurn();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        updateDefeatState();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IGameState getState() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameStarted();
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return new GameStateSnapshot(
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                player,
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                currentRoom,
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                turnManager,
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                pathFinder,
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                eventLog,
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                currentRoom.getId(),
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                exitRoomId,
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                gameOver,
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                victory
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        );
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void applyConfig(LevelConfig.GameConfig config) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        rooms = new MyLinkedList<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        roomGraph = new MyGraph<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        turnManager = new TurnManager(config.turnosMaximos <= 0 ? DEFAULT_MAX_TURNS : config.turnosMaximos);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        eventLog = new EventLog();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        gameOver = false;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        victory = false;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        exitRoomId = config.habitacionSalida;

        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < config.habitaciones.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            LevelConfig.HabitacionConfig roomConfig = config.habitaciones.get(i);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Room room = new Room(roomConfig.id, roomConfig.nombre, roomConfig.filas, roomConfig.columnas,
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    new MyLinkedList<Enemy>());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            room.setVisited(roomConfig.visitadaInicialmente);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            applyCells(room, roomConfig.celdas);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            rooms.add(room);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            roomGraph.addNode(room.getId());
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < config.conexiones.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            LevelConfig.ConexionConfig connection = config.conexiones.get(i);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (connection.dirigida) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                roomGraph.addEdge(connection.de, connection.a, connection.peso);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                roomGraph.addUndirectedEdge(connection.de, connection.a, connection.peso);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        LevelConfig.JugadorConfig playerConfig = config.jugadorInicial;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<Item> inventory = new MyLinkedList<>();
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < playerConfig.inventario.size(); i++) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            inventory.add(createItem(playerConfig.inventario.get(i)));
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        player = new Player(playerConfig.nombre, playerConfig.vidaMaxima, playerConfig.velocidad,
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                playerConfig.ataqueBase, playerConfig.defensaBase, playerConfig.fila, playerConfig.columna,
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                inventory);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (playerConfig.vidaActual < player.getHp()) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            player.takeDamage(player.getHp() - playerConfig.vidaActual);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        currentRoom = findRoomById(playerConfig.habitacion);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        currentRoom.setVisited(true);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        pathFinder = new PathFinder(roomGraph);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        eventLog.add("Nueva partida iniciada en " + currentRoom.getName());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        updateDefeatState();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void applyCells(Room room, IList<LevelConfig.CeldaConfig> cellConfigs) {
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < cellConfigs.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            LevelConfig.CeldaConfig cellConfig = cellConfigs.get(i);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if ("DOOR".equals(cellConfig.tipo)) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                room.configureDoor(cellConfig.fila, cellConfig.columna, cellConfig.habitacionDestino,
                        // Comentario de estudiante: aqui se prepara una instruccion del programa.
                        cellConfig.bloqueada, cellConfig.salidaExterior);
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                room.getCell(cellConfig.fila, cellConfig.columna).setDoorOpen(cellConfig.abierta);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else if ("ENEMY".equals(cellConfig.tipo)) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                Enemy enemy = new Enemy(cellConfig.enemigo.nombre, cellConfig.enemigo.vidaMaxima,
                        // Comentario de estudiante: aqui se prepara una instruccion del programa.
                        cellConfig.enemigo.velocidad, cellConfig.enemigo.ataque, cellConfig.enemigo.defensa,
                        // Comentario de estudiante: aqui se prepara una instruccion del programa.
                        cellConfig.fila, cellConfig.columna);
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (cellConfig.enemigo.vidaActual < enemy.getHp()) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    enemy.takeDamage(enemy.getHp() - cellConfig.enemigo.vidaActual);
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                room.placeEnemy(enemy);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else if ("ITEM".equals(cellConfig.tipo)) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                room.placeItem(cellConfig.fila, cellConfig.columna, createItem(cellConfig.item));
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else if ("TRAP".equals(cellConfig.tipo)) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                room.getCell(cellConfig.fila, cellConfig.columna).configureTrap(cellConfig.danoTrampa);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else if ("WALL".equals(cellConfig.tipo)) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                room.getCell(cellConfig.fila, cellConfig.columna).setType(CellType.WALL);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private Item createItem(LevelConfig.ItemConfig itemConfig) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (itemConfig == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("Config de item no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if ("Weapon".equals(itemConfig.tipo) || "WEAPON".equals(itemConfig.tipo)) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return new Weapon(itemConfig.nombre, itemConfig.ataqueBonus);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if ("Armor".equals(itemConfig.tipo) || "ARMOR".equals(itemConfig.tipo)) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return new Armor(itemConfig.nombre, itemConfig.defensaBonus);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if ("Key".equals(itemConfig.tipo) || "KEY".equals(itemConfig.tipo)) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return new Key(itemConfig.nombre, itemConfig.puertaObjetivo);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return new Potion(itemConfig.nombre, itemConfig.curacion);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<int[]> getReachableCells() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameStarted();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (gameOver || !turnManager.canMove()) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return new MyLinkedList<int[]>();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return pathFinder.getReachableCells(currentRoom, player);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<int[]> getAttackTargets() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureGameStarted();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (gameOver || !turnManager.canAct()) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return new MyLinkedList<int[]>();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return pathFinder.getAttackTargets(currentRoom, player);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private Room createEntranceRoom() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = new Room(START_ROOM_ID, "Entrada", 6, 7, new MyLinkedList<Enemy>());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.configureDoor(0, 3, EXIT_ROOM_ID, false, false);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.placeEnemy(new Enemy("Enemigo basico", 20, 2, 5, 2, 3, 2));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.placeItem(1, 5, new Potion("Pocion", 20));
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return room;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private Room createExitRoom() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = new Room(EXIT_ROOM_ID, "Sala final", 5, 5, new MyLinkedList<Enemy>());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.configureDoor(4, 2, -1, false, true);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return room;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void ensureGameStarted() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (player == null || currentRoom == null || turnManager == null || pathFinder == null || eventLog == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("La partida no esta iniciada. Llama primero a newGame().");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void validateJsonFile(String jsonPath) throws IOException {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (jsonPath == null || jsonPath.length() == 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IOException("La ruta JSON no puede estar vacia");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String content = readTextFile(jsonPath);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (content.length() == 0 || content.charAt(0) != '{') {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IOException("El fichero no parece un JSON valido: " + jsonPath);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private String readTextFile(String path) throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        BufferedReader reader = new BufferedReader(new FileReader(path));
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StringBuilder builder = new StringBuilder();
        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            String line = reader.readLine();
            // Comentario de estudiante: aqui empieza un bucle while.
            while (line != null) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                builder.append(line.trim());
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                line = reader.readLine();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } finally {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            reader.close();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return builder.toString();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private String buildTemporarySaveJson() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StringBuilder builder = new StringBuilder();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("{\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("  \"version\": \"temporal-track-b\",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("  \"timestampMillis\": ").append(System.currentTimeMillis()).append(",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("  \"turnoActual\": ").append(turnManager.getTurnCount()).append(",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("  \"turnosRestantes\": ").append(turnManager.getTurnsLeft()).append(",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("  \"gameOver\": ").append(gameOver).append(",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("  \"victory\": ").append(victory).append(",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("  \"jugador\": {\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("    \"vidaActual\": ").append(player.getHp()).append(",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("    \"vidaMaxima\": ").append(player.getMaxHp()).append(",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("    \"habitacionActual\": ").append(currentRoom.getId()).append(",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("    \"fila\": ").append(player.getRow()).append(",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("    \"col\": ").append(player.getCol()).append(",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("    \"ataque\": ").append(player.getEffectiveAttack()).append(",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("    \"defensa\": ").append(player.getEffectiveDefense()).append(",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("    \"inventarioSize\": ").append(player.getInventory().size()).append("\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("  },\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("  \"habitacion\": {\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("    \"id\": ").append(currentRoom.getId()).append(",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("    \"nombre\": \"").append(escapeJson(currentRoom.getName())).append("\",\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("    \"enemigosVivos\": ").append(countAliveEnemies(currentRoom)).append("\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("  },\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("  \"ultimoEvento\": \"").append(escapeJson(eventLog.getLastEvent())).append("\"\n");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        builder.append("}\n");
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return builder.toString();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private int countAliveEnemies(Room room) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int count = 0;
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < room.getEnemies().size(); i++) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (room.getEnemies().get(i).isAlive()) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                count++;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return count;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private String escapeJson(String text) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StringBuilder escaped = new StringBuilder();
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < text.length(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            char current = text.charAt(i);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (current == '"' || current == '\\') {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                escaped.append('\\');
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            escaped.append(current);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return escaped.toString();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void ensureGameIsActive() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (gameOver) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new GameAlreadyOverException("La partida ya ha terminado");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private boolean containsCell(IList<int[]> cells, int row, int col) {
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < cells.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int[] cell = cells.get(i);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (cell[0] == row && cell[1] == col) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return true;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return false;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private Room findRoomById(int roomId) {
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < rooms.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Room room = rooms.get(i);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (room.getId() == roomId) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return room;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
        throw new IllegalStateException("Habitacion destino no encontrada: " + roomId);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void placePlayerAtRoomEntry(Room room) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int preferredRow = room.getRows() - 1;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int preferredCol = room.getCols() / 2;
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (room.getCell(preferredRow, preferredCol).getType() == CellType.EMPTY) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            player.setPosition(preferredRow, preferredCol);
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int row = 0; row < room.getRows(); row++) {
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int col = 0; col < room.getCols(); col++) {
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (room.getCell(row, col).getType() == CellType.EMPTY) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    player.setPosition(row, col);
                    // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                    return;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
        throw new IllegalStateException("La habitacion destino no tiene celda de entrada libre");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void runEnemyTurns() {
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < currentRoom.getEnemies().size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Enemy enemy = currentRoom.getEnemies().get(i);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (!enemy.isAlive()) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                continue;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (enemy.isAdjacentTo(player.getRow(), player.getCol())) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                int damage = enemy.attack(player);
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                eventLog.add(enemy.getName() + " ataca al jugador e inflige " + damage + " de dano");
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (!player.isAlive()) {
                    // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                    return;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                int previousRow = enemy.getRow();
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                int previousCol = enemy.getCol();
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (enemy.moveOneStepToward(player, currentRoom)) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    eventLog.add(enemy.getName() + " se mueve de (" + previousRow + ", " + previousCol
                            // Comentario de estudiante: aqui se prepara una instruccion del programa.
                            + ") a (" + enemy.getRow() + ", " + enemy.getCol() + ")");
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void updateDefeatState() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!player.isAlive()) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            gameOver = true;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            victory = false;
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            eventLog.add("Derrota: el jugador ha muerto");
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (turnManager.isTimeUp()) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            gameOver = true;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            victory = false;
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            eventLog.add("Derrota: se han agotado los turnos");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    private static final class GameStateSnapshot implements IGameState {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final Player player;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final Room currentRoom;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final TurnManager turnManager;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final PathFinder pathFinder;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final EventLog eventLog;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final int currentRoomId;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final int exitRoomId;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final boolean gameOver;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final boolean victory;

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        private GameStateSnapshot(Player player, Room currentRoom, TurnManager turnManager,
                                  // Comentario de estudiante: aqui se prepara una instruccion del programa.
                                  PathFinder pathFinder, EventLog eventLog, int currentRoomId,
                                  // Comentario de estudiante: aqui se prepara una instruccion del programa.
                                  int exitRoomId, boolean gameOver, boolean victory) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.player = player;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.currentRoom = currentRoom;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.turnManager = turnManager;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.pathFinder = pathFinder;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.eventLog = eventLog;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.currentRoomId = currentRoomId;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.exitRoomId = exitRoomId;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.gameOver = gameOver;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.victory = victory;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getPlayerRow() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return player.getRow();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getPlayerCol() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return player.getCol();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getPlayerHp() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return player.getHp();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getPlayerMaxHp() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return player.getMaxHp();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getPlayerSpeed() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return player.getSpeed();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getPlayerAttack() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return player.getEffectiveAttack();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getPlayerDefense() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return player.getEffectiveDefense();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public String getEquippedWeaponName() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return player.getEquippedWeapon() == null ? "" : player.getEquippedWeapon().getName();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public String getEquippedArmorName() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return player.getEquippedArmor() == null ? "" : player.getEquippedArmor().getName();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public IList<Item> getInventory() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return copyInventory(player.getInventory());
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getMaxInventorySize() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return player.getMaxInventorySize();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public boolean isInventoryFull() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return player.isInventoryFull();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getCurrentRoomId() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return currentRoom.getId();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public String getCurrentRoomName() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return currentRoom.getName();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getCurrentRoomRows() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return currentRoom.getRows();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getCurrentRoomCols() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return currentRoom.getCols();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public Room getCurrentRoom() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return copyRoom(currentRoom);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getTurnCount() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return turnManager.getTurnCount();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getTurnsLeft() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return turnManager.getTurnsLeft();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public boolean canPlayerMove() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return !gameOver && turnManager.canMove();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public boolean canPlayerAct() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return !gameOver && turnManager.canAct();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getMinRoomsToExit() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return pathFinder.getMinRoomsToExit(currentRoomId, exitRoomId);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public int getDistanceToNearestDoor() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return pathFinder.getDistanceToNearestDoor(currentRoom, player);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public IList<Integer> getPathToExit() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return copyIntegerList(pathFinder.getPathToExit(currentRoomId, exitRoomId));
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public IList<String> getEventLog() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return copyStringList(eventLog.getEvents());
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public String getLastEvent() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return eventLog.getLastEvent();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public boolean isGameOver() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return gameOver;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public boolean isVictory() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return victory;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static IList<Item> copyInventory(IList<Item> source) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            MyLinkedList<Item> copy = new MyLinkedList<>();
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int i = 0; i < source.size(); i++) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                copy.add(copyItem(source.get(i)));
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return copy;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static IList<Integer> copyIntegerList(IList<Integer> source) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            MyLinkedList<Integer> copy = new MyLinkedList<>();
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int i = 0; i < source.size(); i++) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                copy.add(source.get(i));
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return copy;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static IList<String> copyStringList(IList<String> source) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            MyLinkedList<String> copy = new MyLinkedList<>();
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int i = 0; i < source.size(); i++) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                copy.add(source.get(i));
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return copy;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static Room copyRoom(Room source) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Room copy = new Room(source.getId(), source.getName(), source.getRows(), source.getCols(),
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    new MyLinkedList<Enemy>());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            copy.setVisited(source.isVisited());
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int row = 0; row < source.getRows(); row++) {
                // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
                for (int col = 0; col < source.getCols(); col++) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    Cell cellCopy = copyCell(source.getCell(row, col));
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    copy.setCell(row, col, cellCopy);
                    // Comentario de estudiante: aqui se comprueba una condicion con if.
                    if (cellCopy.hasEnemy()) {
                        // Comentario de estudiante: aqui se prepara una instruccion del programa.
                        copy.getEnemies().add(cellCopy.getEnemy());
                    // Comentario de estudiante: aqui se cierra un bloque de codigo.
                    }
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return copy;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static Cell copyCell(Cell source) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Cell copy = new Cell(CellType.EMPTY);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (source.getType() == CellType.ITEM) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                copy.setItem(copyItem(source.getItem()));
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else if (source.getType() == CellType.ENEMY) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                copy.setEnemy(copyEnemy(source.getEnemy()));
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else if (source.getType() == CellType.DOOR) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                copy.configureDoor(source.getDoorTargetId(), source.isDoorLocked(), source.isExteriorExit());
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                copy.setDoorOpen(source.isDoorOpen());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else if (source.getType() == CellType.TRAP) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                copy.configureTrap(source.getTrapDamage());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                copy.setType(source.getType());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return copy;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static Enemy copyEnemy(Enemy source) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (source == null) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return null;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Enemy copy = new Enemy(source.getName(), source.getMaxHp(), source.getSpeed(),
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    source.getEffectiveAttack(), source.getEffectiveDefense(), source.getRow(), source.getCol());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            copy.takeDamage(source.getMaxHp() - source.getHp());
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return copy;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static Item copyItem(Item source) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (source instanceof Weapon) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                Weapon weapon = (Weapon) source;
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return new Weapon(weapon.getName(), weapon.getAttackBonus());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (source instanceof Armor) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                Armor armor = (Armor) source;
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return new Armor(armor.getName(), armor.getDefenseBonus());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (source instanceof Potion) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                Potion potion = (Potion) source;
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                Potion copy = new Potion(potion.getName(), potion.getHpRestore());
                // Comentario de estudiante: aqui empieza un bucle while.
                while (copy.getUsesLeft() > source.getUsesLeft()) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    copy.consumeUse();
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return copy;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (source instanceof Key) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                Key key = (Key) source;
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return new Key(key.getName(), key.getTargetDoorId());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("Tipo de item no soportado en snapshot: " + source.getClass().getName());
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
