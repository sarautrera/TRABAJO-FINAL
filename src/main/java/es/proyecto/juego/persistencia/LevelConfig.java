// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.persistencia;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.StubList;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.BufferedReader;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.FileReader;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.IOException;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class LevelConfig {

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public GameConfig load(String path) throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String json = readTextFile(path);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!json.trim().startsWith("{")) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IOException("JSON de configuracion malformado");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameConfig config = new GameConfig();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        config.version = Json.readString(json, "version", "");
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        config.titulo = Json.readString(json, "titulo", "");
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        config.turnosMaximos = Json.readInt(json, "turnosMaximos", 50);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        config.habitacionInicial = Json.readInt(json, "habitacionInicial", 0);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        config.habitacionSalida = Json.readInt(json, "habitacionSalida", 1);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        config.habitaciones = parseHabitaciones(json);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        config.conexiones = parseConexiones(json);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        config.jugadorInicial = parseJugadorInicial(json);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        validate(config);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return config;
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
    private void validate(GameConfig config) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (config.habitaciones == null || config.habitaciones.isEmpty()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("El juego necesita al menos una habitacion.");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (config.jugadorInicial == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("Falta jugadorInicial en la configuracion.");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private IList<HabitacionConfig> parseHabitaciones(String json) throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<HabitacionConfig> habitaciones = new StubList<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String arrayJson = Json.readArray(json, "habitaciones", false);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (arrayJson.length() == 0) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return habitaciones;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<String> objects = Json.splitObjects(arrayJson);
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < objects.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            String roomJson = objects.get(i);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            HabitacionConfig habitacion = new HabitacionConfig();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            habitacion.id = Json.readInt(roomJson, "id", 0);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            habitacion.nombre = Json.readString(roomJson, "nombre", "Habitacion " + habitacion.id);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            habitacion.filas = Json.readInt(roomJson, "filas", 5);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            habitacion.columnas = Json.readInt(roomJson, "columnas", Json.readInt(roomJson, "cols", 5));
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            habitacion.visitadaInicialmente = Json.readBoolean(roomJson, "visitadaInicialmente", false);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            habitacion.celdas = parseCeldas(roomJson);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            habitaciones.add(habitacion);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return habitaciones;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private IList<CeldaConfig> parseCeldas(String roomJson) throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<CeldaConfig> celdas = new StubList<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String arrayJson = Json.readArray(roomJson, "celdas", false);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (arrayJson.length() == 0) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return celdas;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<String> objects = Json.splitObjects(arrayJson);
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < objects.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            String cellJson = objects.get(i);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            CeldaConfig celda = new CeldaConfig();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            celda.fila = Json.readInt(cellJson, "fila", 0);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            celda.columna = Json.readInt(cellJson, "columna", Json.readInt(cellJson, "col", 0));
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            celda.tipo = normalizeType(Json.readString(cellJson, "tipo", "EMPTY"));

            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if ("DOOR".equals(celda.tipo)) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                celda.habitacionDestino = Json.readInt(cellJson, "habitacionDestino",
                        // Comentario de estudiante: aqui se prepara una instruccion del programa.
                        Json.readInt(cellJson, "conectaCon", -1));
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                celda.bloqueada = Json.readBoolean(cellJson, "bloqueada",
                        // Comentario de estudiante: aqui se prepara una instruccion del programa.
                        Json.readBoolean(cellJson, "necesitaLlave", false));
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                celda.salidaExterior = Json.readBoolean(cellJson, "salidaExterior",
                        // Comentario de estudiante: aqui se prepara una instruccion del programa.
                        Json.readBoolean(cellJson, "esSalida", false));
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                celda.abierta = Json.readBoolean(cellJson, "abierta", false);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else if ("ENEMY".equals(celda.tipo)) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                String enemyJson = Json.readObject(cellJson, "enemigo", false);
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (enemyJson.length() == 0) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    enemyJson = cellJson;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                celda.enemigo = new EnemigoConfig();
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                celda.enemigo.nombre = Json.readString(enemyJson, "nombre", "Enemigo");
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                celda.enemigo.vidaMaxima = Json.readInt(enemyJson, "vidaMaxima",
                        // Comentario de estudiante: aqui se prepara una instruccion del programa.
                        Json.readInt(enemyJson, "vida", 20));
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                celda.enemigo.vidaActual = Json.readInt(enemyJson, "vidaActual", celda.enemigo.vidaMaxima);
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                celda.enemigo.velocidad = Json.readInt(enemyJson, "velocidad", 1);
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                celda.enemigo.ataque = Json.readInt(enemyJson, "ataque", 5);
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                celda.enemigo.defensa = Json.readInt(enemyJson, "defensa", 0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else if ("ITEM".equals(celda.tipo)) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                String itemJson = Json.readObject(cellJson, "item", false);
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (itemJson.length() == 0) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    itemJson = cellJson;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                celda.item = parseItem(itemJson);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else if ("TRAP".equals(celda.tipo)) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                celda.danoTrampa = Json.readInt(cellJson, "dano", Json.readInt(cellJson, "trapDamage", 0));
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            celdas.add(celda);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return celdas;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private ItemConfig parseItem(String itemJson) throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        ItemConfig item = new ItemConfig();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        item.tipo = Json.readString(itemJson, "tipo", Json.readString(itemJson, "efecto", "Potion"));
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        item.nombre = Json.readString(itemJson, "nombre", item.tipo);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        item.curacion = Json.readInt(itemJson, "curacion", Json.readInt(itemJson, "valor", 20));
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        item.ataqueBonus = Json.readInt(itemJson, "ataqueBonus", Json.readInt(itemJson, "bonusAtaque", 0));
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        item.defensaBonus = Json.readInt(itemJson, "defensaBonus", Json.readInt(itemJson, "bonusDefensa", 0));
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        item.puertaObjetivo = Json.readInt(itemJson, "puertaObjetivo",
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                Json.readInt(itemJson, "targetDoorId", Json.readInt(itemJson, "habitacionDestino", 0)));
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return item;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private IList<ConexionConfig> parseConexiones(String json) throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<ConexionConfig> conexiones = new StubList<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String arrayJson = Json.readArray(json, "conexiones", false);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (arrayJson.length() == 0) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return conexiones;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<String> objects = Json.splitObjects(arrayJson);
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < objects.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            String connectionJson = objects.get(i);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            ConexionConfig conexion = new ConexionConfig();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            conexion.de = Json.readInt(connectionJson, "de", 0);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            conexion.a = Json.readInt(connectionJson, "a", 0);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            conexion.dirigida = Json.readBoolean(connectionJson, "dirigida", false);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            conexion.peso = Json.readInt(connectionJson, "peso", 1);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            conexiones.add(conexion);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return conexiones;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private JugadorConfig parseJugadorInicial(String json) throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String playerJson = Json.readObject(json, "jugadorInicial", false);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (playerJson.length() == 0) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return null;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        JugadorConfig jugador = new JugadorConfig();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        jugador.nombre = Json.readString(playerJson, "nombre", "Heroe");
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        jugador.habitacion = Json.readInt(playerJson, "habitacion", 0);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        jugador.fila = Json.readInt(playerJson, "fila", 0);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        jugador.columna = Json.readInt(playerJson, "columna", Json.readInt(playerJson, "col", 0));
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        jugador.vidaActual = Json.readInt(playerJson, "vidaActual", Json.readInt(playerJson, "vida", 100));
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        jugador.vidaMaxima = Json.readInt(playerJson, "vidaMaxima", jugador.vidaActual);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        jugador.velocidad = Json.readInt(playerJson, "velocidad", 3);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        jugador.ataqueBase = Json.readInt(playerJson, "ataqueBase", Json.readInt(playerJson, "ataque", 10));
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        jugador.defensaBase = Json.readInt(playerJson, "defensaBase", Json.readInt(playerJson, "defensa", 3));
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        jugador.inventario = parseInventory(playerJson);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return jugador;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private IList<ItemConfig> parseInventory(String playerJson) throws IOException {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<ItemConfig> inventory = new StubList<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String arrayJson = Json.readArray(playerJson, "inventario", false);
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (arrayJson.length() == 0) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return inventory;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<String> objects = Json.splitObjects(arrayJson);
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < objects.size(); i++) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            inventory.add(parseItem(objects.get(i)));
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return inventory;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private String normalizeType(String type) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if ("PUERTA".equals(type)) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "DOOR";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if ("ENEMIGO".equals(type)) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "ENEMY";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if ("OBJETO".equals(type)) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "ITEM";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if ("TRAMPA".equals(type)) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "TRAP";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if ("MURO".equals(type)) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "WALL";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return type;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    public static class GameConfig {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public String version;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public String titulo;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int turnosMaximos;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int habitacionInicial;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int habitacionSalida;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public IList<HabitacionConfig> habitaciones;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public IList<ConexionConfig> conexiones;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public JugadorConfig jugadorInicial;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    public static class HabitacionConfig {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int id;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public String nombre;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int filas;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int columnas;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public boolean visitadaInicialmente;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public IList<CeldaConfig> celdas;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    public static class CeldaConfig {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int fila;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int columna;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public String tipo;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int habitacionDestino;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public boolean bloqueada;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public boolean salidaExterior;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public boolean abierta;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int danoTrampa;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public EnemigoConfig enemigo;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public ItemConfig item;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    public static class EnemigoConfig {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public String nombre;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int vidaActual;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int vidaMaxima;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int velocidad;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int ataque;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int defensa;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    public static class ItemConfig {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public String tipo;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public String nombre;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int curacion;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int ataqueBonus;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int defensaBonus;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int puertaObjetivo;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    public static class ConexionConfig {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int de;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int a;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public boolean dirigida;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int peso;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    public static class JugadorConfig {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public String nombre;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int habitacion;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int fila;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int columna;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int vidaActual;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int vidaMaxima;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int velocidad;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int ataqueBase;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public int defensaBase;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        public IList<ItemConfig> inventario;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    public static final class Json {
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private Json() {
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public static String readObject(String json, String key, boolean required) throws IOException {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return readScope(json, key, '{', '}', required);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public static String readArray(String json, String key, boolean required) throws IOException {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return readScope(json, key, '[', ']', required);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public static int readInt(String json, String key, int defaultValue) throws IOException {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int keyIndex = findKey(json, key);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (keyIndex < 0) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return defaultValue;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int start = valueStart(json, keyIndex, key);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int end = start;
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (end < json.length() && json.charAt(end) == '-') {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                end++;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui empieza un bucle while.
            while (end < json.length() && Character.isDigit(json.charAt(end))) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                end++;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (end == start || (end == start + 1 && json.charAt(start) == '-')) {
                // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
                throw new IOException("Campo numerico invalido: " + key);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return Integer.parseInt(json.substring(start, end));
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public static boolean readBoolean(String json, String key, boolean defaultValue) throws IOException {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int keyIndex = findKey(json, key);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (keyIndex < 0) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return defaultValue;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int start = valueStart(json, keyIndex, key);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (startsWith(json, start, "true")) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return true;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (startsWith(json, start, "false")) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return false;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IOException("Campo booleano invalido: " + key);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public static String readString(String json, String key, String defaultValue) throws IOException {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int keyIndex = findKey(json, key);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (keyIndex < 0) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return defaultValue;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int start = valueStart(json, keyIndex, key);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (start >= json.length() || json.charAt(start) != '"') {
                // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
                throw new IOException("Campo de texto invalido: " + key);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int end = findStringEnd(json, start);
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return unescape(json.substring(start + 1, end));
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public static StubList<String> splitObjects(String arrayJson) throws IOException {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            StubList<String> objects = new StubList<>();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int cursor = 0;
            // Comentario de estudiante: aqui empieza un bucle while.
            while (cursor < arrayJson.length()) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                int start = arrayJson.indexOf('{', cursor);
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (start < 0) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    break;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                int end = findMatching(arrayJson, start, '{', '}');
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                objects.add(arrayJson.substring(start, end + 1));
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                cursor = end + 1;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return objects;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static String readScope(String json, String key, char open, char close, boolean required)
                // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
                throws IOException {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int keyIndex = findKey(json, key);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (keyIndex < 0) {
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (required) {
                    // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
                    throw new IOException("Campo obligatorio ausente: " + key);
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return "";
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int start = valueStart(json, keyIndex, key);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (start >= json.length() || json.charAt(start) != open) {
                // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
                throw new IOException("Campo con estructura invalida: " + key);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int end = findMatching(json, start, open, close);
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return json.substring(start, end + 1);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static int findKey(String json, String key) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return json.indexOf("\"" + key + "\"");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static int valueStart(String json, int keyIndex, String key) throws IOException {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int colonIndex = json.indexOf(':', keyIndex);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (colonIndex < 0) {
                // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
                throw new IOException("Campo sin separador: " + key);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int start = colonIndex + 1;
            // Comentario de estudiante: aqui empieza un bucle while.
            while (start < json.length() && Character.isWhitespace(json.charAt(start))) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                start++;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return start;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static int findMatching(String text, int start, char open, char close) throws IOException {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int depth = 0;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            boolean inString = false;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            boolean escaped = false;
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int i = start; i < text.length(); i++) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                char current = text.charAt(i);
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (inString) {
                    // Comentario de estudiante: aqui se comprueba una condicion con if.
                    if (escaped) {
                        // Comentario de estudiante: aqui se guarda o actualiza un valor.
                        escaped = false;
                    // Comentario de estudiante: aqui se cierra un bloque de codigo.
                    } else if (current == '\\') {
                        // Comentario de estudiante: aqui se guarda o actualiza un valor.
                        escaped = true;
                    // Comentario de estudiante: aqui se cierra un bloque de codigo.
                    } else if (current == '"') {
                        // Comentario de estudiante: aqui se guarda o actualiza un valor.
                        inString = false;
                    // Comentario de estudiante: aqui se cierra un bloque de codigo.
                    }
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                } else if (current == '"') {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    inString = true;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                } else if (current == open) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    depth++;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                } else if (current == close) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    depth--;
                    // Comentario de estudiante: aqui se comprueba una condicion con if.
                    if (depth == 0) {
                        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                        return i;
                    // Comentario de estudiante: aqui se cierra un bloque de codigo.
                    }
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IOException("Bloque JSON sin cierre");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static int findStringEnd(String text, int start) throws IOException {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            boolean escaped = false;
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int i = start + 1; i < text.length(); i++) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                char current = text.charAt(i);
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (escaped) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    escaped = false;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                } else if (current == '\\') {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    escaped = true;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                } else if (current == '"') {
                    // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                    return i;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IOException("Cadena JSON sin cierre");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static boolean startsWith(String text, int start, String value) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return start + value.length() <= text.length()
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    && text.substring(start, start + value.length()).equals(value);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private static String unescape(String text) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            StringBuilder builder = new StringBuilder();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            boolean escaped = false;
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int i = 0; i < text.length(); i++) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                char current = text.charAt(i);
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (escaped) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    builder.append(current);
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    escaped = false;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                } else if (current == '\\') {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    escaped = true;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                } else {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    builder.append(current);
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return builder.toString();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
