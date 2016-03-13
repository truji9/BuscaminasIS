package packCodigo;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {
	
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
			i = this.randInt(x);
			j = this.randInt(y);
			if(!((matriz[i][j]) instanceof CasillaMina)){
				matriz[i][j] = CasillaFactory.getMiFactoria().generarCasilla("Mina");
				matriz[i][j].inicializar(""+i+","+j);
				generarCasillasNumero(i,j);
				minasAColocar--;
			}	
		}
		
		for(int k=0; k<=filas; k++){
			for(int l=0; l<=columnas;l++){
				if(matriz[k][l] == null){
					matriz[k][l] = CasillaFactory.getMiFactoria().generarCasilla("Vacia");
					matriz[k][l].inicializar(""+k+","+l);
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
				((CasillaNumero)(matriz[pFila][pColumna-1])).sumarNumero();
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
		if(pFila==filas && pColumna == 0){
			if(matriz[pFila-1][pColumna+1] == null){
				generarDDP(pFila, pColumna);
			} else if(matriz[pFila-1][pColumna+1] instanceof CasillaNumero){
				((CasillaNumero)(matriz[pFila-1][pColumna+1])).sumarNumero();
			}
		}else if (pFila==0 && pColumna == columnas){
			if(matriz[pFila+1][pColumna-1] == null){
				generarDDN(pFila, pColumna);
			} else if(matriz[pFila+1][pColumna-1] instanceof CasillaNumero){
				((CasillaNumero)(matriz[pFila+1][pColumna-1])).sumarNumero();
				}
			} else {
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
		if(pFila==0 && pColumna == 0){
			if(matriz[pFila+1][pColumna+1] == null){
				generarDIP(pFila, pColumna);
			} else if(matriz[pFila+1][pColumna+1] instanceof CasillaNumero){
				((CasillaNumero)(matriz[pFila+1][pColumna+1])).sumarNumero();
			}
		}else if (pFila==filas && pColumna == columnas){
			if(matriz[pFila-1][pColumna-1] == null){
				generarDIN(pFila, pColumna);
			} else if(matriz[pFila-1][pColumna-1] instanceof CasillaNumero){
				((CasillaNumero)(matriz[pFila-1][pColumna-1])).sumarNumero();
				}
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

	
	public ArrayList<String> minas(){
		
		ArrayList<String> ls = new ArrayList<String>();
		
		for(int i=0; i<=filas; i++){
			for (int j=0; j<=columnas; j++){
				if(matriz[i][j] instanceof CasillaMina){
					ls.add(matriz[i][j].obtenerCoordenadas());
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
}

