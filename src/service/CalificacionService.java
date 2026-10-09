package service;
import model.domain.Calificacion;
import model.domain.Materia;
import model.domain.structures.Pila;


public class CalificacionService {


    private Pila<Calificacion> calificaciones;

    public CalificacionService() {
        this.calificaciones = new Pila<>();
    }
    public Calificacion crearCalificacion(double notaParcial1, double notaParcial2, double notaFinal, String observaciones, Materia materia) {
        Calificacion profesor = new Calificacion( notaParcial1, notaParcial2, notaFinal, observaciones, materia);
        calificaciones.push(profesor);
        return profesor;
    }
    public boolean estaVacia() {
        return calificaciones.isEmpty();
    }
    public int getTamanio() {
        return calificaciones.getTamanio();
    }
    public Calificacion borrarUltimaCalificacion(){
        return calificaciones.pop();
    }
    public Calificacion seleccionarUltimaCalificacion(){
        return calificaciones.peek();
    }
}

