package es.proyecto.juego.logica.sistemas;

public final class CombatSystem {
    private CombatSystem() {
    }

    public static int calculateDamage(int attack, int defense) {
        return calculateDamage(attack, defense, Math.random());
    }

    public static int calculateDamageWithRoll(int attack, int defense, double roll) {
        return calculateDamage(attack, defense, roll);
    }

    static int calculateDamage(int attack, int defense, double roll) {
        if (attack < 0 || defense < 0) {
            throw new IllegalArgumentException("Ataque y defensa no pueden ser negativos");
        }
        if (roll < 0.0 || roll > 1.0) {
            throw new IllegalArgumentException("El random debe estar entre 0.0 y 1.0");
        }
        int rawDamage = (int) (attack * (roll * 2)) - defense;
        return Math.max(0, rawDamage);
    }
}
