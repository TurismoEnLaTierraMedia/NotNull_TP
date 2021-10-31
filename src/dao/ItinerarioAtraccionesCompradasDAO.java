package dao;

import java.util.List;

import clases.Atraccion;

public interface ItinerarioAtraccionesCompradasDAO extends GenericDAO<Atraccion> {
	public List<Integer> listaAtraccionesPorID(int id);

}
