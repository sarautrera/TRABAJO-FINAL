/*
 * Resumen del fichero: Contiene pruebas automatizadas para comprobar el comportamiento de MatrixBFSJUnitTest.
 */
package es.proyecto.juego.tests;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.MyLinkedList;
import es.proyecto.juego.logica.entidades.Enemy;
import es.proyecto.juego.logica.items.Potion;
import es.proyecto.juego.logica.mundo.Cell;
import es.proyecto.juego.logica.mundo.CellType;
import es.proyecto.juego.logica.mundo.Room;
import es.proyecto.juego.logica.sistemas.MatrixBFS;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatrixBFSJUnitTest {
    @Test
    void reachableCellsUsesCardinalMovementAndAvoidsWalls() {
        Room room = createRoom();
        room.setCell(1, 1, new Cell(CellType.WALL));

        IList<int[]> reachable = MatrixBFS.reachableCells(room, 0, 0, 2);

        assertTrue(containsCell(reachable, 0, 1));
        assertTrue(containsCell(reachable, 1, 0));
        assertTrue(containsCell(reachable, 0, 2));
        assertTrue(containsCell(reachable, 2, 0));
        assertFalse(containsCell(reachable, 1, 1));
        assertFalse(containsCell(reachable, 2, 2));
    }

    @Test
    void itemCellsBlockMovementAndMustBeInteractedFromAdjacentCell() {
        Room room = createRoom();
        room.placeItem(0, 1, new Potion("Pocion", 10));

        IList<int[]> reachable = MatrixBFS.reachableCells(room, 0, 0, 2);

        assertFalse(containsCell(reachable, 0, 1));
        assertTrue(containsCell(reachable, 1, 0));
    }

    @Test
    void distanceReturnsShortestPathOrMinusOne() {
        Room room = createRoom();
        room.setCell(1, 1, new Cell(CellType.WALL));

        assertEquals(4, MatrixBFS.distance(room, 0, 0, 2, 2));

        room.setCell(1, 2, new Cell(CellType.WALL));
        room.setCell(2, 1, new Cell(CellType.WALL));
        room.setCell(0, 1, new Cell(CellType.WALL));
        room.setCell(1, 0, new Cell(CellType.WALL));

        assertEquals(-1, MatrixBFS.distance(room, 0, 0, 2, 2));
    }

    @Test
    void invalidInputThrowsException() {
        Room room = createRoom();

        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                MatrixBFS.reachableCells(room, 0, 0, -1);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                MatrixBFS.distance(room, 0, 0, 5, 5);
            }
        });
    }

    private Room createRoom() {
        return new Room(1, "Sala", 3, 3, new MyLinkedList<Enemy>());
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

    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    }
}
