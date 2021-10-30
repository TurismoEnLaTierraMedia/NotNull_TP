package dao;

import clases.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	public Usuario encontrarUsuario(int id);
	
}
