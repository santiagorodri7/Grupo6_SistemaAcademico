package service;

import java.util.NoSuchElementException;

import model.domain.Estudiante;
import model.domain.Matricula;
import model.domain.structures.ListaSimple;

public class MatriculaService {

    private final EstudianteService estudianteService;
    private final ListaSimple<Matricula> matriculas;

    public MatriculaService(EstudianteService estudianteService) {
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

    public Matricula buscarMatriculaOFallar(String cedula, String nombreMatricula) {
        for (int i = 0; i < matriculas.getTamanio(); i++) {
            Matricula matricula = matriculas.buscarPorInidice(i);
            if (matricula.getEstudiante().getIdentificacion().equals(cedula)
                    && matricula.getNombreMatricula().equalsIgnoreCase(nombreMatricula)) {
                return matricula;
            }
        }
        throw new NoSuchElementException("No existe una matricula con ese nombre para ese estudiante");
    }
}