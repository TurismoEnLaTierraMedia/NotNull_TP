package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import clases.Promocion;
import clases.PromocionAbsoluta;

import jdbc.ConnectionProvider;

public class PromocionAbsolutaDAOImpl implements PromocionDAO {

	private PromocionAbsoluta toPromocionAbsoluta(ResultSet resultados) throws SQLException {
		return new PromocionAbsoluta(resultados.getString(3), resultados.getString(4), resultados.getInt(5));

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
				promociones.add(toPromocionAbsoluta(resultados));
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
		PromocionAbsoluta promoAbs = (PromocionAbsoluta) t;

		try {
			String sql = "INSERT INTO PROMOCIONES (codigoTipoPromocion,TipoAtraccionPromocion,nombre,costo,id_listaAtracciones) VALUES (?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			// id_promocion, codigoTipoPromocion, TipoAtraccionPromocion, nombre, costo,
			// id_listaAtracciones
			statement.setInt(1, 1);
			statement.setString(2, promoAbs.getTipo().toString());
			statement.setString(3, promoAbs.getNombre());
			statement.setInt(4, promoAbs.obtenerPrecioFinal());
			statement.setInt(5, 0);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promocion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Promocion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
