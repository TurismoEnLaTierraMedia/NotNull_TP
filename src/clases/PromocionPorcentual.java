package clases;

public class PromocionPorcentual extends Promocion {
	private int valorDesc;

	public PromocionPorcentual(Tipo_De_Atraccion tipo, String pack, int valorDesc, Atraccion[] atraccion) {
		super(tipo, pack, atraccion);
		this.valorDesc = valorDesc;
	}

	public PromocionPorcentual(Integer tamanio) {
		super(tamanio);
	}

	public int getValorDesc() {
		return this.valorDesc;
	}

	public double obtenerMontoDescontado() {
		return super.getCostoParcial(getAtracciones()) * (getValorDesc() / 100);
	}

	public double obtenerPrecioFinal() {
		return super.getCostoParcial(getAtracciones()) - obtenerMontoDescontado();
	}

	@Override
	public String toString() {
		return "¡Buenas noticias!." + "Con la compra del pack: " + pack + " Recibiste un descuento porcentual de"
				+ this.obtenerMontoDescontado() + "el costo final por tu compra es de " + this.obtenerPrecioFinal();
	}

}
