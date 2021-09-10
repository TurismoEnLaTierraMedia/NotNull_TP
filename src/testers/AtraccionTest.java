package testers;

import static org.junit.Assert.*;

import org.junit.Test;

import clases.Atraccion;

public class AtraccionTest {

	@Test
	public void creacionAtraccionesTest() {
		Atraccion Moria = new Atraccion("Moria", 10, 2, 6, "Deguación");
		Atraccion Moria1 = new Atraccion("Moria", 10, 2, 6, "degustación");
//		Atraccion MinasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, Tipo_De_Atraccion.PAISAJE);
//		Atraccion LaComarca = new Atraccion("La Comarca", 3, 6.5, 150, Tipo_De_Atraccion.DEGUSTACION);
//		Atraccion Mordor = new Atraccion("Mordor", 25, 3, 4, Tipo_De_Atraccion.AVENTURA);
//		Atraccion AbismodeHelm = new Atraccion("Abismo de Helm", 5, 2, 15, Tipo_De_Atraccion.PAISAJE);
//		Atraccion Lothlorien = new Atraccion("Lothlórien", 35, 1, 30, Tipo_De_Atraccion.DEGUSTACION);
//		Atraccion Erebor = new Atraccion("Erebor", 12, 3, 32, Tipo_De_Atraccion.PAISAJE);
//		Atraccion BosqueNegro = new Atraccion("Bosque Negro", 3, 4, 12, Tipo_De_Atraccion.AVENTURA);
//
//		Atraccion Moria2 = new Atraccion("Moria", 10, 2, 6, Tipo_De_Atraccion.AVENTURA);
//
//		assertEquals(Moria2, Moria);
//		assertFalse(Moria2.equals(Mordor));
	}

}
