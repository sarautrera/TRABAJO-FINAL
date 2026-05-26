/*
 * Resumen del fichero: Conserva una implementacion antigua de BST usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.bst;

import es.uah.eedd.listas.prueba.simple.MyLinkedList;

public class BST<T extends Comparable<T>> implements ITree<T> {
    protected T dato;
    protected BST<T> der;
    protected BST<T> izq;
    protected int tamano;

    public BST(){
        this.dato=null;
        this.izq=null;
        this.der=null;
        this.tamano=0;
    }
    public boolean isEmpty(){
        return this.dato==null;
    }
    @Override
    public void add(T nuevo){
        if (this.dato==null){
            this.dato=nuevo;
        }else{
            if(nuevo.compareTo(this.dato)<0){
                if(this.izq==null){
                    this.izq=new BST<>();
                }
                this.izq.add(nuevo);
                tamano++;
            }else if(nuevo.compareTo(this.dato)>0){
                if (this.der==null){
                    this.der=new BST<>();
                }
                this.der.add(nuevo);
                tamano++;
            }
        }
    }
    @Override
    public MyLinkedList<T> getSubArbolIzquierda() {
        if (this.izq == null) {
            return new MyLinkedList<>();
        }
        return this.izq.getListaPreOrden();
    }
    @Override
    public MyLinkedList<T> getSubArbolDerecha() {
        if (this.der == null) {
            return new MyLinkedList<>();
        }
        return this.der.getListaPreOrden();
    }
    @Override
    public boolean isArbolHomogeneo(){
        if (this.dato==null){
            return true;
        }
        if (this.izq==null && this.der==null){
            return true;
        }
        if (((this.izq==null) && (this.der!=null))|| ((this.izq!=null) && (this.der==null))){
            return false;
        }
        return this.izq.isArbolHomogeneo() && this.der.isArbolHomogeneo();
    }
    @Override
    public boolean isArbolCompleto(){
        if (this.dato==null){
            return true;
        }
        if (this.der==null && this.izq==null){
            return true;
        }
        int altIzq=0;
        if (this.izq!=null){
            altIzq=this.izq.getAltura();
        }else{
            altIzq=0;
        }
        int altDer=0;
        if (this.der!=null){
            altDer=this.der.getAltura();
        }else{
            altDer=0;
        }
        boolean alturaCorrecta = (altIzq == altDer);

        boolean izqPerfecto = (this.izq == null) || this.izq.isArbolCompleto();
        boolean derPerfecto = (this.der == null) || this.der.isArbolCompleto();

        return alturaCorrecta && izqPerfecto && derPerfecto;
    }

    @Override
    public boolean isArbolCasiCompleto(){
        int numNodos=this.contarNodos();
        return compararPosiciones(0,numNodos);
    }
    @Override
    public int contarNodos(){
        if (this.dato==null){
            return 0;
        }
        int contador=1;
        if (this.izq != null){
            contador += this.izq.contarNodos();
        }
        if (this.der != null){
            contador += this.der.contarNodos();
        }
        return contador;
    }
    @Override
    public boolean compararPosiciones(int indice, int numNodos){
        if (this.dato == null) {
            return true;
        }
        if (indice >=numNodos) return false;
        boolean izqCorrecto;
        if (this.izq == null) {
            izqCorrecto = true;
        } else {
            izqCorrecto = this.izq.compararPosiciones(2 * indice + 1, numNodos);
        }

        boolean derCorrecto;
        if (this.der == null) {
            derCorrecto = true;
        } else {
            derCorrecto = this.der.compararPosiciones(2 * indice + 2, numNodos);
        }
        return izqCorrecto && derCorrecto;

    }
    @Override
    public MyLinkedList<T> getListaDatosNivel(int nivel) {
        MyLinkedList<T> resultado = new MyLinkedList<>();

        if (this.isEmpty()) {
            throw new RuntimeException("El Ã¡rbol estÃ¡ vacÃ­o");
        }

        if (nivel == 0) {
            resultado.add(this.dato);
            return resultado;
        }

        if (this.izq != null) {
            resultado.addAll(this.izq.getListaDatosNivel(nivel - 1));
        }
        if (this.der != null) {
            resultado.addAll(this.der.getListaDatosNivel(nivel - 1));
        }

        return resultado;
    }
    @Override
    public MyLinkedList<T> getCamino(T valorBuscado) {
        if (this.dato == null) {
            throw new RuntimeException("El Ã¡rbol estÃ¡ vacÃ­o");
        }
        MyLinkedList<T> camino = new MyLinkedList<>();
        camino.add(this.dato);

        if (this.dato.equals(valorBuscado)) {
            return camino;
        }

        if (valorBuscado.compareTo(this.dato) < 0 && this.izq != null) {
            MyLinkedList<T> caminoIzq = this.izq.getCamino(valorBuscado);
            if (caminoIzq != null) {
                camino.addAll(caminoIzq);
                return camino;
            }
        } else if (valorBuscado.compareTo(this.dato) > 0 && this.der != null) {
            MyLinkedList<T> caminoDer = this.der.getCamino(valorBuscado);
            if (caminoDer != null) {
                camino.addAll(caminoDer);
                return camino;
            }
        }

        throw new RuntimeException("El valor no existe en el Ã¡rbol");
    }

    @Override
    public int getAltura() {
        if (this.dato==null){
            return 0;
        }
        int altIzq =0;
        if (this.izq != null){
            altIzq=this.izq.getAltura();
        }else{
            altIzq=0;
        }
        int altDer=0;
        if(this.der !=null){
            altDer=this.der.getAltura();
        }else{
            altDer=0;
        }
        return 1 +Math.max(altIzq,altDer);
    }
    @Override
    public int getGrado() {
        return 2;
    }
    @Override
    public MyLinkedList<T> getListaOrdenCentral(){
        MyLinkedList<T> lista = new MyLinkedList<>();
        if(this.dato != null){
            if(this.izq != null){
                lista.addAll(this.izq.getListaOrdenCentral());}
            lista.add(this.dato);
            if (this.der != null){
                lista.addAll(this.der.getListaOrdenCentral());}
        }
        return lista;

    }
    @Override
    public MyLinkedList<T> getListaPreOrden() {
        MyLinkedList<T> lista = new MyLinkedList<>();
        if (this.dato != null) {
            lista.add(this.dato);
            if (this.izq != null) lista.addAll(this.izq.getListaPreOrden());
            if (this.der != null) lista.addAll(this.der.getListaPreOrden());
        }
        return lista;
    }

    @Override
    public MyLinkedList<T> getListaPostOrden() {
        MyLinkedList<T> lista = new MyLinkedList<>();
        if (this.dato != null) {
            if (this.izq != null) lista.addAll(this.izq.getListaPostOrden());
            if (this.der != null) lista.addAll(this.der.getListaPostOrden());
            lista.add(this.dato);
        }
        return lista;
    }
}
