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
    private int trapDamage;

    public Cell(CellType type) {
        setType(type);
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        if (type == null) {
            throw new IllegalArgumentException("El tipo de celda no puede ser null");
        }
        this.type = type;
        if (type != CellType.ITEM) {
            item = null;
        }
        if (type != CellType.ENEMY) {
            enemy = null;
        }
        if (type != CellType.DOOR) {
            clearDoorData();
        }
        if (type != CellType.TRAP) {
            trapDamage = 0;
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        clearOccupant();
        clearDoorData();
        trapDamage = 0;
        this.item = item;
        this.type = item == null ? CellType.EMPTY : CellType.ITEM;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        item = null;
        clearDoorData();
        trapDamage = 0;
        this.enemy = enemy;
        this.type = enemy == null ? CellType.EMPTY : CellType.ENEMY;
    }

    public void clearOccupant() {
        enemy = null;
        if (type == CellType.ENEMY) {
            type = CellType.EMPTY;
        }
    }

    public int getDoorTargetId() {
        return doorTargetId;
    }

    public void configureDoor(int doorTargetId, boolean doorLocked, boolean exteriorExit) {
        item = null;
        enemy = null;
        trapDamage = 0;
        this.type = CellType.DOOR;
        this.doorTargetId = doorTargetId;
        this.doorLocked = doorLocked;
        this.exteriorExit = exteriorExit;
        this.doorOpen = false;
    }

    public boolean isDoorLocked() {
        return doorLocked;
    }

    public void setDoorLocked(boolean doorLocked) {
        ensureDoor();
        this.doorLocked = doorLocked;
    }

    public boolean isExteriorExit() {
        return exteriorExit;
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public void setDoorOpen(boolean doorOpen) {
        ensureDoor();
        if (doorOpen) {
            doorLocked = false;
        }
        this.doorOpen = doorOpen;
    }

    public int getTrapDamage() {
        return trapDamage;
    }

    public void configureTrap(int trapDamage) {
        if (trapDamage < 0) {
            throw new IllegalArgumentException("El dano de trampa no puede ser negativo");
        }
        item = null;
        enemy = null;
        clearDoorData();
        this.type = CellType.TRAP;
        this.trapDamage = trapDamage;
    }

    public boolean hasItem() {
        return item != null;
    }

    public boolean hasEnemy() {
        return enemy != null;
    }

    public boolean isDoor() {
        return type == CellType.DOOR;
    }

    public boolean isWalkable() {
        return type == CellType.EMPTY || (type == CellType.DOOR && !doorLocked);
    }

    private void ensureDoor() {
        if (type != CellType.DOOR) {
            throw new IllegalStateException("La celda no es una puerta");
        }
    }

    private void clearDoorData() {
        doorTargetId = -1;
        doorLocked = false;
        exteriorExit = false;
        doorOpen = false;
    }
}
