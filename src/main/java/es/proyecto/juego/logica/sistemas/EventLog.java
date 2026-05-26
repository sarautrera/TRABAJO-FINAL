/*
 * Resumen del fichero: Guarda los mensajes relevantes que ocurren durante la partida.
 */
package es.proyecto.juego.logica.sistemas;

import es.proyecto.juego.estructuras.IList;
import es.proyecto.juego.estructuras.MyLinkedList;

public class EventLog {
    private final MyLinkedList<String> events = new MyLinkedList<>();

    public void add(String event) {
        if (event == null || event.length() == 0) {
            throw new IllegalArgumentException("El evento no puede estar vacio");
        }
        events.add(event);
    }

    public String getLastEvent() {
        if (events.isEmpty()) {
            return "";
        }
        return events.get(events.size() - 1);
    }

    public IList<String> getEvents() {
        return events;
    }

    public int size() {
        return events.size();
    }

    public boolean isEmpty() {
        return events.isEmpty();
    }

    public void clear() {
        events.clear();
    }
}
