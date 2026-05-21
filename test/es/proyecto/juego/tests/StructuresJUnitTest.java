package es.proyecto.juego.tests;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.MyCircularList;
import es.proyecto.juego.estructuras.MyGraph;
import es.proyecto.juego.estructuras.MyLinkedList;
import es.proyecto.juego.estructuras.MyLinkedQueue;
import es.proyecto.juego.estructuras.MyLinkedStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StructuresJUnitTest {
    @Test
    void linkedListSupportsAddGetContainsAndRemove() {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();

        list.add(1);
        list.add(3);
        list.add(1, 2);

        assertEquals(3, list.size());
        assertEquals(2, list.get(1).intValue());
        assertTrue(list.contains(3));
        assertEquals(2, list.remove(1).intValue());
        assertFalse(list.contains(2));
    }

    @Test
    void linkedListRejectsInvalidIndexes() {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();

        assertThrows(IndexOutOfBoundsException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                list.get(0);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                list.add(1, 10);
            }
        });
    }

    @Test
    void queueIsFifo() {
        MyLinkedQueue<String> queue = new MyLinkedQueue<String>();

        queue.enqueue("a");
        queue.enqueue("b");

        assertEquals("a", queue.dequeue());
        assertEquals("b", queue.peek());
        assertEquals(1, queue.size());
    }

    @Test
    void stackIsLifo() {
        MyLinkedStack<String> stack = new MyLinkedStack<String>();

        stack.push("a");
        stack.push("b");

        assertEquals("b", stack.pop());
        assertEquals("a", stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void circularListCyclesThroughElements() {
        MyCircularList<String> list = new MyCircularList<String>();

        list.add("player");
        list.add("enemy1");
        list.add("enemy2");

        assertEquals("player", list.current());
        assertEquals("enemy1", list.next());
        assertEquals("enemy2", list.next());
        assertEquals("player", list.next());
    }

    @Test
    void graphSupportsBfsAndShortestPath() {
        MyGraph<Integer> graph = new MyGraph<Integer>();

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, 1);

        assertEquals(3, graph.nodeCount());
        assertTrue(graph.contains(2));
        assertEquals(2, graph.shortestDistance(0, 1));

        IList<Integer> path = graph.shortestPath(0, 1);
        assertEquals(3, path.size());
        assertEquals(0, path.get(0).intValue());
        assertEquals(2, path.get(1).intValue());
        assertEquals(1, path.get(2).intValue());
    }

    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    }
}
