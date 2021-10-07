/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

/**
 *
 * @author jonyj
 */
public class Extensiones {

    /**
     *Intenta convertir un objeto a un valor entero,retorna una tupla cuyos valores representan
     * el valor convertido(si fue exitosa la convesión) y un Booleano que indica si la conversión fue exitosa
     * @param someText
     * @return
     */
    public static Tupla<Integer,Boolean> TryParseInt(Object someText) {
        try {
            return new Tupla<>(Integer.parseInt(someText.toString()),true);
        } catch (NumberFormatException ex) {
            return new Tupla<>(null,false);
        }
    }
    /**
     *Intenta convertir un objeto a un valor doble,retorna una tupla cuyos valores representan
     * el valor convertido(si fue exitosa la convesión) y un Booleano que indica si la conversión fue exitosa
     * @param someText
     * @return
     */
    public static Tupla<Double,Boolean> TryParseDouble(Object someText) {
        try {
            return new Tupla<>(Double.parseDouble(someText.toString()),true);
        } catch (NumberFormatException ex) {
            return new Tupla<>(null,false);
        }
    }

    /**
     *Intenta sumar dos objetos de una estructura
     * @param operador1
     * @param operador2
     * @return
     * @throws UtileriasException
     */
    protected static Object Suma(Object operador1,Object operador2) throws UtileriasException
    {
        if(operador1==null)
            return operador2;
        Object a=operador1;
        Object b=operador2;
        Class<?> clase=operador1.getClass();
        Class<?> _clase=operador2.getClass();
        if(clase!=_clase)
            throw new UtileriasException("No se puede calcular el promedio de tipos de datos diferentes ->"
                    +clase.getName()+" y "+_clase.getName());
        if(clase==Integer.class)
            return (((int)a)+((int)b));
        if(clase==double.class)
            return (double)a+(double)b;
        if(clase==Character.class)
            return (((int)(char)a)+((int)(char)b));
        if(clase==String.class)
        {
            return(((String)a).concat((String)b)); 
        }
        throw new UtileriasException("Tipo de dato '"+clase.getName()+"'-'"+_clase.getName()+"' no soportado para la operación Suma...");
    }
        /**
     *Intenta dividir un objeto de una estructura entre un valor entero
     * @param valorAcumulado
     * @param TotalElementos
     * @param operador1
     * @return
     * @throws UtileriasException
     */
    protected static Object Divide(Object valorAcumulado,int TotalElementos) throws UtileriasException
    {
        if(valorAcumulado==null)
            return valorAcumulado;
        Object a=valorAcumulado;
        Class<?> clase=a.getClass();
        if(clase==Integer.class)
            return (((int)a)/TotalElementos);
        if(clase==double.class)
            return (double)a/(double)TotalElementos;
        if(clase==Character.class)
            return (((int)(char)a)/TotalElementos);
        throw new UtileriasException("Tipo de dato '"+clase.getName()+"'-'"+TotalElementos+"' no soportado para la operación Divide...");
    }
    protected static Object Promedia(Object valorAcumulado,int TotalElementos) throws UtileriasException
    {
        Class<?> clase=valorAcumulado.getClass();
        if(clase==Integer.class)
        {
            return Divide(valorAcumulado,TotalElementos);
        }
        return null;
    }
    /**
     *Una tupla de Objetos
     * @param <X>
     * @param <Y>
     */
    public static class Tupla<X, Y> {
        public final X x;
        public final Y y;
        public Tupla(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }

}
