// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.tests;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Enemy;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Player;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Item;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertNull;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertSame;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertThrows;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class RoomJUnitTest {
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void getCellAndSetCellWork() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = createRoom();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Cell wall = new Cell(CellType.WALL);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.setCell(1, 1, wall);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertSame(wall, room.getCell(1, 1));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(CellType.WALL, room.getCell(1, 1).getType());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void invalidCoordinatesThrowException() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = createRoom();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IndexOutOfBoundsException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                room.getCell(-1, 0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IndexOutOfBoundsException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                room.setCell(0, 3, new Cell(CellType.EMPTY));
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void getDoorsReturnsConfiguredDoors() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = createRoom();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.configureDoor(0, 1, 2, false, false);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.configureDoor(2, 1, -1, false, true);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, room.getDoors().size());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void placingAndRemovingEnemiesKeepsCellConsistent() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = createRoom();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Enemy enemy = new Enemy("Enemigo", 10, 1, 3, 1, 1, 1);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.placeEnemy(enemy);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertSame(enemy, room.getEnemyAt(1, 1));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(CellType.ENEMY, room.getCell(1, 1).getType());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(room.removeEnemy(enemy));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertNull(room.getEnemyAt(1, 1));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(CellType.EMPTY, room.getCell(1, 1).getType());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void cellDoesNotKeepIncompatibleState() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Cell cell = new Cell(CellType.EMPTY);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Enemy enemy = new Enemy("Enemigo", 10, 1, 3, 1, 0, 0);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cell.setEnemy(enemy);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cell.setItem(new Potion("Pocion", 10));

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertNull(cell.getEnemy());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(cell.hasItem());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(CellType.ITEM, cell.getType());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cell.configureTrap(5);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(cell.hasItem());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(CellType.TRAP, cell.getType());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(5, cell.getTrapDamage());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void enemyUsesBfsAndDoesNotMoveOverItemsOrDoors() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = new Room(2, "Sala grande", 4, 4, new MyLinkedList<Enemy>());
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Enemy enemy = new Enemy("Enemigo", 10, 1, 3, 1, 1, 1);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Player player = new Player("Heroe", 100, 3, 10, 3, 3, 3, new MyLinkedList<Item>());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.placeEnemy(enemy);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.placeItem(1, 2, new Potion("Pocion", 10));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.configureDoor(2, 1, 2, false, false);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(enemy.moveOneStepToward(player, room));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, enemy.getRow());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, enemy.getCol());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertSame(enemy, room.getEnemyAt(0, 1));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(room.getCell(1, 2).hasItem());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(room.getCell(2, 1).isDoor());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertNull(room.getEnemyAt(1, 1));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private Room createRoom() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return new Room(1, "Sala", 3, 3, new MyLinkedList<Enemy>());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
