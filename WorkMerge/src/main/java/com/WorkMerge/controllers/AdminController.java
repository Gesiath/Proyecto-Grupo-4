package com.WorkMerge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.WorkMerge.services.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping()
	public String createAdmin() {
		adminService.newAdmin("maximongelos@gmail.com", "123456", "maximongelos");
		return "index";
	}
}
