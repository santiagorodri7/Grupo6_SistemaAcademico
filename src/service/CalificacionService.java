package service;
import model.domain.Calificacion;
import model.domain.Materia;
import model.domain.structures.Pila;


public class CalificacionService {


    private Pila<Calificacion> calificaciones;

    public CalificacionService() {
        this.calificaciones = new Pila<>();
    }
    public Calificacion registrarCalificacion(double notaParcial1, double notaParcial2, double notaFinal, String observaciones, Materia materia) {
        Calificacion calificacion = new Calificacion( notaParcial1, notaParcial2, notaFinal, observaciones, materia);
        calificaciones.push(calificacion);
        return calificacion;
    }
    public boolean estaVacia() {
        return calificaciones.isEmpty();
    }
    public int verTamanio() {
        return calificaciones.size();
    }
    public Calificacion deshacerUltimaCalificacion(){
        return calificaciones.pop();
    }
    public Calificacion consultarUltimaCalificacion(){
        return calificaciones.peek();
    }
    public String recorrerCalificaciones() {
        if (calificaciones.isEmpty()) {
            return "No hay calificaciones registradas.";
        }

        Pila<Calificacion> auxiliar = new Pila<>();
        StringBuilder resultado = new StringBuilder();

        // Paso 1: pasar todo a la auxiliar (el orden se invierte)
        while (!calificaciones.isEmpty()) {
            auxiliar.push(calificaciones.pop());
        }

        // Paso 2: acumular del más antiguo al más reciente y restaurar la original
        while (!auxiliar.isEmpty()) {
            Calificacion calificacion = auxiliar.pop();
            resultado.append(calificacion).append("\n\n");
            calificaciones.push(calificacion);
        }

        return resultado.toString();
    }
}
