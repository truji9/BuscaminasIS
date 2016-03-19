package packCodigo;

import java.util.ArrayList;

public class TableroBuilderN3 extends TableroBuilder{

	private Tablero elTablero ;
	private static TableroBuilderN3 miTablero = new TableroBuilderN3();
	
	public static TableroBuilderN3 getTableroBuilderN3(){
		return miTablero;
	}
		
	public Tablero asignarTablero(){
		elTablero = new Tablero(3,12,25);
		elTablero.generarMatriz();
		return elTablero;
	}
	
	
	public void imprimirTablero(){
		elTablero.imprimirMatriz();
	}
	
	
	public void ponerBandera(int fila, int col) {
		// TODO Auto-generated method stub
		elTablero.ponerBandera(fila,col);
	}
	
	
	public void anadirObservador(Buscaminas buscaminas) {
		// TODO Auto-generated method stub
		elTablero.addObserver(buscaminas);
	}
}
