import java.util.Stack;

public class BackTracking {

    static int minimoCoste = Integer.MAX_VALUE;
    public static int[][] solucionFinal ;

    /**
     * Metodo backtracking
     * @param tablero
     * @param persona
     * @param conteo
     * @param bloquearTarea
     * @return
     */
    static boolean VueltaAtras(int[][] tablero, int persona, int conteo, Stack<Integer> bloquearTarea, int[][] solucion){

        if(esSolucion(solucion)){
            minimoCoste = conteo;
            solucionFinal = solucion;
            imprimir(solucion);
            System.out.println("Costo minimo encontrado: "+conteo+" \n");
            return false;//Al llegar al final nos devolvemos
        }else{
            for (int tarea = 0; tarea < tablero.length; tarea++) {

                if(!bloquearTarea.contains(tarea)){
                    bloquearTarea.add(tarea);//Bloqueamos la tarea
                    conteo += tablero[persona][tarea];//sumamos el conteo
                    solucion[persona][tarea] = tablero[persona][tarea];//mantenemos una solucion parcial
                    imprimir(solucion);

                        if(conteo > minimoCoste){//Nos devolvemos si el costo supera al minimo
                            System.out.println("El costo de esta via es "+conteo+" y el minimo costo es de "+minimoCoste+" se hara vuelta atras");
                            imprimir(solucion);
                            if(tarea == tablero.length-1) {//Si hay mas tareas disponibles no podemos devolvernos todavia
                                return false;
                            }else{//En caso de que hayan tareas disponibles, eliminamos lo temporal y vamos a preguntar por la siguiente tarea
                                solucion[persona][tarea] = 0; conteo -= tablero[persona][tarea]; bloquearTarea.pop();
                                imprimir(solucion);
                                continue;//Se pregunta por la siguiente tarea
                            }
                        }else {//Si no ha superado el minimo, podemos seguir asignando tareas
                            //Aqui es donde se indica que vamos a avanzar, se observa que se llama la misma funcion pero con persona + 1
                            VueltaAtras(tablero, persona+1, conteo, recordarTareas(bloquearTarea), solucionActual(solucion));
                            //Borramos lo que llevamos
                                conteo -= tablero[persona][tarea]; bloquearTarea.pop(); solucion[persona][tarea] = 0; imprimir(solucion);
                        }
                }
            }
            return false;//Si no hay mas tareas disponibles nos devolvemos
        }
    }

    /**
     * Metodo para evitar que las tareas sean las mismas para todos los nodos
     * @param bloquearTareas
     * @return
     */
    static Stack<Integer> recordarTareas(Stack<Integer> bloquearTareas){
        Stack<Integer> recordar = new Stack<Integer>();

        for (int i = 0; i < bloquearTareas.size(); i++) {
            recordar.add(bloquearTareas.get(i));
        }

        return recordar;
    }

    /**
     * Guardamos la solucion que lleva
     * @param solucionParcial
     * @return
     */
    static int[][] solucionActual(int[][] solucionParcial){
        int[][] solucion = new int[solucionParcial.length][solucionParcial.length];
        for (int i = 0; i < solucionParcial.length; i++) {
            for (int j = 0; j < solucionParcial.length; j++) {
                solucion[i][j] = solucionParcial[i][j];
            }
        }
        return solucion;
    }

    /**
     * Metodo para comprobar si se llego a una solucion
     * @param tablero
     * @return
     */
    static boolean esSolucion(int[][] tablero){
        int counter = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if(tablero[i][j] != 0){
                    counter+=1;
                }
            }
        }
        if(counter == tablero.length){
            return true;
        }else{
            return  false;
        }

    }

    /**
     * MEtodo para imprimir los pasos de la solucion
     * @param solucion
     */
    static void imprimir(int[][] solucion){
        for (int i = 0; i < solucion.length; i++) {
            for (int j = 0; j < solucion.length; j++) {
                System.out.print(solucion[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println();
    }

    /**
     * Metodo que dira que tarea debe elegir cada persona para reducir tiempos
     * @param asignador
     */
    public static void asignadorTareas(int[][] asignador){
        for (int i = 0; i < asignador.length; i++) {
            for (int j = 0; j < asignador.length; j++) {
                if(asignador[i][j]!=0){
                    System.out.println("Persona "+(i+1)+" tarea "+(j+1));
                }
            }
        }
    }

}
