package clases;

import java.util.List;
import java.util.Objects;

public class Itinerario {
	private double totalPagar, tiempoNecesario;
	private Usuario usuario;
	List<Promocion> promocionesCompradas;
	List<Atraccion> atraccionesCompradas;

	public Itinerario(double totalPagar, double tiempoNecesario, Usuario usuario, List<Promocion> promocionesCompradas,
			List<Atraccion> atraccionesCompradas) {
		this.totalPagar = totalPagar;
		this.tiempoNecesario = tiempoNecesario;
		this.usuario = usuario;
		this.promocionesCompradas = promocionesCompradas;
		this.atraccionesCompradas = atraccionesCompradas;
	}

	public Itinerario() {
		this.totalPagar = 0;
		this.tiempoNecesario = 0;
		this.usuario = null;
		this.promocionesCompradas = null;
		this.atraccionesCompradas = null;
	}

	protected double getTotalPagar() {
		return totalPagar;
	}

	protected void setTotalPagar(double totalPagar) {
		this.totalPagar = totalPagar;
	}

	protected double getTiempoNecesario() {
		return tiempoNecesario;
	}

	protected void setTiempoNecesario(double tiempoNecesario) {
		this.tiempoNecesario = tiempoNecesario;
	}

	protected Usuario getUsuario() {
		return usuario;
	}

	protected void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	protected List<Promocion> getPromocionesCompradas() {
		return promocionesCompradas;
	}

	protected void setPromocionesCompradas(List<Promocion> promocionesCompradas) {
		this.promocionesCompradas = promocionesCompradas;
	}

	protected List<Atraccion> getAtraccionesCompradas() {
		return atraccionesCompradas;
	}

	protected void setAtraccionesCompradas(List<Atraccion> atraccionesCompradas) {
		this.atraccionesCompradas = atraccionesCompradas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itinerario other = (Itinerario) obj;
		return Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Itinerario [totalPagar=" + totalPagar + ", tiempoNecesario=" + tiempoNecesario + ", usuario=" + usuario
				+ ", promocionesCompradas=" + promocionesCompradas + ", atraccionesCompradas=" + atraccionesCompradas
				+ "]";
	}

}
