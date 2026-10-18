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
            opcion = inputValidator.leerEnEnteroEnRango(0, 6, mostrarOpciones());
            switch (opcion) {
                case 1 -> registrarLlegada();
                case 2 -> atender();
                case 3 -> consultarSiguiente();
                case 4 -> verSiHayEnEspera();
                case 5 -> contar();
                case 6 -> listar();
                case 0 -> inputValidator.mensaje("Saliendo del menú de colas...");
                default -> inputValidator.mensaje("Opción inválida");
            }            
        } while (opcion != 0);
    }
    private String mostrarOpciones() {
        return "1. Registrar llegada (enqueue) \n"
            + "2. Atender al siguiente (dequeue) \n"
            + "3. Ver quién sigue (front) \n"
            + "4. ¿Hay estudiantes en espera? (isEmpty) \n"
            + "5. Cantidad en espera (size) \n"
            + "6. Listar la fila \n"
            + "0. Salir";
    }

    private void registrarLlegada(){
        String identificacion = inputValidator.leerString("Identificación del estudiante: ");
        if (!estudianteService.existeEstudiante(identificacion)) {
            inputValidator.mensaje("No existe un estudiante con esa identificación");
            return;
        }
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
        inputValidator.mensaje(estudianteService.listarEnEspera());
    }
    private String textoEstudiante(Estudiante e) {
        return e.getNombre() + " (" + e.getIdentificacion() + ")";
    }
}
