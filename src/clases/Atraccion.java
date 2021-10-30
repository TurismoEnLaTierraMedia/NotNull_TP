package clases;

import java.util.Objects;

public class Atraccion {
	private int idBaseDeDatos;
	private String nombre;
	private int costoDeVisita;
	private double duracion;
	private int cupo;
	private Tipo_De_Atraccion tipo;
	
	public Atraccion(int idBaseDeDatos, String nombre, int costoDeVisita, double duracion, int cupo, String tipo) {
		this.idBaseDeDatos = idBaseDeDatos;
		this.setNombre(nombre);
		this.setCostoDeVisita(costoDeVisita);
		this.setDuracion(duracion);
		this.setCupo(cupo);
		this.setTipo(tipo);
	}

	public Atraccion(String nombre, int costoDeVisita, double duracion, int cupo, String tipo) {
		this.setNombre(nombre);
		this.setCostoDeVisita(costoDeVisita);
		this.setDuracion(duracion);
		this.setCupo(cupo);
		this.setTipo(tipo);
	}
	
	public int getID() {
		return this.idBaseDeDatos;
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

	public void setCostoDeVisita(int costoDeVisita) {
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

	public void setCupo(int cupo) {
		if (cupo < 0)
			throw new Error("El cupo no puede ser menor a 0");
		this.cupo = cupo;
	}

	public Tipo_De_Atraccion getTipo() {
		return tipo;
	}

	// Evalúa si el String pasado como parámetro es un valor de Tipo de atracción
	// Si no lo es arroja la excepción

	private void setTipo(String tipo) {
		try {
			// Convierte el String en mayúscula
			// Si degustación tiene tilde, la cambia. Es la única tilde que admite
			this.tipo = Tipo_De_Atraccion.valueOf(tipo.toUpperCase().replace("Ó", "O"));

		} catch (IllegalArgumentException ex) {
			System.out.println(tipo + " no corresponde con un tipo de atracción");
		}
	}

	// Retorna true si la atracción tiene cupo

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

	// Dos atracciones son iguales si el NOMBRE y el TIPO coinciden
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return Objects.equals(nombre, other.nombre) && tipo == other.tipo;
	}

	@Override
	public String toString() {
		return "Atraccion " + nombre + ": Costo de visita: " + costoDeVisita + ", duracion: " + duracion + ", cupo: "
				+ cupo + ", tipo: " + tipo;
	}

}
