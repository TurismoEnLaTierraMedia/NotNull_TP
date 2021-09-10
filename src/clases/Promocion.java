package clases;

import excepciones.NoEsMismoTipoException;
import excepciones.NoHayMasCupoException;

public abstract class Promocion {

	protected Atraccion[] atraccion;
	protected Tipo_De_Atraccion tipo;
	protected int indice;

	String pack;

	public Promocion(String tipo, String pack, Atraccion[] atraccion) {
		this.atraccion = atraccion;
		this.pack = pack;
		this.tipo = Tipo_De_Atraccion.valueOf(tipo);
	}

	public Promocion(String pack, Atraccion[] atraccion) {
		this.atraccion = atraccion;
		this.indice = 0;
		this.pack = pack;
	}

	public Promocion(String tipo, String pack, Integer tamanio) {
		this.atraccion = new Atraccion[tamanio];
		this.indice = 0;
		this.tipo = Tipo_De_Atraccion.valueOf(tipo);
		this.pack = pack;
	}

	public Tipo_De_Atraccion getTipo() {
		return tipo;
	}

	public double getTiempoTotal() {
		double tiempoTotal = 0;
		for (Atraccion atracc : this.atraccion) {
			tiempoTotal += atracc.getDuracion();
		}
		return tiempoTotal;
	}

	public void anadirAtraccion(Atraccion atraccion) throws NoEsMismoTipoException, NoHayMasCupoException {
		if (atraccion.getTipo() != this.tipo)
			throw new NoEsMismoTipoException("¡Las atracciones deben ser del mismo tipo!");
		if (atraccion.tieneCupo() == false)
			throw new NoHayMasCupoException("La atraccion no posee cupo disponible");
		this.atraccion[indice] = atraccion;
		indice++;
	}

	public Atraccion[] getAtracciones() {
		return this.atraccion;
	}

	public int getCostoParcial() {
		int costoTotal = 0;
		for (Atraccion atracc : this.atraccion) {
			costoTotal += atracc.getCostoDeVisita();
		}
		return costoTotal;
	}

}
