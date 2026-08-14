public class Profesor{
    String nombre;
    String materia;
    String identificacion;
    int edad;
    public Profesor(String nombre, String materia, String identificacion, int edad){
        this.nombre = nombre;
        this.materia = materia;
        this.identificacion = identificacion;
        this.edad = edad;
    }
    public void mostrarInformacion(){
        System.out.println("Soy el profesor " + profesor + ", doy la materia de " + materia + " y tengo " + edad + " años");
    }
}
