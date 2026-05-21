package es.proyecto.juego.tests;

import es.proyecto.juego.logica.entidades.Enemy;
import es.proyecto.juego.logica.entidades.Player;
import es.proyecto.juego.estructuras.MyLinkedList;
import es.proyecto.juego.logica.items.Item;
import es.proyecto.juego.logica.sistemas.CombatSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CombatSystemJUnitTest {
    @Test
    void calculateDamageNeverReturnsNegativeDamage() {
        for (int i = 0; i < 1000; i++) {
            assertTrue(CombatSystem.calculateDamage(5, 100) >= 0);
        }
    }

    @Test
    void calculateDamageUsesRequiredFormula() {
        assertEquals(0, CombatSystem.calculateDamageWithRoll(10, 0, 0.0));
        assertEquals(5, CombatSystem.calculateDamageWithRoll(10, 5, 0.5));
        assertEquals(20, CombatSystem.calculateDamageWithRoll(10, 0, 1.0));
    }

    @Test
    void calculateDamageRejectsInvalidInput() {
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                CombatSystem.calculateDamageWithRoll(-1, 0, 0.5);
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                CombatSystem.calculateDamageWithRoll(1, 0, 1.5);
            }
        });
    }

    @Test
    void defenderHpNeverBecomesNegative() {
        Enemy enemy = new Enemy("Enemigo", 10, 1, 3, 0, 0, 0);
        Player player = new Player("Heroe", 100, 3, 10, 3, 0, 1, new MyLinkedList<Item>());

        enemy.takeDamage(999);
        player.takeDamage(999);

        assertEquals(0, enemy.getHp());
        assertEquals(0, player.getHp());
    }

    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    }
}
