
public class Seleccion {

	public static void main(String[] args) {
		
		double start = System.currentTimeMillis();;
		
		int U[] = {1,2,3,4,5,6};
		int V[] = {6,5,4,3,2,1};
		int W[] = {1,1,1,1,1,1};
		
		OrdenarPorSeleccion(U);
		OrdenarPorSeleccion(V);
		OrdenarPorSeleccion(W);
		
		double end = System.currentTimeMillis();;
		System.out.println((end - start) + " ms");
	}
	
	public static void OrdenarPorSeleccion(int array[]) {
        int i, j, menor, pos, tmp;
        for (i = 0; i < array.length - 1; i++) { // tomamos como menor el primero
              menor = array[i]; // de los elementos que quedan por ordenar
              pos = i; // y guardamos su posición
              for (j = i + 1; j < array.length; j++){ // buscamos en el resto
                    if (array[j] < menor) { // del array algún elemento
                        menor = array[j]; // menor que el actual
                        pos = j;
                    }
              }
              if (pos != i){ // si hay alguno menor se intercambia
                  tmp = array[i];
                  array[i] = array[pos];
                  array[pos] = tmp;
              }
        }
        
        	//Imprimir el arreglo
      		for (int h = 0; h < array.length; h++) {
      			System.out.print(array[h]+" ");
      		}System.out.println();
	}

}
