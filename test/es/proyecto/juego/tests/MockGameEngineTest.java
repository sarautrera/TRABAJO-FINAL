/*
 * Resumen del fichero: Contiene pruebas automatizadas para comprobar el comportamiento de MockGameEngineTest.
 */
package es.proyecto.juego.ui;

import static org.junit.jupiter.api.Assertions.*;
import es.proyecto.juego.logica.IGameState;
import org.junit.jupiter.api.Test;

public class MockGameEngineTest {

    @Test
    public void testEstadoInicialDelMockEngine() {
        MockGameEngine engine = new MockGameEngine();
        IGameState state = engine.getState();

        assertNotNull(state, "El motor no ha instanciado un paquete de datos vÃ¡lido");
        assertEquals(4, state.getPlayerRow(), "La fila de inicio del Mock debe ser 4");
        assertEquals(2, state.getPlayerCol(), "La columna de inicio del Mock debe ser 2");
    }

    @Test
    public void testMovimientoFueraDeLimitesDevuelveFalseSinRomper() {
        MockGameEngine engine = new MockGameEngine();

        boolean resultNegativo = engine.movePlayer(-1, 2);
        boolean resultExcesivo = engine.movePlayer(10, 5);

        assertFalse(resultNegativo, "El motor permitiÃ³ teletransporte negativo fuera de la matriz");
        assertFalse(resultExcesivo, "El motor no validÃ³ los lÃ­mites mÃ¡ximos de la habitaciÃ³n (5x5)");

        assertEquals(4, engine.getState().getPlayerRow());
    }
}
