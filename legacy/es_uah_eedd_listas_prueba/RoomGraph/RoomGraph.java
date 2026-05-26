/*
 * Resumen del fichero: Conserva una implementacion antigua de RoomGraph usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.RoomGraph;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import es.uah.eedd.listas.prueba.matrix.Matrix;
import es.uah.eedd.listas.prueba.queue.Queue;
import es.uah.eedd.listas.prueba.simple.MyLinkedList;

import java.io.FileReader;
public class RoomGraph<T extends Comparable<T>>implements IGraph<T> {
    protected Nodo<T> primero;
    protected int tamano;

    public RoomGraph(){
        this.primero=null;
        this.tamano=0;
    }
    @Override
    public void add(T sujeto, String predicado, T objeto){
        Nodo<T> nodoSujeto= getSujeto(sujeto);
        if(nodoSujeto==null){
            nodoSujeto=new Nodo<>(sujeto);
            nodoSujeto.siguiente = primero;
            primero=nodoSujeto;
        }
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

        T ultimoEncontrado = null;

        while (!cola.isEmpty()){
            T actual = cola.dequeue();

            if(actual.equals(b)){
                ultimoEncontrado = actual;
                break;
            }

            Nodo<T> nodoActual = getSujeto(actual);
            if(nodoActual != null){
                AristaDelGrafo<T> arista = nodoActual.listaAristas;
                while(arista != null){
                    if(!estaEnLista(visitados, arista.objeto)){
                        cola.enqueue(arista.objeto);
                        visitados = agregarALista(visitados, arista.objeto, actual);
                    }
                    arista = arista.siguiente;
                }
            }
        }
        return reconstruirCamino(visitados, b);
    }
    private Queue<T> reconstruirCamino(ListaEnlazadaSimple<T> historial, T destino) {
        Queue<T> camino = new Queue<>();
        ListaEnlazadaSimple<T> reverso = null;
        T actual = destino;

        while (actual != null) {
            ListaEnlazadaSimple<T> temp = historial;
            while (temp != null && !temp.nodo.equals(actual)) {
                temp = temp.siguiente;
            }

            if (temp == null) break;

            reverso = agregarALista(reverso, actual, null);

            actual = temp.padre;
        }

        while (reverso != null) {
            camino.enqueue(reverso.nodo);
            reverso = reverso.siguiente;
        }

        return camino;
    }
    @Override
    public ListaEnlazadaSimple<T> agregarALista(ListaEnlazadaSimple<T> lista, T nodo, T padre){
        ListaEnlazadaSimple<T> actualizado=new ListaEnlazadaSimple<>(nodo, padre);
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
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();

            JsonArray listaTripletas = root.getAsJsonArray("tripletas");

            for (JsonElement elemento : listaTripletas) {
                JsonObject tripleta = elemento.getAsJsonObject();

                String s = tripleta.get("s").getAsString();
                String p = tripleta.get("p").getAsString();
                String o = tripleta.get("o").getAsString();

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
        Queue<T> resultados = new Queue<>();
        Nodo<T> actual = primero;

        while (actual != null) {
            AristaDelGrafo<T> arista = actual.listaAristas;
            while (arista != null) {
                if (arista.predicado.equals("nace_en")) {
                    resultados.enqueue(arista.objeto);
                    break;
                }
                arista = arista.siguiente;
            }
            actual = actual.siguiente;
        }
        return resultados;
    }

    private T buscarLugarNacimiento(Nodo<T> nodo) {
        AristaDelGrafo<T> arista = nodo.listaAristas;
        while (arista != null) {
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
                if (pred.contains("premio:nobel") || pred.equals("premio:nobel")) {
                    return true;
                }
            }
            arista = arista.siguiente;
        }
        return false;
    }
    @Override
    public Matrix getAsMatrix() {
        Matrix matriz = new Matrix(20);

        String[] mapear = new String[20];
        int count = 0;
        Nodo<T> actual = primero;
        while (actual != null && count < 20) {
            mapear[count] = actual.sujeto.toString();
            matriz.addNodoName(count, mapear[count]);
            actual = actual.siguiente;
            count++;
        }

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
    public static MyLinkedList<Integer> getAlcanzable(int[][] matrix, int start, int speed){
        MyLinkedList<Integer> alcanzable=new MyLinkedList<>();
        Queue<Integer> q=new Queue<>();
        int n=matrix.length;
        int[] distancia=new int[n];
        for(int i=0; i<n;i++){
            distancia[i]=-1;
        }
        distancia[start]=0;
        q.enqueue(start);

        while(!q.isEmpty()){
            int curr = q.dequeue();

            if(distancia[curr] < speed){
                for(int neighbor=0; neighbor < n; neighbor++){
                    if(matrix[curr][neighbor] > 0 && distancia[neighbor] == -1){
                        distancia[neighbor] = distancia[curr] + 1;
                        alcanzable.add(neighbor);
                        q.enqueue(neighbor);
                    }
                }
            }
        }
        return alcanzable;
    }
    public static int[] dijkstra(int[][] matrix, int start) {
        int n = matrix.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dist[start] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && (u == -1 || dist[i] < dist[u])) {
                    u = i;
                }
            }

            if (dist[u] == Integer.MAX_VALUE) break;

            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (matrix[u][v] > 0 && !visited[v]) {
                    int newDist = dist[u] + matrix[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                    }
                }
            }
        }
        return dist;
    }
}

