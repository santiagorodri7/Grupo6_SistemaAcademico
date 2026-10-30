package model.structures;

import java.util.NoSuchElementException;

public class ListaDoble<T> {
    private NodoDoble<T> head;
    private NodoDoble<T> tail;
    private NodoDoble<T> actual;
    private int tamano;

    public ListaDoble() {
        this.head = null;
        this.tail = null;
        this.actual = null;
        this.tamano = 0;
    }

    public void insertarAlInicio(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento no puede ser null");
        }
        NodoDoble<T> nuevo = new NodoDoble<>(elemento);
        if (isEmpty()) {
            tail = nuevo;
        } else {
            nuevo.setSiguiente(head);
            head.setAnterior(nuevo);
        }
        head = nuevo;
        tamano++;
    }

    public void insertarAlFinal(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento no puede ser null");
        }
        NodoDoble<T> nuevo = new NodoDoble<>(elemento);
        if (isEmpty()) {
            head = nuevo;
        } else {
            tail.setSiguiente(nuevo);
            nuevo.setAnterior(tail);
        }
        tail = nuevo;
        tamano++;
    }

    public NodoDoble<T> buscar(T dato) {
        NodoDoble<T> aux = head;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public T eliminar(NodoDoble<T> nodo) {
        NodoDoble<T> antes = nodo.getAnterior();
        NodoDoble<T> despues = nodo.getSiguiente();
        if (antes == null) {
            head = despues;
        } else {
            antes.setSiguiente(despues);
        }
        if (despues == null) {
            tail = antes;
        } else {
            despues.setAnterior(antes);
        }
        nodo.setAnterior(null);
        nodo.setSiguiente(null);
        if (nodo == actual) {
            actual = null;
        }
        tamano--;
        return nodo.getDato();
    }

    public NodoDoble<T> getHead() {
        return head;
    }

    public T irAlInicio() {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay elementos");
        }
        actual = head;
        return actual.getDato();
    }

    public T irAlFinal() {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay elementos");
        }
        actual = tail;
        return actual.getDato();
    }

    public T siguiente() {
        if (actual == null) {
            return irAlInicio();
        }
        if (actual.getSiguiente() == null) {
            throw new NoSuchElementException("No hay elemento siguiente");
        }
        actual = actual.getSiguiente();
        return actual.getDato();
    }

    public T anterior() {
        if (actual == null) {
            return irAlFinal();
        }
        if (actual.getAnterior() == null) {
            throw new NoSuchElementException("No hay elemento anterior");
        }
        actual = actual.getAnterior();
        return actual.getDato();
    }

    public T retirarActual() {
        if (actual == null) {
            throw new NoSuchElementException("No hay elemento seleccionado");
        }
        return eliminar(actual);
    }

    public String recorrerAdelante() {
        StringBuilder sb = new StringBuilder();
        NodoDoble<T> aux = head;
        while (aux != null) {
            sb.append(aux.getDato()).append("\n");
            aux = aux.getSiguiente();
        }
        return sb.toString();
    }

    public String recorrerAtras() {
        StringBuilder sb = new StringBuilder();
        NodoDoble<T> aux = tail;
        while (aux != null) {
            sb.append(aux.getDato()).append("\n");
            aux = aux.getAnterior();
        }
        return sb.toString();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return tamano;
    }
}