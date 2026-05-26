// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica.sistemas;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedQueue;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Room;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public final class MatrixBFS {
    // Comentario de estudiante: aqui se guarda o actualiza un valor.
    private static final int[][] DIRECTIONS = {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            {-1, 0},
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            {1, 0},
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            {0, -1},
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            {0, 1}
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    };

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private MatrixBFS() {
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public static IList<int[]> reachableCells(Room room, int startRow, int startCol, int maxSteps) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validateInput(room, startRow, startCol, maxSteps);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        boolean[][] visited = new boolean[room.getRows()][room.getCols()];
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<int[]> reachable = new MyLinkedList<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedQueue<Position> pending = new MyLinkedQueue<>();

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        visited[startRow][startCol] = true;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        pending.enqueue(new Position(startRow, startCol, 0));

        // Comentario de estudiante: aqui empieza un bucle while.
        while (!pending.isEmpty()) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Position current = pending.dequeue();
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (current.distance > 0) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                reachable.add(new int[]{current.row, current.col});
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (current.distance == maxSteps) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                continue;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }

            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int i = 0; i < DIRECTIONS.length; i++) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                int nextRow = current.row + DIRECTIONS[i][0];
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                int nextCol = current.col + DIRECTIONS[i][1];
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (canVisit(room, visited, nextRow, nextCol)) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    visited[nextRow][nextCol] = true;
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    pending.enqueue(new Position(nextRow, nextCol, current.distance + 1));
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return reachable;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public static int distance(Room room, int startRow, int startCol, int targetRow, int targetCol) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validateInput(room, startRow, startCol, 0);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!room.isInside(targetRow, targetCol)) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IndexOutOfBoundsException("Destino fuera de la habitacion: " + targetRow + ", " + targetCol);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        boolean[][] visited = new boolean[room.getRows()][room.getCols()];
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedQueue<Position> pending = new MyLinkedQueue<>();

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        visited[startRow][startCol] = true;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        pending.enqueue(new Position(startRow, startCol, 0));

        // Comentario de estudiante: aqui empieza un bucle while.
        while (!pending.isEmpty()) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Position current = pending.dequeue();
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (current.row == targetRow && current.col == targetCol) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return current.distance;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }

            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int i = 0; i < DIRECTIONS.length; i++) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                int nextRow = current.row + DIRECTIONS[i][0];
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                int nextCol = current.col + DIRECTIONS[i][1];
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (canVisit(room, visited, nextRow, nextCol)) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    visited[nextRow][nextCol] = true;
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    pending.enqueue(new Position(nextRow, nextCol, current.distance + 1));
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return -1;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private static boolean canVisit(Room room, boolean[][] visited, int row, int col) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return room.isInside(row, col) && !visited[row][col] && room.getCell(row, col).isWalkable();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private static void validateInput(Room room, int startRow, int startCol, int maxSteps) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (room == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("La habitacion no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!room.isInside(startRow, startCol)) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IndexOutOfBoundsException("Origen fuera de la habitacion: " + startRow + ", " + startCol);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (maxSteps < 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("Los pasos maximos no pueden ser negativos");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    private static final class Position {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final int row;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final int col;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final int distance;

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private Position(int row, int col, int distance) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.row = row;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.col = col;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.distance = distance;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
