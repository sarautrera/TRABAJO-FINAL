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
