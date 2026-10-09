package view;

import model.domain.Estudiante;
import model.domain.Profesor;
import service.EstudianteService;
import service.ProfesorService;
import utils.TypeValidator;

public class MenuView {
    TypeValidator tv = new TypeValidator();
    EstudianteService estudianteService = new EstudianteService();
    ProfesorService profesorService = new ProfesorService();

    public void iniciar() {
        int opcionInicial;
        do {
            opcionInicial = mostrarMenuInicial();

            if (opcionInicial == 1) {
                gestionarEstudiantes();
            } else if (opcionInicial == 2) {
                gestionarProfesores();
            }
        } while (opcionInicial != 0);
    }

    private void gestionarEstudiantes() {
        int opcion;
        do {
            opcion = mostrarMenuDeGestion(1);
            switch (opcion) {
                case 1 -> crearEstudiante();
                case 2 -> buscarEstudiantePorIndice();
                case 3 -> buscarEstudiantePorCedula();
                case 4 -> actualizarEstudiantePorCedula();
                case 5 -> eliminarEstudiantePorCedula();
                case 6 -> listarEstudiantes();
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

    private void crearEstudiante() {
        String nombre = tv.leerString("Ingrese el nombre del estudiante:");
        String correo = tv.leerString("Ingrese el correo del estudiante:");
        String identificacion = tv.leerString("Ingrese la cedula del estudiante:");
        String codigo = tv.leerString("Ingrese el codigo del estudiante:");
        int semestreActual = tv.leerIntEnRango(1, 12, "Ingrese el semestre actual del estudiante (1-12):");

        try {
            estudianteService.crearEstudiante(nombre, correo, identificacion, codigo, semestreActual);
            tv.mensaje("Estudiante creado con exito");
        } catch (Exception ex) {
            tv.mensaje("No se pudo crear el estudiante, correo invalido");
        }
    }

    private void buscarEstudiantePorIndice() {
        if (estudianteService.estaVacia()) {
            tv.mensaje("No hay estudiantes registrados");
            return;
        }
        int indice = tv.leerIntEnRango(0, estudianteService.getTamanio() - 1, "Ingrese el indice del estudiante:");
        Estudiante estudiante = estudianteService.buscarPorIndice(indice);
        tv.mensaje(estudiante.datosResumen());
    }

    private void buscarEstudiantePorCedula() {
        String cedula = tv.leerString("Ingrese la cedula del estudiante a buscar:");
        Estudiante estudiante = estudianteService.buscarPorCedula(cedula);
        if (estudiante == null) {
            tv.mensaje("No se encontro un estudiante con esa cedula");
        } else {
            tv.mensaje(estudiante.datosResumen());
        }
    }

    private void actualizarEstudiantePorCedula() {
        String cedula = tv.leerString("Ingrese la cedula del estudiante a actualizar:");
        Estudiante estudiante = estudianteService.buscarPorCedula(cedula);
        if (estudiante == null) {
            tv.mensaje("No se encontro un estudiante con esa cedula");
            return;
        }
        String nombre = tv.leerString("Ingrese el nuevo nombre:");
        String correo = tv.leerString("Ingrese el nuevo correo:");
        String codigo = tv.leerString("Ingrese el nuevo codigo:");
        int semestreActual = tv.leerIntEnRango(1, 12, "Ingrese el nuevo semestre actual (1-12):");

        estudianteService.actualizarEstudiante(estudiante, nombre, correo, codigo, semestreActual);
        tv.mensaje("Estudiante actualizado con exito");
    }

    private void eliminarEstudiantePorCedula() {
        String cedula = tv.leerString("Ingrese la cedula del estudiante a eliminar:");
        Estudiante estudiante = estudianteService.buscarPorCedula(cedula);
        if (estudiante == null) {
            tv.mensaje("No se encontro un estudiante con esa cedula");
            return;
        }
        if (estudianteService.eliminarEstudiante(estudiante)) {
            tv.mensaje("Estudiante eliminado con exito");
        }
    }

    private void listarEstudiantes() {
        if (estudianteService.estaVacia()) {
            tv.mensaje("No hay estudiantes registrados");
            return;
        }
        String listado = "";
        for (int i = 0; i < estudianteService.getTamanio(); i++) {
            Estudiante actual = estudianteService.buscarPorIndice(i);
            listado += (i + ". " + actual.datosResumen() + "\n");
        }
        tv.mensaje(listado);
    }

    private void crearProfesor() {
        String nombre = tv.leerString("Ingrese el nombre del profesor:");
        String correo = tv.leerString("Ingrese el correo del profesor:");
        String identificacion = tv.leerString("Ingrese la cedula del profesor:");
        String codigo = tv.leerString("Ingrese el codigo del profesor:");
        String departamento = tv.leerString("Ingrese el departamento del profesor:");

        try {
            profesorService.crearProfesor(nombre, correo, identificacion, codigo, departamento);
            tv.mensaje("Profesor creado con exito");
        } catch (IllegalArgumentException ex) {
            tv.mensaje("No se pudo crear el profesor, correo invalido");
        }
    }

    private void buscarProfesorPorIndice() {
        if (profesorService.estaVacia()) {
            tv.mensaje("No hay profesores registrados");
            return;
        }
        int indice = tv.leerIntEnRango(0, profesorService.getTamanio() - 1, "Ingrese el indice del profesor:");
        Profesor profesor = profesorService.buscarPorIndice(indice);
        tv.mensaje(profesor.datosResumen());
    }

    private void buscarProfesorPorCedula() {
        String cedula = tv.leerString("Ingrese la cedula del profesor a buscar:");
        Profesor profesor = profesorService.buscarPorCedula(cedula);
        if (profesor == null) {
            tv.mensaje("No se encontro un profesor con esa cedula");
        } else {
            tv.mensaje(profesor.datosResumen());
        }
    }

    private void actualizarProfesorPorCedula() {
        String cedula = tv.leerString("Ingrese la cedula del profesor a actualizar:");
        Profesor profesor = profesorService.buscarPorCedula(cedula);
        if (profesor == null) {
            tv.mensaje("No se encontro un profesor con esa cedula");
            return;
        }
        String nombre = tv.leerString("Ingrese el nuevo nombre:");
        String correo = tv.leerString("Ingrese el nuevo correo:");
        String codigo = tv.leerString("Ingrese el nuevo codigo:");
        String departamento = tv.leerString("Ingrese el nuevo departamento:");

        profesorService.actualizarProfesor(profesor, nombre, correo, codigo, departamento);
        tv.mensaje("Profesor actualizado con exito");
    }

    private void eliminarProfesorPorCedula() {
        String cedula = tv.leerString("Ingrese la cedula del profesor a eliminar:");
        Profesor profesor = profesorService.buscarPorCedula(cedula);
        if (profesor == null) {
            tv.mensaje("No se encontro un profesor con esa cedula");
            return;
        }
        if (profesorService.eliminarProfesor(profesor)) {
            tv.mensaje("Profesor eliminado con exito");
        }
    }

    private void listarProfesores() {
        if (profesorService.estaVacia()) {
            tv.mensaje("No hay profesores registrados");
            return;
        }
        String listado = "";
        for (int i = 0; i < profesorService.getTamanio(); i++) {
            Profesor actual = profesorService.buscarPorIndice(i);
            listado += (i + ". " + actual.datosResumen() + "\n");
        }
        tv.mensaje(listado);
    }

    public int mostrarMenuInicial() {
        String mensaje = " BIENVENIDO :D! \n Elija una opcion segun su necesidad \n " +
            "1. gestionar estudiantes \n 2. gestionar profesores \n 0. salir";
        return tv.leerIntEnRango(0, 2, mensaje);
    }

    public int mostrarMenuDeGestion(int opcion) {
        if (opcion == 1) {
            String menu = generarMenuEstudiante();
            return tv.leerIntEnRango(0, 6, menu);
        } else {
            String menu = generarMenuProfesor();
            return tv.leerIntEnRango(0, 6, menu);
        }
    }

    public String generarMenuEstudiante() {
        return ("1. crear estudiante \n 2. buscar estudiante por indice \n 3. buscar estudiante por cedula \n 4. actualizar estudiante por cedula \n 5. eliminar estudiante por cedula \n 6. mostrar todos los estudiantes \n 0. en caso de que quiera devolverse");
    }

    public String generarMenuProfesor() {
        return ("1. crear profesor \n 2. buscar profesor por indice \n 3. buscar profesor por cedula \n 4. actualizar profesor por cedula \n 5. eliminar profesor por cedula \n 6. mostrar todos los profesores \n 0. en caso de que se quiera devolver");
    }
}