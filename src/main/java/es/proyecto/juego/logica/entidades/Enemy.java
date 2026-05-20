package es.proyecto.juego.logica.entidades;

import es.proyecto.juego.logica.mundo.CellType;
import es.proyecto.juego.logica.mundo.Room;
import es.proyecto.juego.logica.sistemas.CombatSystem;

public class Enemy {
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

        int bestRow = row;
        int bestCol = col;
        int bestDistance = distanceTo(player.getRow(), player.getCol(), row, col);

        bestDistance = evaluateCandidate(room, player, row - 1, col, bestDistance);
        if (lastCandidateImproved) {
            bestRow = row - 1;
            bestCol = col;
        }
        bestDistance = evaluateCandidate(room, player, row + 1, col, bestDistance);
        if (lastCandidateImproved) {
            bestRow = row + 1;
            bestCol = col;
        }
        bestDistance = evaluateCandidate(room, player, row, col - 1, bestDistance);
        if (lastCandidateImproved) {
            bestRow = row;
            bestCol = col - 1;
        }
        evaluateCandidate(room, player, row, col + 1, bestDistance);
        if (lastCandidateImproved) {
            bestRow = row;
            bestCol = col + 1;
        }

        if (bestRow == row && bestCol == col) {
            return false;
        }
        moveTo(room, bestRow, bestCol);
        return true;
    }

    private boolean lastCandidateImproved;

    private int evaluateCandidate(Room room, Player player, int candidateRow, int candidateCol, int bestDistance) {
        lastCandidateImproved = false;
        if (!room.isInside(candidateRow, candidateCol)) {
            return bestDistance;
        }
        if (candidateRow == player.getRow() && candidateCol == player.getCol()) {
            return bestDistance;
        }
        if (room.getCell(candidateRow, candidateCol).getType() != CellType.EMPTY) {
            return bestDistance;
        }
        int candidateDistance = distanceTo(player.getRow(), player.getCol(), candidateRow, candidateCol);
        if (candidateDistance < bestDistance) {
            lastCandidateImproved = true;
            return candidateDistance;
        }
        return bestDistance;
    }

    private int distanceTo(int targetRow, int targetCol, int fromRow, int fromCol) {
        return Math.abs(targetRow - fromRow) + Math.abs(targetCol - fromCol);
    }

    private void moveTo(Room room, int targetRow, int targetCol) {
        room.getCell(row, col).clearOccupant();
        setPosition(targetRow, targetCol);
        room.getCell(targetRow, targetCol).setEnemy(this);
    }
}
