package clases;

public class InformeCompra {
	private Usuario usuario;

	public InformeCompra() {
		this(null);
	}

	public InformeCompra(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

//	No agregue equals porque no estaba implementado en usuario;
	@Override
	public String toString() {
		return "InformeCompra [usuario=" + usuario + "]";
	}
}
