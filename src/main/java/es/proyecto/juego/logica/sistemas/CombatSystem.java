package es.proyecto.juego.logica.sistemas;

public final class CombatSystem {
    private CombatSystem() {
    }

    public static int calculateDamage(int attack, int defense) {
        return calculateDamage(attack, defense, Math.random());
    }

    static int calculateDamage(int attack, int defense, double roll) {
        int rawDamage = (int) (attack * (roll * 2)) - defense;
        return Math.max(0, rawDamage);
    }
}
