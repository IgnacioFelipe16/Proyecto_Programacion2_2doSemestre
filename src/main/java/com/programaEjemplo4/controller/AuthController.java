package com.programaEjemplo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.programaEjemplo4.dao.UsuarioDao;
import com.programaEjemplo4.model.Usuario;

@RestController
public class AuthController {
	@Autowired								//Permite acceder a UsuarioDao, que es una interface implementada en la clase UsuarioDaoImp.
	private UsuarioDao usuarioDao;
	
	@RequestMapping(value="api/login", method=RequestMethod.POST)
	public String login(@RequestBody Usuario usuario){
		if(usuarioDao.verificarCredenciales(usuario)) {
			return "ok";
		}
		return "fail";
	}
}
