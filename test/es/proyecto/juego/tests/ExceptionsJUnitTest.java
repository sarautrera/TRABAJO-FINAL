/*
 * Resumen del fichero: Contiene pruebas automatizadas para comprobar el comportamiento de ExceptionsJUnitTest.
 */
package es.proyecto.juego.tests;

import es.proyecto.juego.logica.excepciones.DoorLockedException;
import es.proyecto.juego.logica.excepciones.GameAlreadyOverException;
import es.proyecto.juego.logica.excepciones.InvalidAttackException;
import es.proyecto.juego.logica.excepciones.InvalidMoveException;
import es.proyecto.juego.logica.excepciones.InventoryFullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ExceptionsJUnitTest {
    @Test
    void invalidMoveExceptionKeepsMessage() {
        InvalidMoveException exception = new InvalidMoveException("movimiento invalido");

        assertEquals("movimiento invalido", exception.getMessage());
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void invalidAttackExceptionKeepsMessage() {
        InvalidAttackException exception = new InvalidAttackException("ataque invalido");

        assertEquals("ataque invalido", exception.getMessage());
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void doorLockedExceptionKeepsMessage() {
        DoorLockedException exception = new DoorLockedException("puerta bloqueada");

        assertEquals("puerta bloqueada", exception.getMessage());
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void gameAlreadyOverExceptionKeepsMessage() {
        GameAlreadyOverException exception = new GameAlreadyOverException("partida terminada");

        assertEquals("partida terminada", exception.getMessage());
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void inventoryFullExceptionKeepsMessage() {
        InventoryFullException exception = new InventoryFullException("inventario lleno");

        assertEquals("inventario lleno", exception.getMessage());
        assertInstanceOf(RuntimeException.class, exception);
    }
}
