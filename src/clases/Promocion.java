package clases;

public abstract class Promocion {

	protected Atraccion[] atraccion;
	protected Tipo_De_Atraccion tipo;
	protected int indice;

	String pack;

	public Promocion(Tipo_De_Atraccion tipo, String pack, Atraccion[] atraccion) {
		this.atraccion = atraccion;
		this.pack = pack;
		this.tipo = tipo;
	}

	public Promocion(String pack, Atraccion[] atraccion) {
		this.atraccion = atraccion;
		this.indice = 0;
		this.pack = pack;
	}

	public Promocion(Integer tamanio) {
		this.atraccion = new Atraccion[tamanio];
		this.indice = 0;
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

	public double getCostoParcial(Atraccion[] atraccion) {
		double costoTotal = 0;
		for (Atraccion atracc : atraccion) {
			costoTotal += atracc.getCostoDeVisita();
		}
		return costoTotal;
	}

}
