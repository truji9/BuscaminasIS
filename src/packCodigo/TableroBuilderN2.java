package packCodigo;

public class TableroBuilderN2 extends TableroBuilder{
	
	private static int nivel = 2;
	private static int fila = 9;
	private static int columna = 14;
	private int minas;
	
	public TableroBuilderN2(){
		this.minas = nivel*(columna+1);
	}
	
	public int getMinas(){
		return this.minas;
	}

}
