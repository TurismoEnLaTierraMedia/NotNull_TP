package clases;

import java.util.Objects;

public class Atraccion {
	private String nombre;
	private int costoDeVisita;
	private double duracion;
	private int cupo;
	private Tipo_De_Atraccion tipo;

	public Atraccion(String nombre, int costoDeVisita, double duracion, int cupo, String tipo) {
		this.nombre = nombre;
		this.costoDeVisita = costoDeVisita;
		this.duracion = duracion;
		this.cupo = cupo;
		this.setTipo(tipo);
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

	public void setTipo(String tipo) {
		try {
			this.tipo = Tipo_De_Atraccion.valueOf(tipo.toUpperCase().replace("Ó", "O"));

		} catch (IllegalArgumentException ex) {
			System.out.println(tipo + " no corresponde con un tipo de atracción");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(costoDeVisita, cupo, duracion, nombre, tipo);
	}

	public boolean tieneCupo() {
		return this.cupo > 0;
	}
	
	public void reducirCupo() {
		this.cupo--;
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
