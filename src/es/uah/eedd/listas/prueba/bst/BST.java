package es.uah.eedd.listas.prueba.bst;

import es.uah.eedd.listas.prueba.simple.MyLinkedList;

public class BST<T extends Comparable<T>> implements ITree<T> {
    protected T dato;
    protected BST<T> der;
    protected BST<T> izq;
    protected int tamaño;

    //Constructor vacio
    public BST(){
        this.dato=null;
        this.izq=null;
        this.der=null;
        this.tamaño=0;
    }
    public boolean isEmpty(){
        return this.dato==null;
    }
    ////OPERACIONES//////////////////////////////////////////////////
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
                tamaño++;
            }else if(nuevo.compareTo(this.dato)>0){
                if (this.der==null){
                    this.der=new BST<>();
                }
                this.der.add(nuevo);
                tamaño++;
            }
        }
    }
    @Override
    public MyLinkedList<T> getSubArbolIzquierda() {
        // Si no hay hijo izquierdo:
        if (this.izq == null) {
            return new MyLinkedList<>();//Para evitar que devuelva null
        }
        // Si existe:
        return this.izq.getListaPreOrden();
    }
    @Override
    public MyLinkedList<T> getSubArbolDerecha() {
        // Si no hay hijo derecho:
        if (this.der == null) {
            return new MyLinkedList<>();
        }
        // Si existe:
        return this.der.getListaPreOrden();
    }
    ///////////////////////////////////////////////////////////
    @Override
    public boolean isArbolHomogeneo(){
        //Primer caso: el arbol esta vacio
        if (this.dato==null){
            return true;
        }
        //Segundo caso: el arbol es solo raiz
        if (this.izq==null && this.der==null){
            return true;
        }
        //Tercer caso(falso): el arbol solo tiene un hijo
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
        //Caso base: que no tenga hijos(si tiene uno no es homogeneo)
        if (this.der==null && this.izq==null){
            return true;
        }
        //Contar ambos lados
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
        // Comprobamos la altura Y ADEMÁS obligamos a los hijos a autocomprobarse
        boolean alturaCorrecta = (altIzq == altDer);

        // Si el hijo es null, no necesita comprobación (es correcto).
        boolean izqPerfecto = (this.izq == null) || this.izq.isArbolCompleto();
        boolean derPerfecto = (this.der == null) || this.der.isArbolCompleto();

        return alturaCorrecta && izqPerfecto && derPerfecto;
    }

    //Para hacer el arbol casi completo necesitamos comprobar comprobar que el numero de nodos es igual al de posiciones
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
        //hay un salto de posiciones en el lado izquierdo
        if (indice >=numNodos) return false;
        // Hijo izquierdo: 2*i + 1
        // Hijo derecho: 2*i + 2
        // Para el hijo izquierdo
        boolean izqCorrecto;
        if (this.izq == null) {
            // Si no hay hijo, es correcto (no rompe la regla)
            izqCorrecto = true;
        } else {
            // Si hay hijo comprobamos
            izqCorrecto = this.izq.compararPosiciones(2 * indice + 1, numNodos);
        }

// Para el hijo derecho
        boolean derCorrecto;
        if (this.der == null) {
            // Si no hay hijo, es correcto
            derCorrecto = true;
        } else {
            // Si hay hijo comprobamos
            derCorrecto = this.der.compararPosiciones(2 * indice + 2, numNodos);
        }
        return izqCorrecto && derCorrecto;

    }
    @Override
    public MyLinkedList<T> getListaDatosNivel(int nivel) {
        MyLinkedList<T> resultado = new MyLinkedList<>();

        // 1. Si el árbol está vacío, devolvemos la lista vacía
        if (this.isEmpty()) {
            throw new RuntimeException("El árbol está vacío");
        }

        // 2. CASO BASE: Si llegamos al nivel 0, hemos llegado al destino
        if (nivel == 0) {
            //hacemos add para añadir el dato de ese nivel y addAll para añadir lso elementos de los ninbeles inferiores
            resultado.add(this.dato);
            return resultado;
        }

        // 3. PASO RECURSIVO: Si aún no es 0, bajamos un nivel
        // Le pedimos a los hijos que busquen en el nivel anterior (nivel - 1)
        if (this.izq != null) {
            resultado.addAll(this.izq.getListaDatosNivel(nivel - 1));
        }
        if (this.der != null) {
            resultado.addAll(this.der.getListaDatosNivel(nivel - 1));
        }

        return resultado;
    }
    // Dentro de tu clase de Nodo o Arbol:
    @Override
    public MyLinkedList<T> getCamino(T valorBuscado) {
        if (this.dato == null) {
            throw new RuntimeException("El árbol está vacío");
        }
        MyLinkedList<T> camino = new MyLinkedList<>();
        camino.add(this.dato); // Añadimos el actual

        if (this.dato.equals(valorBuscado)) {
            return camino; // ¡Encontrado!
        }

        // Buscamos en el hijo correcto según el valor
        if (valorBuscado.compareTo(this.dato) < 0 && this.izq != null) {
            MyLinkedList<T> caminoIzq = this.izq.getCamino(valorBuscado);
            if (caminoIzq != null) {
                camino.addAll(caminoIzq); // Unimos el camino
                return camino;
            }
        } else if (valorBuscado.compareTo(this.dato) > 0 && this.der != null) {
            MyLinkedList<T> caminoDer = this.der.getCamino(valorBuscado);
            if (caminoDer != null) {
                camino.addAll(caminoDer); // Unimos el camino
                return camino;
            }
        }

        throw new RuntimeException("El valor no existe en el árbol");// No está en ninguna rama
    }

    //Altura y grado
    @Override
    public int getAltura() {
        if (this.dato==null){
            return 0;
        }
        //Altura en el lado izquierdo
        int altIzq =0;
        if (this.izq != null){
            altIzq=this.izq.getAltura();
        }else{
            altIzq=0;
        }
        //Altura en el lado derecho
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
        return 2; // Por definición de árbol binario en este ejercicio
    }
    /////ORDEN///////////////////////////////////////////////////////////////
    //RECURSIVIDAD: CADA LLAMADA ES INDIVIDUAL
    //RECORRIDO ORDEN CENTRAL(Izquierda -> Raíz -> Derecha)
    //se ordenan de menor a mayor, como si aplastaras el arbol contra el suelo)
    @Override
    public MyLinkedList<T> getListaOrdenCentral(){
        //En esta lista voy añadiendo los elementos de izq o der
        MyLinkedList<T> lista = new MyLinkedList<>();
        if(this.dato != null){
            if(this.izq != null){
                //Si no es nulo usamos recursividad para repetir el proceso
                lista.addAll(this.izq.getListaOrdenCentral());}
            //Se hace para cada llamada
            lista.add(this.dato);
            if (this.der != null){
                //Hacemos addAll para pasar de la estructura jerarquica(arbol) a lineal(lista)
                lista.addAll(this.der.getListaOrdenCentral());}
        }
        return lista;

    }
    // RECORRIDO EN PRE-ORDEN (Raíz - Izquierda - Derecha)
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

    // RECORRIDO EN POST-ORDEN (Izquierda - Derecha - Raíz)
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
