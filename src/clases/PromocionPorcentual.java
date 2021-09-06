package clases;

public class PromocionPorcentual extends Promocion {
	private int porcenDesc;

	public PromocionPorcentual(Tipo_De_Atraccion tipo, String pack, double valorDesc, Atraccion[] atraccion) {
		super(tipo, pack, valorDesc, atraccion);
	}

	public PromocionPorcentual(Integer tamanio) {
		super(tamanio);
	}

	public int getPorcenDesc() {
		return this.porcenDesc;
	}

	public double obtenerMontoDescontado() {
		return super.getCostoParcial(getAtracciones()) * (getPorcenDesc() / 100);
	}

	public double obtenerPrecioFinal() {
		return super.getCostoParcial(getAtracciones()) - obtenerMontoDescontado();
	}

	@Override
	public String toString() {
		return "¡Buenas noticias!. Recibiste un descuento porcentual de" + this.obtenerMontoDescontado()
				+ "el costo final por tu compra es de " + this.obtenerPrecioFinal();
	}

}
