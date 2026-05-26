/*
 * Resumen del fichero: Carga la configuracion inicial de niveles, habitaciones, jugador e items desde JSON.
 */
package es.proyecto.juego.persistencia;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.StubList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LevelConfig {

    public GameConfig load(String path) throws IOException {
        String json = readTextFile(path);
        if (!json.trim().startsWith("{")) {
            throw new IOException("JSON de configuracion malformado");
        }

        GameConfig config = new GameConfig();
        config.version = Json.readString(json, "version", "");
        config.titulo = Json.readString(json, "titulo", "");
        config.turnosMaximos = Json.readInt(json, "turnosMaximos", 50);
        config.habitacionInicial = Json.readInt(json, "habitacionInicial", 0);
        config.habitacionSalida = Json.readInt(json, "habitacionSalida", 1);
        config.habitaciones = parseHabitaciones(json);
        config.conexiones = parseConexiones(json);
        config.jugadorInicial = parseJugadorInicial(json);
        validate(config);
        return config;
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

    private void validate(GameConfig config) {
        if (config.habitaciones == null || config.habitaciones.isEmpty()) {
            throw new IllegalStateException("El juego necesita al menos una habitacion.");
        }
        if (config.jugadorInicial == null) {
            throw new IllegalStateException("Falta jugadorInicial en la configuracion.");
        }
    }

    private IList<HabitacionConfig> parseHabitaciones(String json) throws IOException {
        StubList<HabitacionConfig> habitaciones = new StubList<>();
        String arrayJson = Json.readArray(json, "habitaciones", false);
        if (arrayJson.length() == 0) {
            return habitaciones;
        }

        StubList<String> objects = Json.splitObjects(arrayJson);
        for (int i = 0; i < objects.size(); i++) {
            String roomJson = objects.get(i);
            HabitacionConfig habitacion = new HabitacionConfig();
            habitacion.id = Json.readInt(roomJson, "id", 0);
            habitacion.nombre = Json.readString(roomJson, "nombre", "Habitacion " + habitacion.id);
            habitacion.filas = Json.readInt(roomJson, "filas", 5);
            habitacion.columnas = Json.readInt(roomJson, "columnas", Json.readInt(roomJson, "cols", 5));
            habitacion.visitadaInicialmente = Json.readBoolean(roomJson, "visitadaInicialmente", false);
            habitacion.celdas = parseCeldas(roomJson);
            habitaciones.add(habitacion);
        }
        return habitaciones;
    }

    private IList<CeldaConfig> parseCeldas(String roomJson) throws IOException {
        StubList<CeldaConfig> celdas = new StubList<>();
        String arrayJson = Json.readArray(roomJson, "celdas", false);
        if (arrayJson.length() == 0) {
            return celdas;
        }

        StubList<String> objects = Json.splitObjects(arrayJson);
        for (int i = 0; i < objects.size(); i++) {
            String cellJson = objects.get(i);
            CeldaConfig celda = new CeldaConfig();
            celda.fila = Json.readInt(cellJson, "fila", 0);
            celda.columna = Json.readInt(cellJson, "columna", Json.readInt(cellJson, "col", 0));
            celda.tipo = normalizeType(Json.readString(cellJson, "tipo", "EMPTY"));

            // Cada tipo de celda necesita datos distintos, por eso se completa por ramas separadas.
            if ("DOOR".equals(celda.tipo)) {
                celda.habitacionDestino = Json.readInt(cellJson, "habitacionDestino",
                        Json.readInt(cellJson, "conectaCon", -1));
                celda.bloqueada = Json.readBoolean(cellJson, "bloqueada",
                        Json.readBoolean(cellJson, "necesitaLlave", false));
                celda.salidaExterior = Json.readBoolean(cellJson, "salidaExterior",
                        Json.readBoolean(cellJson, "esSalida", false));
                celda.abierta = Json.readBoolean(cellJson, "abierta", false);
            } else if ("ENEMY".equals(celda.tipo)) {
                String enemyJson = Json.readObject(cellJson, "enemigo", false);
                if (enemyJson.length() == 0) {
                    enemyJson = cellJson;
                }
                celda.enemigo = new EnemigoConfig();
                celda.enemigo.nombre = Json.readString(enemyJson, "nombre", "Enemigo");
                celda.enemigo.vidaMaxima = Json.readInt(enemyJson, "vidaMaxima",
                        Json.readInt(enemyJson, "vida", 20));
                celda.enemigo.vidaActual = Json.readInt(enemyJson, "vidaActual", celda.enemigo.vidaMaxima);
                celda.enemigo.velocidad = Json.readInt(enemyJson, "velocidad", 1);
                celda.enemigo.ataque = Json.readInt(enemyJson, "ataque", 5);
                celda.enemigo.defensa = Json.readInt(enemyJson, "defensa", 0);
            } else if ("ITEM".equals(celda.tipo)) {
                String itemJson = Json.readObject(cellJson, "item", false);
                if (itemJson.length() == 0) {
                    itemJson = cellJson;
                }
                celda.item = parseItem(itemJson);
            } else if ("TRAP".equals(celda.tipo)) {
                celda.danoTrampa = Json.readInt(cellJson, "dano", Json.readInt(cellJson, "trapDamage", 0));
            }
            celdas.add(celda);
        }
        return celdas;
    }

    private ItemConfig parseItem(String itemJson) throws IOException {
        ItemConfig item = new ItemConfig();
        item.tipo = Json.readString(itemJson, "tipo", Json.readString(itemJson, "efecto", "Potion"));
        item.nombre = Json.readString(itemJson, "nombre", item.tipo);
        item.curacion = Json.readInt(itemJson, "curacion", Json.readInt(itemJson, "valor", 20));
        item.ataqueBonus = Json.readInt(itemJson, "ataqueBonus", Json.readInt(itemJson, "bonusAtaque", 0));
        item.defensaBonus = Json.readInt(itemJson, "defensaBonus", Json.readInt(itemJson, "bonusDefensa", 0));
        item.puertaObjetivo = Json.readInt(itemJson, "puertaObjetivo",
                Json.readInt(itemJson, "targetDoorId", Json.readInt(itemJson, "habitacionDestino", 0)));
        return item;
    }

    private IList<ConexionConfig> parseConexiones(String json) throws IOException {
        StubList<ConexionConfig> conexiones = new StubList<>();
        String arrayJson = Json.readArray(json, "conexiones", false);
        if (arrayJson.length() == 0) {
            return conexiones;
        }

        StubList<String> objects = Json.splitObjects(arrayJson);
        for (int i = 0; i < objects.size(); i++) {
            String connectionJson = objects.get(i);
            ConexionConfig conexion = new ConexionConfig();
            conexion.de = Json.readInt(connectionJson, "de", 0);
            conexion.a = Json.readInt(connectionJson, "a", 0);
            conexion.dirigida = Json.readBoolean(connectionJson, "dirigida", false);
            conexion.peso = Json.readInt(connectionJson, "peso", 1);
            conexiones.add(conexion);
        }
        return conexiones;
    }

    private JugadorConfig parseJugadorInicial(String json) throws IOException {
        String playerJson = Json.readObject(json, "jugadorInicial", false);
        if (playerJson.length() == 0) {
            return null;
        }

        JugadorConfig jugador = new JugadorConfig();
        jugador.nombre = Json.readString(playerJson, "nombre", "Heroe");
        jugador.habitacion = Json.readInt(playerJson, "habitacion", 0);
        jugador.fila = Json.readInt(playerJson, "fila", 0);
        jugador.columna = Json.readInt(playerJson, "columna", Json.readInt(playerJson, "col", 0));
        jugador.vidaActual = Json.readInt(playerJson, "vidaActual", Json.readInt(playerJson, "vida", 100));
        jugador.vidaMaxima = Json.readInt(playerJson, "vidaMaxima", jugador.vidaActual);
        jugador.velocidad = Json.readInt(playerJson, "velocidad", 3);
        jugador.ataqueBase = Json.readInt(playerJson, "ataqueBase", Json.readInt(playerJson, "ataque", 10));
        jugador.defensaBase = Json.readInt(playerJson, "defensaBase", Json.readInt(playerJson, "defensa", 3));
        jugador.inventario = parseInventory(playerJson);
        return jugador;
    }

    private IList<ItemConfig> parseInventory(String playerJson) throws IOException {
        StubList<ItemConfig> inventory = new StubList<>();
        String arrayJson = Json.readArray(playerJson, "inventario", false);
        if (arrayJson.length() == 0) {
            return inventory;
        }

        StubList<String> objects = Json.splitObjects(arrayJson);
        for (int i = 0; i < objects.size(); i++) {
            inventory.add(parseItem(objects.get(i)));
        }
        return inventory;
    }

    private String normalizeType(String type) {
        if ("PUERTA".equals(type)) {
            return "DOOR";
        }
        if ("ENEMIGO".equals(type)) {
            return "ENEMY";
        }
        if ("OBJETO".equals(type)) {
            return "ITEM";
        }
        if ("TRAMPA".equals(type)) {
            return "TRAP";
        }
        if ("MURO".equals(type)) {
            return "WALL";
        }
        return type;
    }

    public static class GameConfig {
        public String version;
        public String titulo;
        public int turnosMaximos;
        public int habitacionInicial;
        public int habitacionSalida;
        public IList<HabitacionConfig> habitaciones;
        public IList<ConexionConfig> conexiones;
        public JugadorConfig jugadorInicial;
    }

    public static class HabitacionConfig {
        public int id;
        public String nombre;
        public int filas;
        public int columnas;
        public boolean visitadaInicialmente;
        public IList<CeldaConfig> celdas;
    }

    public static class CeldaConfig {
        public int fila;
        public int columna;
        public String tipo;
        public int habitacionDestino;
        public boolean bloqueada;
        public boolean salidaExterior;
        public boolean abierta;
        public int danoTrampa;
        public EnemigoConfig enemigo;
        public ItemConfig item;
    }

    public static class EnemigoConfig {
        public String nombre;
        public int vidaActual;
        public int vidaMaxima;
        public int velocidad;
        public int ataque;
        public int defensa;
    }

    public static class ItemConfig {
        public String tipo;
        public String nombre;
        public int curacion;
        public int ataqueBonus;
        public int defensaBonus;
        public int puertaObjetivo;
    }

    public static class ConexionConfig {
        public int de;
        public int a;
        public boolean dirigida;
        public int peso;
    }

    public static class JugadorConfig {
        public String nombre;
        public int habitacion;
        public int fila;
        public int columna;
        public int vidaActual;
        public int vidaMaxima;
        public int velocidad;
        public int ataqueBase;
        public int defensaBase;
        public IList<ItemConfig> inventario;
    }

    public static final class Json {
        private Json() {
        }

        public static String readObject(String json, String key, boolean required) throws IOException {
            return readScope(json, key, '{', '}', required);
        }

        public static String readArray(String json, String key, boolean required) throws IOException {
            return readScope(json, key, '[', ']', required);
        }

        public static int readInt(String json, String key, int defaultValue) throws IOException {
            int keyIndex = findKey(json, key);
            if (keyIndex < 0) {
                return defaultValue;
            }

            int start = valueStart(json, keyIndex, key);
            int end = start;
            if (end < json.length() && json.charAt(end) == '-') {
                end++;
            }
            while (end < json.length() && Character.isDigit(json.charAt(end))) {
                end++;
            }
            if (end == start || (end == start + 1 && json.charAt(start) == '-')) {
                throw new IOException("Campo numerico invalido: " + key);
            }
            return Integer.parseInt(json.substring(start, end));
        }

        public static boolean readBoolean(String json, String key, boolean defaultValue) throws IOException {
            int keyIndex = findKey(json, key);
            if (keyIndex < 0) {
                return defaultValue;
            }

            int start = valueStart(json, keyIndex, key);
            if (startsWith(json, start, "true")) {
                return true;
            }
            if (startsWith(json, start, "false")) {
                return false;
            }
            throw new IOException("Campo booleano invalido: " + key);
        }

        public static String readString(String json, String key, String defaultValue) throws IOException {
            int keyIndex = findKey(json, key);
            if (keyIndex < 0) {
                return defaultValue;
            }

            int start = valueStart(json, keyIndex, key);
            if (start >= json.length() || json.charAt(start) != '"') {
                throw new IOException("Campo de texto invalido: " + key);
            }
            int end = findStringEnd(json, start);
            return unescape(json.substring(start + 1, end));
        }

        public static StubList<String> splitObjects(String arrayJson) throws IOException {
            StubList<String> objects = new StubList<>();
            int cursor = 0;
            while (cursor < arrayJson.length()) {
                int start = arrayJson.indexOf('{', cursor);
                if (start < 0) {
                    break;
                }
                int end = findMatching(arrayJson, start, '{', '}');
                objects.add(arrayJson.substring(start, end + 1));
                cursor = end + 1;
            }
            return objects;
        }

        private static String readScope(String json, String key, char open, char close, boolean required)
                throws IOException {
            int keyIndex = findKey(json, key);
            if (keyIndex < 0) {
                if (required) {
                    throw new IOException("Campo obligatorio ausente: " + key);
                }
                return "";
            }

            int start = valueStart(json, keyIndex, key);
            if (start >= json.length() || json.charAt(start) != open) {
                throw new IOException("Campo con estructura invalida: " + key);
            }
            int end = findMatching(json, start, open, close);
            return json.substring(start, end + 1);
        }

        private static int findKey(String json, String key) {
            return json.indexOf("\"" + key + "\"");
        }

        private static int valueStart(String json, int keyIndex, String key) throws IOException {
            int colonIndex = json.indexOf(':', keyIndex);
            if (colonIndex < 0) {
                throw new IOException("Campo sin separador: " + key);
            }
            int start = colonIndex + 1;
            while (start < json.length() && Character.isWhitespace(json.charAt(start))) {
                start++;
            }
            return start;
        }

        private static int findMatching(String text, int start, char open, char close) throws IOException {
            int depth = 0;
            boolean inString = false;
            boolean escaped = false;
            // Se respeta el texto entre comillas para no confundir llaves o corchetes dentro de cadenas.
            for (int i = start; i < text.length(); i++) {
                char current = text.charAt(i);
                if (inString) {
                    if (escaped) {
                        escaped = false;
                    } else if (current == '\\') {
                        escaped = true;
                    } else if (current == '"') {
                        inString = false;
                    }
                } else if (current == '"') {
                    inString = true;
                } else if (current == open) {
                    depth++;
                } else if (current == close) {
                    depth--;
                    if (depth == 0) {
                        return i;
                    }
                }
            }
            throw new IOException("Bloque JSON sin cierre");
        }

        private static int findStringEnd(String text, int start) throws IOException {
            boolean escaped = false;
            for (int i = start + 1; i < text.length(); i++) {
                char current = text.charAt(i);
                if (escaped) {
                    escaped = false;
                } else if (current == '\\') {
                    escaped = true;
                } else if (current == '"') {
                    return i;
                }
            }
            throw new IOException("Cadena JSON sin cierre");
        }

        private static boolean startsWith(String text, int start, String value) {
            return start + value.length() <= text.length()
                    && text.substring(start, start + value.length()).equals(value);
        }

        private static String unescape(String text) {
            StringBuilder builder = new StringBuilder();
            boolean escaped = false;
            for (int i = 0; i < text.length(); i++) {
                char current = text.charAt(i);
                if (escaped) {
                    builder.append(current);
                    escaped = false;
                } else if (current == '\\') {
                    escaped = true;
                } else {
                    builder.append(current);
                }
            }
            return builder.toString();
        }
    }
}
