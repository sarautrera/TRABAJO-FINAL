package es.uah.eedd.listas.prueba.queue;

import es.uah.eedd.listas.prueba.simple.MyLinkedList;



public class Queue<T> implements IQueue<T> {
    //Usamos la lista simple
    private MyLinkedList<T> lista;

    public Queue(){
        this.lista=new MyLinkedList<>();
    }
    //Añadir elementos
    @Override
    public void enqueue(T dato){
        lista.add(dato);
    }
    //Quitar elementos
    @Override
    public T dequeue() throws RuntimeException{
        if (isEmpty()) {
            throw new RuntimeException("La cola está vacía.");
        }
        return lista.remove(0);
    }
    @Override
    public boolean isEmpty(){return lista.isEmpty();}
    //metodo para saber quien esta al frente de la cola
    @Override
    public T peek() throws RuntimeException{
        if (isEmpty()) {
            throw new RuntimeException("La cola está vacía.");
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
