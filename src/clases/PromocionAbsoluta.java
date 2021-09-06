package clases;

public class PromocionAbsoluta extends Promocion {

	public PromocionAbsoluta(Integer tamanio) {
		super(tamanio);
	}

	public PromocionAbsoluta(Tipo_De_Atraccion tipo, String pack, double valorDesc, Atraccion[] atraccion) {
		super(tipo, pack, valorDesc, atraccion);
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
