package AsignacionTareas;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Clase BackTracking que se emplea para la asignacion de tareas
 * @author Adrian Hoyos
 *
 */
public class BackTracking {

    
    static int sumaActual = 0;
    static int minimoCoste = Integer.MAX_VALUE;
    static ArrayList<int[][]> solucionFinal= new ArrayList<int[][]>();

    /**
     * Metodo para realizar el backtracking
     * @param tablero
     * @param persona
     * @param tarea
     * @param bloquearPersona
     * @param bloquearTarea
     * @param solucionParcial
     */
    public static void backtracking( int[][] tablero, int persona, int tarea, Stack<Integer> bloquearPersona, Stack<Integer> bloquearTarea, int[][] solucionParcial ) {

        if (esSolucion(solucionParcial)) {//Caso base
            solucionFinal.add(clonarMatriz(solucionParcial));//Añadimos esa solucion como postulante
            imprimir(solucionParcial);//Imprimimos la solucion parcial
            System.out.println("Esta solucion por el momento es la mas optima con un coste de: "+darCoste(solucionParcial)+"\n");
            minimoCoste = sumaActual;//El minimo coste ahora es la solucion encontrada
            sumaActual -= solucionParcial[bloquearPersona.peek()][bloquearTarea.peek()];//Reducimos el coste actual puesto que nos estamos devolviendo
            solucionParcial[bloquearPersona.pop()][bloquearTarea.pop()] = 0;//Eliminamos la solucion parcial
            return;
        } else {//Procedimiento

                for (int j = 0; j < tablero.length; j++) {//Nos enfocamos en las tareas
                    if (!(bloquearTarea.contains(j))) {//Si la tarea y la persona no estan bloqueadas
                        bloquearTarea.add(j); bloquearPersona.add(persona);//La persona es necesaria para eliminar la tarea
                        solucionParcial[persona][j] = tablero[persona][j];//Armamos nuestra solucion parcial
                        sumaActual += tablero[persona][j];
                        if(sumaActual > minimoCoste){//Si la suma actual es mayor que el minimo coste encontrado, no se va por esa via
                            imprimir(solucionParcial);
                            System.out.println("Esta via no es optima, por lo tanto se hara vuelta atras puesto que el coste minimo actual es de "+minimoCoste+" y esta via representa un coste de "+darCoste(solucionParcial)+"\n");
                            sumaActual -= solucionParcial[bloquearPersona.pop()][bloquearTarea.pop()];
                            solucionParcial[persona][j] = 0;
                        }else {
                            backtracking(tablero, persona + 1, j, bloquearPersona, bloquearTarea, solucionParcial);//Si la suma actual es optima, nos vamos por esa via
                        }
                    }
                }
                if (!bloquearPersona.isEmpty()){//Al devolvernos hasta la primera persona, debemos tener en cuenta que la tarea que le asignaremos es la que le sigue
                    sumaActual -= solucionParcial[bloquearPersona.peek()][bloquearTarea.peek()];
                    solucionParcial[bloquearPersona.pop()][bloquearTarea.pop()] = 0;
                }
        }
    }

    /**
     * Verificar si es la solucion
     * @param tablero
     * @return
     */
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

    /**
     * MAtriz que dice el cost de una posible solucion
     * @param solucionParcial
     * @return
     */
    static int darCoste(int[][] solucionParcial){
        int coste = 0;
        for (int i = 0; i < solucionParcial.length; i++) {
            for (int j = 0; j < solucionParcial.length; j++) {
                if(solucionParcial[i][j] != 0){
                    coste+= solucionParcial[i][j];
                }
            }
        }
        return coste;
    }

    /**
     * Metodo para imprimir la solucion encontrada
     * @param solucion
     */
    public static void imprimir(int[][] solucion){

            for (int i = 0; i < solucion.length; i++) {
                for (int j = 0; j < solucion.length; j++) {
                    System.out.print(solucion[i][j]+" ");
                }
                System.out.println();
            }

    }

    /**
     * Metodo para clonar la matriz, debido a que se modifica
     * @param solucion
     * @return
     */
    static int[][] clonarMatriz(int[][] solucion){
        int[][] aux = new int [solucion.length][solucion.length];

        for (int i = 0; i < solucion.length; i++) {
            for (int j = 0; j < solucion.length; j++) {
                aux[i][j] = solucion[i][j];
            }
        }

        return aux;

    }

    /**
     * Metodo que dice las posibles soluciones dependiendo del coste minimo
     * @param solucionesFinales
     */
    public static void darPosiblesSoluciones(ArrayList<int[][]> solucionesFinales){

        System.out.println("Las posibles soluciones segun el coste generado son: \n");

        for (int i = 0; i < solucionesFinales.size(); i++) {

            if(darCoste(solucionesFinales.get(i)) == minimoCoste) {

                for (int j = 0; j < solucionesFinales.get(i).length; j++) {
                    for (int k = 0; k < solucionesFinales.get(i).length; k++) {
                        if(solucionesFinales.get(i)[j][k] != 0){
                            System.out.println("persona "+(j+1)+" tarea "+(k+1));
                        }
                    }
                }

                System.out.println();
            }

        }

    }

}
