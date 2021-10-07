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
public final class NodoLineal<T> extends Nodo<T> {
    public NodoLineal hijo;
    NodoLineal()
    {
        this.hijo=null;
        this.Valor=null;
    }
    protected NodoLineal(T valor)
    {
        this.hijo=null;
        this.Valor=valor;
    }
    NodoLineal(int tamanio)
    {
        NodoLineal Padre=this;
        for(int i=0;i<tamanio;i++)
        {
            Padre.hijo=new NodoLineal();
            Padre=Padre.hijo;
        }
    }
    public void Set(Object valor,int nivel) throws UtileriasException
    {
        if(valor!=null&&this.Valor!=null&&this.Valor.getClass()!=valor.getClass())
            throw new UtileriasException("El tipo de dato no corresponde con el de la cola");
        NodoLineal Padre=this;
        for(int i=0;i<nivel;i++)
        {
            Padre=Padre.hijo;
        }
        Padre.Valor=valor;
    }
    public void Set(T valor) throws UtileriasException
    {
        this.Valor=valor;
    }
    public T Get(int Nivel)
    {
        if(Nivel==0)
        {
            return Get();
        }
        NodoLineal Padre=this;
        for(int i=0;i<Nivel;i++)
        {
            Padre=Padre.hijo;
        }
        return (T)Padre.Valor;
    }
    public T Get()
    {
        return Valor;
    }
}
