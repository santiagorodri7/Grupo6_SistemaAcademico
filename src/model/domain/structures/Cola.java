package model.domain.structures;
import java.util.NoSuchElementException;

public class Cola<T>{
    private Nodo<T> frente;
    private Nodo<T> fin;
    int tamanio;
    
    public void enqueue(T dato){
        if(dato==null){
            throw new IllegalArgumentException();
        }
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
            throw new NoSuchElementException("La cola está vacía");
        }
        T dato = frente.getDato();
        frente = frente.getSiguiente();
        if(frente==null){
            fin=null;
        }
        tamanio--;
        return dato;
    }

    public T front(){
        if (isEmpty()) throw new NoSuchElementException("La cola está vacía");            
        return frente.getDato();
    }

    public boolean isEmpty(){
        return this.frente == null; 
    }

    public int size(){
        return tamanio;
    }
}