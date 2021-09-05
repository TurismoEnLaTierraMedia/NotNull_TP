package clases;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {

	private int valorDesc;
	String nombrePromocion;

	public PromocionAbsoluta(ArrayList<Atraccion> atraccion) {
		super(atraccion);
	}

	public int getValorDesc() {
		return this.valorDesc;
	}

	public double obtenerPrecioFinal(int i) {
		return super.getCostoParcial(getAtracciones()) - getValorDesc();
	}

	public double bosqueNegroyMordor (ArrayList<Atraccion> atraccion) {
		if (atraccion.contains(Mordor) && atraccion.contains(BosqueNegro)) {
			valorDesc = 30;
			nombrePromocion = "Bosque Negro y Mordor";
		}
	}

	@Override
	public String toString() {

		return "¡Buenas noticias!. Por la compra de " + nombrePromocion + "Usted ha recibido un descuento de " + valorDesc
				+ ". El costo final de su recorrido es de: " + this.obtenerPrecioFinal(valorDesc);
	}

}
