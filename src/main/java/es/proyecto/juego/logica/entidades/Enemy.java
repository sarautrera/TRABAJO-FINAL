package es.proyecto.juego.logica.entidades;

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
}
