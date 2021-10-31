package clases;

public class PromocionPorcentual extends Promocion {

	// Atributos

	private int valorDesc;

	// Constructores
	
	public PromocionPorcentual(int id, String tipo, String pack, int valorDesc) {
		super(id, tipo, pack);
		setValorDesc(valorDesc);
	}

	public PromocionPorcentual(String tipo, String pack, int valorDesc) {
		super(tipo, pack);
		setValorDesc(valorDesc);
	}

	// Setters

	private void setValorDesc(int valorDesc) {
		if (valorDesc <= 0)
			throw new Error("El sistema debe recibir un valor de descuento positivo para validar la promoción");
		this.valorDesc = valorDesc;
	}

	// Getters

	public int getValorDesc() {
		return this.valorDesc;
	}

	// Métodos

	public int obtenerMontoDescontado() {
		return (int) (super.obtenerPrecioFinal() * (double) getValorDesc() / 100);
	}

	public int obtenerPrecioFinal() {
		int result = super.obtenerPrecioFinal();
		return result - obtenerMontoDescontado();
	}

	@Override
	public String toString() {
		return "Con la compra del pack: " + pack + ". Usted recibe un descuento del " + this.getValorDesc()
				+ "%, el costo final por las atracciones incluídas pasa a ser: " + this.obtenerPrecioFinal()
				+ " monedas";
	}

}
