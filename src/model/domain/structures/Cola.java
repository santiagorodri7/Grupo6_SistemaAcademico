package model.domain.structures;
import java.util.NoSuchElementException;

public class Cola<T>{
    private Nodo<T> frente;
    private Nodo<T> fin;
    int tamanio;
    
    public void enqueue(T dato){
        Nodo<T> nuevoDato = new Nodo<T>(dato, null);
        if(isEmpty()){
            frente = nuevoDato;
        } else {
            fin.setSiguiente(nuevoDato);
        }
        fin=nuevoDato;
        tamanio++;
    }
    public T dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        T dato = frente.getDato();
        frente = frente.getSiguiente();
        if(frente==null){
            fin=null;
        }
        tamanio--;
        return dato;
    }

    public T frente(){
        if (isEmpty()) {
            throw new NoSuchElementException();            
        }
        return frente.getDato();
    }


    public int size(){
        return tamanio;
    }

    public boolean isEmpty(){
        return frente == null; 
    }
}