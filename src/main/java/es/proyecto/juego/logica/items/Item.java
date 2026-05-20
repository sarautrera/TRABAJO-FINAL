package es.proyecto.juego.logica.items;

import es.proyecto.juego.logica.entidades.Player;

public abstract class Item {
    private final String name;
    private final boolean consumable;
    private int usesLeft;

    protected Item(String name, boolean consumable, int usesLeft) {
        this.name = name;
        this.consumable = consumable;
        this.usesLeft = usesLeft;
    }

    public String getName() {
        return name;
    }

    public boolean isConsumable() {
        return consumable;
    }

    public int getUsesLeft() {
        return usesLeft;
    }

    public boolean isEquippable() {
        return false;
    }

    public void consumeUse() {
        if (usesLeft > 0) {
            usesLeft--;
        }
    }

    public abstract void applyEffect(Player player);
}
