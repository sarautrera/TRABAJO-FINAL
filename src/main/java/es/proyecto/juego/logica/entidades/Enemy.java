// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica.entidades;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedQueue;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.CellType;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Room;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.sistemas.CombatSystem;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class Enemy {
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

    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final String name;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int hp;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int maxHp;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int speed;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int attack;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int defense;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int row;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int col;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Enemy(String name, int maxHp, int speed, int attack, int defense, int row, int col) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (name == null || name.length() == 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El nombre del enemigo no puede estar vacio");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (maxHp <= 0 || speed < 0 || attack < 0 || defense < 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("Atributos invalidos para el enemigo");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.name = name;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.hp = maxHp;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.maxHp = maxHp;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.speed = speed;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.attack = attack;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.defense = defense;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.row = row;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.col = col;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public String getName() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return name;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getHp() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return hp;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getMaxHp() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return maxHp;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getSpeed() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return speed;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getEffectiveAttack() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return attack;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getEffectiveDefense() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return defense;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getRow() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return row;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getCol() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return col;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void setPosition(int row, int col) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.row = row;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.col = col;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void takeDamage(int damage) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        hp = Math.max(0, hp - Math.max(0, damage));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isAlive() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return hp > 0;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isAdjacentTo(int targetRow, int targetCol) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int rowDistance = Math.abs(row - targetRow);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int colDistance = Math.abs(col - targetCol);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return rowDistance + colDistance == 1;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int attack(Player player) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (player == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El jugador no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int damage = CombatSystem.calculateDamage(getEffectiveAttack(), player.getEffectiveDefense());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.takeDamage(damage);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return damage;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean moveOneStepToward(Player player, Room room) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (player == null || room == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("Jugador y habitacion son obligatorios");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!isAlive() || isAdjacentTo(player.getRow(), player.getCol())) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return false;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int[] nextStep = findNextStepWithBfs(player, room);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (nextStep == null) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return false;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int bestRow = nextStep[0];
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int bestCol = nextStep[1];

        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if ((bestRow == row && bestCol == col) || (bestRow == player.getRow() && bestCol == player.getCol())) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return false;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        moveTo(room, bestRow, bestCol);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private int[] findNextStepWithBfs(Player player, Room room) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        boolean[][] visited = new boolean[room.getRows()][room.getCols()];
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedQueue<SearchNode> pending = new MyLinkedQueue<>();

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        visited[row][col] = true;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        pending.enqueue(new SearchNode(row, col, row, col, 0));

        // Comentario de estudiante: aqui empieza un bucle while.
        while (!pending.isEmpty()) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            SearchNode current = pending.dequeue();
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (current.distance > 0 && isAdjacentToPlayer(current.row, current.col, player)) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return new int[]{current.firstRow, current.firstCol, current.distance};
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }

            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int i = 0; i < DIRECTIONS.length; i++) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                int nextRow = current.row + DIRECTIONS[i][0];
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                int nextCol = current.col + DIRECTIONS[i][1];
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (canEnemyVisit(room, player, visited, nextRow, nextCol)) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    visited[nextRow][nextCol] = true;
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    int firstRow = current.distance == 0 ? nextRow : current.firstRow;
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    int firstCol = current.distance == 0 ? nextCol : current.firstCol;
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    pending.enqueue(new SearchNode(nextRow, nextCol, firstRow, firstCol, current.distance + 1));
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private boolean canEnemyVisit(Room room, Player player, boolean[][] visited, int targetRow, int targetCol) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!room.isInside(targetRow, targetCol) || visited[targetRow][targetCol]) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return false;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (targetRow == player.getRow() && targetCol == player.getCol()) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return false;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return room.getCell(targetRow, targetCol).getType() == CellType.EMPTY;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private boolean isAdjacentToPlayer(int candidateRow, int candidateCol, Player player) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return Math.abs(candidateRow - player.getRow()) + Math.abs(candidateCol - player.getCol()) == 1;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void moveTo(Room room, int targetRow, int targetCol) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.getCell(row, col).clearOccupant();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setPosition(targetRow, targetCol);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        room.getCell(targetRow, targetCol).setEnemy(this);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    private static final class SearchNode {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final int row;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final int col;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final int firstRow;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final int firstCol;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final int distance;

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private SearchNode(int row, int col, int firstRow, int firstCol, int distance) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.row = row;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.col = col;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.firstRow = firstRow;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.firstCol = firstCol;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.distance = distance;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
