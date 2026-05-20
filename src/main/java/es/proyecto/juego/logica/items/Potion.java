package es.proyecto.juego.logica.items;

import es.proyecto.juego.logica.entidades.Player;

public class Potion extends Item {
    private final int hpRestore;

    public Potion(String name, int hpRestore) {
        super(name, true, 1);
        this.hpRestore = hpRestore;
    }

    public int getHpRestore() {
        return hpRestore;
    }

    @Override
    public void applyEffect(Player player) {
        player.heal(hpRestore);
        consumeUse();
    }
}
