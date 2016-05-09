package packCodigo;

import java.util.ArrayList;
import java.util.Iterator;

public class CasillaVacia extends Casilla {
	
		
	public CasillaVacia(){
		super();
	}
	
	
	public void descubrir(){
		super.descubrir();
		System.out.println("VACIA");
	}
	
	public void inicializar(String coor){
		super.inicializar(coor);
	}
	
	public void cambioBandera(){
		super.cambioBandera();
	}
	
	public void imprimirInfo(){
		System.out.println("Vacia");
	}
	
	public void anadirVecino(String vecino){
		super.anadirVecino(vecino);

	}

	
	private Iterator<String> getIterador(){
		return super.obtenerVecinos().iterator();
	}
	
	public ArrayList<String> devolverVecinos(){
		ArrayList<String> aux = new ArrayList<String>();
		Iterator<String> itr = getIterador();
		while(itr.hasNext()){
			String aux2= itr.next();
			System.out.println("Soy el vecino: "+aux2);
			aux.add(aux2);
		}
		return aux;
	}
}