package clases;

public class PromocionAxB extends Promocion {
	private Atraccion atraccionGratis;

	public PromocionAxB(Atraccion atraccionGratis, Atraccion[] atraccion) {
		super(atraccion);
		this.atraccionGratis = atraccionGratis;
	}

	public Atraccion getAtraccionGratis() {
		return this.atraccionGratis;
	}

}
