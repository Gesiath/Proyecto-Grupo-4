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
import com.WorkMerge.entities.Client;
import com.WorkMerge.entities.Company;
import com.WorkMerge.entities.Job;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.services.AdminService;
import com.WorkMerge.services.ClientService;
import com.WorkMerge.services.CompanyService;
import com.WorkMerge.services.JobService;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CompanyService companyService;
	
	
	private final String viewPath = "admin/";
	
	@GetMapping()
	public String mainAdmin(ModelMap modelo) {		
		return this.viewPath.concat("MainAdmin");
	}
	
	@GetMapping("/listasAdmin")
	public String listAdmin(ModelMap modelo, @RequestParam(required = false) String q) {
		
		if (q != null) {
			modelo.addAttribute("admins", adminService.findActiveByQ(q));
		}else {
			List<Admin> listAdmin = adminService.listAdmins();
			modelo.addAttribute("admins", listAdmin);
			
		}
		return this.viewPath.concat("listasAdmin");
	}
	
	@GetMapping("/adminEmpresas")
	public String adminEmpresas(ModelMap modelo, @RequestParam(required = false) String q) {
		
		if (q != null) {
			modelo.addAttribute("companies", adminService.findCompanyByQ(q));
		}else {
			List<Company> listCompanies = adminService.listCompanies();
			modelo.addAttribute("companies", listCompanies);
			
		}
		return this.viewPath.concat("tableroAdminEmpresas");
	}
	
	@GetMapping("/adminClientes")
	public String adminClientes(ModelMap modelo, @RequestParam(required = false) String q) {
		
		if (q != null) {
			modelo.addAttribute("clients", adminService.findClientByQ(q));
		}else {
		List<Client> listClients = adminService.listClients();
		modelo.addAttribute("clients", listClients);
		}
		return this.viewPath.concat("tableroAdminUsuarios");
	}
	
	@GetMapping("/adminTrabajos")
	public String adminTrabajos(ModelMap modelo, @RequestParam(required = false) String q) {
		if (q != null) {
			modelo.addAttribute("jobs", adminService.findJobByQ(q));
			
		}else {
		List<Job> listJobs = adminService.listJobs();
		modelo.addAttribute("jobs", listJobs);
		}
		return this.viewPath.concat("tableroAdminPost");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/registro")
	public String crearAdmin() {
		return this.viewPath.concat("crearAdmin");
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
			return "redirect:/admin/listasAdmin";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "redirect:/admin/listasAdmin";
		}
		
	}
	
	@GetMapping("altaTrabajo/{idJob}")
	public String alta(@PathVariable("idJob") String idJob) {
		try {
			jobService.upgradeJob(idJob);
			return "redirect:/admin/adminTrabajos";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "redirect:/admin/adminTrabajos";
		}
		
	}
	
	@GetMapping("altaCliente/{idCliente}")
	public String altaCliente(@PathVariable("idCliente") String idCliente) {
		clientService.hightCustomer(idCliente);
		return "redirect:/admin/adminClientes";
		
	}
	
	@GetMapping("altaEmpresa/{idEmpresa}")
	public String altaEmpresa(@PathVariable("idEmpresa") String idEmpresa) {
		try {
			companyService.upgradeCompany(idEmpresa);
			return "redirect:/admin/adminEmpresas";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "redirect:/admin/adminEmpresas";
		}
		
	}
	
	@GetMapping("bajaTrabajo/{idJob}")
	public String baja(@PathVariable("idJob") String idJob) {
		try {
			jobService.downgradeJob(idJob);
			return "redirect:/admin/adminTrabajos";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "redirect:/admin/adminTrabajos";
		}
		
	}
	
	@GetMapping("bajaCliente/{idCliente}")
	public String bajaCliente(@PathVariable("idCliente") String idCliente) {
		clientService.lowCustomer(idCliente);
		return "redirect:/admin/adminClientes";
		
	}
	
	@GetMapping("bajaEmpresa/{idEmpresa}")
	public String bajaEmpresa(@PathVariable("idEmpresa") String idEmpresa) {
		try {
			companyService.downgradeCompany(idEmpresa);
			return "redirect:/admin/adminEmpresas";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "redirect:/admin/adminEmpresas";
		}
		
	}
	
	@GetMapping("/deleteJob/{idJob}")
	public String deleteJob(@PathVariable("idJob") String id) {
		try {
			jobService.deleteJob(id);
			return "redirect:/admin/adminTrabajos";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "redirect:/admin/adminTrabajos";
		}
		
	}
}
