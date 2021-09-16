package testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import clases.Atraccion;
import clases.PromocionAbsoluta;
import clases.PromocionAxB;
import clases.PromocionPorcentual;
import excepciones.NoEsMismoTipoException;
import excepciones.NoHayMasCupoException;

public class PromocionTest {
	
	

	@Test
	public void creacionPromocionAbsoluta() {
		PromocionAbsoluta packDegustacion = new PromocionAbsoluta("Degustacion", "Absoluta Degustacion", 5);
		assertEquals("Absoluta Degustacion", packDegustacion.getNombre());
		PromocionAbsoluta packDegustacion2 = new PromocionAbsoluta("Excursion", "Absoluta Degustacion", 5);
	}
	
	@Test
	public void creacionPromocionAxB() {
		Atraccion Erebor = new Atraccion("Erebor", 12, 3, 32, "paisaje");
		PromocionAxB packPaisajes = new PromocionAxB("Paisaje", "Combo de Paisajes", Erebor);
		assertEquals(Erebor.toString(), packPaisajes.getAtraccionGratis().toString());
	}
	
	@Test
	public void creacionPromocionPorcentual() {
		PromocionPorcentual packAventura = new PromocionPorcentual("Degustacion", "Porcentual de Aventuras", 30);
		assertEquals("Porcentual de Aventuras", packAventura.getNombre());
	}
	
	@Test
	public void anadirAtracciones() throws NoEsMismoTipoException, NoHayMasCupoException {
		Atraccion Erebor = new Atraccion("Erebor", 12, 3, 32, "paisaje");
		Atraccion MinasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, "Paisaje");
		Atraccion AbismodeHelm = new Atraccion("Abismo de Helm", 5, 2, 15, "Paisaje");
		
		PromocionAxB packPaisajes = new PromocionAxB("Paisaje", "Combo de Paisajes", Erebor);
		packPaisajes.anadirAtraccion(Erebor);
		packPaisajes.anadirAtraccion(MinasTirith);
		packPaisajes.anadirAtraccion(AbismodeHelm);
		
		assertFalse(packPaisajes.getAtracciones().isEmpty());
	}
	
	
	@Test
	public void calculoPreciofinalAbsoluta() throws NoEsMismoTipoException, NoHayMasCupoException {
		Atraccion LaComarca = new Atraccion("La Comarca", 3, 6.5, 150, "Degustación");
		Atraccion Lothlorien = new Atraccion("Lothlórien", 35, 1, 30, "Degustacion");
		PromocionAbsoluta packDegustacion = new PromocionAbsoluta("Degustacion", "Absoluta Degustacion", 5);
		packDegustacion.anadirAtraccion(Lothlorien);
		packDegustacion.anadirAtraccion(LaComarca);
		assertEquals(33,packDegustacion.obtenerPrecioFinal());
	}

}
