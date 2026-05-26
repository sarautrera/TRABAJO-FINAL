package es.uah.eedd.listas.prueba.Personajes;

/**
 * Esta clase representa cualquier objeto del juego (Jugador, Enemigo, Ficha).
 * Implementa Comparable para que el BST pueda ordenarlas alfabéticamente.
 */
public class Personajes implements Comparable<Personajes> {
    private String id;
    private String name;
    private String type; // "Pieza Ajedrez", "Ficha Monopoly", etc.
    private int health;
    private int strength;

    public Personajes(String id, String name, String type, int health, int strength) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.health = health;
        this.strength = strength;
    }

    // Getters necesarios para el juego
    public String getName() { return name; }
    public String getId() { return id; }
    public int getHealth() { return health; }
    public int getStrength() { return strength; }

    // Setters (por si reciben daño o suben de nivel)
    public void setHealth(int health) { this.health = health; }

    @Override
    public int compareTo(Personajes other) {
        // Ordenamos por nombre para que el BST los ordene alfabeticamente
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "[" + type + "] " + name + " - Health: " + health + " Strength: " + strength;
    }
}