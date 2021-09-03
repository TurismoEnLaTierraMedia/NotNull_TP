package clases;

import java.util.Iterator;

public class Prueba {

	public static void main(String[] args) {
		Sistema s1 = new Sistema();
		s1.cargaUsuarios("ListaDeUsuarios");
		s1.cargaAtracciones("ListaDeAtracciones");
		
		Iterator<Usuario> usuarioIterator = s1.getUsuarios().iterator();
		Iterator<Atraccion> atraccionIterator = s1.getAtracciones().iterator();
		
		while (usuarioIterator.hasNext()) {
			System.out.println(usuarioIterator.next().toString());
		}
		
		while (atraccionIterator.hasNext()) {
			System.out.println(atraccionIterator.next().toString());
		}

	}

}
