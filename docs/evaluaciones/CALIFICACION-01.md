# Retroalimentación — Laboratorio 1: Codificación del diseño OO

**Grupo:** Grupo6 · **Proyecto:** Sistema Académico
**Fecha límite:** 2026-09-08 23:59 · **Versión revisada:** commit `4ccf35f`

¡Buen trabajo! Las pruebas y la forma de trabajar en equipo están muy bien; falta completar dos clases del diagrama.

## Nota

| Criterio | Peso | Nota (0-5) |
|---|---|---|
| El código sigue el diagrama UML | 60% | 4.0 |
| Pruebas: creación de objetos en el programa | 20% | 5.0 |
| Buenas prácticas de programación | 20% | 5.0 |
| **Nota del laboratorio** | | **4.40** |

La nota se calcula así: 60% diseño UML + 20% pruebas + 20% buenas prácticas.

## 1. El código sigue el diagrama UML (4.0)
**Lo que hicieron bien:**
- La interfaz `RolAcademico` tiene `datosResumen()` e `identificarRol()`, como pide el diagrama.
- `Persona` es abstracta, implementa `RolAcademico` y protege sus tres atributos. Resuelve `datosResumen()` y deja `identificarRol()` para las subclases.
- El constructor de `Persona` revisa que el correo tenga `@` y lanza un error si no lo tiene. Un detalle: antes del error imprime un mensaje en consola que no hace falta.
- `Estudiante` y `Profesor` heredan de `Persona`, tienen sus propios atributos y cada uno resuelve `identificarRol()`.
- `Calificacion` tiene sus cuatro atributos protegidos y su relación con `Materia`.

**Lo que pueden mejorar:**
- `Matricula` está vacía. Le falta la lista de Calificaciones (una Matrícula tiene una o varias Calificaciones) y la relación con `Estudiante`. Es una parte importante del diagrama.
- `Materia` también está vacía. El diagrama no le pide atributos, pero sin nada adentro no se ve que esté bien construida.
- No siguieron la estructura acordada en clase: las clases están en `src/src/domain/model` en vez de `src/model/domain`, y ninguna tiene la línea `package ...;` al inicio. Sin ella, Java no sabe que las clases pertenecen al paquete del dominio.
- Quedó un `Profesor.java` viejo en la carpeta `model/domain/` de la raíz. Como hay dos clases `Profesor`, si se compila todo el repositorio junto, Java marca error.

## 2. Pruebas: creación de objetos (5.0)
**Lo que hicieron bien:**
- `PruebaCreacionObjetos` está fuera del paquete del dominio, que es donde debe ir.
- Crean un `Estudiante` y un `Profesor`, llaman a `identificarRol()` y `datosResumen()` en cada uno, y en pantalla se ve que cada uno responde diferente.

## 3. Buenas prácticas (5.0)
**Lo que hicieron bien:**
- Hicieron commits seguido durante varias semanas, con mensajes que explican el cambio.
- Trabajaron en ramas por integrante y unieron los cambios a `main` con Pull Requests. Muy bien.
- Los nombres siguen la convención de Java: clases con mayúscula inicial (`Estudiante`) y métodos y atributos en camelCase (`semestreActual`).

## ¿El programa funciona?
Sí, si se compilan solo los archivos correctos del proyecto: muestra los datos del estudiante y del profesor sin errores. Pero si se compila todo el repositorio, falla por el `Profesor.java` repetido.

## Para el próximo laboratorio
- Borren la carpeta `src/src/` y el `Profesor.java` viejo. Pongan las clases del dominio en `src/model/domain/` y agreguen `package model.domain;` al inicio de cada archivo.
- Completen `Matricula`: una lista de Calificaciones, la relación con `Estudiante`, un constructor que valide los datos, y getters y setters.
- Quiten el mensaje por consola que aparece antes del error de correo en `Persona`.
