package model.domain;


public class App {
    public static void main(String[] args) {
    
        Matricula Matricula1 = new Matricula("Juan", "M0009", "2026-02-10", "Activa", 1905000.0);
        
       
        Matricula Matricula2 = new Matricula("Maria", "M1200", "2020-09-15", "Inactiva", 1887000.0);

        
        Matricula1.mostrarInformacion();
        System.out.println(); 
        Matricula2.mostrarInformacion();
    }
}