package Varilla;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase principal para ejecutar el Algoritmo de la varilla
 * @author: Adrian Hoyos
 */
public class Main {

    static int[][] tablaDinamica;
    static int[][] tablaDecisiones;
    static ArrayList<Cortes> GUARDARECORTES = new ArrayList<Cortes>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //int[] arr = Cortes.preciosCortes(br);
        int[] arr = {1,5,8,9,10,17,17,20,24,30}; //Descomentar esta linea para arreglo por defecto
        System.out.println("Sus precios por defecto son: \n"+Arrays.toString(arr));

        System.out.println("Ingrese el tamanio de la varilla");
        int longitud = Integer.parseInt(br.readLine());

        int beneficio = operacionesVarilla.cortarVarilla(arr, longitud);

        System.out.println("\nLa tabla dinamica es: \n");
        operacionesVarilla.imprimirMatriz(tablaDinamica);

        System.out.println("\nLa tabla de decisiones es: \n");
       operacionesVarilla.imprimirMatriz(tablaDecisiones);

        System.out.println("\nEl beneficio maximo es: "+beneficio);

        System.out.println("\nLos cortes necesarios son: ");
        Cortes.cortesNecesarios();

    }

}
