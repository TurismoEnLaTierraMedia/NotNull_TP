package clases;

import java.util.ArrayList;
import java.io.*;

public class Sistema {

	// Atributos
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private ArrayList<Atraccion> atracciones = new ArrayList<>();
	private ArrayList<Promocion> promociones = new ArrayList<>();
	private ArrayList<InformeCompra> informes = new ArrayList<>();

	// Manejo de archivos
	private File archivo;
	private BufferedReader br;
	private FileReader fr;

	public Sistema() {
		this.usuarios = new ArrayList<Usuario>();
		this.atracciones = new ArrayList<Atraccion>();
		this.promociones = new ArrayList<Promocion>();
		this.informes = new ArrayList<InformeCompra>();
	}

	public void cargaUsuarios(String rutaUsuarios) {
		try {
			archivo = new File(rutaUsuarios);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null) {
				String [] parametros = linea.split("-");
				this.usuarios.add(new Usuario(parametros[0], Integer.parseInt(parametros[1]), Double.parseDouble(parametros[2]), null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void cargarListas(String rutaUsuarios, String rutaAtracciones, String rutaPromociones) {
		cargaUsuarios(rutaUsuarios);
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	

}