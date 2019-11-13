package Biseccion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.script.ScriptException;

/**
 * Clase principal para la ejecucion del algoritmo de Biseccion
 * @author Adrian Hoyos
 */
public class Main {

    public static void main(String[] args) throws IOException, ScriptException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ecuacion = ecuacionOperaciones.capturarFormula();
        double[] interval = ecuacionOperaciones.intervalo("Introduzca el intervalo para hacer la biseccion", br);
        System.out.println(DivideYVenceras.DyVBiseccion(interval[0], interval[1], ecuacion));
    }
}
