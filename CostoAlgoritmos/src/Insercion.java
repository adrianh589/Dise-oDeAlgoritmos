
public class Insercion {//Mas efectivo
	
	public static void main(String[] args) {
		double start = System.currentTimeMillis();;
	    
		int U[] = {1,2,3,4,5,6};
		int V[] = {6,5,4,3,2,1};
		int W[] = {1,1,1,1,1,1};
		
		OrdenarPorInsercion(U);
		OrdenarPorInsercion(V);
		OrdenarPorInsercion(W);
		
		double end = System.currentTimeMillis();;
		
		 System.out.println((end - start) + " ms");
	}
	
	public static void OrdenarPorInsercion(int array[]){
		int clave, temp; //Declaracion de variables
		
		for (int i = 1; i < array.length; i++){//Iteramos por todo el arreglo
			clave = array[i]; //clave es el valor actual de i
			temp = i - 1; //temporal es una posicion menos a la actual
			
			while (temp > -1 && array[temp] >clave) { //Mientras temp no equivalga a -1 y sea mayor que clave
				array[temp+1] = array[temp]; // El arreglo en su siguiente espacio sera ahora la posicion actual
				temp = temp - 1; //La variable temp se reduce una unidad
			}
			
			array[temp+1] = clave; //El arreglo en su siguiente posicion a temp, sera el valor de la clave
		}
		
		//Imprimir el arreglo
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+" ");
		}System.out.println();
		
	}

}
