package es.proyecto.juego.ui.views;

import es.proyecto.juego.logica.IGameState;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

public class PlayerPanel extends VBox {
    private Label lblHpText = new Label("Vida: --/--");
    private ProgressBar pbHp = new ProgressBar(1.0);
    private Label lblStats = new Label("Atq: -- | Def: -- | Vel: --");
    private Label lblTurns = new Label("Turnos restantes: --");
    private Label lblNav = new Label("Salida a -- habs | Puerta a -- pasos");

    public PlayerPanel() {
        this.setPadding(new Insets(10));
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #ECF0F1; -fx-border-color: #BDC3C7;");

        pbHp.setPrefWidth(150);
        pbHp.setStyle("-fx-accent: #E74C3C;"); // Barra roja [cite: 80]

        Label title = new Label("ESTADO DEL JUGADOR");
        title.setStyle("-fx-font-weight: bold;");

        this.getChildren().addAll(title, lblHpText, pbHp, lblStats, lblTurns, lblNav);
    }

    public void update(IGameState state) {
        if(state == null) return;

        lblHpText.setText("Vida: " + state.getPlayerHp() + " / " + state.getPlayerMaxHp());
        pbHp.setProgress((double) state.getPlayerHp() / state.getPlayerMaxHp());

        lblStats.setText("Atq: " + state.getPlayerAttack() + " | Def: " + state.getPlayerDefense() + " | Vel: " + state.getPlayerSpeed());
        lblTurns.setText("Turnos restantes: " + state.getTurnsLeft());
        lblNav.setText("Salida a " + state.getMinRoomsToExit() + " habs | Puerta a " + state.getDistanceToNearestDoor() + " pasos"); // [cite: 187]
    }
}