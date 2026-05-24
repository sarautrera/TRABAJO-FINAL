package es.uah.eedd.listas.prueba.RoomGraph;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import es.uah.eedd.listas.prueba.matrix.Matrix;
import es.uah.eedd.listas.prueba.queue.Queue;

import java.io.FileReader;
public class RoomGraph<T extends Comparable<T>>implements IGraph<T> {
    protected Nodo<T> primero;
    protected int tamaño;

    public RoomGraph(){
        this.primero=null;
        this.tamaño=0;
    }
    @Override
    public void add(T sujeto, String predicado, T objeto){
        //debemos comprobar que no se repite porque si un sujeto esta relacionado con varios predicados los trata como elementos independientes y no los relaciona entre ellos
        Nodo<T> nodoSujeto= getSujeto(sujeto);
        //Si no existe, lo creamos
        if(nodoSujeto==null){
            nodoSujeto=new Nodo<>(sujeto);
            nodoSujeto.siguiente = primero;
            primero=nodoSujeto;
        }
        //Añadimos la arista a este objeto
        AristaDelGrafo<T> nuevaArista=new AristaDelGrafo<>(predicado, objeto);
        nuevaArista.siguiente=nodoSujeto.listaAristas;
        nodoSujeto.listaAristas=nuevaArista;
    }
    @Override
    public Nodo<T> getSujeto(T sujeto){
        Nodo<T> actual=primero;
        while(actual != null){
            if(actual.sujeto.equals(sujeto)){
                return actual;
            }
            actual=actual.siguiente;
        }
        return null;
    }
    @Override
    public Queue<T> minCamino(T a, T b){
        Queue<T> cola = new Queue<>();
        ListaEnlazadaSimple<T> visitados = null;

        cola.enqueue(a);
        visitados = agregarALista(visitados, a, null);

        T ultimoEncontrado = null; // Para guardar dónde terminamos

        while (!cola.isEmpty()){
            T actual = cola.dequeue();

            if(actual.equals(b)){
                ultimoEncontrado = actual;
                break; // Salimos del bucle
            }

            Nodo<T> nodoActual = getSujeto(actual);
            if(nodoActual != null){
                AristaDelGrafo<T> arista = nodoActual.listaAristas;
                while(arista != null){
                    if(!estaEnLista(visitados, arista.objeto)){
                        cola.enqueue(arista.objeto);
                        visitados = agregarALista(visitados, arista.objeto, actual); // Guardamos el padre
                    }
                    arista = arista.siguiente;
                }
            }
        }
        return reconstruirCamino(visitados, b);
    }
    // Método auxiliar necesario
    private Queue<T> reconstruirCamino(ListaEnlazadaSimple<T> historial, T destino) {
        Queue<T> camino = new Queue<>();
        // Usaremos una lista temporal para invertir el orden (porque vamos de atrás hacia adelante)
        ListaEnlazadaSimple<T> reverso = null;
        T actual = destino;

        // 1. Retrocedemos desde el destino hasta el origen
        while (actual != null) {
            // Buscamos el nodo actual en el historial para saber quién es su padre
            ListaEnlazadaSimple<T> temp = historial;
            while (temp != null && !temp.nodo.equals(actual)) {
                temp = temp.siguiente;
            }

            // Si no encontramos el nodo en el historial, es que no hay camino
            if (temp == null) break;

            // Añadimos el nodo a nuestra lista de camino (el primero que entra es el destino)
            reverso = agregarALista(reverso, actual, null);

            // Saltamos al padre para seguir retrocediendo
            actual = temp.padre;
        }

        // 2. Pasamos los nodos a la cola (ahora están en orden correcto: Inicio -> ... -> Fin)
        while (reverso != null) {
            camino.enqueue(reverso.nodo);
            reverso = reverso.siguiente;
        }

        return camino;
    }
    //Lo necesitamos para agregar los elementos a una lista temporal y poder recorrerla y encontrar e que necesitabamos
    @Override
    public ListaEnlazadaSimple<T> agregarALista(ListaEnlazadaSimple<T> lista, T nodo, T padre){
        ListaEnlazadaSimple<T> actualizado=new ListaEnlazadaSimple<>(nodo, padre);
        //El nuevo registro reemplaza al antiguo
        actualizado.siguiente=lista;
        return actualizado;
    }
    @Override
    public boolean estaEnLista(ListaEnlazadaSimple<T> lista, T dato){
        ListaEnlazadaSimple<T> actual=lista;
        while(actual!=null){
            if(actual.nodo.equals(dato)){
                return true;
            }
            actual=actual.siguiente;
        }
        return false;
    }
    @Override
    public void cargarArchivo(String nombreArchivo) {
        try (FileReader reader = new FileReader(nombreArchivo)) {
            // 1. Usamos JsonParser para leer el archivo directamente
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();

            // 2. Accedemos al array "tripletas" que está dentro del JSON
            JsonArray listaTripletas = root.getAsJsonArray("tripletas");

            // 3. Recorremos cada elemento del array sin necesidad de una clase extra
            for (JsonElement elemento : listaTripletas) {
                JsonObject tripleta = elemento.getAsJsonObject();

                // Extraemos los valores usando las claves del JSON ("s", "p", "o")
                String s = tripleta.get("s").getAsString();
                String p = tripleta.get("p").getAsString();
                String o = tripleta.get("o").getAsString();

                // 4. Llamamos a tu método existente para añadir al grafo
                // Hacemos cast a (T) porque el grafo es genérico
                add((T) s, p, (T) o);
            }

            System.out.println("Archivo cargado correctamente usando Gson.");

        } catch (Exception e) {
            System.out.println("Error al procesar el archivo JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public Queue<T> buscarFisicoMismaCiudad(T fisicoObjetivo) {
        // --- PASO 1: Encontrar la ciudad del físico objetivo ---
        T ciudadObjetivo = null;
        Nodo<T> nodoObjetivo = getSujeto(fisicoObjetivo);

        if (nodoObjetivo != null) {
            AristaDelGrafo<T> arista = nodoObjetivo.listaAristas;
            while (arista != null) {
                if (arista.predicado.equals("nace_en")) {
                    ciudadObjetivo = arista.objeto;
                    break;
                }
                arista = arista.siguiente;
            }
        }

        if (ciudadObjetivo == null) return null;

        // --- PASO 2: Buscar a todos con esa ciudad y meterlos en la COLA ---
        Queue<T> resultados = new Queue<>();
        Nodo<T> actual = primero;

        while (actual != null) {
            AristaDelGrafo<T> arista = actual.listaAristas;
            while (arista != null) {
                if (arista.predicado.equals("nace_en") && arista.objeto.equals(ciudadObjetivo)) {
                    resultados.enqueue(actual.sujeto);
                    break;
                }
                arista = arista.siguiente;
            }
            actual = actual.siguiente;
        }

        return resultados;
    }
    @Override
    public void imprimirGrafo() {
        System.out.println("\n--- ESTADO ACTUAL DEL GRAFO ---");
        Nodo<T> actual = primero;
        while (actual != null) {
            System.out.print("Sujeto: " + actual.sujeto + " -> ");
            AristaDelGrafo<T> arista = actual.listaAristas;
            while (arista != null) {
                System.out.print("[" + arista.predicado + ": " + arista.objeto + "] ");
                arista = arista.siguiente;
            }
            System.out.println();
            actual = actual.siguiente;
        }
        System.out.println("-------------------------------\n");
    }
    @Override
    public Queue<T> listarLugaresNacimientoNobelistas() {
        Queue<T> resultados = new Queue<>(); // Creas tu objeto Cola
        Nodo<T> actual = primero;

        while (actual != null) {
            AristaDelGrafo<T> arista = actual.listaAristas;
            while (arista != null) {
                if (arista.predicado.equals("nace_en")) {
                    resultados.enqueue(arista.objeto); //
                    break;
                }
                arista = arista.siguiente;
            }
            actual = actual.siguiente;
        }
        return resultados;
    }

    // Método auxiliar para buscar el lugar específico
    private T buscarLugarNacimiento(Nodo<T> nodo) {
        AristaDelGrafo<T> arista = nodo.listaAristas;
        while (arista != null) {
            // Usar equalsIgnoreCase y trim para mayor robustez
            if (arista.predicado != null && arista.predicado.trim().equalsIgnoreCase("nace_en")) {
                return arista.objeto;
            }
            arista = arista.siguiente;
        }
        return null;
    }
    private boolean esNobel(Nodo<T> nodo) {
        AristaDelGrafo<T> arista = nodo.listaAristas;
        while (arista != null) {
            if (arista.predicado != null) {
                String pred = arista.predicado.trim().toLowerCase();
                // Verifica si el predicado contiene "premio" y el objeto (si es String) contiene "nobel"
                // O simplemente si el predicado es "premio:nobel"
                if (pred.contains("premio:nobel") || pred.equals("premio:nobel")) {
                    return true;
                }
            }
            arista = arista.siguiente;
        }
        return false;
    }
    ////////////////////Convertir a matriz///////////////////////////////
    @Override
    public Matrix getAsMatrix() {
        Matrix matriz = new Matrix(20); // Capacidad de 20 casillas

        // 1. Mapear nombres a índices(a cada habitacion se le asigna un index)
        String[] mapear = new String[20];
        int count = 0;
        Nodo<T> actual = primero;
        while (actual != null && count < 20) {
            mapear[count] = actual.sujeto.toString();
            matriz.addNodoName(count, mapear[count]);
            actual = actual.siguiente;
            count++;
        }

        // 2. Traducir conexiones de tripletas a la matriz(buscamos el predicado 'conecta')
        actual = primero;
        for (int i = 0; i < count; i++) {
            AristaDelGrafo<T> arista = actual.listaAristas;
            while (arista != null) {
                if (arista.predicado.equals("conecta")) {
                    int j = findIndex(mapear, arista.objeto.toString(), count);
                    if (j != -1) matriz.addBorde(i, j);
                }
                arista = arista.siguiente;
            }
            actual = actual.siguiente;
        }
        return matriz;
    }

    private int findIndex(String[] arr, String target, int limite) {
        for (int i = 0; i < limite; i++) {
            if (arr[i].equals(target)) return i;
        }
        return -1;
    }
}

