/*
 * Resumen del fichero: Dibuja la habitacion actual y permite seleccionar celdas del tablero.
 */
package es.proyecto.juego.ui.views;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.logica.mundo.Cell;
import es.proyecto.juego.logica.mundo.CellType;
import es.proyecto.juego.logica.mundo.Room;
import es.proyecto.juego.ui.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class RoomView extends GridPane {
    private GameController controller;

    public RoomView() {
        setAlignment(Pos.CENTER);
        setHgap(2);
        setVgap(2);
        setStyle("-fx-background-color: #1F2933; -fx-padding: 16;");
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void update(IGameState state) {
        getChildren().clear();
        if (state == null) {
            return;
        }

        Room room = state.getCurrentRoom();
        int rows = state.getCurrentRoomRows();
        int cols = state.getCurrentRoomCols();
        IList<int[]> reachable = controller == null ? null : controller.getReachableCells();
        IList<int[]> attackTargets = controller == null ? null : controller.getAttackTargets();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                StackPane cellPane = createCellPane(state, room, reachable, attackTargets, row, col);
                final int selectedRow = row;
                final int selectedCol = col;
                cellPane.setOnMouseClicked(e -> {
                    if (controller != null) {
                        controller.onCellClicked(selectedRow, selectedCol);
                    }
                });
                add(cellPane, col, row);
            }
        }
    }

    private StackPane createCellPane(IGameState state, Room room, IList<int[]> reachable,
                                     IList<int[]> attackTargets, int row, int col) {
        StackPane cellPane = new StackPane();
        cellPane.setPrefSize(58, 58);
        cellPane.setMinSize(58, 58);
        cellPane.setMaxSize(58, 58);

        Cell cell = room == null ? null : room.getCell(row, col);
        String background = colorFor(cell);
        String border = "#31404F";
        String borderWidth = "1";
        String symbol = symbolFor(cell);

        if (row == state.getPlayerRow() && col == state.getPlayerCol()) {
            background = "#2F80ED";
            symbol = "@";
        } else if (containsCell(attackTargets, row, col)) {
            border = "#E74C3C";
            borderWidth = "4";
        } else if (containsCell(reachable, row, col)) {
            border = "#F2C94C";
            borderWidth = "3";
        }

        Label label = new Label(symbol);
        label.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        cellPane.setStyle("-fx-background-color: " + background
                + "; -fx-border-color: " + border
                + "; -fx-border-width: " + borderWidth + ";");
        cellPane.getChildren().add(label);
        return cellPane;
    }

    private String colorFor(Cell cell) {
        if (cell == null) {
            return "#E8E8E8";
        }
        CellType type = cell.getType();
        if (type == CellType.WALL) {
            return "#2C3E50";
        }
        if (type == CellType.ENEMY) {
            return "#C0392B";
        }
        if (type == CellType.ITEM) {
            return "#27AE60";
        }
        if (type == CellType.DOOR) {
            return cell.isExteriorExit() ? "#8E44AD" : "#A97142";
        }
        if (type == CellType.TRAP) {
            return "#D35400";
        }
        return "#ECF0F1";
    }

    private String symbolFor(Cell cell) {
        if (cell == null) {
            return "";
        }
        CellType type = cell.getType();
        if (type == CellType.WALL) {
            return "#";
        }
        if (type == CellType.ENEMY) {
            return "E";
        }
        if (type == CellType.ITEM) {
            return "I";
        }
        if (type == CellType.DOOR) {
            return cell.isExteriorExit() ? "X" : "D";
        }
        if (type == CellType.TRAP) {
            return "!";
        }
        return "";
    }

    private boolean containsCell(IList<int[]> cells, int row, int col) {
        if (cells == null) {
            return false;
        }
        for (int i = 0; i < cells.size(); i++) {
            int[] cell = cells.get(i);
            if (cell[0] == row && cell[1] == col) {
                return true;
            }
        }
        return false;
    }
}
