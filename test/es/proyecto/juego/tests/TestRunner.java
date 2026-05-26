/*
 * Resumen del fichero: Permite lanzar manualmente el conjunto de pruebas del proyecto.
 */
package es.proyecto.juego.tests;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.MyCircularList;
import es.proyecto.juego.estructuras.MyGraph;
import es.proyecto.juego.estructuras.MyLinkedList;
import es.proyecto.juego.estructuras.MyLinkedQueue;
import es.proyecto.juego.estructuras.MyLinkedStack;
import es.proyecto.juego.logica.GameEngineImpl;
import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.logica.entidades.Enemy;
import es.proyecto.juego.logica.entidades.Player;
import es.proyecto.juego.logica.excepciones.InvalidMoveException;
import es.proyecto.juego.logica.items.Item;
import es.proyecto.juego.logica.items.Potion;
import es.proyecto.juego.logica.items.Weapon;
import es.proyecto.juego.logica.mundo.Cell;
import es.proyecto.juego.logica.mundo.CellType;
import es.proyecto.juego.logica.mundo.Room;
import es.proyecto.juego.logica.sistemas.CombatSystem;
import es.proyecto.juego.logica.sistemas.MatrixBFS;
import es.proyecto.juego.logica.sistemas.TurnManager;

public final class TestRunner {
    private int passed;
    private int failed;

    public static void main(String[] args) {
        TestRunner runner = new TestRunner();
        runner.runAll();
    }

    private void runAll() {
        run("Player vida, inventario y equipamiento", new TestCase() {
            @Override
            public void execute() {
                testPlayer();
            }
        });
        run("CombatSystem formula y limites", new TestCase() {
            @Override
            public void execute() {
                testCombatSystem();
            }
        });
        run("Room y Cell validaciones", new TestCase() {
            @Override
            public void execute() {
                testRoomAndCell();
            }
        });
        run("TurnManager acciones por turno", new TestCase() {
            @Override
            public void execute() {
                testTurnManager();
            }
        });
        run("Estructuras propias", new TestCase() {
            @Override
            public void execute() {
                testStructures();
            }
        });
        run("MatrixBFS alcance y obstaculos", new TestCase() {
            @Override
            public void execute() {
                testMatrixBFS();
            }
        });
        run("GameEngineImpl flujo inicial", new TestCase() {
            @Override
            public void execute() {
                testGameEngine();
            }
        });

        System.out.println("Tests pasados: " + passed);
        System.out.println("Tests fallidos: " + failed);
        if (failed > 0) {
            throw new AssertionError("Hay tests fallidos");
        }
    }

    private void testPlayer() {
        Player player = new Player("Heroe", 100, 3, 10, 2, 0, 0, new MyLinkedList<Item>());
        player.takeDamage(150);
        assertEquals(0, player.getHp(), "La vida no debe bajar de 0");
        player.heal(500);
        assertEquals(100, player.getHp(), "La vida no debe superar maxHp");

        Weapon sword = new Weapon("Espada", 5);
        player.addItem(sword);
        player.useInventoryItem(0);
        assertEquals(15, player.getEffectiveAttack(), "El arma debe sumar ataque");

        player.takeDamage(20);
        Potion potion = new Potion("Pocion", 10);
        player.addItem(potion);
        int index = player.getInventory().size() - 1;
        player.useInventoryItem(index);
        assertTrue(!player.getInventory().contains(potion), "La pocion consumida debe salir del inventario");
        assertEquals(90, player.getHp(), "La pocion debe curar");
    }

    private void testCombatSystem() {
        assertEquals(0, CombatSystem.calculateDamageWithRoll(10, 20, 1.0), "El dano minimo debe ser 0");
        assertEquals(20, CombatSystem.calculateDamageWithRoll(10, 0, 1.0), "Formula con roll maximo");
        assertEquals(0, CombatSystem.calculateDamageWithRoll(10, 0, 0.0), "Formula con roll minimo");
    }

    private void testRoomAndCell() {
        Room room = new Room(1, "Sala", 3, 3, new MyLinkedList<Enemy>());
        room.configureDoor(0, 1, 2, false, false);
        assertTrue(room.getCell(0, 1).isDoor(), "La celda debe ser puerta");
        assertEquals(1, room.getDoors().size(), "Debe haber una puerta");

        Enemy enemy = new Enemy("Enemigo", 10, 1, 2, 0, 1, 1);
        room.placeEnemy(enemy);
        assertTrue(room.getEnemyAt(1, 1) == enemy, "El enemigo debe quedar en la celda");
        room.removeEnemy(enemy);
        assertTrue(room.getEnemyAt(1, 1) == null, "El enemigo debe retirarse");

        room.placeItem(2, 2, new Potion("Pocion", 5));
        assertTrue(room.getCell(2, 2).hasItem(), "La celda debe tener item");
        room.takeItem(2, 2);
        assertTrue(!room.getCell(2, 2).hasItem(), "El item debe retirarse");
    }

    private void testTurnManager() {
        TurnManager manager = new TurnManager(2);
        assertTrue(manager.canMove(), "El jugador debe poder moverse al inicio");
        manager.markMovementUsed();
        assertTrue(!manager.canMove(), "No debe poder moverse dos veces");
        manager.markActionUsed();
        assertTrue(!manager.canAct(), "No debe poder actuar dos veces");
        manager.endPlayerTurn();
        assertEquals(1, manager.getTurnCount(), "Debe contar un turno terminado");
        manager.endPlayerTurn();
        assertTrue(manager.isTimeUp(), "Debe agotarse al llegar al maximo");
    }

    private void testStructures() {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        list.add(1);
        list.add(3);
        list.add(1, 2);
        assertEquals(3, list.size(), "Lista size");
        assertEquals(2, list.get(1).intValue(), "Lista insert");
        assertEquals(2, list.remove(1).intValue(), "Lista remove");

        MyLinkedQueue<String> queue = new MyLinkedQueue<String>();
        queue.enqueue("a");
        queue.enqueue("b");
        assertEquals("a", queue.dequeue(), "Cola FIFO");
        assertEquals("b", queue.peek(), "Cola peek");

        MyLinkedStack<String> stack = new MyLinkedStack<String>();
        stack.push("a");
        stack.push("b");
        assertEquals("b", stack.pop(), "Pila LIFO");

        MyCircularList<String> circular = new MyCircularList<String>();
        circular.add("jugador");
        circular.add("enemigo");
        assertEquals("jugador", circular.current(), "Circular current");
        assertEquals("enemigo", circular.next(), "Circular next");
        assertEquals("jugador", circular.next(), "Circular vuelve al inicio");

        MyGraph<Integer> graph = new MyGraph<Integer>();
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, 1);
        assertEquals(2, graph.shortestDistance(0, 1), "Grafo ruta ponderada");
        assertEquals(3, graph.shortestPath(0, 1).size(), "Grafo path");
    }

    private void testMatrixBFS() {
        Room room = new Room(1, "Sala", 3, 3, new MyLinkedList<Enemy>());
        room.setCell(1, 1, new Cell(CellType.WALL));
        IList<int[]> reachable = MatrixBFS.reachableCells(room, 0, 0, 2);
        assertTrue(containsCell(reachable, 0, 1), "Debe alcanzar celda derecha");
        assertTrue(containsCell(reachable, 1, 0), "Debe alcanzar celda inferior");
        assertTrue(!containsCell(reachable, 1, 1), "No debe atravesar muro");
        assertEquals(4, MatrixBFS.distance(room, 0, 0, 2, 2), "Debe rodear el muro");
    }

    private void testGameEngine() {
        GameEngineImpl engine = new GameEngineImpl();
        engine.newGame();
        IGameState state = engine.getState();
        assertEquals(5, state.getPlayerRow(), "Fila inicial");
        assertEquals(3, state.getPlayerCol(), "Col inicial");
        assertEquals(50, state.getTurnsLeft(), "Turnos iniciales");
        assertTrue(engine.getReachableCells().size() > 0, "Debe haber celdas alcanzables");
        assertTrue(engine.movePlayer(4, 3), "Debe moverse a celda alcanzable");
        assertThrowsInvalidMove(new RunnableBlock() {
            @Override
            public void run() {
                engine.movePlayer(4, 2);
            }
        });
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

    private void run(String name, TestCase testCase) {
        try {
            testCase.execute();
            passed++;
            System.out.println("[OK] " + name);
        } catch (Throwable error) {
            failed++;
            System.out.println("[ERROR] " + name + ": " + error.getMessage());
        }
    }

    private void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + ". Esperado " + expected + ", obtenido " + actual);
        }
    }

    private void assertEquals(String expected, String actual, String message) {
        if (expected == null ? actual != null : !expected.equals(actual)) {
            throw new AssertionError(message + ". Esperado " + expected + ", obtenido " + actual);
        }
    }

    private void assertThrowsInvalidMove(RunnableBlock block) {
        try {
            block.run();
        } catch (InvalidMoveException expected) {
            return;
        }
        throw new AssertionError("Se esperaba InvalidMoveException");
    }

    private interface TestCase {
        void execute();
    }

    private interface RunnableBlock {
        void run();
    }
}
