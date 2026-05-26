// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica.sistemas;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyCircularList;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class TurnManager {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    public static final String PLAYER_ACTOR = "PLAYER";

    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int turnCount;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final int maxTurns;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final MyCircularList<String> turnOrder;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private boolean movementUsed;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private boolean actionUsed;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public TurnManager(int maxTurns) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (maxTurns <= 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El maximo de turnos debe ser positivo");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.maxTurns = maxTurns;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.turnOrder = new MyCircularList<>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        this.turnOrder.add(PLAYER_ACTOR);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void addActor(String actorId) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (actorId == null || actorId.length() == 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El actor no puede estar vacio");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        turnOrder.add(actorId);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public String getCurrentActor() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return turnOrder.current();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isPlayerTurn() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return PLAYER_ACTOR.equals(getCurrentActor());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean canMove() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return isPlayerTurn() && !movementUsed && !isTimeUp();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean canAct() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return isPlayerTurn() && !actionUsed && !isTimeUp();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void markMovementUsed() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!canMove()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("El movimiento ya fue usado o no es turno del jugador");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        movementUsed = true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void markActionUsed() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (!canAct()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalStateException("La accion ya fue usada o no es turno del jugador");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        actionUsed = true;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void endPlayerTurn() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        movementUsed = false;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        actionUsed = false;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        advanceActor();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public String advanceActor() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String nextActor = turnOrder.next();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (PLAYER_ACTOR.equals(nextActor)) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            endRound();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return nextActor;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void resetTurnActions() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        movementUsed = false;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        actionUsed = false;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void endRound() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        turnCount++;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isTimeUp() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return turnCount >= maxTurns;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getTurnCount() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return turnCount;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getTurnsLeft() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return Math.max(0, maxTurns - turnCount);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isMovementUsed() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return movementUsed;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isActionUsed() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return actionUsed;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
