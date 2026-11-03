package service;

import model.domain.Materia;
import model.structures.ListaSimple;

public class MateriaService {
    private final ListaSimple<Materia> materias = new ListaSimple<>();

    public void registrarMateria(String codigo, String nombre, int creditos) {
        materias.insertarFinal(new Materia(nombre, codigo, creditos));
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
    @Override
public String toString() {
    return codigo + " - " + nombre + " (" + creditos + " créditos)";
}
}