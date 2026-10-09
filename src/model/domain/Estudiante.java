package model.domain;
import model.domain.structures.ListaSimple;

public class Estudiante extends Persona {
    private String codigo;
    private int semestreActual;
    private ListaSimple<Matricula> matriculas;

    @Override
    public String identificarRol() {
        return "Estudiante";
    }

    public Estudiante(String nombre, String correo, String identificacion,
                       String codigo, int semestreActual) {
        super(nombre, correo, identificacion);
        this.codigo = codigo;
        this.semestreActual = semestreActual;
        this.matriculas = new ListaSimple<>();
    } 

    public String getCodigo() { 
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getSemestreActual() {
        return semestreActual;
    }

    public void setSemestreActual(int semestreActual) {
        this.semestreActual = semestreActual;
    }

    public ListaSimple<Matricula> getMatriculas() {
        return matriculas;
    }
    
}
