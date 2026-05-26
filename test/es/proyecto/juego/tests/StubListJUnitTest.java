// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.tests;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.stubs.StubList;
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
public class StubListJUnitTest {
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void startsEmptyAndAddsElements() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<String> list = new StubList<String>();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(list.isEmpty());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add("a");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add("b");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(list.isEmpty());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, list.size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("a", list.get(0));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("b", list.get(1));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void addAtIndexInsertsElement() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<Integer> list = new StubList<Integer>();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add(1);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add(3);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add(1, 2);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, list.size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(1, list.get(0).intValue());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, list.get(1).intValue());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(3, list.get(2).intValue());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void removeByIndexReturnsRemovedElement() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<String> list = new StubList<String>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add("a");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add("b");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add("c");

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String removed = list.remove(1);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("b", removed);
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals(2, list.size());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("c", list.get(1));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void removeByValueUsesEqualsAndReturnsWhetherItRemoved() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<String> list = new StubList<String>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add("a");
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add("b");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(list.remove("a"));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(list.contains("a"));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(list.contains("b"));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertFalse(list.remove("missing"));
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void supportsNullValues() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<String> list = new StubList<String>();

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        list.add(null);

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(list.contains(null));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(list.remove(null));
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertTrue(list.isEmpty());
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void invalidIndexesThrowException() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        StubList<Integer> list = new StubList<Integer>();

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
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertThrows(IndexOutOfBoundsException.class, new ExecutableBlock() {
            // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
            @Override
            // Comentario de estudiante: aqui empieza un metodo o constructor.
            public void execute() {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                list.remove(0);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        });
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza una interfaz con metodos que otras clases deben cumplir.
    private interface ExecutableBlock extends org.junit.jupiter.api.function.Executable {
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
