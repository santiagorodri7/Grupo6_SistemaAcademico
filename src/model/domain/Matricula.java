package model.domain;

import model.domain.structures.ListaSimple;

public class Matricula {

    private ListaSimple<Calificacion> calificaciones;
    private Estudiante estudiante;
    private String periodoAcademico;
    private String nombreMatricula;

    public Matricula( Estudiante estudiante, String periodoAcademico, String nombreMatricula) {
        this.calificaciones = new ListaSimple<>();
        this.estudiante = estudiante;
        this.periodoAcademico = periodoAcademico;
        this.nombreMatricula = nombreMatricula;
    }

    public ListaSimple<Calificacion> getCalificaciones() {
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