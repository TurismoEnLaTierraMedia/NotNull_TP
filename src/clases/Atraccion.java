package clases;

import java.util.Objects;

public class Atraccion {
	private String nombre;
	private int costoDeVisita;
	private double duracion;
	private int cupo;
	private Tipo_De_Atraccion tipo;

	public Atraccion(String nombre, int costoDeVisita, double duracion, int cupo, Tipo_De_Atraccion tipo) {
		this.nombre = nombre;
		this.costoDeVisita = costoDeVisita;
		this.duracion = duracion;
		this.cupo = cupo;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCostoDeVisita() {
		return costoDeVisita;
	}

	public void setCostoDeVisita(int costoDeVisita) {
		this.costoDeVisita = costoDeVisita;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public Tipo_De_Atraccion getTipo() {
		return tipo;
	}

	public void setTipo(Tipo_De_Atraccion tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(costoDeVisita, cupo, duracion, nombre, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return costoDeVisita == other.costoDeVisita && cupo == other.cupo
				&& Double.doubleToLongBits(duracion) == Double.doubleToLongBits(other.duracion)
				&& Objects.equals(nombre, other.nombre) && tipo == other.tipo;
	}

	@Override
	public String toString() {
		return "Atraccion " + nombre + ": Costo de visita: " + costoDeVisita + ", duracion: " + duracion + ", cupo: "
				+ cupo + ", tipo: " + tipo;
	}

}
