package com.programaEjemplo4.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.programaEjemplo4.model.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository									
@Transactional
public class ClienteDaoImp implements ClienteDao{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Cliente> obtenerClientes() {
		String query="from Cliente";
		return entityManager.createQuery(query).getResultList();
	}
}