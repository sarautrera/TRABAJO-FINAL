package es.uah.eedd.listas.prueba.bst;

import es.uah.eedd.listas.prueba.simple.MyLinkedList;

public interface ITree<T> {
    boolean isEmpty();
    void add(T nuevo);
    MyLinkedList<T> getSubArbolIzquierda();
    MyLinkedList<T> getSubArbolDerecha();
    boolean isArbolHomogeneo();
    boolean isArbolCompleto();
    boolean isArbolCasiCompleto();
    int contarNodos();
    boolean compararPosiciones(int indice, int numNodos);
    MyLinkedList<T> getListaDatosNivel(int nivel);
    MyLinkedList<T> getCamino(T valorBuscado);
    int getAltura();
    int getGrado();
    MyLinkedList<T> getListaOrdenCentral();
    MyLinkedList<T> getListaPreOrden();
    MyLinkedList<T> getListaPostOrden();
}
