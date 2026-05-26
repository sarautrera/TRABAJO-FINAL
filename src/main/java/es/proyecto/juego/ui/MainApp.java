// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.ui;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.GameEngineImpl;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameEngine;
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
import javafx.application.Application;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.geometry.Pos;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.Scene;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.Button;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.Label;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.TextArea;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.layout.BorderPane;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.layout.HBox;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.layout.VBox;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.stage.Stage;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class MainApp extends Application {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Stage primaryStage;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private GameController controller;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private IGameEngine engine;

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void start(Stage primaryStage) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.primaryStage = primaryStage;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.engine = new GameEngineImpl();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.controller = new GameController(this, engine);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        showInicio();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void showInicio() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        VBox root = new VBox(20);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        root.setAlignment(Pos.CENTER);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        root.setStyle("-fx-background-color: #2C3E50; -fx-padding: 50;");

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Label title = new Label("DUNGEON EXPLORER 2026");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        title.setStyle("-fx-text-fill: white; -fx-font-size: 28px; -fx-font-weight: bold;");

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Button btnNewGame = new Button("Nueva Partida");
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Button btnLoadGame = new Button("Cargar Partida");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        btnNewGame.setPrefWidth(200);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        btnLoadGame.setPrefWidth(200);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        btnNewGame.setOnAction(e -> controller.onNewGameClicked());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        btnLoadGame.setOnAction(e -> controller.onLoadClicked());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        root.getChildren().addAll(title, btnNewGame, btnLoadGame);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Scene scene = new Scene(root, 800, 600);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        primaryStage.setScene(scene);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        primaryStage.setTitle("Dungeon Explorer - Inicio");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        primaryStage.show();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void showJuego() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        RoomView roomView = new RoomView();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        PlayerPanel playerPanel = new PlayerPanel();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        InventoryPanel inventoryPanel = new InventoryPanel();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        ActionPanel actionPanel = new ActionPanel();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        LogPanel logPanel = new LogPanel();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        controller.setViews(roomView, playerPanel, inventoryPanel, actionPanel, logPanel);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        roomView.setController(controller);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        inventoryPanel.setController(controller);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        actionPanel.setController(controller);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        BorderPane root = new BorderPane();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        root.setCenter(roomView);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        VBox rightPanel = new VBox(10, playerPanel, inventoryPanel);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        root.setRight(rightPanel);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        HBox bottomPanel = new HBox(10, actionPanel, logPanel);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        root.setBottom(bottomPanel);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        controller.startGame();

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Scene scene = new Scene(root, 850, 650);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        primaryStage.setScene(scene);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        primaryStage.setTitle("Dungeon Explorer - En Partida");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void showFin(String resultMessage, String completeLog) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        VBox root = new VBox(15);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        root.setAlignment(Pos.CENTER);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        root.setStyle("-fx-background-color: #34495E; -fx-padding: 30;");

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Label lblResult = new Label(resultMessage);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        lblResult.setStyle("-fx-text-fill: #E74C3C; -fx-font-size: 24px; -fx-font-weight: bold;");
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (resultMessage.contains("VICTORIA")) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            lblResult.setStyle("-fx-text-fill: #2ECC71; -fx-font-size: 24px; -fx-font-weight: bold;");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Label lblLogTitle = new Label("Historial completo de la sesion:");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        lblLogTitle.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        TextArea txtCompleteLog = new TextArea(completeLog);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        txtCompleteLog.setEditable(false);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        txtCompleteLog.setWrapText(true);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        txtCompleteLog.setPrefHeight(300);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Button btnBack = new Button("Volver al menu principal");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        btnBack.setOnAction(e -> showInicio());

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        root.getChildren().addAll(lblResult, lblLogTitle, txtCompleteLog, btnBack);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Scene scene = new Scene(root, 800, 600);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        primaryStage.setScene(scene);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        primaryStage.setTitle("Dungeon Explorer - Fin de Partida");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Stage getPrimaryStage() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return primaryStage;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public static void main(String[] args) {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        launch(args);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
