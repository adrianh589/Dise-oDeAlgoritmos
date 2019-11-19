import java.util.Stack;

public class MainBackTracking {

    public static int[][] tablero = {{1,2,3,4,2},
                                     {5,6,7,8,1},
                                     {7,6,3,2,3},
                                     {3,2,1,5,7},
                                     {1,2,3,4,5}};

    public static void main(String[] args) {

        VueltaAtras.backtracking(tablero, 0, 0, new Stack(), new Stack(), new int [tablero.length][tablero.length]);
    }

}
