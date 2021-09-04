package clases;

public class PromocionAbsoluta extends Promocion {

	private int valorDesc;

	public PromocionAbsoluta(int valorDesc, Atraccion[] atraccion) {
		super(atraccion);
		this.valorDesc = valorDesc;
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

		return "�Buenas noticias! Usted ha recibido un descuento de " + valorDesc;

	}

}
