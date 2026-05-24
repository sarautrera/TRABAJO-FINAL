package es.uah.eedd.listas.prueba.stack;

import es.uah.eedd.listas.prueba.simple.MyLinkedList;

public class Stack<T> implements IStack<T>{
    //Usamos la lista simple
    private MyLinkedList<T> lista;

    public Stack(){
        this.lista=new MyLinkedList<>();
    }
    //Añadir elementos
    @Override
    public void push(T dato){
        lista.add(0,dato);
    }
    //Quitar elementos
    @Override
    public T pop() {
        if (isEmpty()) return null;
        return lista.remove(0);
    }
    @Override
    public boolean isEmpty(){return lista.isEmpty();}
    //metodo para saber quien esta al frente de la cola
    @Override
    public T peek(){
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
