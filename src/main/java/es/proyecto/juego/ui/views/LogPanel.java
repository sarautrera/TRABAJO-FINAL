/*
 * Resumen del fichero: Muestra el registro de eventos de la partida.
 */
package es.proyecto.juego.ui.views;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.logica.IGameState;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class LogPanel extends VBox {
    private final TextArea textArea = new TextArea();

    public LogPanel() {
        setPadding(new Insets(10));
        setStyle("-fx-background-color: #ECF0F1;");

        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefHeight(120);
        textArea.setPrefWidth(330);

        getChildren().add(textArea);
    }

    public void update(IGameState state) {
        if (state == null) {
            return;
        }

        textArea.clear();
        IList<String> events = state.getEventLog();
        for (int i = 0; i < events.size(); i++) {
            textArea.appendText("> " + events.get(i) + "\n");
        }
    }
}
