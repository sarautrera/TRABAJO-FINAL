// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica.items;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Player;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class Armor extends Item {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int defenseBonus;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Armor(String name, int defenseBonus) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        super(name, false, -1);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (defenseBonus < 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El bonus de defensa no puede ser negativo");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.defenseBonus = defenseBonus;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getDefenseBonus() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return defenseBonus;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isEquippable() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void applyEffect(Player player) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.equipArmor(this);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
