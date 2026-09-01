public abstract class Persona implements RolAcademico {

    private String identificacion;
    private String nombre;
    private String correo;

    public Persona(String nombre, String correo, String identificacion) {
        this.nombre = nombre;
        if(correo.contains("@"))
            this.correo = correo;
        else{
            System.out.println("Correo no valido porfavor intente nuevamente");
            throw new IllegalArgumentException();
        }
        this.identificacion = identificacion;
    }

    public String datosResumen() {
        return "Mi identificacion es " + identificacion
                + ", mi nombre es " + nombre
                + " y mi correo es " + correo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // public abstract String identificarRol();
}
