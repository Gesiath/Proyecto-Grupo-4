package com.WorkMerge.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.services.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/form")
	public String register() {
		return "registroInicialCliente";
	}
	
	@PostMapping("/save")
	public String createClient(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("password2") String password2) {
		try {
			clientService.registerClient(email, password, password2);
			return "registroDatoCliente";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "registroInicialCliente";
		}
	}
	
	@GetMapping("/loadCv")
	public String registerCv() {
		return "registroDatoCliente";
	}
	
	@PostMapping("/saveCv/{id}")
	public String createCv(@PathVariable("id") String id, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, 
			@RequestParam("dni") Integer dni, @RequestParam("genero") String genero, @RequestParam("nacionalidad") String nacionalidad,
			@RequestParam("ciudad") String ciudad, @RequestParam("domicilio") String domicilio, @RequestParam("fecha") Date fecha, 
			@RequestParam("telefono") Integer telefono, @RequestParam("educacion") String educacion, 
			@RequestParam("experienciaLaboral") String experienciaLaboral, @RequestParam("idiomas") String idiomas,
			@RequestParam("habilidadesInformáticas") String habilidadesInformáticas) {
		try {
			clientService.loadData(id, nombre, apellido, dni, genero, nacionalidad, ciudad, domicilio, fecha, telefono, educacion, experienciaLaboral, idiomas, habilidadesInformáticas);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
}
