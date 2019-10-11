import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Clase para metodos del tablero
 * @author adrian
 *
 */
public class Tablero {
	
	static BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
	public static ArrayList<Figuras> figurasAincluirEnElTablero = new ArrayList<Figuras>();
	static DecimalFormat df = new DecimalFormat("#");
	
	/**
	 * Metodo que permitira crear el tablero
	 * @return El tablero inicial
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static int[][] crearTablero() throws NumberFormatException, IOException {
		
		System.out.println("Introduzca el tamaño de su tablero");
		int filas = lectorEnteros("Introduzca las filas: ");
		int columnas = lectorEnteros("Introduzca las columnas: ");
		int [][] matriz = new int [filas][columnas];
		
		return matriz;
	}
	
	/**
	 * Metodo que imprime el tablero generado por el usuario
	 * @param matriz
	 */
	public static void mostrarTablero(int [][] matriz) {
		System.out.println("El tablero generado es: ");
		
		for (int i = 0; i < matriz[0].length; i++) {
			System.out.print(" _");
		}
		
		System.out.println();
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if(j == 0) {
					System.out.print("|_|");
				}else {
					System.out.print("_|");
				}
				
			}
			System.out.println();
		}
		
		System.out.println();
	}
	
	/**
	 * Metodo que evita que el usuario ingrese letras o numeros invalidos
	 * @param mensaje Mensaje que se imprimira
	 * @return La variable entera
	 * @throws IOException Excepcion para el Buffered
	 */
	static int lectorEnteros(String mensaje) throws IOException {
		int variable = 0;
		do {
			try {
				System.out.println( mensaje );
				variable = Integer.parseInt(br.readLine());	
			}catch(NumberFormatException e) {System.out.println("Numero invalido");}
		}while(variable == 0 || variable < 1);
		return variable;
	}
	
	/**
	 * Metodo que metera las figuras en el tablero
	 * @param tablero
	 * @param figura
	 */
	public static double meterFigurasTablero(int [][]tablero, Figuras figura) {
		int restantes = tablero.length * tablero[0].length;
		int piezasTableroLlenas = 0;
		int fichas = figura.getCantidadCuadros();
		boolean metioAlmenosUnaPieza = false;
		double beneficioTotal = 0;
		
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				if(tablero[i][j] == 1) {
					piezasTableroLlenas ++;
				}
			}
		}
		
		if(piezasTableroLlenas < restantes) {
			
			System.out.println("Se procedera a ingresar las figuras");
			System.out.println("Figura a ingresar: ");
			Figuras.mostrarFigura(figura.getDimension());
		
			System.out.println("Figura introducida en el tablero: ");
			int cantidadFichasMetidas = 0;
			for (int i = 0; i < tablero[0].length; i++) {
				System.out.print(" _");
			}
			
			System.out.println();
			
			for (int i = 0; i < tablero.length; i++) {
				for (int j = 0; j < tablero[i].length; j++) {
					
					if(cantidadFichasMetidas < figura.getCantidadCuadros() && tablero[i][j] != 1) {
						tablero[i][j] = 1;
						metioAlmenosUnaPieza = true;
						cantidadFichasMetidas++;
						
					}
					
					if(j == 0 && tablero[i][j] == 1) {
						System.out.print("|*|");
					}
					
					if(tablero[i][j] == 1 && j > 0) {
						System.out.print("*|");
					}
					
					if(tablero[i][j] == 0 && j == 0){
						System.out.print("|_|");
					}
					
					if(j > 0 && tablero[i][j] == 0) {
						System.out.print("_|");
					}
					
				}
				System.out.println();
			}
			
			double piezas = (double) cantidadFichasMetidas;
			double cuadros = (double)figura.getCantidadCuadros();
			double beneficio = figura.getBeneficio();
			
			beneficioTotal = (piezas / cuadros) * beneficio;
			
			System.out.println("Beneficio Ingresado: "+beneficioTotal);
			
			//Si metio almenos una pieza de la figura, sera incluida para el tablero
			if(metioAlmenosUnaPieza == true) {
				figurasAincluirEnElTablero.add(figura);
			}
			
			if(beneficioTotal == figura.getBeneficio()) {
				figura.setMensaje("Esta figura no ha surgido particiones, por lo tanto su beneficio ingresado en el tablero es de: "+beneficioTotal);
			}else {
				figura.setMensaje("Esta figura tuvo que partirse en "+df.format(piezas)+" de sus "+df.format(cuadros)+" piezas, por lo que el beneficio ingresado en el tablero fue de: "+beneficioTotal);
			}
			
		}
		
		System.out.println();
			
			return beneficioTotal;
		
	}

}
