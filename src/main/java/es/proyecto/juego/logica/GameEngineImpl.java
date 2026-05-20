package es.proyecto.juego.logica;

import es.proyecto.juego.estructuras.IList;

import java.io.IOException;

public class GameEngineImpl implements IGameEngine {
    @Override
    public void loadConfig(String jsonPath) throws IOException {
        throw new UnsupportedOperationException("Pendiente de implementar en Track B/C");
    }

    @Override
    public void newGame() {
        throw new UnsupportedOperationException("Pendiente de implementar en Track B");
    }

    @Override
    public void loadGame(String jsonPath) throws IOException {
        throw new UnsupportedOperationException("Pendiente de implementar en Track B/C");
    }

    @Override
    public void saveGame(String jsonPath) throws IOException {
        throw new UnsupportedOperationException("Pendiente de implementar en Track B/C");
    }

    @Override
    public boolean movePlayer(int targetRow, int targetCol) {
        throw new UnsupportedOperationException("Pendiente de implementar en Track B");
    }

    @Override
    public boolean attack(int targetRow, int targetCol) {
        throw new UnsupportedOperationException("Pendiente de implementar en Track B");
    }

    @Override
    public boolean useItem(int inventoryIndex) {
        throw new UnsupportedOperationException("Pendiente de implementar en Track B");
    }

    @Override
    public boolean pickItem(int row, int col) {
        throw new UnsupportedOperationException("Pendiente de implementar en Track B");
    }

    @Override
    public boolean openDoor(int row, int col) {
        throw new UnsupportedOperationException("Pendiente de implementar en Track B");
    }

    @Override
    public void endTurn() {
        throw new UnsupportedOperationException("Pendiente de implementar en Track B");
    }

    @Override
    public IGameState getState() {
        throw new UnsupportedOperationException("Pendiente de implementar en Track B");
    }

    @Override
    public IList<int[]> getReachableCells() {
        throw new UnsupportedOperationException("Pendiente de implementar en Track B");
    }

    @Override
    public IList<int[]> getAttackTargets() {
        throw new UnsupportedOperationException("Pendiente de implementar en Track B");
    }
}
