// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.ui.views;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.geometry.Insets;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.TextArea;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.layout.VBox;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class LogPanel extends VBox {
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private final TextArea textArea = new TextArea();

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public LogPanel() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setPadding(new Insets(10));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setStyle("-fx-background-color: #ECF0F1;");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        textArea.setEditable(false);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        textArea.setWrapText(true);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        textArea.setPrefHeight(120);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        textArea.setPrefWidth(330);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        getChildren().add(textArea);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void update(IGameState state) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (state == null) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        textArea.clear();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IList<String> events = state.getEventLog();
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < events.size(); i++) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            textArea.appendText("> " + events.get(i) + "\n");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
