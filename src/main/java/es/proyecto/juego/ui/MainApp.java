/*
 * Resumen del fichero: Arranca la aplicacion JavaFX y construye las pantallas principales del juego.
 */
package es.proyecto.juego.ui;

import es.proyecto.juego.logica.GameEngineImpl;
import es.proyecto.juego.logica.IGameEngine;
import es.proyecto.juego.ui.views.ActionPanel;
import es.proyecto.juego.ui.views.InventoryPanel;
import es.proyecto.juego.ui.views.LogPanel;
import es.proyecto.juego.ui.views.PlayerPanel;
import es.proyecto.juego.ui.views.RoomView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;
    private GameController controller;
    private IGameEngine engine;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.engine = new GameEngineImpl();
        this.controller = new GameController(this, engine);
        showInicio();
    }

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
        btnNewGame.setOnAction(e -> controller.onNewGameClicked());
        btnLoadGame.setOnAction(e -> controller.onLoadClicked());

        root.getChildren().addAll(title, btnNewGame, btnLoadGame);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dungeon Explorer - Inicio");
        primaryStage.show();
    }

    public void showJuego() {
        RoomView roomView = new RoomView();
        PlayerPanel playerPanel = new PlayerPanel();
        InventoryPanel inventoryPanel = new InventoryPanel();
        ActionPanel actionPanel = new ActionPanel();
        LogPanel logPanel = new LogPanel();

        controller.setViews(roomView, playerPanel, inventoryPanel, actionPanel, logPanel);
        roomView.setController(controller);
        inventoryPanel.setController(controller);
        actionPanel.setController(controller);

        BorderPane root = new BorderPane();
        root.setCenter(roomView);

        VBox rightPanel = new VBox(10, playerPanel, inventoryPanel);
        root.setRight(rightPanel);

        HBox bottomPanel = new HBox(10, actionPanel, logPanel);
        root.setBottom(bottomPanel);

        controller.startGame();

        Scene scene = new Scene(root, 850, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dungeon Explorer - En Partida");
    }

    public void showFin(String resultMessage, String completeLog) {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #34495E; -fx-padding: 30;");

        Label lblResult = new Label(resultMessage);
        lblResult.setStyle("-fx-text-fill: #E74C3C; -fx-font-size: 24px; -fx-font-weight: bold;");
        if (resultMessage.contains("VICTORIA")) {
            lblResult.setStyle("-fx-text-fill: #2ECC71; -fx-font-size: 24px; -fx-font-weight: bold;");
        }

        Label lblLogTitle = new Label("Historial completo de la sesion:");
        lblLogTitle.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        TextArea txtCompleteLog = new TextArea(completeLog);
        txtCompleteLog.setEditable(false);
        txtCompleteLog.setWrapText(true);
        txtCompleteLog.setPrefHeight(300);

        Button btnBack = new Button("Volver al menu principal");
        btnBack.setOnAction(e -> showInicio());

        root.getChildren().addAll(lblResult, lblLogTitle, txtCompleteLog, btnBack);

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
