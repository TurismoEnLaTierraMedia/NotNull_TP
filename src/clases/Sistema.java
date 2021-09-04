package clases;

import java.util.ArrayList;
import java.util.Iterator;
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

	private Atraccion convertirStringAAtraccion(String nombre) {
		Atraccion result = null;
		Iterator<Atraccion> atraccionIterator = this.getAtracciones().iterator();
		while (atraccionIterator.hasNext()) {
			if (atraccionIterator.next().getNombre() == nombre) {
				result = atraccionIterator.next();
			}
		}
		return result;
	}
	
	private Promocion determinarTipoPromocion(String [] parametro) {
		if (Integer.parseInt(parametro[0]) == 1) {
			return new PromocionAbsoluta(Integer.parseInt(parametro[0]), parametro.length);
		}else if (Integer.parseInt(parametro[0]) == 2) {
			return new PromocionPorcentual(Integer.parseInt(parametro[0]), parametro.length);
		}else if (Integer.parseInt(parametro[0]) == 3){
			return new PromocionAxB(convertirStringAAtraccion(parametro[1]), parametro.length);
		} else {
			throw new IllegalArgumentException("Codigo de tipo de promocion invalido");
		}
		
	}

	public void cargaPromociones(String rutaPromociones) {
		try {
			archivo = new File(rutaPromociones);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] parametro = linea.split("-");
				Promocion promocionaAgregar = determinarTipoPromocion(parametro);
				for (int i = 1; i < parametro.length; i++) {
					promocionaAgregar.anadirAtraccion(convertirStringAAtraccion(parametro[i]));
				}
				this.promociones.add(promocionaAgregar);
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
		cargaAtracciones(rutaAtracciones);
	}

	public void recomendar(Usuario usu) {
		Iterator<Atraccion> atraccionesIterator = this.getAtracciones().iterator();

		while (atraccionesIterator.hasNext()) {
			Atraccion atraccion = (Atraccion) atraccionesIterator.next();

		}

	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}

	public ArrayList<Promocion> getPromociones() {
		return promociones;
	}
	
	

}