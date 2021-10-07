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
public abstract class Arbol<T> extends Estructura {
    @Override
    public abstract void Imprime() ;

    @Override
    public abstract void Ordena(Ordenacion metodo) throws UtileriasException;

    @Override
    public abstract void Random(int Longuitud);

    @Override
    public abstract void Random(int desde, int hasta);

    @Override
    public abstract void Convierte(Estructura estructura) throws UtileriasException;
    //To change body of generated methods, choose Tools | Templates.

}
