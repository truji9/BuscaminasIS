package packCodigo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.Timer;

public class Buscaminas {

	private static Buscaminas miBuscaminas;
	private TableroBuilder tablero;
	private CasillaFactory casilla;
	private ArrayList<String> lMinas = new ArrayList<String>();
	private int nivel;
	private int contMinas = lMinas.size();
	private Timer time;//Aqui va el tiempo
	private ArrayList<Casilla> lCasillasVacias = new ArrayList<Casilla>();
	private Stack<Casilla> casillasPorVisitar = new Stack<Casilla>();
	private ArrayList<Casilla> lCasillasVisitadas = new ArrayList<Casilla>();
	private boolean juego;
	
	
	/****************
	 * CONSTRUCTORA	*
	 ****************/
	private Buscaminas(){
		if (miBuscaminas == null){
			miBuscaminas = new Buscaminas();
		}
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
//	public static Buscaminas setContMinas(){
//		contMinas=lMinas.size();
//	}

	/**Iniciamos el juego**/
	public void inicioJuego(int pNivel){
		setNivel(pNivel);
		setJuego(true);
		iniciarTablero(pNivel);
		lMinas = tablero.cualesSonMina();
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
		//tablero = new TableroBuilder();
		//casilla = new CasillaFactory();
		//contMinas = tablero.calcularMinas(nivel);
		lCasillasVacias = new ArrayList<Casilla>();
		casillasPorVisitar = new Stack<Casilla>();
		lCasillasVisitadas = new ArrayList<Casilla>();
		//TODO FALTA RESETEAR EL TIEMPO
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
	private Iterator<Casilla> getIteradorVacias(){
		return lCasillasVacias.iterator();
	}
	
	/********************************************
	 * @return lCasillasVisitadas.iterator();	*
	 ********************************************/
	private Iterator<Casilla> getIteradorVisitadas(){
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
	public Casilla buscarCasillaVacia(int pFila, int pCol){
		Iterator<Casilla> itr = getIteradorVacias();
		Casilla casilla = null;
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
	public boolean estaCasilla(int pFila, int pCol, Casilla pCasilla){
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
	public Casilla buscarCasillaVisitada(int pFila, int pCol){
		Iterator<Casilla> itr = getIteradorVisitadas();
		Casilla casilla = null;
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
		casilla.descubrir();
	}
	
	/**
	 * 
	 */
	public void gameOver(){
		this.mostrarTablero();
	}
	
	/**
	 * @param pCasilla
	 */
	public void anadirVisitadas(Casilla pCasilla){
		lCasillasVisitadas.add(pCasilla);
	}
	
	/**
	 * @param pCasilla
	 */
	public void anadirPorVisitar(Casilla pCasilla){
		casillasPorVisitar.push(pCasilla);
	}
	
	/**
	 * @param pCasilla
	 */
	public void anadirVacia(Casilla pCasilla){
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
	public void CogeryEliminarPorVisitar(Casilla pCasilla){
		casillasPorVisitar.pop();
	}
	
	/****************************************************
	 * Coge las coordenadas de la casilla y lo separa	*
	 * metiendolo en un array de Strings -> String[]	*
	 * @param pCasilla									*
	 * @return pCasilla.obtenerCoordenadas().split(" ")	*
	 ****************************************************/
	private String[] separarCoordenadas(Casilla pCasilla){
		return pCasilla.obtenerCoordenadas().split(",");
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
		return pCoord.split(",");
	}
}