package packCodigo;

import java.util.ArrayList;

public class TableroBuilderN3 extends TableroBuilder{

	private Tablero eltablero ;
	private static TableroBuilderN3 miTablero = new TableroBuilderN3();
	
	public static TableroBuilderN3 getTableroBuilderN3(){
		return miTablero;
	}
		
	public void asignarTablero(){
		eltablero = new Tablero(3,12,25);
	}
}
