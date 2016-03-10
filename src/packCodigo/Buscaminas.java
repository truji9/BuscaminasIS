package packCodigo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Buscaminas {

	private static Buscaminas miBuscaminas;
	private TableroBuilder tablero;
	private CasillaFactory casilla;
	private ArrayList<CasillaMina> lMinas = new ArrayList<CasillaMina>();
	private int nivel;
	private int contMinas = tablero.calcularMinas(nivel);
	//Aqui va el tiempo
	private ArrayList<Casilla> lCasillasVacias = new ArrayList<Casilla>();
	private Stack<Casilla> casillasPorVisitar = new Stack<Casilla>();
	private ArrayList<Casilla> lCasillasVisitadas = new ArrayList<Casilla>();
	
	
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
	
	/************************************************************
	 * Resetea el Buscaminas haciendo una nueva instancia de	*
	 * tablero, casilla, casillasVacias, lCasillasVisitadas 	*
	 * y lCasillasVacias volviendo a calcular el numero de 		*
	 * minas. El tiempo se resetea.								*												*
	 ************************************************************/
	public void reset(){
		tablero = new TableroBuilder();
		casilla = new CasillaFactory();
		contMinas = tablero.calcularMinas(nivel);
		lCasillasVacias = new ArrayList<Casilla>();
		casillasPorVisitar = new Stack<Casilla>();
		lCasillasVisitadas = new ArrayList<Casilla>();
		//TODO FALTA RESETEAR EL TIEMPO
	}
	
	/********************
	 * @param pNivel	*
	 ********************/
	public void setNivel(int pNivel){
		nivel = pNivel;
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
		//TODO 
		
	}
	
	/**
	 * @param pFila
	 * @param pCol
	 * @return
	 */
	private Casilla buscarCasillaTablero(int pFila, int pCol){
		Casilla casilla = Tablero.buscarCasilla(pFila,pCol);
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
	public void anadirMina(CasillaMina pCasilla){
		lMinas.add(pCasilla);
	}
	
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
	
	public int obtenerNumMinas(){
		return this.contMinas;
	}
}