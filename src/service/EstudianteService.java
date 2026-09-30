package service;

import domain.model.Estudiante;
import domain.model.structures.List;

public class EstudianteService {

    private List<Estudiante> estudiantes;

    public EstudianteService() {
        this.estudiantes = new List<>();
    }

    public Estudiante crearEstudiante(String nombre, String correo, String identificacion, String codigo, int semestreActual) {
        Estudiante estudiante = new Estudiante(nombre, correo, identificacion, codigo, semestreActual);
        estudiantes.insertarFinal(estudiante);
        return estudiante;
    }

    public boolean estaVacia() {
        return estudiantes.estaVacia();
    }

    public int getTamanio() {
        return estudiantes.getTamanio();
    }

    public Estudiante buscarPorIndice(int indice) {
        return estudiantes.buscarPorInidice(indice);
    }

    public Estudiante buscarPorCedula(String cedula) {
        for (int i = 0; i < estudiantes.getTamanio(); i++) {
            Estudiante actual = estudiantes.buscarPorInidice(i);
            if (actual.getIdentificacion().equals(cedula)) {
                return actual;
            }
        }
        return null;
    }

    public void actualizarEstudiante(Estudiante estudiante, String nombre, String correo, String codigo, int semestreActual) {
        estudiante.setNombre(nombre);
        estudiante.setCorreo(correo);
        estudiante.setCodigo(codigo);
        estudiante.setSemestreActual(semestreActual);
    }

    public boolean eliminarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            return false;
        }
        estudiantes.eliminarPorValor(estudiante);
        return true;
    }
}