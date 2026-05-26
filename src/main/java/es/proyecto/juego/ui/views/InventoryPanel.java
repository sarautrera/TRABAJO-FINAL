/*
 * Resumen del fichero: Construye y actualiza el panel de inventario del jugador.
 */
package es.proyecto.juego.ui.views;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.logica.items.Item;
import es.proyecto.juego.ui.GameController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class InventoryPanel extends VBox {
    private final ListView<String> listView = new ListView<>();
    private final Button btnUse = new Button("Usar / Equipar");
    private GameController controller;

    public InventoryPanel() {
        setPadding(new Insets(10));
        setSpacing(10);
        setStyle("-fx-background-color: #ECF0F1; -fx-border-color: #BDC3C7;");

        Label title = new Label("INVENTARIO");
        title.setStyle("-fx-font-weight: bold;");
        listView.setPrefHeight(150);

        btnUse.setOnAction(e -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && controller != null && !listView.getItems().isEmpty()) {
                String selected = listView.getItems().get(selectedIndex);
                if (!"Inventario vacio".equals(selected)) {
                    controller.onUseItemClicked(selectedIndex);
                }
            }
        });

        getChildren().addAll(title, listView, btnUse);
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void update(IGameState state) {
        listView.getItems().clear();
        if (state == null) {
            btnUse.setDisable(true);
            return;
        }

        IList<Item> inventory = state.getInventory();
        if (inventory == null || inventory.isEmpty()) {
            listView.getItems().add("Inventario vacio");
            btnUse.setDisable(true);
            return;
        }

        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item != null) {
                listView.getItems().add("[" + i + "] " + item.getName());
            }
        }
        btnUse.setDisable(state.isGameOver() || !state.canPlayerAct());
    }
}
