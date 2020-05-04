package com.aplicacion.aplicacion.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import com.aplicacion.aplicacion.entity.User;
import com.aplicacion.aplicacion.repository.UserRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repositorio;

	@Override
	public Iterable<User> getAllUsers() {
		
		return repositorio.findAll();
	}

	@Override
	public void save(User user) {
		 repositorio.save(user);
		
	}

	public boolean validarUsuario(User user) throws Exception {
		java.util.Optional<User> userFound = repositorio.findByUsername(user.getUsername());
		if (userFound.isPresent()) {
			throw new Exception("Username no disponible");
		}
		return true;
	}
	
	private boolean validarPass(User user) throws Exception {
		if ( !user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y Confirm Password no son iguales");
		}
		return true;
	}

	@Override
	public User crearUsuario(User user)throws Exception {
		if (validarUsuario(user) && validarPass(user)) {
			user = repositorio.save(user);
		}
		return user;
	}


	
}
