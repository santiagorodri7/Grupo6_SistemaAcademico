package domain.model;

import domain.model.structures.List;

public class Matricula {

    private List<Calificacion> calificaciones;
    private Estudiante estudiante;

    public Matricula( Estudiante estudiante) {
        this.calificaciones = new List<>();
        this.estudiante = estudiante;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    
}