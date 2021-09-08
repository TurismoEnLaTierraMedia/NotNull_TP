package clases;

public class PromocionAxB extends Promocion {
	private Atraccion atraccionGratis;

	public PromocionAxB(String tipo, String pack, Atraccion atraccGratis, Atraccion[] atraccion) {
		super(tipo, pack, atraccion);
		this.atraccionGratis = atraccGratis;
	}

	public PromocionAxB(String tipo, String pack, Atraccion atraccGratis, Integer tamanio) {
		super(tipo, pack, tamanio);
		this.atraccionGratis = atraccGratis;
	}

	public Atraccion getAtraccionGratis() {
		return this.atraccionGratis;
	}
	
	public String toStringArray () {
		String atracciones = "";
		for (Atraccion atracc : atraccion) {
			atracciones += atracc.getNombre() + ", ";
		}
		return atracciones;
	}

	
	
	@Override
	public String toString() {
		return "¡En horabuena!, por la compra de las atracciones: " + toStringArray() + "del pack" + pack
				+ " usted recibe gratis la atracción: " + atraccionGratis;
	}

}
