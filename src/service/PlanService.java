package service;

import model.domain.Materia;
import model.domain.PlanDeEstudios;

public class PlanService {
    // Un solo plan por Service: todas las operaciones ven la misma lista
    private final PlanDeEstudios plan = new PlanDeEstudios("Plan de estudios");
    private final MateriaService materiaService;

    public PlanService(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    public void registrarMateriaEnPlan(String codigoMateria) {
        Materia materia = materiaService.buscarMateria(codigoMateria);
        if (materia == null) {
            throw new IllegalArgumentException("La materia no existe: " + codigoMateria);
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