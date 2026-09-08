public class PruebaCreacionObjetos {
    public static void main(String[] args) {

        Estudiante estudiante = new Estudiante("Ana Torres", "ana@correo.com", "1001", "E-100", 3);

        Profesor profesor = new Profesor("Carlos Ruiz", "carlos@correo.com", "2002", "P-200", "Sistemas");
        
        System.out.println(estudiante.identificarRol());
        System.out.println(estudiante.datosResumen());

        System.out.println(profesor.identificarRol());
        System.out.println(profesor.datosResumen());       

    }
}