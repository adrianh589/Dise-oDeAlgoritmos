import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Clase para metodos con figuras
 * @author adrian
 *
 */
public class Figuras {
	
	private int[][] dimension;
	private double beneficio;
	private int cantidadCuadros;
	private double BeneficioDivDimension;
	private String mensaje = "";
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	Figuras() throws NumberFormatException, IOException{
		
		//Alto de la figura
		int filas = lectorEnteros("Porfavor introduzca el alto de la figura");
		
		//Ancho de la figura
		int columnas = lectorEnteros("Porfavor introduzca el ancho de la figura");
		
		//Beneficio de la figura
		this.beneficio = lectorEnteros("Porfavor introduzca el beneficio de esta figura");
		
		//Creacion de la dimension de la figura
		this.dimension = new int [filas][columnas];
		
		//Espacio de la figura
		this.cantidadCuadros = this.dimension.length*this.dimension[0].length;
		
		System.out.println("Su figura generada es: ");
		
		//Mostramos la figura generada por el usuario
		mostrarFigura(dimension);
		
		System.out.println("Beneficio ingresado: " + this.beneficio);
		
		//asignamos el Beneficio/Peso a la figura
		this.BeneficioDivDimension = (double) this.beneficio/(dimension.length * dimension[0].length);
		System.out.println("Beneficio/Dimension: "+BeneficioDivDimension);
	}
	
	/**
	 * Metodo que evita que el usuario ingrese letras o numeros invalidos
	 * @param mensaje Mensaje que se imprimira
	 * @return La variable entera
	 * @throws IOException Excepcion para el Buffered
	 */
	static int lectorEnteros(String mensaje) throws IOException {
		int variable = 0;
		do {
			try {
				System.out.println( mensaje );
				variable = Integer.parseInt(br.readLine());	
			}catch(NumberFormatException e) {System.out.println("Numero invalido");}
		}while(variable == 0 || variable < 1);
		return variable;
	}
	
	/**
	 * Metodo que permite mostrarle la figura al usuario que él genero
	 * @param figura Recibe la figura
	 */
	static void mostrarFigura(int [][] figura) {
		for (int i = 0; i < figura[0].length; i++) {
			System.out.print(" _");
		}
		
		System.out.println();
		
		for (int i = 0; i < figura.length; i++) {
			for (int j = 0; j < figura[i].length; j++) {
				if(j == 0) {
					System.out.print("|_|");
				}else {
					System.out.print("_|");
				}
				
			}
			System.out.println();
		}
		
		System.out.println();
	}
	
	/**
	 * Metodo que ordena las figuras por tamanio en orden descendente
	 * @param figuras
	 */
	public static void ordenarPorBeneficioSobreDimension(ArrayList<Figuras> figuras) {
		Collections.sort(figuras, new Comparator<Figuras>() {
	        @Override 
	        public int compare (Figuras p1, Figuras p2) {
	            return Double.compare(p1.getBeneficioDivDimension(), p2.getBeneficioDivDimension());
	        }
		});
		Collections.reverse(figuras);   // cambia en orden descendente
	}

	//Getters y Setters
	
	public double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(double beneficio) {
		this.beneficio = beneficio;
	}

	public double getBeneficioDivDimension() {
		return BeneficioDivDimension;
	}

	public void setBeneficioDivDimension(double beneficioDivDimension) {
		BeneficioDivDimension = beneficioDivDimension;
	}

	public int[][] getDimension() {
		return dimension;
	}

	public void setDimension(int[][] dimension) {
		this.dimension = dimension;
	}

	public int getCantidadCuadros() {
		return cantidadCuadros;
	}

	public void setCantidadCuadros(int cantidadCuadros) {
		this.cantidadCuadros = cantidadCuadros;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
