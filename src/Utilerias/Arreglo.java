/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonyj
 * @param <T>
 */
public class Arreglo<T> extends Estructura implements Cloneable   {
    T arreglo[];
    int TamaniosPorDimension[];
   /**
     *Crea un Estructura de n dimensiones de Cualquier tamaño
     * @param tamanio Tamaños de las dimensiones del arreglo
    */  
    public Arreglo(int ... tamanio)
    {
        TamanioTotal=1;
        TamaniosPorDimension=new int[tamanio.length];
        for(int i=0;i<tamanio.length;i++){
            this.TamaniosPorDimension[i]=tamanio[i];
            TamanioTotal*=(tamanio[i]); //
        }
        this.arreglo=(T[])new Object[TamanioTotal];
        //throw new UtileriasException("Tipo de dato no soportado para el arreglo!");
    }   
    /**
     *Crea una instancia de la clase basandose en un arreglo existente
     * @param arreglo Arreglo base para crear la instancia de clase
     */
    public Arreglo(T[] arreglo)
    {
        this.arreglo=arreglo;
        this.TamanioTotal=arreglo.length;
        this.TamaniosPorDimension=new int[]{this.TamanioTotal};
    }
    public T[] ToArray()
    {
        return this.arreglo;
    }
    @Override
    public Object clone() throws CloneNotSupportedException 
    { 
        Arreglo<T> arreglo=new Arreglo<>(this.TamanioTotal);
        for(int i=0;i<this.TamanioTotal;i++)
        {
            try {
                arreglo.Set(this.Get(i),i);
            } catch (UtileriasException ex) 
            {
                UtileriasException.Mostrar(ex);
            }
        }
        return arreglo;
    } 
    /**
     *Establece el indice que indica la posicion actual en el arreglo
     * @param x
     * @throws UtileriasException 
     */
    public void IndiceActual (Integer x) throws UtileriasException{
        if(x>=this.TamanioTotal){
            throw new UtileriasException("El valor de:"+x+" no es valido para IndiceActual");

        }
          this.indiceActual = x;
    }
    /**
     *Inserta elemento en un arreglo auto incrementando la posicion
     * 
     * @param elemento
     * @return Falso cuando el arreglo esta lleno y verdadero cuando se pude continuar insertando
     * @throws UtileriasException
     */
    public Boolean Insertar(T elemento) throws UtileriasException
    {
        T[] temp=(T[])new Object[this.arreglo.length+1];
        System.arraycopy(arreglo, 0, temp, 0, arreglo.length);
        temp[this.arreglo.length]=elemento;
        this.arreglo=temp;
        this.indiceActual=0;
        this.TamanioTotal++;
        this.TamaniosPorDimension[this.TamaniosPorDimension.length-1]=this.arreglo.length;
        return (this.indiceActual<this.TamanioTotal);
    }
    public int PosicionDe(T elemento)
    {
        for(int i=0;i<this.TamanioTotal;i++)
        {
            if(this.arreglo[i]==elemento)
            {
                return i;
            }
        }
        return -1;
    }
     @Override
    public void Imprime(){
        for(int i:this.TamaniosPorDimension)
        {
            for(int j=0;j<i;j++)
            {
                UConsole.Imprime.Imprimir("|"+(this.arreglo[j]==null? 0:this.arreglo[j]));
            }
           UConsole.Imprime.Imprimir("|\n");
        }     
    }
    public void Set(T valor,int... posiciones) throws UtileriasException
    {
        int localidad=1;
        for(int x:posiciones)
        {
           localidad*=x;
        }
        if(localidad<0||localidad>this.TamanioTotal)
        {
            throw new UtileriasException("Indice fuera de los limites de la matriz");
        }
        this.arreglo[localidad]=valor;
    }
    public T Get(int... posiciones) throws UtileriasException
    {
        int localidad=1;
        for(int x:posiciones)
        {
           localidad*=x;
        }
         if(localidad<0||localidad>this.TamanioTotal)
        {
            throw new UtileriasException("Indice fuera de los limites de la matriz");
        }
        return this.arreglo[localidad];
    }
    public void RemoverEn(int posicion) throws UtileriasException
    {
        if(posicion<0||posicion>this.TamanioTotal)
        {
            throw new UtileriasException("Indice fuera de los limites de la matriz");
        }
        this.TamanioTotal--;
        for(int i=0;i<this.TamaniosPorDimension.length;i++)
        {
            if(this.TamaniosPorDimension[i]>=posicion)
            {
                this.TamaniosPorDimension[i]--;
                break;
            }
        }
        this.indiceActual=0;
        T[] temp=(T[])new Object[this.TamanioTotal];
        int j=0;
       for(int i=0;i<this.arreglo.length;i++)
       {
           if(i==posicion){continue;}
           temp[j]=this.arreglo[i];
           j++;
       }
       this.arreglo=temp;
    }
    @Override
    public void Ordena(Ordenacion metodo)throws UtileriasException
    {
        switch(metodo)
        {
            case BURBUJA:
                Burbuja(this.arreglo);
                break;
            case MEZCLA:
               this.arreglo=(T[])Mezcla((Object[])this.arreglo,0,this.arreglo.length);
                break;
            case QUICKSORT:
                this.arreglo=(T[])QuickSort((Object[])this.arreglo,0,this.arreglo.length-1);
                break;
            case INSERCION:
                this.arreglo=(T[])InsercionDirecta((Object[])this.arreglo);  
                break;
            case SELECCION:
                 this.arreglo=(T[])Seleccion((Object[])this.arreglo);
                 break;
            case SHELL:
                 this.arreglo=(T[])Shell((Object[])this.arreglo);
                 break;

        }
    }
    @Override
    /**
     *Rellena el arreglo con valores aleatorios segun su tipo
     * String Longitud se toma como el tamaño de palabra alteatoria
     */
    public void Random(int Longuitud) throws UtileriasException
    {
        T tipo=this.arreglo[0];
        String ttipo= tipo.getClass().getName();
        
        switch(ttipo)
        {
            case "java.lang.Integer":
                for(int i=0;i<this.arreglo.length;i++)
                {
                    arreglo[i]=(T)(Object)UConsole.Aleatorio();
                }
                break;
            case "java.lang.String":
                for(int i=0;i<this.arreglo.length;i++)
                {
                    String palabra="";
                    for(int j=0;j<Longuitud;j++)
                    {
                        palabra+=(char)UConsole.Aleatorio(65,90);
                    }
                    arreglo[i]=(T)palabra;
                }
                break;
            default:
                throw new UtileriasException("El metodo Random no esta disponible para el tipo de dato'"+tipo+"'");

        }

    }
    @Override
    /**
     *Rellena el arreglo con valores aleatorios enteros
     */
    public void Random(int desde,int hasta)
    {
        for(int i=0;i<this.arreglo.length;i++)
        {
            arreglo[i]=(T)(Object)UConsole.Aleatorio(desde,hasta);
        }
    }
    public void Redimensionar(int NuevoTamanio)
    {
        this.arreglo = Arrays.copyOf(this.arreglo,NuevoTamanio);
        this.TamanioTotal=NuevoTamanio;
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
    public boolean Contiene(Object valor) throws UtileriasException {
        return PosicionDe((T)valor)>=0;
    }
}
