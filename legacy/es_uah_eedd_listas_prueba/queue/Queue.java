/*
 * Resumen del fichero: Conserva una implementacion antigua de Queue usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.queue;

import es.uah.eedd.listas.prueba.simple.MyLinkedList;



public class Queue<T> implements IQueue<T> {
    private MyLinkedList<T> lista;

    public Queue(){
        this.lista=new MyLinkedList<>();
    }
    @Override
    public void enqueue(T dato){
        lista.add(dato);
    }
    @Override
    public T dequeue() throws RuntimeException{
        if (isEmpty()) {
            throw new RuntimeException("La cola estÃ¡ vacÃ­a.");
        }
        return lista.remove(0);
    }
    @Override
    public boolean isEmpty(){return lista.isEmpty();}
    @Override
    public T peek() throws RuntimeException{
        if (isEmpty()) {
            throw new RuntimeException("La cola estÃ¡ vacÃ­a.");
        }
        return lista.getFirst();
    }
    @Override
    public int size(){
        return lista.getSize();
    }
    @Override
    public void clear(){
        lista.clear();
    }
}
