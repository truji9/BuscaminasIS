package packCodigo;

import java.util.ArrayList;

public class TableroBuilderN1 extends TableroBuilder{

	private Tablero eltablero ;
	private static TableroBuilderN1 miTablero = new TableroBuilderN1();
	
	public static TableroBuilderN1 getTableroBuilderN1(){
		return miTablero;
	}
	
	public void asignarTablero(){
		eltablero = new Tablero(1,7,10);
	}
	
	public ArrayList<String> obtenerMinas(){
		return super.obtenerMinas();
	}
	
}
