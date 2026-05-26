package es.proyecto.juego.ui.views;

import es.proyecto.juego.ui.GameController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ActionPanel extends HBox {
    private GameController controller;

    public ActionPanel() {
        this.setPadding(new Insets(10));
        this.setSpacing(15);
        this.setStyle("-fx-background-color: #BDC3C7;");

        Button btnEndTurn = new Button("Fin Turno");
        Button btnSave = new Button("Guardar");
        Button btnLoad = new Button("Cargar");
        Button btnNewGame = new Button("Nueva Partida");

        btnEndTurn.setOnAction(e -> {
            if(controller != null) controller.onEndTurn();
        });

        // Aquí conectarías los botones de guardar y cargar al controlador y luego al FileChooser de JavaFX [cite: 167]
        btnSave.setOnAction(e -> System.out.println("Abrir FileChooser Guardar"));
        btnLoad.setOnAction(e -> System.out.println("Abrir FileChooser Cargar"));

        this.getChildren().addAll(btnEndTurn, btnSave, btnLoad, btnNewGame);
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void update(es.proyecto.juego.logica.IGameState state) {
        // Podrías deshabilitar "Fin Turno" si isGameOver() es true
    }
}