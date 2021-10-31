package clases;

public class PromocionAbsoluta extends Promocion {

	// Atributos

	private int valorDesc;

	// Constructores
	
	public PromocionAbsoluta(int id, String tipo, String pack, int valorDesc) {
		super(id, tipo, pack);
		setValorDesc(valorDesc);
	}

	public PromocionAbsoluta(String tipo, String pack, int valorDesc) {
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

	@Override
	public int obtenerPrecioFinal() {
		int result = super.obtenerPrecioFinal();
		return result - this.valorDesc;
	}

	@Override
	public String toString() {

		return " Por la compra del paquete: " + pack + ". Usted recibe un descuento de: " + valorDesc
				+ " monedas. El costo final por las atracciones incluídas pasa a ser de " + this.obtenerPrecioFinal()
				+ " monedas";
	}

}
