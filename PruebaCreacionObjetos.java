import java.util.ArrayList;
import java.util.List;
public class PruebaCreacionObjetos{
        public static void main(String[] args) {
        Profesor profe = new Profesor("samuelito", "Ana@Gomez", "2391293123",
                "PRF-10", "Ingenieria de Sistemas");
 
        Estudiante estudiante = new Estudiante("miguelito", "Juan@Perez", "123124512",
                "EST-22", 5);
 
        System.out.println("=== Prueba directa ===");
        System.out.println(profe.identificarRol() + " -> " + profe.datosResumen());
        System.out.println(estudiante.identificarRol() + " -> " + estudiante.datosResumen());
 
        System.out.println("\n=== Prueba de polimorfismo (via interfaz RolAcademico) ===");
        List<RolAcademico> roles = new ArrayList<>();
        roles.add(profe);
        roles.add(estudiante);
 
        for (RolAcademico rol : roles) {
            System.out.println("[" + rol.identificarRol() + "] " + rol.datosResumen());
        }
 
        System.out.println("\n=== Prueba via clase abstracta Persona ===");
        List<Persona> personas = new ArrayList<>();
        personas.add(profe);
        personas.add(estudiante);
 
        for (Persona p : personas) {
            System.out.println(p.getNombre() + " es " + p.identificarRol());
        }
    }

}