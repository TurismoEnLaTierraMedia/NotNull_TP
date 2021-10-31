package dao;

import java.util.List;

import clases.Atraccion;

public interface PromocionListaAtraccionesDAO extends GenericDAO<Atraccion> {

	public List<Integer> obtenerListaAtraccionesporID(int id);

}
