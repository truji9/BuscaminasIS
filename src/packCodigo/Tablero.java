package packCodigo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import packVentanas.VBuscaminas;

public class Tablero extends Observable{
	
	private int nivel;
	private int columnas;
	private int filas;
	private Casilla[][] matriz;
	
	public Tablero (int pNivel,int pFila, int pColumna){
		nivel = pNivel;
		filas = pFila-1;
		columnas = pColumna-1;
		matriz = new Casilla[pFila][pColumna];	
	}
	
	public void generarMatriz(){
		int minasAColocar = this.calcularMinas();
		int x = this.filas;
		int y = this.columnas;
		int i,j = 0;
		while(minasAColocar != 0){
			System.out.println("vuelta " +minasAColocar);
			i = this.randInt(x);
			j = this.randInt(y);
			if(!((matriz[i][j]) instanceof CasillaMina)){
				matriz[i][j] = CasillaFactory.getMiFactoria().generarCasilla("Mina");
				matriz[i][j].inicializar(i+","+j);
				System.out.println("Casilla: " +i+","+j);
				generarCasillasNumero(i,j);
				minasAColocar--;
			}	
		}
		
		for(int k=0; k<=filas; k++){
			for(int l=0; l<=columnas;l++){
				if(matriz[k][l] == null){
					matriz[k][l] = CasillaFactory.getMiFactoria().generarCasilla("Vacia");
					matriz[k][l].inicializar(""+k+","+l);
					anadirVecinos(k,l);
				}
			}
		}

	}
	
	private int calcularMinas(){
		int sol = nivel*columnas;
		return sol;
	}
	
	public static int randInt(int max) {
		int min = 0;
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + min);
	    return randomNum;
	}
	
	private void generarCasillasNumero(int pFila, int pColumna){
		generarH(pFila,pColumna);
		generarV(pFila,pColumna);
		generarDD(pFila,pColumna);
		generarDI(pFila,pColumna);
	}
	
	private void generarH(int pFila, int pColumna){
		if(pColumna==0){
			if (matriz[pFila][pColumna+1] == null){
				generarHP(pFila, pColumna);
			}else if(matriz[pFila][pColumna+1] instanceof CasillaNumero){
				((CasillaNumero)(matriz[pFila][pColumna+1])).sumarNumero();
			}
		}else if (pColumna==columnas){ 
			if (matriz[pFila][pColumna-1] == null){
				generarHN(pFila, pColumna);
			} else if(matriz[pFila][pColumna-1] instanceof CasillaNumero){
				((CasillaNumero)(matriz[pFila][pColumna-1])).sumarNumero();
			}
		}else {
			if (matriz[pFila][pColumna+1] == null){
				generarHP(pFila, pColumna);
			}else if(matriz[pFila][pColumna+1] instanceof CasillaNumero){
				((CasillaNumero)(matriz[pFila][pColumna+1])).sumarNumero();
			}
			if (matriz[pFila][pColumna-1] == null){
				generarHN(pFila, pColumna);
				}else if(matriz[pFila][pColumna-1] instanceof CasillaNumero){
					((CasillaNumero)(matriz[pFila][pColumna-1])).sumarNumero();
				}
		}
	}
	
	private void generarV(int pFila, int pColumna){
		if(pFila==0){
			if (matriz[pFila+1][pColumna] == null){
				generarVP(pFila, pColumna);
			}	else if(matriz[pFila+1][pColumna] instanceof CasillaNumero){
				((CasillaNumero)(matriz[pFila+1][pColumna])).sumarNumero();	
			}			
		} else if(pFila==filas){
				if (matriz[pFila-1][pColumna] == null){
					generarVN(pFila, pColumna);
				}	else if(matriz[pFila-1][pColumna] instanceof CasillaNumero){
					((CasillaNumero)(matriz[pFila-1][pColumna])).sumarNumero();			
				}			
		} else {
				if (matriz[pFila+1][pColumna] == null){
					generarVP(pFila, pColumna);
				}	else if(matriz[pFila+1][pColumna] instanceof CasillaNumero){
					((CasillaNumero)(matriz[pFila+1][pColumna])).sumarNumero();
				}		
				if (matriz[pFila-1][pColumna] == null){
					generarVN(pFila, pColumna);
				}	else if(matriz[pFila-1][pColumna] instanceof CasillaNumero){
					((CasillaNumero)(matriz[pFila-1][pColumna])).sumarNumero();	
				}	
		}
	}
	
	private void generarDD(int pFila, int pColumna){
		if(pFila==filas && pColumna != columnas){
			if(matriz[pFila-1][pColumna+1] == null){
				generarDDP(pFila, pColumna);
			} else if(matriz[pFila-1][pColumna+1] instanceof CasillaNumero){
				((CasillaNumero)(matriz[pFila-1][pColumna+1])).sumarNumero();
			}
		}else if (pFila==0 && pColumna != 0){
			if(matriz[pFila+1][pColumna-1] == null){
				generarDDN(pFila, pColumna);
			} else if(matriz[pFila+1][pColumna-1] instanceof CasillaNumero){
				((CasillaNumero)(matriz[pFila+1][pColumna-1])).sumarNumero();
				}
			}else if(pColumna == 0 && pFila !=0){
				if(matriz[pFila-1][pColumna+1] == null){
					generarDDP(pFila, pColumna);
				} else if(matriz[pFila-1][pColumna+1] instanceof CasillaNumero){
					((CasillaNumero)(matriz[pFila-1][pColumna+1])).sumarNumero();
				}
			} else if(pColumna == columnas && pFila!=0){
				if(matriz[pFila+1][pColumna-1] == null){
					generarDDN(pFila, pColumna);
				} else if(matriz[pFila+1][pColumna-1] instanceof CasillaNumero){
					((CasillaNumero)(matriz[pFila+1][pColumna-1])).sumarNumero();
					}
			}
			else if((pFila==0 && pColumna==0) || (pFila==filas && pColumna == 0) 
					|| (pFila == 0 && pColumna == columnas) || (pFila == filas && pColumna == columnas)){
				
			} else{
				if(matriz[pFila-1][pColumna+1] == null){
					generarDDP(pFila, pColumna);
				} else if(matriz[pFila-1][pColumna+1] instanceof CasillaNumero){
					((CasillaNumero)(matriz[pFila-1][pColumna+1])).sumarNumero();
				}
				if(matriz[pFila+1][pColumna-1] == null){
					generarDDN(pFila, pColumna);
				} else if(matriz[pFila+1][pColumna-1] instanceof CasillaNumero){
					((CasillaNumero)(matriz[pFila+1][pColumna-1])).sumarNumero();
					}
			}
		}
	
	
	private void generarDI(int pFila, int pColumna){
		if(pFila==0 && pColumna != columnas){
			if(matriz[pFila+1][pColumna+1] == null){
				generarDIP(pFila, pColumna);
			} else if(matriz[pFila+1][pColumna+1] instanceof CasillaNumero){
				((CasillaNumero)(matriz[pFila+1][pColumna+1])).sumarNumero();
			}
		}else if (pFila==filas && pColumna != 0){
			if(matriz[pFila-1][pColumna-1] == null){
				generarDIN(pFila, pColumna);
			} else if(matriz[pFila-1][pColumna-1] instanceof CasillaNumero){
				((CasillaNumero)(matriz[pFila-1][pColumna-1])).sumarNumero();
				}
			} else if(pColumna ==0 && pFila!=filas){
				if(matriz[pFila+1][pColumna+1] == null){
					generarDIP(pFila, pColumna);
				} else if(matriz[pFila+1][pColumna+1] instanceof CasillaNumero){
					((CasillaNumero)(matriz[pFila+1][pColumna+1])).sumarNumero();
				}
				}else if(pColumna == columnas && pFila !=0){
					if(matriz[pFila-1][pColumna-1] == null){
						generarDIN(pFila, pColumna);
					} else if(matriz[pFila-1][pColumna-1] instanceof CasillaNumero){
						((CasillaNumero)(matriz[pFila-1][pColumna-1])).sumarNumero();
						}
				}else if((pFila==0 && pColumna==0) || (pFila==filas && pColumna == 0) 
						|| (pFila == 0 && pColumna == columnas) || (pFila == filas && pColumna == columnas)){
				
				
			} else {
				if(matriz[pFila+1][pColumna+1] == null){
					generarDIP(pFila, pColumna);
				} else if(matriz[pFila+1][pColumna+1] instanceof CasillaNumero){
					((CasillaNumero)(matriz[pFila+1][pColumna+1])).sumarNumero();
				}
				if(matriz[pFila-1][pColumna-1] == null){
					generarDIN(pFila, pColumna);
				} else if(matriz[pFila-1][pColumna-1] instanceof CasillaNumero){
					((CasillaNumero)(matriz[pFila-1][pColumna-1])).sumarNumero();
					}
			}
	}
	
	private void generarHP(int pFila, int pColumna){
		matriz[pFila][pColumna+1] = CasillaFactory.getMiFactoria().generarCasilla("Numero");
		matriz[pFila][pColumna+1].inicializar(""+pFila+","+(pColumna+1));
		((CasillaNumero)(matriz[pFila][pColumna+1])).sumarNumero();
	}
	
	private void generarHN(int pFila, int pColumna){
		matriz[pFila][pColumna-1] = CasillaFactory.getMiFactoria().generarCasilla("Numero");
		matriz[pFila][pColumna-1].inicializar(""+pFila+","+(pColumna-1));
		((CasillaNumero)(matriz[pFila][pColumna-1])).sumarNumero();
	}
	
	private void generarVP(int pFila, int pColumna){
		matriz[pFila+1][pColumna] = CasillaFactory.getMiFactoria().generarCasilla("Numero");
		matriz[pFila+1][pColumna].inicializar(""+(pFila+1)+","+pColumna);
		((CasillaNumero)(matriz[pFila+1][pColumna])).sumarNumero();
	}
	
	private void generarVN(int pFila, int pColumna){
		matriz[pFila-1][pColumna] = CasillaFactory.getMiFactoria().generarCasilla("Numero");
		matriz[pFila-1][pColumna].inicializar(""+(pFila-1)+","+pColumna);
		((CasillaNumero)(matriz[pFila-1][pColumna])).sumarNumero();
	}
	
	private void generarDDP(int pFila, int pColumna){
		matriz[pFila-1][pColumna+1] = CasillaFactory.getMiFactoria().generarCasilla("Numero");
		matriz[pFila-1][pColumna+1].inicializar(""+(pFila-1)+","+(pColumna+1));
		((CasillaNumero)(matriz[pFila-1][pColumna+1])).sumarNumero();
	}
	
	private void generarDDN(int pFila, int pColumna){
		matriz[pFila+1][pColumna-1] = CasillaFactory.getMiFactoria().generarCasilla("Numero");
		matriz[pFila+1][pColumna-1].inicializar(""+(pFila+1)+","+(pColumna-1));
		((CasillaNumero)(matriz[pFila+1][pColumna-1])).sumarNumero();
	}
	
	private void generarDIP(int pFila, int pColumna){
		matriz[pFila+1][pColumna+1] = CasillaFactory.getMiFactoria().generarCasilla("Numero");
		matriz[pFila+1][pColumna+1].inicializar(""+(pFila+1)+","+(pColumna+1));
		((CasillaNumero)(matriz[pFila+1][pColumna+1])).sumarNumero();
	}
	
	private void generarDIN(int pFila, int pColumna){
		matriz[pFila-1][pColumna-1] = CasillaFactory.getMiFactoria().generarCasilla("Numero");
		matriz[pFila-1][pColumna-1].inicializar(""+(pFila-1)+","+(pColumna-1));
		((CasillaNumero)(matriz[pFila-1][pColumna-1])).sumarNumero();
	}
	public Casilla buscarCasilla(int pFila, int pCol) {
		Casilla sol = matriz[pFila][pCol];
		return sol;
	}

	
	private void anadirVecinos(int pFila, int pCol){	
		anadirVecinosH(pFila,pCol);
		anadirVecinosV(pFila,pCol);
		anadirVecinosDD(pFila,pCol);
		anadirVecinosDI(pFila,pCol);
	}
	
	
	private void anadirVecinosH(int pFila, int pCol) {
		if(pCol != columnas && pCol != 0){
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino(pFila+","+(pCol-1));
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino(pFila+","+(pCol+1));
			} else if(pCol == 0){
				((CasillaVacia)(matriz[pFila][pCol])).anadirVecino(pFila+","+(pCol+1));
		} else if(pCol == columnas){
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino(pFila+","+(pCol-1));
		}
	}
	
	private void anadirVecinosV(int pFila, int pCol) {
		if(pFila != filas && pFila != 0){
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila-1)+","+pCol);
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila+1)+","+pCol);
			} else if(pFila == 0){
				((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila+1)+","+pCol);		
		} else if(pFila == columnas){
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila-1)+","+pCol);
		}
	}
	
	private void anadirVecinosDD(int pFila, int pCol) {
		if(pFila == 0 && pCol != 0){
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila+1)+","+(pCol-1));
		}else if(pFila == filas && pCol != columnas){
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila-1)+","+(pCol+1));
		} else if(pFila != 0 && pCol == 0){
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila-1)+","+(pCol+1));
		} else if(pFila != filas && pCol == columnas){
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila+1)+","+(pCol-1));
		} else if((pFila==0 && pCol==0) || (pFila==filas && pCol == 0) || (pFila == 0 && pCol == columnas) || (pFila == filas && pCol == columnas)){
			
		} else{
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila+1)+","+(pCol-1));
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila-1)+","+(pCol+1));
		}
					
	}
	
	private void anadirVecinosDI(int pFila, int pCol) {
		if(pFila == 0 && pCol != columnas){
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila+1)+","+(pCol+1));
		}else if(pFila == filas && pCol != 0){
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila-1)+","+(pCol-1));
		} else if(pFila != filas && pCol == 0){
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila+1)+","+(pCol+1));
		} else if(pFila != 0 && pCol == columnas){
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila-1)+","+(pCol-1));
		} else if((pFila==0 && pCol==0) || (pFila==filas && pCol == 0) || (pFila == 0 && pCol == columnas) || (pFila == filas && pCol == columnas)){
			
		} else{
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila+1)+","+(pCol+1));
			((CasillaVacia)(matriz[pFila][pCol])).anadirVecino((pFila-1)+","+(pCol-1));
		}
					
	}

	public ArrayList<String> minas(){
		
		ArrayList<String> ls = new ArrayList<String>();
		
		for(int i=0; i<=filas; i++){
			for (int j=0; j<=columnas; j++){
				if(matriz[i][j] instanceof CasillaMina){
					System.out.println("SOY LA MINA: "+((CasillaMina)matriz[i][j]).obtenerCoordenadas());
					ls.add(((CasillaMina)matriz[i][j]).obtenerCoordenadas());
				}
			}
		}
		return ls;
	}

	public ArrayList<String> vacias() {
		ArrayList<String> ls = new ArrayList<String>();
		
		for(int i=0; i<=filas; i++){
			for (int j=0; j<=columnas; j++){
				if(matriz[i][j] instanceof CasillaVacia){
					ls.add(matriz[i][j].obtenerCoordenadas());
				}
			}
		}
		return ls;
	}

	public int obtenerNumFilas() {
		// TODO Auto-generated method stub
		return this.filas;
	}
	
	public int obtenerNumColumnas() {
		// TODO Auto-generated method stub
		return this.columnas;
	}
	
	public void imprimirMatriz() {
		for(int i=0; i<=filas; i++){
			for (int j=0; j<=columnas; j++){
				matriz[i][j].imprimirInfo();
			}
		}
	}

	public void ponerBandera(int fila, int col) {
		// TODO Auto-generated method stub
		matriz[fila][col].cambioBandera();
		setChanged();
		
		notifyObservers(matriz[fila][col].tieneBandera());
	}

	public void descubrirCasilla(int pFila, int pCol) {
		// TODO Auto-generated method stub
		Casilla cas = buscarCasilla(pFila, pCol);
		
		if(cas instanceof CasillaNumero){
			int num=((CasillaNumero)cas).obtenerNumero();
			cas.descubrir();
			setChanged();
			notifyObservers(pFila+","+pCol+","+num);
		} if (cas instanceof CasillaVacia){
			cas.descubrir();
			setChanged();
			notifyObservers(pFila+","+pCol+","+0);
		}if (cas instanceof CasillaMina){
			cas.descubrir();
			setChanged();
			notifyObservers(pFila+","+pCol+","+10);
		}
	}
}


