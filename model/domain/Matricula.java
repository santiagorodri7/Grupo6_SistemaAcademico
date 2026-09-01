import java.util.ArrayList;
import java.util.List;

public class Matricula {
    private String nombre;
    private String Cmatricula;
    private String fecha;
    private String estado;
    private double Valor;

    


    private Estudiante estudiante;

    private List<Calificacion> calificaciones;

    public Matricula() {
        this.calificaciones = new ArrayList<>();
    }

    public Matricula(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.calificaciones = new ArrayList<>();
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    // Por ser composicion, la Calificacion no deberia existir
    // sin la Matricula, por eso se agrega asi en vez de solo con el setter
    public void agregarCalificacion(Calificacion calificacion) {
        this.calificaciones.add(calificacion);
    }
}