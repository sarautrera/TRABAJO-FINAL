/*
 * Resumen del fichero: Contiene pruebas automatizadas para comprobar el comportamiento de TurnManagerJUnitTest.
 */
package es.proyecto.juego.tests;

import es.proyecto.juego.logica.sistemas.TurnManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TurnManagerJUnitTest {
    @Test
    void playerCanMoveAndActOncePerTurn() {
        TurnManager manager = new TurnManager(3);

        assertTrue(manager.canMove());
        assertTrue(manager.canAct());

        manager.markMovementUsed();
        manager.markActionUsed();

        assertFalse(manager.canMove());
        assertFalse(manager.canAct());
    }

    @Test
    void endPlayerTurnResetsActionsAndCountsRound() {
        TurnManager manager = new TurnManager(3);

        manager.markMovementUsed();
        manager.markActionUsed();
        manager.endPlayerTurn();

        assertEquals(1, manager.getTurnCount());
        assertTrue(manager.canMove());
        assertTrue(manager.canAct());
    }

    @Test
    void actorCycleAdvancesInCircularOrder() {
        TurnManager manager = new TurnManager(5);
        manager.addActor("ENEMY_1");
        manager.addActor("ENEMY_2");

        assertEquals(TurnManager.PLAYER_ACTOR, manager.getCurrentActor());
        assertEquals("ENEMY_1", manager.advanceActor());
        assertEquals("ENEMY_2", manager.advanceActor());
        assertEquals(TurnManager.PLAYER_ACTOR, manager.advanceActor());
        assertEquals(1, manager.getTurnCount());
    }

    @Test
    void timeUpWhenTurnCountReachesMaxTurns() {
        TurnManager manager = new TurnManager(2);

        manager.endPlayerTurn();
        assertFalse(manager.isTimeUp());
        manager.endPlayerTurn();

        assertTrue(manager.isTimeUp());
        assertEquals(0, manager.getTurnsLeft());
    }

    @Test
    void invalidMaxTurnsIsRejected() {
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                new TurnManager(0);
            }
        });
    }

    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    }
}
