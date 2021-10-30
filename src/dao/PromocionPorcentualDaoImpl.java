package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import clases.Promocion;
import clases.PromocionPorcentual;
import jdbc.ConnectionProvider;

public class PromocionPorcentualDaoImpl implements PromocionDAO {
	
	
	private PromocionPorcentual toPromocionPorcentual (ResultSet resultados) throws SQLException {
		return new PromocionPorcentual(resultados.getString(2), resultados.getString(3), resultados.getInt(5));
	}
	

	@Override
	public List<Promocion> findAll() throws SQLException {
		try {
			String sql = "SELECT * FROM PROMOCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promociones = new LinkedList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromocionPorcentual(resultados));
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public int countAll() throws SQLException {
		try {
			String sql = "SELECT * AS TOTAL FROM PROMOCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Promocion t) throws SQLException {
		try {
			String sql = "INSERT INTO PROMOCIONES (TIPO, PACK, VALOR DE DESCUENTO) VALUES (?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, PromocionPorcentual.getTipo());
			statement.setString(2, PromocionPorcentual.getNombre());
			statement.setInt(3, PromocionPorcentual.getValorDesc());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promocion t) throws SQLException {
		try {
			String sql = "UPDATE PROMOCIONES SET TIPO = ? PACK = ? VALOR DE DESCUENTO = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, PromocionPorcentual.getTipo());
			statement.setString(2, PromocionPorcentual.getNombre());
			statement.setInt(3, PromocionPorcentual.getValorDesc());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	

	@Override
	public int delete(Promocion t) throws SQLException {
		try {
			String sql = "DELETE FROM PROMOCIONES WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, PromocionPorcentual.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
