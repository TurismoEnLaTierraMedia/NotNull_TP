package jdbc;

import java.sql.Statement;
import java.util.Collections;
import java.util.Iterator;

import clases.Atraccion;
import clases.OrdenablePorPrecioYTiempo;
import clases.Promocion;
import clases.PromocionAbsoluta;
import clases.PromocionAxB;
import clases.PromocionPorcentual;
import clases.Tipo_De_Atraccion;
import clases.Usuario;
import dao.AtraccionDAO;
import dao.FactoryDAO;
import dao.PromocionDAO;
import dao.PromocionListaAtraccionesDAO;
import dao.TipoAtraccionDAO;
import dao.TipoAtraccionDAOImpl;
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
		String tablaUsuario = "CREATE TABLE IF NOT EXISTS usuarios (\n" + " usuario_id INTEGER PRIMARY KEY, \n"
				+ " nombre TEXT NOT NULL,\n" + "	presupuesto INTEGER NOT NULL DEFAULT 0,\n"
				+ " tiempodisponible REAL NOT NULL,\n" + "preferencia TEXT NOT NULL,\n" + " UNIQUE(nombre)" + ");";

		String tablaAtraccion = "CREATE TABLE IF NOT EXISTS atracciones (\n" + " atraccion_id INTEGER PRIMARY KEY, \n"
				+ " nombre TEXT NOT NULL,\n" + " costo INTEGER NOT NULL DEFAULT 0,\n" + " tiempo REAL NOT NULL,\n"
				+ " cupo INTEGER NOT NULL,\n" + " tipo TEXT NOT NULL,\n" + "UNIQUE(nombre)" + ");";

		String tablaPromocion = "CREATE TABLE IF NOT EXISTS promociones (\n" + " id_promocion INTEGER PRIMARY KEY,\n"
				+ " codigoTipoPromocion INTEGER NOT NULL,\n" + " TipoAtraccionPromocion TEXT NOT NULL,\n"
				+ " nombre TEXT NOT NULL,\n" + " costo INTEGER,\n" + " id_listaAtracciones INTEGER,\n"
				+ " id_atraccionGratis INTEGER,\n" + "UNIQUE(nombre)" + ");";

		String tablaAtraccionesDePromocion = "CREATE TABLE IF NOT EXISTS promociones_atracciones (\n"
				+ "id_listaAtracciones INTEGER NOT NULL,\n" + "id_atraccion INTEGER NOT NULL,\n" 
				+ "FOREIGN KEY (id_listaAtracciones) REFERENCES promociones (id_promocion)" + ");";

		String tablaTipoAtraccion = "CREATE TABLE IF NOT EXISTS tipoAtracciones (\n" + " id INTEGER NOT NULL,\n"
				+ " nombre TEXT NOT NULL,\n" + "UNIQUE(nombre)" + ");";

		String tablaItinerario = "CREATE TABLE IF NOT EXISTS itinerarios (\n"
				+ " id_itinerario INTEGER PRIMARY KEY AUTOINCREMENT,\n" + " id_usuario INTEGER NOT NULL,\n"
				+ " tiempoTotal REAL NOT NULL,\n" + " costoTotal INTEGER NOT NULL" + ");";

		String tablaItinerarioPromocionesCompradas = "CREATE TABLE IF NOT EXISTS itinerarios_promocionescompradas (\n"
				+ " id_promocionesCompradas INTEGER NOT NULL,\n" + "id_promocion INTEGER NOT NULL" + ");";

		String tablaItinerarioAtraccionesCompradas = "CREATE TABLE IF NOT EXISTS itinerarios_atraccionescompradas (\n"
				+ " id_atraccionescompradas INTEGER NOT NULL,\n " + " id_atraccion INTEGER NOT NULL,\n"
				+ "FOREIGN KEY (id_atraccionescompradas) REFERENCES itinerarios (id_itinerario)" + ");";

		Statement stmt = connection.createStatement();
		stmt.execute(tablaUsuario);
		stmt.execute(tablaAtraccion);
		stmt.execute(tablaPromocion);
		stmt.execute(tablaAtraccionesDePromocion);
		stmt.execute(tablaTipoAtraccion);
		stmt.execute(tablaItinerario);
		stmt.execute(tablaItinerarioAtraccionesCompradas);
		stmt.execute(tablaItinerarioPromocionesCompradas);
	}

	private static void cargaUsuarios(String rutaUsuarios) {
		UsuarioDAO usuDAO = FactoryDAO.getUsuarioDAO();
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

	private static void cargaTipoAtraccion() throws SQLException {
		TipoAtraccionDAO TatraccDAO = FactoryDAO.getTipoAtraccionDAO();
		Tipo_De_Atraccion[] tipos = Tipo_De_Atraccion.values();

		for (int i = 0; i < tipos.length; i++) {
			TatraccDAO.insert(tipos[i]);
		}
	}

	public static void cargarEjemplos() throws SQLException {
		cargaUsuarios("ListaDeUsuarios");
		cargaAtraccion("ListaDeAtracciones");
		cargaTipoAtraccion();
		cargaPromocion("ListaDePromociones");
	}

	public static void crearBaseDeDatos() throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		crearTablas(connection);
		cargarEjemplos();
	}

	public static void cargaPromocion(String rutaPromociones) throws SQLException {
		PromocionDAO promDao = FactoryDAO.getPromocionPorcentualDao();
		PromocionListaAtraccionesDAO listaAtraccionesDAO = FactoryDAO.getPromocionListaAtraccionesDAO();
		AtraccionDAO atraccDAO = FactoryDAO.getAtraccionDAO();

		try {
			archivo = new File(rutaPromociones);
			if (archivo.length() == 0) {
				throw new Error("El archivo" + rutaPromociones + " esta vacio");
			}
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] parametro = linea.split("-");
				try {
					Promocion promocionaAgregar = new PromocionPorcentual(parametro[1], parametro[2],
							Integer.parseInt(parametro[3]));
					promDao.insert(promocionaAgregar);					
					for (int i = 4; i < parametro.length; i++) {
						listaAtraccionesDAO.insert(atraccDAO.encontrarAtraccion(parametro[i]));
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					System.err.println("No se pudo cargar la promocion " + parametro[2]);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					System.err.println("No se pudo cargar la promocion " + parametro[2]);
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

}
