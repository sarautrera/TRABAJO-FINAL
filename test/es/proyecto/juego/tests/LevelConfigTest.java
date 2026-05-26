/*
 * Resumen del fichero: Contiene pruebas automatizadas para comprobar el comportamiento de LevelConfigTest.
 */
package es.proyecto.juego.tests;

import es.proyecto.juego.persistencia.LevelConfig;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LevelConfigTest {
    private final LevelConfig reader = new LevelConfig();

    @Test
    public void testCargaFicheroInexistenteLanzaException() {
        assertThrows(IOException.class, () -> reader.load("ruta/ficticia/mapa_inexistente.json"));
    }

    @Test
    public void cargaLevelConfigExampleConHabitacionesCeldasConexionesYJugador() throws IOException {
        LevelConfig.GameConfig config = reader.load("src/main/resources/levelConfig.example.json");

        assertEquals("1.0", config.version);
        assertEquals("Dungeon de prueba Track B", config.titulo);
        assertEquals(50, config.turnosMaximos);
        assertEquals(0, config.habitacionInicial);
        assertEquals(1, config.habitacionSalida);
        assertEquals(2, config.habitaciones.size());
        assertEquals(1, config.conexiones.size());

        LevelConfig.HabitacionConfig entrada = config.habitaciones.get(0);
        assertEquals(0, entrada.id);
        assertEquals("Entrada", entrada.nombre);
        assertEquals(6, entrada.filas);
        assertEquals(7, entrada.columnas);
        assertTrue(entrada.visitadaInicialmente);
        assertEquals(3, entrada.celdas.size());

        LevelConfig.CeldaConfig puerta = entrada.celdas.get(0);
        assertEquals("DOOR", puerta.tipo);
        assertEquals(0, puerta.fila);
        assertEquals(3, puerta.columna);
        assertEquals(1, puerta.habitacionDestino);

        LevelConfig.CeldaConfig enemigo = entrada.celdas.get(1);
        assertEquals("ENEMY", enemigo.tipo);
        assertEquals("Enemigo basico", enemigo.enemigo.nombre);
        assertEquals(20, enemigo.enemigo.vidaActual);
        assertEquals(2, enemigo.enemigo.defensa);

        LevelConfig.CeldaConfig item = entrada.celdas.get(2);
        assertEquals("ITEM", item.tipo);
        assertEquals("Potion", item.item.tipo);
        assertEquals("Pocion", item.item.nombre);
        assertEquals(20, item.item.curacion);

        LevelConfig.ConexionConfig conexion = config.conexiones.get(0);
        assertEquals(0, conexion.de);
        assertEquals(1, conexion.a);
        assertEquals(1, conexion.peso);

        assertEquals("Heroe", config.jugadorInicial.nombre);
        assertEquals(5, config.jugadorInicial.fila);
        assertEquals(3, config.jugadorInicial.columna);
        assertEquals(100, config.jugadorInicial.vidaActual);
        assertEquals(0, config.jugadorInicial.inventario.size());
    }

    @Test
    public void normalizaTiposEnEspanolYLeeInventarioInicial() throws IOException {
        File file = File.createTempFile("level-es", ".json");
        file.deleteOnExit();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("{\n");
            writer.write("  \"habitaciones\": [\n");
            writer.write("    {\"id\": 0, \"nombre\": \"Sala\", \"filas\": 2, \"columnas\": 2,\n");
            writer.write("     \"celdas\": [\n");
            writer.write("       {\"fila\": 0, \"columna\": 0, \"tipo\": \"MURO\"},\n");
            writer.write("       {\"fila\": 0, \"columna\": 1, \"tipo\": \"TRAMPA\", \"dano\": 7},\n");
            writer.write("       {\"fila\": 1, \"columna\": 0, \"tipo\": \"PUERTA\", \"conectaCon\": 1, \"necesitaLlave\": true}\n");
            writer.write("     ]},\n");
            writer.write("    {\"id\": 1, \"nombre\": \"Salida\", \"filas\": 2, \"columnas\": 2, \"celdas\": []}\n");
            writer.write("  ],\n");
            writer.write("  \"jugadorInicial\": {\"nombre\": \"Heroe\", \"habitacion\": 0, \"fila\": 1, \"columna\": 1,\n");
            writer.write("    \"vida\": 90, \"velocidad\": 2, \"ataque\": 8, \"defensa\": 4,\n");
            writer.write("    \"inventario\": [{\"tipo\": \"Key\", \"nombre\": \"Llave\", \"puertaObjetivo\": 1}]}\n");
            writer.write("}\n");
        }

        LevelConfig.GameConfig config = reader.load(file.getAbsolutePath());

        assertEquals("WALL", config.habitaciones.get(0).celdas.get(0).tipo);
        assertEquals("TRAP", config.habitaciones.get(0).celdas.get(1).tipo);
        assertEquals(7, config.habitaciones.get(0).celdas.get(1).danoTrampa);
        assertEquals("DOOR", config.habitaciones.get(0).celdas.get(2).tipo);
        assertTrue(config.habitaciones.get(0).celdas.get(2).bloqueada);
        assertEquals(1, config.jugadorInicial.inventario.size());
        assertEquals("Key", config.jugadorInicial.inventario.get(0).tipo);
        assertEquals(1, config.jugadorInicial.inventario.get(0).puertaObjetivo);
    }

    @Test
    public void rechazaJsonSinJugadorInicial() throws IOException {
        File file = File.createTempFile("level-invalid", ".json");
        file.deleteOnExit();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("{\"habitaciones\": [{\"id\": 0, \"nombre\": \"Sala\", \"filas\": 2, \"columnas\": 2}]}");
        }

        assertThrows(IllegalStateException.class, () -> reader.load(file.getAbsolutePath()));
    }
}
