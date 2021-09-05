package clases;

import java.util.ArrayList;

public abstract class Promocion {

	
	protected ArrayList<Atraccion> atraccion;

	public Promocion(ArrayList<Atraccion> atraccion) {
		this.atraccion = atraccion;
	}

	public ArrayList<Atraccion> getAtracciones() {
		return this.atraccion;
	}

	public double getCostoParcial(ArrayList<Atraccion> atraccion) {
		double costoTotal = 0;
		for (Atraccion atracciones : atraccion) {
			costoTotal += atracciones.getCostoDeVisita();
		}
		return costoTotal;
	}

}
