// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica.items;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Player;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class Potion extends Item {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int hpRestore;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Potion(String name, int hpRestore) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        super(name, true, 1);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (hpRestore <= 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("La curacion de una pocion debe ser positiva");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.hpRestore = hpRestore;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getHpRestore() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return hpRestore;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void applyEffect(Player player) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!canUse()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("La pocion no tiene usos disponibles");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.heal(hpRestore);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        consumeUse();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
