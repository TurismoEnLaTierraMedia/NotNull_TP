package clases;

import java.util.ArrayList;
import java.util.Iterator;

import excepciones.NoEsMismoTipoException;
import excepciones.NoHayMasCupoException;

public abstract class Promocion {

	protected ArrayList<Atraccion> atraccion;
	protected Tipo_De_Atraccion tipo;
	protected int indice;

	protected String pack;

	public Promocion(String tipo, String pack, ArrayList<Atraccion> atraccion) {
		this.atraccion = atraccion;
		this.pack = pack;
		this.tipo = Tipo_De_Atraccion.valueOf(tipo);
	}

	public Promocion(String pack, ArrayList<Atraccion> atraccion) {
		this.atraccion = atraccion;
		this.pack = pack;
	}

	public Promocion(String tipo, String pack) {
		this.atraccion = new ArrayList<Atraccion>();
		this.tipo = Tipo_De_Atraccion.valueOf(tipo);
		this.pack = pack;
	}

	public Tipo_De_Atraccion getTipo() {
		return tipo;
	}

	public double getTiempoTotal() {
		double tiempoTotal = 0;
		Iterator<Atraccion> atraccionesIterator = this.atraccion.iterator();
		while (atraccionesIterator.hasNext()) {
			Atraccion atraccion = atraccionesIterator.next();
			tiempoTotal += atraccion.getDuracion();
		}
		return tiempoTotal;
	}

	public void anadirAtraccion(Atraccion atraccion) throws NoEsMismoTipoException, NoHayMasCupoException {
		if (atraccion.getTipo() != this.tipo)
			throw new NoEsMismoTipoException("¡Las atracciones deben ser del mismo tipo!");
		if (atraccion.tieneCupo() == false)
			throw new NoHayMasCupoException("La atraccion no posee cupo disponible");
		this.atraccion.add(atraccion);
		indice++;
	}

	public ArrayList<Atraccion> getAtracciones() {
		return this.atraccion;
	}

	public int getCostoParcial() {
		int costoTotal = 0;
		Iterator<Atraccion> atraccionesIterator = this.atraccion.iterator();
		while (atraccionesIterator.hasNext()) {
			Atraccion atraccion = atraccionesIterator.next();
			costoTotal += atraccion.getDuracion();
		}
		return costoTotal;
	}

}
