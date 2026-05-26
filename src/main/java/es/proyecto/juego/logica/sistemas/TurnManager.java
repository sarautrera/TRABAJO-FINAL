/*
 * Resumen del fichero: Controla el orden de turnos y limita movimientos y acciones por ronda.
 */
package es.proyecto.juego.logica.sistemas;

import es.proyecto.juego.estructuras.MyCircularList;

public class TurnManager {
    public static final String PLAYER_ACTOR = "PLAYER";

    private int turnCount;
    private final int maxTurns;
    private final MyCircularList<String> turnOrder;
    private boolean movementUsed;
    private boolean actionUsed;

    public TurnManager(int maxTurns) {
        if (maxTurns <= 0) {
            throw new IllegalArgumentException("El maximo de turnos debe ser positivo");
        }
        this.maxTurns = maxTurns;
        this.turnOrder = new MyCircularList<>();
        this.turnOrder.add(PLAYER_ACTOR);
    }

    public void addActor(String actorId) {
        if (actorId == null || actorId.length() == 0) {
            throw new IllegalArgumentException("El actor no puede estar vacio");
        }
        turnOrder.add(actorId);
    }

    public String getCurrentActor() {
        return turnOrder.current();
    }

    public boolean isPlayerTurn() {
        return PLAYER_ACTOR.equals(getCurrentActor());
    }

    public boolean canMove() {
        return isPlayerTurn() && !movementUsed && !isTimeUp();
    }

    public boolean canAct() {
        return isPlayerTurn() && !actionUsed && !isTimeUp();
    }

    public void markMovementUsed() {
        if (!canMove()) {
            throw new IllegalStateException("El movimiento ya fue usado o no es turno del jugador");
        }
        movementUsed = true;
    }

    public void markActionUsed() {
        if (!canAct()) {
            throw new IllegalStateException("La accion ya fue usada o no es turno del jugador");
        }
        actionUsed = true;
    }

    public void endPlayerTurn() {
        movementUsed = false;
        actionUsed = false;
        advanceActor();
    }

    public String advanceActor() {
        String nextActor = turnOrder.next();
        if (PLAYER_ACTOR.equals(nextActor)) {
            endRound();
        }
        return nextActor;
    }

    public void resetTurnActions() {
        movementUsed = false;
        actionUsed = false;
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

    public boolean isMovementUsed() {
        return movementUsed;
    }

    public boolean isActionUsed() {
        return actionUsed;
    }
}
