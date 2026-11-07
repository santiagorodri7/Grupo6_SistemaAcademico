package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.domain.Materia;
import model.structures.ListaSimple;
import model.structures.Nodo;

public class MateriaService {
    private static final String ARCHIVO_MATERIAS = "data/materias.txt";
    private static final String SEPARADOR = ";";

    private final ListaSimple<Materia> materias = new ListaSimple<>();

    public MateriaService() {
      
        cargarMaterias();
    }

    public void registrarMateria(String codigo, String nombre, int creditos) {
        materias.insertarFinal(new Materia(nombre, codigo, creditos));
        guardarMaterias(); 
    }

    public Materia buscarMateria(String codigo) {
        Nodo<Materia> nodo = materias.getHead();
        while (nodo != null) {
            if (nodo.getDato().getCodigo().equals(codigo)) {
                return nodo.getDato();
            }
            nodo = nodo.getSiguiente();
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
        Nodo<Materia> nodo = materias.getHead();
        while (nodo != null) {
            sb.append(nodo.getDato()).append("\n");
            nodo = nodo.getSiguiente();
        }
        return sb.toString();
    }


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

        
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO_MATERIAS))) {
                Nodo<Materia> nodo = materias.getHead();
                while (nodo != null) {
                    Materia m = nodo.getDato();
                    escritor.write(String.join(SEPARADOR,
                            m.getCodigo(), m.getNombre(), String.valueOf(m.getCreditos())));
                    escritor.newLine();
                    nodo = nodo.getSiguiente();
                }
            }
            return "Se guardaron " + materias.getTamanio() + " materias en " + ARCHIVO_MATERIAS;
        } catch (IOException e) {
            return "No se pudo guardar: " + e.getMessage();
        }
    }

    public String cargarMaterias() {
   
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
                    String[] campos = linea.split(SEPARADOR);
                    if (campos.length != 3) {
                        continue;
                    }
                    try {
                        String codigo = campos[0].trim();
                        String nombre = campos[1].trim();
                        int creditos = Integer.parseInt(campos[2].trim());
                        if (codigo.isEmpty() || nombre.isEmpty()) {
                            continue;
                        }
                        materias.insertarFinal(new Materia(nombre, codigo, creditos));
                    } catch (NumberFormatException e) {
                        continue; 
                    }
                }
            }
        } catch (IOException e) {
            return "No se pudo leer: " + e.getMessage();
        }

        return "Se cargaron " + materias.getTamanio() + " materias";
    }
}