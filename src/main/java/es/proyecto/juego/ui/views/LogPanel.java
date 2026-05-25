package es.proyecto.juego.ui.views;

import es.proyecto.juego.logica.IGameState;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class LogPanel extends VBox {
    private TextArea textArea = new TextArea();

    public LogPanel() {
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: #ECF0F1;");

        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefHeight(100);
        textArea.setPrefWidth(300);

        this.getChildren().add(textArea);
    }

    public void update(IGameState state) {
        if(state == null) return;

        // Simulación: coger el último evento y añadirlo
        String lastEvent = state.getLastEvent();
        if(lastEvent != null && !lastEvent.isEmpty()) {
            textArea.appendText("> " + lastEvent + "\n");
        }

        // En la versión final con IList<String>, iterarías la lista entera o solo los nuevos
    }
}