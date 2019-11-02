import java.util.ArrayList;

/**
 * Clase que usa divide y venceras para lograr los posibles caminos que tiene un taxi
 * hasta llegar al destino
 * @author Adrian Hoyos
 *
 */
public class DivideVenceras {
	
    /**
     * Metodo con divide y venceras que dice cuantos caminos hay para llegar a un destino
     * @param xOrig posicion inicial en X del taxi
     * @param yOrig posicion inicial en Y del taxi
     * @param xDest posicion final en X del taxi, esta posicion es estatica
     * @param yDest posicion final en Y del taxi, esta posicion es estatica
     * @return Devuelve la cantidad de caminos posibles hasta el destino
     */
    static long taxiDyV(int xOrig, int yOrig, int xDest, int yDest){

        Main.coordenadasGuardadas.add("("+xOrig+","+yOrig+")");

        if(xOrig == xDest && yOrig == yDest){//Caso Base, si llego al destino es porque es un camino
            System.out.println(Main.coordenadasGuardadas);
            Main.coordenadasGuardadas.pop();
            return 1;
        }else if(xOrig > xDest || yOrig < yDest){//Si se sale del camino es porque ya no cuenta como camino
            Main.coordenadasGuardadas.pop();
            return 0;
        }

        long caminoHaciaElSur = taxiDyV(xOrig,yOrig-1,xDest,yDest);//Toma el camino hacia el sur
        long caminoHaciaElEste = taxiDyV(xOrig+1,yOrig,xDest,yDest);//Toma el camino hacia el este

        Main.coordenadasGuardadas.pop();

        return caminoHaciaElSur + caminoHaciaElEste;//Suma ambos caminos
    }

}
