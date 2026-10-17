package model.domain.structures;

public class Pila<T> {
    private Nodo<T> tope;
    private int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    public void push(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor, tope);
        tope = nuevo;
        tamanio++;
    }

    public T pop() {
        if (isEmpty())
            throw new IllegalStateException("La pila esta vacia");
        T valor = tope.getDato();
        tope = tope.getSiguiente();
        tamanio--;
        return valor;
    }

    public T peek() {
        if (isEmpty())
            throw new IllegalStateException("La pila esta vacia");
        return tope.getDato();
    }

    public int size() {
        return tamanio;
    }

    public boolean isEmpty() {
        return this.tope == null;
    }
}