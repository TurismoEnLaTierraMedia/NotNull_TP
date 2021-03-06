package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import dao.AtraccionDAO;
import dao.FactoryDAO;
import dao.ItinerarioAtraccionesCompradasDAO;
import dao.ItinerarioDAO;
import dao.PromocionDAO;
import dao.UsuarioDAO;

import java.io.*;
import java.sql.SQLException;

public class Sistema {

	// Atributos
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private ArrayList<Atraccion> atracciones = new ArrayList<>();
	private ArrayList<Promocion> promociones = new ArrayList<>();
	private ArrayList<InformeCompra> informes = new ArrayList<>();

	// Manejo de archivos
	private File archivo;
	private BufferedReader br;
	private FileReader fr;

	// Creacion de archivos
	private PrintWriter salida;

	public Sistema() {
		this.usuarios = new ArrayList<Usuario>();
		this.atracciones = new ArrayList<Atraccion>();
		this.promociones = new ArrayList<Promocion>();
		this.informes = new ArrayList<InformeCompra>();
	}

	/**
	 * Metodo que devuelve verdadero si el archivo pasado por parametro esta vacio.
	 * 
	 * @param archivo
	 * @return true si el archivo esta vacio
	 */
	private boolean archivoVacio(File archivo) {
		return archivo.length() == 0;
	}

	/**
	 * Metodo que chequea si el usuario pasado por parametro tiene suficiente dinero
	 * y tiempo para comprar una atraccion.
	 * 
	 * @param usu el usuario a revisar
	 * @return boolean
	 */
	public boolean usuarioTieneSuficienteDineroYTiempoParaAtracciones(Usuario usu) {
		Iterator<Atraccion> atraccionesIterator = this.getAtracciones().iterator();
		while (atraccionesIterator.hasNext()) {
			Atraccion atraccion = (Atraccion) atraccionesIterator.next();
			if (this.puedeComprar(usu, atraccion)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo que chequea si el usuario parado por parametro tiene suficiente dinero
	 * y tiempo para comprar al menos una atraccion o promocion.
	 * 
	 * @param usu el usuario a revisar
	 * @return boolean
	 */
	public boolean usuarioTieneSuficienteDineroYTiempoParaRecomendar(Usuario usu) {
		Iterator<Promocion> promocionesIterator = this.getPromociones().iterator();
		while (promocionesIterator.hasNext()) {
			Promocion promocion = (Promocion) promocionesIterator.next();
			if (this.puedeComprar(usu, promocion)) {
				return true;
			}
		}
		return this.usuarioTieneSuficienteDineroYTiempoParaAtracciones(usu);

	}
	
	/**
	 * Metodo que realiza la carga de los usuarios desde la base de datos.
	 * 
	 */
	public void nuevaCargaUsuarios() throws SQLException{
		UsuarioDAO usuDAO = FactoryDAO.getUsuarioDAO();
		this.usuarios = (ArrayList<Usuario>)usuDAO.findAll();
	}
	
	public void nuevaCargaAtracciones() throws SQLException{
		AtraccionDAO atraccDAO = FactoryDAO.getAtraccionDAO();
		this.atracciones = (ArrayList<Atraccion>)atraccDAO.findAll();
		Collections.sort(this.atracciones, new OrdenablePorPrecioYTiempo());
	}
	
	public void nuevaCargaPromociones() throws SQLException{
		PromocionDAO promoDAO = FactoryDAO.getPromocionDAO();
		this.promociones = (ArrayList<Promocion>)promoDAO.findAll();
	}

	/**
	 * Metodo que realiza la carga de las atracciones. Recibe como parametro la ruta
	 * del archivo.
	 * 
	 * @param rutaAtracciones
	 */
	public void cargaAtracciones(String rutaAtracciones) {
		try {
			archivo = new File(rutaAtracciones);
			if (this.archivoVacio(archivo)) {
				throw new Error("El archivo" + rutaAtracciones + " esta vacio");
			}
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] parametro = linea.split("-");
				try {
					this.atracciones.add(new Atraccion(parametro[0], Integer.parseInt(parametro[1]),
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
		// Terminada la carga de las atracciones, ordeno la lista
		Collections.sort(this.atracciones, new OrdenablePorPrecioYTiempo());
	}

	// CARGA DE PROMOCIONES - METODOS

	/**
	 * Este metodo devuelve una Atraccion que se encuentra la lista de atracciones.
	 * El parametro corresponde al nombre de la atraccion -Basicamente recorre la
	 * lista de atracciones, pidiendo su nombre y lo compara con el nombre pasado
	 * por parametro
	 * 
	 * @param nombre
	 * @return Atraccion
	 */
	private Atraccion convertirStringAAtraccion(String nombre) {
		Atraccion result = null;
		Iterator<Atraccion> atraccionIterator = this.getAtracciones().iterator();
		while (atraccionIterator.hasNext()) {
			Atraccion atraccionit = atraccionIterator.next();
			if (nombre.equalsIgnoreCase(atraccionit.getNombre())) {
				result = atraccionit;
			}
		}
		if (result == null) {
			throw new IllegalArgumentException("No se encontro la atraccion " + nombre + "en la lista de atracciones");
		}
		return result;
	}

	/**
	 * Metodo que determina el tipo de promocion. Recibe como parametro un array de
	 * Strings con los elementos del constructor.
	 * <p>
	 * Devuelve:
	 * <ul>
	 * <li>PromocionAbsoluta si el codigo es 1
	 * <li>PromocionPorcentual si el codigo es 2
	 * <li>PromocionAxB si el codigo es 3
	 * <li>Lanza una excepcion si el codigo no es ninguno de los anteriores.</li>
	 * </li></li></li>
	 * </ul>
	 *
	 * 
	 * @param
	 * @return Promocion
	 */
	private Promocion determinarTipoPromocion(String[] parametro) {
		if (Integer.parseInt(parametro[0]) == 1) {
			try {
				return new PromocionAbsoluta(parametro[1], parametro[2], Integer.parseInt(parametro[3]));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println("No se pudo cargar la promocion " + parametro[1] + ". Error de parseo.");
				throw new NumberFormatException();
			}
		} else if (Integer.parseInt(parametro[0]) == 2) {
			try {
				return new PromocionPorcentual(parametro[1], parametro[2], Integer.parseInt(parametro[3]));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println("No se pudo cargar la promocion " + parametro[1] + ". Error de parseo.");
				throw new NumberFormatException();
			}
		} else if (Integer.parseInt(parametro[0]) == 3) {
			try {
				return new PromocionAxB(parametro[1], parametro[2], convertirStringAAtraccion(parametro[3]));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println("No se pudo cargar la promocion " + parametro[1] + ". Error de parseo.");
				throw new NumberFormatException();
			}
		} else {
			throw new IllegalArgumentException("Codigo de tipo de promocion invalido."
					+ " \n 1-Promocion absoluta \n 2-Promocion porcentual \n 3-Promocion AxB");
		}

	}

	/**
	 * Metodo que realiza la carga de las promociones. Se pasa como parametro la
	 * ruta del archivo con la lista.
	 * 
	 * @param rutaPromociones
	 */
	public void cargaPromociones(String rutaPromociones) {
		try {
			archivo = new File(rutaPromociones);
			if (this.archivoVacio(archivo)) {
				throw new Error("El archivo" + rutaPromociones + " esta vacio");
			}
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] parametro = linea.split("-");
				try {
					Promocion promocionaAgregar = determinarTipoPromocion(parametro);
					for (int i = 4; i < parametro.length; i++) {
						promocionaAgregar.anadirAtraccion(convertirStringAAtraccion(parametro[i]));
					}
					this.promociones.add(promocionaAgregar);
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

	/**
	 * Metodo que hace algo
	 * 
	 * @param rutaUsuarios
	 * @param rutaAtracciones
	 * @param rutaPromociones
	 */
	public void cargarListas(String rutaUsuarios, String rutaAtracciones, String rutaPromociones) throws SQLException{
		nuevaCargaUsuarios();
		cargaAtracciones(rutaAtracciones);
		cargaPromociones(rutaPromociones);
	}

	public boolean puedeComprar(Usuario usu, Promocion promo) {
		return usu.getPresupuesto() >= promo.obtenerPrecioFinal()
				&& usu.getTiempoDisponible() >= promo.getTiempoTotal();
	}

	public boolean puedeComprar(Usuario usu, Atraccion atrac) {
		return usu.getPresupuesto() >= atrac.getCostoDeVisita() && usu.getTiempoDisponible() >= atrac.getDuracion();
	}

	public boolean recomendar(Usuario usu, Atraccion atrac) {
		if (usu.getPreferencia().equals(atrac.getTipo()) && atrac.tieneCupo()) {
			return this.puedeComprar(usu, atrac);
		}
		return false;
	}

	/**
	 * 
	 * @param usu
	 * @param promo
	 * @return
	 */
	public boolean recomendar(Usuario usu, Promocion promo) {
		if (usu.getPreferencia().equals(promo.getTipo())) {
			if (usu.getPresupuesto() >= promo.obtenerPrecioFinal()
					&& usu.getTiempoDisponible() >= promo.getTiempoTotal()) {
				return true;
			}
		}
		return false;

	}

	public boolean concretarCompra(Usuario usu, String respuesta, Promocion promo) {
		if (respuesta.equalsIgnoreCase("si")) {
			usu.comprarPomocion(promo);
			promo.reducirCupo();
			return true;
		}
		return false;
	}

	public boolean concretarCompra(Usuario usu, String respuesta, Atraccion atrac) {
		if (respuesta.equalsIgnoreCase("si")) {
			if (usu.chequearAtraccionEnPromociones(atrac)) {
				throw new IllegalArgumentException("La atraccion ya fue comprada");
			}
			usu.comprarAtraccion(atrac);
			atrac.reducirCupo();
			return true;
		}
		return false;
	}

	public void aniadirCompraAInformes(InformeCompra informe) {
		informes.add(informe);
	}

	public void generarInformes() throws IOException {
		Iterator<Usuario> usuarios = this.getUsuarios().iterator();
		while (usuarios.hasNext()) {
			Usuario usuario = (Usuario) usuarios.next();
			Iterator<InformeCompra> informes = this.getInformes().iterator();
			salida = new PrintWriter(usuario.getNombre() + ".txt");
			String datos = usuario.toString();
			String movimientos = "";
			int costoTotal = 0;
			double tiempoTotal = 0;
			while (informes.hasNext()) {
				InformeCompra informeCompra = (InformeCompra) informes.next();

				if (informeCompra.getUsuario().equals(usuario)) {
					movimientos += informeCompra.toString() + "\n";
					if (informeCompra instanceof CompraAtraccion) {
						costoTotal += ((CompraAtraccion) informeCompra).getAtraccion().getCostoDeVisita();
						tiempoTotal += ((CompraAtraccion) informeCompra).getAtraccion().getDuracion();
					}
					if (informeCompra instanceof CompraPromocion) {
						costoTotal += ((CompraPromocion) informeCompra).getPromocion().obtenerPrecioFinal();
						tiempoTotal += ((CompraPromocion) informeCompra).getPromocion().getTiempoTotal();
					}
				}
			}
			salida.println(datos);
			salida.println("");
			salida.println(movimientos);
			salida.println("");
			if (costoTotal != 0 && tiempoTotal != 0) {
				salida.println("Resumen de compra");
				salida.println("-------------------------------");
				salida.println("Costo total: " + costoTotal + " - Tiempo requerido: " + tiempoTotal);
			} else {
				salida.println("Este usuario no ha realizado compras");
			}
			salida.close();
		}

	}

	public String pedirItinerario(Usuario usu) {
		String datosUsuario = "Itinerario de " + usu.getNombre() + "\n";
		if (this.usuarios.contains(usu)) {
			Itinerario itinerarioUsuario = usu.generarItinerario();

			if (!itinerarioUsuario.getPromocionesCompradas().isEmpty()) {
				datosUsuario += "Promociones Compradas" + "\n";
			}
			for (Iterator<Promocion> iterator = itinerarioUsuario.getPromocionesCompradas().iterator(); iterator
					.hasNext();) {
				Promocion promocion = (Promocion) iterator.next();
				datosUsuario += "-" + promocion.getNombre() + "\n";
			}

			if (!itinerarioUsuario.getAtraccionesCompradas().isEmpty()) {
				datosUsuario += "Atracciones Compradas" + "\n";
			}
			for (Iterator<Atraccion> iterator = itinerarioUsuario.getAtraccionesCompradas().iterator(); iterator
					.hasNext();) {
				Atraccion atraccion = (Atraccion) iterator.next();
				datosUsuario += "-" + atraccion.getNombre() + "\n";
			}

			datosUsuario += "\n" + "Costo Final: " + itinerarioUsuario.getTotalPagar() + " Tiempo requerido: "
					+ itinerarioUsuario.getTiempoNecesario();
		} else {
			throw new IllegalArgumentException("Este usuario no esta en la lista");
		}
		return datosUsuario;
	}

	private void actualizarUsuarios() throws SQLException {
		UsuarioDAO usuDAO = FactoryDAO.getUsuarioDAO();
		Iterator<Usuario> usuarioIterator = this.getUsuarios().iterator();
		while (usuarioIterator.hasNext()) {
			Usuario usuario = (Usuario) usuarioIterator.next();
			usuDAO.update(usuario);
		}
	}

	private void actualizarAtracciones() throws SQLException {
		AtraccionDAO atracDAO = FactoryDAO.getAtraccionDAO();
		Iterator<Atraccion> atraccionIterator = this.getAtracciones().iterator();
		while (atraccionIterator.hasNext()) {
			Atraccion atraccion = (Atraccion) atraccionIterator.next();
			atracDAO.update(atraccion);
		}
	}
	
	private void actualizarItinerario() throws SQLException {
		ItinerarioDAO itineDAO = FactoryDAO.getItinerarioDAO();
		ItinerarioAtraccionesCompradasDAO itineAtraccDAO = FactoryDAO.getItinerarioAtraccionesCompradasDAO();
		Iterator<Usuario> usuIterator = this.getUsuarios().iterator();
		while (usuIterator.hasNext()) {
			Usuario usuario = (Usuario) usuIterator.next();
			Itinerario usuItinerario = usuario.generarItinerario();
			Iterator<Atraccion> atracciones = usuItinerario.getAtraccionesCompradas().iterator();
			itineDAO.insert(usuItinerario);
			while (atracciones.hasNext()) {
				Atraccion atraccion = (Atraccion) atracciones.next();
				itineAtraccDAO.insert(atraccion);
			}
		}
	}

	public void actualizarBaseDeDatos() throws SQLException {
		this.actualizarUsuarios();
		this.actualizarAtracciones();
		this.actualizarItinerario();
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}

	public ArrayList<Promocion> getPromociones() {
		return promociones;
	}

	public ArrayList<InformeCompra> getInformes() {
		return informes;
	}

}