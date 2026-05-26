// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica.entidades;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Armor;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Item;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Key;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Weapon;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.InventoryFullException;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class Player {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    public static final int MAX_INVENTORY_SIZE = 10;

    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final String name;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int hp;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int maxHp;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int speed;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int baseAttack;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int baseDefense;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int row;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int col;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final IList<Item> inventory;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Weapon equippedWeapon;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Armor equippedArmor;

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    public Player(String name, int maxHp, int speed, int baseAttack, int baseDefense,
                  // Comentario de estudiante: aqui se prepara una instruccion del programa.
                  int row, int col, IList<Item> inventory) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (name == null || name.length() == 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El nombre del jugador no puede estar vacio");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (maxHp <= 0 || speed < 0 || baseAttack < 0 || baseDefense < 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("Atributos invalidos para el jugador");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (inventory == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El inventario no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (inventory.size() > MAX_INVENTORY_SIZE) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InventoryFullException("El inventario inicial supera el limite de " + MAX_INVENTORY_SIZE);
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
        this.baseAttack = baseAttack;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.baseDefense = baseDefense;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.row = row;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.col = col;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.inventory = inventory;
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
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int bonus = equippedWeapon == null ? 0 : equippedWeapon.getAttackBonus();
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return baseAttack + bonus;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getEffectiveDefense() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int bonus = equippedArmor == null ? 0 : equippedArmor.getDefenseBonus();
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return baseDefense + bonus;
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
    public IList<Item> getInventory() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return inventory;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getMaxInventorySize() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return MAX_INVENTORY_SIZE;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isInventoryFull() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return inventory.size() >= MAX_INVENTORY_SIZE;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Weapon getEquippedWeapon() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return equippedWeapon;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Armor getEquippedArmor() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return equippedArmor;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void addItem(Item item) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (item == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El item no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isInventoryFull()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new InventoryFullException("El inventario esta lleno");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        inventory.add(item);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Item getInventoryItem(int index) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return inventory.get(index);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Item removeInventoryItem(int index) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return inventory.remove(index);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean removeInventoryItem(Item item) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return inventory.remove(item);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void useInventoryItem(int index) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Item item = inventory.get(index);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        item.applyEffect(this);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (item.isDepleted()) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            inventory.remove(index);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void equipWeapon(Weapon weapon) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (weapon == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El arma no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.equippedWeapon = weapon;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void equipArmor(Armor armor) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (armor == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("La armadura no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.equippedArmor = armor;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean hasKeyForDoor(int doorId) {
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < inventory.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Item item = inventory.get(i);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (item instanceof Key && ((Key) item).getTargetDoorId() == doorId) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return true;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return false;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void takeDamage(int damage) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        hp = Math.max(0, hp - Math.max(0, damage));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void heal(int amount) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        hp = Math.min(maxHp, hp + Math.max(0, amount));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isAlive() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return hp > 0;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
