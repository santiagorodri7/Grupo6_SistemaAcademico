
import java.util.ArrayList;
import java.util.List;

public class App{
    //Puerba de cloude
  
        public static void main(String[] args) {
        Profesor profe = new Profesor("P001", "Ana Gomez", "ana.gomez@uni.edu",
                "PRF-10", "Ingenieria de Sistemas");
 
        Estudiante estudiante = new Estudiante("E001", "Juan Perez", "juan.perez@uni.edu",
                "EST-22", 5);
 
        // Probamos que se puede tratar cada objeto como su clase concreta
        System.out.println("=== Prueba directa ===");
        System.out.println(profe.identificarRol() + " -> " + profe.datosResumen());
        System.out.println(estudiante.identificarRol() + " -> " + estudiante.datosResumen());
 
        // Probamos el polimorfismo: tratarlos como RolAcademico (la interfaz)
        System.out.println("\n=== Prueba de polimorfismo (via interfaz RolAcademico) ===");
        List<RolAcademico> roles = new ArrayList<>();
        roles.add(profe);
        roles.add(estudiante);
 
        for (RolAcademico rol : roles) {
            System.out.println("[" + rol.identificarRol() + "] " + rol.datosResumen());
        }
 
        // Probamos también tratarlos como Persona (la clase abstracta)
        System.out.println("\n=== Prueba via clase abstracta Persona ===");
        List<Persona> personas = new ArrayList<>();
        personas.add(profe);
        personas.add(estudiante);
 
        for (Persona p : personas) {
            System.out.println(p.getNombre() + " es " + p.identificarRol());
        }
    }

}