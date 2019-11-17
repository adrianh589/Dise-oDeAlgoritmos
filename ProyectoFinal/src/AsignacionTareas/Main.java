package AsignacionTareas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Stack;

public class Main {



    public static void main(String[] args) throws NumberFormatException, IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	BackTracking backTrack = new BackTracking();
    	System.out.println("Ingrese la cantidad a calcular, recuerde que personas = tareas");
    	int n = Integer.parseInt(br.readLine());
    	int[][] tablero = llenarMatrizAleatoria(n);
    	System.out.println("Su tablero generado es: ");
    	backTrack.imprimir(tablero);
    	System.out.println("\nA continuacion se iniciara con el algoritmo...");
    	

        int persona = 0;
        int tarea = 0;
        int[][] solucionParcial = new int [tablero.length][tablero.length];

        backTrack.VueltaAtras(tablero, persona, 0, new Stack(), solucionParcial);
        backTrack.asignadorTareas(backTrack.solucionFinal);
	System.out.println("El coste minimo fue de: "+backTrack.minimoCoste);
    }
    
    /**
	 * Llenar matriz aleatoria
	 * 
	 * @param length
	 * @return
	 */
	static int[][] llenarMatrizAleatoria(int length) {
		Random rd = new Random();
		int matriz[][] = new int[length][length];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = rd.nextInt(9)+1;
			}
		}
		return matriz;
	}



}
