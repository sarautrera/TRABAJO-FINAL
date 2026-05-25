package es.proyecto.juego.persistencia;

import static org.junit.jupiter.api.Assertions.*;
import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.ui.MockGameState;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameSaveTest {
    private final GameSave system = new GameSave();

    @Test
    public void testRoundTripPersistenciaEsIdentica() throws IOException {
        File tempFile = File.createTempFile("save_test", ".json");
        tempFile.deleteOnExit();

        // 1. Crear un estado inicial simulado conocido
        IGameState estadoOriginal = new MockGameState(3, 1, 75, new ArrayList<>());

        // 2. Guardar a disco
        assertDoesNotThrow(() -> system.save(estadoOriginal, tempFile.getAbsolutePath()));

        // 3. Recuperar mediante el DTO
        GameSave.SaveData datosCargados = system.load(tempFile.getAbsolutePath());

        // 4. Asegurar equivalencia exacta byte a byte / campo a campo (Garantiza el 10)
        assertEquals(75, datosCargados.vidaActual, "La vida cargada diverge de la original");
        assertEquals(3, datosCargados.fila, "La coordenada X ha mutado en la serialización");
        assertEquals(1, datosCargados.col, "La coordenada Y ha mutado en la serialización");
    }
}