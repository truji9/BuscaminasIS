package packCodigo;

public abstract class Casilla {

	private boolean bandera=false;
	private String coordenada;
	private boolean desvelada=false;
	private boolean visitado=false;
	
	public Casilla(){
		
		
	}

	public String obtenerCoordenadas() {
		return this.coordenada;

	}
	
	public void descubrir(){
		if (desvelada == false){
			this.desvelada = true;
		}
	}
	
	
	public void inicializar(String coor){
		this.coordenada = coor;
	}
	
	public void cambioBandera(){
		if (bandera == false){
			bandera = true;
			System.out.println("BANDERA PUESTA");
		} else if(bandera == true){
			bandera = false;
			System.out.println("BANDERA QUITADA");
		}
	}
	
	public boolean estaDesvelada(){
		return this.desvelada;
	}
	
	public boolean tieneBandera(){
		return this.bandera;
	}

	public void imprimirInfo() {
		
	}
}

