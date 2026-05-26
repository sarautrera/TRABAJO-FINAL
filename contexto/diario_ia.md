# Diario de uso de IA

Proyecto: Practica Final 2026 MP + EEDD  
Parte principal documentada aqui: Track B - Logica del juego  
Entrega: 28/05/2026 a las 10:00 h

## Objetivo del diario

Registrar de forma trazable como se ha usado la IA durante el desarrollo del proyecto. Este documento debe permitir explicar:

- Que se pidio a la IA.
- Que contexto se le proporciono.
- Que resultado produjo.
- Que se acepto, modifico o rechazo.
- Que riesgos se detectaron.
- Que metodologia final se extrajo del uso de IA.

## Criterios de uso responsable

- La IA se usa despues de leer y entender la especificacion.
- Las respuestas de la IA no se aceptan automaticamente: se revisan contra los documentos del proyecto.
- El codigo generado debe ser probado con JUnit cuando afecte a clases no visuales.
- Las decisiones abiertas del enunciado deben justificarse por el grupo, no delegarse sin revision.
- La IA no sustituye el conocimiento del equipo: todos los miembros deben poder explicar el diseno y el codigo entregado.

## Formato de registro

Para cada uso relevante de IA, anadir una entrada con este formato:

```md
### Entrada N - Titulo breve

- Fecha:
- Persona responsable:
- Herramienta/agente:
- Objetivo:
- Prompt o peticion:
- Contexto proporcionado:
- Resultado obtenido:
- Cambios realizados por el equipo:
- Validacion:
- Critica y riesgos:
- Decision final:
```

## Entradas

### Entrada 1 - Lectura de documentacion y contexto del Track B

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: comprender el enunciado principal y las especificaciones de la carpeta `docs`, centrando el trabajo en la parte B del proyecto.
- Prompt o peticion: "tengo que hacer un proyecto basandonos en la documentacion que aparece en la carpeta docs, mi parte del proyecto es la B, la primera tarea es que leas las instrucciones en Practica Final 2026 MP EEDD, las especificaciones en los otros documentos y una vez las hayas comprendido escribas un fichero md con todo el contexto necesario para realizar estas tareas".
- Contexto proporcionado: carpeta `docs` con PDF principal y documentos DOCX de resumen, arquitectura avanzada y tracks A/B/C.
- Resultado obtenido: se leyeron los documentos, se extrajo el contenido relevante del PDF y de los DOCX, y se creo `contexto/contexto_track_b_logica.md`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: el documento generado resume responsabilidades, contratos, clases, reglas de movimiento, combate, turnos, puertas, victoria/derrota, tests JUnit, UML e integracion con A y C.
- Critica y riesgos: el documento es una sintesis, no sustituye la lectura del enunciado original. Hay decisiones abiertas que deben cerrarse con el grupo, como si las celdas con objetos bloquean movimiento o como se equipa una llave.
- Decision final: usar `contexto/contexto_track_b_logica.md` como base de trabajo para implementar Track B, revisandolo con el equipo antes de programar.

### Entrada 2 - Creacion del diario de IA

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: crear un fichero Markdown para registrar el uso de IA exigido por la practica.
- Prompt o peticion: "lo de la fecha es una errata, es para 2026 y tienes que hacer en paralelo al cowork el fichero del diario de IA en formato md".
- Contexto proporcionado: confirmacion de que la fecha correcta de entrega es 2026 y no 2025.
- Resultado obtenido: se creo `contexto/diario_ia.md` con estructura de registro, criterios de uso responsable y primeras entradas.
- Cambios realizados por el equipo: pendiente de completar nombres, responsables y revisiones.
- Validacion: el formato cubre agentes, prompts, resultados, modificaciones, critica y metodologia, que son los puntos pedidos por el enunciado.
- Critica y riesgos: si no se actualiza durante el desarrollo, el diario quedara incompleto. Conviene registrar cada intervencion importante justo despues de usar IA.
- Decision final: mantener este fichero vivo durante todo el desarrollo del proyecto.

### Entrada 3 - Creacion de estructura Java de Fase 0

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: crear la estructura inicial de carpetas y ficheros minimos del proyecto Java para poder empezar a trabajar por tracks.
- Prompt o peticion: "el proyecto es en java como has visto en la documentacion, como fase 0 vamos a crear la estructura de carpetas del proyecto y los ficheros minimos".
- Contexto proporcionado: documentacion ya resumida en `contexto/contexto_track_b_logica.md` y estructura recomendada en los documentos de `docs`.
- Resultado obtenido: se creo una base Java plana con paquetes `estructuras`, `logica`, `ui` y `persistencia`; interfaces de contrato de Fase 0; esqueletos de dominio del Track B; stubs temporales; JSON de ejemplo; y `.gitignore`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion local correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`. No se usara Maven.
- Critica y riesgos: `GameEngineImpl` es todavia un esqueleto con metodos pendientes. `StubList` es una lista enlazada minima propia para apoyo temporal y debe sustituirse por la implementacion final de lista de Track A antes de la entrega integrada.
- Decision final: usar esta estructura como base comun de Fase 0 y no cambiar los contratos sin consenso del grupo.

### Entrada 4 - Ajuste a Java plano sin colecciones estandar

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: retirar Maven y cualquier uso de estructuras estandar de Java en la base inicial.
- Prompt o peticion: "no vamos a usar maven ni librerias y estructuras de java".
- Contexto proporcionado: estructura inicial de Fase 0 ya creada.
- Resultado obtenido: se elimino `pom.xml`, se sustituyo `StubList` por una lista enlazada minima con nodos propios y se quito la dependencia de `Iterable`/`Iterator` en `IList`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`. Busqueda en `src` sin apariciones de `ArrayList`, `HashMap`, `LinkedList`, `import java.util`, Maven, `Iterable` ni `Iterator`.
- Critica y riesgos: la especificacion original proponia `IList<T> extends Iterable<T>`, pero se ha retirado para evitar dependencias de iteradores de Java. Si el profesor exige exactamente esa firma, habra que consensuarlo.
- Decision final: trabajar con Java plano y estructuras propias desde el inicio.

### Entrada 5 - Resumen de siguientes pasos

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: documentar los siguientes pasos de implementacion tras crear el esqueleto inicial.
- Prompt o peticion: "escribemelo en bulletpoints.md dentro de docs".
- Contexto proporcionado: estructura Java de Fase 0 y resumen previo de tareas pendientes.
- Resultado obtenido: se creo `docs/bulletpoints.md` con una lista de pasos para contratos, estructuras, dominio, sistemas, `GameEngineImpl`, reglas de fin, tests, integracion y documentacion.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: revision manual del contenido generado.
- Critica y riesgos: el documento es una guia de trabajo, no una planificacion cerrada. Debe actualizarse si cambian los contratos con A o C.
- Decision final: usar `docs/bulletpoints.md` como checklist inicial de implementacion.

### Entrada 6 - Definicion de contratos de Fase 0

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: cerrar las firmas iniciales de `IList`, `IStack`, `IQueue`, `IGraph`, `ITree`, `IGameEngine` e `IGameState` para que A, B y C trabajen sobre una base comun.
- Prompt o peticion: "- Definir definitivamente los contratos de Fase 0: Confirmar firmas de `IList`, `IStack`, `IQueue`, `IGraph`, `ITree`. Confirmar firmas de `IGameEngine` e `IGameState`. Acordar con A y C que no se cambian sin consenso."
- Contexto proporcionado: interfaces ya creadas en `src/main/java/es/proyecto/juego/estructuras` y `src/main/java/es/proyecto/juego/logica`, mas el checklist de `docs/bulletpoints.md`.
- Resultado obtenido: se creo `docs/contratos_fase_0.md` con las firmas acordadas, usos previstos y reglas para cambios de contrato; tambien se marco el punto como hecho en `docs/bulletpoints.md`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`.
- Critica y riesgos: `docs/` esta ignorado por `.gitignore`, asi que este contrato no aparecera en `git status` mientras no se cambie la politica de versionado o se fuerce su inclusion. Si A o C necesitan modificar firmas, debe actualizarse el documento y revisar impacto.
- Decision final: mantener las firmas actuales como contrato de Fase 0 y no cambiarlas sin consenso.

### Entrada 7 - Implementacion de estructuras minimas propias

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: implementar las estructuras propias minimas necesarias para avanzar con movimiento, turnos y rutas.
- Prompt o peticion: "- Implementar estructuras minimas propias necesarias para avanzar: Lista enlazada definitiva o temporal robusta. Cola para BFS y turnos. Lista circular para `TurnManager`. Grafo de habitaciones. BFS de matriz."
- Contexto proporcionado: interfaces de Fase 0 ya acordadas, clases de dominio `Room` y `Cell`, y necesidad de evitar colecciones estandar de Java.
- Resultado obtenido: se crearon `MyLinkedList`, `MyLinkedQueue`, `MyLinkedStack`, `MyCircularList` y `MyGraph` en `es.proyecto.juego.estructuras`; se creo `MatrixBFS` en `es.proyecto.juego.logica.sistemas`; y `StubList` paso a reutilizar `MyLinkedList`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`; busqueda sin imports de `java.util` ni estructuras estandar reales. `jshell` no pudo usarse para prueba manual por un problema de permisos de preferencias de Java en Windows.
- Critica y riesgos: `MyGraph.shortestPath` y `shortestDistance` ya tienen en cuenta pesos, pero `addEdge` crea aristas dirigidas; si el mapa de habitaciones debe ser no dirigido, el codigo que cargue conexiones debera insertar ambas direcciones o se debera acordar otro contrato.
- Decision final: usar estas estructuras como base propia para avanzar con Track B hasta la integracion final con Track A.

### Entrada 8 - Aclaracion sobre tests JUnit

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: aclarar que los tests JUnit se crearan cuando el proyecto este mas avanzado y las firmas principales esten estables.
- Prompt o peticion: "apunta en el contexto y en los bullet que hay que crear test de junit cuando ya este avanzado el proyecto"
- Contexto proporcionado: `contexto/contexto_track_b_logica.md` ya indicaba JUnit obligatorio y `docs/bulletpoints.md` hablaba de tests manuales.
- Resultado obtenido: se actualizo el contexto para indicar que las pruebas manuales son solo apoyo temporal y que la validacion final de clases no visuales debe hacerse con JUnit; tambien se actualizo el checklist de `docs/bulletpoints.md`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: revision manual del texto actualizado.
- Critica y riesgos: si no se prepara JUnit antes de la entrega, quedara un hueco de validacion importante. Conviene crear la bateria cuando `GameEngineImpl`, dominio y sistemas esten implementados de forma estable.
- Decision final: posponer JUnit hasta una fase mas avanzada, pero mantenerlo como validacion final esperada.

### Entrada 9 - Completar dominio inicial del Track B

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: completar la base de dominio del Track B antes de empezar sistemas y `GameEngineImpl`.
- Prompt o peticion: "- Completar dominio del Track B: `Player`: inventario, equipamiento, vida, dano, curacion. `Enemy`: vida, movimiento basico, ataque. `Room`: matriz, enemigos, puertas, validaciones. `Cell`: control correcto de tipo, objeto, enemigo, puerta y trampa. `Item`, `Weapon`, `Potion`, `Key`, opcionalmente `Armor`."
- Contexto proporcionado: clases de dominio ya existentes, estructuras propias implementadas y reglas resumidas en `contexto/contexto_track_b_logica.md`.
- Resultado obtenido: se reforzo `Player` con validaciones, inventario, uso de items, equipamiento y comprobacion de llaves; `Enemy` con ataque y movimiento cardinal simple hacia el jugador; `Room` con operaciones de colocacion/retirada de enemigos e items, puertas y busqueda de puertas; `Cell` con limpieza de estados incompatibles y soporte de trampas; e items con validaciones y control de usos.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`; busqueda sin `TODO`, `return null`, colecciones estandar ni imports de `java.util` en dominio.
- Critica y riesgos: el movimiento de enemigos es intencionadamente basico y codicioso, no usa todavia BFS. `Room.getGrid()` sigue exponiendo la matriz mutable para facilitar integracion inicial con UI, pero habra que vigilar que la UI no modifique estado directamente.
- Decision final: considerar cerrado el dominio inicial y avanzar hacia sistemas de juego y `GameEngineImpl`.

### Entrada 10 - Implementacion de sistemas de juego iniciales

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: implementar sistemas de juego reutilizables antes de completar `GameEngineImpl`.
- Prompt o peticion: "- Implementar sistemas de juego: `CombatSystem` con formula exacta del enunciado. `TurnManager` con control de movimiento y accion por turno. `PathFinder` para celdas alcanzables, puertas y ruta a salida. Log de eventos de partida."
- Contexto proporcionado: dominio inicial de Track B, estructuras propias, `MatrixBFS`, `MyCircularList` y contrato de `IGameState`.
- Resultado obtenido: `CombatSystem` valida ataque/defensa y permite roll inyectado para tests; `TurnManager` controla actor actual, turno de jugador, movimiento usado, accion usada, orden circular y turnos restantes; `PathFinder` calcula celdas alcanzables, objetivos atacables, distancia a puerta cercana y ruta/distancia de habitaciones; se creo `EventLog` para registrar eventos.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`.
- Critica y riesgos: `TurnManager` usa identificadores `String` para actores como solucion simple de Fase 0; si se necesita enlazar actores con objetos reales, se puede crear una clase/DTO de actor mas adelante. `PathFinder.getDistanceToNearestDoor` calcula distancia hasta la puerta o hasta una celda adyacente si la puerta esta bloqueada.
- Decision final: considerar cerrada la base inicial de sistemas y avanzar a `GameEngineImpl`.

### Entrada 11 - Implementacion inicial de `newGame()`

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: empezar `GameEngineImpl` implementando `newGame()` y el estado interno minimo.
- Prompt o peticion: "implementa el 1"
- Contexto proporcionado: orden de trabajo propuesto para `GameEngineImpl`, donde el punto 1 era `newGame()` mas estado interno minimo.
- Resultado obtenido: `GameEngineImpl` ahora inicializa habitaciones, grafo bidireccional, jugador, turno, `PathFinder`, `EventLog`, flags de victoria/derrota y un escenario hardcodeado de Fase 0 con dos habitaciones conectadas.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`.
- Critica y riesgos: el escenario inicial es temporal y hardcodeado porque `loadConfig()` y persistencia JSON siguen pendientes. Todavia no se puede consultar el estado por `getState()` porque ese punto se implementara despues.
- Decision final: usar este `newGame()` como base para conectar `getState()`, celdas alcanzables y acciones del jugador.

### Entrada 12 - Consulta de estado inicial en `GameEngineImpl`

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: implementar el segundo paso de `GameEngineImpl`: `getState()`, `getReachableCells()` y `getAttackTargets()`.
- Prompt o peticion: "implementa el 2"
- Contexto proporcionado: `newGame()` ya inicializaba jugador, habitacion, turnos, grafo, `PathFinder` y `EventLog`.
- Resultado obtenido: se creo un `GameStateSnapshot` interno que implementa `IGameState`; `getState()` expone datos del jugador, habitacion, turnos, ruta, distancia a puerta, logs y flags de fin; `getReachableCells()` y `getAttackTargets()` delegan en `PathFinder`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`.
- Critica y riesgos: el snapshot devuelve referencias a inventario, habitacion y log, por lo que la UI debe tratarlas como solo lectura segun el contrato. Si hace falta inmutabilidad estricta, habra que crear copias defensivas con estructuras propias.
- Decision final: dejar listo el estado consultable y avanzar al movimiento del jugador.

### Entrada 13 - Implementacion de `movePlayer()`

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: implementar el tercer paso de `GameEngineImpl`: movimiento del jugador dentro de la habitacion actual.
- Prompt o peticion: "implementa el 3"
- Contexto proporcionado: `newGame()`, `getState()`, `PathFinder`, `MatrixBFS`, `TurnManager` y excepciones de dominio ya disponibles.
- Resultado obtenido: `movePlayer(row, col)` valida partida iniciada y activa, turno/movimiento disponible, coordenadas dentro de la habitacion, celda distinta a la actual y celda alcanzable; actualiza posicion, marca movimiento usado y registra el evento.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`.
- Critica y riesgos: el movimiento solo cambia posicion dentro de la habitacion actual. Cruzar puertas, trampas y cambio de habitacion quedan para `openDoor()`/reglas posteriores.
- Decision final: considerar `movePlayer()` listo como base para integrar combate, objetos y turnos.

### Entrada 14 - Implementacion de `attack()`

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: implementar el cuarto paso de `GameEngineImpl`: ataque del jugador a enemigos adyacentes.
- Prompt o peticion: "implementa el 4"
- Contexto proporcionado: `CombatSystem`, `PathFinder.getAttackTargets()`, `Room.getEnemyAt()`, `Room.removeEnemy()`, `TurnManager` y excepciones de dominio.
- Resultado obtenido: `attack(row, col)` valida partida iniciada y activa, accion disponible, coordenadas validas y objetivo atacable; calcula dano con `CombatSystem`, aplica dano, consume la accion, registra log y retira al enemigo si muere.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`.
- Critica y riesgos: el ataque solo contempla rango adyacente, coherente con la base inicial. Si se implementan armas a distancia, habra que extender `PathFinder.getAttackTargets()` y la validacion de ataque.
- Decision final: considerar `attack()` listo como base y avanzar a recogida/uso de objetos.

### Entrada 15 - Implementacion de `pickItem()` y `useItem()`

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: implementar el quinto paso de `GameEngineImpl`: recogida y uso de objetos.
- Prompt o peticion: "implementa el 5"
- Contexto proporcionado: `Player` ya tenia inventario y uso de items; `Room` tenia `takeItem`; `Cell` distinguia items; `TurnManager` controlaba accion usada.
- Resultado obtenido: `pickItem(row, col)` valida partida activa, accion disponible, item adyacente y celda con item; mueve el item al inventario, consume accion y registra log. `useItem(index)` valida indice y accion, aplica el efecto del item, elimina consumibles agotados desde `Player`, consume accion y registra log.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`.
- Critica y riesgos: se mantiene la decision provisional de recoger objetos desde una celda adyacente. Usar una llave desde inventario no abre puertas; la apertura real queda para `openDoor()`.
- Decision final: considerar recogida y uso de objetos listos como base y avanzar a turnos/enemigos o puertas.

### Entrada 16 - Implementacion de `endTurn()`

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: implementar el sexto paso de `GameEngineImpl`: cierre de turno, actuacion de enemigos y derrota.
- Prompt o peticion: "implementa el 6"
- Contexto proporcionado: `TurnManager`, `Enemy.attack()`, `Enemy.moveOneStepToward()`, `EventLog` y estado interno de `GameEngineImpl`.
- Resultado obtenido: `endTurn()` valida partida activa, registra fin de turno, ejecuta enemigos vivos de la habitacion actual, incrementa el contador de turno y comprueba derrota por muerte del jugador o por turnos agotados.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`.
- Critica y riesgos: la IA enemiga es basica: ataca si esta adyacente al inicio de su turno y, si no, se mueve un paso hacia el jugador. No ataca despues de moverse en el mismo turno.
- Decision final: considerar `endTurn()` listo como base y avanzar a `openDoor()`.

### Entrada 17 - Implementacion de `openDoor()`

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: implementar el septimo paso de `GameEngineImpl`: apertura de puertas, cambio de habitacion y victoria.
- Prompt o peticion: "implementa el 7"
- Contexto proporcionado: `Cell` con datos de puerta, `Room`, `Player.hasKeyForDoor()`, excepcion `DoorLockedException`, `TurnManager` y estado interno de habitaciones.
- Resultado obtenido: `openDoor(row, col)` valida partida activa, accion disponible, puerta adyacente, celda de puerta y llave si esta bloqueada; abre la puerta, consume accion, activa victoria si es salida exterior o cambia a la habitacion destino si es una puerta normal.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`.
- Critica y riesgos: al cambiar de habitacion, el jugador se coloca en una celda libre de entrada calculada de forma simple porque todavia no hay JSON ni puertas reciprocas completas. Si el formato final define coordenadas de entrada, habra que sustituir esta regla.
- Decision final: considerar `openDoor()` listo como base y pasar a reglas finales de victoria/derrota y pruebas cuando corresponda.

### Entrada 18 - Persistencia temporal en Track B

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: retirar los `UnsupportedOperationException` restantes de `loadConfig`, `loadGame` y `saveGame` con una solucion minima temporal dentro de Track B.
- Prompt o peticion: "haz una solucion minima temporal dentro de trak B y apunta al final de los bullet points que esto queda pendiente para la integracion con C. de todas formas no esta esto definido en los docs?"
- Contexto proporcionado: el contexto del proyecto indica que Track C se encarga principalmente de persistencia JSON, pero `IGameEngine` mantiene metodos con rutas de fichero y la coordinacion B/C esta pendiente.
- Resultado obtenido: `loadConfig` y `loadGame` validan que el fichero parezca JSON y arrancan una partida base temporal; `saveGame` escribe un snapshot JSON minimo con turno, jugador, habitacion, flags de fin y ultimo evento.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`; no quedan `UnsupportedOperationException` en `GameEngineImpl`.
- Critica y riesgos: esta persistencia no reconstruye ni guarda el estado completo real. Es solo un puente para que el contrato no falle hasta la integracion con Track C.
- Decision final: mantener esta solucion temporal y sustituirla en la integracion con C por parseo/serializacion JSON completos.

### Entrada 19 - Verificacion de reglas de fin de partida

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: confirmar y documentar las reglas de fin de partida.
- Prompt o peticion: "- Implementar reglas de fin de partida: Victoria al abrir puerta de salida exterior. Derrota por vida del jugador a 0. Derrota por agotar turnos."
- Contexto proporcionado: `GameEngineImpl` ya tenia `openDoor()`, `endTurn()` y `updateDefeatState()`.
- Resultado obtenido: se verifico que la victoria se activa al abrir una puerta con `exteriorExit`, que la derrota por vida se activa cuando el jugador deja de estar vivo y que la derrota por turnos se activa con `turnManager.isTimeUp()`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta con `javac -encoding UTF-8 -d out` sobre todos los ficheros de `src/main/java`.
- Critica y riesgos: las derrotas se comprueban actualmente al cerrar turno o tras cambio de habitacion. Si mas adelante se anaden trampas u otros efectos que puedan matar durante una accion, deberan llamar tambien a `updateDefeatState()`.
- Decision final: marcar las reglas de fin de partida como implementadas en la base inicial.

### Entrada 20 - Tests manuales temporales

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: crear una bateria de pruebas para validar la base actual del proyecto mientras no se incorpore JUnit.
- Prompt o peticion: "- Crear tests cuando el proyecto este mas avanzado: Usar JUnit para las clases no visuales si se mantiene como requisito del enunciado. Probar `Player`, `CombatSystem`, `Room`, `TurnManager`, estructuras propias, `MatrixBFS` y `GameEngineImpl`. Dejar los tests manuales solo como apoyo temporal durante desarrollo, no como validacion final. Registrar resultados para la memoria."
- Contexto proporcionado: el proyecto no usa Maven ni librerias externas, y no hay JUnit disponible en el repositorio.
- Resultado obtenido: se creo `src/test/java/es/proyecto/juego/tests/TestRunner.java` con pruebas manuales de `Player`, `CombatSystem`, `Room`, `TurnManager`, estructuras propias, `MatrixBFS` y `GameEngineImpl`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta de `src/main/java` y `src/test/java`; ejecucion correcta con `java -cp "out;out\\test" es.proyecto.juego.tests.TestRunner`, resultado 7 tests pasados y 0 fallidos.
- Critica y riesgos: estos tests no son JUnit y no deben presentarse como validacion final si el enunciado exige JUnit. Son una red temporal mientras se decide como incorporar JUnit sin Maven o con el entorno del profesor.
- Decision final: mantener `TestRunner` como apoyo temporal y planificar JUnit real para la fase de entrega.

### Entrada 21 - Primer test JUnit para `Player`

- Fecha: 20/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: empezar la bateria JUnit real por el primer bloque de pruebas, centrado en `Player`.
- Prompt o peticion: "haz test de Junit con el 1"
- Contexto proporcionado: no hay Maven ni jars de JUnit en el repositorio; existe un `TestRunner` manual temporal.
- Resultado obtenido: se creo `src/test/java/es/proyecto/juego/tests/PlayerJUnitTest.java` con tests JUnit 5 para vida, curacion, equipamiento, pociones, llaves y validaciones del constructor.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: no se pudo compilar ni ejecutar JUnit porque no hay `junit-platform-console-standalone` ni otra dependencia JUnit local disponible. La compilacion de `src/main/java` sigue correcta.
- Critica y riesgos: para ejecutar estos tests hay que anadir un jar local de JUnit 5 o confirmar que el entorno del profesor lo proporciona. Hasta entonces, los tests JUnit quedan preparados pero no verificados automaticamente.
- Decision final: usar este fichero como inicio de la bateria JUnit real y mantener `TestRunner` como apoyo temporal.

### Entrada 22 - Bateria JUnit completa inicial

- Fecha: 21/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: crear el resto de tests JUnit para las clases no visuales principales.
- Prompt o peticion: "haz el resto de test Junit"
- Contexto proporcionado: estructura actual con tests en `test/java`, JUnit 5 en `lib` y `PlayerJUnitTest` ya funcionando.
- Resultado obtenido: se crearon tests JUnit para `CombatSystem`, `Room`/`Cell`, `TurnManager`, estructuras propias, `MatrixBFS` y `GameEngineImpl`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta de `src/main/java` y `test/java`; ejecucion correcta con `java -jar lib\junit-platform-console-standalone-1.14.0.jar --class-path "out;out\test" --scan-class-path`, resultado 36 tests encontrados, 36 ejecutados, 36 correctos y 0 fallidos.
- Critica y riesgos: los tests cubren la base actual y la persistencia temporal, pero cuando Track C sustituya la persistencia JSON habra que actualizar los tests de carga/guardado.
- Decision final: usar esta bateria JUnit como validacion principal de Track B y mantener `TestRunner` solo como apoyo temporal.

### Entrada 23 - Cobertura JUnit y tests de excepciones/stubs

- Fecha: 21/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: reforzar la bateria JUnit para mejorar cobertura, especialmente excepciones y clases puente.
- Prompt o peticion: "los test de Junit tienen que tener 100% de cobertura", "me falta un 60% en excepciones", "haz el test de stub", "stublist sigue a 0"
- Contexto proporcionado: IntelliJ se estaba usando con Run with Coverage y habia clases con cobertura baja aunque los tests funcionales pasaban.
- Resultado obtenido: se anadieron tests JUnit para excepciones propias y `StubList`; tambien se anadio constructor explicito en `StubList` para que IntelliJ pueda atribuir cobertura a la clase puente.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: ejecucion JUnit correcta con `java -jar lib\junit-platform-console-standalone-1.14.0.jar --class-path "out;out\test" --scan-class-path`, resultado final 47 tests encontrados, 47 correctos y 0 fallidos.
- Critica y riesgos: la cobertura de IntelliJ depende de que el directorio `test` este marcado correctamente como test source. Los tests de clases triviales pueden mejorar cobertura numerica, pero la validacion importante sigue siendo comportamiento y reglas de juego.
- Decision final: mantener estos tests como parte de la bateria JUnit y registrar los resultados en la memoria final.

### Entrada 24 - Reorganizacion de estructura de tests

- Fecha: 21/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: normalizar la estructura de tests para que IntelliJ y la consola usen la misma raiz.
- Prompt o peticion: "quiero que el source de test sea test y que no exista la carpeta java por debajo"
- Contexto proporcionado: habia tests repartidos entre `test`, `test/java` y `test/es`, y el fichero `.iml` tenia raices solapadas.
- Resultado obtenido: todos los tests quedaron bajo `test/es/proyecto/juego/tests`; el source root de test en el `.iml` local quedo apuntando a `test`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion de produccion y tests correcta; ejecucion JUnit con 47 tests encontrados, 47 correctos y 0 fallidos.
- Critica y riesgos: `.iml` esta ignorado por `.gitignore`, asi que cada integrante puede tener que marcar `test` como test source en su IntelliJ si no se versiona la configuracion del IDE.
- Decision final: usar `test` como raiz de tests del proyecto y evitar la carpeta intermedia `test/java`.

### Entrada 25 - Preparacion de integracion con Track C

- Fecha: 21/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: preparar el contrato de estado y los ejemplos JSON para que Track C pueda empezar UI/persistencia.
- Prompt o peticion: "- Preparar integracion con Track C: Asegurar que `IGameState` devuelve todo lo que necesita la UI. Mantener `GameEngineImpl` sin imports de JavaFX. Preparar JSON de ejemplo coherente con el estado real del juego."
- Contexto proporcionado: `IGameState` ya exponia jugador, habitacion, turnos, rutas y logs, pero faltaban datos directos para UI como nombre/dimensiones de sala, equipo y acciones disponibles.
- Resultado obtenido: `IGameState` se amplio con getters de arma equipada, armadura equipada, id/nombre/dimensiones de habitacion y flags `canPlayerMove()`/`canPlayerAct()`; `GameEngineImpl.GameStateSnapshot` implementa esos datos; `levelConfig.example.json` y `gameSave.example.json` se actualizaron para reflejar la partida base real de `newGame()`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: busqueda en `src/main/java` sin imports `javafx`; compilacion correcta; JUnit con 47 tests correctos.
- Critica y riesgos: `loadConfig()` y `loadGame()` siguen sin parsear completamente JSON. Los ejemplos son contrato provisional coherente, no persistencia final.
- Decision final: entregar esta base a Track C y cerrar con el equipo el formato JSON definitivo.

### Entrada 26 - Revision general de cumplimiento

- Fecha: 21/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: revisar el codigo frente a las especificaciones conocidas.
- Prompt o peticion: "revisa todo el codigo para ver si cumplimos las especificaciones"
- Contexto proporcionado: estado completo de Track B, tests JUnit, contratos de Fase 0 y contexto del proyecto.
- Resultado obtenido: se confirmo que la logica no importa JavaFX, que produccion no usa colecciones estandar, que la base de Track B esta implementada y que los JUnit pasan. Se detectaron pendientes: persistencia JSON real, mutabilidad de datos devueltos por `IGameState`, decision de grafo dirigido/no dirigido, puertas reciprocas, posible BFS de enemigos, limite de inventario y posible `IList extends Iterable`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion y JUnit correctos con 47 tests correctos.
- Critica y riesgos: el proyecto no puede considerarse cerrado al 100% mientras la persistencia JSON final y la integracion con UI no esten implementadas.
- Decision final: anotar estos riesgos como pendientes reales antes de cerrar la entrega.

### Entrada 27 - Actualizacion de documentacion y pendientes

- Fecha: 21/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: mantener actualizados el diario de IA, el contexto del Track B y la lista de decisiones abiertas.
- Prompt o peticion: "- Actualizar documentacion: Anadir cada intervencion relevante al `diario_ia.md`. Documentar decisiones abiertas: objetos bloquean o no, apertura de puertas, llaves, inventario, rango de ataque. Mantener el contexto del Track B actualizado segun cambios reales del codigo."
- Contexto proporcionado: entradas anteriores del diario, contexto tecnico de Track B, estructura actual de tests y cambios recientes de integracion con Track C.
- Resultado obtenido: se documentaron las intervenciones recientes y se actualizo el contexto para reflejar el estado real de JUnit, `IGameState`, JSON de ejemplo y decisiones abiertas.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: revision manual de los ficheros de documentacion.
- Critica y riesgos: siguen pendientes nombres/responsables del grupo y registrar resultados de coverage en IntelliJ cuando se ejecute de forma definitiva.
- Decision final: mantener estos documentos vivos hasta el cierre de memoria.

### Entrada 28 - Copias defensivas en `IGameState`

- Fecha: 21/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: evitar que la UI pueda modificar directamente estado interno del motor mediante objetos devueltos por `IGameState`.
- Prompt o peticion: "Copias defensivas en IGameState. Ahora devuelve Room, inventario y log reales. Podemos evitar que la UI modifique estado directamente devolviendo copias con estructuras propias o anadiendo getters mas especificos."
- Contexto proporcionado: `GameStateSnapshot` devolvia referencias reales a inventario, habitacion y log; esto era un riesgo para integracion con Track C.
- Resultado obtenido: `getInventory()`, `getCurrentRoom()`, `getPathToExit()` y `getEventLog()` devuelven copias defensivas usando estructuras propias. La copia de sala crea una nueva `Room`, nuevas `Cell` y copias de enemigos/items principales.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: compilacion correcta de produccion y tests; JUnit con 48 tests encontrados, 48 correctos y 0 fallidos. Se anadio un test que intenta mutar inventario, log y sala devueltos por `IGameState` y verifica que el estado real no cambia.
- Critica y riesgos: los DTOs especificos para UI podrian ser mas limpios que exponer `Room`, pero la copia defensiva reduce el riesgo sin romper el contrato actual.
- Decision final: considerar resuelta la mutabilidad directa del snapshot como base inicial; revisar con C si necesita DTOs mas concretos.

### Entrada 29 - Cierre de reglas abiertas de la base actual

- Fecha: 21/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: fijar por tests y documentacion las reglas abiertas que pueden cerrarse antes de integrar con Track C.
- Prompt o peticion: "Decidir y fijar reglas abiertas: objetos bloquean movimiento, puertas se abren desde celda adyacente, llaves basta tenerlas en inventario, inventario sin limite, ataque solo adyacente, enemigos no pisan objetos ni puertas."
- Contexto proporcionado: varias reglas ya estaban implementadas en codigo, pero algunas seguian descritas como provisionales o pendientes en la documentacion.
- Resultado obtenido: se dejaron como decisiones cerradas de la base actual: los objetos bloquean movimiento y se recogen adyacentes; las puertas se abren desde celda adyacente; las llaves solo deben estar en inventario; inicialmente se dejo inventario sin limite; el ataque es adyacente; los enemigos no pisan objetos ni puertas.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: se anadieron tests JUnit que fijan objetos bloqueando BFS, interaccion adyacente con puertas/items, ataque solo adyacente y bloqueo de movimiento enemigo sobre objetos/puertas. La regla de inventario se ajusto despues en la Entrada 30.
- Critica y riesgos: si el profesor o el grupo cambia alguna de estas reglas, habra que actualizar codigo, tests y memoria de forma coordinada.
- Decision final: tratar estas reglas como contrato de Track B para la integracion inicial con Track C.

### Entrada 30 - Limite de inventario

- Fecha: 21/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: cambiar la regla de inventario para que tenga capacidad maxima.
- Prompt o peticion: "el inventario tiene que tener limite"
- Contexto proporcionado: existia `InventoryFullException`, pero el inventario estaba documentado y probado como ilimitado.
- Resultado obtenido: se fijo `Player.MAX_INVENTORY_SIZE = 10`; `Player.addItem()` lanza `InventoryFullException` si se supera el limite; el constructor rechaza inventarios iniciales con mas de 10 items; `IGameState` expone `getMaxInventorySize()` e `isInventoryFull()` para la UI; los JSON de ejemplo incluyen `inventarioMaximo`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: se actualizaron tests JUnit para comprobar limite de inventario y excepcion.
- Critica y riesgos: si el enunciado exige otra capacidad concreta, solo hay que cambiar la constante `Player.MAX_INVENTORY_SIZE` y los ejemplos JSON.
- Decision final: inventario limitado a 10 items como contrato de Track B.

### Entrada 31 - Movimiento enemigo con BFS

- Fecha: 21/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: sustituir el movimiento heuristico de enemigos por BFS antes de integrar con Track C.
- Prompt o peticion: "Movimiento enemigo con BFS o dejarlo oficialmente heurístico. Si el enunciado valora pathfinding, podemos cambiar enemigos a BFS antes de integrar."
- Contexto proporcionado: `Enemy.moveOneStepToward()` usaba una heuristica codiciosa Manhattan y ya existian estructuras propias de cola y BFS de matriz.
- Resultado obtenido: `Enemy.moveOneStepToward()` ahora usa BFS con `MyLinkedQueue` para encontrar la ruta mas corta hasta una celda adyacente al jugador y mover un paso por turno. No pisa objetos, puertas, enemigos ni la celda del jugador.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: se actualizo el test de movimiento enemigo para comprobar que rodea obstaculos con BFS y conserva bloqueados items/puertas. JUnit pasa con 52 tests correctos.
- Critica y riesgos: el metodo conserva semantica de un paso por turno (`moveOneStepToward`); si se quiere usar `speed` del enemigo para varios pasos, habra que ampliar la regla y los tests.
- Decision final: movimiento enemigo con BFS como contrato de Track B.

### Entrada 32 - Grafo de habitaciones dirigido/no dirigido

- Fecha: 21/05/2026
- Persona responsable: pendiente de completar
- Herramienta/agente: Codex
- Objetivo: resolver antes de integrar con Track C si el grafo de habitaciones debe duplicar conexiones o soportar aristas no dirigidas.
- Prompt o peticion: "Grafo dirigido/no dirigido. Podemos añadir un helper tipo addUndirectedEdge() o documentar que el JSON debe declarar ambas direcciones. Mejor resolverlo antes de C."
- Contexto proporcionado: `MyGraph.addEdge` era dirigido y `GameEngineImpl.newGame()` insertaba manualmente las dos direcciones entre entrada y sala final.
- Resultado obtenido: se mantuvo `addEdge` como arista dirigida y se anadieron `addUndirectedEdge(first, second)` y `addUndirectedEdge(first, second, weight)` a `IGraph`/`MyGraph`. `GameEngineImpl.newGame()` usa ahora `addUndirectedEdge`. El JSON de ejemplo declara una unica conexion con `dirigida: false`.
- Cambios realizados por el equipo: pendiente de revision por el equipo.
- Validacion: se anadio un test JUnit para comprobar que `addUndirectedEdge` permite distancia y vecinos en ambos sentidos.
- Critica y riesgos: Track C debe respetar la propiedad `dirigida`; si no aparece, se recomienda tratar conexiones de habitaciones como no dirigidas por defecto.
- Decision final: el mapa de habitaciones usa conexiones no dirigidas salvo que el JSON final indique explicitamente lo contrario.

## Metodologia provisional extraida

1. Leer primero la documentacion oficial del proyecto.
2. Pedir a la IA tareas acotadas: resumen, plan, diseno, implementacion de una clase concreta o tests.
3. Revisar cada resultado contra la especificacion.
4. Registrar el prompt, resultado y cambios aplicados.
5. Validar con tests o revision manual.
6. Anotar errores, limitaciones y decisiones propias del equipo.

## Pendientes de completar

- Nombres de los miembros del grupo.
- Persona responsable de cada entrada.
- Entradas futuras de implementacion.
- Critica final del uso de IA.
- Metodologia final definitiva tras terminar el proyecto.
