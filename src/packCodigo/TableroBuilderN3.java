package packCodigo;

import java.util.ArrayList;

public class TableroBuilderN3 extends TableroBuilder{

	private Tablero elTablero ;
	private static TableroBuilderN3 miTablero = new TableroBuilderN3();
	
	public static TableroBuilderN3 getTableroBuilderN3(){
		return miTablero;
	}
		
	public ArrayList<String> cualesSonMina(){
		ArrayList<String> minas = elTablero.minas();
		return minas;
	}
	
	public void asignarTablero(){
		elTablero = new Tablero(3,12,25);
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
