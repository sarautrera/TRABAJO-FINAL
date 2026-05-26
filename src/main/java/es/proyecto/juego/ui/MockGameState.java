// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.ui;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.StubList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Item;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Room;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class MockGameState implements IGameState {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int row;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int col;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int hp;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private IList<String> logTemporal;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public MockGameState(int row, int col, int hp, IList<String> log) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.row = row;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.col = col;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.hp = hp;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.logTemporal = log;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getPlayerRow() { return row; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getPlayerCol() { return col; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getPlayerHp() { return hp; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getPlayerMaxHp() { return 100; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getPlayerSpeed() { return 3; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getPlayerAttack() { return 15; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getPlayerDefense() { return 5; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public String getEquippedWeaponName() { return ""; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public String getEquippedArmorName() { return ""; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public IList<Item> getInventory() { return new StubList<>(); }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getMaxInventorySize() { return 5; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public boolean isInventoryFull() { return false; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getCurrentRoomId() { return 1; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public String getCurrentRoomName() { return "Mock"; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getCurrentRoomRows() { return 5; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getCurrentRoomCols() { return 5; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public Room getCurrentRoom() { return null; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getTurnCount() { return 5; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getTurnsLeft() { return 45; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public boolean canPlayerMove() { return true; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public boolean canPlayerAct() { return true; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getMinRoomsToExit() { return 2; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public int getDistanceToNearestDoor() { return 4; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public IList<Integer> getPathToExit() { return new StubList<>(); }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public IList<String> getEventLog() { return logTemporal; }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public String getLastEvent() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (logTemporal == null || logTemporal.isEmpty()) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return logTemporal.get(logTemporal.size() - 1);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public boolean isGameOver() { return false; }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override public boolean isVictory() { return false; }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
