package dao;

import clases.Tipo_De_Atraccion;

public interface TipoAtraccionDAO extends GenericDAO<Tipo_De_Atraccion>{
	public Tipo_De_Atraccion encontrarTipo(int id);
}
