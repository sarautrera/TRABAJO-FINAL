package es.proyecto.juego.logica.mundo;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.logica.entidades.Enemy;

public class Room {
    private final int id;
    private final String name;
    private final int rows;
    private final int cols;
    private final Cell[][] grid;
    private final IList<Enemy> enemies;
    private boolean visited;

    public Room(int id, String name, int rows, int cols, IList<Enemy> enemies) {
        this.id = id;
        this.name = name;
        this.rows = rows;
        this.cols = cols;
        this.enemies = enemies;
        this.grid = new Cell[rows][cols];
        initializeEmptyGrid();
    }

    private void initializeEmptyGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new Cell(CellType.EMPTY);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCell(int row, int col) {
        validatePosition(row, col);
        return grid[row][col];
    }

    public void setCell(int row, int col, Cell cell) {
        validatePosition(row, col);
        grid[row][col] = cell;
    }

    public IList<Enemy> getEnemies() {
        return enemies;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isInside(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private void validatePosition(int row, int col) {
        if (!isInside(row, col)) {
            throw new IndexOutOfBoundsException("Celda fuera de la habitacion: " + row + ", " + col);
        }
    }
}
