package es.proyecto.juego.logica.items;

import es.proyecto.juego.logica.entidades.Player;

public class Weapon extends Item {
    private final int attackBonus;

    public Weapon(String name, int attackBonus) {
        super(name, false, -1);
        this.attackBonus = attackBonus;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    @Override
    public boolean isEquippable() {
        return true;
    }

    @Override
    public void applyEffect(Player player) {
        player.equipWeapon(this);
    }
}
