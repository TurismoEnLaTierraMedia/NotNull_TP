package clases;

public class Promocion {

	protected Atraccion[] atraccion;

	public Promocion(Atraccion[] atraccion) {
		this.atraccion = atraccion;
	}

	public Atraccion [] getAtracciones(){
		return this.atraccion;
	}
	
	public double getCostoParcial( Atraccion [] atraccion) {
		double costoTotal = 0;
		for (Atraccion atracciones : atraccion) {
			costoTotal+= atracciones.getCostoDeVisita();
		}
		return costoTotal;
	}

}
