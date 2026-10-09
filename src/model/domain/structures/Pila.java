package model.domain.structures;

import java.util.NoSuchElementException;

public class Pila<T> {
    private Nodo<T> tope;
    private int tamanio;

    
    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    public void push(T valor){
        Nodo<T> nuevo = new Nodo<>(valor, tope);
        nuevo.setSiguiente(tope);
        tope = nuevo;
        this.tamanio++;
    }
    public T pop(){
        if(isEmpty())
            throw new IllegalStateException("La pita esta vacia");
        T valor = tope.getDato();
        tope = tope.getSiguiente();
        tamanio--;
        return valor;
    }
    public T peek(){
        if(isEmpty())
            throw new IllegalStateException("La pila esta vacia");
        return tope.getDato();
    }
    public int size(){
        return tamanio;
    }
    public boolean isEmpty(){
        return tamanio==0;
    }
    public Nodo<T> getTope() {
        return tope;
    }
    public void setTope(Nodo<T> tope) {
        this.tope = tope;
    }
    public int getTamanio() {
        return tamanio;
    }
    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }



    







    
}
