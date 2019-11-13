package Biseccion;

import javax.script.ScriptException;
import java.io.IOException;

/**
 * Clase que permite hacer la biseccion con divida y venceras
 * @author Adrian Hoyos
 */
public class DivideYVenceras {

    /**
     * Funcion con Divide y Venceras que calcula la raiz de un funcion
     * @param limiteInferior limite inferior del intervalo
     * @param limiteSuperior limite superior del intervalo
     * @return retorna la raiz calculada
     * @throws IOException
     * @throws ScriptException
     */
    static double DyVBiseccion(double limiteInferior, double limiteSuperior, String ecuacion) throws IOException, ScriptException {
        double Xa = (limiteInferior + limiteSuperior)/2;//Mitad
        double FXi = ecuacionOperaciones.evaluarFormula(ecuacion, limiteInferior); //Valor inferior
        double FXa = ecuacionOperaciones.evaluarFormula(ecuacion, Xa);//Valor medio

        if(FXa == 0){//Si el valor medio da 0 es porque se encontro la raiz
            return limiteInferior;
        }

        if(FXi*FXa < 0){//Si el signo de la multiplicacion de ambas funciones por ambos limites es negativo, se tomata como intervalo el limite inferior hasta la mitad
            return DyVBiseccion(limiteInferior, Xa, ecuacion);
        }else{
            return DyVBiseccion(Xa, limiteSuperior, ecuacion);//En caso contrario tomamos la otra mitad
        }
    }

}
