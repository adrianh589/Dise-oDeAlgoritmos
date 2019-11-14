import java.util.ArrayList;

public class back2 {

    public static int[][] tablero = {{1,2,3},
                                     {4,5,6},
                                     {7,8,9}};

    public boolean backtrack(int i, int j){
        // Caso base cuando alcanzamos el objetivo, hemos resuelto el problema.
        if (esSolucion(tablero)) {
            return true;
        }
return true;
    }

    private static boolean dentroDeLimites(int row, int col) {
        boolean result =  row >= 0 && row < tablero.length
                && col >= 0 && col < tablero[row].length
                && (tablero[row][col] == 1 || tablero[row][col] == 3);
        return result;
    }

    //Verificar si se llego a la solucion
    static boolean esSolucion(int [][] tablero){
        int elementos = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if(tablero[i][j] != 0){
                    elementos+=1;
                }
            }
        }

        if (elementos == tablero.length){
            return true;
        }else{
            return false;
        }
    }

    public static void imprimir(ArrayList<int[][]> soluciones){

        for (int z = soluciones.size()-1; z < soluciones.size(); z++){

            for (int i = 0; i < soluciones.get(z).length; i++) {
                for (int j = 0; j < soluciones.get(z).length; j++) {
                    System.out.print(soluciones.get(z)[i][j]+" ");
                }
                System.out.println();
            }

        }

        System.out.println();

    }
}
