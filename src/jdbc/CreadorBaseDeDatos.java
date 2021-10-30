package jdbc;

import java.sql.Statement;
import java.util.Collections;

import clases.Atraccion;
import clases.OrdenablePorPrecioYTiempo;
import clases.Usuario;
import dao.AtraccionDAO;
import dao.FactoryDAO;
import dao.UsuarioDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

public class CreadorBaseDeDatos {
	private static File archivo;
	private static BufferedReader br;
	private static FileReader fr;

	private static void crearTablas(Connection connection) throws SQLException {
		String tablaUsuario = "CREATE TABLE IF NOT EXISTS usuarios (\n" + " usuario_id INTEGER PRIMARY KEY, \n" + " nombre TEXT NOT NULL,\n"
				+ "	presupuesto INTEGER NOT NULL DEFAULT 0,\n" + " tiempodisponible REAL NOT NULL,\n"
				+ "preferencia TEXT NOT NULL,\n" + " UNIQUE(nombre)" + ");";

		String tablaAtraccion = "CREATE TABLE IF NOT EXISTS atracciones (\n" + " atraccion_id INTEGER PRIMARY KEY, \n" + " nombre TEXT NOT NULL,\n"
				+ " costo INTEGER NOT NULL DEFAULT 0,\n" + " tiempo REAL NOT NULL,\n" + " cupo INTEGER NOT NULL,\n"
				+ " tipo TEXT NOT NULL,\n" + "UNIQUE(nombre)" + ");";
		
		String tablaPromocion = "CREATE TABLE IF NOT EXISTS promociones (\n" + " codigoTipoPromocion INTEGER NOT NULL,\n"
				+ " TipoAtraccionPromocion TEXT NOT NULL,\n" + " nombre TEXT NOT NULL,\n" + " costo INTEGER,\n"
				+ " id_listaAtracciones INTEGER NOT NULL,\n" + "UNIQUE(nombre)" + ");";
		
		String tablaTipoAtraccion = "CREATE TABLE IF NOT EXISTS tipoAtracciones (\n" + " id INTEGER NOT NULL,\n"
				+ " nombre TEXT NOT NULL" + ");";
		
		String tablaItinerario = "CREATE TABLE IF NOT EXISTS itinerarios (\n" + " id_usuario INTEGER NOT NULL,\n"
				+ " id_promocionesCompradas INTEGER,\n" + " id_atraccionesCompradas INTEGER,\n" + " tiempoTotal REAL NOT NULL,\n"
				+ " costoTotal INTEGER NOT NULL" + ");";

		Statement stmt = connection.createStatement();
		stmt.execute(tablaUsuario);
		stmt.execute(tablaAtraccion);
		stmt.execute(tablaPromocion);
		stmt.execute(tablaTipoAtraccion);
		stmt.execute(tablaItinerario);
	}
	
	private static void cargaUsuarios(String rutaUsuarios) {
		UsuarioDAO usuDAO= FactoryDAO.getUsuarioDAO();
		try {
			
			archivo = new File(rutaUsuarios);
			if (archivo.length() == 0) {
				throw new Error("El archivo" + rutaUsuarios + " esta vacio");
			}
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] parametros = linea.split("-");
				try {
					Usuario usuario = new Usuario(parametros[0], Integer.parseInt(parametros[1]),
							Double.parseDouble(parametros[2]), parametros[3]);
					usuDAO.insert(usuario);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					System.err.println("No se pudo cargar al usuario " + parametros[0] + ". Error de parseo");
				}
			}
		} catch (NumberFormatException e) {
			System.err.println("Parseo incorrecto");
			e.printStackTrace();
		} catch (Exception IO) {
			IO.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private static void cargaAtraccion(String rutaAtracciones) {
		AtraccionDAO atraccDAO = FactoryDAO.getAtraccionDAO();
		try {
			archivo = new File(rutaAtracciones);
			if (archivo.length() == 0) {
				throw new Error("El archivo" + rutaAtracciones + " esta vacio");
			}
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] parametro = linea.split("-");
				try {
					atraccDAO.insert(new Atraccion(parametro[0], Integer.parseInt(parametro[1]),
							Double.parseDouble(parametro[2]), Integer.parseInt(parametro[3]), parametro[4]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
					System.err.println("No se pudo cargar la atraccion " + parametro[0] + ". Error de parseo");
				}
			}
		} catch (NumberFormatException e) {
			System.err.println("Parseo incorrecto");
			e.printStackTrace();
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
	}
	
	private static void cargaPromocion() {
		
	}
	
	private static void cargaListaAtraccionesPromocion(){
		
	}
	
	public static void cargarEjemplos() {
		cargaUsuarios("ListaDeUsuarios");;
		cargaAtraccion("ListaDeAtracciones");
	}

	public static void crearBaseDeDatos() throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		crearTablas(connection);
		cargarEjemplos();
	}

}
