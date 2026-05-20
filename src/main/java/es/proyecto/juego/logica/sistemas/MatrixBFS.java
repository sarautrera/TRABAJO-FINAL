package es.proyecto.juego.logica.sistemas;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.MyLinkedList;
import es.proyecto.juego.estructuras.MyLinkedQueue;
import es.proyecto.juego.logica.mundo.Room;

public final class MatrixBFS {
    private static final int[][] DIRECTIONS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    private MatrixBFS() {
    }

    public static IList<int[]> reachableCells(Room room, int startRow, int startCol, int maxSteps) {
        validateInput(room, startRow, startCol, maxSteps);

        boolean[][] visited = new boolean[room.getRows()][room.getCols()];
        MyLinkedList<int[]> reachable = new MyLinkedList<>();
        MyLinkedQueue<Position> pending = new MyLinkedQueue<>();

        visited[startRow][startCol] = true;
        pending.enqueue(new Position(startRow, startCol, 0));

        while (!pending.isEmpty()) {
            Position current = pending.dequeue();
            if (current.distance > 0) {
                reachable.add(new int[]{current.row, current.col});
            }
            if (current.distance == maxSteps) {
                continue;
            }

            for (int i = 0; i < DIRECTIONS.length; i++) {
                int nextRow = current.row + DIRECTIONS[i][0];
                int nextCol = current.col + DIRECTIONS[i][1];
                if (canVisit(room, visited, nextRow, nextCol)) {
                    visited[nextRow][nextCol] = true;
                    pending.enqueue(new Position(nextRow, nextCol, current.distance + 1));
                }
            }
        }

        return reachable;
    }

    public static int distance(Room room, int startRow, int startCol, int targetRow, int targetCol) {
        validateInput(room, startRow, startCol, 0);
        if (!room.isInside(targetRow, targetCol)) {
            throw new IndexOutOfBoundsException("Destino fuera de la habitacion: " + targetRow + ", " + targetCol);
        }

        boolean[][] visited = new boolean[room.getRows()][room.getCols()];
        MyLinkedQueue<Position> pending = new MyLinkedQueue<>();

        visited[startRow][startCol] = true;
        pending.enqueue(new Position(startRow, startCol, 0));

        while (!pending.isEmpty()) {
            Position current = pending.dequeue();
            if (current.row == targetRow && current.col == targetCol) {
                return current.distance;
            }

            for (int i = 0; i < DIRECTIONS.length; i++) {
                int nextRow = current.row + DIRECTIONS[i][0];
                int nextCol = current.col + DIRECTIONS[i][1];
                if (canVisit(room, visited, nextRow, nextCol)) {
                    visited[nextRow][nextCol] = true;
                    pending.enqueue(new Position(nextRow, nextCol, current.distance + 1));
                }
            }
        }

        return -1;
    }

    private static boolean canVisit(Room room, boolean[][] visited, int row, int col) {
        return room.isInside(row, col) && !visited[row][col] && room.getCell(row, col).isWalkable();
    }

    private static void validateInput(Room room, int startRow, int startCol, int maxSteps) {
        if (room == null) {
            throw new IllegalArgumentException("La habitacion no puede ser null");
        }
        if (!room.isInside(startRow, startCol)) {
            throw new IndexOutOfBoundsException("Origen fuera de la habitacion: " + startRow + ", " + startCol);
        }
        if (maxSteps < 0) {
            throw new IllegalArgumentException("Los pasos maximos no pueden ser negativos");
        }
    }

    private static final class Position {
        private final int row;
        private final int col;
        private final int distance;

        private Position(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }
}
