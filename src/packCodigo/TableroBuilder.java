package packCodigo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TableroBuilder {
	
	private ArrayList<CasillaMina> lCasillaMina;
	private Tablero elTtablero;
	
	public Tablero generarTablero(int pNivel){
		Tablero tablero = new Tablero();
		int minasAColocar = this.calcularMinas(pNivel);
		String filasColumnas = this.calcularFilasColumnas(pNivel);
		String [] sFilasColumnas = this.separarCoordenadas(filasColumnas);
		int x = this.separarCoordenadasFil(sFilasColumnas);
		int y = this.separarCoordenadasCol(sFilasColumnas);
		int i,j = 0;
		while(minasAColocar != 0){
			i = this.randInt(x);
			j = this.randInt(j);
			
			
		}
		
		
		
		return tablero;
	}
	
	/**
	 * Introducido el nivel devuelve el numero de 
	 * minas si el nivel es distinto a 1,2 o 3 devuelve -1
	 * @param pNivel
	 * @return minas
	 */
	public int calcularMinas(int pNivel) {
		int minas = -1;
		if (pNivel == 1){
			minas = 10 * pNivel;
		}else if(pNivel == 2){
			minas = 15 * pNivel;
		}else if(pNivel == 3){
			minas = 25 * pNivel;
		}
		return minas;
	}
	
	/**
	 * Calcula el numero de filas y columnas
	 * Si el nivel es incorrecto devuelve ""
	 * @param pNivle
	 * @return nFilasColumnas
	 */
	private String calcularFilasColumnas(int pNivel){
		String fC = "";
		if (pNivel == 1){
			fC = "7,10";
		}else if(pNivel == 2){
			fC = "10,15";
		}else if(pNivel == 3){
			fC = "12,25";
		}
		return fC;
	}
	
	private Iterator<CasillaMina> getIteradorCasillaMinas(){
		return lCasillaMina.iterator();
	}
	
	/**
	 * Suma +1 al numero de la casilla
	 * que se encuentren arriba y abajo 
	 * de la mina 
	 */
	private void visitarH(){
		
	}
	
	//////////////////////////////LISTA MINAS////////////////////////////////////////
	
	//////////////////////////////SUBPROGRAMAS COORDENADAS///////////////////////////
	
	/****************************************************
	 * Coge las coordenadas de la casilla y lo separa	*
	 * metiendolo en un array de Strings -> String[]	*
	 * @param pCasilla									*
	 * @return pCasilla.obtenerCoordenadas().split(",")	*
	 ****************************************************/
	private String[] separarCoordenadas(String pFilaColumna){
		return pFilaColumna.split(",");
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
	
	public static int randInt(int max) {
		int min = 0;
	    Random rand = null;
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    if (randomNum<0 || randomNum>=8){
	    	randomNum = rand.nextInt((max - min) + 1) + min;
	    }
	    return randomNum;
	}
}
