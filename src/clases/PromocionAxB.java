package clases;

public class PromocionAxB extends Promocion {
	private Atraccion atraccionGratis;

	public PromocionAxB(Tipo_De_Atraccion tipo, String pack, Atraccion atraccGratis, Atraccion[] atraccion) {
		super(tipo, pack, atraccion);
		this.atraccionGratis = atraccGratis;
	}

	public PromocionAxB(Integer tamanio) {
		super(tamanio);
	}

	public Atraccion getAtraccionGratis() {
		return this.atraccionGratis;
	}

	@Override
	public String toString() {
		return "¡En horabuena!, por la compra de las atracciones: " + atraccion + "del pack" + pack
				+ " usted recibe gratis la atracción: " + atraccionGratis;
	}

}
