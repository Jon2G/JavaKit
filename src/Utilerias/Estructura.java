/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

import static Utilerias.Extensiones.Promedia;
import static Utilerias.Extensiones.Suma;

/**
 *
 * @author jonyj
 * @param <T>
 */
public abstract class Estructura<T> {
    int indiceActual;
    int TamanioTotal;
    public enum Ordenacion {
    BURBUJA, MEZCLA ,QUICKSORT,INSERCION,SELECCION,SHELL
    }
    public enum TipoCola {
    LIFO, FIFO 
    }
    public enum TipoPila
    {
        LIFO,FIFO
    }
    public enum TipoLista
    {
        Circular,Lineal
    }
    public enum RecorridoArbol
    {
        In_Orden,Pre_Orden,Post_Orden
    }
    public Estructura(){
        indiceActual=0;
    }
    public Integer IndiceActual() { return indiceActual; }

    /**
     *Retorna el indice actual de la instancia que indica la posición
     * @return Indice actual
     */
    public int getIndiceActual()
    {
        return indiceActual;
    }

    /**
     *Calcula el promedio de los valores no nulos contenidos en la estructura
     * @return Promedio
     * @throws UtileriasException
     */
    public T Promedio() throws UtileriasException
    {   
        switch(this.getClass().getName())
        {
            case "Utilerias.Arreglo":
                T promedio=null;
                 Arreglo<T> arreglo=(Arreglo<T>)this;
                for(int i=0;i<arreglo.TamanioTotal;i++)
                {
                    if(arreglo.arreglo[i]==null){continue;}
                    promedio=(T)Suma(promedio,arreglo.arreglo[i]);   
                }
                return (T)Promedia(promedio,this.TamanioTotal);
            case "Utilerias.Cola":
                Cola<T> cola=(Cola<T>)this;
                return (T)cola.Promedio();
            case "Utilerias.Lista":
                Lista<T> lista=(Lista<T>)this;
               return (T)lista._Arreglo.Promedio();
            case "Utilerias.Pila":
                Pila<T> pila=(Pila<T>)this;
                return (T)pila.Promedio();
            default:
                throw new UtileriasException("Promedio no soportado para la estructura:"+this.getClass().getName());
        }
    }
    /**
     *Imprime los elementos de la estructura...
     */
    public abstract void Imprime();

    /**
     *Intenta convertir una estructura a otra
     * @param estructura estructura de donde se tomaran los valores para la nueva estructura actual convertida
     * @throws Utilerias.UtileriasException
     */
    public abstract void Convierte(Estructura estructura) throws UtileriasException;

    /**
     *Elimina todos los elementos de la estructura
     */
    public abstract void Limpiar();
//    public abstract void Set(Object valor,int... posiciones) throws UtileriasException;
//    public abstract Object Get(int... posicion) throws UtileriasException;

    /**
     *Ordena la estructura por el metodo seleccionado
     * @param metodo Metodo de ordenación
     * @throws UtileriasException
     */
    public abstract void Ordena(Ordenacion metodo)  throws UtileriasException;
//    public abstract void RemoverEn(int posicion) throws UtileriasException;

    /**
     *Rellena la estructura con valores aleatorios
     */
    public abstract void Random(int Longuitud)  throws UtileriasException;

    /**
     *Rellena la estructura con valores aleatorios enteros dentro de un rango en especifico
     * @param desde
     * @param hasta
     */
    public abstract void Random(int desde,int hasta);
    public abstract boolean Contiene(T valor) throws UtileriasException;
    protected void Burbuja(Object[] arreglo) throws UtileriasException
    {
        Object temporal;
        for(int i=0;i<arreglo.length;i++){
            for(int j=0;j<arreglo.length;j++)
            {
                if(MenorQue(arreglo[i],arreglo[j]))
                {
                    temporal=arreglo[j];
                    arreglo[j]=arreglo[i];
                    arreglo[i]=temporal;
                }
            }
        }   
    }
    protected T[] Mezcla(Object[] arreglo,int inicio,int fin)  throws UtileriasException
    {
        int n1;
        int n2;
        Object[] salida=arreglo;
        if(fin>1)
        {
            n1=fin/2;
            n2=fin-n1;
            Mezcla(arreglo,inicio,n1);
            Mezcla(arreglo,inicio+n1,n2);
            salida=merge(arreglo,inicio,n1,n2);
        }
        return (T[])salida;
    }
    private T[] merge(Object[] matrix,int init,int n1,int n2) throws UtileriasException
    {
        Object[] buffer=new Object[n1+n2];
        int temp=0;
        int temp1=0;
        int temp2=0;
        int i;
        while((temp1<n1)&&(temp2<n2))
        {
            if(MenorQue(matrix[init+temp1],matrix[init+n1+temp2]))
             buffer[temp++]=matrix[init+(temp1++)];   
            else
             buffer[temp++]=matrix[init+n1+(temp2++)];   
        }
        while(temp1<n1)
            buffer[temp++]=matrix[init+(temp1++)];
        while(temp2<n2)
           buffer[temp++]=matrix[init+n1+(temp2++)];
        for(i=0;i<n1+n2;i++)
            matrix[init+i]=buffer[i];
        return (T[])matrix;
    }
    protected T[] QuickSort(Object[] arreglo,int izquierda,int derecha) throws UtileriasException
    {
        //1. Elegir el pivote
        Object pivote = arreglo[izquierda];
        //2. Los elementos > al pivote van a su derecha, los < a su izquierda
        //esta parte de la implementación es el corazón del ordenamiento
        //se utilizan variables auxiliares:
        //- i para controlar los elementos a la izquierda del pivote
        //- j para controlar los elementos a la derecha del pivote
        int i = izquierda;
        int j = derecha;
        //mientras que deban evaluarse los elementos en el arreglo
        //para ubicar al nuevo pivote
        while (i < j) {
            //mientras que el elemento vector[i] sea menor o igual al pivote
            //se aumenta el valor de i
            //cuando este loop se detenga, el elemento en vector[i]
            //es mayor a pivote y deberá ir a su derecha
            while (MenorQueoIgual(arreglo[i],pivote)&& i < j) {
                i++;
            }
            //mientras que el elemento vector[j] sea mayor al pivote
            //se desminuye el valor de j
            //cuando este loop se detenga, el elemento en vector[j]
            //es menor o igual a pivote y deberá ir a su izquierda
            while (MayorQue(arreglo[j],pivote)) {
                j--;
                if(j<0){j=0;break;}
            }
            //siempre y cuando i sea menor a j, se hace un cambio de los elementos
            //puesto que el elemento en vector[i] debe ir a la derecha
            //y vector[j] a la izquierda
            if (MenorQue(i,j)) {
                Object auxIntercambio = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = auxIntercambio;
            }
        }
        //Por los ciclos anteriores, j llegó a una posición donde su elemento (i.e. vector[j]) 
        //es menor o igual al pivote, actualizamos entonces la posición del pivote, mandando vector[j] 
        //a la ubicación del pivote y viceversa (el pivote a la posicion vector[j])
        arreglo[izquierda] = arreglo[j];
        arreglo[j] = pivote;
        //3. Para A1 y A2, aplicar el mismo proceso.
        if (izquierda < j - 1) {
            //quicksort aplicado a A1
            QuickSort(arreglo, izquierda, j - 1);
        }
        if (j + 1 < derecha) {
            //quicksort aplicado a A2
            QuickSort(arreglo, j + 1, derecha);
        }
     return (T[])arreglo;
    }
    protected T[] InsercionDirecta(Object[] arreglo) throws UtileriasException
    {
        int p, j;
        T aux;
        for (p = 1; p <arreglo.length; p++){ // desde el segundo elemento hasta
            aux = (T)arreglo[p]; // el final, guardamos el elemento y
            j = p - 1; // empezamos a comprobar con el anterior
              while ((j >= 0) &&MenorQue(aux,(T)arreglo[j]))// mientras queden posiciones y el
              { // valor de aux sea menor que los de la izquierda, se desplaza a la derecha
                  arreglo[j + 1] = arreglo[j];       
                  j--;                   
              }
              arreglo[j + 1] = aux; // colocamos aux en su sitio
        }
        return (T[])arreglo;
    }
    protected T[] Seleccion(Object[] arreglo) throws UtileriasException
    {
          int i, j,pos;
          T menor, tmp;
          for (i = 0; i < arreglo.length - 1; i++) { // tomamos como menor el primero
                menor = (T)arreglo[i]; // de los elementos que quedan por ordenar
                pos = i; // y guardamos su posición
                for (j = i + 1; j < arreglo.length; j++){ // buscamos en el resto
                      if (MenorQue(arreglo[j],menor)) { // del array algún elemento
                          menor = (T)arreglo[j]; // menor que el actual
                          pos = j;
                      }
                }
                if (pos != i){ // si hay alguno menor se intercambia
                    tmp = (T)arreglo[i];
                    arreglo[i] = arreglo[pos];
                    arreglo[pos] = tmp;
                }
          }
          return (T[])arreglo;
    }
    protected T[] Shell(Object[] arreglo) throws UtileriasException
    {
		int inta, i;
                T aux;
		boolean band;
		inta = arreglo.length;
		while(inta > 0){
			inta = inta / 2;
			band = true;
			while(band){
				band = false;
				i = 0;
				while ((i+inta) <=arreglo.length-1){//2.1.1
					if (MayorQue(arreglo[i],arreglo[i + inta])){
                                            aux = (T)arreglo[i];
                                            arreglo[i] = arreglo[i+inta];
                                            arreglo[i+inta] = aux;
                                            band = true;
					}
					i = i +1;
				}
			}
		}
        return (T[])arreglo;
    }
    protected Boolean MenorQue(Object a,Object b) throws UtileriasException
    {
        if(IgualQue(a,b))
        {
            return false;
        }
        if(a==null||b==null){return false;}
        Class<?> clase=a.getClass();
        Class<?> _clase=b.getClass();
        if(clase!=_clase){return false;}
        if(clase==Integer.class)
            return (((int)a)<((int)b));
        if(clase==double.class)
            return (double)a<(double)b;
        if(clase==Character.class)
            return (((int)(char)a)<((int)(char)b));
        if(clase==String.class)
        {
            if(((String)a).length()!=((String)b).length())
            {
              return(((String)a).length()<((String)b).length()); 
            }
            for(int i=0;i<((String)a).length();i++)
            {
                if(((int)((String)a).charAt(i))!=((int)((String)b).charAt(i)))
                    return ((int)((String)a).charAt(i))<((int)((String)b).charAt(i));
            }
        }
        throw new UtileriasException("Tipo de dato '"+clase.getName()+"'-'"+_clase.getName()+"' no soportado para la operación menor que...");
    }
    protected Boolean MayorQue(Object a,Object b) throws UtileriasException
    {
        if(IgualQue(a,b))
        {
            return false;
        }
        return !MenorQue(a,b);
    }
    protected Boolean MenorQueoIgual(Object a,Object b) throws UtileriasException
    {
        return MenorQue(a,b)||IgualQue(a,b);
    }
    protected Boolean IgualQue(Object a,Object b)
    {
        if(a==null&&b==null){return true;}
        if(a==null||b==null){return false;}
        Class<?> clase=a.getClass();
        Class<?> _clase=b.getClass();
        if(clase!=_clase){return false;}
        if(clase==Integer.class)
        {
            return (((int)a)==((int)b));
        }
        if(clase==double.class)
        {
            return (double)a==(double)b;
        }
        if(clase==String.class)
        {
            if(((String)a).length()!=((String)b).length())
            {
              return false; 
            }
            for(int i=0;i<((String)a).length();i++)
            {
                if(((int)((String)a).charAt(i))!=((int)((String)b).charAt(i)))
                    return false;
            }
            return true;
        }
        return false;
    }
    
}
