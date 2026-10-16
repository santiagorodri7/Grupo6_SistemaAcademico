package service;

import model.domain.Estudiante;
import model.domain.structures.Cola;

public class EstudianteService {

    private Cola<Estudiante> estudiantes;

    public EstudianteService() {
        this.estudiantes = new Cola<>();
    }

    public Estudiante crearEstudiante(String nombre, String correo, String identificacion, String codigo, int semestreActual) {
        Estudiante estudiante = new Estudiante(nombre, correo, identificacion, codigo, semestreActual);
        estudiantes.enqueue(estudiante);
        return estudiante;
    }

    public boolean estaVacia() {
        return estudiantes.isEmpty();
    }

    public int getTamanio() {
        return estudiantes.size();
    }
    public void actualizarEstudiante(Estudiante estudiante, String nombre, String correo, String codigo, int semestreActual) {
        estudiante.setNombre(nombre);
        estudiante.setCorreo(correo);
        estudiante.setCodigo(codigo);
        estudiante.setSemestreActual(semestreActual);
    }

    public Estudiante eliminarPrimerEstudiante() {
        return estudiantes.dequeue();
    }
}