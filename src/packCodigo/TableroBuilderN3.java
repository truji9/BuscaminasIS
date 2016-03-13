package packCodigo;

import java.util.ArrayList;

public class TableroBuilderN3 extends TableroBuilder{

	private Tablero elTablero ;
	private static TableroBuilderN3 miTablero = new TableroBuilderN3();
	
	public static TableroBuilderN3 getTableroBuilderN3(){
		return miTablero;
	}
		
	public void asignarTablero(){
		elTablero = new Tablero(3,12,25);
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
