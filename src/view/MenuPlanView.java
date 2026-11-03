package view;

import service.MateriaService;
import service.PlanService;
import utils.TypeValidator;

public class MenuPlanView {
    private final TypeValidator typeValidator = new TypeValidator();
    private final PlanService planService;
    private final MateriaService materiaService;

    public MenuPlanView(PlanService planService, MateriaService materiaService) {
        this.planService = planService;
        this.materiaService = materiaService;
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = typeValidator.leerIntEnRango(0, 9, generarMenu());
            switch (opcion) {
                case 1 -> registrarMateria();
                case 2 -> agregarMateriaAlPlan();
                case 3 -> typeValidator.mensaje(planService.listarPlanEnOrden());
                case 4 -> typeValidator.mensaje(planService.listarPlanEnOrdenInverso());
                case 5 -> verAnterior();
                case 6 -> verSiguiente();
                case 7 -> retirar();
                case 8 -> guardarTodo();
                case 9 -> typeValidator.mensaje(materiaService.listarMaterias());
                case 0 -> guardarTodo();
            }
        } while (opcion != 0);
    }

    private String generarMenu() {
        return "1. registrar materia \n"
                + " 2. agregar materia al plan \n"
                + " 3. listar plan del primero al ultimo semestre \n"
                + " 4. listar plan del ultimo al primero \n"
                + " 5. ver materia anterior \n"
                + " 6. ver materia siguiente \n"
                + " 7. retirar del plan la materia que estoy mirando \n"
                + " 8. guardar \n"
                + " 9. listar todas las materias registradas \n"
                + " 0. guardar y volver";
    }

    private void registrarMateria() {
        String codigo = typeValidator.leerString("Ingrese el codigo de la materia:");
        if (materiaService.existeMateria(codigo)) {
            typeValidator.mensaje("Ya existe una materia con ese codigo");
            return;
        }
        String nombre = typeValidator.leerString("Ingrese el nombre de la materia:");
        int creditos = typeValidator.leerIntEnRango(1, 10, "Ingrese los creditos (1-10):");
        materiaService.registrarMateria(codigo, nombre, creditos);
        typeValidator.mensaje("Materia registrada con exito");
    }

    private void agregarMateriaAlPlan() {
        String codigo = typeValidator.leerString("Ingrese el codigo de la materia a agregar al plan:");
        if (!planService.existeMateria(codigo)) {
            typeValidator.mensaje("No existe una materia con ese codigo");
            return;
        }
        planService.registrarMateriaEnPlan(codigo);
        typeValidator.mensaje("Materia agregada al plan");
    }

    private void verAnterior() {
        if (!planService.hayMaterias()) {
            typeValidator.mensaje("No hay materias en el plan");
            return;
        }
        if (!planService.hayMateriaAnterior()) {
            typeValidator.mensaje("No hay materia anterior");
            return;
        }
        typeValidator.mensaje("Materia anterior:\n" + planService.verMateriaAnterior());
    }

    private void verSiguiente() {
        if (!planService.hayMaterias()) {
            typeValidator.mensaje("No hay materias en el plan");
            return;
        }
        if (!planService.hayMateriaSiguiente()) {
            typeValidator.mensaje("No hay materia siguiente");
            return;
        }
        typeValidator.mensaje("Materia siguiente:\n" + planService.verMateriaSiguiente());
    }

    private void retirar() {
        if (!planService.hayMateriaSeleccionada()) {
            typeValidator.mensaje("No hay una materia seleccionada: navegue primero con anterior o siguiente");
            return;
        }
        typeValidator.mensaje("Materia retirada del plan:\n" + planService.retirarMateriaDelPlan());
    }

    private void guardarTodo() {
        typeValidator.mensaje(planService.guardarPlan());
        typeValidator.mensaje(materiaService.guardarMaterias());
    }
}