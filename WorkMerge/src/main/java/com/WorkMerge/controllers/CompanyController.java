package com.WorkMerge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.services.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/form")
	public String registerCompany() {
		return "registroInicialEmpresa";
	}
	
	@PostMapping("/save")
	public String createCompany(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("password2") String password2) {
		try {
			companyService.newCompany(email, password, password2);
			return "registroDatoEmpresa";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "registroInicialEmpresa";
		}
	}
	
}
