package packCodigo;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {
	
	private int nivel;
	private int columnas;
	private int filas;
	private Casilla[][] matriz;
	
	public Tablero (int pNivel,int pFila, int pColumna){
		nivel = pNivel-1;
		filas = pFila-1;
		columnas = pColumna;
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
				matriz[i][j] = new CasillaMina();
				generarCasillasNumero(i,j);
				minasAColocar--;
			}	
		}
		
		for(int k=0; k<=filas; k++){
			for(int l=0; l<=columnas;l++){
				if(matriz[k][l] == null){
					matriz[k][l] = new CasillaVacia();
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
		}else if (pColumna==columnas){ //Miramos a ver si esta en el punto [x][max]
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
		matriz[pFila][pColumna+1] = new CasillaNumero();
		matriz[pFila][pColumna+1].inicializar(""+pFila+","+(pColumna+1));
		((CasillaNumero)(matriz[pFila][pColumna+1])).sumarNumero();
	}
	
	private void generarHN(int pFila, int pColumna){
		matriz[pFila][pColumna-1] = new CasillaNumero();
		matriz[pFila][pColumna-1].inicializar(""+pFila+","+(pColumna-1));
		((CasillaNumero)(matriz[pFila][pColumna-1])).sumarNumero();
	}
	
	private void generarVP(int pFila, int pColumna){
		matriz[pFila+1][pColumna] = new CasillaNumero();
		matriz[pFila+1][pColumna].inicializar(""+(pFila+1)+","+pColumna);
		((CasillaNumero)(matriz[pFila+1][pColumna])).sumarNumero();
	}
	
	private void generarVN(int pFila, int pColumna){
		matriz[pFila-1][pColumna] = new CasillaNumero();
		matriz[pFila-1][pColumna].inicializar(""+(pFila-1)+","+pColumna);
		((CasillaNumero)(matriz[pFila-1][pColumna])).sumarNumero();
	}
	
	private void generarDDP(int pFila, int pColumna){
		matriz[pFila-1][pColumna+1] = new CasillaNumero();
		matriz[pFila-1][pColumna+1].inicializar(""+(pFila-1)+","+(pColumna+1));
		((CasillaNumero)(matriz[pFila-1][pColumna+1])).sumarNumero();
	}
	
	private void generarDDN(int pFila, int pColumna){
		matriz[pFila+1][pColumna-1] = new CasillaNumero();
		matriz[pFila+1][pColumna-1].inicializar(""+(pFila+1)+","+(pColumna-1));
		((CasillaNumero)(matriz[pFila+1][pColumna-1])).sumarNumero();
	}
	
	private void generarDIP(int pFila, int pColumna){
		matriz[pFila+1][pColumna+1] = new CasillaNumero();
		matriz[pFila+1][pColumna+1].inicializar(""+(pFila+1)+","+(pColumna+1));
		((CasillaNumero)(matriz[pFila+1][pColumna+1])).sumarNumero();
	}
	
	private void generarDIN(int pFila, int pColumna){
		matriz[pFila-1][pColumna-1] = new CasillaNumero();
		matriz[pFila-1][pColumna-1].inicializar(""+(pFila-1)+","+(pColumna-1));
		((CasillaNumero)(matriz[pFila-1][pColumna-1])).sumarNumero();
	}
	public static Casilla buscarCasilla(int pFila, int pCol) {
		// TODO Auto-generated method stub
		return null;
	}

}

