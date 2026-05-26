/*
 * Resumen del fichero: Contiene pruebas automatizadas para comprobar el comportamiento de UiViewsJUnitTest.
 */
package es.proyecto.juego.tests;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.MyLinkedList;
import es.proyecto.juego.logica.IGameEngine;
import es.proyecto.juego.logica.IGameState;
import es.proyecto.juego.logica.items.Item;
import es.proyecto.juego.logica.items.Potion;
import es.proyecto.juego.logica.mundo.Cell;
import es.proyecto.juego.logica.mundo.CellType;
import es.proyecto.juego.logica.mundo.Room;
import es.proyecto.juego.ui.GameController;
import es.proyecto.juego.ui.MainApp;
import es.proyecto.juego.ui.views.ActionPanel;
import es.proyecto.juego.ui.views.InventoryPanel;
import es.proyecto.juego.ui.views.LogPanel;
import es.proyecto.juego.ui.views.PlayerPanel;
import es.proyecto.juego.ui.views.RoomView;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiViewsJUnitTest {
    @BeforeAll
    static void startJavaFx() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Platform.startup(latch::countDown);
        } catch (IllegalStateException alreadyStarted) {
            latch.countDown();
        }
        assertTrue(latch.await(5, TimeUnit.SECONDS));
    }

    @Test
    void panelsRenderNullEmptyActiveAndGameOverStates() throws Exception {
        runOnFxThread(() -> {
            FakeState state = new FakeState();
            state.inventory.add(new Potion("Pocion", 10));
            state.events.add("Inicio");
            state.events.add("Movimiento");

            PlayerPanel playerPanel = new PlayerPanel();
            playerPanel.update(null);
            playerPanel.update(state);
            assertEquals("Vida: 70 / 100", labelAt(playerPanel, 1).getText());
            assertEquals(0.7, ((ProgressBar) playerPanel.getChildren().get(2)).getProgress(), 0.0001);
            assertEquals("Atq: 9 | Def: 4 | Vel: 3", labelAt(playerPanel, 3).getText());

            InventoryPanel inventoryPanel = new InventoryPanel();
            inventoryPanel.update(null);
            assertTrue(buttonAt(inventoryPanel, 2).isDisabled());
            state.inventory.clear();
            inventoryPanel.update(state);
            assertEquals("Inventario vacio", listViewAt(inventoryPanel, 1).getItems().get(0));
            assertTrue(buttonAt(inventoryPanel, 2).isDisabled());
            state.inventory.add(new Potion("Pocion", 10));
            inventoryPanel.update(state);
            assertEquals("[0] Pocion", listViewAt(inventoryPanel, 1).getItems().get(0));
            assertFalse(buttonAt(inventoryPanel, 2).isDisabled());
            state.gameOver = true;
            inventoryPanel.update(state);
            assertTrue(buttonAt(inventoryPanel, 2).isDisabled());

            ActionPanel actionPanel = new ActionPanel();
            actionPanel.update(null);
            assertTrue(buttonAt(actionPanel, 0).isDisabled());
            assertTrue(buttonAt(actionPanel, 1).isDisabled());
            state.gameOver = false;
            actionPanel.update(state);
            assertFalse(buttonAt(actionPanel, 0).isDisabled());
            assertFalse(buttonAt(actionPanel, 1).isDisabled());
            state.gameOver = true;
            actionPanel.update(state);
            assertTrue(buttonAt(actionPanel, 0).isDisabled());
            assertFalse(buttonAt(actionPanel, 1).isDisabled());

            LogPanel logPanel = new LogPanel();
            logPanel.update(null);
            logPanel.update(state);
            TextArea area = (TextArea) logPanel.getChildren().get(0);
            assertTrue(area.getText().contains("> Inicio"));
            assertTrue(area.getText().contains("> Movimiento"));
        });
    }

    @Test
    void roomViewPaintsAllCellTypesAndHighlightsReachableTargets() throws Exception {
        runOnFxThread(() -> {
            FakeState state = new FakeState();
            state.room.setCell(0, 0, new Cell(CellType.WALL));
            state.room.setCell(0, 1, new Cell(CellType.ENEMY));
            state.room.setCell(0, 2, new Cell(CellType.ITEM));
            state.room.configureDoor(1, 0, 7, true, true);
            state.room.configureDoor(1, 1, 8, false, false);
            state.room.setCell(1, 2, new Cell(CellType.TRAP));
            state.row = 2;
            state.col = 2;

            FakeController controller = new FakeController(state);
            controller.reachable.add(new int[]{2, 0});
            controller.attackTargets.add(new int[]{0, 1});

            RoomView roomView = new RoomView();
            roomView.setController(controller);
            roomView.update(null);
            assertEquals(0, roomView.getChildren().size());
            roomView.update(state);

            assertEquals(9, roomView.getChildren().size());
            assertCellLabel(roomView, 0, "#");
            assertCellLabel(roomView, 1, "E");
            assertCellLabel(roomView, 2, "I");
            assertCellLabel(roomView, 3, "X");
            assertCellLabel(roomView, 4, "D");
            assertCellLabel(roomView, 5, "!");
            assertCellLabel(roomView, 8, "@");
            assertTrue(roomView.getChildren().get(1).getStyle().contains("#E74C3C"));
            assertTrue(roomView.getChildren().get(6).getStyle().contains("#F2C94C"));
        });
    }

    @Test
    void gameControllerRefreshesViewsAndDelegatesSafeActions() throws Exception {
        runOnFxThread(() -> {
            FakeState state = new FakeState();
            FakeEngine engine = new FakeEngine(state);
            FakeMainApp app = new FakeMainApp();
            GameController controller = new GameController(app, engine);

            RoomView roomView = new RoomView();
            PlayerPanel playerPanel = new PlayerPanel();
            InventoryPanel inventoryPanel = new InventoryPanel();
            ActionPanel actionPanel = new ActionPanel();
            LogPanel logPanel = new LogPanel();
            controller.setViews(roomView, playerPanel, inventoryPanel, actionPanel, logPanel);
            roomView.setController(controller);
            inventoryPanel.setController(controller);
            actionPanel.setController(controller);

            controller.startGame();
            assertEquals(9, roomView.getChildren().size());
            assertEquals(1, controller.getReachableCells().size());
            assertEquals(1, controller.getAttackTargets().size());

            controller.onCellClicked(0, 1);
            assertEquals("attack", engine.lastAction);
            controller.onCellClicked(1, 0);
            assertEquals("openDoor", engine.lastAction);
            state.room.placeItem(1, 1, new Potion("Pocion", 5));
            controller.onCellClicked(1, 1);
            assertEquals("pickItem", engine.lastAction);
            controller.onCellClicked(2, 0);
            assertEquals("move", engine.lastAction);
            controller.onUseItemClicked(0);
            assertEquals("useItem", engine.lastAction);
            controller.onEndTurn();
            assertEquals("endTurn", engine.lastAction);

            state.gameOver = true;
            state.victory = true;
            controller.onEndTurn();
            assertEquals("VICTORIA ABSOLUTA", app.lastResult);
            assertTrue(app.lastLog.contains("REGISTRO COMPLETO"));
        });
    }

    private static void runOnFxThread(Runnable runnable) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<Throwable> error = new AtomicReference<>();
        Platform.runLater(() -> {
            try {
                runnable.run();
            } catch (Throwable throwable) {
                error.set(throwable);
            } finally {
                latch.countDown();
            }
        });
        assertTrue(latch.await(5, TimeUnit.SECONDS));
        if (error.get() != null) {
            throw new AssertionError(error.get());
        }
    }

    private static Label labelAt(PlayerPanel panel, int index) {
        return (Label) panel.getChildren().get(index);
    }

    private static Button buttonAt(javafx.scene.layout.Pane panel, int index) {
        return (Button) panel.getChildren().get(index);
    }

    @SuppressWarnings("unchecked")
    private static ListView<String> listViewAt(javafx.scene.layout.Pane panel, int index) {
        return (ListView<String>) panel.getChildren().get(index);
    }

    private static void assertCellLabel(RoomView view, int index, String expected) {
        StackPane pane = (StackPane) view.getChildren().get(index);
        Node child = pane.getChildren().get(0);
        assertEquals(expected, ((Label) child).getText());
    }

    private static final class FakeMainApp extends MainApp {
        private String lastResult;
        private String lastLog;

        @Override
        public Stage getPrimaryStage() {
            return null;
        }

        @Override
        public void showFin(String resultMessage, String completeLog) {
            this.lastResult = resultMessage;
            this.lastLog = completeLog;
        }
    }

    private static class FakeController extends GameController {
        private final MyLinkedList<int[]> reachable = new MyLinkedList<>();
        private final MyLinkedList<int[]> attackTargets = new MyLinkedList<>();

        FakeController(FakeState state) {
            super(new FakeMainApp(), new FakeEngine(state));
        }

        @Override
        public IList<int[]> getReachableCells() {
            return reachable;
        }

        @Override
        public IList<int[]> getAttackTargets() {
            return attackTargets;
        }
    }

    private static final class FakeEngine implements IGameEngine {
        private final FakeState state;
        private final MyLinkedList<int[]> reachable = new MyLinkedList<>();
        private final MyLinkedList<int[]> attackTargets = new MyLinkedList<>();
        private String lastAction = "";

        FakeEngine(FakeState state) {
            this.state = state;
            reachable.add(new int[]{2, 0});
            attackTargets.add(new int[]{0, 1});
        }

        @Override public void loadConfig(String jsonPath) throws IOException { lastAction = "loadConfig"; }
        @Override public void newGame() { lastAction = "newGame"; }
        @Override public void loadGame(String jsonPath) throws IOException { lastAction = "loadGame"; }
        @Override public void saveGame(String jsonPath) throws IOException { lastAction = "saveGame"; }
        @Override public boolean movePlayer(int row, int col) { lastAction = "move"; state.row = row; state.col = col; return true; }
        @Override public boolean attack(int targetRow, int targetCol) { lastAction = "attack"; return true; }
        @Override public boolean useItem(int inventoryIndex) { lastAction = "useItem"; return true; }
        @Override public boolean pickItem(int row, int col) { lastAction = "pickItem"; return true; }
        @Override public boolean openDoor(int row, int col) { lastAction = "openDoor"; return true; }
        @Override public void endTurn() { lastAction = "endTurn"; }
        @Override public IGameState getState() { return state; }
        @Override public IList<int[]> getReachableCells() { return reachable; }
        @Override public IList<int[]> getAttackTargets() { return attackTargets; }
    }

    private static final class FakeState implements IGameState {
        private int row = 2;
        private int col = 2;
        private boolean gameOver;
        private boolean victory;
        private final Room room = new Room(1, "Sala", 3, 3, new MyLinkedList<>());
        private final MyLinkedList<Item> inventory = new MyLinkedList<>();
        private final MyLinkedList<String> events = new MyLinkedList<>();

        private FakeState() {
            room.configureDoor(1, 0, 1, false, false);
        }

        @Override public int getPlayerRow() { return row; }
        @Override public int getPlayerCol() { return col; }
        @Override public int getPlayerHp() { return 70; }
        @Override public int getPlayerMaxHp() { return 100; }
        @Override public int getPlayerSpeed() { return 3; }
        @Override public int getPlayerAttack() { return 9; }
        @Override public int getPlayerDefense() { return 4; }
        @Override public String getEquippedWeaponName() { return ""; }
        @Override public String getEquippedArmorName() { return ""; }
        @Override public IList<Item> getInventory() { return inventory; }
        @Override public int getMaxInventorySize() { return 5; }
        @Override public boolean isInventoryFull() { return false; }
        @Override public int getCurrentRoomId() { return 1; }
        @Override public String getCurrentRoomName() { return "Sala"; }
        @Override public int getCurrentRoomRows() { return 3; }
        @Override public int getCurrentRoomCols() { return 3; }
        @Override public Room getCurrentRoom() { return room; }
        @Override public int getTurnCount() { return 1; }
        @Override public int getTurnsLeft() { return 49; }
        @Override public boolean canPlayerMove() { return true; }
        @Override public boolean canPlayerAct() { return true; }
        @Override public int getMinRoomsToExit() { return 2; }
        @Override public int getDistanceToNearestDoor() { return 3; }
        @Override public IList<Integer> getPathToExit() { return new MyLinkedList<>(); }
        @Override public IList<String> getEventLog() { return events; }
        @Override public String getLastEvent() { return events.isEmpty() ? "" : events.get(events.size() - 1); }
        @Override public boolean isGameOver() { return gameOver; }
        @Override public boolean isVictory() { return victory; }
    }
}
