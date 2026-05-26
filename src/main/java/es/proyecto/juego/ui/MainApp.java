package es.proyecto.juego.ui;

import es.proyecto.juego.ui.views.*;
import es.proyecto.juego.logica.IGameEngine;
import es.proyecto.juego.logica.GameEngineImpl; // Se usará en la integración final con el Track B
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;
    private GameController controller;
    private IGameEngine engine; // Declarado por la interfaz (Polimorfismo puro)

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // =====================================================================
        // LA PRUEBA DEL ÁCIDO: INTEGRACIÓN FINAL
        // =====================================================================
        this.engine = new MockGameEngine();       // FASE DE DESARROLLO (UI aislada)
        this.engine = new GameEngineImpl();     // FASE DE ENTREGA (Sustitución final)
        // =====================================================================

        // Inicializar el controlador base pasando la abstracción del motor
        this.controller = new GameController(this, engine);

        // Mostrar pantalla inicial al arrancar
        showInicio();
    }

    // PANTALLA 1: Menú de Inicio
    public void showInicio() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #2C3E50; -fx-padding: 50;");

        Label title = new Label("DUNGEON EXPLORER 2026");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 28px; -fx-font-weight: bold;");

        Button btnNewGame = new Button("Nueva Partida");
        Button btnLoadGame = new Button("Cargar Partida");
        btnNewGame.setPrefWidth(200);
        btnLoadGame.setPrefWidth(200);

        // Vinculación corregida usando el flujo del controlador para inyectar configuraciones
        btnNewGame.setOnAction(e -> controller.onNewGameClicked());
        btnLoadGame.setOnAction(e -> controller.onLoadClicked());

        root.getChildren().addAll(title, btnNewGame, btnLoadGame);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dungeon Explorer - Inicio");
        primaryStage.show();
    }

    // PANTALLA 2: Escena Principal de Juego
    public void showJuego() {
        RoomView roomView = new RoomView();
        PlayerPanel playerPanel = new PlayerPanel();
        InventoryPanel inventoryPanel = new InventoryPanel();
        ActionPanel actionPanel = new ActionPanel();
        LogPanel logPanel = new LogPanel();

        // Vincular vistas al controlador
        controller.setViews(roomView, playerPanel, inventoryPanel, actionPanel, logPanel);
        roomView.setController(controller);
        inventoryPanel.setController(controller);
        actionPanel.setController(controller);

        BorderPane root = new BorderPane();
        root.setCenter(roomView);

        VBox rightPanel = new VBox(10, playerPanel, inventoryPanel);
        root.setRight(rightPanel);

        javafx.scene.layout.HBox bottomPanel = new javafx.scene.layout.HBox(10, actionPanel, logPanel);
        root.setBottom(bottomPanel);

        controller.startGame(); // Fuerza el primer pintado refreshAll()

        Scene scene = new Scene(root, 850, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dungeon Explorer - En Partida");
    }

    // PANTALLA 3: Fin de Partida (Muestra el Log Completo)
    public void showFin(String mensajeResultado, String logCompleto) {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #34495E; -fx-padding: 30;");

        Label lblResultado = new Label(mensajeResultado);
        lblResultado.setStyle("-fx-text-fill: #E74C3C; -fx-font-size: 24px; -fx-font-weight: bold;");
        if (mensajeResultado.contains("VICTORIA")) {
            lblResultado.setStyle("-fx-text-fill: #2ECC71; -fx-font-size: 24px; -fx-font-weight: bold;");
        }

        Label lblLogTitle = new Label("Historial Completo de la Sesión:");
        lblLogTitle.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        TextArea txtLogCompleto = new TextArea(logCompleto);
        txtLogCompleto.setEditable(false);
        txtLogCompleto.setWrapText(true);
        txtLogCompleto.setPrefHeight(300);

        Button btnVolver = new Button("Volver al Menú Principal");
        btnVolver.setOnAction(e -> showInicio());

        root.getChildren().addAll(lblResultado, lblLogTitle, txtLogCompleto, btnVolver);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dungeon Explorer - Fin de Partida");
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}