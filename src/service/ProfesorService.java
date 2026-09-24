package service;

import domain.model.Profesor;
import domain.model.structures.List;
import utils.TypeValidator;

public class ProfesorService {

    private List<Profesor> profesores;
    private TypeValidator tv;

    public ProfesorService() {
        this.profesores = new List<>();
        this.tv = new TypeValidator();
    }

    public void crearProfesor() {
        String nombre = tv.leerString("Ingrese el nombre del profesor:");
        String correo = tv.leerString("Ingrese el correo del profesor:");
        String identificacion = tv.leerString("Ingrese la cedula del profesor:");
        String codigo = tv.leerString("Ingrese el codigo del profesor:");
        String departamento = tv.leerString("Ingrese el departamento del profesor:");

        try {
            Profesor profesor = new Profesor(nombre, correo, identificacion, codigo, departamento);
            profesores.insertarFinal(profesor);
            tv.Mensaje("Profesor creado con exito");
        } catch (IllegalArgumentException ex) {
            tv.Mensaje("No se pudo crear el profesor, correo invalido");
        }
    }

    public void buscarPorIndice() {
        if (profesores.estaVacia()) {
            tv.Mensaje("No hay profesores registrados");
            return;
        }
        int indice = tv.leerIntEnRango(0, profesores.getTamanio() - 1, "Ingrese el indice del profesor:");
        Profesor profesor = profesores.buscarPorInidice(indice);
        tv.Mensaje(profesor.datosResumen());
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
        String cedula = tv.leerString("Ingrese la cedula del profesor a buscar:");
        Profesor profesor = buscarProfesorPorCedula(cedula);
        if (profesor == null) {
            tv.Mensaje("No se encontro un profesor con esa cedula");
        } else {
            tv.Mensaje(profesor.datosResumen());
        }
    }

    public void actualizarPorCedula() {
        String cedula = tv.leerString("Ingrese la cedula del profesor a actualizar:");
        Profesor profesor = buscarProfesorPorCedula(cedula);
        if (profesor == null) {
            tv.Mensaje("No se encontro un profesor con esa cedula");
            return;
        }
        String nombre = tv.leerString("Ingrese el nuevo nombre:");
        String correo = tv.leerString("Ingrese el nuevo correo:");
        String codigo = tv.leerString("Ingrese el nuevo codigo:");
        String departamento = tv.leerString("Ingrese el nuevo departamento:");

        profesor.setNombre(nombre);
        profesor.setCorreo(correo);
        profesor.setCodigo(codigo);
        profesor.setDepartamento(departamento);
        tv.Mensaje("Profesor actualizado con exito");
    }

    public void eliminarPorCedula() {
        String cedula = tv.leerString("Ingrese la cedula del profesor a eliminar:");
        Profesor profesor = buscarProfesorPorCedula(cedula);
        if (profesor == null) {
            tv.Mensaje("No se encontro un profesor con esa cedula");
            return;
        }
        profesores.eliminarPorValor(profesor);
        tv.Mensaje("Profesor eliminado con exito");
    }

    public void mostrarTodos() {
        if (profesores.estaVacia()) {
            tv.Mensaje("No hay profesores registrados");
            return;
        }
        String listado = "";
        for (int i = 0; i < profesores.getTamanio(); i++) {
            Profesor actual = profesores.buscarPorInidice(i);
            listado += (i + ". " + actual.datosResumen() + "\n");
        }
        tv.Mensaje(listado);
    }
}