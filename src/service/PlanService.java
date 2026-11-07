package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.domain.Materia;
import model.domain.PlanDeEstudios;
import model.structures.NodoDoble;

public class PlanService {
    private static final String ARCHIVO_PLAN = "data/plan.txt";
    private static final String SEPARADOR = ";";
    private final PlanDeEstudios plan = new PlanDeEstudios("Plan de estudios");
    private final MateriaService materiaService;
    public PlanService(MateriaService materiaService) {
        this.materiaService = materiaService;
        cargarPlan();
    }

    public void registrarMateriaEnPlan(String codigoMateria) {
        Materia materia = materiaService.buscarMateria(codigoMateria);
        if (materia == null) {
            throw new IllegalArgumentException("La materia no existe: " + codigoMateria);
        }
        plan.getMaterias().insertarAlFinal(materia);
        guardarPlan(); 
    }

    public boolean existeMateria(String codigoMateria) {
        return materiaService.existeMateria(codigoMateria);
    }

    public String listarPlanEnOrden() {
        if (plan.getMaterias().isEmpty()) {
            return "No hay materias en el plan";
        }
        return plan.getMaterias().recorrerAdelante();
    }

    public String listarPlanEnOrdenInverso() {
        if (plan.getMaterias().isEmpty()) {
            return "No hay materias en el plan";
        }
        return plan.getMaterias().recorrerAtras();
    }

    public boolean hayMaterias() {
        return !plan.getMaterias().isEmpty();
    }

    public boolean hayMateriaAnterior() {
        return plan.getMaterias().hayAnterior();
    }

    public boolean hayMateriaSiguiente() {
        return plan.getMaterias().haySiguiente();
    }

    public boolean hayMateriaSeleccionada() {
        return plan.getMaterias().haySeleccionado();
    }

    public Materia verMateriaAnterior() {
        return plan.getMaterias().anterior();
    }

    public Materia verMateriaSiguiente() {
        return plan.getMaterias().siguiente();
    }


    public Materia retirarMateriaDelPlan() {
        Materia retirada = plan.getMaterias().retirarActual();
        guardarPlan(); 
        return retirada;
    }

    public int contarMaterias() {
        return plan.getMaterias().size();
    }



    private void escribirPlan() throws IOException {

        File carpeta = new File(ARCHIVO_PLAN).getParentFile();
        if (carpeta != null) {
            carpeta.mkdirs();
        }


        StringBuilder texto = new StringBuilder();
        NodoDoble<Materia> nodo = plan.getMaterias().getHead();
        while (nodo != null) {
            texto.append(String.join(SEPARADOR, nodo.getDato().getCodigo())).append("\n");
            nodo = nodo.getSiguiente();
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO_PLAN))) {
            escritor.write(texto.toString());
        }
    }

    public String guardarPlan() {
        try {
            escribirPlan();
            return "Se guardaron " + plan.getMaterias().size() + " materias en " + ARCHIVO_PLAN;
        } catch (IOException e) {
            return "No se pudo guardar: " + e.getMessage();
        }
    }

    public String cargarPlan() {
        File archivo = new File(ARCHIVO_PLAN);


        while (plan.getMaterias().getHead() != null) {
            plan.getMaterias().eliminar(plan.getMaterias().getHead());
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(SEPARADOR);
                if (campos.length != 1) {
                    continue; 
                }

                Materia materia = materiaService.buscarMateria(campos[0].trim());
                if (materia == null) {
                    continue;
                }
                plan.getMaterias().insertarAlFinal(materia); 
            }
        } catch (FileNotFoundException e) {
            return "No existe " + ARCHIVO_PLAN + "; se inicia con el plan vacío";
        } catch (IOException e) {
            return "No se pudo leer: " + e.getMessage();
        }

        return "Se cargaron " + plan.getMaterias().size() + " materias en el plan";
    }
}