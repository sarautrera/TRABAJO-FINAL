/*
 * Resumen del fichero: Contiene pruebas automatizadas para comprobar el comportamiento de StubListJUnitTest.
 */
package es.proyecto.juego.tests;

import es.proyecto.juego.logica.stubs.StubList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StubListJUnitTest {
    @Test
    void startsEmptyAndAddsElements() {
        StubList<String> list = new StubList<String>();

        assertTrue(list.isEmpty());
        list.add("a");
        list.add("b");

        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
    }

    @Test
    void addAtIndexInsertsElement() {
        StubList<Integer> list = new StubList<Integer>();

        list.add(1);
        list.add(3);
        list.add(1, 2);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0).intValue());
        assertEquals(2, list.get(1).intValue());
        assertEquals(3, list.get(2).intValue());
    }

    @Test
    void removeByIndexReturnsRemovedElement() {
        StubList<String> list = new StubList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        String removed = list.remove(1);

        assertEquals("b", removed);
        assertEquals(2, list.size());
        assertEquals("c", list.get(1));
    }

    @Test
    void removeByValueUsesEqualsAndReturnsWhetherItRemoved() {
        StubList<String> list = new StubList<String>();
        list.add("a");
        list.add("b");

        assertTrue(list.remove("a"));
        assertFalse(list.contains("a"));
        assertTrue(list.contains("b"));
        assertFalse(list.remove("missing"));
    }

    @Test
    void supportsNullValues() {
        StubList<String> list = new StubList<String>();

        list.add(null);

        assertTrue(list.contains(null));
        assertTrue(list.remove(null));
        assertTrue(list.isEmpty());
    }

    @Test
    void invalidIndexesThrowException() {
        StubList<Integer> list = new StubList<Integer>();

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
        assertThrows(IndexOutOfBoundsException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                list.remove(0);
            }
        });
    }

    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    }
}
