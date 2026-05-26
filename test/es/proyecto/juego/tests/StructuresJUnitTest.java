// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.tests;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyCircularList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyGraph;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedQueue;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedStack;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import org.junit.jupiter.api.Test;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertThrows;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class StructuresJUnitTest {
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void linkedListSupportsAddGetContainsAndRemove() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add(1);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add(3);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add(1, 2);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, list.size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, list.get(1).intValue());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(list.contains(3));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, list.remove(1).intValue());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(list.contains(2));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void linkedListRejectsInvalidIndexes() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IndexOutOfBoundsException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                list.get(0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IndexOutOfBoundsException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                list.add(1, 10);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void queueIsFifo() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedQueue<String> queue = new MyLinkedQueue<String>();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        queue.enqueue("a");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        queue.enqueue("b");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("a", queue.dequeue());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("b", queue.peek());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, queue.size());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void stackIsLifo() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedStack<String> stack = new MyLinkedStack<String>();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        stack.push("a");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        stack.push("b");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("b", stack.pop());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("a", stack.peek());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, stack.size());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void circularListCyclesThroughElements() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyCircularList<String> list = new MyCircularList<String>();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add("player");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add("enemy1");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add("enemy2");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("player", list.current());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("enemy1", list.next());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("enemy2", list.next());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("player", list.next());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void graphSupportsBfsAndShortestPath() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyGraph<Integer> graph = new MyGraph<Integer>();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addEdge(0, 1, 10);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addEdge(0, 2, 1);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addEdge(2, 1, 1);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, graph.nodeCount());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(graph.contains(2));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, graph.shortestDistance(0, 1));

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        IList<Integer> path = graph.shortestPath(0, 1);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, path.size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(0, path.get(0).intValue());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, path.get(1).intValue());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, path.get(2).intValue());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void graphSupportsUndirectedEdges() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyGraph<Integer> graph = new MyGraph<Integer>();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        graph.addUndirectedEdge(0, 1, 4);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, graph.nodeCount());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(4, graph.shortestDistance(0, 1));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(4, graph.shortestDistance(1, 0));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(graph.getNeighbors(0).contains(1));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(graph.getNeighbors(1).contains(0));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
