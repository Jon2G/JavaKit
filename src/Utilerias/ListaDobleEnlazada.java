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
public class ListaDobleEnlazada<T> extends Lista<T> {

    private NodoDoble Nodo;

    public ListaDobleEnlazada(Estructura.TipoLista TipoLista) throws UtileriasException {
        this.TipoLista = TipoLista;
        this.Nodo = null;
        this.CuantosElementos
                = this.TamanioTotal = 0;
    }

    public ListaDobleEnlazada() {
        this.TipoLista = Estructura.TipoLista.Lineal;
        this.Nodo = null;
        this.CuantosElementos
                = this.TamanioTotal = 0;
    }

    /**
     * Retorna el elemento de la Lista doblemente enlazada en la posicion
     * indicada
     *
     * @param indice
     * @return
     * @throws UtileriasException
     */
    @Override
    public T Obtener(int indice) throws UtileriasException {
        NodoDoble temp = this.Nodo;
        if (temp == null) {
            this.indiceActual = 0;
            throw new UtileriasException("La lista esta vacia...");
        }
        if (indice >= this.TamanioTotal) {
            this.indiceActual = 0;
            throw new UtileriasException("No existe el indice:" + indice);
        }
        this.indiceActual = indice;
        while (indice > 0) {
            temp = temp.Izquierdo;
            indice--;
        }
        return (T) temp.Valor;
    }

    /**
     * Agrega un valor al final de la lista DoblementeEnlazada
     *
     * @param valor que se agregará
     */
    @Override
    public void Agregar(T valor) {
        NodoDoble nuevo = new NodoDoble(valor);
        if (this.Nodo == null) {
            this.Nodo = nuevo;
        } else {
            NodoDoble aux = this.Nodo;
            switch (this.TipoLista) {
                case Circular:
                    while (aux.Izquierdo != null && aux.Izquierdo != this.Nodo) {
                        aux = aux.Izquierdo;
                    }
                    break;
                case Lineal:
                    while (aux.Izquierdo != null) {
                        aux = aux.Izquierdo;
                    }
                    break;
                default:
                    throw new AssertionError(this.TipoLista.name());
            }
            aux.Izquierdo = nuevo;
            nuevo.Derecho = aux;
            /////////////
            if (this.TipoLista == TipoLista.Circular) {
                nuevo.Izquierdo = this.Nodo;
            }
            //////////////
        }
        this.CuantosElementos
                = this.TamanioTotal++;
    }

    /**
     * Agrega un elmento al inicio de la lista doblemente ligada
     *
     * @param valor valor que será agregado
     */
    @Override
    public void Anteponer(T valor) {
        NodoDoble nuevo = new NodoDoble(valor);
        if (this.Nodo == null) {
            this.Nodo = nuevo;
        } else {
            nuevo.Izquierdo = this.Nodo;
            this.Nodo.Derecho = nuevo;
            this.Nodo = nuevo;
        }
    }

    /**
     * Inserta un elemento el la posición dada
     *
     * @param indice Indice de el nuevo elemento
     * @param valor Valor a insertar
     * @throws UtileriasException
     */
    @Override
    public void Insertar(int indice, T valor) throws UtileriasException {
        this.indiceActual = indice;
        if (this.indiceActual <= this.TamanioTotal + 1) {
            NodoDoble nuevo = new NodoDoble();
            nuevo.Valor = valor;
            if (this.indiceActual == 1) {
                nuevo.Izquierdo = this.Nodo;
                if (this.Nodo != null) {
                    this.Nodo.Derecho = nuevo;
                }
                this.Nodo = nuevo;
            } else if (this.indiceActual == this.TamanioTotal + 1) {
                NodoDoble reco = this.Nodo;
                while (reco.Izquierdo != null) {
                    reco = reco.Izquierdo;
                }
                reco.Izquierdo = nuevo;
                nuevo.Derecho = reco;
                nuevo.Izquierdo = null;
            } else {
                if (this.Nodo == null) {
                    this.Nodo = new NodoDoble();
                }
                NodoDoble reco = this.Nodo;
                for (int f = 1; f <= this.indiceActual - 2; f++) {
                    reco = reco.Izquierdo;
                }
                NodoDoble siguiente = reco.Izquierdo;
                reco.Izquierdo = nuevo;
                nuevo.Derecho = reco;
                nuevo.Izquierdo = siguiente;
                if (siguiente != null) {
                    siguiente.Derecho = nuevo;
                } else {
                    this.Nodo = nuevo;
                }
            }
        }
        this.CuantosElementos
                = this.TamanioTotal++;
    }

    /**
     * Elimina un elemento de la lista doblemente enlazada en la posición
     * indicada
     *
     * @param indice
     * @throws UtileriasException
     */
    @Override
    public void EliminarEn(int indice) throws UtileriasException {
        if (indice != 0 && indice > this.TamanioTotal - 1) {
            throw new UtileriasException("No existe ningun elemento en la posición de ->" + indice);
        }
        this.indiceActual = indice;
        if (this.Nodo != null) {
            NodoDoble aux = this.Nodo;
            NodoDoble ant = null;
            while (this.indiceActual > 0) {
                ant = aux;
                aux = aux.Izquierdo;
                this.indiceActual--;
            }
            if (ant == null) {
                this.Nodo = this.Nodo.Izquierdo;
                aux.Izquierdo = null;
                aux = this.Nodo;
                //////
                if (this.TipoLista == TipoLista.Circular) {
                    aux.Derecho = this.Nodo;
                }
                ///////////
            } else {
                ant.Izquierdo = aux.Izquierdo;
                aux.Izquierdo = null;
                aux = ant.Izquierdo;
            }
        }
        this.TamanioTotal--;
    }
    @Override
    public Boolean Cotiene(Object valor)
    {
        try{
        return PosicionDe((T)valor)>-1;
        }catch(UtileriasException ex)
        {
            UtileriasException.Mostrar(ex);
        }
        return false;
    }
    @Override
    public int PosicionDe(T valor) throws UtileriasException {
        int indice = -1;
        NodoDoble temp = this.Nodo;
        if (temp == null) {
            throw new UtileriasException("La lista esta vacia...");
        }
        if (this.TipoLista == TipoLista.Lineal) {
            while (temp != null) {
                indice++;
                if (temp.Get().equals(valor)) {
                    return indice;
                }
                temp = temp.Izquierdo;
            }
        } else {
            while (temp != null) {
                indice++;
                if (temp.Get().equals(valor)) {
                    return indice;
                }
                temp = temp.Izquierdo;
                if (temp == this.Nodo) {
                    break;
                }
            }
        }
        return -1;
    }

    @Override
    public void Eliminar(T valor) throws UtileriasException {
        int indice = PosicionDe(valor);
        while (indice != -1) {
            EliminarEn(indice);
            if (this.TamanioTotal <= 0) {
                return;
            }
            indice = PosicionDe(valor);

        }
        this.CuantosElementos
                = this.TamanioTotal--;
    }

    @Override
    public void Imprime() {
        NodoDoble temp = this.Nodo;
        if (temp == null) {
            UConsole.Imprime.Imprimir("La lista esta vacia...");
        }
        while (temp != null) {
            UConsole.Imprime.Imprimir("|" + temp.Get());
            temp = temp.Izquierdo;
            if (temp == this.Nodo) {
                break;
            }
        }
    }

    /**
     * Imprime la lista doblemente enlazada en reversa
     *
     * @throws Utilerias.UtileriasException
     */
    public void ImprimeReversa(Boolean Separador) throws UtileriasException {
        NodoDoble temp = this.Nodo;
        if (temp == null) {
            throw new UtileriasException("La lista esta vacia...");
        }
        if (this.TipoLista == TipoLista.Lineal) {
            while (temp.Izquierdo != null) {
                temp = temp.Izquierdo;
            }
            UConsole.Imprime.Imprimir((Separador ? "|" : "") + temp.Get());
            temp = temp.Derecho;
            while (temp != null) {
                UConsole.Imprime.Imprimir((Separador ? "|" : "") + temp.Get());
                temp = temp.Derecho;
            }
        } else {
            while (temp.Izquierdo != null) {
                if (temp.Izquierdo == this.Nodo) {
                    break;
                }
                temp = temp.Izquierdo;
            }
            UConsole.Imprime.Imprimir((Separador ? "|" : "") + temp.Get());
            temp = temp.Derecho;
            while (temp != null) {
                UConsole.Imprime.Imprimir((Separador ? "|" : "") + temp.Get());
                temp = temp.Derecho;
                if (temp == this.Nodo) {
                    break;
                }
            }
        }

    }

    /**
     * Retorna el elemento siguiente en la lista basandose en el Indice Actual
     *
     * @return
     * @throws UtileriasException
     */
    @Override
    public T Siguiente() throws UtileriasException {
        if (this.indiceActual >= this.TamanioTotal) {
            if (this.TipoLista == TipoLista.Circular) {
                this.indiceActual = 0;
            } else {
                this.indiceActual = this.TamanioTotal - 1;
                throw new UtileriasException("Se alcanzo el final de la lista...");
            }
        }
        T temp = Obtener(this.indiceActual);
        this.indiceActual++;
        return temp;
    }

    /**
     * Retorna el elemento anterior en la lista basandose en el Indice Actual
     *
     * @return
     * @throws UtileriasException
     */
    @Override
    public T Anterior() throws UtileriasException {
        this.indiceActual--;
        if (this.indiceActual < 0) {
            if (this.TipoLista == TipoLista.Circular) {
                this.indiceActual = 0;
            } else {
                throw new UtileriasException("Se alcanzo el inicio de la lista...");
            }
        }
        T temp = Obtener(this.indiceActual);
        return temp;
    }

    @Override
    public void Limpiar() {
        this.Nodo = null;
        this.TamanioTotal = 0;
        this.CuantosElementos = 0;
    }

    protected NodoDoble NodoEn(int indice) throws UtileriasException {
        NodoDoble temp = this.Nodo;
        if (temp == null) {
            this.indiceActual = 0;
            throw new UtileriasException("La lista esta vacia...");
        }
        if (indice >= this.TamanioTotal) {
            this.indiceActual = 0;
            throw new UtileriasException("No existe el indice:" + indice);
        }
        this.indiceActual = indice;
        while (indice > 0) {
            temp = temp.Izquierdo;
            indice--;
        }
        return temp;
    }

    @Override
    public void Ordena(Ordenacion metodo) throws UtileriasException {
        switch (metodo) {
            case BURBUJA:
                Burbuja();
                break;
            case MEZCLA:
                Mezcla(this, 0, this.TamanioTotal);
                break;
            case QUICKSORT:
                QuickSort(this,0,this.TamanioTotal);
                break;
        }
        Arreglo<Object> temp = new Arreglo<>(this.TamanioTotal);
        for (int i = 0; i < this.TamanioTotal; i++) {
            temp.Set(this.Obtener(i), i);
        }
        temp.Ordena(metodo);
        this.Limpiar();
        for (int i = 0; i < temp.TamanioTotal; i++) {
            this.Agregar((T) temp.Get(i));
        }
    }

    private void Burbuja() throws UtileriasException {
        T temporal;
        NodoDoble<T> temp1;
        NodoDoble<T> temp2;
        for (int i = 0; i < this.TamanioTotal; i++) {
            for (int j = 0; j < this.TamanioTotal; j++) {
                temp1 = this.NodoEn(i);
                temp2 = this.NodoEn(j);
                if (MenorQue((Object) temp1.Valor, (Object) temp2.Valor)) {
                    temporal = temp2.Valor;
                    temp2.Valor = temp1.Valor;
                    temp1.Valor = temporal;
                }
            }
        }
    }

    private ListaDobleEnlazada<T> Mezcla(ListaDobleEnlazada<T> arreglo, int inicio, int fin) throws UtileriasException {
        int n1;
        int n2;
        ListaDobleEnlazada<T> salida = arreglo;
        if (fin > 1) {
            n1 = fin / 2;
            n2 = fin - n1;
            Mezcla(arreglo, inicio, n1);
            Mezcla(arreglo, inicio + n1, n2);
            salida = merge(arreglo, inicio, n1, n2);
        }
        return salida;
    }

    private ListaDobleEnlazada<T> merge(ListaDobleEnlazada<T> matrix, int init, int n1, int n2) throws UtileriasException {
        ListaDobleEnlazada<T> buffer = new ListaDobleEnlazada<>();
        int temp = 0;
        int temp1 = 0;
        int temp2 = 0;
        int i;
        NodoDoble nodo1;
        NodoDoble nodo2;
        while ((temp1 < n1) && (temp2 < n2)) {
            nodo1 = matrix.NodoEn(init + temp1);
            nodo2 = matrix.NodoEn(init + n1 + temp2);
            if (MenorQue((Object) nodo1.Valor, (Object) nodo2.Valor)) {
                buffer.Agregar((T) matrix.NodoEn(init + (temp1++)).Valor);
            } else {
                buffer.Agregar((T) matrix.NodoEn(init + n1 + (temp2++)).Valor);
            }
        }
        while (temp1 < n1) {
            buffer.Agregar((T) matrix.NodoEn(init + (temp1++)).Valor);
        }
        while (temp2 < n2) {
            buffer.Agregar((T) matrix.NodoEn(init + n1 + (temp2++)).Valor);
        }
        for (i = 0; i < n1 + n2; i++) {
            matrix.NodoEn(init + i).Valor = buffer.NodoEn(i).Valor;
        }
        return matrix;
    }

    protected ListaDobleEnlazada<T> QuickSort(ListaDobleEnlazada<T> arreglo, int izquierda, int derecha) throws UtileriasException {
        //1. Elegir el pivote
        T pivote = (T) arreglo.NodoEn(izquierda).Valor;
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
            while (MenorQueoIgual((Object) arreglo.NodoEn(i).Valor, (Object) pivote) && i < j) {
                i++;
            }
            //mientras que el elemento vector[j] sea mayor al pivote
            //se desminuye el valor de j
            //cuando este loop se detenga, el elemento en vector[j]
            //es menor o igual a pivote y deberá ir a su izquierda
            while (MayorQue((Object) arreglo.NodoEn(j).Valor, pivote)) {
                j--;
                if (j < 0) {
                    j = 0;
                    break;
                }
            }
            //siempre y cuando i sea menor a j, se hace un cambio de los elementos
            //puesto que el elemento en vector[i] debe ir a la derecha
            //y vector[j] a la izquierda
            if (MenorQue(i, j)) {
                T auxIntercambio = (T) arreglo.NodoEn(i).Valor;
                arreglo.NodoEn(i).Valor = arreglo.NodoEn(j).Valor;
                arreglo.NodoEn(j).Valor = auxIntercambio;
            }
        }
        //Por los ciclos anteriores, j llegó a una posición donde su elemento (i.e. vector[j]) 
        //es menor o igual al pivote, actualizamos entonces la posición del pivote, mandando vector[j] 
        //a la ubicación del pivote y viceversa (el pivote a la posicion vector[j])
        arreglo.NodoEn(izquierda).Valor = arreglo.NodoEn(j).Valor;
        arreglo.NodoEn(j).Valor = pivote;
        //3. Para A1 y A2, aplicar el mismo proceso.
        if (izquierda < j - 1) {
            //quicksort aplicado a A1
            QuickSort(arreglo, izquierda, j - 1);
        }
        if (j + 1 < derecha) {
            //quicksort aplicado a A2
            QuickSort(arreglo, j + 1, derecha);
        }
        return arreglo;
    }
}
