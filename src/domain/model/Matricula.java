import list.List;

public class Matricula {

    private List<Calificacion> calificaciones;
    private Estudiante estudiante;

    public Matricula(List<Calificacion> calificaciones, Estudiante estudiante) {
        this.calificaciones = calificaciones;
        this.estudiante = estudiante;
    }
}