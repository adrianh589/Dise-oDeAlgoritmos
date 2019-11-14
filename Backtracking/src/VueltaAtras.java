import java.util.Stack;

public class VueltaAtras {

    public static void backtracking(int[][] tablero, int persona, int tarea, Stack<Integer> bloquearPersona, Stack<Integer> bloquearTarea, int[][] solucionParcial) {

        if (esSolucion(solucionParcial)) {//Caso base
            imprimir(solucionParcial);
            solucionParcial[bloquearPersona.pop()][bloquearTarea.pop()] = 0;
            return;
        } else {//Procedimiento

                for (int j = 0; j < tablero.length; j++) {//Nos enfocamos en las tareas
                    if (!(bloquearTarea.contains(j))) {//Si la tarea y la persona no estan bloqueadas
                        bloquearTarea.add(j); bloquearPersona.add(persona);//La persona es necesaria para eliminar la tarea
                        solucionParcial[persona][j] = tablero[persona][j];//Armamos nuestra solucion parcial
                         backtracking(tablero, persona+1, j, bloquearPersona, bloquearTarea, solucionParcial);//Volvemos a llamar a la funcion
                    }

                }
                if (!bloquearPersona.isEmpty()){
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
        System.out.println();

    }
    
}
