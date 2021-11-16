package com.WorkMerge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.WorkMerge.services.AdminService;
import com.WorkMerge.services.ClientService;
import com.WorkMerge.services.CompanyService;


@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping()
	public String index() {
		return "index";
	}
	
}