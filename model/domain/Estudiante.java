public class Estudiante{
    private String nombreC;
    private int edad ;
    private String identificacion;
    private String carrera;
    public Estudiante(String nombreC, int edad, String identificacion,String carrera){
        this.nombreC=nombreC;
        this.edad=edad;
        this.identificacion=identificacion;
        this.carrera=carrera;
    }
    //get
    public String getNombre(){
        return this.nombreC;
    }     
    //set
    public String setNombre(){
        return this.nombreC;
    } 
    public void mostrarDatos(){
        System.out.printIn("Nombre Estudiante: "+nombreC+" tengo "+edad+" y estudio "+carrera)
    }

}