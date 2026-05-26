/*
 * Resumen del fichero: Define una excepcion especifica para expresar un error de reglas del juego.
 */
package es.proyecto.juego.logica.excepciones;

public class InvalidAttackException extends RuntimeException {
    public InvalidAttackException(String message) {
        super(message);
    }
}
