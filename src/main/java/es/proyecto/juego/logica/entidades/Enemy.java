package es.proyecto.juego.logica.entidades;

import es.proyecto.juego.estructuras.MyLinkedQueue;
import es.proyecto.juego.logica.mundo.CellType;
import es.proyecto.juego.logica.mundo.Room;
import es.proyecto.juego.logica.sistemas.CombatSystem;

public class Enemy {
    private static final int[][] DIRECTIONS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    private final String name;
    private int hp;
    private final int maxHp;
    private final int speed;
    private final int attack;
    private final int defense;
    private int row;
    private int col;

    public Enemy(String name, int maxHp, int speed, int attack, int defense, int row, int col) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("El nombre del enemigo no puede estar vacio");
        }
        if (maxHp <= 0 || speed < 0 || attack < 0 || defense < 0) {
            throw new IllegalArgumentException("Atributos invalidos para el enemigo");
        }
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.row = row;
        this.col = col;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getEffectiveAttack() {
        return attack;
    }

    public int getEffectiveDefense() {
        return defense;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void takeDamage(int damage) {
        hp = Math.max(0, hp - Math.max(0, damage));
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public boolean isAdjacentTo(int targetRow, int targetCol) {
        int rowDistance = Math.abs(row - targetRow);
        int colDistance = Math.abs(col - targetCol);
        return rowDistance + colDistance == 1;
    }

    public int attack(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("El jugador no puede ser null");
        }
        int damage = CombatSystem.calculateDamage(getEffectiveAttack(), player.getEffectiveDefense());
        player.takeDamage(damage);
        return damage;
    }

    public boolean moveOneStepToward(Player player, Room room) {
        if (player == null || room == null) {
            throw new IllegalArgumentException("Jugador y habitacion son obligatorios");
        }
        if (!isAlive() || isAdjacentTo(player.getRow(), player.getCol())) {
            return false;
        }

        int[] nextStep = findNextStepWithBfs(player, room);
        if (nextStep == null) {
            return false;
        }

        int bestRow = nextStep[0];
        int bestCol = nextStep[1];

        if ((bestRow == row && bestCol == col) || (bestRow == player.getRow() && bestCol == player.getCol())) {
            return false;
        }
        moveTo(room, bestRow, bestCol);
        return true;
    }

    private int[] findNextStepWithBfs(Player player, Room room) {
        boolean[][] visited = new boolean[room.getRows()][room.getCols()];
        MyLinkedQueue<SearchNode> pending = new MyLinkedQueue<>();

        visited[row][col] = true;
        pending.enqueue(new SearchNode(row, col, row, col, 0));

        while (!pending.isEmpty()) {
            SearchNode current = pending.dequeue();
            if (current.distance > 0 && isAdjacentToPlayer(current.row, current.col, player)) {
                return new int[]{current.firstRow, current.firstCol, current.distance};
            }

            for (int i = 0; i < DIRECTIONS.length; i++) {
                int nextRow = current.row + DIRECTIONS[i][0];
                int nextCol = current.col + DIRECTIONS[i][1];
                if (canEnemyVisit(room, player, visited, nextRow, nextCol)) {
                    visited[nextRow][nextCol] = true;
                    int firstRow = current.distance == 0 ? nextRow : current.firstRow;
                    int firstCol = current.distance == 0 ? nextCol : current.firstCol;
                    pending.enqueue(new SearchNode(nextRow, nextCol, firstRow, firstCol, current.distance + 1));
                }
            }
        }
        return null;
    }

    private boolean canEnemyVisit(Room room, Player player, boolean[][] visited, int targetRow, int targetCol) {
        if (!room.isInside(targetRow, targetCol) || visited[targetRow][targetCol]) {
            return false;
        }
        if (targetRow == player.getRow() && targetCol == player.getCol()) {
            return false;
        }
        return room.getCell(targetRow, targetCol).getType() == CellType.EMPTY;
    }

    private boolean isAdjacentToPlayer(int candidateRow, int candidateCol, Player player) {
        return Math.abs(candidateRow - player.getRow()) + Math.abs(candidateCol - player.getCol()) == 1;
    }

    private void moveTo(Room room, int targetRow, int targetCol) {
        room.getCell(row, col).clearOccupant();
        setPosition(targetRow, targetCol);
        room.getCell(targetRow, targetCol).setEnemy(this);
    }

    private static final class SearchNode {
        private final int row;
        private final int col;
        private final int firstRow;
        private final int firstCol;
        private final int distance;

        private SearchNode(int row, int col, int firstRow, int firstCol, int distance) {
            this.row = row;
            this.col = col;
            this.firstRow = firstRow;
            this.firstCol = firstCol;
            this.distance = distance;
        }
    }
}
