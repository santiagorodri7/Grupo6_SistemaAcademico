package service;

import model.domain.Calificacion;
import model.domain.Materia;
import model.domain.Matricula;
import model.domain.structures.Pila;

public class CalificacionService {

    private final MatriculaService matriculaService;

    public CalificacionService(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    public void registrarCalificacion(String cedula, String nombreMatricula, float notaParcial1,
                                      float notaParcial2, float notaFinal, String observaciones,
                                      String nombreMateria, String codigoMateria, int creditosMateria) {
        Matricula matricula = matriculaService.buscarMatriculaOFallar(cedula, nombreMatricula);
        Materia materia = new Materia(nombreMateria, codigoMateria, creditosMateria);
        Calificacion calificacion = new Calificacion(notaParcial1, notaParcial2, notaFinal, observaciones, materia);
        matricula.getCalificaciones().push(calificacion);
    }

    public Calificacion deshacerUltimaCalificacion(String cedula, String nombreMatricula) {
        Matricula matricula = matriculaService.buscarMatriculaOFallar(cedula, nombreMatricula);
        return matricula.getCalificaciones().pop();
    }

    public Calificacion consultarUltimaCalificacion(String cedula, String nombreMatricula) {
        Matricula matricula = matriculaService.buscarMatriculaOFallar(cedula, nombreMatricula);
        return matricula.getCalificaciones().peek();
    }

    public boolean sinCalificacionesPorDeshacer(String cedula, String nombreMatricula) {
        Matricula matricula = matriculaService.buscarMatriculaOFallar(cedula, nombreMatricula);
        return matricula.getCalificaciones().isEmpty();
    }

    public int contarCalificaciones(String cedula, String nombreMatricula) {
        Matricula matricula = matriculaService.buscarMatriculaOFallar(cedula, nombreMatricula);
        return matricula.getCalificaciones().size();
    }

    public String listarCalificaciones(String cedula, String nombreMatricula) {
        Matricula matricula = matriculaService.buscarMatriculaOFallar(cedula, nombreMatricula);
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