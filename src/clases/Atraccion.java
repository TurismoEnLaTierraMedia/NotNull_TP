package clases;

import java.util.Objects;

public class Atraccion {
	private String nombre;
	private int costoDeVisita;
	private double duracion;
	private int cupo;
	private Tipo_De_Atraccion tipo;

	public Atraccion(String nombre, int costoDeVisita, double duracion, int cupo, String tipo) {
		this.setNombre(nombre);
		this.setCostoDeVisita(costoDeVisita);
		this.setDuracion(duracion);
		this.setCupo(cupo);
		this.setTipo(tipo);
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == "")
			throw new Error("La atracción debe tener nombre");
		this.nombre = nombre;
	}

	public int getCostoDeVisita() {
		return costoDeVisita;
	}

	private void setCostoDeVisita(int costoDeVisita) {
		if (costoDeVisita < 0)
			throw new Error("El costo no puede ser menor a 0");
		this.costoDeVisita = costoDeVisita;
	}

	public double getDuracion() {
		return duracion;
	}

	private void setDuracion(double duracion) {
		if (duracion <= 0)
			throw new Error("La duración debe ser mayor a 0");
		this.duracion = duracion;
	}

	public int getCupo() {
		return cupo;
	}

	private void setCupo(int cupo) {
		if (cupo < 0)
			throw new Error("El cupo no puede ser menor a 0");
		this.cupo = cupo;
	}

	public Tipo_De_Atraccion getTipo() {
		return tipo;
	}

	private void setTipo(String tipo) {
		try {
			this.tipo = Tipo_De_Atraccion.valueOf(tipo.toUpperCase().replace("Ó", "O"));

		} catch (IllegalArgumentException ex) {
			System.out.println(tipo + " no corresponde con un tipo de atracción");
		}
	}

	public boolean tieneCupo() {
		return this.cupo > 0;
	}

	public void reducirCupo() {
		this.cupo--;
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
