/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

import static Utilerias.UConsole.Imprime.Imprimir;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JRootPane;

/**
 *
 * @author jonyj
 */
public class UConsole {

    private static Consola cons;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Retorna la instacia de MiConsola actual, si no existe crea una
     *
     * @return
     */
    public static Consola MiConsola() {
        if (UConsole.cons == null) {
            UConsole.cons = new Consola();
        }
        return UConsole.cons;
    }

    /**
     * Ayuda a Lee.Tecla() a determinar la última tecla presionada
     *
     * @param c Ultimo evento tecla provocado
     */
    public static void SetUltimaTecla(KeyEvent c) {
        cons.SetUltimaTecla(c);
    }

    /**
     * Retorna un número entero aleatorio
     *
     * @return Número aleatorio
     */
    public static int Aleatorio() {
        Random rand = new Random();
        return rand.nextInt();
    }

    /**
     * Retorna un número entero Aleatorio entre un rango
     *
     * @param desde Valor minimo
     * @param hasta Valor máximo
     * @return
     */
    public static int Aleatorio(int desde, int hasta) {
        Random rand = new Random();
        return rand.nextInt(hasta - desde + 1) + desde;
    }

    /**
     * Limpia la pantalla (MiConsola o Log)
     */
    public static void LimpiaPantalla() {
        if (UConsole.cons != null) {
            cons.LimpiaPantalla();
            return;
        }
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final IOException | InterruptedException e) {
            System.out.println("Falle!" + e.getMessage());
        }
    }

    public static void Iniciar(String comando) throws IOException, InterruptedException {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            new ProcessBuilder("cmd", "/c", comando).inheritIO().start().waitFor();
        } else {
            Runtime.getRuntime().exec(comando);
        }
    }

    public static Lista<File> ListaArchivos(String carpeta, final String extension) {
        File f = new File(carpeta);
        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(extension);
            }
        };
        File[] files = f.listFiles(textFilter);
        return new Lista<>(files);
    }

    public static class Imprime extends UConsole {

        /**
         * Imprime una cadena en pantalla (MiConsola o Log)
         *
         * @param texto Texto a imprimir
         */
        public static void Imprimir(String texto) {
            if (UConsole.cons != null) {
                UConsole.cons.Imprimir(texto);
                return;
            }
            System.out.print(texto);
        }

        /**
         * Imprime un caracter en pantalla (MiConsola o Log)
         *
         * @param caracter Caracter a imprimir
         */
        public static void Imprimir(char caracter) {
            if (UConsole.cons != null) {
                UConsole.cons.Imprimir(caracter);
                return;
            }
            System.out.print(caracter);
        }

        /**
         * Imprime una estructura usando su metodo por defecto (Es equivalente a
         * usar "Estructura.Imprime();")
         *
         * @param estructura
         */
        public static void Imprimir(Estructura estructura) {
            estructura.Imprime();
        }

        /**
         * Imprime la representacion de cadena por defecto de un objeto
         *
         * @param objeto Objeto a imprimir
         */
        public static void Imprimir(Object objeto) {
            if (UConsole.cons != null) {
                UConsole.cons.Imprimir(objeto);
                return;
            }
            System.out.print(objeto);
        }

        public static void ArchivoDeTexto(String texto, String NombreArchivo, Boolean Sobreescribir) throws IOException {
            File file = new File(NombreArchivo);
            if (file.exists()) {
                if (Sobreescribir) {
                    file.delete();
                }
            }
            file.createNewFile();
            FileWriter writer = new FileWriter(file, true);
            writer.write(texto, 0, texto.length());
            writer.close();
        }

        public static void Log(String log) {
            System.out.print(log + "\n");
        }
    }

    public static class Lee extends UConsole {

        public static Integer Entero() {
            int n = -1;
            try {
                if (UConsole.cons != null) {
                    String a = cons.Lee();
                    n = Integer.parseInt(a);
                    return n;
                }
                n = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                UtileriasException.Mostrar(ex);
            }
            return n;
        }

        public static Long Long() {
            long n = -1;
            try {
                if (UConsole.cons != null) {
                    n = Long.parseLong(cons.Lee());
                    return n;
                }
                n = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException ex) {
                UtileriasException.Mostrar(ex);
            }
            return n;
        }

        public static String Cadena() {
            if (UConsole.cons != null) {
                return cons.Lee();
            }
            scanner.reset();
            String cadena = scanner.nextLine();
            return cadena;
        }

        public static Object Tecla() throws IOException {
            if (UConsole.cons != null) {
                try {
                    Thread.sleep(100);
                    cons.Tecla();
                } catch (InterruptedException ex) {
                    Logger.getLogger(UConsole.class.getName()).log(Level.SEVERE, null, ex);
                }
                return cons.UltimaTecla();
            }
            scanner.reset();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String temp = br.readLine();
            if (temp.length() > 0) {
                return temp.charAt(0);
            }
            return '\n';

        }

        public static void GetChar() {
            final JFrame frame = new JFrame();
            synchronized (frame) {
                frame.setUndecorated(true);
                frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
                frame.addKeyListener(new KeyListener() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        synchronized (frame) {
                            frame.setVisible(false);
                            frame.dispose();
                            frame.notify();
                        }
                    }

                    @Override
                    public void keyTyped(KeyEvent e) {
                        throw new UnsupportedOperationException("Not supported yet.1" + e); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        throw new UnsupportedOperationException("Not supported yet.2" + e); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                frame.setVisible(true);
                try {
                    frame.wait();
                } catch (InterruptedException e1) {
                }
            }
        }

        public static Lista<String> PalabrasArchivoDeTexto(String ruta) throws FileNotFoundException, IOException, UtileriasException {
            Lista<String> lista = new Lista<>();
            File file = new File(ruta);
            Imprimir("\nLeyendo archivo...\n");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String temp;
            while ((temp = br.readLine()) != null) {
                temp = temp.toUpperCase().trim();
                Arreglo<String> arreglo = new Arreglo<>(temp.split("\\s"));
                lista.Agregar(arreglo.arreglo);
            }
            return lista;
        }

        public static Lista<String> PalabrasArchivoDeTexto(File archivo) throws FileNotFoundException, IOException, UtileriasException {
            Lista<String> lista = new Lista<>();
            Imprimir("\nLeyendo archivo...\n");
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String temp;
            while ((temp = br.readLine()) != null) {
                temp = temp.toUpperCase().trim();
                Arreglo<String> arreglo = new Arreglo<>(temp.split("\\s"));
                lista.Agregar(arreglo.arreglo);
            }
            return lista;
        }

        public static Lista<String> PalabrasArchivoDeTexto() throws FileNotFoundException, UnsupportedEncodingException, IOException, UtileriasException {
            if (UConsole.cons == null || !UConsole.cons.isVisible()) {
                MiConsola().setVisible(true);
            }
            Lista<String> lista = new Lista<>();
            final JFileChooser fc = new JFileChooser();
            Imprimir("Dame la ruta del archivo:");
            int returnVal = fc.showOpenDialog(UConsole.MiConsola());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                lista = PalabrasArchivoDeTexto(file.getPath());
            }
            return lista;
        }

        public static Lista<Character> CarcteresArchivoDeTexto(String ruta) throws FileNotFoundException, UnsupportedEncodingException, IOException {
            Lista<Character> lista = new Lista<>();
            File file = new File(ruta);
            Imprimir("\nLeyendo archivo...\n");
            FileInputStream is = new FileInputStream(file);
            Reader r = new InputStreamReader(is, "UTF-8");
            int c;
            while ((c = r.read()) != -1) {
                lista.Agregar((char) c);
            }
            return lista;
        }

        public static Lista<Character> CarcteresArchivoDeTexto() throws UnsupportedEncodingException, IOException {
            if (UConsole.cons == null || !UConsole.cons.isVisible()) {
                MiConsola().setVisible(true);
            }
            Lista<Character> lista = new Lista<>();
            final JFileChooser fc = new JFileChooser();
            Imprimir("Dame la ruta del archivo:");
            int returnVal = fc.showOpenDialog(UConsole.MiConsola());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                lista = CarcteresArchivoDeTexto(file.getPath());
            }
            return lista;
        }

        public static class Menu extends UConsole {
            private String Pie;
            private String Encabezado;
            private Lista<String> opciones = new Lista<>();
            private int seleccion = 0;
            private int longuitudMaxima = 0;

            public Menu(Lista<String> opciones) throws UtileriasException {
                this.opciones = opciones;
                if (opciones.TamanioTotal <= 0) {
                    throw new UtileriasException("El el menu no puede estar vacio!");
                }
                CalculaLonguitud();
            }

            public Menu(String... opciones) throws UtileriasException {
                this.opciones = new Lista<>(opciones);
                if (this.opciones.TamanioTotal <= 0) {
                    throw new UtileriasException("El el menu no puede estar vacio!");
                }
                CalculaLonguitud();
            }
            public void Encabezado(String Encabezado)
            {
                this.Encabezado=Encabezado;
            }
            public void Pie(String Pie)
            {
                this.Pie=Pie;
            }
            private void CalculaLonguitud() throws UtileriasException {
                for (int i = 0; i < this.opciones.CuantosElementos; i++) {
                    if (this.longuitudMaxima < this.opciones.Obtener(i).length()) {
                        this.longuitudMaxima = opciones.Obtener(i).length();
                    }
                }
                this.longuitudMaxima += 3;
            }

            private void Barra() {
                for (int i = 0; i < this.longuitudMaxima; i++) {
                    Imprime.Imprimir('-');
                }
                Imprime.Imprimir('\n');
            }
            private void ImprimeEncabezado()
            {
                int lados=(this.longuitudMaxima-(this.Encabezado.length()-1))/2;
                for(int i = 0; i < lados*2; i++)
                {
                    if(i==lados)
                    {
                       Imprime.Imprimir(this.Encabezado); 
                       continue;
                    }
                    Imprime.Imprimir('-');
                }
                Imprime.Imprimir("\n"); 
            }
            private void Despliega(boolean LimpiarPantalla) throws UtileriasException {
                if(LimpiarPantalla){
                    LimpiaPantalla();
                }
                if(this.Encabezado!=null)
                {
                    ImprimeEncabezado();
                }
                Barra();
                for (int i = 0; i < this.opciones.CuantosElementos; i++) {
                    Imprime.Imprimir((i + 1) + ")" + (this.seleccion == i ? "-->" : "") + this.opciones.Obtener(i) + "\n");
                }
                Barra();
                if(this.Pie!=null)
                {
                    Imprime.Imprimir(this.Pie);
                    Imprime.Imprimir("\n");
                    Barra();
                }
            }

            public int MuestraMenu(boolean LimpiarPantalla) throws UtileriasException {
                Despliega(LimpiarPantalla);
                EsperaPorAccion();
                return this.seleccion;
            }

            private void Arriba() {
                this.seleccion--;
                if (this.seleccion < 0) {
                    this.seleccion = 0;
                }
            }

            private void Abajo() {
                this.seleccion++;
                if (this.seleccion > this.opciones.CuantosElementos) {
                    this.seleccion = 0;
                }
            }

            private void EsperaPorAccion() {
                boolean EsEnter = false;
                if (UConsole.cons != null) {
                    try {
                        do {
                            KeyEvent tecla = (KeyEvent) UConsole.Lee.Tecla();
                            switch (tecla.getKeyCode()) {
                                case KeyEvent.VK_LEFT:
                                case KeyEvent.VK_DOWN:
                                    this.seleccion++;
                                    break;
                                case KeyEvent.VK_RIGHT:
                                case KeyEvent.VK_UP:
                                    this.seleccion--;
                                    break;
                                case KeyEvent.VK_ENTER:
                                    EsEnter = true;
                                    break;
                            }
                            if (this.seleccion >= this.opciones.CuantosElementos) {
                                this.seleccion = 0;
                            }
                            if (this.seleccion <= -1) {
                                this.seleccion = (this.opciones.CuantosElementos - 1);
                            }
                            Thread.sleep(100);
                            this.Despliega(true);
                            Thread.sleep(100);
                        } while (!EsEnter);
                    } catch (IOException | UtileriasException | InterruptedException ex) {
                        Logger.getLogger(UConsole.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                }
                int r = Lee.Entero();
                this.seleccion = r - 1;
            }
        }
    }
}
