package clases;

import java.util.ArrayList;
import java.util.Iterator;

public class PromocionAxB extends Promocion {

	// Atributos
	private Atraccion atraccionGratis;

	// Constructores
	public PromocionAxB(String tipo, String pack, Atraccion atraccGratis, ArrayList<Atraccion> atraccion) {
		super(tipo, pack, atraccion);
		this.atraccionGratis = atraccGratis;
	}

	public PromocionAxB(String tipo, String pack, Atraccion atraccGratis) {
		super(tipo, pack);
		this.atraccionGratis = atraccGratis;
		this.atraccion.add(atraccGratis);
	}

	public Atraccion getAtraccionGratis() {
		return this.atraccionGratis;
	}

	/**
	 * Método toString para mostrar por consola la lista de atracciones compradas
	 * por la cual se obtiene una atracción gratis.
	 * 
	 * @return
	 */

	public String toStringArray() {
		String atracciones = "";
		Iterator<Atraccion> atraccionesIterator = this.atraccion.iterator();
		while (atraccionesIterator.hasNext()) {
			Atraccion atraccion = atraccionesIterator.next();
			atracciones += atraccion.getNombre() + ", ";
		}
		return atracciones;
	}

	@Override
	public int obtenerPrecioFinal() {
		int result = super.obtenerPrecioFinal();
		return result - this.atraccionGratis.getCostoDeVisita();
	}

	@Override
	public String toString() {
		return "¡En horabuena!, por la compra de las atracciones: " + toStringArray() + " del pack" + pack
				+ " usted recibe gratis la atracción: " + atraccionGratis;
	}

}
