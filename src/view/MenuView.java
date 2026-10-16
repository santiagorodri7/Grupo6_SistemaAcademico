package view;

import model.domain.Calificacion;
import model.domain.Estudiante;
import model.domain.Materia;
import model.domain.Profesor;
import service.CalificacionService;
import service.EstudianteService;
import service.ProfesorService;
import utils.TypeValidator;

public class MenuView {
    TypeValidator typeValidator = new TypeValidator();
    EstudianteService estudianteService = new EstudianteService();
    ProfesorService profesorService = new ProfesorService();
    CalificacionService calificacionService = new CalificacionService();

    public void iniciar() {
        int opcionInicial;
        do {
            opcionInicial = mostrarMenuInicial();

            if (opcionInicial == 1) {
                gestionarEstudiantes();
            } else if (opcionInicial == 2) {
                gestionarProfesores();
            } else if (opcionInicial == 3) {
                gestionarCalificaciones();
            }
        } while (opcionInicial != 0);
    }

    private void gestionarEstudiantes() {
        int opcion;
        do {
            opcion = mostrarMenuDeGestion(1);
            switch (opcion) {
                case 1 -> crearEstudiante();
                case 2 -> eliminarPrimerEstudiante();
                case 3 -> verTamanioEstudiantes();
            }
        } while (opcion != 0);
    }

    private void gestionarProfesores() {
        int opcion;
        do {
            opcion = mostrarMenuDeGestion(2);
            switch (opcion) {
                case 1 -> crearProfesor();
                case 2 -> buscarProfesorPorIndice();
                case 3 -> buscarProfesorPorCedula();
                case 4 -> actualizarProfesorPorCedula();
                case 5 -> eliminarProfesorPorCedula();
                case 6 -> listarProfesores();
            }
        } while (opcion != 0);
    }

    private void gestionarCalificaciones() {
        int opcion;
        do {
            opcion = mostrarMenuDeGestion(3);
            switch (opcion) {
                case 1 -> crearCalificacion();
                case 2 -> verUltimaCalificacion();
                case 3 -> eliminarUltimaCalificacion();
                case 4 -> listarCalificaciones();
            }
        } while (opcion != 0);
    }

    private void crearEstudiante() {
        String nombre = typeValidator.leerString("Ingrese el nombre del estudiante:");
        String correo = typeValidator.leerString("Ingrese el correo del estudiante:");
        String identificacion = typeValidator.leerString("Ingrese la cedula del estudiante:");
        String codigo = typeValidator.leerString("Ingrese el codigo del estudiante:");
        int semestreActual = typeValidator.leerIntEnRango(1, 12, "Ingrese el semestre actual del estudiante (1-12):");

        try {
            estudianteService.crearEstudiante(nombre, correo, identificacion, codigo, semestreActual);
            typeValidator.mensaje("Estudiante creado con exito");
        } catch (Exception ex) {
            typeValidator.mensaje("No se pudo crear el estudiante, correo invalido");
        }
    }

    private void eliminarPrimerEstudiante() {
        if (estudianteService.estaVacia()) {
            typeValidator.mensaje("No hay estudiantes registrados en la cola");
            return;
        }
        Estudiante estudiante = estudianteService.eliminarPrimerEstudiante();
        typeValidator.mensaje("Estudiante atendido/eliminado de la cola:\n" + estudiante.datosResumen());
    }

    private void verTamanioEstudiantes() {
        if (estudianteService.estaVacia()) {
            typeValidator.mensaje("No hay estudiantes registrados");
            return;
        }
        typeValidator.mensaje("Cantidad de estudiantes en la cola: " + estudianteService.getTamanio());
    }

    private void crearProfesor() {
        String nombre = typeValidator.leerString("Ingrese el nombre del profesor:");
        String correo = typeValidator.leerString("Ingrese el correo del profesor:");
        String identificacion = typeValidator.leerString("Ingrese la cedula del profesor:");
        String codigo = typeValidator.leerString("Ingrese el codigo del profesor:");
        String departamento = typeValidator.leerString("Ingrese el departamento del profesor:");

        try {
            profesorService.crearProfesor(nombre, correo, identificacion, codigo, departamento);
            typeValidator.mensaje("Profesor creado con exito");
        } catch (IllegalArgumentException ex) {
            typeValidator.mensaje("No se pudo crear el profesor, correo invalido");
        }
    }

    private void buscarProfesorPorIndice() {
        if (profesorService.estaVacia()) {
            typeValidator.mensaje("No hay profesores registrados");
            return;
        }
        int indice = typeValidator.leerIntEnRango(0, profesorService.getTamanio() - 1, "Ingrese el indice del profesor:");
        Profesor profesor = profesorService.buscarPorIndice(indice);
        typeValidator.mensaje(profesor.datosResumen());
    }

    private void buscarProfesorPorCedula() {
        String cedula = typeValidator.leerString("Ingrese la cedula del profesor a buscar:");
        Profesor profesor = profesorService.buscarPorCedula(cedula);
        if (profesor == null) {
            typeValidator.mensaje("No se encontro un profesor con esa cedula");
        } else {
            typeValidator.mensaje(profesor.datosResumen());
        }
    }

    private void actualizarProfesorPorCedula() {
        String cedula = typeValidator.leerString("Ingrese la cedula del profesor a actualizar:");
        Profesor profesor = profesorService.buscarPorCedula(cedula);
        if (profesor == null) {
            typeValidator.mensaje("No se encontro un profesor con esa cedula");
            return;
        }
        String nombre = typeValidator.leerString("Ingrese el nuevo nombre:");
        String correo = typeValidator.leerString("Ingrese el nuevo correo:");
        String codigo = typeValidator.leerString("Ingrese el nuevo codigo:");
        String departamento = typeValidator.leerString("Ingrese el nuevo departamento:");

        profesorService.actualizarProfesor(profesor, nombre, correo, codigo, departamento);
        typeValidator.mensaje("Profesor actualizado con exito");
    }

    private void eliminarProfesorPorCedula() {
        String cedula = typeValidator.leerString("Ingrese la cedula del profesor a eliminar:");
        Profesor profesor = profesorService.buscarPorCedula(cedula);
        if (profesor == null) {
            typeValidator.mensaje("No se encontro un profesor con esa cedula");
            return;
        }
        if (profesorService.eliminarProfesor(profesor)) {
            typeValidator.mensaje("Profesor eliminado con exito");
        }
    }

    private void listarProfesores() {
        if (profesorService.estaVacia()) {
            typeValidator.mensaje("No hay profesores registrados");
            return;
        }
        String listado = "";
        for (int i = 0; i < profesorService.getTamanio(); i++) {
            Profesor actual = profesorService.buscarPorIndice(i);
            listado += (i + ". " + actual.datosResumen() + "\n");
        }
        typeValidator.mensaje(listado);
    }

    private void crearCalificacion() {
        double notaParcial1 = leerDoubleSeguro("Ingrese la nota del parcial 1:");
        double notaParcial2 = leerDoubleSeguro("Ingrese la nota del parcial 2:");
        double notaFinal = leerDoubleSeguro("Ingrese la nota final:");
        String observaciones = typeValidator.leerString("Ingrese las observaciones:");
        
        
        String nombreMateria = typeValidator.leerString("Ingrese el nombre de la materia:");
        String codigoMateria = typeValidator.leerString("Ingrese el codigo de la materia:");
        int creditosMateria = typeValidator.leerIntEnRango(1, 10, "Ingrese los creditos de la materia (1-10):");
        
        Materia materia = new Materia(nombreMateria, codigoMateria, creditosMateria);

        try {
            calificacionService.crearCalificacion(notaParcial1, notaParcial2, notaFinal, observaciones, materia);
            typeValidator.mensaje("Calificacion creada con exito");
        } catch (Exception ex) {
            typeValidator.mensaje("No se pudo crear la calificacion");
        }
    }

    private double leerDoubleSeguro(String mensaje) {
        while (true) {
            try {
                String entrada = typeValidator.leerString(mensaje);
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                typeValidator.mensaje("Por favor, ingrese un número válido.");
            }
        }
    }

    private void verUltimaCalificacion() {
        if (calificacionService.estaVacia()) {
            typeValidator.mensaje("No hay calificaciones registradas");
            return;
        }
        Calificacion calificacion = calificacionService.seleccionarUltimaCalificacion();
        typeValidator.mensaje(calificacion.toString());
    }

    private void eliminarUltimaCalificacion() {
        if (calificacionService.estaVacia()) {
            typeValidator.mensaje("No hay calificaciones para eliminar");
            return;
        }
        calificacionService.borrarUltimaCalificacion();
        typeValidator.mensaje("Ultima calificacion eliminada con exito");
    }

    private void listarCalificaciones() {
        if (calificacionService.estaVacia()) {
            typeValidator.mensaje("No hay calificaciones registradas");
            return;
        }
        typeValidator.mensaje("Total de calificaciones en la pila: " + calificacionService.getTamanio() + 
                            "\nUltima calificacion (cima): " + calificacionService.seleccionarUltimaCalificacion().toString());
    }

    public int mostrarMenuInicial() {
        String mensaje = " BIENVENIDO :D! \n Elija una opcion segun su necesidad \n " +
                "1. gestionar estudiantes \n 2. gestionar profesores \n 3. gestionar calificaciones \n 0. salir";
        return typeValidator.leerIntEnRango(0, 3, mensaje);
    }

    public int mostrarMenuDeGestion(int opcion) {
        switch (opcion) {
            case 1 -> {
                String menu = generarMenuEstudiante();
                return typeValidator.leerIntEnRango(0, 3, menu);
            }
            case 2 -> {
                String menu = generarMenuProfesor();
                return typeValidator.leerIntEnRango(0, 6, menu);
            }
            default -> {
                String menu = generarMenuCalificacion();
                return typeValidator.leerIntEnRango(0, 4, menu);
            }
        }
    }

    public String generarMenuEstudiante() {
        return ("1. crear estudiante (enqueue)\n 2. eliminar/atender primer estudiante (dequeue)\n 3. ver cantidad de estudiantes\n 0. en caso de que quiera devolverse");
    }

    public String generarMenuProfesor() {
        return ("1. crear profesor \n 2. buscar profesor por indice \n 3. buscar profesor por cedula \n 4. actualizar profesor por cedula \n 5. eliminar profesor por cedula \n 6. mostrar todos los profesores \n 0. en caso de que se quiera devolver");
    }

    public String generarMenuCalificacion() {
        return ("1. crear calificacion (push) \n 2. ver ultima calificacion (peek) \n 3. eliminar ultima calificacion (pop) \n 4. mostrar estado de calificaciones \n 0. en caso de que se quiera devolver");
    }
}