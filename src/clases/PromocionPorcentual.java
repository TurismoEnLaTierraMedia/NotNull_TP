package clases;

import java.util.ArrayList;

public class PromocionPorcentual extends Promocion {
	private int valorDesc;

	public PromocionPorcentual(String tipo, String pack, int valorDesc, ArrayList<Atraccion> atraccion) {
		super(tipo, pack, atraccion);
		this.valorDesc = valorDesc;
	}

	public PromocionPorcentual(String tipo, String pack, int valorDesc) {
		super(tipo, pack);
		this.valorDesc = valorDesc;
	}

	public int getValorDesc() {
		return this.valorDesc;
	}

	public int obtenerMontoDescontado() {
		return super.getCostoParcial() * (getValorDesc() / 100);
	}

	public int obtenerPrecioFinal() {
		return super.getCostoParcial() - obtenerMontoDescontado();
	}

	@Override
	public String toString() {
		return "¡Buenas noticias!." + "Con la compra del pack: " + pack + " Recibiste un descuento porcentual de"
				+ this.obtenerMontoDescontado() + "el costo final por tu compra es de " + this.obtenerPrecioFinal();
	}

}
