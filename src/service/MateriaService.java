package service;

import java.io.IOException;

import model.domain.Materia;
import model.structures.ListaSimple;

public class MateriaService {
    private final ListaSimple<Materia> materias = new ListaSimple<>();
    private final ArchivoService archivo = new ArchivoService("data/materias.txt");

    public MateriaService() {

        cargarMaterias();
    }

    public void registrarMateria(String codigo, String nombre, int creditos) {
        materias.insertarFinal(new Materia(nombre, codigo, creditos));
        guardarMaterias(); 
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


    public String guardarMaterias() {
        try {
            ListaSimple<String> lineas = new ListaSimple<>();
            for (int i = 0; i < materias.getTamanio(); i++) {
                Materia m = materias.buscarPorInidice(i);
                lineas.insertarFinal(String.join(ArchivoService.SEPARADOR,
                        m.getCodigo(), m.getNombre(), String.valueOf(m.getCreditos())));
            }
            archivo.escribirLineas(lineas);
            return "Se guardaron " + materias.getTamanio() + " materias en " + archivo.getRuta();
        } catch (IOException e) {
            return "No se pudo guardar: " + e.getMessage();
        }
    }

    public String cargarMaterias() {
      
        while (!materias.estaVacia()) {
            materias.eliminarInicio();
        }

        try {
            boolean existia = archivo.existe();
            ListaSimple<String> lineas = archivo.leerLineas(); 
            if (!existia) {
                return "No existía " + archivo.getRuta() + "; se creó vacío y se inicia con la lista de materias vacía";
            }

            for (int i = 0; i < lineas.getTamanio(); i++) {
                String[] campos = lineas.buscarPorInidice(i).split(ArchivoService.SEPARADOR);
                if (campos.length != 3) {
                    continue; 
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
                    continue; 
                }
            }
        } catch (IOException e) {
            return "No se pudo leer: " + e.getMessage();
        }

        return "Se cargaron " + materias.getTamanio() + " materias";
    }
}