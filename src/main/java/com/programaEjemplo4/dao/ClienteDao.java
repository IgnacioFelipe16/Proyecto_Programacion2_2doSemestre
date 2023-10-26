package com.programaEjemplo4.dao;

import java.util.List;

import com.programaEjemplo4.model.Cliente;

import jakarta.transaction.Transactional;

@Transactional
public interface ClienteDao {
	List<Cliente> obtenerClientes();
}