/*
 * Resumen del fichero: Conserva una implementacion antigua de Matrix usada como referencia del trabajo.
 */
package es.uah.eedd.listas.prueba.matrix;

public class Matrix {
    private int[][] matrix;
    private String[] nodoNames;
    private int size;

    public Matrix(int capacidad){
        this.matrix=new int[capacidad][capacidad];
        this.nodoNames=new String[capacidad];
        this.size=0;
    }

    public void addNodoName(int index, String name){
        if (index < nodoNames.length) {
            nodoNames[index] = name;
        }
    }
    public void addBorde(int from, int to){
        if (from < matrix.length && to < matrix.length) {
            matrix[from][to] = 1;
            matrix[to][from] = 1;
        }
    }
    public void print() {
        System.out.println("\n--- MATRIZ DE ADYACENCIA (Mapa del Juego) ---");
        for (int i = 0; i < nodoNames.length && nodoNames[i] != null; i++) {
            for (int j = 0; j < nodoNames.length && nodoNames[j] != null; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(" [" + nodoNames[i] + "]");
        }
    }
}
