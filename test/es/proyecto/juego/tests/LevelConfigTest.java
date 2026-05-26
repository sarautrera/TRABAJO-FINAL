// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.tests;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.persistencia.LevelConfig;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import org.junit.jupiter.api.Test;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.File;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.FileWriter;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.IOException;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertThrows;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class LevelConfigTest {
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private final LevelConfig reader = new LevelConfig();

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void testCargaFicheroInexistenteLanzaException() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IOException.class, () -> reader.load("ruta/ficticia/mapa_inexistente.json"));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void cargaLevelConfigExampleConHabitacionesCeldasConexionesYJugador() throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        LevelConfig.GameConfig config = reader.load("src/main/resources/levelConfig.example.json");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("1.0", config.version);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("Dungeon de prueba Track B", config.titulo);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(50, config.turnosMaximos);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, config.habitacionInicial);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, config.habitacionSalida);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, config.habitaciones.size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, config.conexiones.size());

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        LevelConfig.HabitacionConfig entrada = config.habitaciones.get(0);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, entrada.id);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("Entrada", entrada.nombre);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(6, entrada.filas);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(7, entrada.columnas);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(entrada.visitadaInicialmente);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, entrada.celdas.size());

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        LevelConfig.CeldaConfig puerta = entrada.celdas.get(0);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("DOOR", puerta.tipo);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, puerta.fila);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, puerta.columna);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, puerta.habitacionDestino);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        LevelConfig.CeldaConfig enemigo = entrada.celdas.get(1);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("ENEMY", enemigo.tipo);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("Enemigo basico", enemigo.enemigo.nombre);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(20, enemigo.enemigo.vidaActual);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, enemigo.enemigo.defensa);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        LevelConfig.CeldaConfig item = entrada.celdas.get(2);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("ITEM", item.tipo);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("Potion", item.item.tipo);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("Pocion", item.item.nombre);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(20, item.item.curacion);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        LevelConfig.ConexionConfig conexion = config.conexiones.get(0);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, conexion.de);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, conexion.a);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, conexion.peso);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("Heroe", config.jugadorInicial.nombre);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(5, config.jugadorInicial.fila);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, config.jugadorInicial.columna);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(100, config.jugadorInicial.vidaActual);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, config.jugadorInicial.inventario.size());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void normalizaTiposEnEspanolYLeeInventarioInicial() throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File file = File.createTempFile("level-es", ".json");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        file.deleteOnExit();

        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try (FileWriter writer = new FileWriter(file)) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("{\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"habitaciones\": [\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    {\"id\": 0, \"nombre\": \"Sala\", \"filas\": 2, \"columnas\": 2,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("     \"celdas\": [\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("       {\"fila\": 0, \"columna\": 0, \"tipo\": \"MURO\"},\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("       {\"fila\": 0, \"columna\": 1, \"tipo\": \"TRAMPA\", \"dano\": 7},\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("       {\"fila\": 1, \"columna\": 0, \"tipo\": \"PUERTA\", \"conectaCon\": 1, \"necesitaLlave\": true}\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("     ]},\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    {\"id\": 1, \"nombre\": \"Salida\", \"filas\": 2, \"columnas\": 2, \"celdas\": []}\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  ],\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"jugadorInicial\": {\"nombre\": \"Heroe\", \"habitacion\": 0, \"fila\": 1, \"columna\": 1,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"vida\": 90, \"velocidad\": 2, \"ataque\": 8, \"defensa\": 4,\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"inventario\": [{\"tipo\": \"Key\", \"nombre\": \"Llave\", \"puertaObjetivo\": 1}]}\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("}\n");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        LevelConfig.GameConfig config = reader.load(file.getAbsolutePath());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("WALL", config.habitaciones.get(0).celdas.get(0).tipo);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("TRAP", config.habitaciones.get(0).celdas.get(1).tipo);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(7, config.habitaciones.get(0).celdas.get(1).danoTrampa);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("DOOR", config.habitaciones.get(0).celdas.get(2).tipo);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(config.habitaciones.get(0).celdas.get(2).bloqueada);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, config.jugadorInicial.inventario.size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("Key", config.jugadorInicial.inventario.get(0).tipo);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, config.jugadorInicial.inventario.get(0).puertaObjetivo);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void rechazaJsonSinJugadorInicial() throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File file = File.createTempFile("level-invalid", ".json");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        file.deleteOnExit();

        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try (FileWriter writer = new FileWriter(file)) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("{\"habitaciones\": [{\"id\": 0, \"nombre\": \"Sala\", \"filas\": 2, \"columnas\": 2}]}");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IllegalStateException.class, () -> reader.load(file.getAbsolutePath()));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
