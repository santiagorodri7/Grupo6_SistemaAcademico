package model.domain;

public class Calificacion {

    private float notaParcial1;
    private float notaParcial2;
    private float notaFinal;
    private String observaciones;
    private Materia materia;

    public Calificacion(float notaParcial1, float notaParcial2, float notaFinal, String observaciones, Materia materia) {

        if ((notaParcial1 < 0 || notaParcial1 > 5) || (notaParcial2 < 0 || notaParcial2 > 5) || (notaFinal < 0 || notaFinal > 5))
            throw new IllegalArgumentException("Notas no validas, el rango de nota disponible esta entre 0 y 5");
        this.notaParcial1 = notaParcial1;
        this.notaParcial2 = notaParcial2;
        this.notaFinal = notaFinal;
        this.observaciones = observaciones;
        this.materia = materia;
    }

    public float getNotaParcial1() {
        return notaParcial1;
    }

    public void setNotaParcial1(float notaParcial1) {
        if (notaParcial1 < 0 || notaParcial1 > 5)
            throw new IllegalArgumentException("Nota no valida, el rango de nota disponible esta entre 0 y 5");
        this.notaParcial1 = notaParcial1;
    }

    public float getNotaParcial2() {
        return notaParcial2;
    }

    public void setNotaParcial2(float notaParcial2) {
        if (notaParcial2 < 0 || notaParcial2 > 5)
            throw new IllegalArgumentException("Nota no valida, el rango de nota disponible esta entre 0 y 5");
        this.notaParcial2 = notaParcial2;
    }

    public float getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(float notaFinal) {
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


}