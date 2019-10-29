import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
    public static void main(String[] args) throws IOException {

        int[] coordenadasOrigen = devolverCoordenadas("Introduzca las coordenadas de ORIGEN separadas por coma","origen");
        int[] coordenadasDestino = devolverCoordenadas("Introduzca las coordenadas de DESTINO separadas por coma","destino");

        if(!Arrays.toString(coordenadasOrigen).equals(Arrays.toString(coordenadasDestino))) {
            System.out.print("\nEl taxi desde las coordenadas [" + coordenadasOrigen[0] + ", " + coordenadasOrigen[1] + "] "
                    + "tiene " + taxiDyV(coordenadasOrigen[0], coordenadasOrigen[1], coordenadasDestino[0], coordenadasDestino[1]) +
                    " caminos posibles hasta el destino [" + coordenadasDestino[0] + ", " + coordenadasDestino[1] + "] ");
        }else{
            System.out.println("Ambas coordenadas son el origen, por lo tanto no hay caminos para recorrer");
        }

    }

    /**
     * Metodo con divide y venceras que dice cuantos caminos hay para llegar a un destino
     * @param xOrig posicion inicial en X del taxi
     * @param yOrig posicion inicial en Y del taxi
     * @param xDest posicion final en X del taxi, esta posicion es estatica
     * @param yDest posicion final en Y del taxi, esta posicion es estatica
     * @return Devuelve la cantidad de caminos posibles hasta el destino
     */
    static long taxiDyV(int xOrig, int yOrig, int xDest, int yDest){
        if(xOrig == xDest && yOrig == yDest){//Caso Base, si llego al destino es porque es un camino
            return 1;
        }else if(xOrig > xDest || yOrig < yDest){//Si se sale del camino es porque ya no cuenta como camino
            return 0;
        }

        long caminoHaciaElSur = taxiDyV(xOrig,yOrig-1,xDest,yDest);//Toma el camino hacia el sur
        long caminoHaciaElEste = taxiDyV(xOrig+1,yOrig,xDest,yDest);//Toma el camino hacia el este

        return caminoHaciaElSur + caminoHaciaElEste;//Suma ambos caminos
    }


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
