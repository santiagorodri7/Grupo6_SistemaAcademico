package model.domain;

public class Matricula {
    String nombre;
    String Cmatricula;
    String fecha;
    String estado;
    double Valor;

   
    public Matricula(String nombre, String Cmatricula, String fecha, String estado, double Valor) {
        this.Cmatricula = Cmatricula;
        this.fecha = fecha;
        this.estado = estado;
        this.Valor = Valor;
        this.nombre = nombre;
    }

    
    public void mostrarInformacion() {
        System.out.println("INFORMACION MATRICULA");
        System.out.println("Nombre: " + nombre);
        System.out.println("Código: " + Cmatricula);
        System.out.println("Fecha: " + fecha);
        System.out.println("Estado: " + estado);
        System.out.println("Costo: $" + Valor);
    }
}