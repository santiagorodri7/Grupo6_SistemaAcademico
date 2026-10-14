# Retroalimentación — Lista Simple (Momento 2)

**Grupo:** Grupo6 · **Proyecto:** Sistema Académico

## Nota

| Criterio | Peso | Nota (0-5) |
|---|---|---|
| Identificación de las relaciones uno-a-muchos | 20 % | 5.0 |
| `ListaSimple<T>` integrada al `Service` | 30 % | 4.5 |
| Menú en consola funcional | 15 % | 4.5 |
| Reemplazo del arreglo previo, sin código muerto | 20 % | 5.0 |
| Buenas prácticas (commits y nombres) | 15 % | 3.5 |
| **Nota del laboratorio** | | **4.55** |

La nota se calcula así: cada criterio (de 0 a 5) se multiplica por su peso y se suman los resultados, para una nota de 0 a 5.

## 1. Relaciones uno-a-muchos (5.0)
**Lo que hicieron bien:**
- Eligieron dos listas con sentido en su dominio: la de estudiantes y la de profesores, cada una con muchos elementos.
- Cada lista vive en su `Service` (`EstudianteService` y `ProfesorService`), que es quien las administra.

## 2. Lista integrada al `Service` (4.5)
**Lo que hicieron bien:**
- Reutilizaron su clase de lista genérica en los dos `Service` y usan `insertarFinal` para crear y `eliminarPorValor` para borrar.
- Los `Service` ofrecen crear, buscar por posición, buscar por cédula, actualizar, eliminar y saber si la lista está vacía o cuántos elementos tiene.
- La vista no toca la lista: siempre pasa por el `Service`.
- La búsqueda por posición recorre bien la lista, así que buscar por cédula y el listado muestran los datos correctos.

**Lo que pueden mejorar:**
- `insertarIndice` no revisa que la posición sea válida: si piden una posición negativa o mayor al tamaño, el programa se cae en vez de avisar con un error claro, como sí hace la búsqueda por posición.
- En `insertarIndice`, el nodo nuevo se crea al principio, pero cuando la posición es la primera o la última se descarta, porque esos casos crean su propio nodo. Conviene crearlo solo en el caso del medio.
- En `insertarFinal`, el nodo nuevo nace apuntando al inicio de la lista y después se corrige a vacío. Funciona, pero es más claro crearlo apuntando a vacío desde el principio.

## 3. Menú en consola (4.5)
**Lo que hicieron bien:**
- Hay un menú organizado con crear, buscar, actualizar, eliminar y listar, tanto para estudiantes como para profesores.
- El menú muestra bien los datos al buscar y listar, incluso con varios elementos.

## 4. Reemplazo del arreglo previo (5.0)
**Lo que hicieron bien:**
- No quedan arreglos ni `ArrayList` en el proyecto; todo usa su lista.

## 5. Buenas prácticas (3.5)
**Lo que hicieron bien:**
- Commits frecuentes, repartidos entre varios integrantes, con ramas por persona y fusiones a `main`.
- La clase de lista se llama `ListaSimple`, como se pidió, y los paquetes de dominio están como `model.domain`.

**Lo que pueden mejorar:**
- Las estructuras quedaron en `model/domain/structures`; la estructura acordada en clase es `model/structures`.
- Algunos nombres siguen sin cumplir las convenciones de Java: `buscarPorInidice` (con error de escritura) y `Mensaje` en `InputValidator` (empieza en mayúscula).
- Algunos mensajes de commit tienen errores de escritura.

## Para el próximo laboratorio
- Validen la posición en `insertarIndice` y prueben con posiciones fuera de rango.
- Muevan las estructuras a `model/structures`.
- Corrijan los nombres `buscarPorInidice` y `Mensaje`.
- Escriban mensajes de commit claros y sin errores de escritura.
