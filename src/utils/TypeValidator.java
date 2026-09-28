package utils;

public class TypeValidator {
    static InputValidator lector = new InputValidator();

    public TypeValidator() {
    }

    public void Mensaje(String texto) {
        lector.Mensaje(texto);
    }

    public String leerString(String texto) {
        return lector.leerString(texto);
    }

    public int leerIntEnRango(int minimo, int maximo, String texto) {
        return lector.leerEnEnteroEnRango(minimo, maximo, texto);
    }
}