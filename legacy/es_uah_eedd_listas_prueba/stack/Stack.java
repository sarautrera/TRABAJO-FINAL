/*
 * Resumen del fichero: Conserva una implementacion antigua de Stack usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.stack;

import es.uah.eedd.listas.prueba.simple.MyLinkedList;

public class Stack<T> implements IStack<T>{
    private MyLinkedList<T> lista;

    public Stack(){
        this.lista=new MyLinkedList<>();
    }
    @Override
    public void push(T dato){
        lista.add(0,dato);
    }
    @Override
    public T pop() {
        if (isEmpty()) return null;
        return lista.remove(0);
    }
    @Override
    public boolean isEmpty(){return lista.isEmpty();}
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
