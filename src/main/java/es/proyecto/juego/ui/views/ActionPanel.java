/*
 * Resumen del fichero: Construye el panel de acciones disponibles para el jugador.
 */
package es.proyecto.juego.ui.views;

import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.ui.GameController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ActionPanel extends HBox {
    private final Button btnEndTurn = new Button("Fin Turno");
    private final Button btnSave = new Button("Guardar");
    private final Button btnLoad = new Button("Cargar");
    private final Button btnNewGame = new Button("Nueva Partida");
    private GameController controller;

    public ActionPanel() {
        setPadding(new Insets(10));
        setSpacing(15);
        setStyle("-fx-background-color: #BDC3C7;");

        btnEndTurn.setOnAction(e -> {
            if (controller != null) {
                controller.onEndTurn();
            }
        });
        btnSave.setOnAction(e -> {
            if (controller != null) {
                controller.onSaveClicked();
            }
        });
        btnLoad.setOnAction(e -> {
            if (controller != null) {
                controller.onLoadClicked();
            }
        });
        btnNewGame.setOnAction(e -> {
            if (controller != null) {
                controller.onNewGameClicked();
            }
        });

        getChildren().addAll(btnEndTurn, btnSave, btnLoad, btnNewGame);
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void update(IGameState state) {
        boolean noGame = state == null;
        boolean gameOver = state != null && state.isGameOver();
        btnEndTurn.setDisable(noGame || gameOver);
        btnSave.setDisable(noGame);
    }
}
