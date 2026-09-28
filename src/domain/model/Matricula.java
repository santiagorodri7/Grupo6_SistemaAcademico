package domain.model;

import domain.model.structures.List;

public class Matricula {

    private List<Calificacion> calificaciones;
    private Estudiante estudiante;
    private String periodoAcademico;
    private String nombreMatricula;

    public Matricula( Estudiante estudiante, String periodoAcademico, String nombreMatricula) {
        this.calificaciones = new List<>();
        this.estudiante = estudiante;
        this.periodoAcademico = periodoAcademico;
        this.nombreMatricula = nombreMatricula;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public String getPeriodoAcademico(){
        return this.periodoAcademico;
    }

    public String getNombreMatricula(){
        return this.nombreMatricula;
    }

    
}