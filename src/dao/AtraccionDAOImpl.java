package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.Atraccion;
import jdbc.ConnectionProvider;

public class AtraccionDAOImpl implements AtraccionDAO {

	@Override
	public List<Atraccion> findAll() throws SQLException {
		try {
			String sql = "SELECT * FROM atracciones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> atracciones = new ArrayList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(aAtraccion(resultados));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion aAtraccion(ResultSet resultados) throws SQLException {
		return new Atraccion(resultados.getInt(1),resultados.getString(2), Integer.parseInt(resultados.getString(3)),
				Double.parseDouble(resultados.getString(4)), Integer.parseInt(resultados.getString(5)),
				resultados.getString(6));
	}

	@Override
	public int countAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Atraccion atracc) throws SQLException {
		try {
			String sql = "INSERT OR IGNORE INTO atracciones (nombre, costo, tiempo, cupo, tipo) VALUES (?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atracc.getNombre());
			statement.setInt(2, atracc.getCostoDeVisita());
			statement.setDouble(3, atracc.getDuracion());
			statement.setInt(4, atracc.getCupo());
			statement.setString(5, atracc.getTipo().toString());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Atraccion atracc) throws SQLException {
		try {
			String sql = "UPDATE atracciones SET cupo = ? " + " WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atracc.getCupo());
			statement.setString(2, atracc.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Atraccion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
