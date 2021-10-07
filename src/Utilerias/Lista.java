/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonyj
 * @param <T>
 */
public class Lista<T> extends Estructura {

    protected Estructura.TipoLista TipoLista;

    /**
     * Imprime la lista con el metodo generico
     */
    @Override
    public void Imprime() {
        UConsole.Imprime.Imprimir(this);
    }

    /**
     * Ordena los valores de la lista por el metodo seleccionado
     *
     * @throws Utilerias.UtileriasException
     */
    @Override
    public void Ordena(Ordenacion metodo) throws UtileriasException {
        this._Arreglo.Ordena(metodo);
    }

    @Override
    public void Random(int Longuitud) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Random(int desde, int hasta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //////////LISTA METODOS   
    Arreglo _Arreglo;
    public int CuantosElementos;

    public Lista() {
        this._Arreglo = new Arreglo<>();
    }

    public Lista(T... valores) {
        this._Arreglo = new Arreglo<>();
        for (T valor : valores) {
            this.Agregar(valor);
        }
    }

    public void Agregar(T valor) {
        try {
            if (this.TamanioTotal <= this.indiceActual) {
                this._Arreglo.Redimensionar((this.TamanioTotal + 1) * 2);
                this.TamanioTotal = this._Arreglo.TamanioTotal;
            }
            this._Arreglo.Set(valor, this.indiceActual);
            this.indiceActual++;
            this.CuantosElementos++;
        } catch (UtileriasException ex) {
            UtileriasException.Mostrar(ex);
        }
    }

    public void Agregar(T[] valores) {
        try {
            for (T valor : valores) {
                if (valor == null) {
                    continue;
                }
                if (this.TamanioTotal <= this.indiceActual) {
                    this._Arreglo.Redimensionar((this.TamanioTotal + 1) * 2);
                    this.TamanioTotal = this._Arreglo.TamanioTotal;
                }
                this._Arreglo.Set(valor, this.indiceActual);
                this.indiceActual++;
                this.CuantosElementos++;
            }
        } catch (UtileriasException ex) {
            UtileriasException.Mostrar(ex);
        }
    }

    public void Anteponer(T valor) {

    }

    public void Insertar(int indice, T valor) throws UtileriasException {

    }

    /**
     * Retorna la primer posicion de un elemento en la lista
     *
     * @param valor Elemento a buscar
     * @return Indice base cero del elemento en caso de encontrarlo (-1 si no
     * existe)
     * @throws Utilerias.UtileriasException
     */
    public int PosicionDe(T valor) throws UtileriasException {
        return this._Arreglo.PosicionDe(valor);
    }

    public Boolean Cotiene(T valor) throws UtileriasException {
        return PosicionDe(valor) > -1;
    }

    public void Reemplazar(int indiceOriginal, Object nuevoValor) {

    }

    public void EliminarEn(int indice) throws UtileriasException {
        throw new UtileriasException("No existe ningun elemento en la posición de ->" + indice);
    }

    /**
     * Retorna el elemento de la Lista en la posicion indicada
     *
     * @param indice
     * @return
     * @throws UtileriasException
     */
    public T Obtener(int indice) throws UtileriasException {
        if (indice > this.TamanioTotal) {
            throw new UtileriasException("No existe ningun elemento en la posición de ->" + indice);
        }
        return (T) this._Arreglo.Get(indice);
    }

    /**
     * Elimina todas las apariciones de un valor en la lista
     *
     * @param valor
     * @throws Utilerias.UtileriasException
     */
    public void Eliminar(T valor) throws UtileriasException {
        int indice = PosicionDe(valor);
        while (indice != -1) {
            EliminarEn(indice);
            indice = PosicionDe(valor);
        }
    }

    /**
     * Retorna el elemento siguiente en la lista basandose en el Indice Actual
     *
     * @return
     * @throws UtileriasException
     */
    public T Siguiente() throws UtileriasException {
        if (this.indiceActual + 1 > this.TamanioTotal) {
            throw new UtileriasException("Se alcanzo el final de la lista...");
        }
        return Obtener(this.indiceActual++);
    }

    public T Anterior() throws UtileriasException {
        return null;
    }

    @Override
    public void Limpiar() {
        this.indiceActual = 0;
        this._Arreglo.arreglo = (T[]) new Object[0];
        this.TamanioTotal = 0;
    }

    @Override
    public void Convierte(Estructura estructura) throws UtileriasException {
        Lista<T> lista;
        switch (estructura.getClass().getName()) {
            case "Utilerias.Lista":
                lista = (Lista<T>) estructura;
                this.Limpiar();
                for (int i = 0; i < lista.CuantosElementos; i++) {
                    Agregar(lista.Obtener(i));
                }
                break;
            case "Utilerias.Arreglo":
                Arreglo<T> arreglo = (Arreglo<T>) estructura;
                this.Limpiar();
                this.Agregar(arreglo.arreglo);
                break;
            default:
                throw new UtileriasException("Conversion de:" + estructura.getClass().getName() + " a Lista no soportada");
        }
    }

    @Override
    public boolean Contiene(Object valor) {
        try {
            return PosicionDe((T) valor) >= 0;
        } catch (UtileriasException ex) {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
