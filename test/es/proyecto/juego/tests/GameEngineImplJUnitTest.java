// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.tests;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.GameEngineImpl;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Player;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.DoorLockedException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.GameAlreadyOverException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.InvalidMoveException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Potion;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Cell;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.CellType;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Room;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import org.junit.jupiter.api.Test;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.File;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.FileWriter;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.IOException;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertThrows;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class GameEngineImplJUnitTest {
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void newGameInitializesQueryableState() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameEngineImpl engine = new GameEngineImpl();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.newGame();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IGameState state = engine.getState();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(5, state.getPlayerRow());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, state.getPlayerCol());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(100, state.getPlayerHp());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, state.getPlayerSpeed());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(10, state.getPlayerAttack());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, state.getPlayerDefense());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("", state.getEquippedWeaponName());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("", state.getEquippedArmorName());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(Player.MAX_INVENTORY_SIZE, state.getMaxInventorySize());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(state.isInventoryFull());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, state.getCurrentRoomId());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("Entrada", state.getCurrentRoomName());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(6, state.getCurrentRoomRows());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(7, state.getCurrentRoomCols());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(50, state.getTurnsLeft());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, state.getTurnCount());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(state.canPlayerMove());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(state.canPlayerAct());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(state.isGameOver());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(state.isVictory());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(engine.getReachableCells().size() > 0);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void movePlayerUpdatesPositionAndCanOnlyBeUsedOncePerTurn() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameEngineImpl engine = new GameEngineImpl();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.newGame();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(engine.movePlayer(4, 3));

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(4, engine.getState().getPlayerRow());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, engine.getState().getPlayerCol());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(engine.getState().canPlayerMove());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(engine.getState().canPlayerAct());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, engine.getReachableCells().size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(InvalidMoveException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.movePlayer(4, 2);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void rulesRequireAdjacentDoorItemAndAttackTargets() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameEngineImpl engine = new GameEngineImpl();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.newGame();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(InvalidMoveException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.openDoor(0, 3);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(InvalidMoveException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.pickItem(1, 5);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, engine.getAttackTargets().size());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.movePlayer(4, 2);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, engine.getAttackTargets().size());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void pickItemAddsItemToInventoryAndUseItemConsumesAction() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameEngineImpl engine = new GameEngineImpl();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.newGame();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.movePlayer(2, 3);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.endTurn();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.movePlayer(2, 5);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(engine.pickItem(1, 5));

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, engine.getState().getInventory().size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.useItem(0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void endTurnRunsEnemiesAndIncrementsTurnCount() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameEngineImpl engine = new GameEngineImpl();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.newGame();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.endTurn();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, engine.getState().getTurnCount());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(49, engine.getState().getTurnsLeft());
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        assertTrue(engine.getState().getEventLog().size() >= 2);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void stateReturnsDefensiveCopiesForMutableData() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameEngineImpl engine = new GameEngineImpl();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.newGame();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.movePlayer(2, 3);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.endTurn();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.movePlayer(2, 5);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.pickItem(1, 5);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IGameState state = engine.getState();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        state.getInventory().add(new Potion("Pocion falsa", 1));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        state.getEventLog().add("evento falso");
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room roomCopy = state.getCurrentRoom();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        roomCopy.setCell(0, 0, new Cell(CellType.WALL));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        roomCopy.getEnemies().remove(0);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IGameState freshState = engine.getState();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, freshState.getInventory().size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(freshState.getEventLog().contains("evento falso"));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(CellType.EMPTY, freshState.getCurrentRoom().getCell(0, 0).getType());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, freshState.getCurrentRoom().getEnemies().size());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void openingExteriorExitWinsGame() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameEngineImpl engine = new GameEngineImpl();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.newGame();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.movePlayer(2, 3);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.endTurn();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.movePlayer(1, 3);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.openDoor(0, 3);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.movePlayer(2, 1);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.endTurn();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.movePlayer(3, 2);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.openDoor(4, 2);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(engine.getState().isGameOver());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(engine.getState().isVictory());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(GameAlreadyOverException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.endTurn();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void temporarySaveWritesJsonFile() throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameEngineImpl engine = new GameEngineImpl();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.newGame();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File file = File.createTempFile("game-save", ".json");

        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            engine.saveGame(file.getAbsolutePath());

            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(file.length() > 0);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } finally {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            file.delete();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void loadGameRestoresBasicSavedStateOverBaseScenario() throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameEngineImpl engine = new GameEngineImpl();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File file = File.createTempFile("game-load", ".json");

        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            FileWriter writer = new FileWriter(file);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("{\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"version\": \"test\",\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"turnoActual\": 3,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"jugador\": {\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"vidaActual\": 42,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"habitacionActual\": 1,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"fila\": 2,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"col\": 2\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  }\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("}\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.close();

            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            engine.loadGame(file.getAbsolutePath());

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            IGameState state = engine.getState();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals(42, state.getPlayerHp());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals(1, state.getCurrentRoomId());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals(2, state.getPlayerRow());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals(2, state.getPlayerCol());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals(3, state.getTurnCount());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals(47, state.getTurnsLeft());
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } finally {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            file.delete();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void loadConfigBuildsGameFromJson() throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameEngineImpl engine = new GameEngineImpl();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.loadConfig("src/main/resources/levelConfig.example.json");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(100, engine.getState().getPlayerHp());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, engine.getState().getCurrentRoomId());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("Entrada", engine.getState().getCurrentRoomName());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(6, engine.getState().getCurrentRoomRows());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(7, engine.getState().getCurrentRoomCols());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, engine.getState().getPathToExit().size() - 1);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void lockedDoorRequiresMatchingKeyInInventory() throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File file = File.createTempFile("locked-door-level", ".json");
        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            FileWriter writer = new FileWriter(file);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("{\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"turnosMaximos\": 10,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"habitacionInicial\": 0,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"habitacionSalida\": 1,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"habitaciones\": [\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    {\"id\": 0, \"nombre\": \"Entrada\", \"filas\": 3, \"columnas\": 3,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("     \"celdas\": [\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("       {\"fila\": 0, \"columna\": 1, \"tipo\": \"DOOR\", \"habitacionDestino\": 1, \"bloqueada\": true},\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("       {\"fila\": 1, \"columna\": 2, \"tipo\": \"ITEM\", \"item\": {\"tipo\": \"Key\", \"nombre\": \"Llave\", \"puertaObjetivo\": 1}}\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("     ]},\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    {\"id\": 1, \"nombre\": \"Salida\", \"filas\": 3, \"columnas\": 3, \"celdas\": []}\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  ],\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"conexiones\": [{\"de\": 0, \"a\": 1, \"dirigida\": false}],\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"jugadorInicial\": {\"nombre\": \"Heroe\", \"habitacion\": 0, \"fila\": 1, \"columna\": 1,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"vidaActual\": 100, \"vidaMaxima\": 100, \"velocidad\": 3, \"ataqueBase\": 10, \"defensaBase\": 3,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"inventario\": []}\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("}\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.close();

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            GameEngineImpl engine = new GameEngineImpl();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            engine.loadConfig(file.getAbsolutePath());

            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertThrows(DoorLockedException.class, new ExecutableBlock() {
                // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
                @Override
                // Comentario de estudiante: aqui empieza un metodo o constructor.
                public void execute() {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    engine.openDoor(0, 1);
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            });

            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(engine.pickItem(1, 2));
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            engine.endTurn();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(engine.openDoor(0, 1));
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals(1, engine.getState().getCurrentRoomId());
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } finally {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            file.delete();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void gameOverBlocksAllMutatingPlayerActions() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameEngineImpl engine = new GameEngineImpl();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.newGame();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.movePlayer(2, 3);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.endTurn();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.movePlayer(1, 3);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.openDoor(0, 3);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.movePlayer(2, 1);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.endTurn();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.movePlayer(3, 2);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.openDoor(4, 2);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(GameAlreadyOverException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.movePlayer(3, 1);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(GameAlreadyOverException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.attack(0, 0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(GameAlreadyOverException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.useItem(0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(GameAlreadyOverException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.pickItem(0, 0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(GameAlreadyOverException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.openDoor(4, 2);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
