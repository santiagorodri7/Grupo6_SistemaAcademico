package utils;

public class TypeValidator {
    static InputValidator lector = new InputValidator();

    public TypeValidator() {
    }

    public void mensaje(String texto) {
        lector.mensaje(texto);
    }

    public String leerString(String texto) {
        return lector.leerString(texto);
    }

    public String leerCorreo(String texto) {
        String correo = lector.leerString(texto);
        while (!correo.contains("@")) {
            lector.mensaje("Correo no valido, debe contener @");
            correo = lector.leerString(texto);
        }
        return correo;
    }

    public int leerIntEnRango(int minimo, int maximo, String texto) {
        return lector.leerEnEnteroEnRango(minimo, maximo, texto);
    }

    public float leerFloatEnRango(float minimo, float maximo, String texto) {
        return lector.leerEnFloatEnRango(minimo, maximo, texto);
    }
}