package clases;

import java.util.Iterator;

public class PromocionAxB extends Promocion {

	// Atributos

	private Atraccion atraccionGratis;

	// Constructores
	
	public PromocionAxB(int id, String tipo, String pack, Atraccion atraccGratis) {
		super(id, tipo, pack);
		setAtraccion(atraccGratis);
		this.atraccion.add(atraccGratis);
	}

	public PromocionAxB(String tipo, String pack, Atraccion atraccGratis) {
		super(tipo, pack);
		setAtraccion(atraccGratis);
		this.atraccion.add(atraccGratis);
	}

	// Setters

	private void setAtraccion(Atraccion atraccGratis) {
		if (atraccGratis == null)
			throw new Error("El sistema debe recibir una atracción gratuita para validar la promoción");
		this.atraccionGratis = atraccGratis;
	}

	// Getters

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
		return "Por la compra de las atracciones: " + toStringArray() + " del pack " + pack
				+ ". Usted recibe gratis la atracción: " + atraccionGratis.getNombre();
	}

}
