// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.ui.views;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.geometry.Insets;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.Label;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.ProgressBar;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.layout.VBox;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class PlayerPanel extends VBox {
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private Label lblHpText = new Label("Vida: --/--");
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private ProgressBar pbHp = new ProgressBar(1.0);
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private Label lblStats = new Label("Atq: -- | Def: -- | Vel: --");
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private Label lblTurns = new Label("Turnos restantes: --");
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private Label lblNav = new Label("Salida a -- habs | Puerta a -- pasos");

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public PlayerPanel() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        this.setPadding(new Insets(10));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        this.setSpacing(10);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        this.setStyle("-fx-background-color: #ECF0F1; -fx-border-color: #BDC3C7;");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        pbHp.setPrefWidth(150);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        pbHp.setStyle("-fx-accent: #E74C3C;");

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Label title = new Label("ESTADO DEL JUGADOR");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        title.setStyle("-fx-font-weight: bold;");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        this.getChildren().addAll(title, lblHpText, pbHp, lblStats, lblTurns, lblNav);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void update(IGameState state) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if(state == null) return;

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        lblHpText.setText("Vida: " + state.getPlayerHp() + " / " + state.getPlayerMaxHp());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        pbHp.setProgress((double) state.getPlayerHp() / state.getPlayerMaxHp());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        lblStats.setText("Atq: " + state.getPlayerAttack() + " | Def: " + state.getPlayerDefense() + " | Vel: " + state.getPlayerSpeed());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        lblTurns.setText("Turnos restantes: " + state.getTurnsLeft());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        lblNav.setText("Salida a " + state.getMinRoomsToExit() + " habs | Puerta a " + state.getDistanceToNearestDoor() + " pasos");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
