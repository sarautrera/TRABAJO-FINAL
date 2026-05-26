/*
 * Resumen del fichero: Representa una pocion que cura al jugador al utilizarse.
 */
package es.proyecto.juego.logica.items;

import es.proyecto.juego.logica.entidades.Player;

public class Potion extends Item {
    private final int hpRestore;

    public Potion(String name, int hpRestore) {
        super(name, true, 1);
        if (hpRestore <= 0) {
            throw new IllegalArgumentException("La curacion de una pocion debe ser positiva");
        }
        this.hpRestore = hpRestore;
    }

    public int getHpRestore() {
        return hpRestore;
    }

    @Override
    public void applyEffect(Player player) {
        if (!canUse()) {
            throw new IllegalStateException("La pocion no tiene usos disponibles");
        }
        player.heal(hpRestore);
        consumeUse();
    }
}
