package com.WorkMerge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.WorkMerge.entities.Admin;
import com.WorkMerge.entities.Client;
import com.WorkMerge.entities.Company;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.services.AdminService;
import com.WorkMerge.services.ClientService;
import com.WorkMerge.services.CompanyService;

@Controller
@RequestMapping("/login")
public class ControllerLogin {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("")
	public String login(Model model, @RequestParam(required = false) String error, @RequestParam(required = false) String mail, 
			@RequestParam(required = false) String logout) throws ServiceException {
		if (error != null) {
			model.addAttribute("error", "El usuario ingresado o la contraseña son incorrectas");
		}
		
		if (mail != null) {
			model.addAttribute("mail", mail);			
		}
		
		Client client = clientService.obtenerPorMail(mail);
		Company company = companyService.obtenerPorMail(mail);
		Admin admin = adminService.obtenerPorMail(mail);
		
		if(client != null) {
			model.addAttribute("session", client);
		} 
		
		if(company != null) {
			model.addAttribute("session", company);
		} 
		 
		if(admin != null) {
			model.addAttribute("session", admin);
		} 
		
		
		return "login/login";
	}
	
}