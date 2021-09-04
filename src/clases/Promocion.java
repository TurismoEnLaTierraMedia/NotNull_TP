package clases;

public abstract class Promocion {

	protected Atraccion[] atraccion;
	protected int indice;

	public Promocion(Atraccion[] atraccion) {
		this.atraccion = atraccion;
		this.indice = 0;
	}
	
	public Promocion(Integer tamanio) {
		this.atraccion = new Atraccion[tamanio];
		this.indice = 0;
	}
	
	public void anadirAtraccion(Atraccion atraccion) {
		this.atraccion[indice] = atraccion;
		indice++;
	}

	public Atraccion[] getAtracciones() {
		return this.atraccion;
	}

	public double getCostoParcial(Atraccion[] atraccion) {
		double costoTotal = 0;
		for (Atraccion atracciones : atraccion) {
			costoTotal += atracciones.getCostoDeVisita();
		}
		return costoTotal;
	}

}
