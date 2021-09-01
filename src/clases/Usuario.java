package clases;

public class Usuario {

	private String nombre;
	private double presupuesto;
	private int tiempoDisponible;
	private Tipo_De_Atraccion preferencia;

	public Usuario(String nombre, double presupuesto, int tiempoDisponible, Tipo_De_Atraccion preferencia) {
		super();
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.preferencia = preferencia;
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

	public Tipo_De_Atraccion getPreferencia() {
		return preferencia;
	}
}
