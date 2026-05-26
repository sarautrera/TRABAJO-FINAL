// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.proyecto.juego.logica.sistemas;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.IList;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.proyecto.juego.estructuras.MyLinkedList;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class EventLog {
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private final MyLinkedList<String> events = new MyLinkedList<>();

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void add(String event) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (event == null || event.length() == 0) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new IllegalArgumentException("El evento no puede estar vacio");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        events.add(event);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public String getLastEvent() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (events.isEmpty()) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return "";
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return events.get(events.size() - 1);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public IList<String> getEvents() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return events;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int size() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return events.size();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isEmpty() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return events.isEmpty();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void clear() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        events.clear();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
