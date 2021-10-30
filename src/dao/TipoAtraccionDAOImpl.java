package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import clases.Tipo_De_Atraccion;
import jdbc.ConnectionProvider;

public class TipoAtraccionDAOImpl implements TipoAtraccionDAO{

	@Override
	public List<Tipo_De_Atraccion> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int insert(Tipo_De_Atraccion t) throws SQLException {
		try {
			String sql = "INSERT OR IGNORE INTO tipoAtracciones (id, nombre) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, t.ordinal());
			statement.setString(2, t.toString());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Tipo_De_Atraccion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Tipo_De_Atraccion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tipo_De_Atraccion encontrarTipo(int id) {
		try {
			String sql = "SELECT * FROM tipoAtracciones WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			return aTipoDeAtraccion(resultados);
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Tipo_De_Atraccion aTipoDeAtraccion(ResultSet resultados) throws SQLException {
		return Tipo_De_Atraccion.valueOf(resultados.getString(2));
	}

}
