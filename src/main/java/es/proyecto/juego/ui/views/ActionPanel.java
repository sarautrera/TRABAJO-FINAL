// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.ui.views;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.ui.GameController;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.geometry.Insets;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.Button;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.layout.HBox;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class ActionPanel extends HBox {
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private final Button btnEndTurn = new Button("Fin Turno");
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private final Button btnSave = new Button("Guardar");
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private final Button btnLoad = new Button("Cargar");
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private final Button btnNewGame = new Button("Nueva Partida");
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private GameController controller;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public ActionPanel() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setPadding(new Insets(10));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setSpacing(15);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setStyle("-fx-background-color: #BDC3C7;");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        btnEndTurn.setOnAction(e -> {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (controller != null) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                controller.onEndTurn();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        btnSave.setOnAction(e -> {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (controller != null) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                controller.onSaveClicked();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        btnLoad.setOnAction(e -> {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (controller != null) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                controller.onLoadClicked();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        btnNewGame.setOnAction(e -> {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (controller != null) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                controller.onNewGameClicked();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        getChildren().addAll(btnEndTurn, btnSave, btnLoad, btnNewGame);
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
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        boolean noGame = state == null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        boolean gameOver = state != null && state.isGameOver();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        btnEndTurn.setDisable(noGame || gameOver);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        btnSave.setDisable(noGame);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
