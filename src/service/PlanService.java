package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.domain.Materia;
import model.domain.PlanDeEstudios;
import model.structures.NodoDoble;

public class PlanService {
    private static final String ARCHIVO_PLAN = "data/plan.txt";
    private static final String SEPARADOR = ";";

    // Un solo plan por Service: todas las operaciones ven la misma lista
    private final PlanDeEstudios plan = new PlanDeEstudios("Plan de estudios");
    private final MateriaService materiaService;

    // MateriaService debe crearse ANTES: el plan se carga por código de materia
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
        guardarPlan(); // guardado automático
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

    // Solo sale del plan; la materia sigue existiendo en MateriaService
    public Materia retirarMateriaDelPlan() {
        Materia retirada = plan.getMaterias().retirarActual();
        guardarPlan(); // guardado automático
        return retirada;
    }

    public int contarMaterias() {
        return plan.getMaterias().size();
    }

    // ---------------- Archivos ----------------

    // Crea la carpeta data/ y el archivo si no existen. Devuelve true si tuvo que crear el archivo.
    private boolean asegurarArchivo() throws IOException {
        File archivo = new File(ARCHIVO_PLAN);
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        if (!archivo.exists()) {
            return archivo.createNewFile();
        }
        return false;
    }

    public String guardarPlan() {
        try {
            asegurarArchivo();

            // Se escribe ADELANTE (head -> tail) para que el orden se conserve al cargar
            StringBuilder texto = new StringBuilder();
            NodoDoble<Materia> nodo = plan.getMaterias().getHead();
            while (nodo != null) {
                texto.append(nodo.getDato().getCodigo()).append("\n");
                nodo = nodo.getSiguiente();
            }

            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO_PLAN))) {
                escritor.write(texto.toString());
            }
            return "Se guardaron " + plan.getMaterias().size() + " materias en " + ARCHIVO_PLAN;
        } catch (IOException e) {
            return "No se pudo guardar: " + e.getMessage();
        }
    }

    public String cargarPlan() {
        // Vaciar antes de leer: cargar dos veces no duplica
        while (plan.getMaterias().getHead() != null) {
            plan.getMaterias().eliminar(plan.getMaterias().getHead());
        }

        try {
            if (asegurarArchivo()) {
                return "No existía " + ARCHIVO_PLAN + "; se creó vacío y se inicia con el plan vacío";
            }

            try (BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO_PLAN))) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    if (linea.trim().isEmpty()) {
                        continue;
                    }
                    String[] campos = linea.split(SEPARADOR);
                    if (campos.length != 1) {
                        continue; // campos de más: se omite
                    }
                    Materia materia = materiaService.buscarMateria(campos[0].trim());
                    if (materia == null) {
                        continue; // la materia no existe: no se puede enlazar
                    }
                    plan.getMaterias().insertarAlFinal(materia); // mismo orden del archivo
                }
            }
        } catch (IOException e) {
            return "No se pudo leer: " + e.getMessage();
        }

        return "Se cargaron " + plan.getMaterias().size() + " materias en el plan";
    }
}