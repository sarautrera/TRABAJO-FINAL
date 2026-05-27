/*
 * Resumen del fichero: Contiene pruebas automatizadas para comprobar el comportamiento de GameEngineImplJUnitTest.
 */
package es.proyecto.juego.tests;

import es.proyecto.juego.logica.GameEngineImpl;
import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.logica.entidades.Player;
import es.proyecto.juego.logica.excepciones.DoorLockedException;
import es.proyecto.juego.logica.excepciones.GameAlreadyOverException;
import es.proyecto.juego.logica.excepciones.InvalidMoveException;
import es.proyecto.juego.logica.items.Potion;
import es.proyecto.juego.logica.mundo.Cell;
import es.proyecto.juego.logica.mundo.CellType;
import es.proyecto.juego.logica.mundo.Room;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameEngineImplJUnitTest {
    @Test
    void newGameInitializesQueryableState() {
        GameEngineImpl engine = new GameEngineImpl();

        engine.newGame();
        IGameState state = engine.getState();

        assertEquals(5, state.getPlayerRow());
        assertEquals(3, state.getPlayerCol());
        assertEquals(100, state.getPlayerHp());
        assertEquals(3, state.getPlayerSpeed());
        assertEquals(10, state.getPlayerAttack());
        assertEquals(3, state.getPlayerDefense());
        assertEquals("", state.getEquippedWeaponName());
        assertEquals("", state.getEquippedArmorName());
        assertEquals(Player.MAX_INVENTORY_SIZE, state.getMaxInventorySize());
        assertFalse(state.isInventoryFull());
        assertEquals(0, state.getCurrentRoomId());
        assertEquals("Entrada", state.getCurrentRoomName());
        assertEquals(6, state.getCurrentRoomRows());
        assertEquals(7, state.getCurrentRoomCols());
        assertEquals(50, state.getTurnsLeft());
        assertEquals(0, state.getTurnCount());
        assertTrue(state.canPlayerMove());
        assertTrue(state.canPlayerAct());
        assertFalse(state.isGameOver());
        assertFalse(state.isVictory());
        assertTrue(engine.getReachableCells().size() > 0);
    }

    @Test
    void movePlayerUpdatesPositionAndCanOnlyBeUsedOncePerTurn() {
        GameEngineImpl engine = new GameEngineImpl();
        engine.newGame();

        assertTrue(engine.movePlayer(4, 3));

        assertEquals(4, engine.getState().getPlayerRow());
        assertEquals(3, engine.getState().getPlayerCol());
        assertFalse(engine.getState().canPlayerMove());
        assertTrue(engine.getState().canPlayerAct());
        assertEquals(0, engine.getReachableCells().size());
        assertThrows(InvalidMoveException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                engine.movePlayer(4, 2);
            }
        });
    }

    @Test
    void rulesRequireAdjacentDoorItemAndAttackTargets() {
        GameEngineImpl engine = new GameEngineImpl();
        engine.newGame();

        assertThrows(InvalidMoveException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                engine.openDoor(0, 3);
            }
        });
        assertThrows(InvalidMoveException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                engine.pickItem(1, 5);
            }
        });
        assertEquals(0, engine.getAttackTargets().size());

        engine.movePlayer(4, 2);

        assertEquals(1, engine.getAttackTargets().size());
    }

    @Test
    void pickItemAddsItemToInventoryAndUseItemConsumesAction() {
        GameEngineImpl engine = new GameEngineImpl();
        engine.newGame();

        engine.movePlayer(2, 3);
        engine.endTurn();
        engine.movePlayer(2, 5);
        assertTrue(engine.pickItem(1, 5));

        assertEquals(1, engine.getState().getInventory().size());
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                engine.useItem(0);
            }
        });
    }

    @Test
    void endTurnRunsEnemiesAndIncrementsTurnCount() {
        GameEngineImpl engine = new GameEngineImpl();
        engine.newGame();

        engine.endTurn();

        assertEquals(1, engine.getState().getTurnCount());
        assertEquals(49, engine.getState().getTurnsLeft());
        assertTrue(engine.getState().getEventLog().size() >= 2);
    }

    @Test
    void stateReturnsDefensiveCopiesForMutableData() {
        GameEngineImpl engine = new GameEngineImpl();
        engine.newGame();

        engine.movePlayer(2, 3);
        engine.endTurn();
        engine.movePlayer(2, 5);
        engine.pickItem(1, 5);

        IGameState state = engine.getState();
        state.getInventory().add(new Potion("Pocion falsa", 1));
        state.getEventLog().add("evento falso");
        Room roomCopy = state.getCurrentRoom();
        roomCopy.setCell(0, 0, new Cell(CellType.WALL));
        roomCopy.getEnemies().remove(0);

        IGameState freshState = engine.getState();
        assertEquals(1, freshState.getInventory().size());
        assertFalse(freshState.getEventLog().contains("evento falso"));
        assertEquals(CellType.EMPTY, freshState.getCurrentRoom().getCell(0, 0).getType());
        assertEquals(1, freshState.getCurrentRoom().getEnemies().size());
    }

    @Test
    void openingExteriorExitWinsGame() {
        GameEngineImpl engine = new GameEngineImpl();
        engine.newGame();

        engine.movePlayer(2, 3);
        engine.endTurn();
        engine.movePlayer(1, 3);
        engine.openDoor(0, 3);

        engine.movePlayer(2, 1);
        engine.endTurn();
        engine.movePlayer(3, 2);
        engine.openDoor(4, 2);

        assertTrue(engine.getState().isGameOver());
        assertTrue(engine.getState().isVictory());
        assertThrows(GameAlreadyOverException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                engine.endTurn();
            }
        });
    }

    @Test
    void temporarySaveWritesJsonFile() throws IOException {
        GameEngineImpl engine = new GameEngineImpl();
        engine.newGame();
        File file = File.createTempFile("game-save", ".json");

        try {
            engine.saveGame(file.getAbsolutePath());

            assertTrue(file.length() > 0);
        } finally {
            file.delete();
        }
    }

    @Test
    void loadGameRestoresBasicSavedStateOverBaseScenario() throws IOException {
        GameEngineImpl engine = new GameEngineImpl();
        File file = File.createTempFile("game-load", ".json");

        try {
            FileWriter writer = new FileWriter(file);
            writer.write("{\n");
            writer.write("  \"version\": \"test\",\n");
            writer.write("  \"turnoActual\": 3,\n");
            writer.write("  \"jugador\": {\n");
            writer.write("    \"vidaActual\": 42,\n");
            writer.write("    \"habitacionActual\": 1,\n");
            writer.write("    \"fila\": 2,\n");
            writer.write("    \"col\": 2\n");
            writer.write("  }\n");
            writer.write("}\n");
            writer.close();

            engine.loadGame(file.getAbsolutePath());

            IGameState state = engine.getState();
            assertEquals(42, state.getPlayerHp());
            assertEquals(1, state.getCurrentRoomId());
            assertEquals(2, state.getPlayerRow());
            assertEquals(2, state.getPlayerCol());
            assertEquals(3, state.getTurnCount());
            assertEquals(47, state.getTurnsLeft());
        } finally {
            file.delete();
        }
    }

    @Test
    void saveAndLoadGameRestoresInventoryEquipmentRoomAndLog() throws IOException {
        File levelFile = File.createTempFile("full-save-level", ".json");
        File saveFile = File.createTempFile("full-save-game", ".json");
        try {
            FileWriter writer = new FileWriter(levelFile);
            writer.write("{\n");
            writer.write("  \"turnosMaximos\": 20,\n");
            writer.write("  \"habitacionInicial\": 0,\n");
            writer.write("  \"habitacionSalida\": 0,\n");
            writer.write("  \"habitaciones\": [\n");
            writer.write("    {\"id\": 0, \"nombre\": \"Sala persistente\", \"filas\": 3, \"columnas\": 3,\n");
            writer.write("     \"celdas\": [\n");
            writer.write("       {\"fila\": 1, \"columna\": 2, \"tipo\": \"ITEM\", \"item\": {\"tipo\": \"Weapon\", \"nombre\": \"Espada guardada\", \"ataqueBonus\": 5}},\n");
            writer.write("       {\"fila\": 0, \"columna\": 0, \"tipo\": \"WALL\"}\n");
            writer.write("     ]}\n");
            writer.write("  ],\n");
            writer.write("  \"conexiones\": [],\n");
            writer.write("  \"jugadorInicial\": {\"nombre\": \"Heroe\", \"habitacion\": 0, \"fila\": 1, \"columna\": 1,\n");
            writer.write("    \"vidaActual\": 80, \"vidaMaxima\": 100, \"velocidad\": 2, \"ataqueBase\": 10, \"defensaBase\": 3,\n");
            writer.write("    \"inventario\": []}\n");
            writer.write("}\n");
            writer.close();

            GameEngineImpl engine = new GameEngineImpl();
            engine.loadConfig(levelFile.getAbsolutePath());
            assertTrue(engine.pickItem(1, 2));
            engine.endTurn();
            assertTrue(engine.useItem(0));
            engine.saveGame(saveFile.getAbsolutePath());

            GameEngineImpl restored = new GameEngineImpl();
            restored.loadGame(saveFile.getAbsolutePath());

            IGameState state = restored.getState();
            assertEquals("Sala persistente", state.getCurrentRoomName());
            assertEquals(1, state.getInventory().size());
            assertEquals("Espada guardada", state.getEquippedWeaponName());
            assertEquals(15, state.getPlayerAttack());
            assertEquals(CellType.EMPTY, state.getCurrentRoom().getCell(1, 2).getType());
            assertEquals(CellType.WALL, state.getCurrentRoom().getCell(0, 0).getType());
            assertTrue(state.getTurnCount() >= 1);
            assertTrue(state.getEventLog().contains("Jugador recoge Espada guardada"));
        } finally {
            levelFile.delete();
            saveFile.delete();
        }
    }

    @Test
    void loadConfigBuildsGameFromJson() throws IOException {
        GameEngineImpl engine = new GameEngineImpl();

        engine.loadConfig("src/main/resources/levelConfig.example.json");

        assertEquals(100, engine.getState().getPlayerHp());
        assertEquals(0, engine.getState().getCurrentRoomId());
        assertEquals("Entrada", engine.getState().getCurrentRoomName());
        assertEquals(6, engine.getState().getCurrentRoomRows());
        assertEquals(7, engine.getState().getCurrentRoomCols());
        assertEquals(1, engine.getState().getPathToExit().size() - 1);
    }

    @Test
    void lockedDoorRequiresMatchingKeyInInventory() throws IOException {
        File file = File.createTempFile("locked-door-level", ".json");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("{\n");
            writer.write("  \"turnosMaximos\": 10,\n");
            writer.write("  \"habitacionInicial\": 0,\n");
            writer.write("  \"habitacionSalida\": 1,\n");
            writer.write("  \"habitaciones\": [\n");
            writer.write("    {\"id\": 0, \"nombre\": \"Entrada\", \"filas\": 3, \"columnas\": 3,\n");
            writer.write("     \"celdas\": [\n");
            writer.write("       {\"fila\": 0, \"columna\": 1, \"tipo\": \"DOOR\", \"habitacionDestino\": 1, \"bloqueada\": true},\n");
            writer.write("       {\"fila\": 1, \"columna\": 2, \"tipo\": \"ITEM\", \"item\": {\"tipo\": \"Key\", \"nombre\": \"Llave\", \"puertaObjetivo\": 1}}\n");
            writer.write("     ]},\n");
            writer.write("    {\"id\": 1, \"nombre\": \"Salida\", \"filas\": 3, \"columnas\": 3, \"celdas\": []}\n");
            writer.write("  ],\n");
            writer.write("  \"conexiones\": [{\"de\": 0, \"a\": 1, \"dirigida\": false}],\n");
            writer.write("  \"jugadorInicial\": {\"nombre\": \"Heroe\", \"habitacion\": 0, \"fila\": 1, \"columna\": 1,\n");
            writer.write("    \"vidaActual\": 100, \"vidaMaxima\": 100, \"velocidad\": 3, \"ataqueBase\": 10, \"defensaBase\": 3,\n");
            writer.write("    \"inventario\": []}\n");
            writer.write("}\n");
            writer.close();

            GameEngineImpl engine = new GameEngineImpl();
            engine.loadConfig(file.getAbsolutePath());

            assertThrows(DoorLockedException.class, new ExecutableBlock() {
                @Override
                public void execute() {
                    engine.openDoor(0, 1);
                }
            });

            assertTrue(engine.pickItem(1, 2));
            engine.endTurn();
            assertTrue(engine.openDoor(0, 1));
            assertEquals(1, engine.getState().getCurrentRoomId());
        } finally {
            file.delete();
        }
    }

    @Test
    void gameOverBlocksAllMutatingPlayerActions() {
        GameEngineImpl engine = new GameEngineImpl();
        engine.newGame();

        engine.movePlayer(2, 3);
        engine.endTurn();
        engine.movePlayer(1, 3);
        engine.openDoor(0, 3);
        engine.movePlayer(2, 1);
        engine.endTurn();
        engine.movePlayer(3, 2);
        engine.openDoor(4, 2);

        assertThrows(GameAlreadyOverException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                engine.movePlayer(3, 1);
            }
        });
        assertThrows(GameAlreadyOverException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                engine.attack(0, 0);
            }
        });
        assertThrows(GameAlreadyOverException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                engine.useItem(0);
            }
        });
        assertThrows(GameAlreadyOverException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                engine.pickItem(0, 0);
            }
        });
        assertThrows(GameAlreadyOverException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                engine.openDoor(4, 2);
            }
        });
    }

    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    }
}
