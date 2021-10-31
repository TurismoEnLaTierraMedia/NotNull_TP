package dao;

import clases.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion>{
	public Atraccion encontrarAtraccion(int id);

	public Atraccion encontrarAtraccion(String string);
}
