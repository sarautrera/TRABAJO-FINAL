// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.simple;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class MiIterador<T> {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private Node<T> actual;

    // El iterador nace apuntando al primero de la lista
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public MiIterador(Node<T> primero) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.actual = primero;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Â¿Hay un nodo donde estoy apuntando?
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean hasNext() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return actual != null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Devuelve el dato actual y mueve el "dedo" al siguiente nodo
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T next() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (actual == null) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return null; // En lugar de lanzar error, devolvemos null
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        T dato = actual.value;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        actual = actual.next;
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return dato;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
