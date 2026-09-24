package domain.model.structures;

import java.util.NoSuchElementException;

public class List<T> {
    private Nodo<T> head;
    private int tamanio;

    public void Lista(){
        head = null;
        tamanio =0;

    }
    public void insertarInicio(T dato){
        Nodo<T> nuevoNodo = new Nodo<>(dato, head);
        head = nuevoNodo;
        tamanio++;
    }
    public void insertarFinal(T dato){
        Nodo<T> nuevoNodo = new Nodo<>(dato, head);
        if(estaVacia()){
            head = nuevoNodo;
        } else {
            Nodo<T> actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();                
            }
            actual.setSiguiente(nuevoNodo);
            nuevoNodo.setSiguiente(null);
        }
        this.tamanio++;
    }

    public void insertarIndice(T dato, int indice){
        Nodo<T> nuevoNodo = new Nodo<>(dato, head);
        if(indice == 0){
            insertarInicio(dato);
            return;
        } else if (indice == tamanio){
            insertarFinal(dato);
            return;
        } else {
            Nodo<T> anterior = head;
            for(int i = 0; i<indice-1; i++){
                anterior = anterior.getSiguiente();
            }
            nuevoNodo.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente(nuevoNodo);
            tamanio++;
        }
    }

    public T buscarPorInidice(int indice){
        if(indice >= tamanio || indice<0){
            throw new IndexOutOfBoundsException("Indice fuera del rango");
        } 
        Nodo<T> actual = head;
        for(int i = 0; i<indice-1; i++){
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    public boolean buscarPorValor(T valor){
        Nodo<T> actual = head;
        while(actual != null){
            if(actual.getDato().equals(valor)){
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public void eliminarInicio() {
        if (estaVacia()) {
            throw new NoSuchElementException("No hay elementos para eliminar");
        }
        head = head.getSiguiente(); // el segundo nodo pasa a ser el primero
        tamanio--;
    }

    public void eliminarFinal(){
        if (estaVacia()) {
            throw new NoSuchElementException("No hay elementos para eliminar");
        }
        if(head.getSiguiente()== null){
            head = null;
            tamanio--;
            return;
        }
        Nodo<T> actual = head;
        while(actual.getSiguiente().getSiguiente() != null){
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(null);
        tamanio--;
    }

    public boolean eliminarPorValor(T valor){
        if (estaVacia()) {
            return false;
        } 
        if(head.getDato().equals(valor)){
            eliminarInicio();
            return true;
        }
        Nodo<T> actual = head;
        Nodo<T> anterior = head;
        while (actual != null) {
            if(actual.getDato().equals(valor)){
                anterior.setSiguiente(actual.getSiguiente());
                tamanio--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }







    public int getTamanio() {
        return tamanio;
    }

    public boolean estaVacia() {
        return head == null;
    }

    
}
