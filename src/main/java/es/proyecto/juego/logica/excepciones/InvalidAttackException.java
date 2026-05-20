package es.proyecto.juego.logica.excepciones;

public class InvalidAttackException extends RuntimeException {
    public InvalidAttackException(String message) {
        super(message);
    }
}
