package clases;


public class PromocionPorcentual extends Promocion {
	private int porcenDesc;

	public PromocionPorcentual(int porcenDesc, Atraccion [] atraccion) {
		super(atraccion);
		this.porcenDesc = porcenDesc;
	}
	
	public PromocionPorcentual(int porcenDesc, int tamanio) {
		super(tamanio);
		this.porcenDesc = porcenDesc;
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
