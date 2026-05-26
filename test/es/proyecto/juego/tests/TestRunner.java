// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.tests;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyCircularList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyGraph;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedQueue;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedStack;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.GameEngineImpl;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Enemy;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Player;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.InvalidMoveException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Item;
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
import es.proyecto.juego.logica.sistemas.MatrixBFS;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.sistemas.TurnManager;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public final class TestRunner {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int passed;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int failed;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public static void main(String[] args) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        TestRunner runner = new TestRunner();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        runner.runAll();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void runAll() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        run("Player vida, inventario y equipamiento", new TestCase() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                testPlayer();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        run("CombatSystem formula y limites", new TestCase() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                testCombatSystem();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        run("Room y Cell validaciones", new TestCase() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                testRoomAndCell();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        run("TurnManager acciones por turno", new TestCase() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                testTurnManager();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        run("Estructuras propias", new TestCase() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                testStructures();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        run("MatrixBFS alcance y obstaculos", new TestCase() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                testMatrixBFS();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        run("GameEngineImpl flujo inicial", new TestCase() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                testGameEngine();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        System.out.println("Tests pasados: " + passed);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        System.out.println("Tests fallidos: " + failed);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (failed > 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new AssertionError("Hay tests fallidos");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void testPlayer() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Player player = new Player("Heroe", 100, 3, 10, 2, 0, 0, new MyLinkedList<Item>());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.takeDamage(150);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, player.getHp(), "La vida no debe bajar de 0");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.heal(500);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(100, player.getHp(), "La vida no debe superar maxHp");

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Weapon sword = new Weapon("Espada", 5);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.addItem(sword);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.useInventoryItem(0);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(15, player.getEffectiveAttack(), "El arma debe sumar ataque");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.takeDamage(20);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Potion potion = new Potion("Pocion", 10);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.addItem(potion);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int index = player.getInventory().size() - 1;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.useInventoryItem(index);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(!player.getInventory().contains(potion), "La pocion consumida debe salir del inventario");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(90, player.getHp(), "La pocion debe curar");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void testCombatSystem() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, CombatSystem.calculateDamageWithRoll(10, 20, 1.0), "El dano minimo debe ser 0");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(20, CombatSystem.calculateDamageWithRoll(10, 0, 1.0), "Formula con roll maximo");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, CombatSystem.calculateDamageWithRoll(10, 0, 0.0), "Formula con roll minimo");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void testRoomAndCell() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = new Room(1, "Sala", 3, 3, new MyLinkedList<Enemy>());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.configureDoor(0, 1, 2, false, false);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(room.getCell(0, 1).isDoor(), "La celda debe ser puerta");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, room.getDoors().size(), "Debe haber una puerta");

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Enemy enemy = new Enemy("Enemigo", 10, 1, 2, 0, 1, 1);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.placeEnemy(enemy);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        assertTrue(room.getEnemyAt(1, 1) == enemy, "El enemigo debe quedar en la celda");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.removeEnemy(enemy);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        assertTrue(room.getEnemyAt(1, 1) == null, "El enemigo debe retirarse");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.placeItem(2, 2, new Potion("Pocion", 5));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(room.getCell(2, 2).hasItem(), "La celda debe tener item");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.takeItem(2, 2);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(!room.getCell(2, 2).hasItem(), "El item debe retirarse");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void testTurnManager() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        TurnManager manager = new TurnManager(2);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(manager.canMove(), "El jugador debe poder moverse al inicio");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        manager.markMovementUsed();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(!manager.canMove(), "No debe poder moverse dos veces");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        manager.markActionUsed();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(!manager.canAct(), "No debe poder actuar dos veces");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        manager.endPlayerTurn();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, manager.getTurnCount(), "Debe contar un turno terminado");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        manager.endPlayerTurn();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(manager.isTimeUp(), "Debe agotarse al llegar al maximo");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void testStructures() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add(1);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add(3);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add(1, 2);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, list.size(), "Lista size");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, list.get(1).intValue(), "Lista insert");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, list.remove(1).intValue(), "Lista remove");

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedQueue<String> queue = new MyLinkedQueue<String>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        queue.enqueue("a");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        queue.enqueue("b");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("a", queue.dequeue(), "Cola FIFO");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("b", queue.peek(), "Cola peek");

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedStack<String> stack = new MyLinkedStack<String>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        stack.push("a");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        stack.push("b");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("b", stack.pop(), "Pila LIFO");

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyCircularList<String> circular = new MyCircularList<String>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        circular.add("jugador");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        circular.add("enemigo");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("jugador", circular.current(), "Circular current");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("enemigo", circular.next(), "Circular next");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("jugador", circular.next(), "Circular vuelve al inicio");

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyGraph<Integer> graph = new MyGraph<Integer>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addEdge(0, 1, 10);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addEdge(0, 2, 1);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addEdge(2, 1, 1);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, graph.shortestDistance(0, 1), "Grafo ruta ponderada");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, graph.shortestPath(0, 1).size(), "Grafo path");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void testMatrixBFS() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = new Room(1, "Sala", 3, 3, new MyLinkedList<Enemy>());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.setCell(1, 1, new Cell(CellType.WALL));
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IList<int[]> reachable = MatrixBFS.reachableCells(room, 0, 0, 2);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(containsCell(reachable, 0, 1), "Debe alcanzar celda derecha");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(containsCell(reachable, 1, 0), "Debe alcanzar celda inferior");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(!containsCell(reachable, 1, 1), "No debe atravesar muro");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(4, MatrixBFS.distance(room, 0, 0, 2, 2), "Debe rodear el muro");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void testGameEngine() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameEngineImpl engine = new GameEngineImpl();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.newGame();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IGameState state = engine.getState();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(5, state.getPlayerRow(), "Fila inicial");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, state.getPlayerCol(), "Col inicial");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(50, state.getTurnsLeft(), "Turnos iniciales");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(engine.getReachableCells().size() > 0, "Debe haber celdas alcanzables");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(engine.movePlayer(4, 3), "Debe moverse a celda alcanzable");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrowsInvalidMove(new RunnableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void run() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.movePlayer(4, 2);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
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
    private void run(String name, TestCase testCase) {
        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            testCase.execute();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            passed++;
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            System.out.println("[OK] " + name);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } catch (Throwable error) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            failed++;
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            System.out.println("[ERROR] " + name + ": " + error.getMessage());
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void assertTrue(boolean condition, String message) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!condition) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new AssertionError(message);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void assertEquals(int expected, int actual, String message) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (expected != actual) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new AssertionError(message + ". Esperado " + expected + ", obtenido " + actual);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void assertEquals(String expected, String actual, String message) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (expected == null ? actual != null : !expected.equals(actual)) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new AssertionError(message + ". Esperado " + expected + ", obtenido " + actual);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void assertThrowsInvalidMove(RunnableBlock block) {
        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            block.run();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } catch (InvalidMoveException expected) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
        throw new AssertionError("Se esperaba InvalidMoveException");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
    private interface TestCase {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        void execute();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
    private interface RunnableBlock {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        void run();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
