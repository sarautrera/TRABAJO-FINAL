// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica.sistemas;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public final class CombatSystem {
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private CombatSystem() {
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public static int calculateDamage(int attack, int defense) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return calculateDamage(attack, defense, Math.random());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public static int calculateDamageWithRoll(int attack, int defense, double roll) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return calculateDamage(attack, defense, roll);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    static int calculateDamage(int attack, int defense, double roll) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (attack < 0 || defense < 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("Ataque y defensa no pueden ser negativos");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (roll < 0.0 || roll > 1.0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El random debe estar entre 0.0 y 1.0");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int rawDamage = (int) (attack * (roll * 2)) - defense;
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return Math.max(0, rawDamage);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
