/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

/**
 *
 * @author jonyj
 * @param <T>
 */
public class ListaEnlazada<T> extends Lista<T> {
     NodoLineal<T> nodo;
     public ListaEnlazada() throws UtileriasException
     {
         this.TamanioTotal=0;
     }
    @Override
    public Double Promedio() throws UtileriasException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Imprime() {
        NodoLineal temp=this.nodo;
        if(temp==null)
           UConsole.Imprime.Imprimir("La lista esta vacia...");
        while(temp!=null)
        {
            UConsole.Imprime.Imprimir("|"+temp.Get());
            temp=temp.hijo;
        }
    }

    @Override
    public void Ordena(Ordenacion metodo) throws UtileriasException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Random(int Longuitud) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Random(int desde, int hasta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
     @Override
    public void Agregar(T valor)
    {
        if(this.nodo==null)
        {
            this.nodo=new NodoLineal<>(valor);
            this.TamanioTotal=0;
            return;
        }
        NodoLineal nuevo = new NodoLineal<>(valor); 
        nuevo.hijo = this.nodo; 
        this.nodo = nuevo; 
        this.TamanioTotal++;
    }
     @Override
    public void Insertar(int indice,T valor)
    {
        
    }
    @Override
    public void Reemplazar(int indiceOriginal,Object nuevoValor)
    {
        
    }
    @Override
    public void EliminarEn(int indice) throws UtileriasException
    {
        if(indice>this.TamanioTotal)
        {
            throw new UtileriasException("No existe ningun elemento en la posición de ->"+indice);
        }
        if(indice==0){
            this.nodo=null;
            return;
        }
        NodoLineal temp=this.nodo;
        while(indice>1)
        {
            temp=temp.hijo;
            indice--;
        } 
        temp.hijo=temp.hijo.hijo;
        this.TamanioTotal--;
    }
    @Override
    public void Eliminar(T valor) throws UtileriasException
    {
        int indice=PosicionDe(valor);
        while(indice!=-1)
        {
            EliminarEn(indice);
            indice=PosicionDe(valor);
        }
    }
    @Override
    public int PosicionDe(T valor)
    {
        NodoLineal temp=this.nodo;
        int i=0;
        while(temp!=null)
        {
            if(temp.Get().equals(valor))
            {
                return i;
            }
            temp=temp.hijo;
            i++;
        }
        return -1;
    }
    @Override
    public Boolean Cotiene(T valor)
    {
        return PosicionDe(valor)>-1;
    }
}
