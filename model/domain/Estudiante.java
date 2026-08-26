public class Estudiante extends Persona {

    private String codigo;
    private int semestreActual;



    public Estudiante(String nombre, String correo, String documento,
                       String codigo, int semestreActual) {
        super(nombre, correo, documento);
        this.codigo = codigo;
        this.semestreActual = semestreActual;
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
    @Override
    public String datosResumen(){
        return "a";
    }
    @Override
    public String identificarRol() {
        return "Estudiante";
    }
}
