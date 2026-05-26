// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica.mundo;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Enemy;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Item;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class Room {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int id;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final String name;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int rows;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int cols;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final Cell[][] grid;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final IList<Enemy> enemies;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private boolean visited;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Room(int id, String name, int rows, int cols, IList<Enemy> enemies) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (name == null || name.length() == 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El nombre de la habitacion no puede estar vacio");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (rows <= 0 || cols <= 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("Las dimensiones de la habitacion deben ser positivas");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (enemies == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("La lista de enemigos no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.id = id;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.name = name;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.rows = rows;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.cols = cols;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.enemies = enemies;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.grid = new Cell[rows][cols];
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        initializeEmptyGrid();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void initializeEmptyGrid() {
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int row = 0; row < rows; row++) {
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int col = 0; col < cols; col++) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                grid[row][col] = new Cell(CellType.EMPTY);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getId() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return id;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public String getName() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return name;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getRows() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return rows;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getCols() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return cols;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Cell[][] getGrid() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return grid;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Cell getCell(int row, int col) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validatePosition(row, col);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return grid[row][col];
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void setCell(int row, int col, Cell cell) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validatePosition(row, col);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (cell == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("La celda no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        grid[row][col] = cell;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<Enemy> getEnemies() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return enemies;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isVisited() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return visited;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void setVisited(boolean visited) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.visited = visited;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isInside(int row, int col) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return row >= 0 && row < rows && col >= 0 && col < cols;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isWalkable(int row, int col) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validatePosition(row, col);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return grid[row][col].isWalkable();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void placeEnemy(Enemy enemy) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (enemy == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El enemigo no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validatePosition(enemy.getRow(), enemy.getCol());
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Cell cell = getCell(enemy.getRow(), enemy.getCol());
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (cell.getType() != CellType.EMPTY) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("No se puede colocar enemigo en celda ocupada");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cell.setEnemy(enemy);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!enemies.contains(enemy)) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            enemies.add(enemy);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean removeEnemy(Enemy enemy) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (enemy == null) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return false;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isInside(enemy.getRow(), enemy.getCol())) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Cell cell = getCell(enemy.getRow(), enemy.getCol());
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (cell.getEnemy() == enemy) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                cell.clearOccupant();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return enemies.remove(enemy);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Enemy getEnemyAt(int row, int col) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validatePosition(row, col);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return grid[row][col].getEnemy();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void placeItem(int row, int col, Item item) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validatePosition(row, col);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Cell cell = getCell(row, col);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (cell.getType() != CellType.EMPTY) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("No se puede colocar item en celda ocupada");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cell.setItem(item);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Item takeItem(int row, int col) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validatePosition(row, col);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Cell cell = getCell(row, col);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Item item = cell.getItem();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (item == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("No hay item en la celda indicada");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cell.setItem(null);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return item;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void configureDoor(int row, int col, int targetRoomId, boolean locked, boolean exteriorExit) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validatePosition(row, col);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        grid[row][col].configureDoor(targetRoomId, locked, exteriorExit);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<int[]> getDoors() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<int[]> doors = new MyLinkedList<>();
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int row = 0; row < rows; row++) {
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int col = 0; col < cols; col++) {
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (grid[row][col].isDoor()) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    doors.add(new int[]{row, col});
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return doors;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isAdjacent(int firstRow, int firstCol, int secondRow, int secondCol) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validatePosition(firstRow, firstCol);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validatePosition(secondRow, secondCol);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return Math.abs(firstRow - secondRow) + Math.abs(firstCol - secondCol) == 1;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void validatePosition(int row, int col) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!isInside(row, col)) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IndexOutOfBoundsException("Celda fuera de la habitacion: " + row + ", " + col);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
