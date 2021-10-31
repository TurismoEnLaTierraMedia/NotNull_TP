package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.Atraccion;
import clases.Usuario;
import jdbc.ConnectionProvider;

public class PromocionListaAtraccionesDAOImpl implements PromocionListaAtraccionesDAO{

	@Override
	public List<Atraccion> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Atraccion atracc) throws SQLException {
		String sql = "INSERT OR IGNORE INTO promociones_atracciones "
				+ "(id_listaAtracciones, id_atraccion) " + "VALUES ((SELECT max(id_promocion) FROM promociones),?)";

		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, atracc.getID());
		
		int rows = statement.executeUpdate();
		return rows;
	}

	@Override
	public int update(Atraccion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Atraccion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<Integer> obtenerListaAtraccionesporID(int id){
		try {
			String sql = "SELECT DISTINCT id_atraccion\r\n"
					+ "FROM promociones_atracciones A\r\n"
					+ "INNER JOIN promociones B\r\n"
					+ "ON A.id_listaAtracciones = B.id_promocion";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Integer> idAtracciones = new ArrayList<Integer>();
			while (resultados.next()) {
				idAtracciones.add(resultados.getInt(1));
			}

			return idAtracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
