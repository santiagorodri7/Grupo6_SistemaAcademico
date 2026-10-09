package service;

import model.domain.Profesor;
import model.domain.structures.ListaSimple;

public class ProfesorService {

    private ListaSimple<Profesor> profesores;

    public ProfesorService() {
        this.profesores = new ListaSimple<>();
    }

    public Profesor crearProfesor(String nombre, String correo, String identificacion, String codigo, String departamento) {
        Profesor profesor = new Profesor(nombre, correo, identificacion, codigo, departamento);
        profesores.insertarFinal(profesor);
        return profesor;
    }

    public boolean estaVacia() {
        return profesores.estaVacia();
    }

    public int getTamanio() {
        return profesores.getTamanio();
    }

    public Profesor buscarPorIndice(int indice) {
        return profesores.buscarPorInidice(indice);
    }

    public Profesor buscarPorCedula(String cedula) {
        for (int i = 0; i < profesores.getTamanio(); i++) {
            Profesor actual = profesores.buscarPorInidice(i);
            if (actual.getIdentificacion().equals(cedula)) {
                return actual;
            }
        }
        return null;
    }

    public void actualizarProfesor(Profesor profesor, String nombre, String correo, String codigo, String departamento) {
        profesor.setNombre(nombre);
        profesor.setCorreo(correo);
        profesor.setCodigo(codigo);
        profesor.setDepartamento(departamento);
    }

    public boolean eliminarProfesor(Profesor profesor) {
        if (profesor == null) {
            return false;
        }
        profesores.eliminarPorValor(profesor);
        return true;
    }
}