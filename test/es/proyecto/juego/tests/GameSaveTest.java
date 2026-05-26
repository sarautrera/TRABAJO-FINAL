/*
 * Resumen del fichero: Contiene pruebas automatizadas para comprobar el comportamiento de GameSaveTest.
 */
package es.proyecto.juego.tests;

import es.proyecto.juego.estructuras.StubList;
import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.persistencia.GameSave;
import es.proyecto.juego.ui.MockGameState;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameSaveTest {
    private final GameSave system = new GameSave();

    @Test
    public void testRoundTripPersistenciaEsIdentica() throws IOException {
        File tempFile = File.createTempFile("save_test", ".json");
        tempFile.deleteOnExit();

        IGameState estadoOriginal = new MockGameState(3, 1, 75, new StubList<String>());

        assertDoesNotThrow(() -> system.save(estadoOriginal, tempFile.getAbsolutePath()));

        GameSave.SaveData datosCargados = system.load(tempFile.getAbsolutePath());

        assertEquals(75, datosCargados.vidaActual, "La vida cargada diverge de la original");
        assertEquals(3, datosCargados.fila, "La coordenada X ha mutado en la serializacion");
        assertEquals(1, datosCargados.col, "La coordenada Y ha mutado en la serializacion");
    }

    @Test
    public void loadAceptaFormatoConJugadorAnidadoYAliasColumna() throws IOException {
        File tempFile = File.createTempFile("save_alias", ".json");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("{\n");
            writer.write("  \"version\": \"1.0\",\n");
            writer.write("  \"turnoActual\": 4,\n");
            writer.write("  \"jugador\": {\n");
            writer.write("    \"vidaActual\": 61,\n");
            writer.write("    \"habitacion\": 2,\n");
            writer.write("    \"fila\": 5,\n");
            writer.write("    \"columna\": 6\n");
            writer.write("  }\n");
            writer.write("}\n");
        }

        GameSave.SaveData data = system.load(tempFile.getAbsolutePath());

        assertEquals("1.0", data.version);
        assertEquals(4, data.turnoActual);
        assertEquals(61, data.vidaActual);
        assertEquals(2, data.habitacionActual);
        assertEquals(5, data.fila);
        assertEquals(6, data.col);
    }

    @Test
    public void loadRechazaContenidoQueNoEsObjetoJson() throws IOException {
        File tempFile = File.createTempFile("save_invalid", ".json");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("[]");
        }

        assertThrows(IOException.class, () -> system.load(tempFile.getAbsolutePath()));
    }
}
