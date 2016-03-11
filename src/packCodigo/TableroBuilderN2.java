package packCodigo;

import java.util.ArrayList;

public class TableroBuilderN2 extends TableroBuilder{

	private Tablero eltablero ;
	private static TableroBuilderN2 miTablero = new TableroBuilderN2();
	
	public static TableroBuilderN2 getTableroBuilderN2(){
		return miTablero;
	}
	
	public void asignarTablero(){
		eltablero = new Tablero(2,10,15);
	}
	
	public ArrayList<String> obtenerMinas(){
		return super.obtenerMinas();
	}
}
