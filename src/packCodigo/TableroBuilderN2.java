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
	
	public void asignarTablero(){
		elTablero = new Tablero(2,10,15);
	}
	
	public ArrayList<String> obtenerMinas(){
		return super.obtenerMinas();
	}
	
	public ArrayList<String> obtenerVacias(){
		return super.obtenerVacias();
	}
	
	public void imprimirTablero(){
		elTablero.imprimirMatriz();
	}
}
