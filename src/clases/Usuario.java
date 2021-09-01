package clases;

public class Usuario {

	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private Tipo_De_Atraccion preferencia;

	

	public Usuario(String nombre, int presupuesto, double tiempoDisponible, Tipo_De_Atraccion preferencia) {
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

	
	public Tipo_De_Atraccion getPreferencia() {
		return preferencia;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", presupuesto=" + presupuesto + ", tiempoDisponible=" + tiempoDisponible
				+ ", preferencia=" + preferencia + "]";
	}
	
}
