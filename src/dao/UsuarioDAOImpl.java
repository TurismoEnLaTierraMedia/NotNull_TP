package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import clases.Usuario;
import jdbc.ConnectionProvider;

public class UsuarioDAOImpl implements UsuarioDAO{

	@Override
	public List<Usuario> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Usuario usu) throws SQLException {
		try {
			String sql = "INSERT INTO usuarios (nombre, costo, tiempodisponible, preferencia) VALUES (?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usu.getNombre());
			statement.setInt(2, usu.getPresupuesto());
			statement.setDouble(3, usu.getTiempoDisponible());
			statement.setString(4, usu.getPreferencia().toString());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
