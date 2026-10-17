package service;

import java.util.NoSuchElementException;

import model.domain.Estudiante;
import model.domain.ProcesoMatricula;
import model.domain.structures.Cola;
import model.domain.structures.ListaSimple;

public class EstudianteService {

    private ListaSimple<Estudiante> estudiantes;
    private final ProcesoMatricula procesoMatricula = new ProcesoMatricula("null");

    public void registrarLlegada(String identificacion){
        Estudiante estudiante = buscarEstudianteOFallar(identificacion);
        procesoMatricula.getFila().enqueue(estudiante);
    }
    public Estudiante buscarEstudianteOFallar(String identificacion){
        for(int i=0; i < estudiantes.getTamanio();i++){
            Estudiante estudiante = estudiantes.buscarPorInidice(i);
            if(estudiante.getIdentificacion().equals(identificacion)){
                return estudiante;
            }
        }
        throw new NoSuchElementException("No existe un estudiante con esa identificación");
    }

    public Estudiante atenderSiguiente(){
        Estudiante estudianteAtendido = procesoMatricula.getFila().dequeue();
        return estudianteAtendido;
    }

    public Estudiante consultarSiguiente(){
        return procesoMatricula.getFila().front();
    }

    public boolean sinClientesEnEspera(){
        return procesoMatricula.getFila().isEmpty();
    }

    public int contarEnEspera(){
        return procesoMatricula.getFila().size();
    }


    public EstudianteService() {
        this.estudiantes = new ListaSimple<>();
    }

    public Estudiante crearEstudiante(String nombre, String correo, String identificacion, String codigo, int semestreActual) {
        Estudiante estudiante = new Estudiante(nombre, correo, identificacion, codigo, semestreActual);
        estudiantes.insertarFinal(estudiante);
        return estudiante;
    }

    public boolean estaVacia() {
        return estudiantes.estaVacia();
    }

    public int getTamanio() {
        return estudiantes.getTamanio();
    }
    public void actualizarEstudiante(Estudiante estudiante, String nombre, String correo, String codigo, int semestreActual) {
        estudiante.setNombre(nombre);
        estudiante.setCorreo(correo);
        estudiante.setCodigo(codigo);
        estudiante.setSemestreActual(semestreActual);
    }
    public Estudiante buscarPorIndice(int indice) {
        return estudiantes.buscarPorInidice(indice);
    }

    public Estudiante buscarPorCedula(String cedula) {
        for (int i = 0; i < estudiantes.getTamanio(); i++) {
            Estudiante estudiante = estudiantes.buscarPorInidice(i);
            if (estudiante.getIdentificacion().equals(cedula)) {
                return estudiante;
            }
        }
        return null;
    }

    public boolean eliminarEstudiante(Estudiante estudiante) {
        return estudiantes.eliminarPorValor(estudiante);
    }

    public String listarEnEspera() {
        Cola<Estudiante> fila = procesoMatricula.getFila();

        if (fila.isEmpty()) {
            return "No hay estudiantes en espera";
        }
        StringBuilder texto = new StringBuilder();
        int total = fila.size();
        for (int i = 1; i <= total; i++) {
            Estudiante e = fila.dequeue();
            texto.append(i).append(". ")
                .append(e.getNombre())
                .append(" (").append(e.getIdentificacion()).append(")\n");
            fila.enqueue(e);
        }
        return texto.toString();
    }
}