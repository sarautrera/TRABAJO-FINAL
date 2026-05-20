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
