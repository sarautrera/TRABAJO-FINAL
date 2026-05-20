package es.proyecto.juego.logica.entidades;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.logica.items.Armor;
import es.proyecto.juego.logica.items.Item;
import es.proyecto.juego.logica.items.Weapon;

public class Player {
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

    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    public void equipArmor(Armor armor) {
        this.equippedArmor = armor;
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
