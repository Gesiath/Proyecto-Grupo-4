 package com.WorkMerge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.WorkMerge.entities.Admin;
import com.WorkMerge.entities.Company;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.services.AdminService;

@Controller
@PreAuthorize("isAuthenticated()")
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
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/registro")
	public String crearAdmin() {
		return this.viewPath.concat("register-admin");
	}
	
	@GetMapping("/adminEmpresas1")
	public String adminEmpresas(ModelMap modelo) {
		
		List<Company> listCompanies = adminService.listCompanies();
		modelo.addAttribute("companies", listCompanies);
		return this.viewPath.concat("tableroAdminEmpresas");
		
	}
	
	@GetMapping("/adminEmpresas2")
	public String adminEmpresas(ModelMap modelo, @RequestParam(value = "q", required = false) String q) {
		
		List<Company> listCompaniesByParam = adminService.listCompanyByParam(q);
		modelo.addAttribute("companiesPorParam", listCompaniesByParam);
		return this.viewPath.concat("tableroAdminEmpresas");
		
	}
	
	@GetMapping("/adminClientes")
	public String adminClientes() {
		return this.viewPath.concat("tableroAdminUsuarios");
	}
	
	@PostMapping("/guardar")
	public String guardarAdmin(@RequestParam("email") String email, @RequestParam("nickname") String nickname, @RequestParam("password") String password) {
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
			return "redirect:/admin/adminEmpresas";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "redirect:/admin";
		}
		
	}
}
