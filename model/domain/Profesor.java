public class Profesor extends Persona{
    private String codigo;
    private String departamento;
    
    @Override
    public String identificarRol() {
        return "Profesor";
    }
    //el constructor  
    public Profesor(String nombre, String correo, String identificacion, String codigo, String departamento) {
        super(nombre, correo, identificacion);
        this.codigo = codigo;
        this.departamento = departamento;
    }
    //getters y setters
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
  


}
