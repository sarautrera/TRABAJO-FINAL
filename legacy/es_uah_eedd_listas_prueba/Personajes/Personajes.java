// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.Personajes;

/**
 * Esta clase representa cualquier objeto del juego (Jugador, Enemigo, Ficha).
 * Implementa Comparable para que el BST pueda ordenarlas alfabÃ©ticamente.
 */
// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class Personajes implements Comparable<Personajes> {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private String id;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private String name;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private String type; // "Pieza Ajedrez", "Ficha Monopoly", etc.
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int health;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int strength;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Personajes(String id, String name, String type, int health, int strength) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.id = id;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.name = name;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.type = type;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.health = health;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.strength = strength;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Getters necesarios para el juego
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public String getName() { return name; }
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public String getId() { return id; }
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getHealth() { return health; }
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getStrength() { return strength; }

    // Setters (por si reciben daÃ±o o suben de nivel)
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void setHealth(int health) { this.health = health; }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int compareTo(Personajes other) {
        // Ordenamos por nombre para que el BST los ordene alfabeticamente
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return this.name.compareTo(other.name);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public String toString() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return "[" + type + "] " + name + " - Health: " + health + " Strength: " + strength;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
