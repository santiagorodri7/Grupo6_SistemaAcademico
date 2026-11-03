package service;

import java.io.IOException;

import model.domain.Materia;
import model.domain.PlanDeEstudios;
import model.structures.ListaSimple;
import model.structures.NodoDoble;

public class PlanService {
    private final PlanDeEstudios plan = new PlanDeEstudios("Plan de estudios");
    private final MateriaService materiaService;
    private final ArchivoService archivo = new ArchivoService("data/plan.txt");

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



    public String guardarPlan() {
        try {
          
            ListaSimple<String> lineas = new ListaSimple<>();
            NodoDoble<Materia> nodo = plan.getMaterias().getHead();
            while (nodo != null) {
                lineas.insertarFinal(nodo.getDato().getCodigo());
                nodo = nodo.getSiguiente();
            }
            archivo.escribirLineas(lineas);
            return "Se guardaron " + plan.getMaterias().size() + " materias en " + archivo.getRuta();
        } catch (IOException e) {
            return "No se pudo guardar: " + e.getMessage();
        }
    }

    public String cargarPlan() {
   
        while (plan.getMaterias().getHead() != null) {
            plan.getMaterias().eliminar(plan.getMaterias().getHead());
        }

        try {
            boolean existia = archivo.existe();
            ListaSimple<String> lineas = archivo.leerLineas(); 
            if (!existia) {
                return "No existía " + archivo.getRuta() + "; se creó vacío y se inicia con el plan vacío";
            }

            for (int i = 0; i < lineas.getTamanio(); i++) {
                String[] campos = lineas.buscarPorInidice(i).split(ArchivoService.SEPARADOR);
                if (campos.length != 1) {
                    continue; 
                }
                Materia materia = materiaService.buscarMateria(campos[0].trim());
                if (materia == null) {
                    continue; 
                }
                plan.getMaterias().insertarAlFinal(materia); 
            }
        } catch (IOException e) {
            return "No se pudo leer: " + e.getMessage();
        }

        return "Se cargaron " + plan.getMaterias().size() + " materias en el plan";
    }
}