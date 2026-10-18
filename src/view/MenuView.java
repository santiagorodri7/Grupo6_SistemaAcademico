package view;

import service.CalificacionService;
import service.EstudianteService;
import service.MatriculaService;
import service.ProfesorService;
import utils.TypeValidator;

public class MenuView {

    private final TypeValidator typeValidator = new TypeValidator();
    private final EstudianteService estudianteService = new EstudianteService();
    private final ProfesorService profesorService = new ProfesorService();
    private final MatriculaService matriculaService = new MatriculaService(estudianteService);
    private final CalificacionService calificacionService = new CalificacionService(matriculaService);
    private final MenuPilaView menuPilaView = new MenuPilaView(matriculaService, calificacionService);

    public void iniciar() {
        int opcion;
        do {
            opcion = mostrarMenuInicial();
            switch (opcion) {
                case 1 -> gestionarEstudiantes();
                case 2 -> gestionarProfesores();
                case 3 -> menuPilaView.iniciar();
            }
        } while (opcion != 0);
    }

    private int mostrarMenuInicial() {
        String menu = " BIENVENIDO :D! \n Elija una opcion segun su necesidad \n "
                + "1. gestionar estudiantes \n 2. gestionar profesores \n 3. gestionar calificaciones \n 0. salir";
        return typeValidator.leerIntEnRango(0, 3, menu);
    }

    private void gestionarEstudiantes() {
        int opcion;
        do {
            opcion = typeValidator.leerIntEnRango(0, 5, generarMenuEstudiante());
            switch (opcion) {
                case 1 -> crearEstudiante();
                case 2 -> registrarLlegadaEstudiante();
                case 3 -> atenderSiguienteEstudiante();
                case 4 -> verSiguienteEstudiante();
                case 5 -> verCantidadEnEspera();
            }
        } while (opcion != 0);
    }

    private void gestionarProfesores() {
        int opcion;
        do {
            opcion = typeValidator.leerIntEnRango(0, 6, generarMenuProfesor());
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
        String nombre = typeValidator.leerString("Ingrese el nombre del estudiante:");
        String correo = typeValidator.leerCorreo("Ingrese el correo del estudiante:");
        String identificacion = typeValidator.leerString("Ingrese la cedula del estudiante:");
        String codigo = typeValidator.leerString("Ingrese el codigo del estudiante:");
        int semestreActual = typeValidator.leerIntEnRango(1, 12, "Ingrese el semestre actual del estudiante (1-12):");

        estudianteService.crearEstudiante(nombre, correo, identificacion, codigo, semestreActual);
        typeValidator.mensaje("Estudiante creado con exito");
    }

    private void registrarLlegadaEstudiante() {
        String cedula = typeValidator.leerString("Ingrese la cedula del estudiante que llega:");
        if (!estudianteService.existeEstudiante(cedula)) {
            typeValidator.mensaje("No existe un estudiante con esa cedula");
            return;
        }
        estudianteService.registrarLlegada(cedula);
        typeValidator.mensaje("Estudiante agregado a la fila de atencion");
    }

    private void atenderSiguienteEstudiante() {
        if (estudianteService.sinClientesEnEspera()) {
            typeValidator.mensaje("No hay estudiantes en la fila");
            return;
        }
        typeValidator.mensaje("Estudiante atendido:\n" + estudianteService.atenderSiguiente().datosResumen());
    }

    private void verSiguienteEstudiante() {
        if (estudianteService.sinClientesEnEspera()) {
            typeValidator.mensaje("No hay estudiantes en la fila");
            return;
        }
        typeValidator.mensaje("Siguiente en la fila:\n" + estudianteService.consultarSiguiente().datosResumen());
    }

    private void verCantidadEnEspera() {
        typeValidator.mensaje("Estudiantes en espera: " + estudianteService.contarEnEspera());
    }

    private void crearProfesor() {
        String nombre = typeValidator.leerString("Ingrese el nombre del profesor:");
        String correo = typeValidator.leerCorreo("Ingrese el correo del profesor:");
        String identificacion = typeValidator.leerString("Ingrese la cedula del profesor:");
        String codigo = typeValidator.leerString("Ingrese el codigo del profesor:");
        String departamento = typeValidator.leerString("Ingrese el departamento del profesor:");

        profesorService.crearProfesor(nombre, correo, identificacion, codigo, departamento);
        typeValidator.mensaje("Profesor creado con exito");
    }

    private void buscarProfesorPorIndice() {
        if (profesorService.estaVacia()) {
            typeValidator.mensaje("No hay profesores registrados");
            return;
        }
        int indice = typeValidator.leerIntEnRango(0, profesorService.getTamanio() - 1, "Ingrese el indice del profesor:");
        typeValidator.mensaje(profesorService.buscarPorIndice(indice).datosResumen());
    }

    private void buscarProfesorPorCedula() {
        String cedula = typeValidator.leerString("Ingrese la cedula del profesor a buscar:");
        if (!profesorService.existeProfesor(cedula)) {
            typeValidator.mensaje("No se encontro un profesor con esa cedula");
            return;
        }
        typeValidator.mensaje(profesorService.buscarPorCedula(cedula).datosResumen());
    }

    private void actualizarProfesorPorCedula() {
        String cedula = typeValidator.leerString("Ingrese la cedula del profesor a actualizar:");
        if (!profesorService.existeProfesor(cedula)) {
            typeValidator.mensaje("No se encontro un profesor con esa cedula");
            return;
        }
        String nombre = typeValidator.leerString("Ingrese el nuevo nombre:");
        String correo = typeValidator.leerCorreo("Ingrese el nuevo correo:");
        String codigo = typeValidator.leerString("Ingrese el nuevo codigo:");
        String departamento = typeValidator.leerString("Ingrese el nuevo departamento:");

        profesorService.actualizarProfesor(profesorService.buscarPorCedula(cedula), nombre, correo, codigo, departamento);
        typeValidator.mensaje("Profesor actualizado con exito");
    }

    private void eliminarProfesorPorCedula() {
        String cedula = typeValidator.leerString("Ingrese la cedula del profesor a eliminar:");
        if (!profesorService.existeProfesor(cedula)) {
            typeValidator.mensaje("No se encontro un profesor con esa cedula");
            return;
        }
        profesorService.eliminarProfesor(profesorService.buscarPorCedula(cedula));
        typeValidator.mensaje("Profesor eliminado con exito");
    }

    private void listarProfesores() {
        typeValidator.mensaje(profesorService.listarProfesores());
    }

    private String generarMenuEstudiante() {
        return "1. crear estudiante \n"
                + " 2. registrar llegada a la fila (enqueue) \n"
                + " 3. atender siguiente estudiante (dequeue) \n"
                + " 4. ver siguiente en la fila (front) \n"
                + " 5. ver cantidad de estudiantes en espera \n"
                + " 0. en caso de que quiera devolverse";
    }

    private String generarMenuProfesor() {
        return "1. crear profesor \n"
                + " 2. buscar profesor por indice \n"
                + " 3. buscar profesor por cedula \n"
                + " 4. actualizar profesor por cedula \n"
                + " 5. eliminar profesor por cedula \n"
                + " 6. mostrar todos los profesores \n"
                + " 0. en caso de que se quiera devolver";
    }
}