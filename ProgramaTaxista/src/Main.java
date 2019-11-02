import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Clase principal para la ejecucion del programa
 * @author adria
 *
 */
public class Main {
	
	public static Stack<String> coordenadasGuardadas = new Stack<String>();
	
    public static void main(String[] args) throws IOException {

        int[] coordenadasOrigen = Coordenadas.devolverCoordenadas("Introduzca las coordenadas de ORIGEN separadas por coma","origen");
        int[] coordenadasDestino = Coordenadas.devolverCoordenadas("Introduzca las coordenadas de DESTINO separadas por coma","destino");
        
        
        if(!Arrays.toString(coordenadasOrigen).equals(Arrays.toString(coordenadasDestino))) {
            System.out.print("\nEl taxi desde las coordenadas [" + coordenadasOrigen[0] + ", " + coordenadasOrigen[1] + "] "
                    + "tiene " + DivideVenceras.taxiDyV(coordenadasOrigen[0], coordenadasOrigen[1], coordenadasDestino[0], coordenadasDestino[1]) +
                    " caminos posibles hasta el destino [" + coordenadasDestino[0] + ", " + coordenadasDestino[1] + "] ");
        }else{
            System.out.println("Ambas coordenadas son el origen, por lo tanto no hay caminos para recorrer");
        }

    }



}
