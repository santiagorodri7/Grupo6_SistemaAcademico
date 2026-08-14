public class Profesor{
    String nombre;
    String asignatura;
    String documento;
    int edad;


public Profesor(String nombre, String asignatura, String documento, int edad){
    this.nombre = nombre;
    this.asignatura = asignatura;
    this.documento = documento;
    this.edad = edad;
}
public void mostratInfo(){
    System.out.println("Hola, soy el profesor " + this.nombre + ", doy la asignatura de: " + asignatura + " y mi documento es " + documento);

}
}