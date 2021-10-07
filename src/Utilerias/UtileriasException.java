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
public class UtileriasException extends Exception {
    	public UtileriasException (String message) {
		super(message);
	}
        public static void Mostrar(Exception ex) {
            UConsole.Imprime.Imprimir("\n");
            UConsole.Imprime.Imprimir("------EXCEPCIÓN------\n");
            UConsole.Imprime.Imprimir(ex.getMessage()+"\n");
            UConsole.Imprime.Imprimir("---------------------\n");
            System.out.print("\n");
            System.out.print("------EXCEPCIÓN------\n");
            System.out.print(ex.getMessage()+"\n");
            System.out.print("---------------------\n");
    }
    
}
