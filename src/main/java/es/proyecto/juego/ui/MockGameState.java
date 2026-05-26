package es.proyecto.juego.ui;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.StubList;
import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.logica.items.Item;
import es.proyecto.juego.logica.mundo.Room;

public class MockGameState implements IGameState {
    private int row;
    private int col;
    private int hp;
    private IList<String> logTemporal;

    public MockGameState(int row, int col, int hp, IList<String> log) {
        this.row = row;
        this.col = col;
        this.hp = hp;
        this.logTemporal = log;
    }

    @Override public int getPlayerRow() { return row; }
    @Override public int getPlayerCol() { return col; }
    @Override public int getPlayerHp() { return hp; }
    @Override public int getPlayerMaxHp() { return 100; }
    @Override public int getPlayerSpeed() { return 3; }
    @Override public int getPlayerAttack() { return 15; }
    @Override public int getPlayerDefense() { return 5; }
    @Override public String getEquippedWeaponName() { return ""; }
    @Override public String getEquippedArmorName() { return ""; }
    @Override public IList<Item> getInventory() { return new StubList<>(); }
    @Override public int getMaxInventorySize() { return 5; }
    @Override public boolean isInventoryFull() { return false; }
    @Override public int getCurrentRoomId() { return 1; }
    @Override public String getCurrentRoomName() { return "Mock"; }
    @Override public int getCurrentRoomRows() { return 5; }
    @Override public int getCurrentRoomCols() { return 5; }
    @Override public Room getCurrentRoom() { return null; }
    @Override public int getTurnCount() { return 5; }
    @Override public int getTurnsLeft() { return 45; }
    @Override public boolean canPlayerMove() { return true; }
    @Override public boolean canPlayerAct() { return true; }
    @Override public int getMinRoomsToExit() { return 2; }
    @Override public int getDistanceToNearestDoor() { return 4; }
    @Override public IList<Integer> getPathToExit() { return new StubList<>(); }
    @Override public IList<String> getEventLog() { return logTemporal; }

    @Override
    public String getLastEvent() {
        if (logTemporal == null || logTemporal.isEmpty()) {
            return "";
        }
        return logTemporal.get(logTemporal.size() - 1);
    }

    @Override public boolean isGameOver() { return false; }
    @Override public boolean isVictory() { return false; }
}
