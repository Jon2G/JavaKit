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
public class ColaCircular<T> extends Cola {
    int IndiceInsertar=-1;
    int IndiceExtraer=-1;
    int disponibles;
    public ColaCircular(int tamanio) throws UtileriasException {
        super(tamanio, Estructura.TipoCola.FIFO);
        this.disponibles=this.TamanioTotal;
    }
    @Override
    public void Insertar(Object valor) throws UtileriasException
    {
        if(this.disponibles<=0)
            throw new UtileriasException("La cola esta llena");
        
        this.cola.Set(valor,(++IndiceInsertar%this.TamanioTotal));
        this.disponibles--;
    }
    @Override
    public T Extraer() throws UtileriasException
    {               
        if(this.disponibles+1>this.TamanioTotal)
            throw new UtileriasException("La cola esta vacia.");  
        
        T valor;
        valor=(T)this.cola.Get( (++IndiceExtraer%this.TamanioTotal));
        this.disponibles++;
        //
        this.cola.Set(0, IndiceExtraer%this.TamanioTotal);
        //       
        return valor;
    }
    
}
