package view;

import service.EstudianteService;
import service.ProfesorService;
import utils.TypeValidator;

public class MenuView{
    TypeValidator tv = new TypeValidator();
    EstudianteService estudianteService = new EstudianteService();
    ProfesorService profesorService = new ProfesorService();

    public void iniciar() {
        int opcionInicial;
        do {
            opcionInicial = mostrarMenuInicial();

            if (opcionInicial == 1) {
                gestionarEstudiantes();
            } else if (opcionInicial == 2) {
                gestionarProfesores();
            }
        } while (opcionInicial != 0);
    }

    private void gestionarEstudiantes() {
        int opcion;
        do {
            opcion = mostrarMenuDeGestion(1);
            switch (opcion) {
                case 1 -> estudianteService.crearEstudiante();
                case 2 -> estudianteService.buscarPorIndice();
                case 3 -> estudianteService.buscarPorCedula();
                case 4 -> estudianteService.actualizarPorCedula();
                case 5 -> estudianteService.eliminarPorCedula();
                case 6 -> estudianteService.mostrarTodos();
            }
        } while (opcion != 0);
    }

    private void gestionarProfesores() {
        int opcion;
        do {
            opcion = mostrarMenuDeGestion(2);
            switch (opcion) {
                case 1 -> profesorService.crearProfesor();
                case 2 -> profesorService.buscarPorIndice();
                case 3 -> profesorService.buscarPorCedula();
                case 4 -> profesorService.actualizarPorCedula();
                case 5 -> profesorService.eliminarPorCedula();
                case 6 -> profesorService.mostrarTodos();
            }
        } while (opcion != 0);
    }

    public int mostrarMenuInicial(){
        String mensaje = " BIENVENIDO :D! \n Elija una opcion segun su necesidad \n " + 
            "1. gestionar estudiantes \n 2. gestionar profesores \n 0. salir";
        return tv.leerIntEnRango(0,2,mensaje);
    }
    public int mostrarMenuDeGestion(int opcion){
        if(opcion==1){
            String menu = generarMenuEstudiante();
            return tv.leerIntEnRango(0, 6, menu);
        } else {
            String menu = generarMenuProfesor();
            return tv.leerIntEnRango(0, 6, menu);
        }
    }
    public String generarMenuEstudiante(){
        return ("1. crear estudiante \n 2. buscar estudiante por indice \n 3. buscar estudiante por cedula \n 4. actualizar estudiante por cedula \n 5. eliminar estudiante por cedula \n 6. mostrar todos los estudiantes \n 0. en caso de que quiera devolverse");


    }
    public String generarMenuProfesor(){
        return ("1. crear profesor \n 2. buscar profesor por indice \n 3. buscar profesor por cedula \n 4. actualizar profesor por cedula \n 5. eliminar profesor por cedula \n 6. mostrar todos los profesores \n 0. en caso de que se quiera devolver");


    }

    
}