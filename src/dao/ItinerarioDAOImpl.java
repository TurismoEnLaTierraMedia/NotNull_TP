package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import clases.Atraccion;
import clases.Itinerario;
import clases.Usuario;
import jdbc.ConnectionProvider;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	@Override
	public List<Itinerario> findAll() throws SQLException {
		try {
			String sql = "SELECT * FROM usuarios";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Itinerario> usuarios = new ArrayList<Itinerario>();
			while (resultados.next()) {
				usuarios.add(aItinerario(resultados));
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private Itinerario aItinerario(ResultSet resultados) throws SQLException{
		UsuarioDAO usuDAO = FactoryDAO.getUsuarioDAO();
		ItinerarioAtraccionesCompradasDAO itiAtraccCompraDAO = FactoryDAO.getItinerarioAtraccionesCompradasDAO();
		AtraccionDAO atraccDAO = FactoryDAO.getAtraccionDAO();
		List<Integer> idIntegers = itiAtraccCompraDAO.listaAtraccionesPorID(resultados.getInt(1));
		List<Atraccion> listaAtracciones = new ArrayList<Atraccion>();
		for (int i = 0; i < idIntegers.size(); i++) {
			listaAtracciones.add(atraccDAO.encontrarAtraccion(idIntegers.get(i)));
		}
		return new Itinerario(usuDAO.encontrarUsuario(resultados.getInt(1)), null, listaAtracciones, resultados.getInt(4), resultados.getDouble(5));
	}

	@Override
	public int countAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Itinerario iti) throws SQLException {

		try {
			String sql = "INSERT INTO itinerarios " + "(id_usuario, "
					+ "tiempoTotal, costoTotal)" + "VALUES (?,?,?)";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, iti.getUsuario().getID());
			statement.setDouble(2, iti.getTiempoNecesario());
			statement.setInt(3, iti.getTotalPagar());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Itinerario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Itinerario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
