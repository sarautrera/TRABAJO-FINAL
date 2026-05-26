// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.queue;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.uah.eedd.listas.prueba.simple.MyLinkedList;



// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class Queue<T> implements IQueue<T> {
    //Usamos la lista simple
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private MyLinkedList<T> lista;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Queue(){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.lista=new MyLinkedList<>();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    //AÃ±adir elementos
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void enqueue(T dato){
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        lista.add(dato);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    //Quitar elementos
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T dequeue() throws RuntimeException{
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isEmpty()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new RuntimeException("La cola estÃ¡ vacÃ­a.");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return lista.remove(0);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isEmpty(){return lista.isEmpty();}
    //metodo para saber quien esta al frente de la cola
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public T peek() throws RuntimeException{
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (isEmpty()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new RuntimeException("La cola estÃ¡ vacÃ­a.");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return lista.getFirst();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int size(){
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return lista.getSize();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void clear(){
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        lista.clear();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
