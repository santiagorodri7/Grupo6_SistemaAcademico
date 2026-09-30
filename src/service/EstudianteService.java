package service;

import domain.model.Estudiante;
import domain.model.Matricula;
import domain.model.structures.List;
import utils.TypeValidator;

public class EstudianteService {

    private List<Estudiante> estudiantes;
    private TypeValidator typeValidator;

    public EstudianteService() {
        this.estudiantes = new List<>();
        this.typeValidator = new TypeValidator();
    }

    public void crearEstudiante() {
        String nombre = typeValidator.leerString("Ingrese el nombre del estudiante:");
        String correo = typeValidator.leerString("Ingrese el correo del estudiante:");
        String identificacion = typeValidator.leerString("Ingrese la cedula del estudiante:");
        String codigo = typeValidator.leerString("Ingrese el codigo del estudiante:");
        int semestreActual = typeValidator.leerIntEnRango(1, 12, "Ingrese el semestre actual del estudiante (1-12):");

        try {
            Estudiante estudiante = new Estudiante(nombre, correo, identificacion, codigo, semestreActual);
            estudiantes.insertarFinal(estudiante);
            typeValidator.Mensaje("Estudiante creado con exito");
        } catch (Exception ex) {
            typeValidator.Mensaje("No se pudo crear el estudiante, correo invalido");
        }
    }

    public void agregarMatricula(){
        String cedula = typeValidator.leerString("Ingrese el numero de la cedula a buscar:");
        Estudiante estudiante = buscarEstudiantePorCedula(cedula);
        if (estudiante == null){
            typeValidator.Mensaje("No existe el estudiante con esa cedula");
            return;
        }
        String nombreMatricula = typeValidator.leerString("Ingrese el nombre de la matricula (ITM):");
        String periodoAcademico = typeValidator.leerString("Ingrese el periodo academico (2026-2):");
        Matricula matricula = new Matricula(estudiante, periodoAcademico, nombreMatricula);
        estudiante.getMatriculas().insertarFinal(matricula);
    }
    public void buscarMatriculaPorCedula() {
        String cedula = typeValidator.leerString("Ingrese la cedula a buscar: ");
        Estudiante estudiante = buscarEstudiantePorCedula(cedula);
        if (estudiante == null) {
            typeValidator.Mensaje("Estudiante no existente");
            return;
        }

        if (estudiante.getMatriculas().estaVacia()) {  
            typeValidator.Mensaje("Este estudiante no tiene matrículas registradas");
            return;
        }

        typeValidator.Mensaje("Matrículas de " + estudiante.getNombre() + ":");

        String rpta = "";
        int i = 0;
        int total = estudiante.getMatriculas().getTamanio();

        while (i < total) {
            Matricula m = estudiante.getMatriculas().buscarPorInidice(i);
            rpta += m.getNombreMatricula()+ "\n";
            i++;
        }

        typeValidator.Mensaje(rpta);

    }

    public void eliminarMatricula(){
         String cedula = typeValidator.leerString("Ingrese la cedula por eliminar: ");
         Estudiante estudiante = buscarEstudiantePorCedula(cedula);
         if(estudiante == null){
            typeValidator.Mensaje("El estudiante no existe"); 
            return; 
         }
         estudiante.getMatriculas().eliminarInicio();
    }

    public void buscarPorIndice() {
        if (estudiantes.estaVacia()) {
            typeValidator.Mensaje("No hay estudiantes registrados");
            return;
        }
        int indice = typeValidator.leerIntEnRango(0, estudiantes.getTamanio() - 1, "Ingrese el indice del estudiante:");
        Estudiante estudiante = estudiantes.buscarPorInidice(indice);
        typeValidator.Mensaje(estudiante.datosResumen());
    }

    private Estudiante buscarEstudiantePorCedula(String cedula) {
        for (int i = 0; i < estudiantes.getTamanio(); i++) {
            Estudiante actual = estudiantes.buscarPorInidice(i);
            if (actual.getIdentificacion().equals(cedula)) {
                return actual;
            }
        }
        return null;
    }

    public void buscarPorCedula() {
        String cedula = typeValidator.leerString("Ingrese la cedula del estudiante a buscar:");
        Estudiante estudiante = buscarEstudiantePorCedula(cedula);
        if (estudiante == null) {
            typeValidator.Mensaje("No se encontro un estudiante con esa cedula");
        } else {
            typeValidator.Mensaje(estudiante.datosResumen());
        }
    }

    public void actualizarPorCedula() {
        String cedula = typeValidator.leerString("Ingrese la cedula del estudiante a actualizar:");
        Estudiante estudiante = buscarEstudiantePorCedula(cedula);
        if (estudiante == null) {
            typeValidator.Mensaje("No se encontro un estudiante con esa cedula");
            return;
        }
        String nombre = typeValidator.leerString("Ingrese el nuevo nombre:");
        String correo = typeValidator.leerString("Ingrese el nuevo correo:");
        String codigo = typeValidator.leerString("Ingrese el nuevo codigo:");
        int semestreActual = typeValidator.leerIntEnRango(1, 12, "Ingrese el nuevo semestre actual (1-12):");

        estudiante.setNombre(nombre);
        estudiante.setCorreo(correo);
        estudiante.setCodigo(codigo);
        estudiante.setSemestreActual(semestreActual);
        typeValidator.Mensaje("Estudiante actualizado con exito");
    }

    public void eliminarPorCedula() {
        String cedula = typeValidator.leerString("Ingrese la cedula del estudiante a eliminar:");
        Estudiante estudiante = buscarEstudiantePorCedula(cedula);
        if (estudiante == null) {
            typeValidator.Mensaje("No se encontro un estudiante con esa cedula");
            return;
        }
        estudiantes.eliminarPorValor(estudiante);
        typeValidator.Mensaje("Estudiante eliminado con exito");
    }

    public void mostrarTodos() {
        if (estudiantes.estaVacia()) {
            typeValidator.Mensaje("No hay estudiantes registrados");
            return;
        }
        String listado = "";
        for (int i = 0; i < estudiantes.getTamanio(); i++) {
            Estudiante actual = estudiantes.buscarPorInidice(i);
            listado += (i + ". " + actual.datosResumen() + "\n");
        }
        typeValidator.Mensaje(listado);
    }
}