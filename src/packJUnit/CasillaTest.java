package packJUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packCodigo.Casilla;
import packCodigo.CasillaFactory;

public class CasillaTest {

	private Casilla cV;
	private Casilla cM;
	private Casilla cN;
	private CasillaFactory f;
	
	@Before
	public void setUp() throws Exception {
		cV = f.getMiFactoria().generarCasilla("Vacia");
		cM = f.getMiFactoria().generarCasilla("Mina");
		cN = f.getMiFactoria().generarCasilla("Numero");
		cV.inicializar("0,1");
		cM.inicializar("0,0");
		cN.inicializar("1,1");
	}

	@After
	public void tearDown() throws Exception {
		cV = null;
		cM = null;
		cN = null;
	}

	@Test
	public void testCasilla() {
		assertNotNull(cV);
		assertNotNull(cM);
		assertNotNull(cN);
	}

	@Test
	public void testObtenerCoordenadas() {
		assertTrue(cV.obtenerCoordenadas()=="0,1");
		assertTrue(cM.obtenerCoordenadas()=="0,0");
		assertTrue(cN.obtenerCoordenadas()=="1,1");
	}

	@Test
	public void testCambioBandera() {
		cV.cambioBandera();
		assertTrue(cV.tieneBandera());
		cV.cambioBandera();
		assertFalse(cV.tieneBandera());
		cV.descubrir();
		assertFalse(cV.tieneBandera());
		cM.cambioBandera();
		assertTrue(cM.tieneBandera());
		cM.cambioBandera();
		assertFalse(cM.tieneBandera());
		cM.descubrir();
		assertFalse(cM.tieneBandera());
		cN.cambioBandera();
		assertTrue(cN.tieneBandera());
		cN.cambioBandera();
		assertFalse(cN.tieneBandera());
		cN.descubrir();
		assertFalse(cN.tieneBandera());
		
	}

	@Test
	public void testEstaDesvelada() {
		cV.imprimirInfo();
		cM.imprimirInfo();
		cN.imprimirInfo();
	}
}