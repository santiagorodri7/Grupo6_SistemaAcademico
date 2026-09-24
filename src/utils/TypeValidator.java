package utils;

public class TypeValidator {
    static InputValidator lector = new InputValidator();

    public TypeValidator() {
    }

    public void Mensaje(String texto) {
        lector.Mensaje(texto);
    }

    public int leerInt(String texto) {
        return lector.leerInt(texto);
    }

    public float leerFloat(String texto) {
        return lector.leerFloat(texto);
    }

    public String leerString(String texto) {
        return lector.leerString(texto);
    }

    public int leerIntEnRango(int minimo, int maximo, String texto) {
        return lector.leerEnEnteroEnRango(minimo, maximo, texto);
    }

    public float leerFloatEnRango(float minimo, float maximo, String texto) {
        return lector.leerEnFloatEnRango(minimo, maximo, texto);
    }
}