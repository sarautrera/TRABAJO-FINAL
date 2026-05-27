# Contexto para implementar el Track B - Logica del juego

Este documento resume el contexto necesario para desarrollar la parte B del proyecto, tomando como base:

- `docs/Practica Final 2026 MP EEDD.pdf` (archivo original con tilde en el nombre).
- `docs/01_Resumen_Ejecutivo.docx`.
- `docs/02_Documento_Avanzado.docx`.
- `docs/04_Track_B_Logica.docx`.
- Referencias de integracion con `03_Track_A_Estructuras.docx` y `05_Track_C_UI_Persistencia.docx`.

Nota sobre fechas: la entrega correcta es `28/05/2026 a las 10:00 h`. La referencia `28/5/2025` de la ultima pagina del PDF se considera una errata.

## Vision general del proyecto

El proyecto es un juego por turnos de exploracion de habitaciones con interfaz JavaFX, persistencia JSON y estructuras de datos propias. El jugador se mueve dentro de habitaciones representadas como matrices y entre habitaciones conectadas como un grafo. El objetivo es llegar desde la habitacion inicial hasta una puerta de salida exterior antes de morir o quedarse sin turnos.

La arquitectura se divide en tres tracks:

- Track A: estructuras de datos propias (`IList`, `IStack`, `IQueue`, `IGraph`, `ITree`, listas, pila, cola, lista circular, BST, grafo, BFS, Dijkstra).
- Track B: logica del juego, sin JavaFX. Esta es nuestra parte.
- Track C: interfaz JavaFX y persistencia JSON, usando `IGameEngine` e `IGameState`.

Regla de integracion: ningun track debe depender de clases concretas de otro track. Se usan interfaces acordadas en la fase 0. Si otro track no esta terminado, se usan stubs o mocks temporales.

## Responsabilidad del Track B

El Track B es el nucleo de reglas y estado del juego. Debe implementar:

- Jugador, enemigos, habitaciones, celdas e items.
- Movimiento dentro de una habitacion.
- Combate.
- Gestion de turnos.
- Inventario y uso/equipamiento de objetos.
- Apertura de puertas y cambio de habitacion.
- Condiciones de victoria y derrota.
- Logs de eventos.
- Excepciones de reglas invalidas.
- `GameEngineImpl`, que expone la logica a la UI mediante `IGameEngine`.
- `IGameState`, que expone el estado de solo lectura a la UI.
- Tests JUnit de todas las clases no visuales.

Restriccion importante: el paquete de logica no debe importar `javafx.*`.

## Dependencias permitidas y estrategia con stubs

Track B depende de interfaces de Track A, no de sus implementaciones:

- `IList<T>` para inventario, enemigos, logs, listas de celdas alcanzables, rutas, etc.
- `IGraph<Integer>` para grafo de habitaciones.
- `MatrixBFS` o una utilidad equivalente de A para movimiento dentro de la matriz, si ya existe.

Mientras A no entregue sus clases, B puede usar stubs temporales:

- `StubList<T> implements IList<T>` usando nodos propios, sin `ArrayList`, `HashMap`, `LinkedList` ni colecciones equivalentes de `java.util`.
- `StubGraph<T> implements IGraph<T>` si hace falta para desarrollar `PathFinder`.
- Cualquier stub debe estar claramente marcado como temporal y reemplazarse en la integracion 1.

La restriccion de no usar `ArrayList`, `HashMap`, `LinkedList` ni estructuras estandar equivalentes se aplicara tambien durante el desarrollo para evitar dependencias que luego haya que retirar.

## Interfaces de contrato

### `IGameEngine`

Track B debe implementar esta interfaz para que Track C pueda controlar el juego:

```java
public interface IGameEngine {
    void loadConfig(String jsonPath) throws IOException;
    void newGame();
    void loadGame(String jsonPath) throws IOException;
    void saveGame(String jsonPath) throws IOException;

    boolean movePlayer(int targetRow, int targetCol);
    boolean attack(int targetRow, int targetCol);
    boolean useItem(int inventoryIndex);
    boolean pickItem(int row, int col);
    boolean openDoor(int row, int col);
    void endTurn();

    IGameState getState();
    IList<int[]> getReachableCells();
    IList<int[]> getAttackTargets();
}
```

Punto a decidir con el grupo: el enunciado pide gestionar errores mediante excepciones; el documento avanzado indica que algunas acciones devuelven `true` si son validas. Una solucion coherente es:

- Devolver `true` si la accion se ejecuta.
- Devolver `false` solo para acciones no destructivas o intentos invalidos desde UI si asi se acuerda.
- Lanzar excepciones personalizadas cuando se viola una regla clara del dominio (`InvalidMoveException`, `InvalidAttackException`, etc.).

### `IGameState`

Debe exponer estado de solo lectura para la UI:

```java
public interface IGameState {
    int getPlayerRow();
    int getPlayerCol();
    int getPlayerHp();
    int getPlayerMaxHp();
    int getPlayerSpeed();
    int getPlayerAttack();
    int getPlayerDefense();
    String getEquippedWeaponName();
    String getEquippedArmorName();
    IList<Item> getInventory();
    int getMaxInventorySize();
    boolean isInventoryFull();

    int getCurrentRoomId();
    String getCurrentRoomName();
    int getCurrentRoomRows();
    int getCurrentRoomCols();
    Room getCurrentRoom();
    int getTurnCount();
    int getTurnsLeft();
    boolean canPlayerMove();
    boolean canPlayerAct();

    int getMinRoomsToExit();
    int getDistanceToNearestDoor();
    IList<Integer> getPathToExit();

    IList<String> getEventLog();
    String getLastEvent();
    boolean isGameOver();
    boolean isVictory();
}
```

La UI no debe modificar directamente el estado devuelto. Las mutaciones deben pasar por `IGameEngine`.

Estado real actual: `IGameState` ya expone tambien datos directos para UI (`currentRoomId`, nombre y dimensiones de sala, equipo y acciones disponibles). `GameStateSnapshot` devuelve copias defensivas de inventario, sala actual, ruta y log para que la UI no pueda mutar directamente el estado interno del motor.

## Modelo de dominio minimo

### `Player`

Atributos esperados:

- `hp`, `maxHp`.
- `speed`.
- `baseAttack`, `baseDefense`.
- `row`, `col`.
- `IList<Item> inventory`.
- Limite de inventario: 10 items (`Player.MAX_INVENTORY_SIZE`).
- Arma equipada.
- Armadura/defensa equipada, si se implementa.

Metodos clave:

- `getEffectiveAttack()`: ataque base + bonus de arma equipada.
- `getEffectiveDefense()`: defensa base + bonus de armadura/equipo.
- `takeDamage(int dmg)`: la vida nunca baja de 0.
- `heal(int amount)`: la vida nunca supera `maxHp`.
- `isAlive()`.
- Gestion de inventario y equipamiento.
- `addItem(Item item)` lanza `InventoryFullException` si el inventario ya tiene 10 items.

### `Enemy`

Atributos esperados:

- Nombre.
- Vida y vida maxima.
- Velocidad.
- Ataque.
- Defensa.
- Posicion `(row, col)`.
- Equipo ofensivo/defensivo opcional.

Comportamiento:

- Se mueve hacia el jugador usando BFS de matriz hasta una celda adyacente al jugador.
- Ataca si el jugador esta en rango.
- No es obligatorio que cambie de habitacion.

### `Room`

La habitacion es una matriz, no solo un nodo abstracto:

- `id`.
- `rows`, `cols`.
- `Cell[][] grid`.
- `IList<Enemy> enemies`.
- `visited`.

Metodos clave:

- `getCell(row, col)`.
- `setCell(row, col, cell)`.
- `getDoors()`.
- Validacion de coordenadas.

El grid puede ser un array bidimensional nativo. La matriz de habitaciones debe modelarse de forma clara y justificarse en la memoria.

### `Cell` y `CellType`

Tipos minimos:

```java
public enum CellType {
    EMPTY, ENEMY, ITEM, DOOR, TRAP, WALL
}
```

Campos sugeridos en `Cell`:

- `CellType type`.
- `Item item`, si `type == ITEM`.
- `Enemy enemy`, si `type == ENEMY`.
- `int doorTargetId`, si es puerta.
- `boolean doorLocked`.
- `boolean exteriorExit`.
- Estado de puerta abierta/cerrada, si se necesita.

Invariantes:

- El jugador siempre ocupa una celda valida.
- Una celda no debe contener multiples entidades incompatibles.
- La vida nunca es negativa.
- Un objeto concreto no puede estar duplicado, aunque puede haber dos objetos iguales como instancias distintas.

### Items

Jerarquia minima:

- `Item` abstracto.
- `Weapon`.
- `Potion`.
- `Key`.
- Opcional: `Armor`, `Shield`, objetos especiales.

Campos comunes:

- `name`.
- `consumable`.
- `usesLeft`, con `-1` para usos infinitos.

Reglas:

- Una pocion restaura vida y desaparece si es consumible.
- Un arma puede dar bonus de ataque al equiparse.
- Una llave debe estar en inventario y equipada o usable para abrir una puerta cerrada, segun la decision del grupo.
- Algunos objetos aplican efecto por posesion y otros solo al equiparse.

## Reglas de movimiento

El jugador puede hacer como maximo un movimiento por turno.

Movimiento dentro de la habitacion:

- Se calcula desde la posicion actual.
- Usa BFS.
- Maximo `speed` pasos.
- Solo direcciones cardinales: arriba, abajo, izquierda, derecha.
- No hay movimiento diagonal directo.
- Cada paso cuesta 1 punto de movimiento.
- Celdas con muros, enemigos y trampas bloquean el paso segun el documento avanzado.
- Las celdas alcanzables se exponen a la UI con `getReachableCells()`.

Punto a acordar: el documento de A dice que los objetos bloquean el paso en `MatrixBFS`, mientras el enunciado permite recoger/interactuar con objetos adyacentes. Conviene decidir si una celda con objeto es transitable o solo interactuable desde una celda contigua. Para no contradecir la especificacion avanzada, asumir inicialmente que bloquea el paso y se recoge con `pickItem(row, col)` desde una celda adyacente.

## Acciones por turno

En un turno el jugador puede realizar:

- Maximo 1 movimiento.
- Maximo 1 accion.

Ejemplos validos:

- Mover y atacar.
- Mover y usar objeto.
- No moverse y usar objeto.
- Mover y no hacer nada.
- No moverse y no hacer nada.

Siempre va primero el movimiento. La defensa es automatica y no consume accion.

Acciones de jugador:

- Moverse dentro de la habitacion.
- Cambiar de habitacion al abrir/cruzar puerta.
- Recoger objeto.
- Usar o equipar objeto.
- Atacar enemigo.
- Abrir puerta.
- Terminar turno.

## Orden de turno

Orden general:

1. Actua el jugador.
2. Actua enemigo 1.
3. Actua enemigo 2.
4. Actuan el resto de enemigos de la habitacion.
5. Se incrementa el contador de turno.

El documento avanzado propone `TurnManager` basado en `MyCircularList`:

- Ciclo: jugador -> enemigo1 -> enemigo2 -> jugador -> ...
- `turnCount`.
- `maxTurns`.
- `isTimeUp()`.
- `getTurnsLeft()`.

Mientras A no entregue `MyCircularList`, se puede usar un stub temporal. En la integracion final debe usarse la estructura propia de A.

## Combate

Formula obligatoria:

```text
vidaDefensor = vidaDefensor - max(0, ataque * (random * 2) - defensa)
```

Donde:

- `random` es un `double` entre `0.0` y `1.0`, por ejemplo `Math.random()`.
- `ataque` incluye ataque base + modificadores.
- `defensa` incluye defensa base + modificadores.
- El resultado de dano nunca es negativo.
- La vida final nunca baja de 0.

Implementacion sugerida:

```java
public final class CombatSystem {
    public static int calculateDamage(int attack, int defense) {
        double roll = Math.random();
        int raw = (int) (attack * (roll * 2)) - defense;
        return Math.max(0, raw);
    }
}
```

Para tests deterministas puede ser util separar el calculo con un roll inyectado o un metodo auxiliar package-private, manteniendo la API publica pedida.

Validaciones:

- Para atacar, el enemigo debe estar en la celda objetivo.
- Normalmente el enemigo debe estar adyacente, salvo que se implemente rango por arma.
- Atacar una celda vacia debe lanzar `InvalidAttackException`.
- Todo ataque debe registrar un evento en el log.

## Puertas, habitaciones y victoria

Para interactuar con una puerta:

- El jugador debe estar en una celda adyacente a la puerta o ponerse en la celda de puerta, segun la decision final del grupo.
- El enunciado indica que se abre desde la contigua y se atraviesa poniendose en la puerta.
- El documento avanzado simplifica: `openDoor(row, col)` abre y cambia de habitacion si procede.

Reglas:

- Si la puerta no necesita llave, se abre inmediatamente.
- Si necesita llave, el jugador debe tener/equipar/usar la llave adecuada.
- Abrir/cruzar una puerta termina automaticamente el turno porque cambia de habitacion al instante.
- Si la puerta es salida exterior (`exteriorExit == true`), el jugador gana.

Track B debe mantener:

- Habitacion actual.
- Grafo de habitaciones.
- ID de salida o puertas de salida.
- Ruta minima hasta la salida mediante `PathFinder`.
- Distancia a la puerta adecuada de la habitacion actual.

## Derrota

La partida termina en derrota si:

- El jugador llega a 0 puntos de vida.
- Se agotan los turnos globales (`turnCount >= maxTurns`).
- Opcional: se agotan turnos especificos de habitacion, si el grupo decide implementar esa ampliacion.

Si `isGameOver()` es `true`, las acciones que modifiquen estado deberian impedirse y lanzar `GameAlreadyOverException` o devolver `false`, segun el contrato acordado.

## PathFinder y datos para la UI

`PathFinder` actua como puente entre logica y estructuras:

- Usa `IGraph<Integer>` para habitaciones.
- Usa BFS de matriz para celdas alcanzables.
- Calcula:
  - `getMinRoomsToExit()`.
  - `getPathToExit()`.
  - `getDistanceToNearestDoor()`.

La UI debe poder mostrar constantemente:

- Habitaciones restantes hasta la salida.
- Distancia a la puerta mas cercana o puerta adecuada.
- Celdas alcanzables en el turno actual.
- Objetivos atacables.

## Logs

Todas las operaciones relevantes deben registrarse:

- Movimiento.
- Ataque y dano.
- Enemigos atacando.
- Recogida de objetos.
- Uso/equipamiento de objetos.
- Apertura de puertas.
- Cambio de habitacion.
- Victoria.
- Derrota.
- Errores o intentos invalidos si se decide registrarlos.

Al final de la partida debe mostrarse el log completo. Track B debe exponerlo mediante `IGameState.getEventLog()` y `getLastEvent()`.

## Excepciones personalizadas

Excepciones recomendadas:

- `InvalidMoveException`.
- `InvalidAttackException`.
- `DoorLockedException`.
- `InventoryFullException`, si se limita el inventario.
- `GameAlreadyOverException`.
- `InvalidGameStateException`, si se detecta configuracion incoherente.

Tambien deben manejarse excepciones de E/S (`IOException`) en `loadConfig`, `loadGame` y `saveGame`, aunque la implementacion concreta de persistencia pertenece principalmente al Track C.

## Relacion con JSON

Track C se encarga principalmente de persistencia, pero Track B debe poder cargar/aplicar estado y configuracion mediante los metodos del engine.

Configuracion inicial (`levelConfig.json`) contiene:

- Version.
- Titulo.
- Turnos maximos.
- Habitaciones.
- Dimensiones de cada matriz.
- Celdas con tipo, enemigos, objetos y puertas.
- Conexiones del grafo.
- Jugador inicial.

Estado guardado (`gameSave.json`) contiene:

- Version.
- Timestamp.
- Turno actual.
- Vida, habitacion y posicion del jugador.
- Inventario.
- Estado de enemigos.
- Objetos presentes.
- Puertas abiertas.

Punto de integracion: decidir si `GameEngineImpl.loadConfig/loadGame/saveGame` delegan en clases de persistencia de C o si B recibe objetos ya parseados. El contrato actual usa rutas de fichero, por lo que debe coordinarse con C.

Estado real actual: `GameEngineImpl.loadConfig()` reconstruye la configuracion inicial desde JSON y `saveGame()`/`loadGame()` guardan y restauran una partida completa sin usar librerias externas.

Estado real actual de ejemplos JSON: existen `src/main/resources/levelConfig.example.json` y `src/main/resources/gameSave.example.json` coherentes con el escenario base de `newGame()`:

- Habitacion 0 `Entrada`, matriz 6x7, puerta en `(0,3)` hacia sala 1, enemigo basico en `(3,2)` y pocion en `(1,5)`.
- Habitacion 1 `Sala final`, matriz 5x5, salida exterior en `(4,2)`.
- Jugador `Heroe` en habitacion 0, posicion `(5,3)`, vida 100, velocidad 3, ataque base 10 y defensa base 3.
- Grafo con conexion no dirigida entre 0 y 1 (`addUndirectedEdge`).

Estos ficheros son ejemplos de contrato para Track C. Estado actual: `loadConfig()` reconstruye habitaciones, celdas, puertas, enemigos, items, jugador y grafo desde `levelConfig.example.json`. `loadGame()` puede restaurar guardados completos generados por `saveGame()` y mantiene compatibilidad con guardados basicos antiguos.

## UML que afecta especialmente al Track B

El proyecto exige:

- Casos de uso.
- Diagrama de clases.
- Diagrama de secuencia minimo.
- Diagrama de estados minimo.
- Diagrama de actividad minimo.

Para Track B conviene preparar:

- Casos de uso: mover dentro de habitacion, cambiar habitacion, atacar, usar objeto, recoger objeto, abrir puerta.
- Secuencia: turno completo con movimiento del jugador, ataque del jugador, movimiento de enemigo y ataque enemigo.
- Estados: inicio, turno jugador, turno enemigos, victoria, derrota.
- Actividad: flujo de decision de una accion de jugador en un turno.

## Tests JUnit obligatorios para Track B

Nota de planificacion: no fue necesario crear toda la bateria de JUnit al inicio de Fase 0. Una vez estabilizados dominio y `GameEngineImpl`, se creo una bateria JUnit inicial para las clases no visuales principales. Las pruebas manuales quedan solo como apoyo temporal durante desarrollo.

Estado real actual: los tests JUnit estan en `test/es/proyecto/juego/tests`, con `test` como test source root. JUnit 5 esta disponible en `lib`. La bateria actual ejecuta 81 tests correctos por consola. `TestRunner` se conserva como apoyo, pero no sustituye la validacion final.

### `CombatSystem`

- 1000 iteraciones de `calculateDamage`: resultado siempre `>= 0`.
- Con ataque alto y defensa 0, el dano es positivo en la mayoria de casos.
- La vida del defensor nunca baja de 0.

### `Player`

- `takeDamage(999)` deja vida en 0, no negativa.
- `heal()` no supera `maxHp`.
- Ataque efectivo suma bonus de arma.
- Defensa efectiva suma bonus de equipo defensivo.
- Inventario agrega, usa y elimina consumibles correctamente.

### `Room`

- `getCell/setCell` funcionan.
- Coordenadas fuera de rango lanzan excepcion.
- `getDoors()` devuelve puertas existentes.
- Una celda no queda con dos entidades incompatibles.

### `TurnManager`

- Con 3 actores, el ciclo es correcto en 6 llamadas a `next()`.
- `isPlayerTurn()` solo es true cuando toca el jugador.
- `isTimeUp()` es true exactamente cuando `turnCount == maxTurns`.

### `GameEngineImpl`

- `movePlayer` a celda alcanzable actualiza posicion.
- `movePlayer` fuera de rango lanza `InvalidMoveException`.
- `attack` a celda sin enemigo lanza `InvalidAttackException`.
- `useItem` con indice invalido lanza `IndexOutOfBoundsException`.
- Usar pocion aumenta vida y elimina la pocion del inventario.
- `openDoor` con salida exterior activa victoria.
- Al terminar el ultimo turno, `isGameOver()` es true.
- Si la vida del jugador llega a 0, `isGameOver()` es true.
- `IGameState` expone datos necesarios para UI: posicion, vida, atributos, equipo, sala actual, dimensiones, turnos, acciones disponibles, ruta/logs y estado final.

## Hoja de ruta recomendada para Track B

### Semana 1: dominio

- Crear interfaces necesarias si aun no existen.
- Crear stubs de A para desarrollo.
- Implementar `Player`. Base inicial implementada: vida, curacion, dano, inventario, uso de items, equipamiento y llaves.
- Implementar `Enemy`. Base inicial implementada: vida, dano, ataque y movimiento cardinal simple hacia el jugador.
- Implementar `Room`. Base inicial implementada: matriz, colocacion/retirada de enemigos e items, puertas y validaciones.
- Implementar `Cell` y `CellType`. Base inicial implementada con control de incompatibilidades entre item, enemigo, puerta y trampa.
- Implementar jerarquia de items. Base inicial implementada con `Item`, `Weapon`, `Potion`, `Key` y `Armor`.
- Tests de `Player`, `Room`, `Cell` e items.

### Semana 2: mecanicas

- Implementar `CombatSystem`. Base inicial implementada con formula del enunciado, validaciones y variante con roll inyectado para tests.
- Implementar `TurnManager`. Base inicial implementada con orden circular de actores, turno de jugador, movimiento usado, accion usada y contador global.
- Implementar movimiento del jugador. Integrado en `GameEngineImpl`; `MatrixBFS` y `PathFinder.getReachableCells` calculan celdas alcanzables.
- Implementar objetivos atacables.
- Implementar IA basica de enemigos.
- Implementar `GameEngineImpl.movePlayer()` y `attack()`.
- Tests de combate, movimiento y turnos.

### Semana 3: engine completo e integracion

- Completar `useItem`, `pickItem`, `openDoor`, `endTurn`.
- Implementar victoria y derrota.
- Implementar `PathFinder`.
- Sustituir stubs por estructuras reales de A.
- Ejecutar todos los tests de B.
- Entregar `IGameEngine`, `IGameState` y `GameEngineImpl` a C.

## Decisiones pendientes que conviene cerrar pronto

- Objetos y movimiento: decision cerrada para la base actual. Una celda con objeto bloquea el movimiento y el item se recoge desde una celda adyacente con `pickItem(row, col)`.
- Apertura de puertas: decision cerrada para la base actual. `openDoor(row, col)` exige que el jugador este adyacente a la puerta; al abrir puerta normal cambia de habitacion inmediatamente y termina el turno. Pendiente resolver puertas reciprocas/coordenadas de entrada con el JSON final.
- Llaves: decision cerrada para la base actual. Basta con tener la llave adecuada en inventario para abrir una puerta bloqueada; no hace falta equiparla.
- Inventario: decision cerrada para la base actual. Hay limite de 10 items (`Player.MAX_INVENTORY_SIZE`). Al intentar superar el limite se lanza `InventoryFullException`.
- Equipo: se implementan `Weapon`, `Potion`, `Key` y `Armor`. Pendiente confirmar si hara falta `Shield` u otros objetos especiales.
- Rango de ataque: decision cerrada para la base actual. El ataque solo permite enemigos adyacentes. Pendiente extender solo si se anaden armas a distancia o rangos variables.
- Movimiento enemigo: decision cerrada para la base actual. El enemigo usa BFS para elegir el primer paso de la ruta mas corta hacia una celda adyacente al jugador.
- Enemigos sobre objetos/puertas: decision cerrada para la base actual. `Room.placeEnemy` solo permite celda vacia y el movimiento enemigo no pisa objetos ni puertas.
- Grafo de habitaciones: decision cerrada para la base actual. `addEdge` se mantiene como arista dirigida, pero `IGraph` y `MyGraph` incluyen `addUndirectedEdge`. El JSON de habitaciones puede declarar una conexion no dirigida una sola vez con `dirigida: false`.
- Persistencia: pendiente resolver con Track C si `GameEngineImpl` parsea directamente ficheros, delega en clases de C o recibe DTOs/objetos ya parseados.
- Formato final de paquetes y nombres: pendiente confirmar con A, B y C para que todo compile junto.
- Mutabilidad de estado para UI: resuelto como base inicial. `IGameState` devuelve copias defensivas de inventario, sala actual, ruta y log. Pendiente revisar con C si necesita DTOs mas especificos para evitar exponer clases de dominio.
- `IList` e `Iterable`: actualmente `IList` no extiende `Iterable` para evitar dependencia de `Iterator`. Pendiente confirmar si el enunciado/profesor exige esa firma exacta.
- Coverage: pendiente ejecutar Run with Coverage en IntelliJ con `test` marcado como test source y registrar el porcentaje final en la memoria.

## Criterios de calidad para no perder puntos

- Separar logica de UI: nada de `javafx.*` en B.
- Mantener encapsulamiento: atributos privados y metodos claros.
- Usar herencia y polimorfismo en items y, si procede, acciones/personajes.
- Bajo acoplamiento: depender de interfaces, no implementaciones concretas.
- Gestionar errores con excepciones claras.
- Registrar operaciones en log.
- Tests JUnit para clases no visuales.
- Justificar decisiones abiertas en la memoria.
- Documentar uso de IA: prompts, resultados, cambios, critica y metodologia final.
- Evitar estructuras estandar en la entrega final donde sustituyan estructuras evaluadas.

## Estado actual tras integracion UI/persistencia minima

Estado validado:

- JavaFX instalado localmente en `lib/javafx-sdk-25.0.3`.
- Ejecucion local mediante `run-game.ps1`.
- Compilacion completa con JavaFX correcta.
- JUnit: 81 tests encontrados, 81 correctos y 0 fallos.
- `loadConfig()` ya construye mundo desde `levelConfig.example.json`: habitaciones, puertas, enemigos, items, jugador y grafo.
- `saveGame()`/`loadGame()` restauran una partida completa: turno, flags de final, habitaciones, puertas, enemigos, objetos presentes, jugador, inventario, equipo, conexiones y log.
- UI integrada con `GameEngineImpl`: `RoomView` pinta sala real, `InventoryPanel` usa inventario real, `LogPanel` muestra eventos reales y `ActionPanel` conecta guardar/cargar/nueva partida/fin de turno.
- Click en celda decide accion: atacar enemigo, abrir puerta, recoger item o mover.

Limitaciones pendientes:

- Las puertas reciprocas y coordenadas exactas de entrada entre habitaciones siguen pendientes si el JSON final las exige.
- Queda pendiente decidir si `IList` debe extender `Iterable`.
- Queda pendiente registrar coverage final de IntelliJ.
- El codigo antiguo de `src/es/uah/eedd/listas/prueba/...` se aislo en `legacy/es_uah_eedd_listas_prueba` para que no se mezcle con el proyecto principal.
