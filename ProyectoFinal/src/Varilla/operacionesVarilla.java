package Varilla;

/**
 * Clase para metodos con la varilla
 * @author: Adrian Hoyos
 */
public class operacionesVarilla {

    /**
     * Metodo para cortar la varilla
     * @param precio
     * @param longitud
     * @return
     */
    static int cortarVarilla(int[] precio, int longitud){
        //Creamos la matriz de valores
        int[][] capturarDatos = llenarMatrizValores(precio, longitud);
        Main.tablaDecisiones = llenarMatrizValores(precio, longitud);
        //Establecemos una variable para tener el maximo beneficio
        int maximoBeneficio = Integer.MIN_VALUE;

        for (int i = 1; i < capturarDatos.length; i++) {
            for (int j = 2; j < capturarDatos[i].length; j++) {
                if (i == 1) {//Segunda fila de operaciones
                    capturarDatos[i][j] = capturarDatos[i - 1][j] * capturarDatos[i][0];
                    if (maximoBeneficio < capturarDatos[i][j]) { maximoBeneficio = capturarDatos[i][j]; }
                    Main.tablaDecisiones[i][j] = capturarDatos[i][j];//En la tabla de decisiones ponemos la cantidad de cortes que uso para generar ese ingreso
                } else if (capturarDatos[0][j] < i) {//Se pregunta si la longitud es menor que i debido a que en este ejercicio se simula que la longitud esta al lado de la fila
                    capturarDatos[i][j] = capturarDatos[i - 1][j];
                } else {//En caso de que la longitud sea igual, se procede a verificar cual es mas optimo, si el numero de arriba o el nuevo que se va a calcular, el cual es
                    //generado con la ecuacion de concurrencia, restando la longitud y sumandole el precio, donde se define el maximo entre este o el precio que vale dicho corte
                    maximoBeneficio = capturarDatos[i][j] = Math.max(capturarDatos[i][j - i] + capturarDatos[i][0], capturarDatos[i - 1][j]);
                    if(i >= capturarDatos[0][i+1]){
                        if(capturarDatos[i][j] == capturarDatos[i-1][j]) {
                            Main.tablaDecisiones[i][j] = 0;/*si el numero calculado es igual a uno calculado anteriormente, es porque no tomo el corte actual*/
                        }else{
                            Main.tablaDecisiones[i][j] = 1;/*se verifican cuantos cortes caben en el precio*/
                        }
                    }
                }
            }
        }
        Main.tablaDinamica = capturarDatos.clone();
        return maximoBeneficio;
    }

    /**
     * Metodo para imprimir
     */
    static void imprimirMatriz(int[][] matriz){

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(j == 0){
                    System.out.print(i+") ");
                }
                if(matriz[i][j] < 10 && matriz[i][j] >= 0){System.out.print("0"+matriz[i][j]+" ");}else{
                    System.out.print(matriz[i][j]+" ");}

            }
            System.out.println();
        }

    }

    /**
     * Metodo para inicializar la matriz con los precios y valores
     * @param precio
     * @param longitud
     * @return
     */
    static int[][] llenarMatrizValores(int[] precio, int longitud){
        int[][] capturarDatos = new int[longitud + 1][longitud + 2];

        int incrementadorFilas = 1;

        //introducimos los valores iniciales los cuales son los precios(pimera columna) y los cortes (primera fila)
        for (int i = 0; i < capturarDatos.length; i++) {
            for (int j = 0; j < capturarDatos[i].length; j++) {
                if(i == 0 && j > 1){capturarDatos[i][j] = incrementadorFilas; incrementadorFilas++;}
                if(i > 0 && j == 0){capturarDatos[i][j] = precio[i-1];}
            }
        }
        return capturarDatos;
    }

}
