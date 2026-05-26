// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.ui.views;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Item;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.ui.GameController;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.geometry.Insets;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.Button;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.Label;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.ListView;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.layout.VBox;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class InventoryPanel extends VBox {
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private final ListView<String> listView = new ListView<>();
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private final Button btnUse = new Button("Usar / Equipar");
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private GameController controller;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public InventoryPanel() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setPadding(new Insets(10));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setSpacing(10);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        setStyle("-fx-background-color: #ECF0F1; -fx-border-color: #BDC3C7;");

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Label title = new Label("INVENTARIO");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        title.setStyle("-fx-font-weight: bold;");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        listView.setPrefHeight(150);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        btnUse.setOnAction(e -> {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (selectedIndex >= 0 && controller != null && !listView.getItems().isEmpty()) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                String selected = listView.getItems().get(selectedIndex);
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (!"Inventario vacio".equals(selected)) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    controller.onUseItemClicked(selectedIndex);
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        getChildren().addAll(title, listView, btnUse);
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
        listView.getItems().clear();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (state == null) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            btnUse.setDisable(true);
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IList<Item> inventory = state.getInventory();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (inventory == null || inventory.isEmpty()) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            listView.getItems().add("Inventario vacio");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            btnUse.setDisable(true);
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < inventory.size(); i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Item item = inventory.get(i);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (item != null) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                listView.getItems().add("[" + i + "] " + item.getName());
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        btnUse.setDisable(state.isGameOver() || !state.canPlayerAct());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
