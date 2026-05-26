/*
 * Resumen del fichero: Representa una armadura equipable que mejora la defensa del jugador.
 */
package es.proyecto.juego.logica.items;

import es.proyecto.juego.logica.entidades.Player;

public class Armor extends Item {
    private final int defenseBonus;

    public Armor(String name, int defenseBonus) {
        super(name, false, -1);
        if (defenseBonus < 0) {
            throw new IllegalArgumentException("El bonus de defensa no puede ser negativo");
        }
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
