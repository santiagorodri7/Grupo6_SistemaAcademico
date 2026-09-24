package service;

import domain.model.Estudiante;
import domain.model.structures.List;
import utils.TypeValidator;

public class EstudianteService {

    private List<Estudiante> estudiantes;
    private TypeValidator tv;

    public EstudianteService() {
        this.estudiantes = new List<>();
        this.tv = new TypeValidator();
    }

    public void crearEstudiante() {
        String nombre = tv.leerString("Ingrese el nombre del estudiante:");
        String correo = tv.leerString("Ingrese el correo del estudiante:");
        String identificacion = tv.leerString("Ingrese la cedula del estudiante:");
        String codigo = tv.leerString("Ingrese el codigo del estudiante:");
        int semestreActual = tv.leerIntEnRango(1, 12, "Ingrese el semestre actual del estudiante (1-12):");

        try {
            Estudiante estudiante = new Estudiante(nombre, correo, identificacion, codigo, semestreActual);
            estudiantes.insertarFinal(estudiante);
            tv.Mensaje("Estudiante creado con exito");
        } catch (IllegalArgumentException ex) {
            tv.Mensaje("No se pudo crear el estudiante, correo invalido");
        }
    }

    public void buscarPorIndice() {
        if (estudiantes.estaVacia()) {
            tv.Mensaje("No hay estudiantes registrados");
            return;
        }
        int indice = tv.leerIntEnRango(0, estudiantes.getTamanio() - 1, "Ingrese el indice del estudiante:");
        Estudiante estudiante = estudiantes.buscarPorInidice(indice);
        tv.Mensaje(estudiante.datosResumen());
    }

    private Estudiante buscarEstudiantePorCedula(String cedula) {
        for (int i = 0; i < estudiantes.getTamanio(); i++) {
            Estudiante actual = estudiantes.buscarPorInidice(i);
            if (actual.getIdentificacion().equals(cedula)) {
                return actual;
            }
        }
        return null;
    }

    public void buscarPorCedula() {
        String cedula = tv.leerString("Ingrese la cedula del estudiante a buscar:");
        Estudiante estudiante = buscarEstudiantePorCedula(cedula);
        if (estudiante == null) {
            tv.Mensaje("No se encontro un estudiante con esa cedula");
        } else {
            tv.Mensaje(estudiante.datosResumen());
        }
    }

    public void actualizarPorCedula() {
        String cedula = tv.leerString("Ingrese la cedula del estudiante a actualizar:");
        Estudiante estudiante = buscarEstudiantePorCedula(cedula);
        if (estudiante == null) {
            tv.Mensaje("No se encontro un estudiante con esa cedula");
            return;
        }
        String nombre = tv.leerString("Ingrese el nuevo nombre:");
        String correo = tv.leerString("Ingrese el nuevo correo:");
        String codigo = tv.leerString("Ingrese el nuevo codigo:");
        int semestreActual = tv.leerIntEnRango(1, 12, "Ingrese el nuevo semestre actual (1-12):");

        estudiante.setNombre(nombre);
        estudiante.setCorreo(correo);
        estudiante.setCodigo(codigo);
        estudiante.setSemestreActual(semestreActual);
        tv.Mensaje("Estudiante actualizado con exito");
    }

    public void eliminarPorCedula() {
        String cedula = tv.leerString("Ingrese la cedula del estudiante a eliminar:");
        Estudiante estudiante = buscarEstudiantePorCedula(cedula);
        if (estudiante == null) {
            tv.Mensaje("No se encontro un estudiante con esa cedula");
            return;
        }
        estudiantes.eliminarPorValor(estudiante);
        tv.Mensaje("Estudiante eliminado con exito");
    }

    public void mostrarTodos() {
        if (estudiantes.estaVacia()) {
            tv.Mensaje("No hay estudiantes registrados");
            return;
        }
        String listado = "";
        for (int i = 0; i < estudiantes.getTamanio(); i++) {
            Estudiante actual = estudiantes.buscarPorInidice(i);
            listado += (i + ". " + actual.datosResumen() + "\n");
        }
        tv.Mensaje(listado);
    }
}