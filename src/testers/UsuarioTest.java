package testers;

import static org.junit.Assert.*;

import org.junit.Test;

import clases.Tipo_De_Atraccion;
import clases.Usuario;

public class UsuarioTest {

	@Test
	public void creaUsuarioTest() {
		Usuario user = new Usuario("Jorge", 18, 2.5, "Aventura");
		assertNotNull(user);
	}
	
	@Test
	public void chequeoPresupuestoUsuarioTest() {
		Usuario user = new Usuario("Jorge", 18, 2.5, "Aventura");
		assertEquals(18, user.getPresupuesto());
		assertEquals(2.5, user.getTiempoDisponible(),0.01);
		assertEquals(Tipo_De_Atraccion.AVENTURA, user.getPreferencia());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void chequeoControlSetPresupuestoTest() {
		Usuario user2 = new Usuario("Juan", -2, 2.5, "Degustacion");
	}
	
}
