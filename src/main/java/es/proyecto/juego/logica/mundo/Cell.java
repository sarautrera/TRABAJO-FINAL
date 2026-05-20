package es.proyecto.juego.logica.mundo;

import es.proyecto.juego.logica.entidades.Enemy;
import es.proyecto.juego.logica.items.Item;

public class Cell {
    private CellType type;
    private Item item;
    private Enemy enemy;
    private int doorTargetId = -1;
    private boolean doorLocked;
    private boolean exteriorExit;
    private boolean doorOpen;

    public Cell(CellType type) {
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        this.type = item == null ? CellType.EMPTY : CellType.ITEM;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
        this.type = enemy == null ? CellType.EMPTY : CellType.ENEMY;
    }

    public int getDoorTargetId() {
        return doorTargetId;
    }

    public void configureDoor(int doorTargetId, boolean doorLocked, boolean exteriorExit) {
        this.type = CellType.DOOR;
        this.doorTargetId = doorTargetId;
        this.doorLocked = doorLocked;
        this.exteriorExit = exteriorExit;
    }

    public boolean isDoorLocked() {
        return doorLocked;
    }

    public void setDoorLocked(boolean doorLocked) {
        this.doorLocked = doorLocked;
    }

    public boolean isExteriorExit() {
        return exteriorExit;
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public void setDoorOpen(boolean doorOpen) {
        this.doorOpen = doorOpen;
    }

    public boolean isWalkable() {
        return type == CellType.EMPTY || type == CellType.DOOR;
    }
}
