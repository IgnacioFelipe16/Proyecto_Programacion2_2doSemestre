package com.programaEjemplo4.dao;
import java.util.List;

import com.programaEjemplo4.model.Usuario;

import jakarta.transaction.Transactional;

@Transactional								//Mejora la comunicación con la base de datos. Fue necesario inyectar una dependencia en pom.xml
public interface UsuarioDao {
	//EN ESTA INTERFACE SE DEFINEN LOS MÉTODOS (pero no se implementan)
	
	List<Usuario> getUsuarios();			//Método para mostrar todos los usuarios.
	
	void eliminar(Long id);					//Método para eliminar un usuarios según su id.

	void registrar(Usuario usuario);		//Método para registrar un usuario.

	boolean verificarCredenciales(Usuario usuario);
}
