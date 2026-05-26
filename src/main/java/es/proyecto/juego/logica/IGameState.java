package es.proyecto.juego.logica;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.logica.items.Item;
import es.proyecto.juego.logica.mundo.Room;

public interface IGameState {
    int getPlayerRow();

    int getPlayerCol();

    int getPlayerHp();

    int getPlayerMaxHp();

    int getPlayerSpeed();

    int getPlayerAttack();

    int getPlayerDefense();

    String getEquippedWeaponName();

    String getEquippedArmorName();

    IList<Item> getInventory();

    int getMaxInventorySize();

    boolean isInventoryFull();

    int getCurrentRoomId();

    String getCurrentRoomName();

    int getCurrentRoomRows();

    int getCurrentRoomCols();

    Room getCurrentRoom();

    int getTurnCount();

    int getTurnsLeft();

    boolean canPlayerMove();

    boolean canPlayerAct();

    int getMinRoomsToExit();

    int getDistanceToNearestDoor();

    IList<Integer> getPathToExit();

    IList<String> getEventLog();

    String getLastEvent();

    boolean isGameOver();

    boolean isVictory();
}
