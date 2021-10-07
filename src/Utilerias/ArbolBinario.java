/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

import Utilerias.Extensiones.Tupla;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonyj
 * @param <T>
 */
public class ArbolBinario<T> extends Arbol<T> 
{
    private Nodo<T> Raiz;
    public Nodo<T> Raiz()
    {
        return Raiz;
    }
    private T Ultimo;
    public T Ultimo()
    {
        return this.Ultimo;
    }
    private int Altura;
    public int Altura() {
        return this.Altura;
    }
    private Boolean Vacio;

    public Boolean Vacio() {
        return this.Vacio;
    }
    private Boolean AceptaDuplicados;

    public ArbolBinario(Boolean AceptaDuplicados) {
        this.Raiz = null;
        this.Altura = 0;
        this.TamanioTotal = 0;
        this.Vacio = true;
        this.indiceActual = 0;
        this.AceptaDuplicados = AceptaDuplicados;
    }

    public ArbolBinario() {
        this.Raiz = null;
        this.Altura = 0;
        this.TamanioTotal = 0;
        this.Vacio = true;
        this.indiceActual = 0;
        this.AceptaDuplicados = true;
    }

    public void Agregar(T valor) {
        this.Raiz = Agregar(this.Raiz, valor);
        this.TamanioTotal++;
        this.Vacio = false;
        UConsole.Imprime.Log(this.TamanioTotal + "-" + valor);
        this.Ultimo=valor;
    }

    /* Function to insert data recursively */
    private Nodo<T> Agregar(Nodo<T> nodo, T valor)
    {
        if (nodo == null) {
            nodo = new Nodo<>(valor);
            this.indiceActual = 0;
        } else {
            try
            {
            if (!this.AceptaDuplicados &&!this.Vacio &&Contiene(valor)) {
                Contiene(this.Raiz, valor).Cuantos++;
            } else {
                if (!nodo.EstaCompleto()) {
                    if (nodo.Derecho == null) {
                        nodo.Derecho = new Nodo<>(valor);
                        ((Nodo) nodo.Derecho).Nivel = nodo.Nivel + 1;
                        this.indiceActual = ((Nodo) nodo.Derecho).Nivel;
                    } else {
                        nodo.Izquierdo = new Nodo<>(valor);
                        ((Nodo) nodo.Izquierdo).Nivel = nodo.Nivel + 1;
                        this.indiceActual = ((Nodo) nodo.Izquierdo).Nivel;
                    }
                } else {
                    if (((Nodo) nodo.Derecho).EstaCompleto() && ((Nodo) nodo.Izquierdo).EstaCompleto()) {
                        Nodo<T> temp = BuscaIncompleto(nodo);
                        temp = Agregar(temp, valor);
                    } else {
                        if (!((Nodo) nodo.Derecho).EstaCompleto()) {
                            nodo.Derecho = Agregar((Nodo) nodo.Derecho, valor);
                        } else {
                            nodo.Izquierdo = Agregar((Nodo) nodo.Izquierdo, valor);
                        }
                    }
                }
            }
            }catch(UtileriasException ex){}
        }
        return nodo;
    }
    
    @Deprecated
    private Nodo<T> _Agregar(Nodo<T> nodo, T valor) {
        if (nodo == null) {
            nodo = new Nodo<>(valor);
            this.indiceActual = 0;
        } else {
            if (nodo.Derecho == null) {
                nodo.Derecho = _Agregar((Nodo<T>) nodo.Derecho, valor);
            } else {
                nodo.Izquierdo = _Agregar((Nodo<T>) nodo.Izquierdo, valor);
            }
        }
        this.Ultimo=valor;
        return nodo;
    }

    private Nodo<T> BuscaIncompleto(Nodo<T> nodo) {
        if (!nodo.EstaCompleto()) {
            return nodo;
        }
        Nodo<T> tempD = (Nodo<T>) nodo.Derecho;
        Nodo<T> tempI = (Nodo<T>) nodo.Izquierdo;
        while (tempD.EstaCompleto() || tempI.EstaCompleto()) {
            tempD = BuscaIncompleto((Nodo) tempD);
            tempI = BuscaIncompleto((Nodo) tempI);
        }
        if (tempD.Nivel < tempI.Nivel) {
            return tempD;
        } else {
            return tempI;
        }
    }

    @Override
    public boolean Contiene(Object val) throws UtileriasException {
        if(this.Vacio)
        {
            throw new UtileriasException("El arbol esta vacio."); 
        }
        return (Contiene(Raiz, (T)val) != null);
    }

    private Nodo<T> Contiene(Nodo<T> r, T val) {
        if(r==null){return null;}
        Nodo<T> temp = null;
        if (IgualQue(r.Valor, val)) {
            return r;
        }
        if (r.Izquierdo != null) {
            temp = Contiene((Nodo<T>) r.Izquierdo, val);
            if (temp != null) {
                return temp;
            }
        }
        if (r.Derecho != null) {
            temp = Contiene((Nodo<T>) r.Derecho, val);
            if (temp != null) {
                return temp;
            }
        }
        return temp;

    }
    public void Imprime(RecorridoArbol recorrido) throws UtileriasException
    {
        if (this.Vacio) {
            throw new UtileriasException("El arbol esta vacio");
        }
        switch(recorrido)
        {
            case In_Orden:
                In_Orden(this.Raiz);
                break;
            case Pre_Orden:
                Pre_Orden(this.Raiz);
                break;
            case Post_Orden:
                Post_Orden(this.Raiz);
                break;
            default:
                throw new AssertionError(recorrido.name());
            
        }
    }
    private void In_Orden(Nodo<T> r) {
        if (r != null) {
            In_Orden((Nodo) r.Izquierdo);
            UConsole.Imprime.Imprimir(r.Valor + " ");
            In_Orden((Nodo) r.Derecho);
        }
    }

    private void Pre_Orden(Nodo<T> r) {
        if (r != null) {
            UConsole.Imprime.Imprimir(r.Valor + " ");
            Pre_Orden((Nodo<T>) r.Izquierdo);
            Pre_Orden((Nodo<T>) r.Derecho);
        }
    }

    private void Post_Orden(Nodo<T> r) {
        if (r != null) {
            Post_Orden((Nodo<T>) r.Izquierdo);
            Post_Orden((Nodo<T>) r.Derecho);
            UConsole.Imprime.Imprimir(r.Valor + " ");
        }
    }

    public void Diagrama() throws UtileriasException, IOException, InterruptedException {
        if (this.Vacio) {
            throw new UtileriasException("El arbol esta vacio");
        }
        String html = "<html>\n"
                + "<head>\n"
                + "<title>Arbol Binario</title>\n"
                + "<style type=\"text/css\">"
                //+".container{position:relative;width:100%;max-width:960px;margin:0 auto;padding:0 20px;box-sizing:border-box}.column,.columns{width:100%;float:left;box-sizing:border-box}@media (min-width:400px){.container{width:85%;padding:0}}@media (min-width:550px){.container{width:80%}.column,.columns{margin-left:4%}.column:first-child,.columns:first-child{margin-left:0}.one.column,.one.columns{width:4.66666666667%}.two.columns{width:13.3333333333%}.three.columns{width:22%}.four.columns{width:30.6666666667%}.five.columns{width:39.3333333333%}.six.columns{width:48%}.seven.columns{width:56.6666666667%}.eight.columns{width:65.3333333333%}.nine.columns{width:74%}.ten.columns{width:82.6666666667%}.eleven.columns{width:91.3333333333%}.twelve.columns{width:100%;margin-left:0}.one-third.column{width:30.6666666667%}.two-thirds.column{width:65.3333333333%}.one-half.column{width:48%}.offset-by-one.column,.offset-by-one.columns{margin-left:8.66666666667%}.offset-by-two.column,.offset-by-two.columns{margin-left:17.3333333333%}.offset-by-three.column,.offset-by-three.columns{margin-left:26%}.offset-by-four.column,.offset-by-four.columns{margin-left:34.6666666667%}.offset-by-five.column,.offset-by-five.columns{margin-left:43.3333333333%}.offset-by-six.column,.offset-by-six.columns{margin-left:52%}.offset-by-seven.column,.offset-by-seven.columns{margin-left:60.6666666667%}.offset-by-eight.column,.offset-by-eight.columns{margin-left:69.3333333333%}.offset-by-nine.column,.offset-by-nine.columns{margin-left:78%}.offset-by-ten.column,.offset-by-ten.columns{margin-left:86.6666666667%}.offset-by-eleven.column,.offset-by-eleven.columns{margin-left:95.3333333333%}.offset-by-one-third.column,.offset-by-one-third.columns{margin-left:34.6666666667%}.offset-by-two-thirds.column,.offset-by-two-thirds.columns{margin-left:69.3333333333%}.offset-by-one-half.column,.offset-by-one-half.columns{margin-left:52%}}html{font-size:62.5%}body{font-size:1.5em;line-height:1.6;font-weight:400;font-family:Raleway,HelveticaNeue,\"Helvetica Neue\",Helvetica,Arial,sans-serif;color:#222}h1,h2,h3,h4,h5,h6{margin-top:0;margin-bottom:2rem;font-weight:300}h1{font-size:4rem;line-height:1.2;letter-spacing:-.1rem}h2{font-size:3.6rem;line-height:1.25;letter-spacing:-.1rem}h3{font-size:3rem;line-height:1.3;letter-spacing:-.1rem}h4{font-size:2.4rem;line-height:1.35;letter-spacing:-.08rem}h5{font-size:1.8rem;line-height:1.5;letter-spacing:-.05rem}h6{font-size:1.5rem;line-height:1.6;letter-spacing:0}@media (min-width:550px){h1{font-size:5rem}h2{font-size:4.2rem}h3{font-size:3.6rem}h4{font-size:3rem}h5{font-size:2.4rem}h6{font-size:1.5rem}}p{margin-top:0}a{color:#1eaedb}a:hover{color:#0fa0ce}.button,button,input[type=button],input[type=reset],input[type=submit]{display:inline-block;height:38px;padding:0 30px;color:#555;text-align:center;font-size:11px;font-weight:600;line-height:38px;letter-spacing:.1rem;text-transform:uppercase;text-decoration:none;white-space:nowrap;background-color:transparent;border-radius:4px;border:1px solid #bbb;cursor:pointer;box-sizing:border-box}.button:focus,.button:hover,button:focus,button:hover,input[type=button]:focus,input[type=button]:hover,input[type=reset]:focus,input[type=reset]:hover,input[type=submit]:focus,input[type=submit]:hover{color:#333;border-color:#888;outline:0}.button.button-primary,button.button-primary,input[type=button].button-primary,input[type=reset].button-primary,input[type=submit].button-primary{color:#fff;background-color:#33c3f0;border-color:#33c3f0}.button.button-primary:focus,.button.button-primary:hover,button.button-primary:focus,button.button-primary:hover,input[type=button].button-primary:focus,input[type=button].button-primary:hover,input[type=reset].button-primary:focus,input[type=reset].button-primary:hover,input[type=submit].button-primary:focus,input[type=submit].button-primary:hover{color:#fff;background-color:#1eaedb;border-color:#1eaedb}input[type=email],input[type=number],input[type=password],input[type=search],input[type=tel],input[type=text],input[type=url],select,textarea{height:38px;padding:6px 10px;background-color:#fff;border:1px solid #d1d1d1;border-radius:4px;box-shadow:none;box-sizing:border-box}input[type=email],input[type=number],input[type=password],input[type=search],input[type=tel],input[type=text],input[type=url],textarea{-webkit-appearance:none;-moz-appearance:none;appearance:none}textarea{min-height:65px;padding-top:6px;padding-bottom:6px}input[type=email]:focus,input[type=number]:focus,input[type=password]:focus,input[type=search]:focus,input[type=tel]:focus,input[type=text]:focus,input[type=url]:focus,select:focus,textarea:focus{border:1px solid #33c3f0;outline:0}label,legend{display:block;margin-bottom:.5rem;font-weight:600}fieldset{padding:0;border-width:0}input[type=checkbox],input[type=radio]{display:inline}label>.label-body{display:inline-block;margin-left:.5rem;font-weight:400}ul{list-style:circle inside}ol{list-style:decimal inside}ol,ul{padding-left:0;margin-top:0}ol ol,ol ul,ul ol,ul ul{margin:1.5rem 0 1.5rem 3rem;font-size:90%}li{margin-bottom:1rem}code{padding:.2rem .5rem;margin:0 .2rem;font-size:90%;white-space:nowrap;background:#f1f1f1;border:1px solid #e1e1e1;border-radius:4px}pre>code{display:block;padding:1rem 1.5rem;white-space:pre}td,th{padding:12px 15px;text-align:left;border-bottom:1px solid #e1e1e1}td:first-child,th:first-child{padding-left:0}td:last-child,th:last-child{padding-right:0}.button,button{margin-bottom:1rem}fieldset,input,select,textarea{margin-bottom:1.5rem}blockquote,dl,figure,form,ol,p,pre,table,ul{margin-bottom:2.5rem}.u-full-width{width:100%;box-sizing:border-box}.u-max-full-width{max-width:100%;box-sizing:border-box}.u-pull-right{float:right}.u-pull-left{float:left}hr{margin-top:3rem;margin-bottom:3.5rem;border-width:0;border-top:1px solid #e1e1e1}.container:after,.row:after,.u-cf{content:\"\";display:table;clear:both}"
                + "/*! normalize.css v3.0.2 | MIT License | git.io/normalize */html{font-family:sans-serif;-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}body{margin:0}article,aside,details,figcaption,figure,footer,header,hgroup,main,menu,nav,section,summary{display:block}audio,canvas,progress,video{display:inline-block;vertical-align:baseline}audio:not([controls]){display:none;height:0}[hidden],template{display:none}a{background-color:transparent}a:active,a:hover{outline:0}abbr[title]{border-bottom:1px dotted}b,strong{font-weight:700}dfn{font-style:italic}h1{font-size:2em;margin:.67em 0}mark{background:#ff0;color:#000}small{font-size:80%}sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}sup{top:-.5em}sub{bottom:-.25em}img{border:0}svg:not(:root){overflow:hidden}figure{margin:1em 40px}hr{-moz-box-sizing:content-box;box-sizing:content-box;height:0}pre{overflow:auto}code,kbd,pre,samp{font-family:monospace,monospace;font-size:1em}button,input,optgroup,select,textarea{color:inherit;font:inherit;margin:0}button{overflow:visible}button,select{text-transform:none}button,html input[type=button],input[type=reset],input[type=submit]{-webkit-appearance:button;cursor:pointer}button[disabled],html input[disabled]{cursor:default}button::-moz-focus-inner,input::-moz-focus-inner{border:0;padding:0}input{line-height:normal}input[type=checkbox],input[type=radio]{box-sizing:border-box;padding:0}input[type=number]::-webkit-inner-spin-button,input[type=number]::-webkit-outer-spin-button{height:auto}input[type=search]{-webkit-appearance:textfield;-moz-box-sizing:content-box;-webkit-box-sizing:content-box;box-sizing:content-box}input[type=search]::-webkit-search-cancel-button,input[type=search]::-webkit-search-decoration{-webkit-appearance:none}fieldset{border:1px solid silver;margin:0 2px;padding:.35em .625em .75em}legend{border:0;padding:0}textarea{overflow:auto}optgroup{font-weight:700}table{border-collapse:collapse;border-spacing:0}td,th{padding:0}"
                + ".tree li code:hover,.tree li code:hover+ul li code{background:#c8e4f8;color:#000;}.tree li code:hover+ul li::after,.tree li code:hover+ul li::before,.tree li code:hover+ul ul::before,.tree li code:hover+ul::before{border-color:#94a0b4}body{font-family:Calibri,Segoe,\"Segoe UI\",\"Gill Sans\",\"Gill Sans MT\",sans-serif}.tree,.tree li,.tree ul{list-style:none;margin:0;padding:0;position:relative}.tree{margin:0 0 1em;text-align:center}.tree,.tree ul{display:table}.tree ul{width:100%}.tree li{display:table-cell;padding:.5em 0;vertical-align:top}.tree li:before{outline:solid 1px #666;content:\"\";left:0;position:absolute;right:0;top:0}.tree li:first-child:before{left:50%}.tree li:last-child:before{right:50%}.tree code,.tree span{border:solid .1em #666;border-radius:.2em;display:inline-block;margin:0 .2em .5em;padding:.2em .5em;position:relative}.tree code{font-family: monaco, Consolas, 'Lucida Console', monospace;font-size: 100%;white-space: nowrap;background: #f1f1f1;}.tree code:before,.tree span:before,.tree ul:before{outline:solid 1px #666;content:\"\";height:.5em;left:50%;position:absolute}.tree ul:before{top:-.5em}.tree code:before,.tree span:before{top:-.55em}.tree>li{margin-top:0}.tree>li:after,.tree>li:before,.tree>li>code:before,.tree>li>span:before{outline:0}"
                + "</style>\n"
                + "</head>\n"
                + "<body style=\"background: aliceblue;height: 100%;max-width: 50%;margin: auto;\">\n";
        /////
        html += "<figure>\n"
                + "<h2 style=\"text-align: center;\">Arbol Binario</h2>\n"
                + "<ul class=\"tree\">" + Diagrama(this.Raiz) + "</ul>";
        /////////////////////
        html += "</body>\n"
                + "</html>";
        UConsole.Imprime.ArchivoDeTexto(html, "diagrama.html", true);
        UConsole.Iniciar("diagrama.html");
    }

    private String Diagrama(Nodo<T> temp) {
        String html = "";
        html += "\n<li><code>" + temp.Valor + (!this.AceptaDuplicados ? (temp.Cuantos > 1 ? "-" + temp.Cuantos : "") : "") + "</code>";
        if (temp.Derecho != null || temp.Izquierdo != null) {
            html += "<ul>";
            if (temp.Derecho != null) {
                html += Diagrama((Nodo<T>) temp.Derecho);
            }
            if (temp.Izquierdo != null) {
                html += Diagrama((Nodo<T>) temp.Izquierdo);
            }
            html += "</ul>";
        }
        html += "</li>\n";
        return html;
    }

    @Override
    public void Imprime() {
        try {
            this.Imprime(RecorridoArbol.Pre_Orden);
        } catch (UtileriasException ex) {
            UtileriasException.Mostrar(ex);
        }
    }

    @Override
    public void Ordena(Ordenacion metodo) throws UtileriasException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Random(int Longuitud) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Random(int desde, int hasta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Convierte(Estructura estructura) throws UtileriasException {
        T temp;
        switch (estructura.getClass().getName()) {
            case "Utilerias.Lista":
                Lista<T> lista = (Lista<T>) estructura;
                for (int i = 0; i < lista.TamanioTotal; i++) {
                    temp = lista.Obtener(i);
                    if (temp != null) {
                        Agregar(temp);
                    }
                }
                break;
            default:
                throw new UtileriasException("Conversion de:" + estructura.getClass().getName() + " a Arbol binario no soportada");
        }
    }

    @Override
    public void Limpiar() {
        this.Raiz = null;
        this.Altura = 0;
        this.TamanioTotal = 0;
        this.Vacio = true;
        this.indiceActual = 0;
    }

    private class Nodo<T> extends NodoDoble {

        public int Cuantos;

        public int Nivel;

        public Nodo() {
            this.Izquierdo = null;
            this.Derecho = null;
        }

        public Nodo(T valor) {
            this.Valor = valor;
            this.Izquierdo = null;
            this.Derecho = null;
        }

        public Nodo(NodoDoble Derecho, T Valor) {
            this.Izquierdo = null;
            this.Derecho = Derecho;
            this.Valor = Valor;
        }

        public Boolean EstaCompleto() {
            return this.Izquierdo != null && this.Derecho != null;
        }
    }
}
