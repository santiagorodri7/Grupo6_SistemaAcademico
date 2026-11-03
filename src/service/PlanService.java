package service;

import model.domain.Materia;
import model.domain.PlanDeEstudios;

public class PlanService {

    // Un solo plan por Service: si se creara uno nuevo en cada método,
    // cada operación vería una lista distinta
    // private final PlanDeEstudios plan = new PlanDeEstudios("Plan de estudios");
    private final PlanDeEstudios plan = new PlanDeEstudios();

    // El Service que ya gestiona las materias (se usa solo para validar que existan)
    // private final MateriaService materiaService;
    private final MatriculaService matriculaService;

    public PlanService(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    // registrarProducto -> agregarMateriaAlPlan
    public void agregarMateriaAlPlan(String codigoMateria) {
        Materia materia = materiaService.buscarMateria(codigoMateria); // ajuste al nombre real
        if (materia == null) {
            throw new IllegalArgumentException("No existe la materia " + codigoMateria);
        }
        plan.getMaterias().insertarAlFinal(materia);
    }

    public String listarPlanEnOrden() {
        if (plan.getMaterias().isEmpty()) {
            return "No hay materias en el plan";
        }
        return plan.getMaterias().recorrerAdelante();
    }

    public String listarPlanEnOrdenInverso() {
        if (plan.getMaterias().isEmpty()) {
            return "No hay materias en el plan";
        }
        return plan.getMaterias().recorrerAtras();
    }

    public Materia verMateriaAnterior() {
        return plan.getMaterias().anterior();
    }

    public Materia verMateriaSiguiente() {
        return plan.getMaterias().siguiente();
    }

    public Materia retirarMateriaDelPlan() {
        return plan.getMaterias().retirarActual();
    }

    public int contarMaterias() {
        return plan.getMaterias().size();
    }
}