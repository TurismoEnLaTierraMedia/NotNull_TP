package clases;

public class Promocion {

	private Atraccion[] atraccion;

	public Atraccion [] getAtracciones(){
		return this.atraccion;
	}
	
	public double getCostoParcial( Atraccion [] atraccion) {
		double costoTotal = 0;
		for (Atraccion atracciones : atraccion) {
			costoTotal+= atracciones.getCosto();
		}
		return costoTotal;
	}

}
