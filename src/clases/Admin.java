package clases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Admin {

	private static String msgBienvenida(String nombre) {
		return "Bienvenido " + nombre + "\n" + "Tenemos algunas ofertas para ti";
	}

	private static String datosUsuario(Usuario usu) {
		return "Monedas: " + usu.getPresupuesto() + " - Tiempo disponible: " + usu.getTiempoDisponible();
	}

	public static void main(String[] args) {
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
			System.out.println(msgBienvenida(usuActual.getNombre()));
			System.out.println(datosUsuario(usuActual));
			// Recorrido de promociones

			while (promocionIterator.hasNext()) {
				Promocion promocion = promocionIterator.next();
				// chequeo si la promocion cumple con las preferencias del usuario
				if (s1.recomendar(usuActual, promocion)) {
					// Si cumple, le pregunto si desea comprarla
					System.out.println(promocion.toString());
					System.out.println("Escriba Si/No");
					String respuesta = sc1.nextLine();
					s1.concretarCompra(usuActual, respuesta, promocion);
					if (respuesta.equalsIgnoreCase("si")) {
						s1.aniadirCompraAInformes(new CompraPromocion(promocion, usuActual));
					}

				}
			}

			// Terminada la oferta de promociones, le ofertamos las atracciones.
			System.out.println("Tambien le ofrecemos las siguientes atracciones");

			while (atraccionIterator.hasNext()) {
				Atraccion atraccion = atraccionIterator.next();
				// Chequeo si la atraccion cumple con las preferencias del usuario
				if (s1.recomendar(usuActual, atraccion)) {
					System.out.println(atraccion.toString());
					System.out.println("Escriba Si/No");
					String respuesta = sc1.nextLine();
					s1.concretarCompra(usuActual, respuesta, atraccion);
					if (respuesta.equalsIgnoreCase("si")) {
						s1.aniadirCompraAInformes(new CompraAtraccion(atraccion, usuActual));
					}

				}
			}

			// Terminado el proceso, antes de pasar al siguiente. Se genera un itinerario
			// con todas
			// las transacciones del usuario.
			// s1.generarItinerario(usuActual);

			// Al terminar con un usuario, debo resetear los iteradores de Promocion y
			// Atraccion
			System.out.println(datosUsuario(usuActual));
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

		Iterator<InformeCompra> informeIterator = s1.getInformes().iterator();

		while (informeIterator.hasNext()) {
			InformeCompra informeCompra = informeIterator.next();
			System.out.println(informeCompra.toString());

		}

//		System.out.println("Si/No");
//		
//		while (usuarioIterator.hasNext()) {
//			System.out.println(usuarioIterator.next().toString());
//		}
//		
//		while (atraccionIterator.hasNext()) {
//			System.out.println(atraccionIterator.next().toString());
//		}
//		
//		while (promocionIterator.hasNext()) {
//			System.out.println(promocionIterator.next().toString());;
//			
//		}

	}

}
