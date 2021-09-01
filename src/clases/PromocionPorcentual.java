package clases;

public class PromocionPorcentual extends Promocion {
	private int porcenDesc;

	public PromocionPorcentual(int porcenDesc) {
		this.porcenDesc = porcenDesc;
	}

	public int getPorcenDesc() {
		return this.porcenDesc;
	}

	public double obtenerMontoDescontado() {
		return super.getCostoParcial (getAtracciones()) * (getPorcenDesc() / 100);
	}

	public double obtenerPrecioFinal() {
		return super.getCostoParcial (getAtracciones()) - obtenerMontoDescontado();
	}

}
