package packCodigo;

import java.util.ArrayList;

public class TableroBuilderN1 extends TableroBuilder{

	private Tablero elTablero ;
	private static TableroBuilderN1 miTablero = new TableroBuilderN1();
	
	public static TableroBuilderN1 getTableroBuilderN1(){
		return miTablero;
	}
	
	
	public Tablero asignarTablero(){
		elTablero = new Tablero(1,7,10);
		elTablero.generarMatriz();
		return elTablero;
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
