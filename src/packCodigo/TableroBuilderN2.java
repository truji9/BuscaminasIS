package packCodigo;

import java.util.ArrayList;

public class TableroBuilderN2 extends TableroBuilder{

	private Tablero elTablero ;
	private static TableroBuilderN2 miTablero = new TableroBuilderN2();
	
	public static TableroBuilderN2 getTableroBuilderN2(){
		return miTablero;
	}
	
	public ArrayList<String> cualesSonMina(){
		ArrayList<String> minas = elTablero.minas();
		return minas;
	}
	
	public Tablero asignarTablero(){
		elTablero = new Tablero(2,10,15);
		elTablero.generarMatriz();
		return elTablero;
	}
	
	public ArrayList<String> obtenerVacias(){
		return elTablero.vacias();
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
		elTablero.ponerBandera(fila,col);
	}
	
	public Casilla buscarCasilla(int pFila, int pColumna){
		
		return elTablero.buscarCasilla(pFila, pColumna);
		
	}
	
	public void anadirObservador(Buscaminas buscaminas) {
		// TODO Auto-generated method stub
		elTablero.addObserver(buscaminas);
	}
}
