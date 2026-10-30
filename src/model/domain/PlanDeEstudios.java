package model.domain;

import model.structures.ListaDoble;

public class PlanDeEstudios {
    private String programa;
    private ListaDoble<Materia> materias;

    public PlanDeEstudios(String programa) {
        if (programa == null || programa.isBlank()) {
            throw new IllegalArgumentException("El programa no puede estar vacío");
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