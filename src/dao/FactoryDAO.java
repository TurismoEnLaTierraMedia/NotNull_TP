package dao;



public class FactoryDAO {
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}
	
	public static AtraccionDAO getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}
	
	public static PromocionDAO getPromocionDAO() {
		return new PromocionDAOImpl();
	}
	
	public static TipoAtraccionDAO getTipoAtraccionDAO() {
		return new TipoAtraccionDAOImpl();
	}
	
	public static ItinerarioDAO getItinerarioDAO() {
		return new ItinerarioDAOImpl();
	}

	public static ItinerarioAtraccionesCompradasDAO getItinerarioAtraccionesCompradasDAO() {
		return new ItinerarioAtraccionesCompradasDAOImpl();
	}

	public static PromocionDAO getPromocionPorcentualDao() {
		return new PromocionPorcentualDaoImpl();
	}
	
	public static PromocionDAO getPromocionAbsolutaDAO() {
		return new PromocionAbsolutaDAOImpl();
	}
	
	public static PromocionDAO getPromocionAxBDAO() {
		return new PromocionAxBDAOImpl();
	}

	public static PromocionListaAtraccionesDAO getPromocionListaAtraccionesDAO() {
		return new PromocionListaAtraccionesDAOImpl();
	}

}
