package service;

import model.domain.Profesor;
import model.domain.structures.Pila;

public class ProfesorService {

    private Pila<Profesor> profesores;

    public ProfesorService() {
        this.profesores = new Pila<>();
    }
    public Profesor crearProfesor(String nombre, String correo, String identificacion, String codigo, String departamento) {
        Profesor profesor = new Profesor(nombre, correo, identificacion, codigo, departamento);
        profesores.push(profesor);
        return profesor;
    }
    public boolean estaVacia() {
        return profesores.isEmpty();
    }
    public int getTamanio() {
        return profesores.getTamanio();
    }
    public Profesor borrarUltimoProfesor(){
        return profesores.pop();
    }
    public Profesor seleccionarUltimoProfesor(){
        return profesores.peek();
    }
}