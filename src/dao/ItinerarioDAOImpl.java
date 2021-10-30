package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import clases.Itinerario;
import jdbc.ConnectionProvider;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	@Override
	public List<Itinerario> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
