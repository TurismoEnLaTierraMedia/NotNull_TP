package clases;

import java.util.Iterator;
import java.util.Scanner;

public class Admin {
	
	private static String msgBienvenida(String nombre) {
		return "Bienvenido " + nombre + "\n" + "Tenemos algunas ofertas para ti";
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
		
		
		//Aqui va a ir recorriendo usuario por usuario
		while (usuarioIterator.hasNext()) {
			Usuario usuActual = (Usuario) usuarioIterator.next();
			System.out.println(msgBienvenida(usuActual.getNombre()));
			//Recorrido de promociones
			while (promocionIterator.hasNext()) {
				Promocion promocion = promocionIterator.next();
				//chequeo si la promocion cumple con las preferencias del usuario
				if (s1.recomendar(usuActual, promocion)) {
					System.out.println(promocion.toString());
					System.out.println("Escriba Si/No");
					s1.concretarCompra(sc1.nextLine());
				}
			}
			
			//Terminada la oferta de promociones, le ofertamos las atracciones.
			System.out.println("Tambien le ofrecemos las siguientes atracciones");
			
			while (atraccionIterator.hasNext()) {
				Atraccion atraccion = atraccionIterator.next();
				
			}
			
			//Terminado el proceso, antes de pasar al siguiente. Se genera un itinerario con todas
			//las transacciones del usuario.
			//s1.generarItinerario(usuActual);
		}
		
		//Terminado todo el proceso, se genera un archivo de salida para cada usuario con sus transacciones.
		//s1.generarInformes();
		
		
		
		
		
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
