/*
 * Resumen del fichero: Contiene pruebas automatizadas para comprobar el comportamiento de ComprehensiveCoverageJUnitTest.
 */
package es.proyecto.juego.tests;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.MyCircularList;
import es.proyecto.juego.estructuras.MyGraph;
import es.proyecto.juego.estructuras.MyLinkedList;
import es.proyecto.juego.estructuras.MyLinkedQueue;
import es.proyecto.juego.estructuras.MyLinkedStack;
import es.proyecto.juego.estructuras.StubList;
import es.proyecto.juego.logica.GameEngineImpl;
import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.logica.entidades.Enemy;
import es.proyecto.juego.logica.entidades.Player;
import es.proyecto.juego.logica.items.Armor;
import es.proyecto.juego.logica.items.Item;
import es.proyecto.juego.logica.items.Key;
import es.proyecto.juego.logica.items.Potion;
import es.proyecto.juego.logica.items.Weapon;
import es.proyecto.juego.logica.mundo.Cell;
import es.proyecto.juego.logica.mundo.CellType;
import es.proyecto.juego.logica.mundo.Room;
import es.proyecto.juego.logica.sistemas.EventLog;
import es.proyecto.juego.logica.sistemas.PathFinder;
import es.proyecto.juego.persistencia.GameSave;
import es.proyecto.juego.persistencia.LevelConfig;
import es.proyecto.juego.ui.MockGameEngine;
import es.proyecto.juego.ui.MockGameState;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComprehensiveCoverageJUnitTest {
    @Test
    void itemsValidateStateAndApplyEffects() {
        Player player = new Player("Heroe", 30, 2, 4, 1, 0, 0, new MyLinkedList<Item>());
        Weapon weapon = new Weapon("Espada", 6);
        Armor armor = new Armor("Armadura", 3);
        Key key = new Key("Llave", 7);
        Potion potion = new Potion("Pocion", 5);

        assertEquals("Espada", weapon.getName());
        assertFalse(weapon.isConsumable());
        assertTrue(weapon.canUse());
        assertTrue(weapon.isEquippable());
        assertTrue(armor.isEquippable());
        assertTrue(key.isEquippable());
        assertEquals(7, key.getTargetDoorId());
        assertEquals(5, potion.getHpRestore());

        weapon.consumeUse();
        assertEquals(-1, weapon.getUsesLeft());
        weapon.applyEffect(player);
        armor.applyEffect(player);
        key.applyEffect(player);
        assertEquals(10, player.getEffectiveAttack());
        assertEquals(4, player.getEffectiveDefense());

        player.takeDamage(10);
        potion.applyEffect(player);
        assertEquals(25, player.getHp());
        assertTrue(potion.isDepleted());
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                potion.applyEffect(player);
            }
        });

        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                new Weapon("", 0);
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                new Weapon("Rota", -1);
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                new Armor("Rota", -1);
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                new Key("Mala", -1);
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                new Potion("Mala", 0);
            }
        });
    }

    @Test
    void playerAndEnemyCoverValidationAndCombatBranches() {
        MyLinkedList<Item> inventory = new MyLinkedList<>();
        Player player = new Player("Heroe", 20, 1, 4, 0, 1, 1, inventory);
        Enemy enemy = new Enemy("Enemigo", 12, 1, 3, 1, 1, 2);

        assertEquals("Enemigo", enemy.getName());
        assertEquals(12, enemy.getMaxHp());
        assertTrue(enemy.isAdjacentTo(1, 1));
        assertTrue(enemy.attack(player) >= 0);
        assertTrue(player.getHp() <= 20);

        player.heal(-5);
        player.takeDamage(-5);
        assertTrue(player.isAlive());
        player.setPosition(0, 0);
        assertEquals(0, player.getRow());
        assertEquals(0, player.getCol());
        assertFalse(player.removeInventoryItem(new Key("Ausente", 1)));

        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                new Enemy(null, 1, 0, 0, 0, 0, 0);
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                new Enemy("Malo", 0, 0, 0, 0, 0, 0);
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                enemy.attack(null);
            }
        });

        Room room = new Room(1, "Sala", 3, 3, new MyLinkedList<Enemy>());
        Enemy blocked = new Enemy("Bloqueado", 10, 1, 1, 0, 0, 0);
        Player target = new Player("Objetivo", 20, 1, 1, 0, 2, 2, new MyLinkedList<Item>());
        room.placeEnemy(blocked);
        room.setCell(0, 1, new Cell(CellType.WALL));
        room.setCell(1, 0, new Cell(CellType.WALL));
        assertFalse(blocked.moveOneStepToward(target, room));
        blocked.takeDamage(99);
        assertFalse(blocked.moveOneStepToward(target, room));
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                blocked.moveOneStepToward(null, room);
            }
        });
    }

    @Test
    void structuresCoverEmptyDuplicateNullAndUnreachablePaths() {
        MyLinkedQueue<String> queue = new MyLinkedQueue<>();
        assertTrue(queue.isEmpty());
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                queue.dequeue();
            }
        });
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                queue.peek();
            }
        });

        MyLinkedStack<String> stack = new MyLinkedStack<>();
        assertTrue(stack.isEmpty());
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                stack.pop();
            }
        });

        MyLinkedList<String> list = new MyLinkedList<>();
        list.add(null);
        list.add("tail");
        assertTrue(list.contains(null));
        assertTrue(list.remove(null));
        assertTrue(list.remove("tail"));
        assertFalse(list.remove("missing"));
        list.add("x");
        list.clear();
        assertTrue(list.isEmpty());

        MyCircularList<String> circular = new MyCircularList<>();
        assertFalse(circular.removeCurrent());
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                circular.current();
            }
        });
        circular.add("uno");
        assertTrue(circular.removeCurrent());
        assertTrue(circular.isEmpty());
        circular.add("a");
        circular.add("b");
        circular.add("c");
        assertEquals("a", circular.current());
        assertTrue(circular.removeCurrent());
        assertEquals("b", circular.current());
        circular.next();
        assertTrue(circular.removeCurrent());
        assertEquals(1, circular.size());

        MyGraph<String> graph = new MyGraph<>();
        graph.addNode(null);
        graph.addNode(null);
        graph.addEdge(null, "a");
        graph.addEdge("a", "b");
        graph.addNode("aislado");
        graph.addEdge("a", "b");
        assertEquals(4, graph.nodeCount());
        assertTrue(graph.bfs(null).contains("b"));
        assertEquals(-1, graph.shortestDistance("a", "aislado"));
        assertEquals(0, graph.shortestPath("a", "aislado").size());
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                graph.addEdge("a", "c", -1);
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                graph.getNeighbors("no existe");
            }
        });
    }

    @Test
    void cellRoomEventLogAndPathFinderCoverBoundaryBranches() {
        Cell cell = new Cell(CellType.EMPTY);
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                cell.setType(null);
            }
        });
        cell.configureDoor(2, true, false);
        assertFalse(cell.isWalkable());
        cell.setDoorOpen(true);
        assertFalse(cell.isDoorLocked());
        assertTrue(cell.isWalkable());
        cell.configureTrap(9);
        assertEquals(CellType.TRAP, cell.getType());
        assertEquals(9, cell.getTrapDamage());
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                cell.configureTrap(-1);
            }
        });
        cell.setType(CellType.EMPTY);
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                cell.setDoorLocked(true);
            }
        });

        Room room = new Room(2, "Sala", 3, 3, new MyLinkedList<Enemy>());
        assertEquals(2, room.getId());
        assertNotSame(room.getGrid()[0][0], room.getGrid()[0][1]);
        assertFalse(room.isVisited());
        room.setVisited(true);
        assertTrue(room.isVisited());
        assertTrue(room.isWalkable(0, 0));
        assertFalse(room.removeEnemy(null));
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                new Room(1, "", 1, 1, new MyLinkedList<Enemy>());
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                room.setCell(0, 0, null);
            }
        });
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                room.takeItem(0, 0);
            }
        });

        EventLog log = new EventLog();
        assertTrue(log.isEmpty());
        assertEquals("", log.getLastEvent());
        log.add("uno");
        log.add("dos");
        assertEquals("dos", log.getLastEvent());
        assertEquals(2, log.size());
        log.clear();
        assertTrue(log.isEmpty());
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                log.add("");
            }
        });

        MyGraph<Integer> graph = new MyGraph<>();
        graph.addUndirectedEdge(1, 2);
        PathFinder pathFinder = new PathFinder(graph);
        Room pathRoom = new Room(1, "Camino", 3, 3, new MyLinkedList<Enemy>());
        Player player = new Player("Heroe", 20, 2, 1, 0, 1, 1, new MyLinkedList<Item>());
        pathRoom.configureDoor(0, 1, 2, true, false);
        assertEquals(1, pathFinder.getDistanceToNearestDoor(pathRoom, player));
        assertEquals(1, pathFinder.getMinRoomsToExit(1, 2));
        assertEquals(2, pathFinder.getPathToExit(1, 2).size());
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                new PathFinder(null);
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                pathFinder.getReachableCells(pathRoom, null);
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                pathFinder.getAttackTargets(null, player);
            }
        });
    }

    @Test
    void persistenceJsonAndConfigCoverDefaultsAliasesAndErrors() throws IOException {
        assertEquals("", LevelConfig.Json.readObject("{}", "missing", false));
        assertEquals("", LevelConfig.Json.readArray("{}", "missing", false));
        assertThrows(IOException.class, new ExecutableBlock() {
            @Override
            public void execute() throws Throwable {
                LevelConfig.Json.readObject("{}", "missing", true);
            }
        });
        assertThrows(IOException.class, new ExecutableBlock() {
            @Override
            public void execute() throws Throwable {
                LevelConfig.Json.readInt("{\"n\":\"x\"}", "n", 0);
            }
        });
        assertThrows(IOException.class, new ExecutableBlock() {
            @Override
            public void execute() throws Throwable {
                LevelConfig.Json.readBoolean("{\"b\": 1}", "b", false);
            }
        });
        assertThrows(IOException.class, new ExecutableBlock() {
            @Override
            public void execute() throws Throwable {
                LevelConfig.Json.readString("{\"s\": 1}", "s", "");
            }
        });
        assertThrows(IOException.class, new ExecutableBlock() {
            @Override
            public void execute() throws Throwable {
                LevelConfig.Json.readArray("{\"a\": [}", "a", true);
            }
        });
        assertEquals("a\"b", LevelConfig.Json.readString("{\"s\":\"a\\\"b\"}", "s", ""));
        assertEquals(2, LevelConfig.Json.splitObjects("[{\"a\":{\"b\":1}}, {\"c\":2}]").size());

        GameSave save = new GameSave();
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() throws Throwable {
                save.save(null, "x");
            }
        });

        StubList<String> log = new StubList<>();
        MockGameState state = new MockGameState(1, 2, 77, log);
        GameSave.SaveData data = GameSave.SaveData.fromState(state);
        assertEquals(77, data.vidaActual);
        assertEquals(1, data.fila);
        assertEquals(2, data.col);

        File tempFile = File.createTempFile("escape-save", ".json");
        tempFile.deleteOnExit();
        save.save(state, tempFile.getAbsolutePath());
        assertTrue(tempFile.length() > 0);

        File malformed = File.createTempFile("bad-level", ".json");
        malformed.deleteOnExit();
        try (FileWriter writer = new FileWriter(malformed)) {
            writer.write("{\"habitaciones\": []}");
        }
        LevelConfig config = new LevelConfig();
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            @Override
            public void execute() throws Throwable {
                config.load(malformed.getAbsolutePath());
            }
        });
    }

    @Test
    void mockEngineAndStateExposeAllContractValues() throws IOException {
        MockGameEngine engine = new MockGameEngine();
        IGameState initial = engine.getState();

        assertEquals(4, initial.getPlayerRow());
        assertEquals(2, initial.getPlayerCol());
        assertEquals(80, initial.getPlayerHp());
        assertEquals(100, initial.getPlayerMaxHp());
        assertEquals(3, initial.getPlayerSpeed());
        assertEquals(15, initial.getPlayerAttack());
        assertEquals(5, initial.getPlayerDefense());
        assertEquals("", initial.getEquippedWeaponName());
        assertEquals("", initial.getEquippedArmorName());
        assertEquals(0, initial.getInventory().size());
        assertEquals(5, initial.getMaxInventorySize());
        assertFalse(initial.isInventoryFull());
        assertEquals(1, initial.getCurrentRoomId());
        assertEquals("Mock", initial.getCurrentRoomName());
        assertEquals(5, initial.getCurrentRoomRows());
        assertEquals(5, initial.getCurrentRoomCols());
        assertNull(initial.getCurrentRoom());
        assertEquals(5, initial.getTurnCount());
        assertEquals(45, initial.getTurnsLeft());
        assertTrue(initial.canPlayerMove());
        assertTrue(initial.canPlayerAct());
        assertEquals(2, initial.getMinRoomsToExit());
        assertEquals(4, initial.getDistanceToNearestDoor());
        assertEquals(0, initial.getPathToExit().size());
        assertFalse(initial.isGameOver());
        assertFalse(initial.isVictory());
        assertTrue(initial.getLastEvent().contains("Mock"));

        engine.loadConfig("nivel.json");
        engine.saveGame("save.json");
        engine.loadGame("save.json");
        assertTrue(engine.attack(1, 1));
        assertTrue(engine.useItem(0));
        assertTrue(engine.pickItem(1, 2));
        assertTrue(engine.openDoor(0, 1));
        engine.endTurn();
        assertTrue(engine.movePlayer(3, 3));
        assertFalse(engine.movePlayer(5, 0));
        assertEquals(2, engine.getReachableCells().size());
        assertEquals(0, engine.getAttackTargets().size());
        assertEquals(3, engine.getState().getPlayerRow());
        assertEquals(3, engine.getState().getPlayerCol());

        MockGameState emptyLogState = new MockGameState(0, 0, 1, new StubList<String>());
        assertEquals("", emptyLogState.getLastEvent());
        MockGameState nullLogState = new MockGameState(0, 0, 1, null);
        assertEquals("", nullLogState.getLastEvent());
    }

    @Test
    void gameEngineRejectsCallsBeforeStartAndSavesEscapedSnapshot() throws IOException {
        GameEngineImpl engine = new GameEngineImpl();

        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                engine.getState();
            }
        });
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                engine.getReachableCells();
            }
        });

        engine.newGame();
        File file = File.createTempFile("engine-save", ".json");
        file.deleteOnExit();
        assertDoesNotThrow(new ExecutableBlock() {
            @Override
            public void execute() throws Throwable {
                engine.saveGame(file.getAbsolutePath());
            }
        });
        assertTrue(file.length() > 0);
    }

    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    }
}
