package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.Usuario;
import jdbc.ConnectionProvider;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public List<Usuario> findAll() throws SQLException {
		try {
			String sql = "SELECT * FROM usuarios";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> usuarios = new ArrayList<Usuario>();
			while (resultados.next()) {
				usuarios.add(aUsuario(resultados));
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	//Ver tema id
	private Usuario aUsuario(ResultSet resultados) throws SQLException {
		return new Usuario(Integer.parseInt(resultados.getString(1)), resultados.getString(2), Integer.parseInt(resultados.getString(3)),
				Double.parseDouble(resultados.getString(4)), resultados.getString(5));
	}

	@Override
	public int countAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Usuario usu) throws SQLException {
		try {
			String sql = "INSERT OR IGNORE INTO usuarios (nombre, presupuesto, tiempodisponible, preferencia) VALUES (?, ?, ?, ?)";
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
	public int update(Usuario usu) {
		try {
			String sql = "UPDATE usuarios SET presupuesto = ? , " + "tiempodisponible = ? " + " WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usu.getPresupuesto());
			statement.setDouble(2, usu.getTiempoDisponible());
			statement.setString(3, usu.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Usuario encontrarUsuario(int id) {
		try {
			String sql = "SELECT * FROM usuarios WHERE usuario_id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			return aUsuario(resultados);
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
