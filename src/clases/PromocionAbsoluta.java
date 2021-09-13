package clases;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {

	private int valorDesc;

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

		return "¡Buenas noticias!. Por la compra del paquete: " + pack + " Usted ha recibido un descuento de "
				+ valorDesc + ". El costo final de su recorrido es de: " + this.obtenerPrecioFinal();
	}

}
