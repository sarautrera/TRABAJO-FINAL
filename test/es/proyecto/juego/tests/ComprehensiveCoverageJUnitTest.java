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
import es.proyecto.juego.estructuras.StubList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.GameEngineImpl;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Enemy;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Player;
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
import es.proyecto.juego.logica.sistemas.EventLog;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.sistemas.PathFinder;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.persistencia.GameSave;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.persistencia.LevelConfig;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.ui.MockGameEngine;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.ui.MockGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import org.junit.jupiter.api.Test;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.File;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.FileWriter;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.IOException;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertNotSame;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertNull;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertThrows;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class ComprehensiveCoverageJUnitTest {
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void itemsValidateStateAndApplyEffects() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Player player = new Player("Heroe", 30, 2, 4, 1, 0, 0, new MyLinkedList<Item>());
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Weapon weapon = new Weapon("Espada", 6);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Armor armor = new Armor("Armadura", 3);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Key key = new Key("Llave", 7);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Potion potion = new Potion("Pocion", 5);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("Espada", weapon.getName());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(weapon.isConsumable());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(weapon.canUse());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(weapon.isEquippable());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(armor.isEquippable());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(key.isEquippable());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(7, key.getTargetDoorId());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(5, potion.getHpRestore());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        weapon.consumeUse();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(-1, weapon.getUsesLeft());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        weapon.applyEffect(player);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        armor.applyEffect(player);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        key.applyEffect(player);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(10, player.getEffectiveAttack());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(4, player.getEffectiveDefense());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.takeDamage(10);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        potion.applyEffect(player);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(25, player.getHp());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(potion.isDepleted());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                potion.applyEffect(player);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new Weapon("", 0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new Weapon("Rota", -1);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new Armor("Rota", -1);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new Key("Mala", -1);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new Potion("Mala", 0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void playerAndEnemyCoverValidationAndCombatBranches() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<Item> inventory = new MyLinkedList<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Player player = new Player("Heroe", 20, 1, 4, 0, 1, 1, inventory);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Enemy enemy = new Enemy("Enemigo", 12, 1, 3, 1, 1, 2);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("Enemigo", enemy.getName());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(12, enemy.getMaxHp());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(enemy.isAdjacentTo(1, 1));
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        assertTrue(enemy.attack(player) >= 0);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        assertTrue(player.getHp() <= 20);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.heal(-5);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.takeDamage(-5);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(player.isAlive());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.setPosition(0, 0);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, player.getRow());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, player.getCol());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(player.removeInventoryItem(new Key("Ausente", 1)));

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new Enemy(null, 1, 0, 0, 0, 0, 0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new Enemy("Malo", 0, 0, 0, 0, 0, 0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                enemy.attack(null);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = new Room(1, "Sala", 3, 3, new MyLinkedList<Enemy>());
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Enemy blocked = new Enemy("Bloqueado", 10, 1, 1, 0, 0, 0);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Player target = new Player("Objetivo", 20, 1, 1, 0, 2, 2, new MyLinkedList<Item>());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.placeEnemy(blocked);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.setCell(0, 1, new Cell(CellType.WALL));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.setCell(1, 0, new Cell(CellType.WALL));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(blocked.moveOneStepToward(target, room));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        blocked.takeDamage(99);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(blocked.moveOneStepToward(target, room));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                blocked.moveOneStepToward(null, room);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void structuresCoverEmptyDuplicateNullAndUnreachablePaths() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedQueue<String> queue = new MyLinkedQueue<>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(queue.isEmpty());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                queue.dequeue();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                queue.peek();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedStack<String> stack = new MyLinkedStack<>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(stack.isEmpty());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                stack.pop();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<String> list = new MyLinkedList<>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add(null);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add("tail");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(list.contains(null));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(list.remove(null));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(list.remove("tail"));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(list.remove("missing"));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add("x");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.clear();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(list.isEmpty());

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyCircularList<String> circular = new MyCircularList<>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(circular.removeCurrent());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                circular.current();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        circular.add("uno");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(circular.removeCurrent());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(circular.isEmpty());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        circular.add("a");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        circular.add("b");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        circular.add("c");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("a", circular.current());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(circular.removeCurrent());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("b", circular.current());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        circular.next();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(circular.removeCurrent());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, circular.size());

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyGraph<String> graph = new MyGraph<>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addNode(null);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addNode(null);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addEdge(null, "a");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addEdge("a", "b");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addNode("aislado");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addEdge("a", "b");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(4, graph.nodeCount());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(graph.bfs(null).contains("b"));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(-1, graph.shortestDistance("a", "aislado"));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, graph.shortestPath("a", "aislado").size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                graph.addEdge("a", "c", -1);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                graph.getNeighbors("no existe");
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void cellRoomEventLogAndPathFinderCoverBoundaryBranches() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Cell cell = new Cell(CellType.EMPTY);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                cell.setType(null);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cell.configureDoor(2, true, false);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(cell.isWalkable());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cell.setDoorOpen(true);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(cell.isDoorLocked());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(cell.isWalkable());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cell.configureTrap(9);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(CellType.TRAP, cell.getType());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(9, cell.getTrapDamage());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                cell.configureTrap(-1);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cell.setType(CellType.EMPTY);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                cell.setDoorLocked(true);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = new Room(2, "Sala", 3, 3, new MyLinkedList<Enemy>());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, room.getId());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertNotSame(room.getGrid()[0][0], room.getGrid()[0][1]);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(room.isVisited());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.setVisited(true);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(room.isVisited());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(room.isWalkable(0, 0));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(room.removeEnemy(null));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new Room(1, "", 1, 1, new MyLinkedList<Enemy>());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                room.setCell(0, 0, null);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                room.takeItem(0, 0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        EventLog log = new EventLog();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(log.isEmpty());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("", log.getLastEvent());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.add("uno");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.add("dos");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("dos", log.getLastEvent());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, log.size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.clear();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(log.isEmpty());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                log.add("");
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyGraph<Integer> graph = new MyGraph<>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addUndirectedEdge(1, 2);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        PathFinder pathFinder = new PathFinder(graph);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room pathRoom = new Room(1, "Camino", 3, 3, new MyLinkedList<Enemy>());
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Player player = new Player("Heroe", 20, 2, 1, 0, 1, 1, new MyLinkedList<Item>());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        pathRoom.configureDoor(0, 1, 2, true, false);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, pathFinder.getDistanceToNearestDoor(pathRoom, player));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, pathFinder.getMinRoomsToExit(1, 2));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, pathFinder.getPathToExit(1, 2).size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new PathFinder(null);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                pathFinder.getReachableCells(pathRoom, null);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                pathFinder.getAttackTargets(null, player);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void persistenceJsonAndConfigCoverDefaultsAliasesAndErrors() throws IOException {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("", LevelConfig.Json.readObject("{}", "missing", false));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("", LevelConfig.Json.readArray("{}", "missing", false));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IOException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() throws Throwable {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                LevelConfig.Json.readObject("{}", "missing", true);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IOException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() throws Throwable {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                LevelConfig.Json.readInt("{\"n\":\"x\"}", "n", 0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IOException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() throws Throwable {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                LevelConfig.Json.readBoolean("{\"b\": 1}", "b", false);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IOException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() throws Throwable {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                LevelConfig.Json.readString("{\"s\": 1}", "s", "");
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IOException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() throws Throwable {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                LevelConfig.Json.readArray("{\"a\": [}", "a", true);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("a\"b", LevelConfig.Json.readString("{\"s\":\"a\\\"b\"}", "s", ""));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, LevelConfig.Json.splitObjects("[{\"a\":{\"b\":1}}, {\"c\":2}]").size());

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameSave save = new GameSave();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() throws Throwable {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                save.save(null, "x");
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<String> log = new StubList<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MockGameState state = new MockGameState(1, 2, 77, log);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameSave.SaveData data = GameSave.SaveData.fromState(state);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(77, data.vidaActual);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, data.fila);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, data.col);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File tempFile = File.createTempFile("escape-save", ".json");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        tempFile.deleteOnExit();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        save.save(state, tempFile.getAbsolutePath());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(tempFile.length() > 0);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File malformed = File.createTempFile("bad-level", ".json");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        malformed.deleteOnExit();
        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try (FileWriter writer = new FileWriter(malformed)) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("{\"habitaciones\": []}");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        LevelConfig config = new LevelConfig();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() throws Throwable {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                config.load(malformed.getAbsolutePath());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void mockEngineAndStateExposeAllContractValues() throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MockGameEngine engine = new MockGameEngine();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IGameState initial = engine.getState();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(4, initial.getPlayerRow());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, initial.getPlayerCol());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(80, initial.getPlayerHp());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(100, initial.getPlayerMaxHp());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, initial.getPlayerSpeed());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(15, initial.getPlayerAttack());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(5, initial.getPlayerDefense());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("", initial.getEquippedWeaponName());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("", initial.getEquippedArmorName());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, initial.getInventory().size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(5, initial.getMaxInventorySize());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(initial.isInventoryFull());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, initial.getCurrentRoomId());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("Mock", initial.getCurrentRoomName());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(5, initial.getCurrentRoomRows());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(5, initial.getCurrentRoomCols());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertNull(initial.getCurrentRoom());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(5, initial.getTurnCount());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(45, initial.getTurnsLeft());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(initial.canPlayerMove());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(initial.canPlayerAct());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, initial.getMinRoomsToExit());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(4, initial.getDistanceToNearestDoor());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, initial.getPathToExit().size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(initial.isGameOver());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(initial.isVictory());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(initial.getLastEvent().contains("Mock"));

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.loadConfig("nivel.json");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.saveGame("save.json");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.loadGame("save.json");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(engine.attack(1, 1));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(engine.useItem(0));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(engine.pickItem(1, 2));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(engine.openDoor(0, 1));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.endTurn();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(engine.movePlayer(3, 3));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(engine.movePlayer(5, 0));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, engine.getReachableCells().size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, engine.getAttackTargets().size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, engine.getState().getPlayerRow());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, engine.getState().getPlayerCol());

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MockGameState emptyLogState = new MockGameState(0, 0, 1, new StubList<String>());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("", emptyLogState.getLastEvent());
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MockGameState nullLogState = new MockGameState(0, 0, 1, null);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("", nullLogState.getLastEvent());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void gameEngineRejectsCallsBeforeStartAndSavesEscapedSnapshot() throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameEngineImpl engine = new GameEngineImpl();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.getState();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalStateException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.getReachableCells();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        engine.newGame();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File file = File.createTempFile("engine-save", ".json");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        file.deleteOnExit();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertDoesNotThrow(new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() throws Throwable {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.saveGame(file.getAbsolutePath());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(file.length() > 0);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
