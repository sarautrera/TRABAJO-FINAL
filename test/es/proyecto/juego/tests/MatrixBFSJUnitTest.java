// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.tests;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Enemy;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Potion;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Cell;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.CellType;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Room;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.sistemas.MatrixBFS;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import org.junit.jupiter.api.Test;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertThrows;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class MatrixBFSJUnitTest {
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void reachableCellsUsesCardinalMovementAndAvoidsWalls() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = createRoom();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.setCell(1, 1, new Cell(CellType.WALL));

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IList<int[]> reachable = MatrixBFS.reachableCells(room, 0, 0, 2);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(containsCell(reachable, 0, 1));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(containsCell(reachable, 1, 0));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(containsCell(reachable, 0, 2));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(containsCell(reachable, 2, 0));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(containsCell(reachable, 1, 1));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(containsCell(reachable, 2, 2));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void itemCellsBlockMovementAndMustBeInteractedFromAdjacentCell() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = createRoom();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.placeItem(0, 1, new Potion("Pocion", 10));

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IList<int[]> reachable = MatrixBFS.reachableCells(room, 0, 0, 2);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(containsCell(reachable, 0, 1));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(containsCell(reachable, 1, 0));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void distanceReturnsShortestPathOrMinusOne() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = createRoom();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.setCell(1, 1, new Cell(CellType.WALL));

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(4, MatrixBFS.distance(room, 0, 0, 2, 2));

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.setCell(1, 2, new Cell(CellType.WALL));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.setCell(2, 1, new Cell(CellType.WALL));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.setCell(0, 1, new Cell(CellType.WALL));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.setCell(1, 0, new Cell(CellType.WALL));

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(-1, MatrixBFS.distance(room, 0, 0, 2, 2));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void invalidInputThrowsException() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = createRoom();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                MatrixBFS.reachableCells(room, 0, 0, -1);
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
                MatrixBFS.distance(room, 0, 0, 5, 5);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private Room createRoom() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return new Room(1, "Sala", 3, 3, new MyLinkedList<Enemy>());
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

    // Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
