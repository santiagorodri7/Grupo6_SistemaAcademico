package src.utils;



public class TypeValidator {
    static InputValidator a = new InputValidator();

    public TypeValidator() {
    }

    public void Mensaje(String texto) {
        a.Mensaje(texto);
    }

    public int leerInt(String texto) {
        return a.leerInt(texto);
    }

    public float leerFloat(String texto) {
        return a.leerFloat(texto);
    }

    public String leerString(String texto) {
        return a.leerString(texto);
    }

    public int leerIntEnRango(int minimo, int maximo, String texto) {
        return a.leerEnEnteroEnRango(minimo, maximo, texto);
    }

    public float leerFloatEnRango(float minimo, float maximo, String texto) {
        return a.leerEnFloatEnRango(minimo, maximo, texto);
    }
}