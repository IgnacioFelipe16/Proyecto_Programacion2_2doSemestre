package com.programaEjemplo4.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.programaEjemplo4.model.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
	

@Repository									//Le agrega las caracterÍsiticas necesarias para trabajar con una base de datos.
@Transactional								//Mejora la comunicación con la base de datos. Fue necesario inyectar una dependencia en pom.xml
public class UsuarioDaoImp implements UsuarioDao{
	//ACÁ SE IMPLEMENTAN LOS MÉTODOS DE LA INTERFACE UsuarioDao. Obligatoriamente se deben implementar todos los métodos de la interface.
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Usuario> getUsuarios() {
		String query="from Usuario";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void eliminar(Long id) {
		Usuario usuario = entityManager.find(Usuario.class, id);
		entityManager.remove(usuario);
	}
	
	@Override
	public void registrar(Usuario usuario) {
		entityManager.merge(usuario);
	}

	@Override
	public boolean verificarCredenciales(Usuario usuario) {
		String query="FROM Usuario WHERE email= :email";
		List<Usuario> lista=entityManager.createQuery(query).setParameter("email", usuario.getEmail()).getResultList();
		if(lista.isEmpty()) {
			return false;
		}
		String passHasheada=lista.get(0).getPassword();
		Argon2 argon2=Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		Boolean passEsIgual=argon2.verify(passHasheada, usuario.getPassword());
		return passEsIgual;
	}
}
