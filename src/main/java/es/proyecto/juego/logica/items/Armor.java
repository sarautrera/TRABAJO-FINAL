package es.proyecto.juego.logica.items;

import es.proyecto.juego.logica.entidades.Player;

public class Armor extends Item {
    private final int defenseBonus;

    public Armor(String name, int defenseBonus) {
        super(name, false, -1);
        this.defenseBonus = defenseBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public boolean isEquippable() {
        return true;
    }

    @Override
    public void applyEffect(Player player) {
        player.equipArmor(this);
    }
}
