package packCodigo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import packVentanas.VBuscaminas;

public class Buscaminas extends Observable implements Observer{

	private static Buscaminas miBuscaminas = new Buscaminas();
	private TableroBuilder generador;
	private Tablero tablero;
	private int nivel;
	private int contMinas;
	private Timer timer=new Timer();//Aqui va el tiempo
	private boolean juego;
	private float tiempoTrans;
	private int contBanderas=0;
	private String nombreJugador;
	private int puntuacion;
	private boolean finalizado = false;
	private Jugador j;
	private int puntos =0;
	
	/****************
	 * CONSTRUCTORA	*
	 ****************/
	private Buscaminas(){
	}
	
	/************************
	 * Singleton.			*
	 * @return miBuscaminas	*
	 ************************/
	public static Buscaminas getBuscaminas(){
		return miBuscaminas;
	}
	

	/************************
	 * 						*
	 * @return 				*
	 ************************/
	private void setContMinas(){
		contMinas = tablero.minas().size();
	}

	/**Iniciamos el juego**/
	public void inicioJuego(int pNivel){
		setNivel(pNivel);
		setJuego(true);
		iniciarTablero(pNivel);
		setContMinas();
		contBanderas = contMinas;
		crono();
	}
	
	/**Iniciar el tablero**/
	
	public void iniciarTablero(int pNivel){
		if(pNivel == 1){
			tablero = TableroBuilderN1.getTableroBuilderN1().asignarTablero();
		} else if (pNivel == 2){
			tablero = TableroBuilderN2.getTableroBuilderN2().asignarTablero();
			
		} else if (pNivel == 3){
			tablero = TableroBuilderN3.getTableroBuilderN3().asignarTablero();
		}
	}

	
	/************************************************************
	 * Resetea el Buscaminas haciendo una nueva instancia de	*
	 * tablero, casilla, casillasVacias, lCasillasVisitadas 	*
	 * y lCasillasVacias volviendo a calcular el numero de 		*
	 * minas. El tiempo se resetea.								*												*
	 ************************************************************/
	public void reset(VBuscaminas vBuscaminas){
		iniciarTablero(nivel);
		tablero.addObserver(vBuscaminas);
		setContMinas();
		contBanderas=contMinas;
		tiempoTrans = -1;
		timer.cancel();
		crono();
		tablero.addObserver(this);
		setJuego(true);
	}
	
	/**SetJuego**/
	private void setJuego(boolean pJuego){
		this.juego = pJuego;
		setChanged();
		notifyObservers(juego);
	}
	
	/********************
	 * @param pNivel	*
	 ********************/
	private void setNivel(int pNivel){
		nivel = pNivel;
	}

	public void descubrirCasilla(int pFila, int pCol){
		tablero.descubrirCasilla(pFila, pCol);
	}
	
	/**
	 * 
	 */
	public void gameOver(){
		timer.cancel();
		tablero.mostrarTablero();
		setJuego(false);
	}

	
	public void imprimirPorConsola(){
		tablero.imprimirMatriz();
	}

	public int obtenerNumFilas() {
		
		return tablero.obtenerNumFilas();
	}
	
	public int obtenerNumColumnas() {
		
		return tablero.obtenerNumColumnas();
	}

	public boolean getJuego(){
		return juego;
	}
	
	public void ponerBandera(int fila, int col) {
		int aux = contBanderas;
		if(0<=contBanderas){
			tablero.ponerBandera(fila,col);
		}
		if(contBanderas < aux){
			setChanged();
			notifyObservers(fila+","+col+","+"PonerBandera");
		} else if (contBanderas > aux){
			setChanged();
			notifyObservers(fila+","+col+","+"QuitarBandera");
		}
		
	}
	
	private void crono(){

	  TimerTask  timerTask = new TimerTask() {
	   @Override
	   public void run() {
	    String texto;
	    tiempoTrans++;
	    int segundos =(int)tiempoTrans;
	    int minutos =0;
	    while(segundos>59){
	     minutos++;
	     segundos = segundos-60;
	    }
	    if(segundos<10){
	     texto=(""+minutos+":0" + segundos); 
	    }else{
	    texto=(""+minutos+":" + segundos);     
	    }		
	    setChanged();
	    notifyObservers(texto+","+contBanderas);
	   }
	  };
	  timer = new Timer();
	  timer.scheduleAtFixedRate(timerTask, 0, 1000);
	 }
	
	@Override

	public void update(Observable pObservable, Object pObjeto) {
		if(pObservable instanceof Tablero){
			String[]p = pObjeto.toString().split(",");
			if(p[1].equals("BANDERA") && p[0].equals("true")){
				if(contBanderas>0){
					contBanderas--;
				}
			}else if(p[1].equals("BANDERA") && p[0].equals("false")){
				if(contBanderas<contMinas){
					contBanderas++;
				}
			}
		}
	}


	public void anadirObservador(VBuscaminas vBuscaminas) {
		addObserver(vBuscaminas);
		tablero.addObserver(vBuscaminas);
		tablero.addObserver(this);
	}

	public void establecerNombreJugador(String text) {
		if(text==""){
			nombreJugador = "Desconocido";
		}else{
			nombreJugador = text;
		}
		boolean esta = Ranking.getRanking().estaEnRanking();
		if(!esta){
			j =  new Jugador(nombreJugador);
			j.establecerPuntuacion(0);
			Ranking.getRanking().anadirLista(j);
		}
	}

	public void establecerNivel(String selectedItem) {
		nivel = Integer.parseInt(selectedItem);
	}
	
	public void establecerPuntuacion(int pPunt){
		puntuacion = pPunt;
	}
	
	public String obtenerNombreJugador(){
		return nombreJugador;
	}
	
	public int obtenerPuntuacion(){
		return puntuacion;
	}
	public void comprobarJuego(){
		System.out.println("Voy a intentar comprobar la situacion");
		if(contBanderas==0 || tablero.getContadorCasillasDescubrir()== contMinas){
			System.out.println("Hola");
			boolean fin = tablero.comprobarJuego();
			setFinalizado(fin);
		}
	}

	private void setFinalizado(boolean fin) {
		// TODO Auto-generated method stub
		this.finalizado = fin;
		if(finalizado){
			System.out.println("He cambiado el finalizado");
			timer.cancel();
			setChanged();
			notifyObservers("FINALIZADO");
			System.out.println("He notificado");
			//Ranking.getRanking().cargarLista();
		}
	}

	public void calcularPuntos(int contP) {
		// TODO Auto-generated method stub
		
		if(nivel==1){
			puntos= (int) (50-(tiempoTrans*2 + contP));
		}else if(nivel==2){
			puntos= (int) (200-(tiempoTrans*2 + contP));
		}else{
			puntos= (int) (400-(tiempoTrans*2 + contP));
		}
		establecerPuntuacion(puntos);
		asignarPuntos();
	}
	
	private void asignarPuntos(){
		Ranking.getRanking().buscarJugador(nombreJugador);
	}
	
	
	public int obtenerPuntos(){
		return puntos;
	}
}