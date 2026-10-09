package model.domain;

import model.domain.structures.Pila;

public class Matricula {

    private Pila<Calificacion> calificaciones;
    private Estudiante estudiante;
    private String periodoAcademico;
    private String nombreMatricula;

    public Matricula( Estudiante estudiante, String periodoAcademico, String nombreMatricula) {
        this.calificaciones = new Pila<>();
        this.estudiante = estudiante;
        this.periodoAcademico = periodoAcademico;
        this.nombreMatricula = nombreMatricula;
    }

    public Pila<Calificacion> getCalificaciones() {
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