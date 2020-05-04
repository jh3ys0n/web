package com.aplicacion.aplicacion.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.aplicacion.aplicacion.entity.User;
import com.aplicacion.aplicacion.repository.RoleRepository;
import com.aplicacion.aplicacion.repository.UserRepository;
import com.aplicacion.aplicacion.service.UserService;

@Controller
public class UserController {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserService userService;
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/registro")
	public String regsitro(){
		
		return "FormularioNuevaCamida";
	}
	
	@GetMapping("/userForm")
	public String getUserForm(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("listTab","active");
		return "user-form/user-view";
	}	
	
	@PostMapping("/userForm")
	public String saveUser(@Valid @ModelAttribute("userForm")User user,BindingResult result,ModelMap model ) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("listTab","active");
		}
		else {
			try {
				userService.crearUsuario(user);
				model.addAttribute("userForm",new User());
				model.addAttribute("fromTab","active");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm",user);
				model.addAttribute("fromTab", "active");
				model.addAttribute("userList",userService.getAllUsers());
				model.addAttribute("roles",roleRepository.findAll());
			}
		}
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		
		return "user-form/user-view";
	}	
	
	
}
