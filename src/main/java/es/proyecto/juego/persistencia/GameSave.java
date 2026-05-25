package es.proyecto.juego.persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import es.proyecto.juego.logica.IGameState;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

// Clase responsable de guardar y cargar las partidas. Transforma los datos del juego en un archivo de texto (.json) y viceversa.
public class GameSave {

    // El objeto 'gson' es el motor de la librería de Google.
    // Usamos 'setPrettyPrinting()' para que el archivo JSON esté ordenado con saltos de línea y tabulaciones, haciéndolo legible para un humano.
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //GUARDA: Toma el estado del juego y lo escribe en un archivo de disco.
            //DATOS DEL MÉTODO:
            //state: El estado actual de la partida (de donde sacamos la vida, posición, etc.)
            //path: La ruta del archivo donde queremos guardar (ej: "partida.json")
            //IOException: Si ocurre un error de lectura/escritura (ej: disco lleno o sin permisos)
    public void save(IGameState state, String path) throws IOException {
        SaveData data = SaveData.fromState(state);  // Extrae los datos numéricos/básicos y los mete en el contenedor 'SaveData'

        // Abre el archivo de texto en modo escritura ('FileWriter')
        // El uso del 'try' asegura que el arcivo se cierre automáticamente al terminar.
        try (Writer w = new FileWriter(path)) {
            gson.toJson(data, w); // La librería Gson convierte el objeto 'data' a texto JSON y lo escribe en el archivo 'w'
        }
    }

    //CARGA: Lee un archivo del disco y reconstruye los datos de la partida.
        //DATOS DEL MÉTODO:
            //path: La ruta del archivo que queremos leer (ej: "partida.json")
            //return: Un objeto SaveData con la información recuperada del archivo
            //IOException Si el archivo no existe o si el formato está corrupto
    public SaveData load(String path) throws IOException {

        // Abre el archivo de texto en modo lectura ('FileReader')
        try (Reader r = new FileReader(path)) {
            return gson.fromJson(r, SaveData.class);//Gson lee el texto del archivo 'r' y lo transforma en un objeto Java de la clase 'SaveData'
        } catch (JsonParseException e) {
            throw new IOException("Partida guardada corrupta: " + e.getMessage(), e);// Si el usuario modificó el archivo JSON a mano y cometió un error de sintaxis,captura el fallo de Gson y lanza una excepción
        }
    }
    // DTO (Data Transfer Object) para aislar la interfaz de la librería JSON, almacena las variables exactas que queremos guardar en el archivo de texto.
    public static class SaveData {
        // Datos que se escribirán directamente en el archivo JSON:
        public String version = "1.0"; // Útil para cuando actualizes el juego y cambies las variables
        public int turnoActual;
        public int vidaActual;
        public int fila, col; // Posición del jugador

        // Recibe el estado completo del juego y "copia" solo los datos necesarios en un 'SaveData'.
        public static SaveData fromState(IGameState state) {
            SaveData data = new SaveData();
            data.turnoActual = state.getTurnCount();
            data.vidaActual = state.getPlayerHp();
            data.fila = state.getPlayerRow();
            data.col = state.getPlayerCol();
            return data;
        }
    }
}