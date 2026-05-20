package es.proyecto.juego.logica;

import es.proyecto.juego.estructuras.IList;

import java.io.IOException;

public interface IGameEngine {
    void loadConfig(String jsonPath) throws IOException;

    void newGame();

    void loadGame(String jsonPath) throws IOException;

    void saveGame(String jsonPath) throws IOException;

    boolean movePlayer(int targetRow, int targetCol);

    boolean attack(int targetRow, int targetCol);

    boolean useItem(int inventoryIndex);

    boolean pickItem(int row, int col);

    boolean openDoor(int row, int col);

    void endTurn();

    IGameState getState();

    IList<int[]> getReachableCells();

    IList<int[]> getAttackTargets();
}
