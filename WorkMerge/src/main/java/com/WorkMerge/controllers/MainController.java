package com.WorkMerge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.WorkMerge.exceptions.ServiceException;


@Controller
@RequestMapping("/")
public class MainController {
		
	@GetMapping()
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model, @RequestParam(required = false) String error, @RequestParam(required = false) String mail, 
			@RequestParam(required = false) String logout) throws ServiceException {
		if (error != null) {
			model.addAttribute("error", "El usuario ingresado o la contraseña son incorrectas");
		}
		
		if (mail != null) {
			model.addAttribute("mail", mail);			
		}
		
		return "login/login";
		
	}
}