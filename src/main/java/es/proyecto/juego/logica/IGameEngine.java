package es.proyecto.juego.logica;

import es.proyecto.juego.estructuras.IList;

/**
 * Interfaz principal del Motor del Juego (Track B).
 * Define todas las operaciones de control, persistencia y acciones de combate/exploración
 * que la interfaz gráfica (JavaFX) puede solicitar durante la partida.
 */
public interface IGameEngine {

    /**
     * Carga la configuración inicial de un nivel desde un archivo JSON.
     * @param jsonPath Ruta del archivo de configuración del nivel.
     */
    void loadConfig(String jsonPath);

    /**
     * Inicializa una nueva partida restableciendo los parámetros del jugador y el mapa.
     */
    void newGame();

    /**
     * Recupera el estado de una partida previamente guardada desde un archivo JSON.
     * @param jsonPath Ruta del archivo de guardado.
     */
    void loadGame(String jsonPath);

    /**
     * Serializa y guarda el estado actual de la partida en un archivo de disco.
     * @param jsonPath Ruta del archivo donde se guardará el estado.
     */
    void saveGame(String jsonPath);

    /**
     * Mueve al jugador a las coordenadas especificadas si la celda es válida y alcanzable.
     * @param row Fila de destino.
     * @param col Columna de destino.
     * @return true si el movimiento se realizó con éxito, false en caso contrario.
     */
    boolean movePlayer(int row, int col);

    /**
     * Ejecuta un ataque hacia una celda objetivo.
     * @param r Fila del objetivo.
     * @param c Columna del objetivo.
     * @return true si el ataque fue válido y se ejecutó, false si no había un objetivo válido.
     */
    boolean attack(int r, int c);

    /**
     * Utiliza o equipa un objeto del inventario del jugador según su posición secuencial.
     * @param index Índice del objeto dentro del inventario.
     * @return true si el objeto pudo ser utilizado con éxito en este turno.
     */
    boolean useItem(int index);

    /**
     * Intenta recoger un objeto del suelo en la celda indicada.
     * @param r Fila de la celda.
     * @param c Columna de la celda.
     * @return true si se recogió el objeto y se añadió al inventario, false si no había nada.
     */
    boolean pickItem(int r, int c);

    /**
     * Intenta interactuar y abrir la puerta ubicada en la celda indicada.
     * @param r Fila de la puerta.
     * @param c Columna de la puerta.
     * @return true si la puerta se abrió con éxito (cumpliendo requisitos), false en caso contrario.
     */
    boolean openDoor(int r, int c);

    /**
     * Finaliza el turno actual del jugador, cediendo el control a la IA de los monstruos
     * o actualizando los estados del mapa.
     */
    void endTurn();

    /**
     * Obtiene el estado actual e inmutable del juego (vida, turnos, posiciones).
     * @return Un objeto que implementa IGameState.
     */
    IGameState getState();

    /**
     * Calcula y devuelve las celdas a las que el jugador puede desplazarse en su turno actual.
     * @return Una lista personalizada (IList) con arrays de coordenadas [fila, columna].
     */
    IList<int[]> getReachableCells();

    /**
     * Identifica qué celdas contienen enemigos al alcance de las armas del jugador.
     * @return Una lista personalizada (IList) con las posiciones de los objetivos válidos.
     */
    IList<int[]> getAttackTargets();
}