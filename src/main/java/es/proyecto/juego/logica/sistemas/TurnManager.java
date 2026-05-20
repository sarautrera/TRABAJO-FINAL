package es.proyecto.juego.logica.sistemas;

public class TurnManager {
    private int turnCount;
    private final int maxTurns;

    public TurnManager(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    public void endRound() {
        turnCount++;
    }

    public boolean isTimeUp() {
        return turnCount >= maxTurns;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public int getTurnsLeft() {
        return Math.max(0, maxTurns - turnCount);
    }
}
