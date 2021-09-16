package clases;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Itinerario {
	private double tiempoNecesario;
	private int totalPagar;
	private Usuario usuario;
	private List<Promocion> promocionesCompradas;
	private List<Atraccion> atraccionesCompradas;

	public Itinerario(Usuario usuario, List<Promocion> promocionesCompradas,
			List<Atraccion> atraccionesCompradas) {
		this.usuario = usuario;
		this.promocionesCompradas = promocionesCompradas;
		this.atraccionesCompradas = atraccionesCompradas;
		setTotalPagar();
		setTiempoNecesario(tiempoNecesario);
	}

	protected int getTotalPagar() {
		return totalPagar;
	}

	private void setTotalPagar() {
		this.totalPagar = 0;
		for (Iterator<Atraccion> iterator = atraccionesCompradas.iterator(); iterator.hasNext();) {
			Atraccion atraccion = (Atraccion) iterator.next();
			this.totalPagar += atraccion.getCostoDeVisita();
		}
		for (Iterator<Promocion> iterator = promocionesCompradas.iterator(); iterator.hasNext();) {
			Promocion promocion = (Promocion) iterator.next();
			this.totalPagar += promocion.obtenerPrecioFinal();
		}

	}

	protected double getTiempoNecesario() {
		return tiempoNecesario;
	}

	private void setTiempoNecesario(double tiempoNecesario) {
		this.tiempoNecesario = 0;
		for (Iterator<Atraccion> iterator = atraccionesCompradas.iterator(); iterator.hasNext();) {
			Atraccion atraccion = (Atraccion) iterator.next();
			this.tiempoNecesario += atraccion.getDuracion();
		}
		for (Iterator<Promocion> iterator = promocionesCompradas.iterator(); iterator.hasNext();) {
			Promocion promocion = (Promocion) iterator.next();
			this.tiempoNecesario += promocion.getTiempoTotal();
		}
	}

	protected Usuario getUsuario() {
		return usuario;
	}

	protected List<Promocion> getPromocionesCompradas() {
		return promocionesCompradas;
	}

	protected List<Atraccion> getAtraccionesCompradas() {
		return atraccionesCompradas;
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
		return "Itinerario --> Total a pagar: " + totalPagar + ", Tiempo necesario: "
				+ tiempoNecesario + ", Usuario: " + usuario + ", Promociones compradas: "
				+ promocionesCompradas + ", Atracciones Compradas" + atraccionesCompradas;
	}

}
