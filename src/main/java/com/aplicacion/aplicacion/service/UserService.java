package com.aplicacion.aplicacion.service;

import javax.validation.Valid;

import com.aplicacion.aplicacion.entity.User;

public interface UserService {

	public Iterable<User> getAllUsers();
	public void save(User user);
	//public boolean validarUsuario(User user);
	public User crearUsuario(User user) throws Exception;
}
