package es.proyecto.juego.logica.excepciones;

public class DoorLockedException extends RuntimeException {
    public DoorLockedException(String message) {
        super(message);
    }
}
