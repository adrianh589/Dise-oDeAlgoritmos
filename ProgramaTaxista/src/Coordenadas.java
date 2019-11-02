import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * Clase para recibir las coordenadas que ingrese el usuario
 * @author Adrian Hoyos
 *
 */
public class Coordenadas {
	
	/**
     * Metodo para leer las coordenadas, ya sean de origen o destino
     * @param mensaje Indicacion para el usuario
     * @param tipo Tipo de coordenadas
     * @return Devuelve las coordenadas en enteros
     * @throws IOException
     */
    static int[] devolverCoordenadas(String mensaje,String tipo) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] coordenadasInt = new int[2];
        System.out.println(mensaje);

        String coordenadasCadena;
        String[] coordenadasString = null;
        boolean bandera = false;
        do {
            try {
                coordenadasCadena = br.readLine();//Recibimos la entrada del usuario
                coordenadasString = coordenadasCadena.split(",");//Separamos por coma dentro de un array de String

                if (coordenadasString.length != 2){
                    throw new ArithmeticException();
                }

                for (int i = 0; i < coordenadasString.length; i++) {
                    coordenadasInt[i] = Integer.parseInt(coordenadasString[i]);//Se convierte el array a enteros
                }

                bandera = true;

            }catch (NumberFormatException e){//Si se ingreso algo diferente de un numero
                System.out.println("Error: Ha ingresado un numero invalido, porfavor vuelva a intentarlo");
            } catch (ArithmeticException a){
                System.out.println("Porfavor, ingrese solo dos numeros, vuelva a intentarlo");
            }
        }while (bandera == false);



        System.out.println("Coordenadas de "+tipo);
        System.out.println("X = "+coordenadasInt[0]);
        System.out.println("Y = "+coordenadasInt[1]);

        return coordenadasInt;//Se devuelve el array de enteros
    }
    
    

}
