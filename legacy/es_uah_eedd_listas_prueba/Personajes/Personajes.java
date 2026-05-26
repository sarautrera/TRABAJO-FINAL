/*
 * Resumen del fichero: Conserva una implementacion antigua de Personajes usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.Personajes;

public class Personajes implements Comparable<Personajes> {
    private String id;
    private String name;
    private String type;
    private int health;
    private int strength;

    public Personajes(String id, String name, String type, int health, int strength) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.health = health;
        this.strength = strength;
    }

    public String getName() { return name; }
    public String getId() { return id; }
    public int getHealth() { return health; }
    public int getStrength() { return strength; }

    public void setHealth(int health) { this.health = health; }

    @Override
    public int compareTo(Personajes other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "[" + type + "] " + name + " - Health: " + health + " Strength: " + strength;
    }
}
