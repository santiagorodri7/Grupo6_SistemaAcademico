package view;

import model.domain.Calificacion;
import model.domain.Materia;
import service.CalificacionService;
import utils.TypeValidator;

public class MenuPilaView {

    private final TypeValidator typeValidator = new TypeValidator();
    private final CalificacionService calificacionService;

    public MenuPilaView(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }
   

    public void iniciar() {
        int opcion;
        do {
            opcion = typeValidator.leerIntEnRango(0, 7, generarMenu());
            switch (opcion) {
                case 1 -> registrar();
                case 2 -> deshacer();
                case 3 -> consultarUltima();
                case 4 -> verSiHayCalificaciones();
                case 5 -> contar();
                case 6 -> listar();
                case 7 -> crearMatricula();
            }
        } while (opcion != 0);
    }

    private void registrar() {
        String cedula = typeValidator.leerString("Cedula del estudiante:");
        String nombreMatricula = typeValidator.leerString("Nombre de la matricula:");
        float notaParcial1 = typeValidator.leerFloatEnRango(0, 5, "Nota del parcial 1 (0-5):");
        float notaParcial2 = typeValidator.leerFloatEnRango(0, 5, "Nota del parcial 2 (0-5):");
        float notaFinal = typeValidator.leerFloatEnRango(0, 5, "Nota final (0-5):");
        String observaciones = typeValidator.leerString("Observaciones:");
        String nombreMateria = typeValidator.leerString("Nombre de la materia:");
        String codigoMateria = typeValidator.leerString("Codigo de la materia:");
        int creditosMateria = typeValidator.leerIntEnRango(1, 10, "Creditos de la materia (1-10):");
        Materia materia = new Materia(nombreMateria, codigoMateria, creditosMateria);

        calificacionService.registrarCalificacion(cedula, nombreMatricula, notaParcial1, notaParcial2,
                notaFinal, observaciones, materia);
        typeValidator.mensaje("Calificacion registrada con exito");
    }

    private void deshacer() {
        String cedula = typeValidator.leerString("Cedula del estudiante:");
        String nombreMatricula = typeValidator.leerString("Nombre de la matricula:");
        if (calificacionService.sinCalificacionesPorDeshacer(cedula, nombreMatricula)) {
            typeValidator.mensaje("No hay calificaciones por deshacer");
            return;
        }
        Calificacion calificacion = calificacionService.deshacerUltimaCalificacion(cedula, nombreMatricula);
        typeValidator.mensaje("Calificacion deshecha:\n" + calificacion);
    }

    private void consultarUltima() {
        String cedula = typeValidator.leerString("Cedula del estudiante:");
        String nombreMatricula = typeValidator.leerString("Nombre de la matricula:");
        if (calificacionService.sinCalificacionesPorDeshacer(cedula, nombreMatricula)) {
            typeValidator.mensaje("No hay calificaciones por deshacer");
            return;
        }
        Calificacion calificacion = calificacionService.consultarUltimaCalificacion(cedula, nombreMatricula);
        typeValidator.mensaje("Ultima calificacion:\n" + calificacion);
    }

    private void verSiHayCalificaciones() {
        String cedula = typeValidator.leerString("Cedula del estudiante:");
        String nombreMatricula = typeValidator.leerString("Nombre de la matricula:");
        if (calificacionService.sinCalificacionesPorDeshacer(cedula, nombreMatricula)) {
            typeValidator.mensaje("No hay calificaciones por deshacer");
        } else {
            typeValidator.mensaje("Hay calificaciones por deshacer");
        }
    }

    private void contar() {
        String cedula = typeValidator.leerString("Cedula del estudiante:");
        String nombreMatricula = typeValidator.leerString("Nombre de la matricula:");
        typeValidator.mensaje("Cantidad de calificaciones: "
                + calificacionService.contarCalificaciones(cedula, nombreMatricula));
    }

    private void listar() {
        String cedula = typeValidator.leerString("Cedula del estudiante:");
        String nombreMatricula = typeValidator.leerString("Nombre de la matricula:");
        typeValidator.mensaje(calificacionService.listarCalificaciones(cedula, nombreMatricula));
    }

    private void crearMatricula() {
        String cedula = typeValidator.leerString("Cedula del estudiante:");
        String nombreMatricula = typeValidator.leerString("Nombre de la matricula:");
        if (calificacionService.existeMatricula(cedula, nombreMatricula)) {
            typeValidator.mensaje("Ya existe una matricula con ese nombre");
            return;
        }
        String periodo = typeValidator.leerString("Periodo academico (ej: 2026-2):");
        calificacionService.crearMatricula(cedula, periodo, nombreMatricula);
        typeValidator.mensaje("Matricula creada con exito");
    }

    private String generarMenu() {
        return "1. Registrar calificacion \n"
                + " 2. Deshacer ultima calificacion \n"
                + " 3. Ver ultima calificacion \n"
                + " 4. Hay calificaciones por deshacer? \n"
                + " 5. Cantidad de calificaciones \n"
                + " 6. Listar calificaciones\n"
                + " 7. Crear matricula\n"
                + " 0. Salir";
    }
}