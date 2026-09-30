package service;

import domain.model.Profesor;
import domain.model.structures.List;
import utils.TypeValidator;

public class ProfesorService {

    private List<Profesor> profesores;
    private TypeValidator typeValidator;

    public ProfesorService() {
        this.profesores = new List<>();
        this.typeValidator = new TypeValidator();
    }

    public void crearProfesor() {
        String nombre = typeValidator.leerString("Ingrese el nombre del profesor:");
        String correo = typeValidator.leerString("Ingrese el correo del profesor:");
        String identificacion = typeValidator.leerString("Ingrese la cedula del profesor:");
        String codigo = typeValidator.leerString("Ingrese el codigo del profesor:");
        String departamento = typeValidator.leerString("Ingrese el departamento del profesor:");

        try {
            Profesor profesor = new Profesor(nombre, correo, identificacion, codigo, departamento);
            profesores.insertarFinal(profesor);
            typeValidator.Mensaje("Profesor creado con exito");
        } catch (IllegalArgumentException ex) {
            typeValidator.Mensaje("No se pudo crear el profesor, correo invalido");
        }
    }

    public void buscarPorIndice() {
        if (profesores.estaVacia()) {
            typeValidator.Mensaje("No hay profesores registrados");
            return;
        }
        int indice = typeValidator.leerIntEnRango(0, profesores.getTamanio() - 1, "Ingrese el indice del profesor:");
        Profesor profesor = profesores.buscarPorInidice(indice);
        typeValidator.Mensaje(profesor.datosResumen());
    }

    private Profesor buscarProfesorPorCedula(String cedula) {
        for (int i = 0; i < profesores.getTamanio(); i++) {
            Profesor actual = profesores.buscarPorInidice(i);
            if (actual.getIdentificacion().equals(cedula)) {
                return actual;
            }
        }
        return null;
    }

    public void buscarPorCedula() {
        String cedula = typeValidator.leerString("Ingrese la cedula del profesor a buscar:");
        Profesor profesor = buscarProfesorPorCedula(cedula);
        if (profesor == null) {
            typeValidator.Mensaje("No se encontro un profesor con esa cedula");
        } else {
            typeValidator.Mensaje(profesor.datosResumen());
        }
    }

    public void actualizarPorCedula() {
        String cedula = typeValidator.leerString("Ingrese la cedula del profesor a actualizar:");
        Profesor profesor = buscarProfesorPorCedula(cedula);
        if (profesor == null) {
            typeValidator.Mensaje("No se encontro un profesor con esa cedula");
            return;
        }
        String nombre = typeValidator.leerString("Ingrese el nuevo nombre:");
        String correo = typeValidator.leerString("Ingrese el nuevo correo:");
        String codigo = typeValidator.leerString("Ingrese el nuevo codigo:");
        String departamento = typeValidator.leerString("Ingrese el nuevo departamento:");

        profesor.setNombre(nombre);
        profesor.setCorreo(correo);
        profesor.setCodigo(codigo);
        profesor.setDepartamento(departamento);
        typeValidator.Mensaje("Profesor actualizado con exito");
    }

    public void eliminarPorCedula() {
        String cedula = typeValidator.leerString("Ingrese la cedula del profesor a eliminar:");
        Profesor profesor = buscarProfesorPorCedula(cedula);
        if (profesor == null) {
            typeValidator.Mensaje("No se encontro un profesor con esa cedula");
            return;
        }
        profesores.eliminarPorValor(profesor);
        typeValidator.Mensaje("Profesor eliminado con exito");
    }

    public void mostrarTodos() {
        if (profesores.estaVacia()) {
            typeValidator.Mensaje("No hay profesores registrados");
            return;
        }
        String listado = "";
        for (int i = 0; i < profesores.getTamanio(); i++) {
            Profesor actual = profesores.buscarPorInidice(i);
            listado += (i + ". " + actual.datosResumen() + "\n");
        }
        typeValidator.Mensaje(listado);
    }
}