// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.RoomGraph;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import com.google.gson.Gson;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import com.google.gson.JsonArray;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import com.google.gson.JsonElement;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import com.google.gson.JsonObject;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import com.google.gson.JsonParser;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.uah.eedd.listas.prueba.matrix.Matrix;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.uah.eedd.listas.prueba.queue.Queue;
// Comentario de estudiante: aqui se importa una clase que se va a usar.
import es.uah.eedd.listas.prueba.simple.MyLinkedList;

// Comentario de estudiante: aqui se importa una clase que se va a usar.
import java.io.FileReader;
// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class RoomGraph<T extends Comparable<T>>implements IGraph<T> {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    protected Nodo<T> primero;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    protected int tamano;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public RoomGraph(){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.primero=null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.tamano=0;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void add(T sujeto, String predicado, T objeto){
        //debemos comprobar que no se repite porque si un sujeto esta relacionado con varios predicados los trata como elementos independientes y no los relaciona entre ellos
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Nodo<T> nodoSujeto= getSujeto(sujeto);
        //Si no existe, lo creamos
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if(nodoSujeto==null){
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            nodoSujeto=new Nodo<>(sujeto);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            nodoSujeto.siguiente = primero;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            primero=nodoSujeto;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        //AÃ±adimos la arista a este objeto
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        AristaDelGrafo<T> nuevaArista=new AristaDelGrafo<>(predicado, objeto);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        nuevaArista.siguiente=nodoSujeto.listaAristas;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        nodoSujeto.listaAristas=nuevaArista;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Nodo<T> getSujeto(T sujeto){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Nodo<T> actual=primero;
        // Comentario de estudiante: aqui empieza un bucle while.
        while(actual != null){
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if(actual.sujeto.equals(sujeto)){
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return actual;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            actual=actual.siguiente;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Queue<T> minCamino(T a, T b){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Queue<T> cola = new Queue<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        ListaEnlazadaSimple<T> visitados = null;

        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        cola.enqueue(a);
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        visitados = agregarALista(visitados, a, null);

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        T ultimoEncontrado = null; // Para guardar dÃ³nde terminamos

        // Comentario de estudiante: aqui empieza un bucle while.
        while (!cola.isEmpty()){
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            T actual = cola.dequeue();

            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if(actual.equals(b)){
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                ultimoEncontrado = actual;
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                break; // Salimos del bucle
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            Nodo<T> nodoActual = getSujeto(actual);
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if(nodoActual != null){
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                AristaDelGrafo<T> arista = nodoActual.listaAristas;
                // Comentario de estudiante: aqui empieza un bucle while.
                while(arista != null){
                    // Comentario de estudiante: aqui se comprueba una condicion con if.
                    if(!estaEnLista(visitados, arista.objeto)){
                        // Comentario de estudiante: aqui se prepara una instruccion del programa.
                        cola.enqueue(arista.objeto);
                        // Comentario de estudiante: aqui se guarda o actualiza un valor.
                        visitados = agregarALista(visitados, arista.objeto, actual); // Guardamos el padre
                    // Comentario de estudiante: aqui se cierra un bloque de codigo.
                    }
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    arista = arista.siguiente;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return reconstruirCamino(visitados, b);
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // MÃ©todo auxiliar necesario
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private Queue<T> reconstruirCamino(ListaEnlazadaSimple<T> historial, T destino) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Queue<T> camino = new Queue<>();
        // Usaremos una lista temporal para invertir el orden (porque vamos de atrÃ¡s hacia adelante)
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        ListaEnlazadaSimple<T> reverso = null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        T actual = destino;

        // 1. Retrocedemos desde el destino hasta el origen
        // Comentario de estudiante: aqui empieza un bucle while.
        while (actual != null) {
            // Buscamos el nodo actual en el historial para saber quiÃ©n es su padre
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            ListaEnlazadaSimple<T> temp = historial;
            // Comentario de estudiante: aqui empieza un bucle while.
            while (temp != null && !temp.nodo.equals(actual)) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                temp = temp.siguiente;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }

            // Si no encontramos el nodo en el historial, es que no hay camino
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (temp == null) break;

            // AÃ±adimos el nodo a nuestra lista de camino (el primero que entra es el destino)
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            reverso = agregarALista(reverso, actual, null);

            // Saltamos al padre para seguir retrocediendo
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            actual = temp.padre;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // 2. Pasamos los nodos a la cola (ahora estÃ¡n en orden correcto: Inicio -> ... -> Fin)
        // Comentario de estudiante: aqui empieza un bucle while.
        while (reverso != null) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            camino.enqueue(reverso.nodo);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            reverso = reverso.siguiente;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return camino;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    //Lo necesitamos para agregar los elementos a una lista temporal y poder recorrerla y encontrar e que necesitabamos
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public ListaEnlazadaSimple<T> agregarALista(ListaEnlazadaSimple<T> lista, T nodo, T padre){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        ListaEnlazadaSimple<T> actualizado=new ListaEnlazadaSimple<>(nodo, padre);
        //El nuevo registro reemplaza al antiguo
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        actualizado.siguiente=lista;
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return actualizado;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public boolean estaEnLista(ListaEnlazadaSimple<T> lista, T dato){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        ListaEnlazadaSimple<T> actual=lista;
        // Comentario de estudiante: aqui empieza un bucle while.
        while(actual!=null){
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if(actual.nodo.equals(dato)){
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return true;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            actual=actual.siguiente;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return false;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void cargarArchivo(String nombreArchivo) {
        // Comentario de estudiante: aqui se intenta ejecutar codigo que puede fallar.
        try (FileReader reader = new FileReader(nombreArchivo)) {
            // 1. Usamos JsonParser para leer el archivo directamente
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();

            // 2. Accedemos al array "tripletas" que estÃ¡ dentro del JSON
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            JsonArray listaTripletas = root.getAsJsonArray("tripletas");

            // 3. Recorremos cada elemento del array sin necesidad de una clase extra
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (JsonElement elemento : listaTripletas) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                JsonObject tripleta = elemento.getAsJsonObject();

                // Extraemos los valores usando las claves del JSON ("s", "p", "o")
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                String s = tripleta.get("s").getAsString();
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                String p = tripleta.get("p").getAsString();
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                String o = tripleta.get("o").getAsString();

                // 4. Llamamos a tu mÃ©todo existente para aÃ±adir al grafo
                // Hacemos cast a (T) porque el grafo es genÃ©rico
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                add((T) s, p, (T) o);
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }

            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            System.out.println("Archivo cargado correctamente usando Gson.");

        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        } catch (Exception e) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            System.out.println("Error al procesar el archivo JSON: " + e.getMessage());
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            e.printStackTrace();
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Queue<T> buscarFisicoMismaCiudad(T fisicoObjetivo) {
        // --- PASO 1: Encontrar la ciudad del fÃ­sico objetivo ---
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        T ciudadObjetivo = null;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Nodo<T> nodoObjetivo = getSujeto(fisicoObjetivo);

        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (nodoObjetivo != null) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            AristaDelGrafo<T> arista = nodoObjetivo.listaAristas;
            // Comentario de estudiante: aqui empieza un bucle while.
            while (arista != null) {
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (arista.predicado.equals("nace_en")) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    ciudadObjetivo = arista.objeto;
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    break;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                arista = arista.siguiente;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (ciudadObjetivo == null) return null;

        // --- PASO 2: Buscar a todos con esa ciudad y meterlos en la COLA ---
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Queue<T> resultados = new Queue<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Nodo<T> actual = primero;

        // Comentario de estudiante: aqui empieza un bucle while.
        while (actual != null) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            AristaDelGrafo<T> arista = actual.listaAristas;
            // Comentario de estudiante: aqui empieza un bucle while.
            while (arista != null) {
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (arista.predicado.equals("nace_en") && arista.objeto.equals(ciudadObjetivo)) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    resultados.enqueue(actual.sujeto);
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    break;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                arista = arista.siguiente;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            actual = actual.siguiente;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return resultados;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void imprimirGrafo() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        System.out.println("\n--- ESTADO ACTUAL DEL GRAFO ---");
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Nodo<T> actual = primero;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (actual != null) {
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            System.out.print("Sujeto: " + actual.sujeto + " -> ");
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            AristaDelGrafo<T> arista = actual.listaAristas;
            // Comentario de estudiante: aqui empieza un bucle while.
            while (arista != null) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                System.out.print("[" + arista.predicado + ": " + arista.objeto + "] ");
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                arista = arista.siguiente;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            System.out.println();
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            actual = actual.siguiente;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        System.out.println("-------------------------------\n");
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Queue<T> listarLugaresNacimientoNobelistas() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Queue<T> resultados = new Queue<>(); // Creas tu objeto Cola
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Nodo<T> actual = primero;

        // Comentario de estudiante: aqui empieza un bucle while.
        while (actual != null) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            AristaDelGrafo<T> arista = actual.listaAristas;
            // Comentario de estudiante: aqui empieza un bucle while.
            while (arista != null) {
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (arista.predicado.equals("nace_en")) {
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    resultados.enqueue(arista.objeto); //
                    // Comentario de estudiante: aqui se prepara una instruccion del programa.
                    break;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                arista = arista.siguiente;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            actual = actual.siguiente;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return resultados;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // MÃ©todo auxiliar para buscar el lugar especÃ­fico
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private T buscarLugarNacimiento(Nodo<T> nodo) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        AristaDelGrafo<T> arista = nodo.listaAristas;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (arista != null) {
            // Usar equalsIgnoreCase y trim para mayor robustez
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (arista.predicado != null && arista.predicado.trim().equalsIgnoreCase("nace_en")) {
                // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                return arista.objeto;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            arista = arista.siguiente;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return null;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private boolean esNobel(Nodo<T> nodo) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        AristaDelGrafo<T> arista = nodo.listaAristas;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (arista != null) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (arista.predicado != null) {
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                String pred = arista.predicado.trim().toLowerCase();
                // Verifica si el predicado contiene "premio" y el objeto (si es String) contiene "nobel"
                // O simplemente si el predicado es "premio:nobel"
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (pred.contains("premio:nobel") || pred.equals("premio:nobel")) {
                    // Comentario de estudiante: aqui se devuelve el resultado del metodo.
                    return true;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            arista = arista.siguiente;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return false;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    ////////////////////Convertir a matriz///////////////////////////////
    // Comentario de estudiante: esta anotacion da informacion extra a Java o a JUnit.
    @Override
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Matrix getAsMatrix() {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Matrix matriz = new Matrix(20); // Capacidad de 20 casillas

        // 1. Mapear nombres a Ã­ndices(a cada habitacion se le asigna un index)
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        String[] mapear = new String[20];
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int count = 0;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Nodo<T> actual = primero;
        // Comentario de estudiante: aqui empieza un bucle while.
        while (actual != null && count < 20) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            mapear[count] = actual.sujeto.toString();
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            matriz.addNodoName(count, mapear[count]);
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            actual = actual.siguiente;
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            count++;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // 2. Traducir conexiones de tripletas a la matriz(buscamos el predicado 'conecta')
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        actual = primero;
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < count; i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            AristaDelGrafo<T> arista = actual.listaAristas;
            // Comentario de estudiante: aqui empieza un bucle while.
            while (arista != null) {
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (arista.predicado.equals("conecta")) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    int j = findIndex(mapear, arista.objeto.toString(), count);
                    // Comentario de estudiante: aqui se comprueba una condicion con if.
                    if (j != -1) matriz.addBorde(i, j);
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
                // Comentario de estudiante: aqui se guarda o actualiza un valor.
                arista = arista.siguiente;
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            actual = actual.siguiente;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return matriz;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    private int findIndex(String[] arr, String target, int limite) {
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < limite; i++) {
            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (arr[i].equals(target)) return i;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return -1;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    /// //CELDAS ALCANZABLES, con velocidad
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public static MyLinkedList<Integer> getAlcanzable(int[][] matrix, int start, int speed){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        MyLinkedList<Integer> alcanzable=new MyLinkedList<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        Queue<Integer> q=new Queue<>();
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int n=matrix.length;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int[] distancia=new int[n];
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for(int i=0; i<n;i++){
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            distancia[i]=-1;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        //Inicializamos distancia
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        distancia[start]=0;
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        q.enqueue(start);

        // Comentario de estudiante: aqui empieza un bucle while.
        while(!q.isEmpty()){
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int curr = q.dequeue();

            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if(distancia[curr] < speed){
                // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
                for(int neighbor=0; neighbor < n; neighbor++){
                    // Comentario de estudiante: aqui se comprueba una condicion con if.
                    if(matrix[curr][neighbor] > 0 && distancia[neighbor] == -1){
                        // Comentario de estudiante: aqui se guarda o actualiza un valor.
                        distancia[neighbor] = distancia[curr] + 1;
                        // Comentario de estudiante: aqui se prepara una instruccion del programa.
                        alcanzable.add(neighbor);
                        // Comentario de estudiante: aqui se prepara una instruccion del programa.
                        q.enqueue(neighbor);
                    // Comentario de estudiante: aqui se cierra un bloque de codigo.
                    }
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return alcanzable;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    /// Algoritmo dijkstra
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public static int[] dijkstra(int[][] matrix, int start) {
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int n = matrix.length;
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        int[] dist = new int[n];
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        boolean[] visited = new boolean[n];

        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < n; i++) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            dist[i] = Integer.MAX_VALUE;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            visited[i] = false;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }

        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        dist[start] = 0;

        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int count = 0; count < n - 1; count++) {
            // Buscamos el nodo con la distancia mÃ­nima que no hayamos visitado
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            int u = -1;
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int i = 0; i < n; i++) {
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (!visited[i] && (u == -1 || dist[i] < dist[u])) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    u = i;
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }

            // Comentario de estudiante: aqui se comprueba una condicion con if.
            if (dist[u] == Integer.MAX_VALUE) break;

            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            visited[u] = true;

            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int v = 0; v < n; v++) {
                // Si hay conexiÃ³n (matrix[u][v] > 0)
                // Comentario de estudiante: aqui se comprueba una condicion con if.
                if (matrix[u][v] > 0 && !visited[v]) {
                    // Comentario de estudiante: aqui se guarda o actualiza un valor.
                    int newDist = dist[u] + matrix[u][v];
                    // Comentario de estudiante: aqui se comprueba una condicion con if.
                    if (newDist < dist[v]) {
                        // Comentario de estudiante: aqui se guarda o actualiza un valor.
                        dist[v] = newDist;
                    // Comentario de estudiante: aqui se cierra un bloque de codigo.
                    }
                // Comentario de estudiante: aqui se cierra un bloque de codigo.
                }
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
        // Comentario de estudiante: aqui se devuelve el resultado del metodo.
        return dist; // Devuelve un array con la distancia mÃ­nima a cada nodo
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}

