package testers;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import java.io.*;

import clases.Atraccion;
import clases.OrdenablePorPrecioYTiempo;
import clases.Promocion;
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
	
	private ArrayList<Atraccion> obtenerListaAtracciones(String ruta){
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
	
	private ArrayList<Promocion> obtenerListaPromociones(String ruta){
		return null;
	}

	@Test
	public void cargaArchivosTest() {
		Sistema s1 = new Sistema();
		ArrayList<Usuario> usuarios = obtenerListaUsuarios("ListaDeUsuarios");
		ArrayList<Atraccion> atracciones = obtenerListaAtracciones("ListaDeAtracciones");
		
		s1.cargaUsuarios("ListaDeUsuarios");
		s1.cargaAtracciones("ListaDeAtracciones");
		Assert.assertEquals(s1.getUsuarios(), usuarios);
		Assert.assertEquals(s1.getAtracciones(), atracciones);
	}
	
	@Test(expected = Error.class)
	public void cargaArchivosVaciosTest() {
		Sistema s1 = new Sistema();
		s1.cargaUsuarios("PruebaArchivoVacio.txt");
	}
	
	@Test(expected = NumberFormatException.class)
	public void cargaUsuariosParametrosIncorrectosTest() {
		Sistema s1 = new Sistema();
		s1.cargaUsuarios("PruebaUsuarios.txt");
	}
	

}
