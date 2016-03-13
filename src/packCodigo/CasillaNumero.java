package packCodigo;

public class CasillaNumero extends Casilla{

	private int numero=0;
	
	public CasillaNumero(){
		super();
	}
	
	
	public void descubrir(){
		super.descubrir();
		System.out.println(numero);
	}
	
	public void inicializar(String coor){
		super.inicializar(coor);
	}
	
	public void cambioBandera(){
		super.cambioBandera();
	}
	
	public void sumarNumero(){
		this.numero = this.numero+1;	
	}
	
	public int obtenerNumero(){
		return this.numero;
	}
	
	public void imprimirInfo(){
		System.out.println(numero);
	}
}