// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.tests;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.DoorLockedException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.GameAlreadyOverException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.InvalidAttackException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.InvalidMoveException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.logica.excepciones.InventoryFullException;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import org.junit.jupiter.api.Test;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class ExceptionsJUnitTest {
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void invalidMoveExceptionKeepsMessage() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        InvalidMoveException exception = new InvalidMoveException("movimiento invalido");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("movimiento invalido", exception.getMessage());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertInstanceOf(RuntimeException.class, exception);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void invalidAttackExceptionKeepsMessage() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        InvalidAttackException exception = new InvalidAttackException("ataque invalido");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("ataque invalido", exception.getMessage());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertInstanceOf(RuntimeException.class, exception);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void doorLockedExceptionKeepsMessage() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        DoorLockedException exception = new DoorLockedException("puerta bloqueada");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("puerta bloqueada", exception.getMessage());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertInstanceOf(RuntimeException.class, exception);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void gameAlreadyOverExceptionKeepsMessage() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        GameAlreadyOverException exception = new GameAlreadyOverException("partida terminada");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("partida terminada", exception.getMessage());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertInstanceOf(RuntimeException.class, exception);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Test
    // Comentario de estudiante: aqui se prepara una instruccion del programa.
    void inventoryFullExceptionKeepsMessage() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        InventoryFullException exception = new InventoryFullException("inventario lleno");

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertEquals("inventario lleno", exception.getMessage());
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        assertInstanceOf(RuntimeException.class, exception);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
