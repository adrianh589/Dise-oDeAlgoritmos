import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Clase principal para la ejecucion del programa
 * @author adrian
 *
 */
public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		double beneficioMaximo = 0;
		
		//Instancia de la clase tablero
		Tablero tablero = new Tablero();
		
		//ArrayList para guardar las figuras
		ArrayList<Figuras> figurasGeneradas = new ArrayList<Figuras>();
		
		//Se crea el tablero
		int [][] matriz = tablero.crearTablero();
		
		//Se muestra el tablero
		tablero.mostrarTablero(matriz);
		
		System.out.println("=======================================");
		
		//Pedir figuras hasta que ingrese no
		String rta = "";
		while(rta.equals("")) {
			Figuras figuras = new Figuras();
			figurasGeneradas.add(figuras);
			System.out.println("**************************************************************************************");
			System.out.println("Presione ENTER para generar otra figura, o escriba \"no\" para no generar mas figuras");
			rta = br.readLine();
		}
		
		System.out.println("===============================");
		
		//Ordenamos las figuras por Beneficio/Dimension
		Figuras.ordenarPorBeneficioSobreDimension(figurasGeneradas);
		
		//Mostramos las figuras que creó el usuario
		System.out.println("Las figuras generadas por usted y ordenadas por Beneficio/Dimension son: ");
		for (int i = 0; i < figurasGeneradas.size(); i++) {
			System.out.println("Figura "+(i+1));
			Figuras.mostrarFigura(figurasGeneradas.get(i).getDimension());
			System.out.println("Beneficio: "+figurasGeneradas.get(i).getBeneficio());
			System.out.println("Beneficio/Dimension: "+figurasGeneradas.get(i).getBeneficioDivDimension()+"\n");
		}
		
		System.out.println("=========================================");
		
		double aux;
		//Procedemos a meter las figuras en el tablero
		for (int i = 0; i < figurasGeneradas.size(); i++) {
			aux = tablero.meterFigurasTablero(matriz, figurasGeneradas.get(i));
			beneficioMaximo += aux;
		}
		
		//Mostramos las figuras que se eligieron para incluir en el tablero
		System.out.println("Las figuras escogidas a incluir en el tablero son: \n===========================================");
		for (int i = 0; i < tablero.figurasAincluirEnElTablero.size(); i++) {
			Figuras.mostrarFigura(tablero.figurasAincluirEnElTablero.get(i).getDimension());
			System.out.println(tablero.figurasAincluirEnElTablero.get(i).getMensaje());
		}
		
		System.out.println("\nEl beneficio maximo es: "+beneficioMaximo);
		
	}

}
