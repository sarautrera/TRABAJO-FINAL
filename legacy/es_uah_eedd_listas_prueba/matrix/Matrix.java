// Comentario de estudiante: aqui se indica a que paquete pertenece esta clase.
package es.uah.eedd.listas.prueba.matrix;

// Comentario de estudiante: aqui empieza una clase, que agrupa datos y metodos.
public class Matrix {
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int[][] matrix;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private String[] nodoNames;
    // Comentario de estudiante: aqui se declara una variable o constante de la clase.
    private int size;

    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public Matrix(int capacidad){
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.matrix=new int[capacidad][capacidad];
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.nodoNames=new String[capacidad];
        // Comentario de estudiante: aqui se guarda o actualiza un valor.
        this.size=0;
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }

    //Para registrar que nombre corresponde a cada indice
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void addNodoName(int index, String name){
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (index < nodoNames.length) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            nodoNames[index] = name;
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    //Poner bordes
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void addBorde(int from, int to){
        // Comentario de estudiante: aqui se comprueba una condicion con if.
        if (from < matrix.length && to < matrix.length) {
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            matrix[from][to] = 1;
            // Comentario de estudiante: aqui se guarda o actualiza un valor.
            matrix[to][from] = 1; // Bidireccional para el mapa
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
    // Comentario de estudiante: aqui empieza un metodo o constructor.
    public void print() {
        // Comentario de estudiante: aqui se prepara una instruccion del programa.
        System.out.println("\n--- MATRIZ DE ADYACENCIA (Mapa del Juego) ---");
        // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
        for (int i = 0; i < nodoNames.length && nodoNames[i] != null; i++) {
            // Comentario de estudiante: aqui empieza un bucle for para repetir codigo.
            for (int j = 0; j < nodoNames.length && nodoNames[j] != null; j++) {
                // Comentario de estudiante: aqui se prepara una instruccion del programa.
                System.out.print(matrix[i][j] + " ");
            // Comentario de estudiante: aqui se cierra un bloque de codigo.
            }
            // Comentario de estudiante: aqui se prepara una instruccion del programa.
            System.out.println(" [" + nodoNames[i] + "]");
        // Comentario de estudiante: aqui se cierra un bloque de codigo.
        }
    // Comentario de estudiante: aqui se cierra un bloque de codigo.
    }
// Comentario de estudiante: aqui se cierra un bloque de codigo.
}
