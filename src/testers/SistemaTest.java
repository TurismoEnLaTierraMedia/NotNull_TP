package testers;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.sql.SQLException;

import clases.Atraccion;
import clases.OrdenablePorPrecioYTiempo;
import clases.Promocion;
import clases.PromocionAbsoluta;
import clases.Sistema;
import clases.Usuario;

public class SistemaTest {

	private ArrayList<Usuario> obtenerListaUsuarios(String ruta) {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] parametros = linea.split("-");
				usuarios.add(new Usuario(parametros[0], Integer.parseInt(parametros[1]),
						Double.parseDouble(parametros[2]), parametros[3]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return usuarios;
	}

	private ArrayList<Atraccion> obtenerListaAtracciones(String ruta) {
		ArrayList<Atraccion> atracciones = new ArrayList<>();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] parametro = linea.split("-");
				atracciones.add(new Atraccion(parametro[0], Integer.parseInt(parametro[1]),
						Double.parseDouble(parametro[2]), Integer.parseInt(parametro[3]), parametro[4]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		Collections.sort(atracciones, new OrdenablePorPrecioYTiempo());
		return atracciones;
	}

	@Test
	public void cargaUsuariosTest() throws SQLException{
		Sistema s1 = new Sistema();
		ArrayList<Usuario> usuarios = obtenerListaUsuarios("ListaDeUsuarios");

		s1.nuevaCargaUsuarios();
		Assert.assertEquals(s1.getUsuarios(), usuarios);
	}

	@Test
	public void cargaAtraccionesTest() {
		Sistema s1 = new Sistema();
		ArrayList<Atraccion> atracciones = obtenerListaAtracciones("ListaDeAtracciones");
		s1.cargaAtracciones("ListaDeAtracciones");
		Assert.assertEquals(s1.getAtracciones(), atracciones);
	}

	@Test
	public void metodosVariosTest() throws SQLException{
		Sistema s1 = new Sistema();
		Usuario usu = new Usuario("Probador", 500, 500, "Aventura");
		Atraccion atrac = new Atraccion("Probaneor", 100, 100, 100, "Aventura");
		Promocion promo = new PromocionAbsoluta("Aventura", "Pack probaneor", 50);
		s1.cargarListas("ListaDeUsuarios", "ListaDeAtracciones", "ListaDePromociones");

		Assert.assertTrue(s1.puedeComprar(usu, atrac));
		;
		Assert.assertTrue(s1.puedeComprar(usu, promo));
		Assert.assertTrue(s1.recomendar(usu, atrac));
		Assert.assertTrue(s1.recomendar(usu, promo));
		Assert.assertTrue(s1.concretarCompra(usu, "SI", atrac));
		Assert.assertEquals(400, usu.getPresupuesto());
		Assert.assertEquals(400, usu.getTiempoDisponible(), 0.01);
		Assert.assertEquals(99, atrac.getCupo());
		Assert.assertTrue(usu.getComprasAtracciones().contains(atrac));

	}

}
