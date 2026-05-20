package es.proyecto.juego.logica.items;

import es.proyecto.juego.logica.entidades.Player;

public abstract class Item {
    private final String name;
    private final boolean consumable;
    private int usesLeft;

    protected Item(String name, boolean consumable, int usesLeft) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("El nombre del item no puede estar vacio");
        }
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

    public boolean canUse() {
        return usesLeft != 0;
    }

    public boolean isDepleted() {
        return consumable && usesLeft == 0;
    }

    public void consumeUse() {
        if (usesLeft > 0) {
            usesLeft--;
        }
    }

    public abstract void applyEffect(Player player);
}
