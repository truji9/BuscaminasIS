package packCodigo;

import java.util.ArrayList;

public class TableroBuilderN1 extends TableroBuilder{

	private Tablero elTablero ;
	private static TableroBuilderN1 miTablero = new TableroBuilderN1();
	
	public static TableroBuilderN1 getTableroBuilderN1(){
		return miTablero;
	}
	
	public ArrayList<String> cualesSonMina(){
		ArrayList<String> minas = elTablero.minas();
		return minas;
	}
	
	public void asignarTablero(){
		elTablero = new Tablero(1,7,10);
	}
	
	public ArrayList<String> obtenerVacias(){
		return super.obtenerVacias();
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
