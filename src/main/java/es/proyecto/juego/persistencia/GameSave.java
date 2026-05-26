// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.persistencia;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.BufferedReader;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.FileReader;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.FileWriter;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.IOException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.Writer;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class GameSave {

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void save(IGameState state, String path) throws IOException {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (state == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El estado no puede ser null");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        SaveData data = SaveData.fromState(state);
        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try (Writer writer = new FileWriter(path)) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("{\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"version\": \"" + escape(data.version) + "\",\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"turnoActual\": " + data.turnoActual + ",\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  \"jugador\": {\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"vidaActual\": " + data.vidaActual + ",\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"habitacionActual\": " + data.habitacionActual + ",\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"fila\": " + data.fila + ",\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("    \"col\": " + data.col + "\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("  }\n");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            writer.write("}\n");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public SaveData load(String path) throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String json = readTextFile(path);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!json.trim().startsWith("{")) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IOException("Partida guardada corrupta: no es un objeto JSON");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String playerJson = LevelConfig.Json.readObject(json, "jugador", false);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (playerJson.length() == 0) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            playerJson = json;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        SaveData data = new SaveData();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        data.version = LevelConfig.Json.readString(json, "version", data.version);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        data.turnoActual = LevelConfig.Json.readInt(json, "turnoActual", 0);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        data.vidaActual = LevelConfig.Json.readInt(playerJson, "vidaActual", 100);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        data.habitacionActual = LevelConfig.Json.readInt(playerJson, "habitacionActual",
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                LevelConfig.Json.readInt(playerJson, "habitacion", 0));
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        data.fila = LevelConfig.Json.readInt(playerJson, "fila", 0);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        data.col = LevelConfig.Json.readInt(playerJson, "col",
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                LevelConfig.Json.readInt(playerJson, "columna", 0));
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return data;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private String readTextFile(String path) throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StringBuilder builder = new StringBuilder();
        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            String line = reader.readLine();
            // Comentario de estudiante: aqui empieza un bucle while.
            while (line != null) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                builder.append(line).append('\n');
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                line = reader.readLine();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return builder.toString();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private static String escape(String text) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StringBuilder escaped = new StringBuilder();
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < text.length(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            char current = text.charAt(i);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (current == '"' || current == '\\') {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                escaped.append('\\');
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            escaped.append(current);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return escaped.toString();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    public static class SaveData {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public String version = "1.0";
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int turnoActual;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int vidaActual;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int habitacionActual;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int fila;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int col;

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public static SaveData fromState(IGameState state) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            SaveData data = new SaveData();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            data.turnoActual = state.getTurnCount();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            data.vidaActual = state.getPlayerHp();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            data.habitacionActual = state.getCurrentRoomId();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            data.fila = state.getPlayerRow();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            data.col = state.getPlayerCol();
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return data;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
