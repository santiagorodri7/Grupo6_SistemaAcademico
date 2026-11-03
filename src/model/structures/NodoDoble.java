package model.structures;

public class NodoDoble<T> {
    private T dato;
    private NodoDoble<T> anterior;
    private NodoDoble<T> siguiente;

    public NodoDoble(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public NodoDoble<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble<T> n) {
        this.anterior = n;
    }

    public NodoDoble<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble<T> n) {
        this.siguiente = n;
    }
}