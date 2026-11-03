package model.domain;

import model.structures.ListaDoble;

public class PlanDeEstudios {
    private String programa;
    private ListaDoble<Materia> materias;

    public PlanDeEstudios(String programa) {
        if (programa == null || programa.trim().isEmpty()) {
            throw new IllegalArgumentException("El programa no puede ser null ni vacío");
        }
        this.programa = programa;
        this.materias = new ListaDoble<>();
    }

    public String getPrograma() {
        return programa;
    }

    public ListaDoble<Materia> getMaterias() {
        return materias;
    }
}