package clases;

public class PromocionAbsoluta extends Promocion {

	private int valorDesc;

	public PromocionAbsoluta(String tipo, String pack, int valorDesc, Integer tamanio) {
		super(tipo, pack, tamanio);
		this.valorDesc = valorDesc;
	}

	public PromocionAbsoluta(String tipo, String pack, int valorDesc, Atraccion[] atraccion) {
		super(tipo, pack, atraccion);
		this.valorDesc = valorDesc;
	}

	public double getValorDesc() {
		return this.valorDesc;
	}

	public double obtenerPrecioFinal() {
		return super.getCostoParcial(getAtracciones()) - getValorDesc();
	}

	@Override
	public String toString() {

		return "¡Buenas noticias!. Por la compra del paquete: " + pack + " Usted ha recibido un descuento de "
				+ valorDesc + ". El costo final de su recorrido es de: " + this.obtenerPrecioFinal();
	}

}
