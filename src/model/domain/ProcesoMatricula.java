package model.domain;
import model.domain.structures.Cola;

public class ProcesoMatricula {
    private String semestre;
    private Cola<Estudiante> fila;

    public ProcesoMatricula(String semestre){
        if(semestre==null  || semestre.isBlank()) throw new IllegalArgumentException("El semestre no puede estar vacío");
        this.semestre = semestre;
        this.fila = new Cola<>();      
    }
    
    public String getSemestre() {
        return semestre;
    }

    public Cola<Estudiante> getFila() {
        return fila;
    }
    

}
