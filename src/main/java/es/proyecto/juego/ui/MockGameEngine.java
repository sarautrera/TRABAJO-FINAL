// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.ui;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.StubList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameEngine;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.IOException;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class MockGameEngine implements IGameEngine {

    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int playerRow = 4;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int playerCol = 2;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int playerHp = 80;
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private StubList<String> log = new StubList<>();

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public MockGameEngine() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.add("Partida iniciada en entorno simulado (Mock).");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void loadConfig(String jsonPath) throws IOException {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.add("Configuracion cargada: " + jsonPath);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void newGame() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        playerRow = 4;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        playerCol = 2;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        playerHp = 80;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.add("Nueva partida simulada.");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void loadGame(String jsonPath) throws IOException {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.add("Partida cargada: " + jsonPath);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void saveGame(String jsonPath) throws IOException {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.add("Partida guardada: " + jsonPath);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean movePlayer(int row, int col) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (row < 0 || col < 0 || row >= 5 || col >= 5) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return false;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        playerRow = row;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        playerCol = col;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.add("Jugador se mueve a (" + row + "," + col + ")");
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean attack(int targetRow, int targetCol) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.add("Ataque simulado a (" + targetRow + "," + targetCol + ")");
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean useItem(int inventoryIndex) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.add("Uso simulado de item " + inventoryIndex);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean pickItem(int row, int col) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.add("Recogida simulada en (" + row + "," + col + ")");
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean openDoor(int row, int col) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.add("Puerta simulada en (" + row + "," + col + ")");
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void endTurn() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.add("Turno finalizado.");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IGameState getState() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return new MockGameState(playerRow, playerCol, playerHp, log);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<int[]> getReachableCells() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<int[]> alcanzables = new StubList<>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        alcanzables.add(new int[]{playerRow - 1, playerCol});
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        alcanzables.add(new int[]{playerRow, playerCol - 1});
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return alcanzables;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<int[]> getAttackTargets() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return new StubList<>();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
