package packCodigo;

import java.util.ArrayList;

public abstract class TableroBuilder {
	
	private Tablero elTablero;

	//-------------------------------------------//
	

	public TableroBuilder(){
		
	}

	
	public ArrayList<String> cualesSonMina(){
		ArrayList<String> minas = elTablero.minas();
		return minas;
	}
	
	public void asignarTablero(){
		
	}
	
	public ArrayList<String> obtenerVacias(){
		
		return elTablero.vacias();
		
	}
	
	public Casilla buscarCasilla(int pFila, int pColumna){
		
		return elTablero.buscarCasilla(pFila, pColumna);
		
	}

	public void imprimirTablero(){
		elTablero.imprimirMatriz();

	}


	public int obtenerNumFilas() {
		return elTablero.obtenerNumFilas();
	}
	
	public int obtenerNumColumnas() {
		return elTablero.obtenerNumColumnas();
	}


	public void ponerBandera(int fila, int col) {
		// TODO Auto-generated method stub
		
	}


	public void anadirObservador(Buscaminas buscaminas) {
		// TODO Auto-generated method stub
		
	}
}
