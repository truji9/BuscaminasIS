package packCodigo;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Casilla {

	private boolean bandera=false;
	private String coordenada;
	private boolean desvelada=false;
	private ArrayList<String> lVecinas = new ArrayList<String>();
	
	public Casilla(){
		
		
	}

	public String obtenerCoordenadas() {
		return this.coordenada;

	}
	
	public void descubrir(){
		if (desvelada == false){
			this.desvelada = true;
		}
	}
	
	public void anadirVecino(String vecino){
		if(!lVecinas.contains(vecino)){
			lVecinas.add(vecino);
		}

	}
	
	public void inicializar(String coor){
		this.coordenada = coor;
	}
	
	public void cambioBandera(){
		if(desvelada ==false){
			if (bandera == false){
				bandera = true;
				System.out.println("BANDERA PUESTA");
			} else if(bandera == true){
				bandera = false;
				System.out.println("BANDERA QUITADA");
			}
		}
		
	}
	
	public ArrayList<String> obtenerVecinos(){
		return this.lVecinas;
	}
	
	public boolean estaDesvelada(){
		return this.desvelada;
	}
	
	public boolean tieneBandera(){
		return this.bandera;
	}

	public void imprimirInfo() {
		
	}
}

