package es.proyecto.juego.ui.views;

import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.logica.Item;
import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.ui.GameController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class InventoryPanel extends VBox {
    private ListView<String> listView = new ListView<>();
    private Button btnUsar = new Button("Usar / Equipar Elemento");
    private GameController controller;

    public InventoryPanel() {
        this.setPadding(new Insets(10));
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #ECF0F1; -fx-border-color: #BDC3C7;");

        Label title = new Label("INVENTARIO ACTIVO");
        title.setStyle("-fx-font-weight: bold;");
        listView.setPrefHeight(150);

        // Envía el índice seleccionado directamente al controlador en caliente
        btnUsar.setOnAction(e -> {
            int selectedIdx = listView.getSelectionModel().getSelectedIndex();
            if (selectedIdx >= 0 && controller != null) {
                controller.onUseItemClicked(selectedIdx);
            }
        });

        this.getChildren().addAll(title, listView, btnUsar);
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Actualiza la lista gráfica extrayendo los elementos del inventario real.
     * Utiliza un bucle clásico indexado, respetando el contrato estricto sin java.util.
     */
    public void update(IGameState state) {
        listView.getItems().clear();
        if (state == null) return;

        IList<Item> inventarioReal = state.getInventory();

        // Control de seguridad: Si el motor devuelve null o está vacío (como en nuestro Mock temporal)
        if (inventarioReal == null || inventarioReal.isEmpty()) {
            // Ponemos datos simulados para que la interfaz mantenga el tipo visual en el Mock
            listView.getItems().add("[0] Poción de Vida (+20 HP) *Mock*");
            listView.getItems().add("[1] Llave de Bronce (Mazmorra) *Mock*");
        } else {
            // Integración definitiva con el Track B: recorremos tu IList de forma indexada pura
            for (int i = 0; i < inventarioReal.size(); i++) {
                Item objeto = inventarioReal.get(i);
                if (objeto != null) {
                    listView.getItems().add("[" + i + "] " + objeto.getName());
                }
            }
        }
    }
}