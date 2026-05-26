// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica.items;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Player;

// Comentario de estudiante: aqui se prepara una instruccion del programa.
public abstract class Item {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final String name;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final boolean consumable;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int usesLeft;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    protected Item(String name, boolean consumable, int usesLeft) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (name == null || name.length() == 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El nombre del item no puede estar vacio");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.name = name;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.consumable = consumable;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.usesLeft = usesLeft;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public String getName() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return name;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isConsumable() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return consumable;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getUsesLeft() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return usesLeft;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isEquippable() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return false;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean canUse() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return usesLeft != 0;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isDepleted() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return consumable && usesLeft == 0;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void consumeUse() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (usesLeft > 0) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            usesLeft--;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public abstract void applyEffect(Player player);
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
