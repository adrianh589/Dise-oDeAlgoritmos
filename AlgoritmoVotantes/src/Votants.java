import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Votants {
	
	static int candidatos;
	static int votantes;
	static int ganador;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		
		System.out.println("Introduzca la cantidad de candidatos");
		candidatos = Integer.parseInt(br.readLine());
		
		System.out.println("introduzca la cantidad de votantes");
		votantes = Integer.parseInt(br.readLine());
		
		int matriz[][] = new int [votantes+1][candidatos+2] ;
		generarMatriz(matriz);
		
		System.out.println("Introduzca el votante que quiere que gane");
		ganador = Integer.parseInt(br.readLine());
		
		

	}
	
	/**
	 * Metodo para generar la matriz
	 * @param matriz
	 */
	static void generarMatriz(int matriz[][]) {
		Random rd = new Random();
		int m = 1;//numero del candidato
		int n = 1;//numero del votante
		int probabilidades[]; 
		
		//bucle para llenar candidatos y votantes (etiquetas)
		for (int i = 0; i < matriz.length; i++) {
			probabilidades = generadorProbabilidades(candidatos);//pribabilidad de cada votante
			for (int j = 0; j < matriz[i].length; j++) {
				
				//separacion inicial de la matriz
				if(i==0 && j == 0) {
					System.out.print("    ");
				}
				
				//introducir los candidatos
				if(i == 0 && j > 0 && j <  matriz[i].length-1) {
					matriz[i][j] = m;
					m++;
					System.out.print("m"+matriz[i][j]+" ");
				}
				
				//introducir el costo y la barra de separacion
				if(i == 0 && j == matriz[i].length-1) {
					System.out.println("Costo");
					System.out.print("------------------");
				}
				
				//introducir los votantes
				if(i > 0 && j == 0) {
					matriz[i][j] = n;
					n++;
					if(matriz[i][j] > 0 && matriz[i][j] < 10) {
						System.out.print("n0"+matriz[i][j]+"|");
					}else {
						System.out.print("n"+matriz[i][j]+"|");
					}
				}
				
				//introducir las probabilidades
				if(i > 0 && j > 0 && j < matriz[i].length-1) {
					matriz[i][j] = probabilidades[j-1];
					if(matriz[i][j] == 0 || matriz[i][j] > 0 && matriz[i][j] < 10) {
						System.out.print("0"+matriz[i][j]+" ");
					}else {
						System.out.print(matriz[i][j]+" ");
					}
				}
				
				//Introducir los costos
				if(j == matriz[i].length-1 && i > 0) {
					matriz[i][j] = rd.nextInt(10)+1;
					System.out.print(matriz[i][j]);
				}
				
			}
			System.out.println();
		}
		
		
	}

	
	/**
	 * Metodo que genera las probabilidades que botara un votante por un candidato
	 */
	static int [] generadorProbabilidades(int candidatos){
		Random rd = new Random();
		int array[] = new int[candidatos];
		int probabilidadTotal = 0;
		
		for (int i = 0; i < array.length; i++) {
			if(i == 0) {
				int inicio = array[0] = rd.nextInt(100) + 1;
				probabilidadTotal += inicio;
			}else {
				if(i == array.length-1 || probabilidadTotal == 100) {
					array[i] = 100-probabilidadTotal;
				}else {
					int secuencia = array[i] = rd.nextInt(100-probabilidadTotal) + 1;
					probabilidadTotal += secuencia;
				}
			}
		}
		
		return array;
	}
	
	/**
	 * metodo para sobornar los votantes
	 */
	static void sobornos(int matriz[][]){
		
		int totalVotantes = votantes;
		while(totalVotantes > 0) {
			
			
			
			
			totalVotantes--;
		}
	}
	
}
