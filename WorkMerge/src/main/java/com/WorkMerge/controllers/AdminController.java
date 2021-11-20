 package com.WorkMerge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.WorkMerge.entities.Admin;
import com.WorkMerge.entities.Client;
import com.WorkMerge.entities.Company;
import com.WorkMerge.entities.Job;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.services.AdminService;
import com.WorkMerge.services.JobService;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private JobService jobService;
	
	private final String viewPath = "admin/";
	
	@GetMapping()
	public String mainAdmin(ModelMap modelo) {		
		return this.viewPath.concat("MainAdmin");
	}
	
	@GetMapping("/listasAdmin")
	public String listAdmin(ModelMap modelo) {
		List<Admin> listAdmin = adminService.listAdmins();
		modelo.addAttribute("admins", listAdmin);
		return this.viewPath.concat("listasAdmin");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/registro")
	public String crearAdmin() {
		return this.viewPath.concat("crearAdmin");
	}
	
	@GetMapping("/adminEmpresas")
	public String adminEmpresas(ModelMap modelo,@Param("search") String search) {
		List<Company> listCompanies = adminService.listCompanies();
		modelo.addAttribute("companies", listCompanies);
		return this.viewPath.concat("tableroAdminEmpresas");		
	}
	
	
	@GetMapping("/adminClientes")
	public String adminClientes(ModelMap modelo) {		
		List<Client> listClients = adminService.listClients();
		modelo.addAttribute("clients", listClients);
		return this.viewPath.concat("tableroAdminUsuarios");
	}
	
	@GetMapping("/adminTrabajos")
	public String adminTrabajos(ModelMap modelo) {		
		List<Job> listJobs = adminService.listJobs();
		modelo.addAttribute("jobs", listJobs);
		return this.viewPath.concat("tableroAdminPost");
	}
	
	@PostMapping("/guardar")
	public String guardarAdmin(@RequestParam("email") String email, @RequestParam("nickname") String nickname, @RequestParam("password") String password) {
		try {
			adminService.newAdmin(email, password, nickname);
			return "redirect:/admin";
		} catch (ServiceException e) {
			e.printStackTrace();
			return this.viewPath.concat("crearAdmin");
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
	
	@GetMapping("alta/{idJob}")
	public String alta(ModelMap modelo, @PathVariable("t.id") String idJob) {
		try {
			jobService.upgradeJob(idJob);
			return this.viewPath.concat("tableroAdminPost");
		} catch (ServiceException e) {
			e.printStackTrace();
			return this.viewPath.concat("tableroAdminPost");
		}
		
	}
	
	@GetMapping("baja/{idJob}")
	public String baja(ModelMap modelo, @PathVariable("t.id") String idJob) {
		try {
			jobService.downgradeJob(idJob);
			return this.viewPath.concat("tableroAdminPost");
		} catch (ServiceException e) {
			e.printStackTrace();
			return this.viewPath.concat("tableroAdminPost");
		}
		
	}
}
