package es.proyecto.juego.tests;

import es.proyecto.juego.logica.GameEngineImpl;
import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.logica.entidades.Player;
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
    void temporaryLoadConfigRequiresJsonShape() throws IOException {
        GameEngineImpl engine = new GameEngineImpl();
        File file = File.createTempFile("level", ".json");

        try {
            FileWriter writer = new FileWriter(file);
            writer.write("{\"version\":\"test\"}");
            writer.close();

            engine.loadConfig(file.getAbsolutePath());

            assertEquals(100, engine.getState().getPlayerHp());
        } finally {
            file.delete();
        }
    }

    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    }
}
