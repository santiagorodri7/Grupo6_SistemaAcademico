package model.domain;

public class Calificacion {

    private double notaParcial1;
    private double notaParcial2;
    private double notaFinal;
    private String observaciones;
    private Materia materia;

    public Calificacion(double notaParcial1, double notaParcial2, double notaFinal, String observaciones, Materia materia) {

        if ((notaParcial1 < 0 || notaParcial1 > 5) || (notaParcial2 < 0 || notaParcial2 > 5) || (notaFinal < 0 || notaFinal > 5))
            throw new IllegalArgumentException("Notas no validas, el rango de nota disponible esta entre 0 y 5");
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
        if (notaParcial1 < 0 || notaParcial1 > 5)
            throw new IllegalArgumentException("Nota no valida, el rango de nota disponible esta entre 0 y 5");
        this.notaParcial1 = notaParcial1;
    }

    public double getNotaParcial2() {
        return notaParcial2;
    }

    public void setNotaParcial2(double notaParcial2) {
        if (notaParcial2 < 0 || notaParcial2 > 5)
            throw new IllegalArgumentException("Nota no valida, el rango de nota disponible esta entre 0 y 5");
        this.notaParcial2 = notaParcial2;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        if (notaFinal < 0 || notaFinal > 5)
            throw new IllegalArgumentException("Nota no valida, el rango de nota disponible esta entre 0 y 5");
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

    @Override
    public String toString() {
        return "Calificaci\u00f3n:\n" +
                "- Parcial 1: " + notaParcial1 + "\n" +
                "- Parcial 2: " + notaParcial2 + "\n" +
                "- Nota Final: " + notaFinal + "\n" +
                "- Observaciones: " + observaciones + "\n" +
                "- Materia: " + (materia != null ? materia.getNombre() : "N/A");
    }
}