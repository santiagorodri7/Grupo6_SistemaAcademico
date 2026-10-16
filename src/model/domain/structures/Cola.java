package model.domain.structures;
import java.util.NoSuchElementException;

public class Cola<T>{
    private Nodo<T> front;
    private Nodo<T> back;
    int tamanio;
    public void enqueue(T dato){
        Nodo<T> nuevoDato = new Nodo<T>(dato, null);
        if(isEmpty()){
            front = nuevoDato;
        } else {
            back.setSiguiente(nuevoDato);
        }
        back=nuevoDato;
        tamanio++;
    }
    public T dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        T dato = front.getDato();
        front = front.getSiguiente();
        if(front==null){
            back=null;
        }
        tamanio--;
        return dato;
    }

    public T front(){
        if (isEmpty()) {
            throw new NoSuchElementException();            
        }
        return front.getDato();
    }


    public int size(){
        return tamanio;
    }

    public boolean isEmpty(){
        return front == null; 
    }
}