// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.ui;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameEngine;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Cell;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Room;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.ui.views.ActionPanel;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.ui.views.InventoryPanel;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.ui.views.LogPanel;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.ui.views.PlayerPanel;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.ui.views.RoomView;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.Alert;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.stage.FileChooser;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.File;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class GameController {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final MainApp mainApp;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private final IGameEngine engine;

    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private RoomView roomView;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private PlayerPanel playerPanel;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private InventoryPanel inventoryPanel;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private ActionPanel actionPanel;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private LogPanel logPanel;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public GameController(MainApp mainApp, IGameEngine engine) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.mainApp = mainApp;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.engine = engine;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    public void setViews(RoomView roomView, PlayerPanel playerPanel, InventoryPanel inventoryPanel,
                         // Comentario de estudiante: aqui se prepara una instruccion del programa.
                         ActionPanel actionPanel, LogPanel logPanel) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.roomView = roomView;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.playerPanel = playerPanel;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.inventoryPanel = inventoryPanel;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.actionPanel = actionPanel;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.logPanel = logPanel;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void startGame() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        refreshAll();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void onNewGameClicked() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        FileChooser fileChooser = new FileChooser();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        fileChooser.setTitle("Seleccionar configuracion de nivel");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos JSON (*.json)", "*.json"));

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File selectedFile = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (selectedFile != null) {
            // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
            try {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.loadConfig(selectedFile.getAbsolutePath());
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                mainApp.showJuego();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } catch (Exception ex) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                showGraphicError("Error de nivel", "No se pudo cargar el mapa", ex.getMessage());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void onLoadClicked() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        FileChooser fileChooser = new FileChooser();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        fileChooser.setTitle("Seleccionar partida guardada");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos JSON (*.json)", "*.json"));

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File selectedFile = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (selectedFile != null) {
            // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
            try {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.loadGame(selectedFile.getAbsolutePath());
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                mainApp.showJuego();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } catch (Exception ex) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                showGraphicError("Error de archivo", "No se pudo cargar la partida", ex.getMessage());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void onSaveClicked() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        FileChooser fileChooser = new FileChooser();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        fileChooser.setTitle("Guardar estado actual");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos JSON (*.json)", "*.json"));

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        File selectedFile = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (selectedFile != null) {
            // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
            try {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.saveGame(selectedFile.getAbsolutePath());
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                refreshAll();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } catch (Exception ex) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                showGraphicError("Error al guardar", "No se pudo escribir en el disco", ex.getMessage());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void onUseItemClicked(int index) {
        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (!engine.useItem(index)) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                showGraphicError("Accion invalida", "Uso de objeto fallido",
                        // Comentario de estudiante: aqui se prepara una instruccion del programa.
                        "El objeto no cumple los requisitos para ser usado ahora.");
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            refreshAll();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            checkGameStatus();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } catch (Exception ex) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            showGraphicError("Accion invalida", "No se pudo usar el objeto", ex.getMessage());
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void onCellClicked(int row, int col) {
        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (containsCell(engine.getAttackTargets(), row, col)) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                engine.attack(row, col);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } else {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                IGameState state = engine.getState();
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                Room room = state.getCurrentRoom();
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                Cell cell = room.getCell(row, col);
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (cell.isDoor()) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    engine.openDoor(row, col);
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                } else if (cell.hasItem()) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    engine.pickItem(row, col);
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                } else if (containsCell(engine.getReachableCells(), row, col)) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    engine.movePlayer(row, col);
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                } else {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    showGraphicError("Accion invalida", "Celda no disponible",
                            // Comentario de estudiante: aqui se prepara una instruccion del programa.
                            "La celda seleccionada no es alcanzable ni contiene una accion valida.");
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            refreshAll();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            checkGameStatus();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } catch (Exception ex) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            showGraphicError("Accion invalida", "No se pudo ejecutar la accion", ex.getMessage());
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void onEndTurn() {
        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            engine.endTurn();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            refreshAll();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            checkGameStatus();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } catch (Exception ex) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            showGraphicError("Accion invalida", "No se pudo terminar el turno", ex.getMessage());
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<int[]> getReachableCells() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return engine.getReachableCells();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<int[]> getAttackTargets() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return engine.getAttackTargets();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void refreshAll() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IGameState state = engine.getState();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (state == null) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        roomView.update(state);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        playerPanel.update(state);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        inventoryPanel.update(state);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        actionPanel.update(state);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        logPanel.update(state);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void checkGameStatus() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IGameState state = engine.getState();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (state == null || !state.isGameOver()) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String message = state.isVictory() ? "VICTORIA ABSOLUTA" : "DERROTA";
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StringBuilder log = new StringBuilder();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        log.append("--- REGISTRO COMPLETO DE LA SESION ---\n");
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IList<String> events = state.getEventLog();
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < events.size(); i++) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            log.append("- ").append(events.get(i)).append("\n");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        mainApp.showFin(message, log.toString());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private boolean containsCell(IList<int[]> cells, int row, int col) {
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

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private void showGraphicError(String title, String header, String content) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Alert alert = new Alert(Alert.AlertType.ERROR);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        alert.setTitle(title);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        alert.setHeaderText(header);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        alert.setContentText(content);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        alert.showAndWait();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
