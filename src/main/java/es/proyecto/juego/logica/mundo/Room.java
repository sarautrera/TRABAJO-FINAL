package es.proyecto.juego.logica.mundo;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.MyLinkedList;
import es.proyecto.juego.logica.entidades.Enemy;
import es.proyecto.juego.logica.items.Item;

public class Room {
    private final int id;
    private final String name;
    private final int rows;
    private final int cols;
    private final Cell[][] grid;
    private final IList<Enemy> enemies;
    private boolean visited;

    public Room(int id, String name, int rows, int cols, IList<Enemy> enemies) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("El nombre de la habitacion no puede estar vacio");
        }
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Las dimensiones de la habitacion deben ser positivas");
        }
        if (enemies == null) {
            throw new IllegalArgumentException("La lista de enemigos no puede ser null");
        }
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
        if (cell == null) {
            throw new IllegalArgumentException("La celda no puede ser null");
        }
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

    public boolean isWalkable(int row, int col) {
        validatePosition(row, col);
        return grid[row][col].isWalkable();
    }

    public void placeEnemy(Enemy enemy) {
        if (enemy == null) {
            throw new IllegalArgumentException("El enemigo no puede ser null");
        }
        validatePosition(enemy.getRow(), enemy.getCol());
        Cell cell = getCell(enemy.getRow(), enemy.getCol());
        if (cell.getType() != CellType.EMPTY) {
            throw new IllegalStateException("No se puede colocar enemigo en celda ocupada");
        }
        cell.setEnemy(enemy);
        if (!enemies.contains(enemy)) {
            enemies.add(enemy);
        }
    }

    public boolean removeEnemy(Enemy enemy) {
        if (enemy == null) {
            return false;
        }
        if (isInside(enemy.getRow(), enemy.getCol())) {
            Cell cell = getCell(enemy.getRow(), enemy.getCol());
            if (cell.getEnemy() == enemy) {
                cell.clearOccupant();
            }
        }
        return enemies.remove(enemy);
    }

    public Enemy getEnemyAt(int row, int col) {
        validatePosition(row, col);
        return grid[row][col].getEnemy();
    }

    public void placeItem(int row, int col, Item item) {
        validatePosition(row, col);
        Cell cell = getCell(row, col);
        if (cell.getType() != CellType.EMPTY) {
            throw new IllegalStateException("No se puede colocar item en celda ocupada");
        }
        cell.setItem(item);
    }

    public Item takeItem(int row, int col) {
        validatePosition(row, col);
        Cell cell = getCell(row, col);
        Item item = cell.getItem();
        if (item == null) {
            throw new IllegalStateException("No hay item en la celda indicada");
        }
        cell.setItem(null);
        return item;
    }

    public void configureDoor(int row, int col, int targetRoomId, boolean locked, boolean exteriorExit) {
        validatePosition(row, col);
        grid[row][col].configureDoor(targetRoomId, locked, exteriorExit);
    }

    public IList<int[]> getDoors() {
        MyLinkedList<int[]> doors = new MyLinkedList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col].isDoor()) {
                    doors.add(new int[]{row, col});
                }
            }
        }
        return doors;
    }

    public boolean isAdjacent(int firstRow, int firstCol, int secondRow, int secondCol) {
        validatePosition(firstRow, firstCol);
        validatePosition(secondRow, secondCol);
        return Math.abs(firstRow - secondRow) + Math.abs(firstCol - secondCol) == 1;
    }

    private void validatePosition(int row, int col) {
        if (!isInside(row, col)) {
            throw new IndexOutOfBoundsException("Celda fuera de la habitacion: " + row + ", " + col);
        }
    }
}
