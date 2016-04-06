
package packCodigo;

public class CasillaMina extends Casilla{
	
	public CasillaMina(){
		super();
	}
	
	public void descubrir(){
		super.descubrir();
		System.out.println("MINA");
		System.out.println(super.obtenerCoordenadas());
	}
	
	public void inicializar(String coor){
		super.inicializar(coor);
	}
	
	public void cambioBandera(){
		super.cambioBandera();
	}
	
	public void imprimirInfo(){
		System.out.println("Mina");
	}
	
}