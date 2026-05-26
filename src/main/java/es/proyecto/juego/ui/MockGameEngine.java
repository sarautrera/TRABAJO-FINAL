package es.proyecto.juego.ui;

import es.proyecto.juego.logica.IGameEngine;
import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.StubList;

public class MockGameEngine implements IGameEngine {

    private int playerRow = 4;
    private int playerCol = 2;
    private int playerHp = 80;
    private StubList<String> log = new StubList<>(); // Uso estricto de StubList sin java.util

    public MockGameEngine() {
        log.add("Partida iniciada en entorno simulado (Mock).");
    }

    @Override
    public boolean movePlayer(int row, int col) {
        if (row < 0 || col < 0 || row >= 5 || col >= 5) return false;
        playerRow = row;
        playerCol = col;
        log.add("Jugador se mueve a (" + row + "," + col + ")");
        return true;
    }

    @Override
    public IGameState getState() {
        return new MockGameState(playerRow, playerCol, playerHp, log);
    }

    @Override
    public IList<int[]> getReachableCells() {
        // Devolvemos celdas adyacentes usando StubList para pintar el reborde amarillo de la UI
        StubList<int[]> alcanzables = new StubList<>();
        alcanzables.add(new int[]{playerRow - 1, playerCol}); // Celda superior
        alcanzables.add(new int[]{playerRow, playerCol - 1}); // Celda izquierda
        return alcanzables;
    }
}