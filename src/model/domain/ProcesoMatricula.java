package model.domain;
import model.domain.structures.Cola;

public class ProcesoMatricula<T> {
    private String semestre;
    private Cola<T> fila;
    
    public String getSemestre() {
        return semestre;
    }
    public Cola<T> getFila() {
        return fila;
    }
    
}
