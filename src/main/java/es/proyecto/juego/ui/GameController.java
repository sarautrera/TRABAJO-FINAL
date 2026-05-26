/*
 * Resumen del fichero: Conecta los botones y celdas de la interfaz con las acciones del motor de juego.
 */
package es.proyecto.juego.ui;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.logica.IGameEngine;
import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.logica.mundo.Cell;
import es.proyecto.juego.logica.mundo.Room;
import es.proyecto.juego.ui.views.ActionPanel;
import es.proyecto.juego.ui.views.InventoryPanel;
import es.proyecto.juego.ui.views.LogPanel;
import es.proyecto.juego.ui.views.PlayerPanel;
import es.proyecto.juego.ui.views.RoomView;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;

public class GameController {
    private final MainApp mainApp;
    private final IGameEngine engine;

    private RoomView roomView;
    private PlayerPanel playerPanel;
    private InventoryPanel inventoryPanel;
    private ActionPanel actionPanel;
    private LogPanel logPanel;

    public GameController(MainApp mainApp, IGameEngine engine) {
        this.mainApp = mainApp;
        this.engine = engine;
    }

    public void setViews(RoomView roomView, PlayerPanel playerPanel, InventoryPanel inventoryPanel,
                         ActionPanel actionPanel, LogPanel logPanel) {
        this.roomView = roomView;
        this.playerPanel = playerPanel;
        this.inventoryPanel = inventoryPanel;
        this.actionPanel = actionPanel;
        this.logPanel = logPanel;
    }

    public void startGame() {
        refreshAll();
    }

    public void onNewGameClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar configuracion de nivel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos JSON (*.json)", "*.json"));

        File selectedFile = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (selectedFile != null) {
            try {
                engine.loadConfig(selectedFile.getAbsolutePath());
                mainApp.showJuego();
            } catch (Exception ex) {
                showGraphicError("Error de nivel", "No se pudo cargar el mapa", ex.getMessage());
            }
        }
    }

    public void onLoadClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar partida guardada");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos JSON (*.json)", "*.json"));

        File selectedFile = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (selectedFile != null) {
            try {
                engine.loadGame(selectedFile.getAbsolutePath());
                mainApp.showJuego();
            } catch (Exception ex) {
                showGraphicError("Error de archivo", "No se pudo cargar la partida", ex.getMessage());
            }
        }
    }

    public void onSaveClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar estado actual");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos JSON (*.json)", "*.json"));

        File selectedFile = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        if (selectedFile != null) {
            try {
                engine.saveGame(selectedFile.getAbsolutePath());
                refreshAll();
            } catch (Exception ex) {
                showGraphicError("Error al guardar", "No se pudo escribir en el disco", ex.getMessage());
            }
        }
    }

    public void onUseItemClicked(int index) {
        try {
            if (!engine.useItem(index)) {
                showGraphicError("Accion invalida", "Uso de objeto fallido",
                        "El objeto no cumple los requisitos para ser usado ahora.");
            }
            refreshAll();
            checkGameStatus();
        } catch (Exception ex) {
            showGraphicError("Accion invalida", "No se pudo usar el objeto", ex.getMessage());
        }
    }

    public void onCellClicked(int row, int col) {
        try {
            // El orden importa: atacar tiene prioridad visual sobre moverse a una celda alcanzable.
            if (containsCell(engine.getAttackTargets(), row, col)) {
                engine.attack(row, col);
            } else {
                IGameState state = engine.getState();
                Room room = state.getCurrentRoom();
                Cell cell = room.getCell(row, col);
                if (cell.isDoor()) {
                    engine.openDoor(row, col);
                } else if (cell.hasItem()) {
                    engine.pickItem(row, col);
                } else if (containsCell(engine.getReachableCells(), row, col)) {
                    engine.movePlayer(row, col);
                } else {
                    showGraphicError("Accion invalida", "Celda no disponible",
                            "La celda seleccionada no es alcanzable ni contiene una accion valida.");
                }
            }
            refreshAll();
            checkGameStatus();
        } catch (Exception ex) {
            showGraphicError("Accion invalida", "No se pudo ejecutar la accion", ex.getMessage());
        }
    }

    public void onEndTurn() {
        try {
            engine.endTurn();
            refreshAll();
            checkGameStatus();
        } catch (Exception ex) {
            showGraphicError("Accion invalida", "No se pudo terminar el turno", ex.getMessage());
        }
    }

    public IList<int[]> getReachableCells() {
        return engine.getReachableCells();
    }

    public IList<int[]> getAttackTargets() {
        return engine.getAttackTargets();
    }

    private void refreshAll() {
        IGameState state = engine.getState();
        if (state == null) {
            return;
        }

        roomView.update(state);
        playerPanel.update(state);
        inventoryPanel.update(state);
        actionPanel.update(state);
        logPanel.update(state);
    }

    private void checkGameStatus() {
        IGameState state = engine.getState();
        if (state == null || !state.isGameOver()) {
            return;
        }

        // Al terminar la partida se muestra el historial completo para justificar el resultado.
        String message = state.isVictory() ? "VICTORIA ABSOLUTA" : "DERROTA";
        StringBuilder log = new StringBuilder();
        log.append("--- REGISTRO COMPLETO DE LA SESION ---\n");
        IList<String> events = state.getEventLog();
        for (int i = 0; i < events.size(); i++) {
            log.append("- ").append(events.get(i)).append("\n");
        }
        mainApp.showFin(message, log.toString());
    }

    private boolean containsCell(IList<int[]> cells, int row, int col) {
        for (int i = 0; i < cells.size(); i++) {
            int[] cell = cells.get(i);
            if (cell[0] == row && cell[1] == col) {
                return true;
            }
        }
        return false;
    }

    private void showGraphicError(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
