import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *Clase que calcula el soborno que se le debe generar a un votante
 *para que vote por el candidato que nosotros deseemos, al final 
 *genera una tabla con el 70% de los votantes a los que se les desea
 *reducir costos
 * @author Adrian Hoyos
 */
public class Votants {
	
	static BufferedReader br = new BufferedReader (new InputStreamReader (System.in));//Leer datos
	static int candidatos;//cantidad de candidatos
	static int votantes;//cantidad de votantes
	static int ganador;//El que deseemos que candidato quiere que gana
	static DecimalFormat df = new DecimalFormat("#");//numero normal				//}
	static DecimalFormat df1 = new DecimalFormat("#.0");//numero con un decimal		// }Creados para generar una buena visualizacion en la consola
	static DecimalFormat df2 = new DecimalFormat("0.00");//numero con dos decimales	//}
	static ArrayList<votantesDatos> votantesDatos = new ArrayList<votantesDatos>();//Array para guardar el nombre y el soborno generado
	static double porcentaje = 0.7;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//lectura de la cantidad de candidatos
		System.out.println( "Introduzca la cantidad de candidatos" );
		candidatos = Integer.parseInt(br.readLine());		
		
		//lectura de la cantidad de votantes
		System.out.println("introduzca la cantidad de votantes");
		votantes = Integer.parseInt(br.readLine());
		
		//matriz, se amplia su tamanio debido a los costos y sobornos
		double matriz[][] = new double [votantes+1][candidatos+3] ;
		
		//Se genera la matriz SIN sobornos
		matriz = generarMatriz(matriz);
		
		//Se visualizan la matriz generada
		imprimirMatriz(matriz);
		
		//Matriz con sobornos calculados
		matriz = sobornos(matriz);
		
		System.out.println("Los sobornos generados por el candidato m"+ ganador  +" por cada votante son: ");
		
		//Se visualizan los valores en consola
		imprimirMatriz(matriz);
		
		//anadir al arrayList el votante con su soborno
		relacionarVotantesConSobornosGenerados(matriz);
		
		//Se ordena el array mediante el soborno en orden ascendente
		ordenarPorSoborno(votantesDatos);
		
		//Muestra el 70% de los votantes que se necesita y el costo total reducido
		generarVotantesYcostosReducidos(votantesDatos);
	}
	
	/**
	 * Metodo para generar la matriz
	 * @param matriz
	 */
	static double[][] generarMatriz(double[][] matriz) {
		Random rd = new Random();
		int m = 1;//numero del candidato
		int n = 1;//numero del votante
		int probabilidades[]; 
		
		//bucle para llenar candidatos y votantes (etiquetas)
		for (int i = 0; i < matriz.length; i++) {
			probabilidades = generadorProbabilidades(candidatos);//probabilidad de cada votante
			for (int j = 0; j < matriz[i].length; j++) {
				
				//introducir los candidatos
				if(i == 0 && j > 0 && j <  matriz[i].length-2) {
					matriz[i][j] = m;
					m++;
				}
				
				//introducir los votantes
				if(i > 0 && j == 0) {
					matriz[i][j] = n;
					n++;
				}
				
				//introducir las probabilidades
				if(i > 0 && j > 0 && j < matriz[i].length-2) {
					matriz[i][j] = probabilidades[j-1];
				}
				
				//Introducir los costos
				if(j == matriz[i].length-2 && i > 0) {
					matriz[i][j] = rd.nextInt(10)+1;
				}
				
				//Poner en 0 el valor del soborno
				if(j == matriz[i].length-1 && i > 0) {
					matriz[i][j] = 0;
				}
				
			}
		}
		
		return matriz;
	}

	
	/**
	 * Metodo que genera las probabilidades que votara un votante por un candidato
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
					array[i] = 100 - probabilidadTotal;
				}else {
					int secuencia = array[i] = rd.nextInt(100-probabilidadTotal) + 1;
					probabilidadTotal += secuencia;
				}
			}
		}
		
		return array;
	}
	
	/**
	 * Metodo para sobornar los votantes
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	static double [][] sobornos(double matriz[][]) throws NumberFormatException, IOException{
		boolean bandera = false;
		
		while(bandera == false){
			try {
				System.out.println("Introduzca el candidato que quiere que gane (SOLO EL NUMERO) y dentro del rango que es de 1 a "+candidatos);
				ganador = Integer.parseInt(br.readLine());
				if(ganador > 0 && ganador <= candidatos) {bandera = true;}
			}catch(Exception e) { bandera = false; }
		}
		
		
		double costo = 0;
		double probabilidadASobornar = 0; 
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if(j == ganador && i > 0) {
					probabilidadASobornar = (100 - matriz[i][j]) / 100;
				}
				else if(i > 0 && j == matriz[i].length - 2) {
					costo = matriz[i][j];
				}
				if( i > 0 && j == matriz[i].length - 1) {
					matriz[i][j] = probabilidadASobornar * costo;
				}
			}
		}
		return matriz;
	}
	
	/**
	 * Metodo que imprime la matriz con todos los datos generados
	 * @param matriz
	 */
	static void imprimirMatriz(double [][] matriz) {
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if(i == 0) {
					if(j == 0) {
						System.out.print("    ");
					}if(matriz[i][j] > 0) {
						System.out.print("m"+df.format(matriz[i][j])+"  ");
					}else if(j == matriz[i].length - 2) {
						System.out.print("Costo");
					}else if(j == matriz[i].length-1) {
						
						System.out.println(" Soborno");
						System.out.print("--------------------------");
					}
				}else if(j == 0) {
					if(i > 0) {
						if(matriz[i][j] > 0 && matriz[i][j] < 10) {
							System.out.print("n0"+df.format(matriz[i][j])+"|");
						}else {
							System.out.print("n"+df.format(matriz[i][j])+"|");
						}
					}
				}
				
				//Imprimir las probabilidades
				if(i > 0 && j > 0 && j < matriz[i].length - 2) {
					if(matriz[i][j] == 0 || matriz[i][j] > 0 && matriz[i][j] < 10) {
						System.out.print("0"+df.format(matriz[i][j])+"% ");
					}else {
						System.out.print(df.format(matriz[i][j])+"% ");
					}
				}
				
				//Imprimir los costos
				if(j == matriz[i].length-2 && i > 0) {
					if(matriz[i][j] < 10) {
						System.out.print(df.format(matriz[i][j])+" ");
					}else {
						System.out.print(df.format(matriz[i][j]));
					}
					
				}
				
				//Imprimir los sobornos
				if(j == matriz[i].length-1 && i > 0) {
					System.out.print("     "+df2.format(matriz[i][j]));
				}
			}
			System.out.println("");
		}
	}
	
	/**
	 * Metodo que asigna en un array el votante con su soborno generado
	 * @param matriz Recibe la matriz con los botantes
	 */
	static void relacionarVotantesConSobornosGenerados(double matriz[][]) {
		for (int i = 1; i < matriz.length; i++) {
			votantesDatos votante = new votantesDatos(matriz[i][0], matriz[i][matriz[i].length-1]);
			votantesDatos.add(votante);
		}
	}
	
	/**
	 * Metodo que ordena por valor de soborno del votante
	 * @param votantes
	 */
	static void ordenarPorSoborno(ArrayList<votantesDatos> votantes) {
		Collections.sort(votantes, new Comparator<votantesDatos>() {
	        @Override 
	        public int compare (votantesDatos p1, votantesDatos p2) {
	            return Double.compare(p1.getSoborno(), p2.getSoborno());
	        }
		});
	}
	
	/**
	 * Metodo que muestra el 70% de los votantes y el costo total reducido
	 */
	static void generarVotantesYcostosReducidos(ArrayList<votantesDatos> votantes) {
		double array [][] = new double [(int) Math.ceil(votantes.size() * porcentaje)][2];//Hacemos la dmension por el porcentaje puesto que lo que queremos es el 70% de los votantes
		double costoTotal = 0;//Variable que ira sumando el costo total
		
		System.out.println("\nEl 70% de los votantes y en orden ascendente por soborno son: \n");
		//Es multiplicado por la variable porcentaje, la cual representa el 70% de los votantes
		for (int i = 0; i < array.length; i++) {
			array[i][0] = votantes.get(i).getNombre();
			array[i][1] = votantes.get(i).getSoborno();
			costoTotal += votantes.get(i).getSoborno();
		}
		
		System.out.println("Votante | Soborno");
		System.out.println("-----------------");
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if(j == 0) {
					if(array[i][j] < 10) {
						System.out.print("n"+df.format(array[i][j])+"         ");
					}else {
						System.out.print("n"+df.format(array[i][j])+"        ");
					}
					
				}else {
					System.out.print(df2.format(array[i][j]));
				}
			}
			System.out.println();
		}
		
		System.out.println("\nEl costo total reducido es de: "+df2.format(costoTotal));
	}
	
}
