package packCodigo;

import java.io.ObjectStreamException;
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
	private TableroBuilder tablero;
	private CasillaFactory casilla;
	private ArrayList<String> lMinas = new ArrayList<String>();
	private int nivel;
	private int contMinas;
	private Timer timer=new Timer();//Aqui va el tiempo
	private ArrayList<String> lCasillasVacias = new ArrayList<String>();
	private Stack<String> casillasPorVisitar = new Stack<String>();
	private ArrayList<String> lCasillasVisitadas = new ArrayList<String>();
	private boolean juego;
	private float tiempoTrans;
	private int contBanderas=0;
	
	
	/****************
	 * CONSTRUCTORA	*
	 ****************/
	private Buscaminas(){
//		if (miBuscaminas == null){
//			miBuscaminas = 
//		}
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
		contMinas=lMinas.size();
	}

	/**Iniciamos el juego**/
	public void inicioJuego(int pNivel){
		System.out.println("hola");
		setNivel(pNivel);
		setJuego(true);
		iniciarTablero(pNivel);
		if(tablero instanceof TableroBuilderN1){
			System.out.println(1);
		} else if (tablero instanceof TableroBuilderN2){
			System.out.println(2);
		} else if(tablero instanceof TableroBuilderN3){
			System.out.println(3);
		}
		lMinas = tablero.cualesSonMina();
		setContMinas();
		contBanderas = contMinas;
		lCasillasVacias=tablero.obtenerVacias();
		crono();
	}
	
	/**Iniciar el tablero**/
	
	public void iniciarTablero(int pNivel){
		if(pNivel == 1){
			tablero = TableroBuilderN1.getTableroBuilderN1();
			tablero.asignarTablero();
		} else if (pNivel == 2){
			tablero = TableroBuilderN2.getTableroBuilderN2();
			tablero.asignarTablero();
		} else if (pNivel == 3){
			tablero = TableroBuilderN3.getTableroBuilderN3();
			tablero.asignarTablero();
		}
	}

	
	/************************************************************
	 * Resetea el Buscaminas haciendo una nueva instancia de	*
	 * tablero, casilla, casillasVacias, lCasillasVisitadas 	*
	 * y lCasillasVacias volviendo a calcular el numero de 		*
	 * minas. El tiempo se resetea.								*												*
	 ************************************************************/
	public void reset(){
		iniciarTablero(nivel);
		lMinas = tablero.cualesSonMina();
		setContMinas();
		lCasillasVacias=tablero.obtenerVacias();
		contBanderas=contMinas;
		tiempoTrans = -1;
		timer.cancel();
		crono();
		lCasillasVacias = new ArrayList<String>();
		casillasPorVisitar = new Stack<String>();
		lCasillasVisitadas = new ArrayList<String>();
		tablero.anadirObservador(this);
	}
	
	/**SetJuego**/
	private void setJuego(boolean pJuego){
		this.juego = pJuego;
	}
	
	/********************
	 * @param pNivel	*
	 ********************/
	private void setNivel(int pNivel){
		nivel = pNivel;
	}
	
	/****************************************
	 * @return lMinas.iterator();			*
	 ****************************************/
	private Iterator<String> getIteradorMinas(){
		return lMinas.iterator();
	}
	
	/****************************************
	 * @return lCasillasVacias.iterator();	*
	 ****************************************/
	private Iterator<String> getIteradorVacias(){
		return lCasillasVacias.iterator();
	}
	
	/********************************************
	 * @return lCasillasVisitadas.iterator();	*
	 ********************************************/
	private Iterator<String> getIteradorVisitadas(){
		return lCasillasVisitadas.iterator();
	}
	
	/**
	 * 
	 */
	public void mostrarTablero(){
		//TODO mostrar el tablero cuando ya ha acabado la partida.
		Iterator<String> itr = getIteradorMinas();
		String mina = null;
		int col;
		int fila;
		Casilla casilla;
		System.out.println(lMinas.size());
		if (lMinas.size()>0){
			while(itr.hasNext()){
				mina=itr.next();
				col=this.separarCoordenadasCol(this.separarCoordenadasString(mina));
				fila=this.separarCoordenadasFil(this.separarCoordenadasString(mina));
				casilla=tablero.buscarCasilla(fila, col);
				if(!casilla.estaDesvelada()&&!casilla.tieneBandera()){
					casilla.descubrir();
				}
			}
		}
	}
	
	/**
	 * @param pFila
	 * @param pCol
	 * @return
	 */
	private Casilla buscarCasillaTablero(int pFila, int pCol){
		Casilla casilla = tablero.buscarCasilla(pFila,pCol);
		return casilla;
	}
	
	/************************************************************
	 * Devuelve la casilla vacia en caso de que se encuentre	*
	 * en la lista lCasillasVacias								*
	 * @param pFila												*
	 * @param pCol												*
	 * @return Casilla o null									*
	 ************************************************************/
	public String buscarCasillaVacia(int pFila, int pCol){
		Iterator<String> itr = getIteradorVacias();
		String casilla = null;
		boolean esta = false;
		
		while (itr.hasNext() && !esta){
			casilla = itr.next();
			if (estaCasilla(pFila, pCol, casilla)){
				esta = true;
			}
		}
		if(esta){
			return casilla;
		}
		else{
			return null;
		}
	}
	
	/************************************************************
	 * Si la fila y columna coinciden devuelve true si no false	*
	 * @param pFila												*
	 * @param pCol												*
	 * @param pCasilla											*
	 * @return esta												*
	 ************************************************************/
	public boolean estaCasilla(int pFila, int pCol, String pCasilla){
		String[] coord;
		int fil;
		int col;
		boolean esta = false;
		
		coord = this.separarCoordenadas(pCasilla);
		fil = this.separarCoordenadasFil(coord);
		col = this.separarCoordenadasCol(coord);
		
		if(fil == pFila && col == pCol){
			esta = true;
		}
		return esta;
	}
	
	/**
	 * @param pFila
	 * @param pCol
	 * @return
	 */
	public String buscarCasillaVisitada(int pFila, int pCol){
		Iterator<String> itr = getIteradorVisitadas();
		String casilla = null;
		boolean esta = false;
		
		while (itr.hasNext() && !esta){
			casilla = itr.next();
			if (estaCasilla(pFila, pCol, casilla)){
				esta = true;
			}
		}
		if(esta){
			return casilla;
		}
		else{
			return null;
		}
	}
	
	/**
	 * @param pFila
	 * @param pCol
	 */
	public void descubrirCasilla(int pFila, int pCol){
		//TODO
		Casilla casilla = this.buscarCasillaTablero(pFila, pCol);
		if(casilla instanceof CasillaMina&&casilla.estaDesvelada()==false){
			casilla.descubrir();
			if(juego){
				gameOver();
			}
		}else if(casilla instanceof CasillaNumero&&casilla.estaDesvelada()==false){
			casilla.descubrir();
		}
		else{
			System.out.println(casilla.estaDesvelada());
			if(casilla.estaDesvelada()==false){
				descubrirCasillaVacia(pFila,pCol);
			}
		}
	}
	
	private void descubrirCasillaVacia(int pFila, int pCol){
		String cadena=pFila+","+pCol;
		String actual;
		Iterator<String> itr = getIteradorVacias();
		ArrayList<String> aux = new ArrayList<String>();
		Casilla casilla;
		String[] coord;
		int f=0;
		int c=0;
		System.out.println("SOY VACIA Y HAY: "+lCasillasVacias.size());
		boolean finalizar=false;
		casilla = buscarCasillaTablero(pFila, pCol);
		
		while(itr.hasNext()&&!finalizar){
			actual=itr.next();
			System.out.println("SOY VACIA CADENA Y ACTUAL SON: "+cadena+" "+actual);
			if(actual.equals(cadena)&&!estaVisitada(cadena)){
				System.out.println("DENTRO IF");
				lCasillasVisitadas.add(actual);
				casilla.descubrir();
				aux=((CasillaVacia)casilla).devolverVecinos();
				System.out.println("HE DESCUBIERTO CASILLA: "+pFila+" "+pCol);
				anadirVecinos(aux);
				while(!casillasPorVisitar.isEmpty()){
					actual=cogeryEliminarPorVisitar();
					coord=separarCoordenadas(actual);
					f=separarCoordenadasFil(coord);
					c=separarCoordenadasCol(coord);
					System.out.println("VOY A DESCUBRIR: "+f+" "+c);
					descubrirCasilla(f, c);
					finalizar=true;
				}
			}
		}
	}
	
	private boolean estaVisitada(String cadena) {
		// TODO Auto-generated method stub
		if(lCasillasVisitadas.contains(cadena)){
			return true;
		}
		else{
			return false;
		}
	}

	private void anadirVecinos(ArrayList<String> pAux){
		Iterator<String> itr = pAux.iterator();
		while(itr.hasNext()){
			anadirPorVisitar(itr.next());
		}
	}
	
	/**
	 * 
	 */
	public void gameOver(){
		this.mostrarTablero();
		setJuego(false);
	}
	
	/**
	 * @param pCasilla
	 */
	public void anadirVisitadas(String pCasilla){
		lCasillasVisitadas.add(pCasilla);
	}
	
	/**
	 * @param pCasilla
	 */
	public void anadirPorVisitar(String pCasilla){
		casillasPorVisitar.push(pCasilla);
	}
	
	/**
	 * @param pCasilla
	 */
	public void anadirVacia(String pCasilla){
		lCasillasVacias.add(pCasilla);
	}
	
	/**
	 * @param pCasilla
	 */
//	public void anadirMina(CasillaMina pCasilla){
//		lMinas.add(pCasilla);
//	}
	
	/**
	 * @param pCasilla
	 */
	public String cogeryEliminarPorVisitar(){
		return casillasPorVisitar.pop();
	}
	
	/****************************************************
	 * Coge las coordenadas de la casilla y lo separa	*
	 * metiendolo en un array de Strings -> String[]	*
	 * @param pCasilla									*
	 * @return pCasilla.obtenerCoordenadas().split(" ")	*
	 ****************************************************/
	private String[] separarCoordenadas(String pCasilla){
		return pCasilla.split(",");
	}
	
	/************************************************
	 * coge la coordenada de la col y lo pasa a int	*
	 * @param pCasilla								*
	 * @return Integer.parseInt(pCasilla[1])		*
	 ************************************************/
	private int separarCoordenadasCol(String[] pCasilla){
		return Integer.parseInt(pCasilla[1]);
	}
	
	/****************************************************
	 * coge la coordenada de la fila y lo pasa a int 	*
	 * @param pCasilla									*
	 * @return Integer.parseInt(pCasilla[0])			*
	 ****************************************************/
	private int separarCoordenadasFil(String[] pCasilla){
		return Integer.parseInt(pCasilla[0]);
	}
	
	/****************************************************
	 * separa un string									*
	 * @param pCasilla									*
	 * @return pCasilla.obtenerCoordenadas().split(" ")	*
	 ****************************************************/
	private String[] separarCoordenadasString(String pCoord){
		System.out.println(pCoord);
		return pCoord.split(",");
	}
	
	public void imprimirPorConsola(){
		tablero.imprimirTablero();
	}

	public int obtenerNumFilas() {
		
		return tablero.obtenerNumFilas();
	}
	
	public int obtenerNumColumnas() {
		
		return tablero.obtenerNumColumnas();
	}

	public void ponerBandera(int fila, int col) {
		if(0<contBanderas){
			tablero.ponerBandera(fila,col);
			System.out.println(contBanderas);
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
		// TODO Auto-generated method stub
		if(pObservable instanceof Tablero){
			if(pObjeto.toString().equals("true")){
				if(contBanderas>0){
					contBanderas--;
				}
			}else{
				if(contBanderas<contMinas){
					contBanderas++;
				}
			}
		}
		System.out.println("Contador Bandera: "+ contBanderas);
	}

	public void anadirObservador(VBuscaminas vBuscaminas) {
		// TODO Auto-generated method stub
		addObserver(vBuscaminas);
		tablero.anadirObservador(this);
	}
}