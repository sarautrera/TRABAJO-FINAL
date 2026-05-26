// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.tests;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameEngine;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.IGameState;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Item;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.items.Potion;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Cell;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.CellType;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.mundo.Room;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.ui.GameController;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.ui.MainApp;
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
import javafx.application.Platform;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.Node;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.Button;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.Label;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.ListView;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.ProgressBar;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.control.TextArea;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.scene.layout.StackPane;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import javafx.stage.Stage;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import org.junit.jupiter.api.BeforeAll;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import org.junit.jupiter.api.Test;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.IOException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.util.concurrent.CountDownLatch;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.util.concurrent.TimeUnit;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.util.concurrent.atomic.AtomicReference;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertNotNull;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class UiViewsJUnitTest {
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @BeforeAll
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    static void startJavaFx() throws Exception {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        CountDownLatch latch = new CountDownLatch(1);
        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            Platform.startup(latch::countDown);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } catch (IllegalStateException alreadyStarted) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            latch.countDown();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(latch.await(5, TimeUnit.SECONDS));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void panelsRenderNullEmptyActiveAndGameOverStates() throws Exception {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        runOnFxThread(() -> {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            FakeState state = new FakeState();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            state.inventory.add(new Potion("Pocion", 10));
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            state.events.add("Inicio");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            state.events.add("Movimiento");

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            PlayerPanel playerPanel = new PlayerPanel();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            playerPanel.update(null);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            playerPanel.update(state);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals("Vida: 70 / 100", labelAt(playerPanel, 1).getText());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals(0.7, ((ProgressBar) playerPanel.getChildren().get(2)).getProgress(), 0.0001);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals("Atq: 9 | Def: 4 | Vel: 3", labelAt(playerPanel, 3).getText());

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            InventoryPanel inventoryPanel = new InventoryPanel();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            inventoryPanel.update(null);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(buttonAt(inventoryPanel, 2).isDisabled());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            state.inventory.clear();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            inventoryPanel.update(state);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals("Inventario vacio", listViewAt(inventoryPanel, 1).getItems().get(0));
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(buttonAt(inventoryPanel, 2).isDisabled());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            state.inventory.add(new Potion("Pocion", 10));
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            inventoryPanel.update(state);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals("[0] Pocion", listViewAt(inventoryPanel, 1).getItems().get(0));
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertFalse(buttonAt(inventoryPanel, 2).isDisabled());
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            state.gameOver = true;
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            inventoryPanel.update(state);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(buttonAt(inventoryPanel, 2).isDisabled());

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            ActionPanel actionPanel = new ActionPanel();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            actionPanel.update(null);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(buttonAt(actionPanel, 0).isDisabled());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(buttonAt(actionPanel, 1).isDisabled());
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            state.gameOver = false;
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            actionPanel.update(state);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertFalse(buttonAt(actionPanel, 0).isDisabled());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertFalse(buttonAt(actionPanel, 1).isDisabled());
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            state.gameOver = true;
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            actionPanel.update(state);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(buttonAt(actionPanel, 0).isDisabled());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertFalse(buttonAt(actionPanel, 1).isDisabled());

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            LogPanel logPanel = new LogPanel();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            logPanel.update(null);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            logPanel.update(state);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            TextArea area = (TextArea) logPanel.getChildren().get(0);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(area.getText().contains("> Inicio"));
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(area.getText().contains("> Movimiento"));
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void roomViewPaintsAllCellTypesAndHighlightsReachableTargets() throws Exception {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        runOnFxThread(() -> {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            FakeState state = new FakeState();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            state.room.setCell(0, 0, new Cell(CellType.WALL));
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            state.room.setCell(0, 1, new Cell(CellType.ENEMY));
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            state.room.setCell(0, 2, new Cell(CellType.ITEM));
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            state.room.configureDoor(1, 0, 7, true, true);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            state.room.configureDoor(1, 1, 8, false, false);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            state.room.setCell(1, 2, new Cell(CellType.TRAP));
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            state.row = 2;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            state.col = 2;

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            FakeController controller = new FakeController(state);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            controller.reachable.add(new int[]{2, 0});
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            controller.attackTargets.add(new int[]{0, 1});

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            RoomView roomView = new RoomView();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            roomView.setController(controller);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            roomView.update(null);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals(0, roomView.getChildren().size());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            roomView.update(state);

            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals(9, roomView.getChildren().size());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertCellLabel(roomView, 0, "#");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertCellLabel(roomView, 1, "E");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertCellLabel(roomView, 2, "I");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertCellLabel(roomView, 3, "X");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertCellLabel(roomView, 4, "D");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertCellLabel(roomView, 5, "!");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertCellLabel(roomView, 8, "@");
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(roomView.getChildren().get(1).getStyle().contains("#E74C3C"));
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(roomView.getChildren().get(6).getStyle().contains("#F2C94C"));
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void gameControllerRefreshesViewsAndDelegatesSafeActions() throws Exception {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        runOnFxThread(() -> {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            FakeState state = new FakeState();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            FakeEngine engine = new FakeEngine(state);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            FakeMainApp app = new FakeMainApp();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            GameController controller = new GameController(app, engine);

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

            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            controller.startGame();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals(9, roomView.getChildren().size());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals(1, controller.getReachableCells().size());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals(1, controller.getAttackTargets().size());

            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            controller.onCellClicked(0, 1);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals("attack", engine.lastAction);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            controller.onCellClicked(1, 0);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals("openDoor", engine.lastAction);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            state.room.placeItem(1, 1, new Potion("Pocion", 5));
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            controller.onCellClicked(1, 1);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals("pickItem", engine.lastAction);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            controller.onCellClicked(2, 0);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals("move", engine.lastAction);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            controller.onUseItemClicked(0);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals("useItem", engine.lastAction);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            controller.onEndTurn();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals("endTurn", engine.lastAction);

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            state.gameOver = true;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            state.victory = true;
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            controller.onEndTurn();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertEquals("VICTORIA ABSOLUTA", app.lastResult);
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            assertTrue(app.lastLog.contains("REGISTRO COMPLETO"));
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private static void runOnFxThread(Runnable runnable) throws Exception {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        CountDownLatch latch = new CountDownLatch(1);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        AtomicReference<Throwable> error = new AtomicReference<>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        Platform.runLater(() -> {
            // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
            try {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                runnable.run();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } catch (Throwable throwable) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                error.set(throwable);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            } finally {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                latch.countDown();
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(latch.await(5, TimeUnit.SECONDS));
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (error.get() != null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new AssertionError(error.get());
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private static Label labelAt(PlayerPanel panel, int index) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return (Label) panel.getChildren().get(index);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private static Button buttonAt(javafx.scene.layout.Pane panel, int index) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return (Button) panel.getChildren().get(index);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @SuppressWarnings("unchecked")
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private static ListView<String> listViewAt(javafx.scene.layout.Pane panel, int index) {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return (ListView<String>) panel.getChildren().get(index);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private static void assertCellLabel(RoomView view, int index, String expected) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StackPane pane = (StackPane) view.getChildren().get(index);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Node child = pane.getChildren().get(0);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(expected, ((Label) child).getText());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    private static final class FakeMainApp extends MainApp {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private String lastResult;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private String lastLog;

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public Stage getPrimaryStage() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return null;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public void showFin(String resultMessage, String completeLog) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.lastResult = resultMessage;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.lastLog = completeLog;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    private static class FakeController extends GameController {
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private final MyLinkedList<int[]> reachable = new MyLinkedList<>();
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private final MyLinkedList<int[]> attackTargets = new MyLinkedList<>();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        FakeController(FakeState state) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            super(new FakeMainApp(), new FakeEngine(state));
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public IList<int[]> getReachableCells() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return reachable;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        public IList<int[]> getAttackTargets() {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return attackTargets;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    private static final class FakeEngine implements IGameEngine {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private final FakeState state;
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private final MyLinkedList<int[]> reachable = new MyLinkedList<>();
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private final MyLinkedList<int[]> attackTargets = new MyLinkedList<>();
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private String lastAction = "";

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        FakeEngine(FakeState state) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.state = state;
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            reachable.add(new int[]{2, 0});
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            attackTargets.add(new int[]{0, 1});
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public void loadConfig(String jsonPath) throws IOException { lastAction = "loadConfig"; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public void newGame() { lastAction = "newGame"; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public void loadGame(String jsonPath) throws IOException { lastAction = "loadGame"; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public void saveGame(String jsonPath) throws IOException { lastAction = "saveGame"; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public boolean movePlayer(int row, int col) { lastAction = "move"; state.row = row; state.col = col; return true; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public boolean attack(int targetRow, int targetCol) { lastAction = "attack"; return true; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public boolean useItem(int inventoryIndex) { lastAction = "useItem"; return true; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public boolean pickItem(int row, int col) { lastAction = "pickItem"; return true; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public boolean openDoor(int row, int col) { lastAction = "openDoor"; return true; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public void endTurn() { lastAction = "endTurn"; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public IGameState getState() { return state; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public IList<int[]> getReachableCells() { return reachable; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public IList<int[]> getAttackTargets() { return attackTargets; }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    private static final class FakeState implements IGameState {
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private int row = 2;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private int col = 2;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private boolean gameOver;
        // Comentario de estudiante: aqui se declara una variable o constante de la clase.
        private boolean victory;
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private final Room room = new Room(1, "Sala", 3, 3, new MyLinkedList<>());
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private final MyLinkedList<Item> inventory = new MyLinkedList<>();
        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private final MyLinkedList<String> events = new MyLinkedList<>();

        // Comentario de estudiante: aqui empieza un metodo o constructor.
        private FakeState() {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            room.configureDoor(1, 0, 1, false, false);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getPlayerRow() { return row; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getPlayerCol() { return col; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getPlayerHp() { return 70; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getPlayerMaxHp() { return 100; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getPlayerSpeed() { return 3; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getPlayerAttack() { return 9; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getPlayerDefense() { return 4; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public String getEquippedWeaponName() { return ""; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public String getEquippedArmorName() { return ""; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public IList<Item> getInventory() { return inventory; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getMaxInventorySize() { return 5; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public boolean isInventoryFull() { return false; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getCurrentRoomId() { return 1; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public String getCurrentRoomName() { return "Sala"; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getCurrentRoomRows() { return 3; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getCurrentRoomCols() { return 3; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public Room getCurrentRoom() { return room; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getTurnCount() { return 1; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getTurnsLeft() { return 49; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public boolean canPlayerMove() { return true; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public boolean canPlayerAct() { return true; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getMinRoomsToExit() { return 2; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public int getDistanceToNearestDoor() { return 3; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public IList<Integer> getPathToExit() { return new MyLinkedList<>(); }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public IList<String> getEventLog() { return events; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public String getLastEvent() { return events.isEmpty() ? "" : events.get(events.size() - 1); }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public boolean isGameOver() { return gameOver; }
        // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
        @Override public boolean isVictory() { return victory; }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
