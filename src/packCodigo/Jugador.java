package packCodigo;

public class Jugador {
	private String nombre;
	private int puntuacion;
	
	public Jugador(String pNombre){
		nombre = pNombre;
	}
	
	public void establecerPuntuacion(int pPuntuacion){
		puntuacion=pPuntuacion;
	}

	public int compareTo(Jugador pivote) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String obtenerNombre(){
		return nombre;
	}
	
	public int obtenerPunt(){
		return puntuacion;
	}
	
	private void ponerPunt(){
		this.puntuacion=Buscaminas.getBuscaminas().obtenerPuntuacion();
	}
	
	public boolean mismoJugador(){
		boolean mismo = false;
		if(this.obtenerNombre().equals(Buscaminas.getBuscaminas().obtenerNombreJugador())){
			mismo = true;
		}
		return mismo;
	}
	
	public void asignarPuntuacionR(){
		mayorPunt();
	}
	
	private void mayorPunt(){
		if(Buscaminas.getBuscaminas().obtenerPuntuacion()>=this.obtenerPunt()){
			this.ponerPunt();
		}
	}
}
