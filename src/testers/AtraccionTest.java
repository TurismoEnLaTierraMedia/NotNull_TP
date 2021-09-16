package testers;

import static org.junit.Assert.*;

import org.junit.Test;

import clases.Atraccion;

public class AtraccionTest {

	@Test
	public void creacionAtraccionesTest() {
		//Admite tipo de atracción Degustación con tilde
		Atraccion Moria = new Atraccion("Moria", 10, 2, 6, "Degustación");
		Atraccion Moria2 = new Atraccion("Moria", 10, 2, 6, "degustacion");
		Atraccion MinasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, "Paisaje");
		Atraccion LaComarca = new Atraccion("La Comarca", 3, 6.5, 150, "Degustación");
		//Admite tipo de atracción con mayúscula
		Atraccion Mordor = new Atraccion("Mordor", 25, 3, 4, "AVENTURA");
		Atraccion AbismodeHelm = new Atraccion("Abismo de Helm", 5, 2, 15, "Paisaje");
		Atraccion Lothlorien = new Atraccion("Lothlórien", 35, 1, 30, "Degustacion");
		//Admite tipo de atracción con minúscula
		Atraccion Erebor = new Atraccion("Erebor", 12, 3, 32, "paisaje");
		Atraccion BosqueNegro = new Atraccion("Bosque Negro", 3, 4, 12, "Aventura");

		assertEquals(Moria2, Moria);
		assertFalse(Moria2.equals(Mordor));
	}

	
	@Test
	public void tieneCupoTest() {
		Atraccion Moria = new Atraccion("Moria", 0, 2, 0, "degustacion");
		Atraccion Moria2 = new Atraccion("Moria", 10, 2, 2, "Degustación");
		assertFalse(Moria.tieneCupo());
		assertTrue(Moria2.tieneCupo());
	}
	
	@Test
	public void queReduceCupoTest() {
		Atraccion Moria = new Atraccion("Moria", 0, 2, 7, "degustacion");
		Moria.reducirCupo();
		assertEquals(6, Moria.getCupo());
	}
	
	//No admite Tipos de Atracción que no están especificados en el enum Tipo_De_Atraccion
	@Test
	public void tipoDeAtraccionErroneoTest() {
		Atraccion Moria = new Atraccion("Moria", 0, 2, 0, "Excursion");
	}
}