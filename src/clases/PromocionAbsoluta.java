package clases;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {

	// Atributos
	private int valorDesc;

	// Constructores
	public PromocionAbsoluta(String tipo, String pack, int valorDesc) {
		super(tipo, pack);
		this.valorDesc = valorDesc;
	}

	public PromocionAbsoluta(String tipo, String pack, int valorDesc, ArrayList<Atraccion> atraccion) {
		super(tipo, pack, atraccion);
		this.valorDesc = valorDesc;
	}

	public int getValorDesc() {
		return this.valorDesc;
	}

	@Override
	public int obtenerPrecioFinal() {
		int result = super.obtenerPrecioFinal();
		return result - this.valorDesc;
	}

	@Override
	public String toString() {

		return " Por la compra del paquete: " + pack + ". Usted recibe un descuento de: " + valorDesc
				+ " monedas. El costo final por las atracciones incluídas pasa a ser de " + this.obtenerPrecioFinal()
				+ " monedas";
	}

}
