package com.WorkMerge.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.WorkMerge.entities.Client;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.services.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	private final String viewPath = "cliente/";
	
	@GetMapping("/form")
	public String register() {
		return this.viewPath.concat("registroInicialCliente");
	}
	
	@PostMapping("/save")
	public String createClient(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("password2") String password2) {
		try {
			clientService.registerClient(email, password, password2);
			Client c = clientService.obtenerPorMail(email);
			String id = c.getId();
			return "redirect:/client/loadCv/".concat(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			return "redirect:/client/form";
		}
	}
	
	@GetMapping("/loadCv/{id}")
	public String registerCv(ModelMap modelo, @PathVariable("id") String id) {
		try {
			modelo.addAttribute("cliente", clientService.obtenerPorId(id));
			return this.viewPath.concat("registroDatoCliente");
		} catch (ServiceException e) {
			e.printStackTrace();
			return "redirect:/client/form";
		}
		
	}
	
	
	@PostMapping("/saveCv/{id}")
	public String createCv(@RequestParam("id") String id, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, 
			@RequestParam("dni") Integer dni, @RequestParam("genero") String genero, @RequestParam("nacionalidad") String nacionalidad,
			@RequestParam("ciudad") String ciudad, @RequestParam("domicilio") String domicilio, @RequestParam("fecha") Date fecha, 
			@RequestParam("telefono") Integer telefono, @RequestParam("educacion") String educacion, 
			@RequestParam("experienciaLaboral") String experienciaLaboral, @RequestParam("idiomas") String idiomas,
			@RequestParam("habilidadesInformáticas") String habilidadesInformáticas) {
		try {
			clientService.loadData(id, nombre, apellido, dni, genero, nacionalidad, ciudad, domicilio, fecha, telefono,
									educacion, experienciaLaboral, idiomas, habilidadesInformáticas);
			return "index";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "redirect:/client/form";
		}
		
	}
}
