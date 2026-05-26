package es.proyecto.juego.tests;

import es.proyecto.juego.estructuras.MyLinkedList;
import es.proyecto.juego.logica.entidades.Player;
import es.proyecto.juego.logica.excepciones.InventoryFullException;
import es.proyecto.juego.logica.items.Armor;
import es.proyecto.juego.logica.items.Item;
import es.proyecto.juego.logica.items.Key;
import es.proyecto.juego.logica.items.Potion;
import es.proyecto.juego.logica.items.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerJUnitTest {
    @Test
    void takeDamageNeverLeavesNegativeHp() {
        Player player = createPlayer();

        player.takeDamage(999);

        assertEquals(0, player.getHp());
        assertFalse(player.isAlive());
    }

    @Test
    void healNeverExceedsMaxHp() {
        Player player = createPlayer();

        player.takeDamage(50);
        player.heal(999);

        assertEquals(player.getMaxHp(), player.getHp());
    }

    @Test
    void effectiveAttackAndDefenseUseEquipmentBonuses() {
        Player player = createPlayer();

        player.addItem(new Weapon("Espada", 5));
        player.addItem(new Armor("Armadura", 4));
        player.useInventoryItem(0);
        player.useInventoryItem(1);

        assertEquals(15, player.getEffectiveAttack());
        assertEquals(7, player.getEffectiveDefense());
    }

    @Test
    void potionHealsAndIsRemovedFromInventory() {
        Player player = createPlayer();
        Potion potion = new Potion("Pocion", 20);

        player.takeDamage(30);
        player.addItem(potion);
        player.useInventoryItem(0);

        assertEquals(90, player.getHp());
        assertEquals(0, player.getInventory().size());
        assertFalse(player.getInventory().contains(potion));
    }

    @Test
    void hasKeyForDoorFindsMatchingKeyOnly() {
        Player player = createPlayer();

        player.addItem(new Key("Llave norte", 2));

        assertTrue(player.hasKeyForDoor(2));
        assertFalse(player.hasKeyForDoor(1));
    }

    @Test
    void inventoryHasLimitInCurrentRules() {
        Player player = createPlayer();

        for (int i = 0; i < Player.MAX_INVENTORY_SIZE; i++) {
            player.addItem(new Potion("Pocion " + i, 1));
        }

        assertEquals(Player.MAX_INVENTORY_SIZE, player.getInventory().size());
        assertTrue(player.isInventoryFull());
        assertThrows(InventoryFullException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                player.addItem(new Potion("Pocion extra", 1));
            }
        });
    }

    @Test
    void constructorRejectsInvalidValues() {
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                new Player("", 100, 3, 10, 3, 0, 0, new MyLinkedList<Item>());
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                new Player("Heroe", 0, 3, 10, 3, 0, 0, new MyLinkedList<Item>());
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                new Player("Heroe", 100, 3, 10, 3, 0, 0, null);
            }
        });
        assertThrows(InventoryFullException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                MyLinkedList<Item> inventory = new MyLinkedList<>();
                for (int i = 0; i <= Player.MAX_INVENTORY_SIZE; i++) {
                    inventory.add(new Potion("Pocion inicial " + i, 1));
                }
                new Player("Heroe", 100, 3, 10, 3, 0, 0, inventory);
            }
        });
    }

    private Player createPlayer() {
        return new Player("Heroe", 100, 3, 10, 3, 0, 0, new MyLinkedList<Item>());
    }

    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    }
}
