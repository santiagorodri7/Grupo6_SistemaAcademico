package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.structures.ListaSimple;

public class ArchivoService {
    public static final String SEPARADOR = ";";

    private final File archivo;

    public ArchivoService(String ruta) {
        if (ruta == null || ruta.trim().isEmpty()) {
            throw new IllegalArgumentException("La ruta del archivo no puede ser null ni vacía");
        }
        this.archivo = new File(ruta);
    }

    public String getRuta() {
        return archivo.getPath();
    }

    public boolean existe() {
        return archivo.exists();
    }


    public boolean asegurarArchivo() throws IOException {
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        if (!archivo.exists()) {
            return archivo.createNewFile();
        }
        return false;
    }


    public ListaSimple<String> leerLineas() throws IOException {
        asegurarArchivo();
        ListaSimple<String> lineas = new ListaSimple<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lineas.insertarFinal(linea);
                }
            }
        }
        return lineas;
    }

    public void escribirLineas(ListaSimple<String> lineas) throws IOException {
        asegurarArchivo();
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            for (int i = 0; i < lineas.getTamanio(); i++) {
                escritor.write(lineas.buscarPorInidice(i));
                escritor.newLine();
            }
        }
    }
}