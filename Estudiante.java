public class Estudiante{
    String nombreC;
    int edad ;
    String identificacion;
    String carrera;
    public Estudiante(String nombreC, int edad, String identificacion,String carrera){
        this.nombreC=nombreC;
        this.edad=edad;
        this.identificacion=identificacion;
        this.carrera=carrera;
    }
    public void mostrarDatos(){
        System.out.printIn("Nombre Estudiante: "+nombreC+" tengo "+edad+" y estudio "+carrera)
    }

}