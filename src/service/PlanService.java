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

    private void escribirPlan() throws IOException {
        // Crear la carpeta data/ si no existe
        File carpeta = new File(ARCHIVO_PLAN).getParentFile();
        if (carpeta != null) {
            carpeta.mkdirs();
        }

        // Una línea por materia, del primero al último (recorriendo los nodos desde getHead):
        // se escribe ADELANTE, si se guarda hacia atrás el archivo queda invertido al cargar
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

        // Dejar el plan vacío antes de leer: sin esto, cargar dos veces duplica las materias
        while (plan.getMaterias().getHead() != null) {
            plan.getMaterias().eliminar(plan.getMaterias().getHead());
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(SEPARADOR);
                if (campos.length != 1) {
                    continue; // campos de más o de menos: se omite la línea
                }
                // El plan guarda solo el código: la materia se busca en MateriaService
                Materia materia = materiaService.buscarMateria(campos[0].trim());
                if (materia == null) {
                    continue; // la materia no existe: no se puede enlazar
                }
                plan.getMaterias().insertarAlFinal(materia); // en el mismo orden del archivo
            }
        } catch (FileNotFoundException e) {
            return "No existe " + ARCHIVO_PLAN + "; se inicia con el plan vacío";
        } catch (IOException e) {
            return "No se pudo leer: " + e.getMessage();
        }

        return "Se cargaron " + plan.getMaterias().size() + " materias en el plan";
    }
}