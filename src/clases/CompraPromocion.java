package clases;

public class CompraPromocion extends InformeCompra {
	private Promocion promocion;

	public CompraPromocion() {
		super();
		this.promocion = null;
	}

	public CompraPromocion(Promocion promocion, Usuario usuario) {
		super(usuario);
		this.promocion = promocion;
	}

	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

	@Override
	public String toString() {
		return "CompraPromocion [promocion=" + promocion + "]";
	}

}
