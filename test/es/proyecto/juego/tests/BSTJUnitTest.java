/*
 * Resumen del fichero: Contiene pruebas automatizadas para comprobar el arbol binario de busqueda.
 */
package es.proyecto.juego.tests;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.MyBST;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BSTJUnitTest {
    @Test
    void insertSearchAndInOrderKeepSortedValues() {
        MyBST<Integer> tree = new MyBST<Integer>();

        tree.insert(5);
        tree.insert(2);
        tree.insert(8);
        tree.insert(1);
        tree.insert(3);

        assertEquals(5, tree.size());
        assertTrue(tree.search(3));
        assertFalse(tree.search(7));

        IList<Integer> values = tree.inOrder();
        assertEquals(5, values.size());
        assertEquals(1, values.get(0).intValue());
        assertEquals(2, values.get(1).intValue());
        assertEquals(3, values.get(2).intValue());
        assertEquals(5, values.get(3).intValue());
        assertEquals(8, values.get(4).intValue());
    }

    @Test
    void toListReturnsInOrderValues() {
        MyBST<String> tree = new MyBST<String>();

        tree.insert("delta");
        tree.insert("alpha");
        tree.insert("charlie");

        IList<String> values = tree.toList();
        assertEquals("alpha", values.get(0));
        assertEquals("charlie", values.get(1));
        assertEquals("delta", values.get(2));
    }

    @Test
    void heightReflectsLongestBranchAndEmptyTreeIsZero() {
        MyBST<Integer> tree = new MyBST<Integer>();

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.height());

        tree.insert(10);
        tree.insert(5);
        tree.insert(2);
        tree.insert(1);
        tree.insert(20);

        assertFalse(tree.isEmpty());
        assertEquals(4, tree.height());
    }

    @Test
    void duplicateValuesAreIgnored() {
        MyBST<Integer> tree = new MyBST<Integer>();

        tree.insert(4);
        tree.insert(4);
        tree.insert(4);

        assertEquals(1, tree.size());
        assertEquals(1, tree.inOrder().size());
    }

    @Test
    void nullValuesAreRejected() {
        MyBST<Integer> tree = new MyBST<Integer>();

        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                tree.insert(null);
            }
        });
        assertThrows(IllegalArgumentException.class, new ExecutableBlock() {
            @Override
            public void execute() {
                tree.search(null);
            }
        });
    }

    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    }
}
