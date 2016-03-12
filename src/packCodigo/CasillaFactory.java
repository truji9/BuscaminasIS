package packCodigo;

import java.util.Random;

public class CasillaFactory {

	
	private static CasillaFactory miCasillaFactory = new CasillaFactory();
	private int mina = 0;
	private CasillaFactory(){
		
	}
	
	public static CasillaFactory getMiFactoria(){
		return miCasillaFactory;
	}
	
	public Casilla obtenerCasilla(){	
		Random rn = new Random();
		int num = rn.nextInt(3-1) + 1;
		Casilla cas = null;
//		while(num==1 && mina==Buscaminas.getBuscaminas().obtenerNumMinas()){
//			num = rn.nextInt(3-1) + 1;
//		}
		
		if(num==1){
			cas = new CasillaMina();
			mina++;
		} else if(num==2){
			cas = new CasillaNumero();
		} else if(num==3){
			cas = new CasillaVacia();
		}
		return cas;
	}
	
	
}

