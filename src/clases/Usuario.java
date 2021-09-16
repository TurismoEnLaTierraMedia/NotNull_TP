package clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Usuario {

	private ArrayList<Atraccion> comprasAtracciones = new ArrayList<>();
	private ArrayList<Promocion> comprasPromociones = new ArrayList<>();

	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private Tipo_De_Atraccion preferencia;

	public Usuario(String nombre, int presupuesto, double tiempoDisponible, String preferencia) {
		this.nombre = nombre;

		this.setPresupuesto(presupuesto);
		this.setTiempoDisponible(tiempoDisponible);
		this.setPreferencia(preferencia);

		
	}

	public String getNombre() {
		return nombre;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	private void setPresupuesto(int presupuesto) {
		if (presupuesto <= 0) {
			throw new IllegalArgumentException("No se permiten números negativos");
		} else {
			this.presupuesto = presupuesto;
		}
	}

	public Tipo_De_Atraccion getPreferencia() {
		return this.preferencia;
	}

	private void setPreferencia(String preferencia) {
		try {
			this.preferencia = Tipo_De_Atraccion.valueOf(preferencia.toUpperCase().replace("Ó", "O"));

		} catch (IllegalArgumentException ex) {
			System.out.println(preferencia + " no corresponde con un tipo de atracción");
		}
	}

	/**
	 * Método que revisa las promociones adquiridas por el usuario, para no ofrecer
	 * atracciones que ya se encuentren en las mismas.
	 * 
	 * @param atraccion
	 * @return boolean
	 */

	public boolean chequearAtraccionEnPromociones(Atraccion atraccion) {
		Iterator<Promocion> promociones = this.getComprasPromociones().iterator();
		while (promociones.hasNext()) {
			Promocion promocion = (Promocion) promociones.next();

			ArrayList<Atraccion> atraccionesDePromociones = promocion.getAtracciones();
//			for (int i = 0; i < atraccionesDePromociones.length; i++) {
//				if (atraccion.equals(atraccionesDePromociones[i])) {
//					return true;
//				}
//			}
			for (int i = 0; i < atraccionesDePromociones.size(); i++) {
				if (atraccion.equals(atraccionesDePromociones.get(i))) {
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * Añade una Atracción a la lista de atraciones compradas por el usuario, y
	 * modifica los atributos correspondientes (presupuesto y tiempo disponible)
	 * 
	 * @param atraccion
	 */

	public void comprarAtraccion(Atraccion atraccion) {
		comprasAtracciones.add(atraccion);
		this.presupuesto -= atraccion.getCostoDeVisita();
		this.tiempoDisponible -= atraccion.getDuracion();
	}

	/**
	 * Añade una Promoción a la lista de promociones compradas por el usuario y
	 * modifica los atributos correspondientes (presupuesto y tiempo disponible)
	 * 
	 * @param promocion
	 */

	public void comprarPomocion(Promocion promocion) {
		comprasPromociones.add(promocion);
		this.presupuesto -= promocion.obtenerPrecioFinal();
		this.tiempoDisponible -= promocion.getTiempoTotal();
	}

	public ArrayList<Atraccion> getComprasAtracciones() {
		return comprasAtracciones;
	}

	public ArrayList<Promocion> getComprasPromociones() {
		return comprasPromociones;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	private void setTiempoDisponible(double tiempoDisponible) {
		if (tiempoDisponible <= 0) {
			throw new IllegalArgumentException("No se permiten números negativos");
		} else {
			this.tiempoDisponible = tiempoDisponible;
		}

	}

	/**
	 * Guarda las compras realizadas por el usuario para incluirlas en el archivo
	 * final.
	 * 
	 * @return miItinerario
	 */

	public Itinerario generarItinerario() {
		Itinerario miItinerario = new Itinerario(this, this.getComprasPromociones(), this.getComprasAtracciones());
		return miItinerario;
	}

	public boolean comproAlgo() {
		return !this.getComprasAtracciones().isEmpty() || !this.getComprasPromociones().isEmpty();
	}

	@Override
	public String toString() {
		return "Usuario: " + nombre + "| Monedero: " + presupuesto + "| Tiempo Disponible: " + tiempoDisponible
				+ "| Preferencia: " + preferencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, preferencia, presupuesto, tiempoDisponible);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre) && preferencia == other.preferencia
				&& presupuesto == other.presupuesto
				&& Double.doubleToLongBits(tiempoDisponible) == Double.doubleToLongBits(other.tiempoDisponible);
	}

}
