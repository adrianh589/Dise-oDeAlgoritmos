
public class votantesDatos {
	
	private String nombre;
	private int costo;
	private double soborno;
	
	votantesDatos() {
		// TODO Auto-generated constructor stub
	}
	
	public votantesDatos(String nombre, int costo, double soborno) {
		this.nombre = nombre;
		this.costo = costo;
		this.soborno = soborno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public double getSoborno() {
		return soborno;
	}

	public void setSoborno(double soborno) {
		this.soborno = soborno;
	}
	
	

}
