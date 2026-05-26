// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.tests;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Player;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.InventoryFullException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Armor;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Item;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Key;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Potion;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Weapon;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import org.junit.jupiter.api.Test;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertThrows;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class PlayerJUnitTest {
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void takeDamageNeverLeavesNegativeHp() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Player player = createPlayer();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.takeDamage(999);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, player.getHp());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(player.isAlive());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void healNeverExceedsMaxHp() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Player player = createPlayer();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.takeDamage(50);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.heal(999);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(player.getMaxHp(), player.getHp());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void effectiveAttackAndDefenseUseEquipmentBonuses() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Player player = createPlayer();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.addItem(new Weapon("Espada", 5));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.addItem(new Armor("Armadura", 4));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.useInventoryItem(0);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.useInventoryItem(1);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(15, player.getEffectiveAttack());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(7, player.getEffectiveDefense());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void potionHealsAndIsRemovedFromInventory() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Player player = createPlayer();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Potion potion = new Potion("Pocion", 20);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.takeDamage(30);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.addItem(potion);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.useInventoryItem(0);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(90, player.getHp());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, player.getInventory().size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(player.getInventory().contains(potion));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void hasKeyForDoorFindsMatchingKeyOnly() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Player player = createPlayer();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.addItem(new Key("Llave norte", 2));

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(player.hasKeyForDoor(2));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(player.hasKeyForDoor(1));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void inventoryHasLimitInCurrentRules() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Player player = createPlayer();

        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < Player.MAX_INVENTORY_SIZE; i++) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            player.addItem(new Potion("Pocion " + i, 1));
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(Player.MAX_INVENTORY_SIZE, player.getInventory().size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(player.isInventoryFull());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(InventoryFullException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                player.addItem(new Potion("Pocion extra", 1));
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void constructorRejectsInvalidValues() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new Player("", 100, 3, 10, 3, 0, 0, new MyLinkedList<Item>());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new Player("Heroe", 0, 3, 10, 3, 0, 0, new MyLinkedList<Item>());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new Player("Heroe", 100, 3, 10, 3, 0, 0, null);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(InventoryFullException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                MyLinkedList<Item> inventory = new MyLinkedList<>();
                // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
                for (int i = 0; i <= Player.MAX_INVENTORY_SIZE; i++) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    inventory.add(new Potion("Pocion inicial " + i, 1));
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new Player("Heroe", 100, 3, 10, 3, 0, 0, inventory);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private Player createPlayer() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return new Player("Heroe", 100, 3, 10, 3, 0, 0, new MyLinkedList<Item>());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
