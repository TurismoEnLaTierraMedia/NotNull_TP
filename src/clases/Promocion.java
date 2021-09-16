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

	public Promocion(String tipo, String pack) {
		this.atraccion = new ArrayList<Atraccion>();
		setTipo(tipo);
		setPack(pack);
	}

	// Setters

	private void setTipo(String tipo) {
		try {
			// Convierte el String en mayúscula
			// Si degustación tiene tilde, la cambia. Es la única tilde que admite
			this.tipo = Tipo_De_Atraccion.valueOf(tipo.toUpperCase().replace("Ó", "O"));

		} catch (IllegalArgumentException ex) {
			System.out.println(tipo + " no corresponde con un tipo de atracción");
		}
	}

	private void setPack(String pack) {
		if (pack == "")
			throw new Error("El sistema debe recibir el nombre del pack para validar la promoción");
		this.pack = pack;
	}

	// Getters

	public Tipo_De_Atraccion getTipo() {
		return tipo;
	}

	public String getNombre() {
		return this.pack;
	}

	// Métodos

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

	public boolean hayCupo() {
		Iterator<Atraccion> atraccionesIterator = this.atraccion.iterator();
		while (atraccionesIterator.hasNext()) {
			Atraccion atraccion = atraccionesIterator.next();
			if (!atraccion.tieneCupo()) {
				return false;
			}

		}
		return true;
	}

	public void reducirCupo() {
		Iterator<Atraccion> atraccionesIterator = this.atraccion.iterator();
		while (atraccionesIterator.hasNext()) {
			Atraccion atraccion = atraccionesIterator.next();
			atraccion.reducirCupo();
		}
	}

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
