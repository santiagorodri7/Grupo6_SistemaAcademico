package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.domain.Materia;
import model.structures.ListaSimple;

public class MateriaService {
    private static final String ARCHIVO_MATERIAS = "data/materias.txt";
    private static final String SEPARADOR = ";";

    private final ListaSimple<Materia> materias = new ListaSimple<>();

    public MateriaService() {
        // Se carga una sola vez, al crear el Service
        cargarMaterias();
    }

    public void registrarMateria(String codigo, String nombre, int creditos) {
        materias.insertarFinal(new Materia(nombre, codigo, creditos));
        guardarMaterias(); // guardado automático
    }

    public Materia buscarMateria(String codigo) {
        for (int i = 0; i < materias.getTamanio(); i++) {
            Materia m = materias.buscarPorInidice(i);
            if (m.getCodigo().equals(codigo)) {
                return m;
            }
        }
        return null;
    }

    public boolean existeMateria(String codigo) {
        return buscarMateria(codigo) != null;
    }

    public int contarMaterias() {
        return materias.getTamanio();
    }

    public String listarMaterias() {
        if (materias.estaVacia()) {
            return "No hay materias registradas";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < materias.getTamanio(); i++) {
            sb.append(materias.buscarPorInidice(i)).append("\n");
        }
        return sb.toString();
    }

    // ---------------- Archivos ----------------

    // Crea la carpeta data/ y el archivo si no existen. Devuelve true si tuvo que crear el archivo.
    private boolean asegurarArchivo() throws IOException {
        File archivo = new File(ARCHIVO_MATERIAS);
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        if (!archivo.exists()) {
            return archivo.createNewFile();
        }
        return false;
    }

    public String guardarMaterias() {
        try {
            asegurarArchivo();

            // Una línea por materia: codigo;nombre;creditos
            StringBuilder texto = new StringBuilder();
            for (int i = 0; i < materias.getTamanio(); i++) {
                Materia m = materias.buscarPorInidice(i);
                texto.append(String.join(SEPARADOR,
                        m.getCodigo(), m.getNombre(), String.valueOf(m.getCreditos())))
                     .append("\n");
            }

            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO_MATERIAS))) {
                escritor.write(texto.toString());
            }
            return "Se guardaron " + materias.getTamanio() + " materias en " + ARCHIVO_MATERIAS;
        } catch (IOException e) {
            return "No se pudo guardar: " + e.getMessage();
        }
    }

    public String cargarMaterias() {
        // Vaciar antes de leer: cargar dos veces no duplica
        while (!materias.estaVacia()) {
            materias.eliminarInicio();
        }

        try {
            if (asegurarArchivo()) {
                return "No existía " + ARCHIVO_MATERIAS + "; se creó vacío y se inicia con la lista de materias vacía";
            }

            try (BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO_MATERIAS))) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    if (linea.trim().isEmpty()) {
                        continue;
                    }
                    String[] campos = linea.split(SEPARADOR);
                    if (campos.length != 3) {
                        continue; // campos de más o de menos: se omite
                    }
                    try {
                        String codigo = campos[0].trim();
                        String nombre = campos[1].trim();
                        int creditos = Integer.parseInt(campos[2].trim());
                        if (codigo.isEmpty() || nombre.isEmpty() || existeMateria(codigo)) {
                            continue;
                        }
                        materias.insertarFinal(new Materia(nombre, codigo, creditos));
                    } catch (NumberFormatException e) {
                        continue; // créditos mal escritos: se omite la línea
                    }
                }
            }
        } catch (IOException e) {
            return "No se pudo leer: " + e.getMessage();
        }

        return "Se cargaron " + materias.getTamanio() + " materias";
    }
}