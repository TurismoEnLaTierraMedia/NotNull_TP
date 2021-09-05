package clases;
public class PromocionAbsoluta extends Promocion {

	private int valorDesc;
	String nombrePromocion;

	public PromocionAbsoluta(Atraccion [] atraccion) {
		super(atraccion);
	}
	
	public PromocionAbsoluta(int valorDesc, int tamanio) {
		super(tamanio);
		this.valorDesc = valorDesc;
	}

	public int getValorDesc() {
		return this.valorDesc;
	}

	public double obtenerPrecioFinal(int i) {
		return super.getCostoParcial(getAtracciones()) - getValorDesc();
	}

	@Override
	public String toString() {

		return "¡Buenas noticias!. Por la compra de " + nombrePromocion + "Usted ha recibido un descuento de " + valorDesc
				+ ". El costo final de su recorrido es de: " + this.obtenerPrecioFinal(valorDesc);
	}

}
