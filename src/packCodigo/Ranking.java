package packCodigo;

import java.util.ArrayList;
import java.util.Iterator;

public class Ranking {
	private static Ranking mRanking = new Ranking();
	private ArrayList<Jugador> lRanking;
	
	private Ranking(){
		lRanking = new ArrayList<Jugador>();
	}
	
	public static Ranking getRanking(){
		return mRanking;
	}
	
	public void cargarLista(){
		try {
			XMLParserRanking.getPDF2XMLParser().parseXmlFile("ficheros/Ranking.xml");
		} catch (XmlParsingException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarLista(){
		XMLWriteRanking.getXMLWriteRanking().cargar();
	}
	
	public void ordenarLista(){
		QuickSort q = new QuickSort(this.lRanking);
		this.lRanking = q.getOrdenada();
	}
	
	private boolean estaEnRanking(){
		boolean esta = false;
		if (lRanking.contains(Buscaminas.getBuscaminas().obtenerNombreJugador())){
			esta = true;
		}
		return esta;
	}
	
	private Iterator<Jugador> getIteradorJugador(){
		return lRanking.iterator();
	}

	private void comprobarPuntuacion(){
		Iterator<Jugador> itr = getIteradorJugador();
		int punt=0;
		Jugador jug;
		
		while(itr.hasNext()){
			jug=itr.next();
			if(jug.mismoJugador()){
				jug.asignarPuntuacionR();
			}
		}
		
	}
}
