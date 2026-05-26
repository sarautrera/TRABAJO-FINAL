/*
 * Resumen del fichero: Representa una llave usada para abrir puertas concretas.
 */
package es.proyecto.juego.logica.items;

import es.proyecto.juego.logica.entidades.Player;

public class Key extends Item {
    private final int targetDoorId;

    public Key(String name, int targetDoorId) {
        super(name, false, -1);
        if (targetDoorId < 0) {
            throw new IllegalArgumentException("El id de puerta objetivo no puede ser negativo");
        }
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
