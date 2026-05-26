// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.ui;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.*;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import org.junit.jupiter.api.Test;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class MockGameEngineTest {

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void testEstadoInicialDelMockEngine() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MockGameEngine engine = new MockGameEngine();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IGameState state = engine.getState();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertNotNull(state, "El motor no ha instanciado un paquete de datos vÃ¡lido");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(4, state.getPlayerRow(), "La fila de inicio del Mock debe ser 4");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, state.getPlayerCol(), "La columna de inicio del Mock debe ser 2");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void testMovimientoFueraDeLimitesDevuelveFalseSinRomper() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MockGameEngine engine = new MockGameEngine();

        // Coordenadas absurdas negativas o desbordadas
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        boolean resultNegativo = engine.movePlayer(-1, 2);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        boolean resultExcesivo = engine.movePlayer(10, 5);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(resultNegativo, "El motor permitiÃ³ teletransporte negativo fuera de la matriz");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(resultExcesivo, "El motor no validÃ³ los lÃ­mites mÃ¡ximos de la habitaciÃ³n (5x5)");

        // Verificar que tras los fallos, el jugador sigue a salvo en su sitio original
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(4, engine.getState().getPlayerRow());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
