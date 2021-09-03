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
				String[] parametros = linea.split("-");
				this.usuarios.add(new Usuario(parametros[0], Integer.parseInt(parametros[1]),
						Double.parseDouble(parametros[2]), null));
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

	public void cargaAtracciones(String rutaAtracciones) {
		try {
			archivo = new File(rutaAtracciones);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] parametro = linea.split("-");
				this.atracciones.add(new Atraccion(parametro[0], Integer.parseInt(parametro[1]),
						Double.parseDouble(parametro[2]), Integer.parseInt(parametro[3]), null));
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
	
//	private Atraccion [] arrayAtracciones (String [] parametro) {
//		Atraccion [] atracciones = new Atraccion [parametro.length-1];
//		
//		return atracciones;
//	}
	
	public void cargaPromociones(String rutaPromociones) {
		try {
			archivo = new File(rutaPromociones);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;
			while((linea = br.readLine()) != null) {
				String [] parametro = linea.split("-");
				this.promociones.add(new PromocionAbsoluta(Integer.parseInt(parametro[0]), arrayAtracciones(parametro)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cargarListas(String rutaUsuarios, String rutaAtracciones, String rutaPromociones) {
		cargaUsuarios(rutaUsuarios);
		cargaAtracciones(rutaAtracciones);
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}
	
	

}