package clases;

public class Usuario {

	private String nombre;
	private double presupuesto;
	private int tiempoDisponible;
	private String tipoPreferido;

	public Usuario(String nombre, double presupuesto, int tiempoDisponible, String tipoPreferido) {
		super();
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoPreferido = tipoPreferido;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public int getTiempoDisponible() {
		return tiempoDisponible;
	}

	public String getTipoPreferido() {
		return tipoPreferido;
	}
}
