package es.proyecto.juego.ui.views;

import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.ui.GameController;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;

public class RoomView extends GridPane {
    private GameController controller;

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void update(IGameState state) {
        this.getChildren().clear(); // Limpia la vista anterior

        // Simulación: Asumimos una matriz 5x5 para el mock.
        // En la versión real usarías state.getCurrentRoom().getFilas()
        int filas = 5;
        int columnas = 5;

        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                StackPane cellPane = new StackPane();
                cellPane.setPrefSize(60, 60);

                // Configuración básica de color por defecto (Vacío)
                String bgColor = "#E8E8E8"; // Gris claro [cite: 80]
                Label lbl = new Label("");

                // Si es el jugador
                if (r == state.getPlayerRow() && c == state.getPlayerCol()) {
                    bgColor = "#4A90D9"; // Azul [cite: 80]
                    lbl.setText("@");
                }
                // Añade aquí más if-else para Enemigos (#E74C3C), Muros (#2C3E50), etc.

                String style = "-fx-background-color: " + bgColor + "; -fx-border-color: #BDC3C7;";

                // Requisito: Resaltar celdas alcanzables en amarillo grueso [cite: 80, 188]
                // IList<int[]> alcanzables = state.getReachableCells();
                // if (isInList(alcanzables, r, c)) {
                //    style += "-fx-border-color: #D4AC0D; -fx-border-width: 3px;";
                // }

                cellPane.setStyle(style);
                cellPane.getChildren().add(lbl);

                // Capturar el click de la celda [cite: 158]
                final int finalR = r;
                final int finalC = c;
                cellPane.setOnMouseClicked(e -> {
                    if(controller != null) controller.onCellClicked(finalR, finalC);
                });

                this.add(cellPane, c, r); // GridPane de JavaFX usa (columna, fila)
            }
        }
    }
}
