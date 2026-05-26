package es.proyecto.juego.logica.entidades;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.logica.items.Armor;
import es.proyecto.juego.logica.items.Item;
import es.proyecto.juego.logica.items.Key;
import es.proyecto.juego.logica.items.Weapon;
import es.proyecto.juego.logica.excepciones.InventoryFullException;

public class Player {
    public static final int MAX_INVENTORY_SIZE = 10;

    private final String name;
    private int hp;
    private final int maxHp;
    private final int speed;
    private final int baseAttack;
    private final int baseDefense;
    private int row;
    private int col;
    private final IList<Item> inventory;
    private Weapon equippedWeapon;
    private Armor equippedArmor;

    public Player(String name, int maxHp, int speed, int baseAttack, int baseDefense,
                  int row, int col, IList<Item> inventory) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("El nombre del jugador no puede estar vacio");
        }
        if (maxHp <= 0 || speed < 0 || baseAttack < 0 || baseDefense < 0) {
            throw new IllegalArgumentException("Atributos invalidos para el jugador");
        }
        if (inventory == null) {
            throw new IllegalArgumentException("El inventario no puede ser null");
        }
        if (inventory.size() > MAX_INVENTORY_SIZE) {
            throw new InventoryFullException("El inventario inicial supera el limite de " + MAX_INVENTORY_SIZE);
        }
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.speed = speed;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.row = row;
        this.col = col;
        this.inventory = inventory;
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
        int bonus = equippedWeapon == null ? 0 : equippedWeapon.getAttackBonus();
        return baseAttack + bonus;
    }

    public int getEffectiveDefense() {
        int bonus = equippedArmor == null ? 0 : equippedArmor.getDefenseBonus();
        return baseDefense + bonus;
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

    public IList<Item> getInventory() {
        return inventory;
    }

    public int getMaxInventorySize() {
        return MAX_INVENTORY_SIZE;
    }

    public boolean isInventoryFull() {
        return inventory.size() >= MAX_INVENTORY_SIZE;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("El item no puede ser null");
        }
        if (isInventoryFull()) {
            throw new InventoryFullException("El inventario esta lleno");
        }
        inventory.add(item);
    }

    public Item getInventoryItem(int index) {
        return inventory.get(index);
    }

    public Item removeInventoryItem(int index) {
        return inventory.remove(index);
    }

    public boolean removeInventoryItem(Item item) {
        return inventory.remove(item);
    }

    public void useInventoryItem(int index) {
        Item item = inventory.get(index);
        item.applyEffect(this);
        if (item.isDepleted()) {
            inventory.remove(index);
        }
    }

    public void equipWeapon(Weapon weapon) {
        if (weapon == null) {
            throw new IllegalArgumentException("El arma no puede ser null");
        }
        this.equippedWeapon = weapon;
    }

    public void equipArmor(Armor armor) {
        if (armor == null) {
            throw new IllegalArgumentException("La armadura no puede ser null");
        }
        this.equippedArmor = armor;
    }

    public boolean hasKeyForDoor(int doorId) {
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item instanceof Key && ((Key) item).getTargetDoorId() == doorId) {
                return true;
            }
        }
        return false;
    }

    public void takeDamage(int damage) {
        hp = Math.max(0, hp - Math.max(0, damage));
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + Math.max(0, amount));
    }

    public boolean isAlive() {
        return hp > 0;
    }
}
