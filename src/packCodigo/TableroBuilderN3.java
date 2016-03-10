package packCodigo;

public class TableroBuilderN3 extends TableroBuilder{
	
	private static int nivel = 3;
	private static int fila = 11;
	private static int columna = 24;
	private int minas;
	
	public TableroBuilderN3(){
		this.minas = nivel*(columna+1);
	}
	
	public int getMinas(){
		return this.minas;
	}

}
