package com.WorkMerge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.WorkMerge.entities.Admin;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.services.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	private final String viewPath = "admin/";
	
	@GetMapping()
	public String listAdmin(ModelMap modelo) {
		List<Admin> listAdmin = adminService.listAdmins();
		modelo.addAttribute("admins", listAdmin);
		return this.viewPath.concat("list-admin");
	}
	
	@GetMapping("/registro")
	public String registerAdmin() {
		return this.viewPath.concat("register-admin");
	}
	
	@PostMapping("/registro")
	public String createAdmin(@RequestParam("email") String email, @RequestParam("nickname") String nickname, @RequestParam("password") String password) {
		try {
			adminService.newAdmin(email, password, nickname);
			return "redirect:/admin";
		} catch (ServiceException e) {
			e.printStackTrace();
			return this.viewPath.concat("register-admin");
		}
	}
	
	@GetMapping("/eliminar/{id}")
	public String deleteAdmin(@PathVariable("id") String id) {
		try {
			adminService.deleteAdmin(id);
			return "redirect:/admin";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "redirect:/admin";
		}
		
	}
}
