package es.proyecto.juego.logica;

import es.proyecto.juego.estructuras.IList;

//Interfaz que define el estado actual de la partida. Proporciona métodos de "solo lectura" (getters) para consultar los datos del jugador, la habitación actual, la navegación y el progreso.
public interface IGameState {

    // JUGADOR(ESTADISTICAS Y ESTADO)
    int getPlayerRow();// Fila (coordenada Y) donde se encuentra el jugador en la sala actual.
    int getPlayerCol();// Columna (coordenada X) donde se encuentra el jugador en la sala actual.
    int getPlayerHp(); // Puntos de vida actuales del jugador. Si llega a 0, muere.
    int getPlayerMaxHp();// Vida máxima que puede tener el jugador.
    int getPlayerSpeed(); // Velocidad del jugador (puede influir en el orden de los turnos o movimiento).
    int getPlayerAttack(); // Puntos de ataque del jugador (daño básico que inflige).
    int getPlayerDefense(); // Puntos de defensa del jugador (reduce el daño recibido).
    IList<Item> getInventory(); //Lista con los objetos (Items) que el jugador lleva equipados o guardados.

    // HABITACIÓN Y PARTIDA (PROGRESO)
    Room getCurrentRoom(); // El objeto 'Room' (habitación) en la que se encuentra el jugador actualmente.
    int getTurnCount(); // Cantidad de turnos que han transcurrido desde que empezó la partida.
    int getTurnsLeft(); // Turnos restantes antes de que ocurra un evento de Game Over o penalización.

    // NAVEGACIÓN (ALGORITMIA/DJKSTRA)
    int getMinRoomsToExit(); // Número mínimo de habitaciones que faltan por cruzar para llegar a la salida del juego.
    int getDistanceToNearestDoor(); // Distancia en casillas (o pasos) hasta la puerta más cercana dentro de la habitación actual.
    IList<Integer> getPathToExit(); // Lista de identificadores o pasos que representan la ruta óptima calculada hacia la salida.

    // LOG Y ESTADO DE LA PARTIDA
    IList<String> getEventLog(); // El historial completo de eventos ocurridos en la partida (mensajes de texto).
    String getLastEvent(); // El último mensaje o evento que ha ocurrido (útil para mostrar alertas en pantalla).
    boolean isGameOver(); // true si la partida ha terminado (ya sea por derrota o por victoria).
    boolean isVictory(); // true si el jugador ha ganado la partida (ha cumplido la condición de victoria).
}