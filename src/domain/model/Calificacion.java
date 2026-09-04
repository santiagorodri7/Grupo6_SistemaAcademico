public class Calificacion {

    private double notaParcial1;
    private double notaParcial2;
    private double notaFinal;
    private String observaciones;

    private Materia materia;
    public Calificacion() {
    }

    public Calificacion(double notaParcial1, double notaParcial2, double notaFinal, String observaciones, Materia materia) {
        this.notaParcial1 = notaParcial1;
        this.notaParcial2 = notaParcial2;
        this.notaFinal = notaFinal;
        this.observaciones = observaciones;
        this.materia = materia;
    }

    public double getNotaParcial1() {
        return notaParcial1;
    }

    public void setNotaParcial1(double notaParcial1) {
        this.notaParcial1 = notaParcial1;
    }

    public double getNotaParcial2() {
        return notaParcial2;
    }

    public void setNotaParcial2(double notaParcial2) {
        this.notaParcial2 = notaParcial2;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}