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
public class Cola<T> extends Estructura {
    Arreglo cola;
    TipoCola tipo;
    public Cola(int tamanio,TipoCola tipoCola) throws UtileriasException {
        this.cola=new Arreglo<>(tamanio);
        this.indiceActual=0;
        this.TamanioTotal=tamanio;
        this.tipo=tipoCola;
    }

    /**
     *Agrega un elemento a la cola
     * @param valor elemento a insertar
     * @throws UtileriasException
     */
    public void Insertar(T valor) throws UtileriasException
    {
        switch(this.tipo)
        {
            case LIFO:
                if(this.indiceActual>(this.cola.TamanioTotal-1))
                    throw new UtileriasException("La cola esta llena");
                this.cola.Set(valor, this.indiceActual);
                break;
            case FIFO:
                if(this.cola.TamanioTotal-this.indiceActual-1<=-1)
                    throw new UtileriasException("La cola esta llena");
                this.cola.Set(valor,this.cola.TamanioTotal-this.indiceActual-1);
                break;
            default:
                this.indiceActual=0;
                throw new UtileriasException("Tipo de dato de cola desconocido");
        }
        this.indiceActual++;
    }
    public T Extraer() throws UtileriasException
    {
        T valor=null;
        for(int i=this.TamanioTotal-1;i>=0;i--)
            if(this.cola.Get(i)!=null){
                valor=(T)cola.Get(i);
                cola.Set(null,i);
                this.indiceActual=i;
                break;
            }
        if(valor == null)
            throw new UtileriasException("La cola esta vacia.");              
        return valor;
    }
    public int Longuitud()
    {
        return this.TamanioTotal;
    }
    @Override
    public void Imprime() {
        this.cola.Imprime();
    }

    @Override
    public void Ordena(Ordenacion metodo) throws UtileriasException {
        this.cola.Ordena(metodo);
    }

    @Override
    public void Random(int Longuitud) throws UtileriasException {
        this.cola.Random(Longuitud);
    }

    @Override
    public void Random(int desde, int hasta) {
        this.cola.Random(desde, hasta);
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
