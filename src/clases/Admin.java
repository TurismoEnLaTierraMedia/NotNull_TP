package clases;

import java.util.Iterator;

public class Admin {
	
	private static String msgBienvenida(String nombre) {
		return "Bienvenido " + nombre + "\n" + "Tenemos algunas ofertas para ti";
	}

	public static void main(String[] args) {
	
		
		Sistema s1 = new Sistema();
		s1.cargaUsuarios("ListaDeUsuarios");
		s1.cargaAtracciones("ListaDeAtracciones");
		s1.cargaPromociones("ListaDePromociones");
		
		Iterator<Usuario> usuarioIterator = s1.getUsuarios().iterator();
		Iterator<Atraccion> atraccionIterator = s1.getAtracciones().iterator();
		Iterator<Promocion> promocionIterator = s1.getPromociones().iterator();
		
		while (usuarioIterator.hasNext()) {
			Usuario usuActual = (Usuario) usuarioIterator.next();
			System.out.println(msgBienvenida(usuActual.getNombre()));
			
			
		}
		
		
		
		while (usuarioIterator.hasNext()) {
			System.out.println(usuarioIterator.next().toString());
		}
		
		while (atraccionIterator.hasNext()) {
			System.out.println(atraccionIterator.next().toString());
		}
		
		while (promocionIterator.hasNext()) {
			System.out.println(promocionIterator.next().toString());;
			
		}

	}

}
