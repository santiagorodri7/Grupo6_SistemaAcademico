# Retroalimentación — Lista Simple (Momento 2)

**Grupo:** Grupo6 · **Proyecto:** Sistema Académico

## Nota

| Criterio | Peso | Nota (0-5) |
|---|---|---|
| Identificación de las relaciones uno-a-muchos | 20 % | 5.0 |
| `ListaSimple<T>` integrada al `Service` | 30 % | 3.5 |
| Menú en consola funcional | 15 % | 3.5 |
| Reemplazo del arreglo previo, sin código muerto | 20 % | 4.0 |
| Buenas prácticas (commits y nombres) | 15 % | 3.0 |
| **Nota del laboratorio** | | **3.83** |

La nota se calcula así: cada criterio (de 0 a 5) se multiplica por su peso y se suman los resultados, para una nota de 0 a 5.

## 1. Relaciones uno-a-muchos (5.0)
**Lo que hicieron bien:**
- Eligieron dos listas con sentido en su dominio: la de estudiantes y la de profesores, cada una con muchos elementos.
- Cada lista vive en su `Service` (`EstudianteService` y `ProfesorService`), que es quien las administra.

## 2. Lista integrada al `Service` (3.5)
**Lo que hicieron bien:**
- Reutilizaron su clase de lista genérica en los dos `Service` y usan `insertarFinal` para crear y `eliminarPorValor` para borrar.
- Los `Service` ofrecen crear, buscar por posición, buscar por cédula, actualizar, eliminar y saber si la lista está vacía o cuántos elementos tiene.
- La vista no toca la lista: siempre pasa por el `Service`.

**Lo que pueden mejorar:**
- `buscarPorInidice` tiene un error: el recorrido se detiene un paso antes, así que las posiciones 0 y 1 devuelven el mismo elemento y las demás devuelven el anterior al pedido. Por eso `buscarPorCedula` nunca encuentra al último elemento y el listado repite al primero.
- En `insertarInicio` e `insertarIndice` hay detalles por revisar, y `Lista()` no es un constructor, así que nunca se ejecuta.

## 3. Menú en consola (3.5)
**Lo que hicieron bien:**
- Hay un menú organizado con crear, buscar, actualizar, eliminar y listar, tanto para estudiantes como para profesores.

**Lo que pueden mejorar:**
- Por el error de `buscarPorInidice`, buscar y listar muestran datos equivocados cuando hay varios elementos.

## 4. Reemplazo del arreglo previo (4.0)
**Lo que hicieron bien:**
- No quedan arreglos ni `ArrayList` en el proyecto; todo usa su lista.

**Lo que pueden mejorar:**
- Quedó código sin uso: el método `Lista()` y el constructor vacío de `Calificacion`.

## 5. Buenas prácticas (3.0)
**Lo que hicieron bien:**
- Commits frecuentes, repartidos entre varios integrantes, con ramas por persona y fusiones a `main`.

**Lo que pueden mejorar:**
- No siguieron la estructura de carpetas acordada en clase: el paquete está como `domain.model` (al revés de `model.domain`) y las estructuras quedaron en `domain/model/structures` en vez de `model/structures`.
- La clase de lista se llama `List` (el nombre pedido es `ListaSimple`) y choca con la `List` de Java.
- Algunos nombres no siguen las convenciones de Java: `buscarPorInidice` (con error de escritura), `Mensaje` (empieza en mayúscula) y `tv`.
- Varios mensajes de commit son poco claros o tienen errores de escritura.

## Para el próximo laboratorio
- Corrijan `buscarPorInidice` y prueben con varios elementos.
- Renombren la clase a `ListaSimple` y ajusten los paquetes a `model.domain` y `model.structures`.
- Eliminen el código que no se usa y corrijan los nombres.
- Escriban mensajes de commit claros que digan qué cambió.
