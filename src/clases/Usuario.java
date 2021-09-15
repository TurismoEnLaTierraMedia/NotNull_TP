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
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.preferencia = Tipo_De_Atraccion.valueOf(preferencia.toUpperCase().replace("Ó", "O"));
	}

	public String getNombre() {
		return nombre;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	
	public Tipo_De_Atraccion getPreferencia() {
		return preferencia;
	}
	
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
	
	public void comprarAtraccion(Atraccion atraccion) {
		comprasAtracciones.add(atraccion);
		this.presupuesto -= atraccion.getCostoDeVisita();
		this.tiempoDisponible -= atraccion.getDuracion();	
	}
	
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
