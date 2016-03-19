package packCodigo;

import java.util.ArrayList;

public abstract class TableroBuilder {
	
	private Tablero elTablero;

	//-------------------------------------------//
	

	public TableroBuilder(){
		
	}

	
	public Tablero asignarTablero(){
		return elTablero;
	}
	
	

	public void imprimirTablero(){
		elTablero.imprimirMatriz();

	}



	public void ponerBandera(int fila, int col) {
		// TODO Auto-generated method stub
		
	}


	public void anadirObservador(Buscaminas buscaminas) {
		// TODO Auto-generated method stub
		
	}
}
