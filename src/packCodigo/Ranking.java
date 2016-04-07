package packCodigo;

import java.util.ArrayList;

public class Ranking {
	private static Ranking mRanking = new Ranking();
	private ArrayList<String> lPuntuaciones;
	private ArrayList<String> lNombres;
	
	private Ranking(){
		lPuntuaciones = new ArrayList<String>();
		lNombres = new ArrayList<String>();
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
//		QuickSort q = new QuickSort(this.lista);
//		this.lista = q.getOrdenada();
	}
	
	public boolean entraEnRanking(){
		boolean entra = false;
		return entra;
	}

}
