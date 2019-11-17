package Biseccion;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Clase que contiene metodos para la captura de las formulas
 * @author Adrian Hoyos
 */
public class ecuacionOperaciones {

    /**
     * Metodo que captura la ecuacion a evaluar
     * @return Retorna el valor con el epresor dado
     * @throws IOException
     * @throws ScriptException
     */
    static String capturarFormula() throws IOException, ScriptException {
        String ecuacion = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean bandera = false;
        double resultado = 0;

        while (bandera == false) {
            try {
                System.out.println("Porfavor, introduzca su ecuacion, en caso de polinomios con (parentesis) usar Math.pow: ");
                ecuacion = br.readLine();//Se captura la formula

                if(! (ecuacion.contains("(") || ecuacion.contains(")")) ){


                    String[] ecuacionString = ecuacion.split("");

                    for (int i = 1; i < ecuacionString.length; i++) {
                        if(ecuacionString[i].equals("x")){
                            if(isNumeric(ecuacionString[i-1])){
                                ecuacionString[i] = "*x";
                            }else{
                                ecuacionString[i] = "x";
                            }
                        }
                    }

                    ecuacion = "";//Borramos el contenido de la ecuacion y lo volvemos a lenguaje que deduzca la maquina
                    for (int i = 0; i < ecuacionString.length; i++) {
                        ecuacion += ecuacionString[i];
                    }

                    //System.out.println("Despues de la conversion: "+ecuacion);

                    /*
                     *Se agrega Math.pow para evitar escribir toda la expresion
                     */
                    String []array = ecuacion.split("");//Convertir el string en array
                    Stack<String> pila = new Stack<String>();//pila para guardar los valores anteriores al ^
                    String cad1 = "";//parte izquierda del ^
                    String cad2 = "";//parte derecha del ^

                    for (int i = 0; i < array.length; i++) {//Recorremos el string
                        if(array[i].equals("^")){//Cuando encuentre un ^ empezara a añadir los valores a la pila
                            for (int j = i - 1; j >= 0  ; j--) {
                                pila.add(array[j]);
                                array[j] = "";
                                if ( j >=1 ){
                                    if (array[j - 1].equals("*") || array[j - 1].equals("+") || array[j - 1].equals("-") || array[j - 1].equals("/") || j - 1 < 0 || array[j - 1].equals("(") || array[j - 1].equals(")")) {
                                        break;
                                    }
                            }
                            }

                            while (!pila.isEmpty()){//Formamos la parte izquierda del ^
                                cad1 += pila.pop();
                            }

                            //Parte derecha del ^
                            for (int k = i+1; k < array.length; k++) {
                                    cad2 += array[k];
                                    array[k] = "";
                                if ( k < array.length-1 ) {
                                    if (array[k + 1].equals("*") || array[k + 1].equals("+") || array[k + 1].equals("-") || array[k + 1].equals("/") || k + 1 >= array.length || array[k - 1].equals("(") || array[k - 1].equals(")")) {
                                        break;
                                    }
                                }
                            }
                            //Reemplazamos
                            array[i] = "Math.pow("+cad1 +","+ cad2+")";
                            cad1 = "";
                            cad2 = "";
                        }
                    }


                    ecuacion = "";//Borramos el contenido de la ecuacion y lo volvemos a lenguaje que deduzca la maquina
                    for (int i = 0; i < array.length; i++) {
                        ecuacion += array[i];
                    }



                    for (int i = 0; i < ecuacion.length(); i++) {//Aqui se elimina * en caso de estar antes un ( o una , debido al Math.pow
                        if (ecuacion.charAt(i) == '*') {
                            if (ecuacion.charAt(i - 1) == ',' || ecuacion.charAt(i - 1) == '(') {
                                ecuacion = ecuacion.substring(0, i).concat(ecuacion.substring(i + 1, ecuacion.length()));
                            }
                        }
                    }

                }

                System.out.println(ecuacion);//nos cersioramos de que la formula sea correcta

                evaluarFormula(ecuacion,1);//Si retorna un resultado, la formula es valida

                bandera = true;
            } catch (Exception e) {
                System.out.println("Error: Ha escrito mal la formula");
            }
        }

        return  ecuacion;
    }

    /**
     * Metodo para evaluar la ecuacion
     * @param ecuacion
     * @param expresor
     * @throws ScriptException
     */
    static double evaluarFormula(String ecuacion, double expresor) throws ScriptException {

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.put("x", expresor);//Las X la cambiamos por el numero a evaluar
        Object operation = engine.eval(ecuacion);
        double resultado = (double) operation;
        return resultado;
    }

    /**
     * MEtodo para capturar el intervalo que introduzca el usuario
     * @param mensaje Mensaje para indicarle al usuario que debe ingresar un intervalo
     * @param br Lector para captura de datos
     * @return Regresa el limite inferior y el limite superior de un intervalo cerrado dado por el usuario
     * @throws IOException
     */
    static double[] intervalo(String mensaje, BufferedReader br) throws IOException {
        double[] array = new double[2];

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
                    array[i] = Integer.parseInt(coordenadasString[i]);//Se convierte el array a enteros
                }

                if(array[1] < array[0]){
                    throw new StackOverflowError();
                }

                bandera = true;

            }catch (NumberFormatException e){//Si se ingreso algo diferente de un numero
                System.out.println("Error: Ha ingresado un numero invalido, porfavor vuelva a intentarlo");
            } catch (ArithmeticException a){
                System.out.println("Porfavor, ingrese solo dos numeros, vuelva a intentarlo");
            } catch (StackOverflowError e){
                System.out.println("El limite superior no puede ser menor que el limite inferior, vuelva a intentarlo");
            }
        }while (bandera == false);



        System.out.println("Intervalo seleccionado");
        System.out.println("Limite inferior = "+array[0]);
        System.out.println("Limite superior = "+array[1]);

        return array;
    }

    /**
     * Metodo para ver si es un numero
     * @param cadena
     * @return
     */
    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

}
