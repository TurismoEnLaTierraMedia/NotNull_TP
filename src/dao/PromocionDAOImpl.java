package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import clases.Promocion;
import clases.PromocionAbsoluta;
import clases.PromocionAxB;
import clases.PromocionPorcentual;
import jdbc.ConnectionProvider;

public class PromocionDAOImpl implements PromocionDAO {

	@Override
	public List<Promocion> findAll() throws SQLException {
		PromocionListaAtraccionesDAO promocionListaAtraccionesDAO = FactoryDAO.getPromocionListaAtraccionesDAO();
		AtraccionDAO atraccDAO = FactoryDAO.getAtraccionDAO();
		try {
			String sql = "SELECT * FROM promociones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promociones = new ArrayList<Promocion>();
			while (resultados.next()) {
				if (resultados.getInt(2) == 1) {
					promociones.add(aPromocionPorcentual(resultados));
				}
				if (resultados.getInt(2) == 2) {
					promociones.add(aPromocionAbsoluta(resultados));
				}
				if (resultados.getInt(2) == 3) {
					promociones.add(aPromocionAxB(resultados));
				}
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Promocion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
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

	public PromocionPorcentual aPromocionPorcentual(ResultSet resultado) throws SQLException{
		PromocionListaAtraccionesDAO promoListaAtraccDAO = FactoryDAO.getPromocionListaAtraccionesDAO();
		AtraccionDAO atraccDAO = FactoryDAO.getAtraccionDAO();
		PromocionPorcentual promocion = new PromocionPorcentual(resultado.getInt(1), resultado.getString(3), resultado.getString(4), resultado.getInt(5));
		List<Integer> listaID = promoListaAtraccDAO.obtenerListaAtraccionesporID(resultado.getInt(1));
		for (Iterator iterator = listaID.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			promocion.getAtracciones().add(atraccDAO.encontrarAtraccion(integer));
		}
		return promocion;
	}

	public PromocionAbsoluta aPromocionAbsoluta(ResultSet resultado) {
		// TODO Auto-generated method stub
		return null;
	}

	public PromocionAxB aPromocionAxB(ResultSet resultado) {
		// TODO Auto-generated method stub
		return null;
	}

}
