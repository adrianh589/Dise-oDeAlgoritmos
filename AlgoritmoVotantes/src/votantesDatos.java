/**
 * Clase que guarda com objeto, a los votantes con su soborno
 * @author Adrian Hoyos
 *
 */
public class votantesDatos {
	
	private double nombre;//Numero del votante
	private double soborno;//Soborno generado dependiendo del candidato escogido por el usuario
	
	votantesDatos() {}
	
	public votantesDatos(double nombre, double soborno) {
		this.nombre =  nombre;
		this.soborno = soborno;
	}

	
	//Getters y Setters
	
	
	public double getNombre() {
		return nombre;
	}

	public void setNombre(double nombre) {
		this.nombre = nombre;
	}

	public double getSoborno() {
		return soborno;
	}

	public void setSoborno(double soborno) {
		this.soborno = soborno;
	}

	
	
	

}
