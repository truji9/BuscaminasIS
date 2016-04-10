package packCodigo;

import java.util.ArrayList;

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
	
	public boolean entraEnRanking(){
		boolean entra = false;
		return entra;
	}

}
