package es.proyecto.juego.ui;

import es.proyecto.juego.logica.IGameEngine;
import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.ui.views.*;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import java.io.File;

public class GameController {
    private MainApp mainApp;
    private IGameEngine engine;

    private RoomView roomView;
    private PlayerPanel playerPanel;
    private InventoryPanel inventoryPanel;
    private ActionPanel actionPanel;
    private LogPanel logPanel;

    // Constructor unificado que conecta el controlador con la aplicación principal y el motor
    public GameController(MainApp mainApp, IGameEngine engine) {
        this.mainApp = mainApp;
        this.engine = engine;
    }

    public void setViews(RoomView rv, PlayerPanel pp, InventoryPanel ip, ActionPanel ap, LogPanel lp) {
        this.roomView = rv;
        this.playerPanel = pp;
        this.inventoryPanel = ip;
        this.actionPanel = ap;
        this.logPanel = lp;
    }

    public void startGame() {
        engine.newGame();
        refreshAll();
    }

    // Flujo correcto: Seleccionar mapa JSON mediante FileChooser e iniciar juego
    public void onNewGameClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Configuración de Nivel (JSON)");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos JSON (*.json)", "*.json"));

        File selectedFile = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (selectedFile != null) {
            try {
                engine.loadConfig(selectedFile.getAbsolutePath());
                engine.newGame();
                mainApp.showJuego(); // Carga la escena tras inicializar el motor con éxito
            } catch (Exception ex) {
                showGraphicError("Error de Nivel", "No se pudo cargar el mapa", ex.getMessage());
            }
        }
    }

    // Requisito: Uso de FileChooser nativo para Cargar Partida
    public void onLoadClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Partida Guardada JSON");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos JSON (*.json)", "*.json"));

        File selectedFile = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (selectedFile != null) {
            try {
                engine.loadGame(selectedFile.getAbsolutePath());
                mainApp.showJuego();
            } catch (Exception ex) {
                showGraphicError("Error de Archivo", "No se pudo cargar la partida", ex.getMessage());
            }
        }
    }

    // Requisito: Guardar Partida con FileChooser
    public void onSaveClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Estado Actual");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos JSON (*.json)", "*.json"));

        File selectedFile = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        if (selectedFile != null) {
            try {
                engine.saveGame(selectedFile.getAbsolutePath());
                refreshAll();
            } catch (Exception ex) {
                showGraphicError("Error al Guardar", "No se pudo escribir en el disco", ex.getMessage());
            }
        }
    }

    // Requisito obligatorio: Ejecutar acciones sobre ítems sin salir de la pantalla principal
    public void onUseItemClicked(int index) {
        try {
            boolean success = engine.useItem(index);
            if (!success) {
                showGraphicError("Acción Inválida", "Uso de objeto fallido", "El objeto no cumple los requisitos para ser usado ahora.");
            }
            refreshAll();
        } catch (IndexOutOfBoundsException ex) {
            showGraphicError("Error de Selección", "Índice de inventario corrupto", ex.getMessage());
        }
    }

    public void onCellClicked(int row, int col) {
        boolean moved = engine.movePlayer(row, col);
        if (!moved) {
            // Se puede capturar silenciosamente o emitir un sonido si choca con una pared
        }
        refreshAll();
        checkGameStatus();
    }

    public void onEndTurn() {
        engine.endTurn();
        refreshAll();
        checkGameStatus();
    }

    private void refreshAll() {
        IGameState state = engine.getState();
        if (state == null) return;

        roomView.update(state);
        playerPanel.update(state);
        inventoryPanel.update(state);
        actionPanel.update(state);
        logPanel.update(state);
    }

    // Requisito: Detectar fin y saltar a la Pantalla de Fin volcando el log acumulado en el IList propio
    private void checkGameStatus() {
        IGameState state = engine.getState();
        if (state == null) return;

        if (state.isGameOver()) {
            String mensaje = state.isVictory() ? "¡VICTORIA ABSOLUTA!" : "DEFEAT: Te has quedado sin turnos o sin vida.";

            StringBuilder sb = new StringBuilder();
            sb.append("--- REGISTRO COMPLETO DE LA SESIÓN ---\n");

            IList<String> eventLog = state.getEventLog();
            if (eventLog != null) {
                // Iteración pura compatible con for-each gracias a que IList hereda de Iterable,
                // usando el iterador cualificado internamente en tu StubList sin hacer imports
                for (String evento : eventLog) {
                    sb.append("- ").append(evento).append("\n");
                }
            } else {
                sb.append("Última acción registrada: ").append(state.getLastEvent());
            }

            mainApp.showFin(mensaje, sb.toString());
        }
    }

    // Requisito de Usabilidad: Centralización de alertas gráficas sin trazas en la consola
    private void showGraphicError(String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}