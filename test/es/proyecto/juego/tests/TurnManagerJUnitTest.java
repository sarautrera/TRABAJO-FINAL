// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.tests;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.sistemas.TurnManager;
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
public class TurnManagerJUnitTest {
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void playerCanMoveAndActOncePerTurn() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        TurnManager manager = new TurnManager(3);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(manager.canMove());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(manager.canAct());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        manager.markMovementUsed();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        manager.markActionUsed();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(manager.canMove());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(manager.canAct());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void endPlayerTurnResetsActionsAndCountsRound() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        TurnManager manager = new TurnManager(3);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        manager.markMovementUsed();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        manager.markActionUsed();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        manager.endPlayerTurn();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, manager.getTurnCount());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(manager.canMove());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(manager.canAct());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void actorCycleAdvancesInCircularOrder() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        TurnManager manager = new TurnManager(5);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        manager.addActor("ENEMY_1");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        manager.addActor("ENEMY_2");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(TurnManager.PLAYER_ACTOR, manager.getCurrentActor());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("ENEMY_1", manager.advanceActor());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("ENEMY_2", manager.advanceActor());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(TurnManager.PLAYER_ACTOR, manager.advanceActor());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, manager.getTurnCount());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void timeUpWhenTurnCountReachesMaxTurns() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        TurnManager manager = new TurnManager(2);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        manager.endPlayerTurn();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(manager.isTimeUp());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        manager.endPlayerTurn();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(manager.isTimeUp());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, manager.getTurnsLeft());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void invalidMaxTurnsIsRejected() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                new TurnManager(0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
