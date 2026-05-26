/*
 * Resumen del fichero: Lee y escribe el estado guardado de una partida en formato JSON.
 */
package es.proyecto.juego.persistencia;

import es.proyecto.juego.logica.IGameState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class GameSave {

    public void save(IGameState state, String path) throws IOException {
        if (state == null) {
            throw new IllegalArgumentException("El estado no puede ser null");
        }

        SaveData data = SaveData.fromState(state);
        try (Writer writer = new FileWriter(path)) {
            writer.write("{\n");
            writer.write("  \"version\": \"" + escape(data.version) + "\",\n");
            writer.write("  \"turnoActual\": " + data.turnoActual + ",\n");
            writer.write("  \"jugador\": {\n");
            writer.write("    \"vidaActual\": " + data.vidaActual + ",\n");
            writer.write("    \"habitacionActual\": " + data.habitacionActual + ",\n");
            writer.write("    \"fila\": " + data.fila + ",\n");
            writer.write("    \"col\": " + data.col + "\n");
            writer.write("  }\n");
            writer.write("}\n");
        }
    }

    public SaveData load(String path) throws IOException {
        String json = readTextFile(path);
        if (!json.trim().startsWith("{")) {
            throw new IOException("Partida guardada corrupta: no es un objeto JSON");
        }

        String playerJson = LevelConfig.Json.readObject(json, "jugador", false);
        if (playerJson.length() == 0) {
            playerJson = json;
        }

        SaveData data = new SaveData();
        data.version = LevelConfig.Json.readString(json, "version", data.version);
        data.turnoActual = LevelConfig.Json.readInt(json, "turnoActual", 0);
        data.vidaActual = LevelConfig.Json.readInt(playerJson, "vidaActual", 100);
        data.habitacionActual = LevelConfig.Json.readInt(playerJson, "habitacionActual",
                LevelConfig.Json.readInt(playerJson, "habitacion", 0));
        data.fila = LevelConfig.Json.readInt(playerJson, "fila", 0);
        data.col = LevelConfig.Json.readInt(playerJson, "col",
                LevelConfig.Json.readInt(playerJson, "columna", 0));
        return data;
    }

    private String readTextFile(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append('\n');
                line = reader.readLine();
            }
        }
        return builder.toString();
    }

    private static String escape(String text) {
        StringBuilder escaped = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);
            if (current == '"' || current == '\\') {
                escaped.append('\\');
            }
            escaped.append(current);
        }
        return escaped.toString();
    }

    public static class SaveData {
        public String version = "1.0";
        public int turnoActual;
        public int vidaActual;
        public int habitacionActual;
        public int fila;
        public int col;

        public static SaveData fromState(IGameState state) {
            SaveData data = new SaveData();
            data.turnoActual = state.getTurnCount();
            data.vidaActual = state.getPlayerHp();
            data.habitacionActual = state.getCurrentRoomId();
            data.fila = state.getPlayerRow();
            data.col = state.getPlayerCol();
            return data;
        }
    }
}
