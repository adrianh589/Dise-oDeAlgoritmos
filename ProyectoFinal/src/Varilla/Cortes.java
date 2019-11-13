package Varilla;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase para metodos de los cortes de la varilla
 * @author: Adrian Hoyos
 */
public class Cortes {

    private int longitud;
    private int piezas;

    Cortes(){}

    Cortes(int longitud, int piezas){
        this.longitud = longitud;
        this.piezas = piezas;
    }

    //Getters y Setters

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getPiezas() {
        return piezas;
    }

    public void setPiezas(int piezas) {
        this.piezas += piezas ;
    }

    /**
     * Metodo para capturar la lista de precios de manera manual
     * @param br Lector
     * @return La la lista de precios generados
     * @throws IOException
     */
    static int[] preciosCortes(BufferedReader br) throws IOException {
        System.out.println("Ingrese los precios separados por coma");
        String arrAux[] = br.readLine().split(",");
        int arr[] = new int[arrAux.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(arrAux[i]);
        }
        return arr;
    }

    /**
     * Metodo para guardar cortes
     * @param matriz
     * @return
     */
    static void numeroDeCortes (int [][] matriz){

        int[] posActual = {matriz.length-1, matriz[0].length-1};

        while ( posActual[0] >= 0 )
        {
            while ( posActual[1] >= 0 )
            {
                if(posActual[1] <= 1){
                    break;
                }
                if (matriz[posActual[0]][posActual[1]] == 1) {
                    contadorDeCortes(new Cortes(posActual[0], 1));
                    posActual = guardaPos( posActual[0] ,posActual[1] - posActual[0]);
                }else{
                    posActual = guardaPos(posActual[0] - 1, posActual[1]);
                }
            }

            posActual[0] -= 1;
        }
    }

    /**
     * Metodo que verifica si ya se hizo un corte
     * @param corte
     */
    static void contadorDeCortes(Cortes corte){

        if(Main.GUARDARECORTES.isEmpty()){//Si no hay recortes, se añade
            Main.GUARDARECORTES.add(corte);
        }else {
            //Se pŕegunta si ya se ha hecho un corte de la misma longitud
            boolean bandera = false;//Bandera para indicar si se encontro o no el corte
            for (int i = 0; i < Main.GUARDARECORTES.size(); i++) {
                if (Main.GUARDARECORTES.get(i).getLongitud() == corte.getLongitud()) {
                    Main.GUARDARECORTES.get(i).setPiezas(1);
                    bandera = true;//Si se encontro el corte, indicamos que lo encontro
                }
            }

            if (bandera == false){//Si no lo encontro, se añade el nuevo corte
                Main.GUARDARECORTES.add(corte);
            }

        }

    }

    /**
     * Metodo para saber cuantos cortes se necesitan segun el beneficio generado
     */
    static void cortesNecesarios(){
        numeroDeCortes(Main.tablaDecisiones);
        for (int i = 0; i < Main.GUARDARECORTES.size(); i++) {
            System.out.println(Main.GUARDARECORTES.get(i).getPiezas() + " piezas de longitud "+Main.GUARDARECORTES.get(i).getLongitud());
        }
    }

    /**
     * Metodo para guardar la posicion actual recorrida
     * @param i
     * @param j
     * @return
     */
    static int[] guardaPos(int i, int j){
        int[] array = new int [2];
        array[0] = i;
        array[1] = j;
        return array;
    }


}
