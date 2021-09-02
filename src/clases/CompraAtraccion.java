package clases;

public class CompraAtraccion extends InformeCompra {
	private Atraccion atraccion;

	public CompraAtraccion() {
		super();
		this.atraccion = null;
	}

	public CompraAtraccion(Atraccion atraccion, Usuario usuario) {
		super(usuario);
		this.atraccion = atraccion;
	}

	public Atraccion getAtraccion() {
		return atraccion;
	}

	public void setAtraccion(Atraccion atraccion) {
		this.atraccion = atraccion;
	}

	@Override
	public String toString() {
		return "CompraAtraccion [atraccion=" + atraccion + "]";
	}
}
