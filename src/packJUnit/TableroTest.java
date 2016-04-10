package packJUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packCodigo.Tablero;


public class TableroTest {

	int n1;
  	int c;
	int f;
/*	int n2;
	int n3;

	ArrayList<String> lM;
	ArrayList<String> lCVacias;
	Stack<String> cPV;
	ArrayList<String> lCVisitadas;
	Casilla[][] matriz;*/
	Tablero t;
	
	@Before
	public void setUp() throws Exception {
		n1=1;
		c=10;
		f=5;
/*		n2=2;
		n3=3;

		lM.add("1,4");
		lCVacias.add("1,1");;
		cPV=new Stack<String>();
		lCVisitadas.add("2,2");
		matriz=new Casilla[f][c];*/
		t=new Tablero(n1, f, c);
	}

	@After
	public void tearDown() throws Exception {
/*		lM=new ArrayList<String>();
		lCVacias=new ArrayList<String>();;
		cPV=new Stack<String>();
		lCVisitadas=new ArrayList<String>();
		matriz=new Casilla[f][c];*/
		t=new Tablero(n1, f, c);
	}


/*	@Test
	public void testGenerarMatriz() {
		fail("Not yet implemented"); // TODO
	}*/

	@Test
	public void testRandInt() {
		int num=Tablero.randInt(10);
		assertTrue(num>0);
		assertTrue(num<10);
	}

	@Test
	public void testMinas() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVacias() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testEstaVisitada() {
		assertTrue(t.getCasillasVisitadas().size()==0);
		assertTrue(t.estaVisitada("2,1")==false);
		t.anadirVisitadas("2,1");
		assertTrue(t.getCasillasVisitadas().size()==1);
		assertTrue(t.estaVisitada("2,1")==true);
	}
	
	@Test
	public void testAnadirVisitadas() {
		assertTrue(t.getCasillasVisitadas().size()==0);
		t.anadirVisitadas("1,2");
		assertTrue(t.getCasillasVisitadas().size()==1);
	}
	
	@Test
	public void testAnadirPorVisitar() {
		assertTrue(t.getCasillasPorVisitar().size()==0);
		t.anadirPorVisitar("0,0");
		assertTrue(t.getCasillasPorVisitar().size()==1);
	}
	
	@Test
	public void testAnadirVacia() {
		assertTrue(t.getCasillasVacias().size()==0);
		t.anadirVacia("1,1");
		assertTrue(t.getCasillasVacias().size()==1);
	}
	
	@Test
	public void testCogeryEliminarPorVisitar() {
		t.anadirPorVisitar("0,0");
		assertTrue(t.getCasillasPorVisitar().size()==1);
		t.cogeryEliminarPorVisitar();
		assertTrue(t.getCasillasPorVisitar().size()==0);
	}

}
