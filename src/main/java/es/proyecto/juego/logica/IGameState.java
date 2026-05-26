// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Item;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Room;

// Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
public interface IGameState {
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getPlayerRow();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getPlayerCol();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getPlayerHp();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getPlayerMaxHp();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getPlayerSpeed();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getPlayerAttack();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getPlayerDefense();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    String getEquippedWeaponName();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    String getEquippedArmorName();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    IList<Item> getInventory();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getMaxInventorySize();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean isInventoryFull();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getCurrentRoomId();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    String getCurrentRoomName();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getCurrentRoomRows();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getCurrentRoomCols();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    Room getCurrentRoom();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getTurnCount();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getTurnsLeft();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean canPlayerMove();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean canPlayerAct();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getMinRoomsToExit();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    int getDistanceToNearestDoor();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    IList<Integer> getPathToExit();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    IList<String> getEventLog();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    String getLastEvent();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean isGameOver();

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    boolean isVictory();
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
