package es.proyecto.juego.ui;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.StubList;
import es.proyecto.juego.logica.IGameEngine;
import es.proyecto.juego.logica.IGameState;

import java.io.IOException;

public class MockGameEngine implements IGameEngine {

    private int playerRow = 4;
    private int playerCol = 2;
    private int playerHp = 80;
    private StubList<String> log = new StubList<>();

    public MockGameEngine() {
        log.add("Partida iniciada en entorno simulado (Mock).");
    }

    @Override
    public void loadConfig(String jsonPath) throws IOException {
        log.add("Configuracion cargada: " + jsonPath);
    }

    @Override
    public void newGame() {
        playerRow = 4;
        playerCol = 2;
        playerHp = 80;
        log.add("Nueva partida simulada.");
    }

    @Override
    public void loadGame(String jsonPath) throws IOException {
        log.add("Partida cargada: " + jsonPath);
    }

    @Override
    public void saveGame(String jsonPath) throws IOException {
        log.add("Partida guardada: " + jsonPath);
    }

    @Override
    public boolean movePlayer(int row, int col) {
        if (row < 0 || col < 0 || row >= 5 || col >= 5) {
            return false;
        }
        playerRow = row;
        playerCol = col;
        log.add("Jugador se mueve a (" + row + "," + col + ")");
        return true;
    }

    @Override
    public boolean attack(int targetRow, int targetCol) {
        log.add("Ataque simulado a (" + targetRow + "," + targetCol + ")");
        return true;
    }

    @Override
    public boolean useItem(int inventoryIndex) {
        log.add("Uso simulado de item " + inventoryIndex);
        return true;
    }

    @Override
    public boolean pickItem(int row, int col) {
        log.add("Recogida simulada en (" + row + "," + col + ")");
        return true;
    }

    @Override
    public boolean openDoor(int row, int col) {
        log.add("Puerta simulada en (" + row + "," + col + ")");
        return true;
    }

    @Override
    public void endTurn() {
        log.add("Turno finalizado.");
    }

    @Override
    public IGameState getState() {
        return new MockGameState(playerRow, playerCol, playerHp, log);
    }

    @Override
    public IList<int[]> getReachableCells() {
        StubList<int[]> alcanzables = new StubList<>();
        alcanzables.add(new int[]{playerRow - 1, playerCol});
        alcanzables.add(new int[]{playerRow, playerCol - 1});
        return alcanzables;
    }

    @Override
    public IList<int[]> getAttackTargets() {
        return new StubList<>();
    }
}
