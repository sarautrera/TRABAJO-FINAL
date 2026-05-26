// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica.mundo;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Enemy;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Item;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class Cell {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private CellType type;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Item item;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Enemy enemy;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int doorTargetId = -1;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private boolean doorLocked;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private boolean exteriorExit;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private boolean doorOpen;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int trapDamage;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Cell(CellType type) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setType(type);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public CellType getType() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return type;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void setType(CellType type) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El tipo de celda no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.type = type;
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type != CellType.ITEM) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            item = null;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type != CellType.ENEMY) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            enemy = null;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type != CellType.DOOR) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            clearDoorData();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type != CellType.TRAP) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            trapDamage = 0;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Item getItem() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return item;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void setItem(Item item) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        clearOccupant();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        clearDoorData();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        trapDamage = 0;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.item = item;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.type = item == null ? CellType.EMPTY : CellType.ITEM;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Enemy getEnemy() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return enemy;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void setEnemy(Enemy enemy) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        item = null;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        clearDoorData();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        trapDamage = 0;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.enemy = enemy;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.type = enemy == null ? CellType.EMPTY : CellType.ENEMY;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void clearOccupant() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        enemy = null;
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type == CellType.ENEMY) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            type = CellType.EMPTY;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getDoorTargetId() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return doorTargetId;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void configureDoor(int doorTargetId, boolean doorLocked, boolean exteriorExit) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        item = null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        enemy = null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        trapDamage = 0;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.type = CellType.DOOR;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.doorTargetId = doorTargetId;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.doorLocked = doorLocked;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.exteriorExit = exteriorExit;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.doorOpen = false;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isDoorLocked() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return doorLocked;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void setDoorLocked(boolean doorLocked) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureDoor();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.doorLocked = doorLocked;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isExteriorExit() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return exteriorExit;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isDoorOpen() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return doorOpen;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void setDoorOpen(boolean doorOpen) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        ensureDoor();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (doorOpen) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            doorLocked = false;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.doorOpen = doorOpen;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getTrapDamage() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return trapDamage;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void configureTrap(int trapDamage) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (trapDamage < 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El dano de trampa no puede ser negativo");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        item = null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        enemy = null;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        clearDoorData();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.type = CellType.TRAP;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.trapDamage = trapDamage;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean hasItem() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return item != null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean hasEnemy() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return enemy != null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isDoor() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return type == CellType.DOOR;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isWalkable() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return type == CellType.EMPTY || (type == CellType.DOOR && !doorLocked);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void ensureDoor() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type != CellType.DOOR) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("La celda no es una puerta");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void clearDoorData() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        doorTargetId = -1;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        doorLocked = false;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        exteriorExit = false;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        doorOpen = false;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
