package packCodigo;

public abstract class Casilla {

	private boolean bandera=false;
	private String coordenada;
	private boolean desvelada=false;
	private boolean visitado=false;
	
	public Casilla(){
		
	}

	public String obtenerCoordenadas() {
		// TODO Auto-generated method stub
		return null;

	}
	
	public void descubrir(){
		if (desvelada = false){
			this.desvelada = true;
		}
	}
	
	
	public void inicializar(String coor){
		this.coordenada = coor;
	}
	
	public void cambioBandera(){
		if (bandera == false){
			bandera = true;
		} else if(bandera == true){
			bandera = false;
		}
	}
	

}
