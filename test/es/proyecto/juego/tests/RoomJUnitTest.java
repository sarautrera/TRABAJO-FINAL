package es.proyecto.juego.tests;

import es.proyecto.juego.estructuras.MyLinkedList;
import es.proyecto.juego.logica.entidades.Enemy;
import es.proyecto.juego.logica.items.Potion;
import es.proyecto.juego.logica.mundo.Cell;
import es.proyecto.juego.logica.mundo.CellType;
import es.proyecto.juego.logica.mundo.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomJUnitTest {
    @Test
    void getCellAndSetCellWork() {
        Room room = createRoom();
        Cell wall = new Cell(CellType.WALL);

        room.setCell(1, 1, wall);

        assertSame(wall, room.getCell(1, 1));
        assertEquals(CellType.WALL, room.getCell(1, 1).getType());
    }

    @Test
    void invalidCoordinatesThrowException() {
        Room room = createRoom();

        assertThrows(IndexOutOfBoundsException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                room.getCell(-1, 0);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                room.setCell(0, 3, new Cell(CellType.EMPTY));
            }
        });
    }

    @Test
    void getDoorsReturnsConfiguredDoors() {
        Room room = createRoom();

        room.configureDoor(0, 1, 2, false, false);
        room.configureDoor(2, 1, -1, false, true);

        assertEquals(2, room.getDoors().size());
    }

    @Test
    void placingAndRemovingEnemiesKeepsCellConsistent() {
        Room room = createRoom();
        Enemy enemy = new Enemy("Enemigo", 10, 1, 3, 1, 1, 1);

        room.placeEnemy(enemy);
        assertSame(enemy, room.getEnemyAt(1, 1));
        assertEquals(CellType.ENEMY, room.getCell(1, 1).getType());

        assertTrue(room.removeEnemy(enemy));
        assertNull(room.getEnemyAt(1, 1));
        assertEquals(CellType.EMPTY, room.getCell(1, 1).getType());
    }

    @Test
    void cellDoesNotKeepIncompatibleState() {
        Cell cell = new Cell(CellType.EMPTY);
        Enemy enemy = new Enemy("Enemigo", 10, 1, 3, 1, 0, 0);

        cell.setEnemy(enemy);
        cell.setItem(new Potion("Pocion", 10));

        assertNull(cell.getEnemy());
        assertTrue(cell.hasItem());
        assertEquals(CellType.ITEM, cell.getType());

        cell.configureTrap(5);
        assertFalse(cell.hasItem());
        assertEquals(CellType.TRAP, cell.getType());
        assertEquals(5, cell.getTrapDamage());
    }

    private Room createRoom() {
        return new Room(1, "Sala", 3, 3, new MyLinkedList<Enemy>());
    }

    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    }
}
