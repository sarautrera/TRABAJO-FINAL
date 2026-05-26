package es.proyecto.juego.ui;

import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.logica.Room;
import es.proyecto.juego.logica.Item;
import es.proyecto.juego.estructuras.IList;

public class MockGameState implements IGameState {
    private int row, col, hp;
    private IList<String> logTemporal;

    public MockGameState(int row, int col, int hp, IList<String> log) {
        this.row = row;
        this.col = col;
        this.hp = hp;
        this.logTemporal = log;
    }

    // --- Jugador ---
    @Override public int getPlayerRow() { return row; }
    @Override public int getPlayerCol() { return col; }
    @Override public int getPlayerHp() { return hp; }
    @Override public int getPlayerMaxHp() { return 100; }
    @Override public int getPlayerSpeed() { return 3; }
    @Override public int getPlayerAttack() { return 15; }
    @Override public int getPlayerDefense() { return 5; }

    @Override
    public IList<Item> getInventory() {
        return null;
    }

    // --- Habitación y partida ---
    @Override public Room getCurrentRoom() { return null; }
    @Override public int getTurnCount() { return 5; }
    @Override public int getTurnsLeft() { return 45; }

    // --- Navegación (Dijkstra) ---
    @Override public int getMinRoomsToExit() { return 2; }
    @Override public int getDistanceToNearestDoor() { return 4; }
    @Override public IList<Integer> getPathToExit() { return null; }

    // --- Log y estado ---
    @Override
    public IList<String> getEventLog() {
        return logTemporal;
    }

    @Override public String getLastEvent() {
        if (logTemporal == null || logTemporal.isEmpty()) return "";
        return logTemporal.get(logTemporal.size() - 1);
    }

    @Override public boolean isGameOver() { return false; }
    @Override public boolean isVictory() { return false; }
}