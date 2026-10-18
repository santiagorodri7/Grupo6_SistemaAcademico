package service;

import java.util.NoSuchElementException;

import model.domain.Calificacion;
import model.domain.Estudiante;
import model.domain.Materia;
import model.domain.Matricula;
import model.domain.structures.ListaSimple;
import model.domain.structures.Pila;

public class CalificacionService {

    private final EstudianteService estudianteService;
    private final ListaSimple<Matricula> matriculas;

    public CalificacionService(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
        this.matriculas = new ListaSimple<>();
    }

    public void crearMatricula(String cedula, String periodoAcademico, String nombreMatricula) {
        Estudiante estudiante = estudianteService.buscarEstudianteOFallar(cedula);
        if (existeMatricula(cedula, nombreMatricula))
            throw new IllegalArgumentException("Ya existe una matricula con ese nombre");
        matriculas.insertarFinal(new Matricula(estudiante, periodoAcademico, nombreMatricula));
    }

    public boolean existeMatricula(String cedula, String nombreMatricula) {
        for (int i = 0; i < matriculas.getTamanio(); i++) {
            Matricula matricula = matriculas.buscarPorInidice(i);
            if (matricula.getEstudiante().getIdentificacion().equals(cedula)
                    && matricula.getNombreMatricula().equalsIgnoreCase(nombreMatricula)) {
                return true;
            }
        }
        return false;
    }

    private Matricula buscarMatriculaOFallar(String cedula, String nombreMatricula) {
        for (int i = 0; i < matriculas.getTamanio(); i++) {
            Matricula matricula = matriculas.buscarPorInidice(i);
            if (matricula.getEstudiante().getIdentificacion().equals(cedula)
                    && matricula.getNombreMatricula().equalsIgnoreCase(nombreMatricula)) {
                return matricula;
            }
        }
        throw new NoSuchElementException("No existe una matricula con ese nombre para ese estudiante");
    }

    public void registrarCalificacion(String cedula, String nombreMatricula, float notaParcial1,
                                      float notaParcial2, float notaFinal, String observaciones, Materia materia) {
        Matricula matricula = buscarMatriculaOFallar(cedula, nombreMatricula);
        Calificacion calificacion = new Calificacion(notaParcial1, notaParcial2, notaFinal, observaciones, materia);
        matricula.getCalificaciones().push(calificacion);
    }

    public Calificacion deshacerUltimaCalificacion(String cedula, String nombreMatricula) {
        Matricula matricula = buscarMatriculaOFallar(cedula, nombreMatricula);
        return matricula.getCalificaciones().pop();
    }

    public Calificacion consultarUltimaCalificacion(String cedula, String nombreMatricula) {
        Matricula matricula = buscarMatriculaOFallar(cedula, nombreMatricula);
        return matricula.getCalificaciones().peek();
    }

    public boolean sinCalificacionesPorDeshacer(String cedula, String nombreMatricula) {
        Matricula matricula = buscarMatriculaOFallar(cedula, nombreMatricula);
        return matricula.getCalificaciones().isEmpty();
    }

    public int contarCalificaciones(String cedula, String nombreMatricula) {
        Matricula matricula = buscarMatriculaOFallar(cedula, nombreMatricula);
        return matricula.getCalificaciones().size();
    }

    public String listarCalificaciones(String cedula, String nombreMatricula) {
        Matricula matricula = buscarMatriculaOFallar(cedula, nombreMatricula);
        Pila<Calificacion> original = matricula.getCalificaciones();

        if (original.isEmpty())
            return "No hay calificaciones por deshacer";

        Pila<Calificacion> auxiliar = new Pila<>();
        StringBuilder resultado = new StringBuilder();
        int numero = 1;

        while (!original.isEmpty()) {
            Calificacion calificacion = original.pop();
            resultado.append(numero++).append(". ").append(calificacion).append("\n\n");
            auxiliar.push(calificacion);
        }

        while (!auxiliar.isEmpty()) {
            original.push(auxiliar.pop());
        }

        return resultado.toString();
    }
}