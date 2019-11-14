import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.ScriptException;

import Biseccion.*;
import Varilla.*;
import AsignacionTareas.*;

public class MenuPrincipal {

	public static void main(String[] args) throws NumberFormatException, IOException, ScriptException {
		String rta = "";

		do {
			int opcion = opcion();
			switch (opcion) {
			case 1:
				Biseccion.Main.main(args);
				break;
			case 2:
				Varilla.Main.main(args);
				break;
			case 3:
				AsignacionTareas.Main.main(args);
				break;

			default:
				System.out.println("Opcion incorrecta");
				break;
			}

		} while (rta.equals(""));

	}

	static int opcion() throws NumberFormatException, IOException {
		System.out.println("***Menu Principal***");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(
				"Elija su opcion para ejecutar el algoritmo... \n1. Biseccion\n2. Varilla\n3. Asignacion de tareas");
		int escogida = Integer.parseInt(br.readLine());
		return escogida;
	}

}
