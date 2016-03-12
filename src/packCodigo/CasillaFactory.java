package packCodigo;

import java.util.Random;

public class CasillaFactory {

	
	private static CasillaFactory miCasillaFactory = new CasillaFactory();
	
	private CasillaFactory(){
		
	}
	
	public static CasillaFactory getMiFactoria(){
		return miCasillaFactory;
	}
	
	public Casilla generarCasilla(String tipo){	
		Casilla cas = null;
//		while(num==1 && mina==Buscaminas.getBuscaminas().obtenerNumMinas()){
//			num = rn.nextInt(3-1) + 1;
//		}
		
		if(tipo == "Mina"){
			cas = new CasillaMina();
		} else if (tipo == "Numero"){
			cas = new CasillaNumero();
		} else if (tipo == "Vacia"){
			cas = new CasillaVacia();
		}
		return cas;
	}
	
	
}

