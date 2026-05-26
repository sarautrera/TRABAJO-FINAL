// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.bst;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.uah.eedd.listas.prueba.simple.MyLinkedList;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class BST<T extends Comparable<T>> implements ITree<T> {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    protected T dato;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    protected BST<T> der;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    protected BST<T> izq;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    protected int tamano;

    //Constructor vacio
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public BST(){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.dato=null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.izq=null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.der=null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.tamano=0;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isEmpty(){
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return this.dato==null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    ////OPERACIONES//////////////////////////////////////////////////
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void add(T nuevo){
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.dato==null){
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            this.dato=nuevo;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }else{
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if(nuevo.compareTo(this.dato)<0){
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if(this.izq==null){
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    this.izq=new BST<>();
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                this.izq.add(nuevo);
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                tamano++;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }else if(nuevo.compareTo(this.dato)>0){
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (this.der==null){
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    this.der=new BST<>();
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                this.der.add(nuevo);
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                tamano++;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public MyLinkedList<T> getSubArbolIzquierda() {
        // Si no hay hijo izquierdo:
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.izq == null) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return new MyLinkedList<>();//Para evitar que devuelva null
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Si existe:
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return this.izq.getListaPreOrden();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public MyLinkedList<T> getSubArbolDerecha() {
        // Si no hay hijo derecho:
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.der == null) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return new MyLinkedList<>();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Si existe:
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return this.der.getListaPreOrden();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    ///////////////////////////////////////////////////////////
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isArbolHomogeneo(){
        //Primer caso: el arbol esta vacio
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.dato==null){
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return true;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        //Segundo caso: el arbol es solo raiz
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.izq==null && this.der==null){
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return true;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        //Tercer caso(falso): el arbol solo tiene un hijo
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (((this.izq==null) && (this.der!=null))|| ((this.izq!=null) && (this.der==null))){
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return false;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return this.izq.isArbolHomogeneo() && this.der.isArbolHomogeneo();
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isArbolCompleto(){
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.dato==null){
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return true;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        //Caso base: que no tenga hijos(si tiene uno no es homogeneo)
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.der==null && this.izq==null){
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return true;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        //Contar ambos lados
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int altIzq=0;
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.izq!=null){
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            altIzq=this.izq.getAltura();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }else{
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            altIzq=0;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int altDer=0;
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.der!=null){
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            altDer=this.der.getAltura();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }else{
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            altDer=0;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comprobamos la altura Y ADEMÃS obligamos a los hijos a autocomprobarse
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        boolean alturaCorrecta = (altIzq == altDer);

        // Si el hijo es null, no necesita comprobaciÃ³n (es correcto).
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        boolean izqPerfecto = (this.izq == null) || this.izq.isArbolCompleto();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        boolean derPerfecto = (this.der == null) || this.der.isArbolCompleto();

        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return alturaCorrecta && izqPerfecto && derPerfecto;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    //Para hacer el arbol casi completo necesitamos comprobar comprobar que el numero de nodos es igual al de posiciones
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean isArbolCasiCompleto(){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int numNodos=this.contarNodos();
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return compararPosiciones(0,numNodos);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int contarNodos(){
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.dato==null){
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return 0;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int contador=1;
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.izq != null){
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            contador += this.izq.contarNodos();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.der != null){
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            contador += this.der.contarNodos();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return contador;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean compararPosiciones(int indice, int numNodos){
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.dato == null) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return true;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        //hay un salto de posiciones en el lado izquierdo
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (indice >=numNodos) return false;
        // Hijo izquierdo: 2*i + 1
        // Hijo derecho: 2*i + 2
        // Para el hijo izquierdo
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        boolean izqCorrecto;
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.izq == null) {
            // Si no hay hijo, es correcto (no rompe la regla)
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            izqCorrecto = true;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } else {
            // Si hay hijo comprobamos
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            izqCorrecto = this.izq.compararPosiciones(2 * indice + 1, numNodos);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

// Para el hijo derecho
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        boolean derCorrecto;
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.der == null) {
            // Si no hay hijo, es correcto
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            derCorrecto = true;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } else {
            // Si hay hijo comprobamos
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            derCorrecto = this.der.compararPosiciones(2 * indice + 2, numNodos);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return izqCorrecto && derCorrecto;

    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public MyLinkedList<T> getListaDatosNivel(int nivel) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<T> resultado = new MyLinkedList<>();

        // 1. Si el Ã¡rbol estÃ¡ vacÃ­o, devolvemos la lista vacÃ­a
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.isEmpty()) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new RuntimeException("El Ã¡rbol estÃ¡ vacÃ­o");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // 2. CASO BASE: Si llegamos al nivel 0, hemos llegado al destino
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (nivel == 0) {
            //hacemos add para aÃ±adir el dato de ese nivel y addAll para aÃ±adir lso elementos de los ninbeles inferiores
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            resultado.add(this.dato);
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return resultado;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // 3. PASO RECURSIVO: Si aÃºn no es 0, bajamos un nivel
        // Le pedimos a los hijos que busquen en el nivel anterior (nivel - 1)
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.izq != null) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            resultado.addAll(this.izq.getListaDatosNivel(nivel - 1));
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.der != null) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            resultado.addAll(this.der.getListaDatosNivel(nivel - 1));
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return resultado;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Dentro de tu clase de Nodo o Arbol:
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public MyLinkedList<T> getCamino(T valorBuscado) {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.dato == null) {
            // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
            throw new RuntimeException("El Ã¡rbol estÃ¡ vacÃ­o");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<T> camino = new MyLinkedList<>();
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        camino.add(this.dato); // AÃ±adimos el actual

        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.dato.equals(valorBuscado)) {
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return camino; // Â¡Encontrado!
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Buscamos en el hijo correcto segÃºn el valor
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (valorBuscado.compareTo(this.dato) < 0 && this.izq != null) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            MyLinkedList<T> caminoIzq = this.izq.getCamino(valorBuscado);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (caminoIzq != null) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                camino.addAll(caminoIzq); // Unimos el camino
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return camino;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } else if (valorBuscado.compareTo(this.dato) > 0 && this.der != null) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            MyLinkedList<T> caminoDer = this.der.getCamino(valorBuscado);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (caminoDer != null) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                camino.addAll(caminoDer); // Unimos el camino
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return camino;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se lanza un error porque algo no es valido.
        throw new RuntimeException("El valor no existe en el Ã¡rbol");// No estÃ¡ en ninguna rama
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    //Altura y grado
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getAltura() {
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.dato==null){
            // Comentario de estudiante: aqui se devuelve el resultado del metodo.
            return 0;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        //Altura en el lado izquierdo
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int altIzq =0;
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.izq != null){
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            altIzq=this.izq.getAltura();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }else{
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            altIzq=0;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        //Altura en el lado derecho
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int altDer=0;
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if(this.der !=null){
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            altDer=this.der.getAltura();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }else{
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            altDer=0;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return 1 +Math.max(altIzq,altDer);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public int getGrado() {
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return 2; // Por definiciÃ³n de Ã¡rbol binario en este ejercicio
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    /////ORDEN///////////////////////////////////////////////////////////////
    //RECURSIVIDAD: CADA LLAMADA ES INDIVIDUAL
    //RECORRIDO ORDEN CENTRAL(Izquierda -> RaÃ­z -> Derecha)
    //se ordenan de menor a mayor, como si aplastaras el arbol contra el suelo)
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public MyLinkedList<T> getListaOrdenCentral(){
        //En esta lista voy aÃ±adiendo los elementos de izq o der
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<T> lista = new MyLinkedList<>();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if(this.dato != null){
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if(this.izq != null){
                //Si no es nulo usamos recursividad para repetir el proceso
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                lista.addAll(this.izq.getListaOrdenCentral());}
            //Se hace para cada llamada
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            lista.add(this.dato);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (this.der != null){
                //Hacemos addAll para pasar de la estructura jerarquica(arbol) a lineal(lista)
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                lista.addAll(this.der.getListaOrdenCentral());}
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return lista;

    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // RECORRIDO EN PRE-ORDEN (RaÃ­z - Izquierda - Derecha)
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public MyLinkedList<T> getListaPreOrden() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<T> lista = new MyLinkedList<>();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.dato != null) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            lista.add(this.dato);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (this.izq != null) lista.addAll(this.izq.getListaPreOrden());
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (this.der != null) lista.addAll(this.der.getListaPreOrden());
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return lista;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // RECORRIDO EN POST-ORDEN (Izquierda - Derecha - RaÃ­z)
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public MyLinkedList<T> getListaPostOrden() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<T> lista = new MyLinkedList<>();
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (this.dato != null) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (this.izq != null) lista.addAll(this.izq.getListaPostOrden());
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (this.der != null) lista.addAll(this.der.getListaPostOrden());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            lista.add(this.dato);
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return lista;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
