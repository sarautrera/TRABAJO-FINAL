package es.proyecto.juego.ui;

import static org.junit.jupiter.api.Assertions.*;
import es.proyecto.juego.logica.IGameState;
import org.junit.jupiter.api.Test;

public class MockGameEngineTest {

    @Test
    public void testEstadoInicialDelMockEngine() {
        MockGameEngine engine = new MockGameEngine();
        IGameState state = engine.getState();

        assertNotNull(state, "El motor no ha instanciado un paquete de datos válido");
        assertEquals(4, state.getPlayerRow(), "La fila de inicio del Mock debe ser 4");
        assertEquals(2, state.getPlayerCol(), "La columna de inicio del Mock debe ser 2");
    }

    @Test
    public void testMovimientoFueraDeLimitesDevuelveFalseSinRomper() {
        MockGameEngine engine = new MockGameEngine();

        // Coordenadas absurdas negativas o desbordadas
        boolean resultNegativo = engine.movePlayer(-1, 2);
        boolean resultExcesivo = engine.movePlayer(10, 5);

        assertFalse(resultNegativo, "El motor permitió teletransporte negativo fuera de la matriz");
        assertFalse(resultExcesivo, "El motor no validó los límites máximos de la habitación (5x5)");

        // Verificar que tras los fallos, el jugador sigue a salvo en su sitio original
        assertEquals(4, engine.getState().getPlayerRow());
    }
}