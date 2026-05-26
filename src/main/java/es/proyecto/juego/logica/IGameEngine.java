// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.IOException;

// Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
public interface IGameEngine {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void loadConfig(String jsonPath) throws IOException;

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void newGame();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void loadGame(String jsonPath) throws IOException;

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void saveGame(String jsonPath) throws IOException;

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean movePlayer(int targetRow, int targetCol);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean attack(int targetRow, int targetCol);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean useItem(int inventoryIndex);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean pickItem(int row, int col);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean openDoor(int row, int col);

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void endTurn();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    IGameState getState();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    IList<int[]> getReachableCells();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    IList<int[]> getAttackTargets();
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
