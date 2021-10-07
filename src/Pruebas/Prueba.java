/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;


import Utilerias.*;
import Utilerias.UConsole;
import Utilerias.UtileriasException;
import Utilerias.UConsole.*;
import static Utilerias.UConsole.Imprime.Imprimir;
import Utilerias.UConsole.Lee.*;
import Utilerias.Estructura.*;
import java.io.IOException;
import static java.lang.System.exit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Prueba {

    public static void main(String[] args) throws UtileriasException, IOException, CloneNotSupportedException {
        UConsole.MiConsola().setVisible(true);
        Arreglo<String> arreglo=new Arreglo<>(20);
        arreglo.Set("Hola",0);
        arreglo.Random(3);
        Menu menu=new Menu(
                "Ordenamiento por Metodo Burbuja",
                "Ordenamiento por Metodo Inserción",
                "Ordenamiento por Metodo Selección",
                "Ordenamiento por Metodo Shell",
                "Salir"
        );
        menu.Encabezado("METODOS DE ORDENACIÓN");
        menu.Pie("Selecciona una opción utilizando las flechas del teclado y la tecla Enter");
        int opcion;
        do
        {
            opcion=menu.MuestraMenu(true);
            UConsole.LimpiaPantalla();
            Imprimir("Arreglo original: ");
            Imprimir(arreglo);
            Arreglo<Integer> ordenar=(Arreglo)arreglo.clone();
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
            Date inicio=new Date();
            Date fin=null;
            String ini="Ordenanción iniciada a las:"+dateFormat.format(inicio)+"\n";
            switch(opcion)
            {
                case 0:
                    ordenar.Ordena(Ordenacion.BURBUJA);
                    fin=new Date();
                    break;
                case 1:
                    ordenar.Ordena(Ordenacion.INSERCION);
                    fin=new Date();
                    break;
                case 2:
                    ordenar.Ordena(Ordenacion.SELECCION);   
                    fin=new Date();
                    break;
                case 3:
                    ordenar.Ordena(Ordenacion.SHELL);   
                    fin=new Date();
                    break;
                case 4:
                    UConsole.LimpiaPantalla();
                    Imprimir("Programa terminado");
                    Lee.Tecla();
                    exit(0);
                    break;
                default:
                    Imprimir("Opcion Invalida!");
                    Lee.Tecla();
                    exit(0);
                    break;
            }
            Imprimir("Arreglo ordenado: ");
            Imprimir(ordenar);
            Imprimir(ini);
            UConsole.Imprime.Imprimir("Ordenanción terminanda a las:"+dateFormat.format(fin)+"\n");
            UConsole.Imprime.Imprimir("Total:"+(fin.getTime() - inicio.getTime())+"ms\n");
            Lee.Tecla();
        }while(true);
    }
}
