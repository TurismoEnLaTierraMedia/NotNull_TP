package clases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import jdbc.ConnectionProvider;
import jdbc.CreadorBaseDeDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class Admin {

	private static String msgBienvenida(String nombre) throws SQLException {
		return "Bienvenido " + nombre + "\n";
	}

	// Intentar que el metodo este en sistema
	private static String datosUsuario(Sistema s1, Usuario usu) {
		return s1.pedirItinerario(usu);
	}

	/**
	 * Metodo que se encarga de ofertar las promociones al usuario que cumpla con
	 * sus preferencias
	 * 
	 * @param usuActual         Usuario
	 * @param promocionIterator Iterator<Promocion>
	 * @param s1                Sistema
	 * @param sc1               Scanner
	 */
	private static void ofrecerPromociones(Usuario usuActual, Iterator<Promocion> promocionIterator, Sistema s1,
			Scanner sc1) {
		while (promocionIterator.hasNext()) {
			Promocion promocion = promocionIterator.next();
			// chequeo si la promocion cumple con las preferencias del usuario
			if (s1.recomendar(usuActual, promocion)) {
				// Si cumple, le pregunto si desea comprarla
				System.out.println(promocion.toString());
				String respuesta;
				do {
					System.out.println("Escriba Si/No");
					respuesta = sc1.next().toUpperCase();
				} while (!(respuesta.equals("SI") || respuesta.equals("NO")));

				System.out.println("");
				if (s1.concretarCompra(usuActual, respuesta, promocion)) {
					s1.aniadirCompraAInformes(new CompraPromocion(promocion, usuActual));
					System.out.println("Compra exitosa");
					System.out.println("");
				}

			}
		}
	}

	/**
	 * Metodo que oferta las atracciones restantes que no hayan cumplido con
	 * 
	 * @param usuActual
	 * @param atraccionIterator
	 * @param s1
	 * @param sc1
	 */
	private static void ofrecerResto(Usuario usuActual, Iterator<Atraccion> atraccionIterator, Sistema s1,
			Scanner sc1) {
		while (atraccionIterator.hasNext()) {
			Atraccion atraccion = (Atraccion) atraccionIterator.next();
			if (!usuActual.chequearAtraccionEnPromociones(atraccion) && s1.puedeComprar(usuActual, atraccion)) {
				System.out.println(atraccion.toString());
				String respuesta;
				do {
					System.out.println("Escriba Si/No");
					respuesta = sc1.next().toUpperCase();
				} while (!(respuesta.equals("SI") || respuesta.equals("NO")));
				if (s1.concretarCompra(usuActual, respuesta, atraccion)) {
					s1.aniadirCompraAInformes(new CompraAtraccion(atraccion, usuActual));
					System.out.println("Compra exitosa");
					System.out.println("");
				}
			}
		}
	}

	private static void ofrecerAtracciones(Usuario usuActual, Iterator<Atraccion> atraccionIterator, Sistema s1,
			Scanner sc1) {
		ArrayList<Atraccion> atraccionesTipoDistinto = new ArrayList<>();
		// Recorro lista entera
		while (atraccionIterator.hasNext()) {
			Atraccion atraccion = atraccionIterator.next();
			// Chequeo si la atraccion cumple con las preferencias del usuario
			if (s1.recomendar(usuActual, atraccion) && !usuActual.chequearAtraccionEnPromociones(atraccion)) {
				System.out.println(atraccion.toString());
				String respuesta;
				do {
					System.out.println("Escriba Si/No");
					respuesta = sc1.next().toUpperCase();
				} while (!(respuesta.equals("SI") || respuesta.equals("NO")));
				if (s1.concretarCompra(usuActual, respuesta, atraccion)) {
					s1.aniadirCompraAInformes(new CompraAtraccion(atraccion, usuActual));
					System.out.println("Compra exitosa");
					System.out.println("");
				}

			} else {
				// Si no es del mismo tipo que la preferencia del usuario, la guardo para
				// despues.
				atraccionesTipoDistinto.add(atraccion);
			}

		}
		// Terminado la oferta de atracciones con mismo tipo, le ofrecemos el resto
		Iterator<Atraccion> atraccionesTipoDistintoIterator = atraccionesTipoDistinto.iterator();
		ofrecerResto(usuActual, atraccionesTipoDistintoIterator, s1, sc1);
	}

	public static void main(String[] args) throws SQLException {
		CreadorBaseDeDatos.crearBaseDeDatos();
		Sistema s1 = new Sistema();
		Scanner sc1 = new Scanner(System.in);

		s1.cargaUsuarios("ListaDeUsuarios");
		s1.cargaAtracciones("ListaDeAtracciones");
		s1.cargaPromociones("ListaDePromociones");

		Iterator<Usuario> usuarioIterator = s1.getUsuarios().iterator();
		Iterator<Atraccion> atraccionIterator = s1.getAtracciones().iterator();
		Iterator<Promocion> promocionIterator = s1.getPromociones().iterator();

		// Aqui va a ir recorriendo usuario por usuario
		while (usuarioIterator.hasNext()) {
			Usuario usuActual = (Usuario) usuarioIterator.next();
			// Si el usuario tiene dinero y tiempo para comprar al menos una atraccion o
			// promocion, entra, sino se le informa.
			if (s1.usuarioTieneSuficienteDineroYTiempoParaRecomendar(usuActual)) {
				System.out.println(msgBienvenida(usuActual.getNombre()));
				// Recorrido de promociones

				ofrecerPromociones(usuActual, promocionIterator, s1, sc1);

				// Terminada la oferta de promociones, le ofertamos las atracciones.
				if (s1.usuarioTieneSuficienteDineroYTiempoParaAtracciones(usuActual)) {
					System.out.println("Le ofrecemos las siguientes atracciones");
				}

				ofrecerAtracciones(usuActual, atraccionIterator, s1, sc1);
				// Terminado el proceso, antes de pasar al siguiente. Se genera un itinerario
				// con todas las transacciones del usuario.

				System.out.println("");
				// Si el usuario compro algo, imprime un resumen, de no ser asi, le informa.
				if (usuActual.comproAlgo()) {
					System.out.println("Gracias por su tiempo, aqui tiene un resumen de sus transacciones");
					System.out.println("");
					System.out.println(datosUsuario(s1, usuActual));
					System.out.println("");
				} else {
					System.out.println("Gracias por su tiempo. Usted no ha realizado transacciones");
					System.out.println("");
				}
			} else {
				System.out.println("");
				System.out.println("Saludos " + usuActual.getNombre()
						+ ".\n Lamentamos informarle que no dispone de Dinero o Tiempo suficiente para comprar.");
				System.out.println("");
			}
			// Al terminar con un usuario, debo resetear los iteradores de Promocion y
			// Atraccion
			promocionIterator = s1.getPromociones().iterator();
			atraccionIterator = s1.getAtracciones().iterator();
		}

		// Terminado todo el proceso, se genera un archivo de salida para cada usuario
		// con sus transacciones.

		try {
			s1.generarInformes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Generados informes de salida con todos los datos");

	}

}
