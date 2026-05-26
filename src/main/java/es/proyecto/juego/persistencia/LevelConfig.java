package es.proyecto.juego.persistencia;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.StubList; // Corregido: añadido punto y coma
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Clase encargada de cargar y validar la configuración inicial de los niveles del juego.
 * Lee un archivo JSON que define cómo son las habitaciones, el título del juego, etc.
 */
public class LevelConfig {
    private final Gson gson = new Gson();

    /**
     * Carga el archivo de configuración del juego desde una ruta específica.
     * @param path Ruta del archivo JSON de configuración.
     * @return Un objeto GameConfig con todos los datos cargados.
     * @throws IOException Si el archivo no existe o el JSON está malformado.
     */
    public GameConfig load(String path) throws IOException {
        // Abre el lector de archivos garantizando su cierre automático con try-with-resources
        try (Reader reader = new FileReader(path)) {
            GameConfig config = gson.fromJson(reader, GameConfig.class);

            // Pasamos el control de calidad y traspasamos datos al IList
            validate(config);
            return config;
        } catch (JsonParseException e) {
            throw new IOException("JSON de configuración malformado: " + e.getMessage(), e);
        }
    }

    /**
     * Método privado de control de calidad. Valida y traspasa el array nativo al IList propio.
     */
    private void validate(GameConfig c) {
        if (c == null) {
            throw new IllegalStateException("El archivo JSON está vacío o es inválido.");
        }

        if (c.habitacionesJson == null || c.habitacionesJson.length == 0) {
            throw new IllegalStateException("El juego necesita al menos una habitación.");
        }

        // CORRECCIÓN CLAVE: Instanciamos StubList en lugar de MyLinkedList
        c.habitaciones = new StubList<>();
        for (int i = 0; i < c.habitacionesJson.length; i++) {
            c.habitaciones.add(c.habitacionesJson[i]);
        }
    }

    // --- DTO (Data Transfer Object) para la Configuración General ---
    public static class GameConfig {
        public String version;
        public String titulo;
        public int turnosMaximos;

        @com.google.gson.annotations.SerializedName("habitaciones")
        protected HabitacionConfig[] habitacionesJson;

        public IList<HabitacionConfig> habitaciones;
    }

    // --- DTO Secundario para estructurar cada habitación ---
    public static class HabitacionConfig {
        public int id;
        public String nombre;
    }
}