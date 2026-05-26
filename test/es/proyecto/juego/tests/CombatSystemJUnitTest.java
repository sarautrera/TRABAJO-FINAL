// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.tests;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Enemy;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.entidades.Player;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Item;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.sistemas.CombatSystem;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import org.junit.jupiter.api.Test;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertThrows;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class CombatSystemJUnitTest {
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void calculateDamageNeverReturnsNegativeDamage() {
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < 1000; i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            assertTrue(CombatSystem.calculateDamage(5, 100) >= 0);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void calculateDamageUsesRequiredFormula() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, CombatSystem.calculateDamageWithRoll(10, 0, 0.0));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(5, CombatSystem.calculateDamageWithRoll(10, 5, 0.5));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(20, CombatSystem.calculateDamageWithRoll(10, 0, 1.0));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void calculateDamageRejectsInvalidInput() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                CombatSystem.calculateDamageWithRoll(-1, 0, 0.5);
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
                CombatSystem.calculateDamageWithRoll(1, 0, 1.5);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void defenderHpNeverBecomesNegative() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Enemy enemy = new Enemy("Enemigo", 10, 1, 3, 0, 0, 0);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Player player = new Player("Heroe", 100, 3, 10, 3, 0, 1, new MyLinkedList<Item>());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        enemy.takeDamage(999);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        player.takeDamage(999);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, enemy.getHp());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, player.getHp());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
