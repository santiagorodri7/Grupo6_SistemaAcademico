package view;

import model.domain.Estudiante;
import service.EstudianteService;
import utils.InputValidator;

public class MenuColaView {
    private final EstudianteService estudianteService;
    private final InputValidator inputValidator = new InputValidator();

    public MenuColaView(EstudianteService estudianteService){
        this.estudianteService = estudianteService;
    }

    public void iniciar(){
        int opcion;
        do {
            mostrarOpciones();
            opcion = inputValidator.leerEnEnteroEnRango(0, 6, "Elija una opción: ");
            switch (opcion) {
                case 1 -> registrarLlegada();
                case 2 -> atender();
                case 3 -> consultarSiguiente();
                case 4 -> verSiHayEnEspera();
                case 5 -> contar();
                case 6 -> listar();
                case 0 -> System.out.println("Saliendo del menú de colas...");
                default -> System.out.println("Opción inválida");
            }            
        } while (opcion != 0);
    }
    private void mostrarOpciones(){
        System.out.println("1. Registrar llegada (enqueue)");
        System.out.println("2. Atender al siguiente (dequeue)");
        System.out.println("3. Ver quién sigue (front)");
        System.out.println("4. ¿Hay estudiantes en espera? (isEmpty)");
        System.out.println("5. Cantidad en espera (size)");
        System.out.println("6. Listar la fila (Parte 7)");
        System.out.println("0. Salir");
    }

    private void registrarLlegada(){
        String identificacion = inputValidator.leerString("Identificación del estudiante: ");
        estudianteService.registrarLlegada(identificacion);
        inputValidator.mensaje("Llegada registrada");
    }
    private void atender() {
        if(estudianteService.sinClientesEnEspera()){
            inputValidator.mensaje("No hay estudiantes en espera");
        } else {
            inputValidator.mensaje("Estudiante atendido: "+textoEstudiante(estudianteService.atenderSiguiente()));
        }
    }
 
    private void consultarSiguiente() {
        if(estudianteService.sinClientesEnEspera()){
            inputValidator.mensaje("No hay estudiantes en espera");
            return;
        }
        inputValidator.mensaje("Sigue: "+textoEstudiante(estudianteService.consultarSiguiente()));
    }
 
    private void verSiHayEnEspera() {
        if(estudianteService.sinClientesEnEspera()){
            inputValidator.mensaje("No hay estudiantes en espera");
        } else {
            inputValidator.mensaje("Hay estudiantes en espera");
        }
    }
 
    private void contar() {
        inputValidator.mensaje("Estudiantes en espera: "+estudianteService.contarEnEspera());;
    }
 
    private void listar() {
        // TODO 6: se completa en la Parte 7, cuando exista listarEnEspera en el Service
    }

    private String textoEstudiante(Estudiante e) {
    return e.getNombre() + " (" + e.getIdentificacion() + ")";
}
}
