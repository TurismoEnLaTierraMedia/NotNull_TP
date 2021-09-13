package clases;

import java.util.ArrayList;
import java.util.Iterator;

import excepciones.NoEsMismoTipoException;
import excepciones.NoHayMasCupoException;

public abstract class Promocion {

	// Atributos
	protected ArrayList<Atraccion> atraccion;
	protected Tipo_De_Atraccion tipo;
	protected String pack;

	// Constructores
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

	// Getters

	public Tipo_De_Atraccion getTipo() {
		return tipo;
	}

	public String getNombre() {
		return this.pack;
	}

	/**
	 * Método que retorna una variable de tipo double que represtenta la suma de las
	 * duraciones de las atracciones contenidas en la lista.
	 * 
	 * @return double
	 */

	public double getTiempoTotal() {
		double tiempoTotal = 0;
		Iterator<Atraccion> atraccionesIterator = this.atraccion.iterator();
		while (atraccionesIterator.hasNext()) {
			Atraccion atraccion = atraccionesIterator.next();
			tiempoTotal += atraccion.getDuracion();
		}
		return tiempoTotal;
	}

	/**
	 * Método que permite agregar atracciones a la lista. Prevee excepciones para
	 * evitar la carga de atracciones de distinto tipo, o para las que no haya cupo
	 * disponible.
	 * 
	 * @param atraccion
	 * @throws NoEsMismoTipoException
	 * @throws NoHayMasCupoException
	 */

	public void anadirAtraccion(Atraccion atraccion) throws NoEsMismoTipoException, NoHayMasCupoException {
		if (atraccion.getTipo() != this.tipo)
			throw new NoEsMismoTipoException("¡Las atracciones deben ser del mismo tipo!");
		if (atraccion.tieneCupo() == false)
			throw new NoHayMasCupoException("La atraccion no posee cupo disponible");
		this.atraccion.add(atraccion);
	}

	public ArrayList<Atraccion> getAtracciones() {
		return this.atraccion;
	}

	/**
	 * Método que retorna una variable de tipo int, que representa la suma del costo
	 * de las atracciones contenidas. Las subclases lo sobreescriben modificando el
	 * valor final adecuado al descuento promocional especifico.
	 * 
	 * @return int
	 */

	public int obtenerPrecioFinal() {
		int costoTotal = 0;
		Iterator<Atraccion> atraccionesIterator = this.atraccion.iterator();
		while (atraccionesIterator.hasNext()) {
			Atraccion atraccion = atraccionesIterator.next();
			costoTotal += atraccion.getCostoDeVisita();
		}
		return costoTotal;
	}

}
