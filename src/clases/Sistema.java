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

	/**
	 * Metodo que realiza la carga de los usuarios. Recibe como parametro la ruta
	 * especificada del archivo.
	 * 
	 * @param rutaUsuarios
	 */
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

	/**
	 * Metodo que realiza la carga de las atracciones. Recibe como parametro la ruta
	 * del archivo.
	 * 
	 * @param rutaAtracciones
	 */
	public void cargaAtracciones(String rutaAtracciones) {
		try {
			archivo = new File(rutaAtracciones);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] parametro = linea.split("-");
				this.atracciones.add(new Atraccion(parametro[0], Integer.parseInt(parametro[1]),
						Double.parseDouble(parametro[2]), Integer.parseInt(parametro[3]), parametro[4]));
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

	// CARGA DE PROMOCIONES - METODOS

	/**
	 * Este metodo devuelve una Atraccion que se encuentra la lista de atracciones.
	 * El parametro corresponde al nombre de la atraccion -Basicamente recorre la
	 * lista de atracciones, pidiendo su nombre y lo compara con el nombre pasado
	 * por parametro
	 * 
	 * @param nombre
	 * @return Atraccion
	 */
	private Atraccion convertirStringAAtraccion(String nombre) {
		Atraccion result = null;
		Iterator<Atraccion> atraccionIterator = this.getAtracciones().iterator();
		while (atraccionIterator.hasNext()) {
			Atraccion atraccionit = atraccionIterator.next();
			if (nombre.equalsIgnoreCase(atraccionit.getNombre())) {
				result = atraccionit;
			}
		}
		return result;
	}

	/**
	 * Metodo que determina el tipo de promocion. Recibe como parametro un array de
	 * Strings con los elementos del constructor.
	 * <p>
	 * Devuelve:
	 * <ul>
	 * <li>PromocionAbsoluta si el codigo es 1
	 * <li>PromocionPorcentual si el codigo es 2
	 * <li>PromocionAxB si el codigo es 3
	 * <li>Lanza una excepcion si el codigo no es ninguno de los anteriores.</li>
	 * </li></li></li>
	 * </ul>
	 *
	 * 
	 * @param
	 * @return Promocion
	 */
	private Promocion determinarTipoPromocion(String[] parametro) {
		if (Integer.parseInt(parametro[0]) == 1) {
			return new PromocionAbsoluta(parametro[1], parametro[2], Integer.parseInt(parametro[3]), parametro.length - 4);
		} else if (Integer.parseInt(parametro[0]) == 2) {
			return new PromocionPorcentual(parametro[1], parametro[2], Integer.parseInt(parametro[3]), parametro.length - 4);
		} else if (Integer.parseInt(parametro[0]) == 3) {
			return new PromocionAxB(parametro[1], parametro[2], convertirStringAAtraccion(parametro[3]), parametro.length - 4);
		} else {
			throw new IllegalArgumentException("Codigo de tipo de promocion invalido."
					+ " \n 1-Promocion absoluta \n 2-Promocion porcentual \n 3-Promocion AxB");
		}

	}

	/**
	 * Metodo que realiza la carga de las promociones. Se pasa como parametro la ruta del archivo con la lista.
	 * @param rutaPromociones
	 */
	public void cargaPromociones(String rutaPromociones) {
		try {
			archivo = new File(rutaPromociones);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] parametro = linea.split("-");
				Promocion promocionaAgregar = determinarTipoPromocion(parametro);
				for (int i = 4; i < parametro.length; i++) {
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
		cargaPromociones(rutaPromociones);
	}
	
	
	
	public void recomendar(Usuario usu, Atraccion atrac) {
		
	}

	public boolean recomendar(Usuario usu, Promocion promo) {
		//if(usu.getPreferencia().equals(promo.getTipo())
		return true;

	}
	
	public void concretarCompra(String respuesta) {
		
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