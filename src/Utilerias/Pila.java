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
public class Pila<T> extends Estructura {

    Arreglo pila;
    Estructura.TipoPila tipo;

    public Pila(int tamanio, Estructura.TipoPila tipoPila) throws UtileriasException {
        this.pila = new Arreglo<>(tamanio);
        this.indiceActual = 0;
        this.TamanioTotal = tamanio;
        this.tipo = tipoPila;
    }

    /**
     * Agrega un elemento a la cola
     *
     * @param valor elemento a insertar
     * @throws UtileriasException
     */
    public void Insertar(T valor) throws UtileriasException {
        switch (this.tipo) {
            case LIFO:
                if (this.indiceActual > (this.pila.TamanioTotal - 1)) {
                    throw new UtileriasException("La pila esta llena");
                }
                this.pila.Set(valor, this.indiceActual);
                break;
            case FIFO:
                if (this.pila.TamanioTotal - this.indiceActual - 1 <= -1) {
                    throw new UtileriasException("La pila esta llena");
                }
                this.pila.Set(valor, this.pila.TamanioTotal - this.indiceActual - 1);
                break;
            default:
                this.indiceActual = 0;
                throw new UtileriasException("Tipo de dato de cola desconocido");
        }
        this.indiceActual++;
    }

    public T Extraer() throws UtileriasException {
        T valor = null;
        switch (this.tipo) {
            case LIFO:
                for (int i = this.TamanioTotal - 1; i >= 0; i--) {
                    if (this.pila.Get(i) != null) {
                        valor = (T) this.pila.Get(i);
                        this.pila.Set(null, i);
                        this.indiceActual = i;
                        break;
                    }
                }
                if (valor == null) {
                    throw new UtileriasException("La pila esta vacia.");
                }
                break;
            case FIFO:
                for (int i = 0; i <this.TamanioTotal; i++) {
                    if (this.pila.Get(i) != null) {
                        valor = (T) this.pila.Get(i);
                        this.pila.Set(null, i);
                        this.indiceActual = i;
                        break;
                    }
                }
                if (valor == null) {
                    throw new UtileriasException("La pila esta vacia.");
                }
                break;
            default:
                throw new AssertionError(this.tipo.name());
        }
        return valor;
    }

    public int Longuitud() {
        return this.TamanioTotal;
    }
    @Override
    public void Imprime() {
        this.pila.Imprime();
    }

    @Override
    public void Ordena(Estructura.Ordenacion metodo) throws UtileriasException {
        this.pila.Ordena(metodo);
    }

    @Override
    public void Random(int Longuitud) throws UtileriasException {
        this.pila.Random(Longuitud);
    }

    @Override
    public void Random(int desde, int hasta) {
        this.pila.Random(desde, hasta);
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
