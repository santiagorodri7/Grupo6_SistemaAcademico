# Retroalimentación — Pilas y Colas en el proyecto de aula

**Grupo:** Grupo6 · **Proyecto:** Sistema Académico

Muy buen trabajo: las dos estructuras funcionan bien y se integran con el resto del proyecto.

## Nota

| Criterio | Pilas | Colas |
|---|---|---|
| `Pila<T>` / `Cola<T>` correcta | 30 / 30 | 30 / 30 |
| Casos límite | 10 / 10 | 10 / 10 |
| Entidad dueña y UML (solo Colas) | – | 14 / 15 |
| Integración en entidad y `Service` (Pilas) / en el `Service` (Colas) | 27 / 30 | 23 / 25 |
| Menú en `view/` | 11 / 15 | 7 / 10 |
| Buenas prácticas | 9 / 15 | 6 / 10 |
| **Total** | **87 / 100** | **90 / 100** |

**Nota del laboratorio: 4.43**

La nota es el promedio de los dos totales (Pilas y Colas) dividido entre 20.

## 1. Estructuras `Pila<T>` y `Cola<T>`, y casos límite (Pilas 40 · Colas 40)
**Lo que hicieron bien:**
- `push`, `pop`, `peek`, `enqueue`, `dequeue` y `front` funcionan sin recorridos y respetan LIFO y FIFO.
- Con la pila o la cola vacía lanzan la excepción correcta con mensaje, y no aceptan valores nulos.
- Al sacar el último elemento de la cola, `frente` y `fin` quedan en nulo, y se puede encolar de nuevo sin problema.

## 2. Entidad dueña y `Service` (Pilas 27 · Colas 14 + 23)
**Lo que hicieron bien:**
- `ProcesoMatricula` coincide con el diagrama: la fila es privada, se crea en el constructor y no tiene `setFila`.
- Los `Service` tienen los cinco métodos más el listado, y ninguno recibe ni devuelve la pila o la cola.
- Listar deja la pila y la fila exactamente como estaban; atender no borra al estudiante de la lista.

**Lo que pueden mejorar:**
- La pila quedó en `Matricula`, no en la entidad que representa la materia como pedía la tabla.
- Deshacer solo retira la última calificación; no hay ninguna otra reversión sobre el dominio.
- En Colas dejaron nombres como `sinClientesEnEspera` en vez de nombres de su dominio (estudiantes), y crearon `ProcesoMatricula("null")` con un semestre de relleno.

## 3. Menús en `view/` (Pilas 11 · Colas 7)
**Lo que hicieron bien:**
- Ambos menús tienen las seis opciones, no usan `try / catch` ni importan nada de `model.structures`.
- Con la pila o la fila vacía muestran un mensaje y el programa sigue funcionando.

**Lo que pueden mejorar:**
- No crearon ni usaron la clase `ConsoleUtils` pedida; leen con sus propias clases `TypeValidator` e `InputValidator`.
- En el menú de Colas, registrar una llegada llama a dos métodos del `Service` en vez de uno.

## 4. Buenas prácticas (Pilas 9 · Colas 6)
**Lo que hicieron bien:**
- Muchos commits con mensajes claros, varias personas aportando y trabajo fusionado a `main` con merges.
- Corrigieron nombres para seguir camelCase.

**Lo que pueden mejorar:**
- No siguieron la estructura de carpetas acordada en clase: `Pila`, `Cola`, `Nodo` y `ListaSimple` están en `model/domain/structures/` y deben estar en `model/structures/`.
- Las ramas se llamaron `pilas` y `Vasil`, no `feature/pila-en-el-proyecto` ni `feature/cola-en-el-proyecto`.
- Algunos commits mezclan varios cambios a la vez.
