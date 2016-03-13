package packCodigo;

import java.util.ArrayList;

public class CasillaVacia extends Casilla {
	
	private ArrayList<String> lVecinas = new ArrayList<String>();
	
	
	public CasillaVacia(){
		super();
	}
	
	
	public void descubrir(){
		super.descubrir();
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
		if(!lVecinas.contains(vecino)){
			lVecinas.add(vecino);
		}
	}
}