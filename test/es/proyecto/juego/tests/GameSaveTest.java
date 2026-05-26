// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.tests;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.StubList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.persistencia.GameSave;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.ui.MockGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import org.junit.jupiter.api.Test;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.File;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.FileWriter;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.IOException;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertThrows;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class GameSaveTest {
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private final GameSave system = new GameSave();

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void testRoundTripPersistenciaEsIdentica() throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File tempFile = File.createTempFile("save_test", ".json");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        tempFile.deleteOnExit();

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IGameState estadoOriginal = new MockGameState(3, 1, 75, new StubList<String>());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertDoesNotThrow(() -> system.save(estadoOriginal, tempFile.getAbsolutePath()));

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameSave.SaveData datosCargados = system.load(tempFile.getAbsolutePath());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(75, datosCargados.vidaActual, "La vida cargada diverge de la original");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, datosCargados.fila, "La coordenada X ha mutado en la serializacion");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, datosCargados.col, "La coordenada Y ha mutado en la serializacion");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void loadAceptaFormatoConJugadorAnidadoYAliasColumna() throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File tempFile = File.createTempFile("save_alias", ".json");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        tempFile.deleteOnExit();

        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try (FileWriter writer = new FileWriter(tempFile)) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("{\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"version\": \"1.0\",\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"turnoActual\": 4,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"jugador\": {\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"vidaActual\": 61,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"habitacion\": 2,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"fila\": 5,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"columna\": 6\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  }\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("}\n");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameSave.SaveData data = system.load(tempFile.getAbsolutePath());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("1.0", data.version);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(4, data.turnoActual);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(61, data.vidaActual);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, data.habitacionActual);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(5, data.fila);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(6, data.col);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void loadRechazaContenidoQueNoEsObjetoJson() throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File tempFile = File.createTempFile("save_invalid", ".json");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        tempFile.deleteOnExit();

        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try (FileWriter writer = new FileWriter(tempFile)) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("[]");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IOException.class, () -> system.load(tempFile.getAbsolutePath()));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
