package es.proyecto.juego.logica.items;

import es.proyecto.juego.logica.entidades.Player;

public class Key extends Item {
    private final int targetDoorId;

    public Key(String name, int targetDoorId) {
        super(name, false, -1);
        this.targetDoorId = targetDoorId;
    }

    public int getTargetDoorId() {
        return targetDoorId;
    }

    @Override
    public boolean isEquippable() {
        return true;
    }

    @Override
    public void applyEffect(Player player) {
        // La apertura real se valida en GameEngineImpl.openDoor().
    }
}
