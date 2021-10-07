/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

/**
 *
 * @author UDI
 * @param <T>
 */
public class NodoDoble<T> extends Nodo<T> {

    protected NodoDoble Izquierdo; //ex-hijo

    public NodoDoble Izquierdo() {
        return Izquierdo;
    }
    protected NodoDoble Derecho;   //ex-padre

    public NodoDoble Derecho() {
        return Derecho;
    }

    public NodoDoble() {
        this.Izquierdo = null;
        this.Derecho = null;
    }

    public NodoDoble(T valor) {
        this.Valor = valor;
        this.Izquierdo = null;
        this.Derecho = null;
    }

    public NodoDoble(NodoDoble Derecho, T Valor) {
        this.Izquierdo = null;
        this.Derecho = Derecho;
        this.Valor = Valor;
    }

    public T Get() {
        return Valor;
    }

}
