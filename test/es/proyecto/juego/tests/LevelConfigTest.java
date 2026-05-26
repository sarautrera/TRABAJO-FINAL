package es.proyecto.juego.persistencia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class LevelConfigTest {
    private final LevelConfig reader = new LevelConfig();

    @Test
    public void testCargaFicheroInexistenteLanzaException() {
        // Debe capturarse la excepción controlada de entrada/salida
        assertThrows(IOException.class, () -> {
            reader.load("ruta/ficticia/mapa_inexistente.json");
        });
    }
}