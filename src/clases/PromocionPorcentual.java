package clases;

import java.util.ArrayList;

public class PromocionPorcentual extends Promocion {

	// Atributos
	private int valorDesc;

	// Constructores
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
		return super.obtenerPrecioFinal() * (getValorDesc() / 100);
	}

	public int obtenerPrecioFinal() {
		int result = super.obtenerPrecioFinal();
		return result - obtenerMontoDescontado();
	}

	@Override
	public String toString() {
		return "Con la compra del pack: " + pack + ". Usted recibe un descuento porcentual de: "
				+ this.obtenerMontoDescontado() + " el costo final pasa a ser: " + this.obtenerPrecioFinal();
	}

}
