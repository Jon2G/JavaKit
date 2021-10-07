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
public class ArbolColaCircular<T> extends Arbol {
    int IndiceInsertar=-1;
    int IndiceExtraer=-1;
    int disponibles;
    NodoLineal<T> cola;
    public ArbolColaCircular(int tamanio){
        this.TamanioTotal=tamanio;
        cola=new NodoLineal(tamanio);
        this.disponibles=this.TamanioTotal;
    }
    public void Insertar(T valor) throws UtileriasException
    {
        if(this.disponibles<=0)
            throw new UtileriasException("La cola esta llena");
        
        this.cola.Set(valor,(++IndiceInsertar%this.TamanioTotal));
        this.disponibles--;
    }
    public T Extraer() throws UtileriasException
    {               
        if(this.disponibles+1>this.TamanioTotal)
            throw new UtileriasException("La cola esta vacia.");  
        
        T valor;
        valor=this.cola.Get( (++IndiceExtraer%this.TamanioTotal));
        this.disponibles++;
        //
        this.cola.Set(null, IndiceExtraer%this.TamanioTotal);
        //       
        return valor;
    }
    @Override
    public  void Imprime() 
    {
        for(int j=0;j<this.TamanioTotal;j++)
        {
            UConsole.Imprime.Imprimir("|"+(this.cola.Get(j)));
        }
       UConsole.Imprime.Imprimir("|\n");
    }    

    @Override
    public Double Promedio() throws UtileriasException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void Convierte(Estructura estructura) throws UtileriasException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Limpiar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Contiene(Object arg0) throws UtileriasException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
