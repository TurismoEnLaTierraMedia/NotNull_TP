package clases;

public class PromocionAxB extends Promocion {
	private Atraccion atraccionGratis;

	public PromocionAxB(Atraccion atraccionGratis, Atraccion [] atraccion) {
		super(atraccion);
		this.atraccionGratis = atraccionGratis;
	}
	
	public PromocionAxB(Atraccion atraccionGratis, int tamanio) {
		super(tamanio);
		this.atraccionGratis = atraccionGratis;
	}

	public Atraccion getAtraccionGratis() {
		return this.atraccionGratis;
	}

	@Override
	public String toString() {
		return "¡En horabuena!, por la compra del paquete usted recibe gratis la atracción: " + atraccionGratis;
	}

}
