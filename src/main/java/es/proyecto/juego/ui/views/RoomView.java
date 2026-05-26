// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.ui.views;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Cell;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.CellType;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Room;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.ui.GameController;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.geometry.Pos;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.Label;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.layout.GridPane;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.layout.StackPane;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class RoomView extends GridPane {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private GameController controller;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public RoomView() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setAlignment(Pos.CENTER);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setHgap(2);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setVgap(2);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setStyle("-fx-background-color: #1F2933; -fx-padding: 16;");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void setController(GameController controller) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.controller = controller;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void update(IGameState state) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        getChildren().clear();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (state == null) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Room room = state.getCurrentRoom();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int rows = state.getCurrentRoomRows();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int cols = state.getCurrentRoomCols();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IList<int[]> reachable = controller == null ? null : controller.getReachableCells();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IList<int[]> attackTargets = controller == null ? null : controller.getAttackTargets();

        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int row = 0; row < rows; row++) {
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int col = 0; col < cols; col++) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                StackPane cellPane = createCellPane(state, room, reachable, attackTargets, row, col);
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                final int selectedRow = row;
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                final int selectedCol = col;
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                cellPane.setOnMouseClicked(e -> {
                    // Comentario de estudiante: aqui se comprueba una condicion con if.
                    if (controller != null) {
                        // Comentario de estudiante: aqui se prepara una instruccion del programa.
                        controller.onCellClicked(selectedRow, selectedCol);
                    // Comentario de estudiante: aqui se cierra un bloque de codigo.
                    }
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                });
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                add(cellPane, col, row);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    private StackPane createCellPane(IGameState state, Room room, IList<int[]> reachable,
                                     // Comentario de estudiante: aqui se prepara una instruccion del programa.
                                     IList<int[]> attackTargets, int row, int col) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StackPane cellPane = new StackPane();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cellPane.setPrefSize(58, 58);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cellPane.setMinSize(58, 58);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cellPane.setMaxSize(58, 58);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Cell cell = room == null ? null : room.getCell(row, col);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String background = colorFor(cell);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String border = "#31404F";
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String borderWidth = "1";
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String symbol = symbolFor(cell);

        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (row == state.getPlayerRow() && col == state.getPlayerCol()) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            background = "#2F80ED";
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            symbol = "@";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } else if (containsCell(attackTargets, row, col)) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            border = "#E74C3C";
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            borderWidth = "4";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } else if (containsCell(reachable, row, col)) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            border = "#F2C94C";
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            borderWidth = "3";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Label label = new Label(symbol);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        label.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cellPane.setStyle("-fx-background-color: " + background
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                + "; -fx-border-color: " + border
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                + "; -fx-border-width: " + borderWidth + ";");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cellPane.getChildren().add(label);
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return cellPane;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private String colorFor(Cell cell) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (cell == null) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "#E8E8E8";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        CellType type = cell.getType();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type == CellType.WALL) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "#2C3E50";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type == CellType.ENEMY) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "#C0392B";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type == CellType.ITEM) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "#27AE60";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type == CellType.DOOR) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return cell.isExteriorExit() ? "#8E44AD" : "#A97142";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type == CellType.TRAP) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "#D35400";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return "#ECF0F1";
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private String symbolFor(Cell cell) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (cell == null) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        CellType type = cell.getType();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type == CellType.WALL) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "#";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type == CellType.ENEMY) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "E";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type == CellType.ITEM) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "I";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type == CellType.DOOR) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return cell.isExteriorExit() ? "X" : "D";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (type == CellType.TRAP) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "!";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return "";
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private boolean containsCell(IList<int[]> cells, int row, int col) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (cells == null) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return false;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < cells.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int[] cell = cells.get(i);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (cell[0] == row && cell[1] == col) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return true;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return false;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
