package com.WorkMerge.services;




import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.WorkMerge.repositories.ClientRepository;



import com.WorkMerge.entities.Client;
import com.WorkMerge.entities.Curriculum;
import com.WorkMerge.entities.Photo;
import com.WorkMerge.enums.Rol;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	//Registrar cliente
	public void Save(String id, Rol rol,String email, String password,Curriculum curriculum, Photo photo, boolean active) throws Exception{ //BUSCAMOS UNA CLIENTE Y DEVOLVEMOS UN OPTIONAL
		validar(email,password);
		Client cliente = new Client();
			cliente.setEmail(email);
			cliente.setPassword(password);
			cliente.setRol(Rol.CLIENT);
			cliente.setCurriculum(curriculum);
			cliente.setPhoto(photo);
			cliente.setActive(true);
			clientRepository.save(cliente);
		}
	
	
	//MODIFICAR CLIENTE
	@Transactional //Transactional (se pone porque cambia algo en la base de datos)
	public Client ModifyClient(String id,Rol rol,String email, String password,Curriculum curriculum, Photo photo, boolean active) throws Exception {
		validar(email,password);
		Optional<Client> respuesta = clientRepository.findById(id);
		if(respuesta.isPresent()) {
		Client p = respuesta.get();
		p.setRol(Rol.CLIENT);
		p.setEmail(email);
		String encript = new BCryptPasswordEncoder().encode(password); //ENCRIPTANDO PASSWORD
		p.setPassword(encript);
		p.setCurriculum(curriculum);
		p.setPhoto(photo);
		p.setActive(true);
		return clientRepository.save(p);
	}else {
		throw new Exception("No se encontro el cliente solicitado");
	}	
	}
	
	
	@Transactional
	//Eliminar cliente
	public void delete(Client p) {
		clientRepository.delete(p);
	}
	
	@Transactional
	//Dar de baja cliente
	public void LowCustomer(String id) {
		Optional<Client> respuesta = clientRepository.findById(id);
		if(respuesta.isPresent()) {
			Client cliente = respuesta.get();
			cliente.setActive(false);
		}
	}
	
	
	@Transactional
	//Dar de alta cliente
	public void HightCustomer(String id) {
		Optional<Client> respuesta = clientRepository.findById(id);
		if(respuesta.isPresent()) {
			Client cliente = respuesta.get();
			cliente.setActive(true);
		}
	}
	
	
	
	//Metodo validación
	public void validar (String email,String password)throws Exception{
		if(email==null || email.isEmpty()) {
			throw new Exception("El email no puede estar vacío");
		}
		if(password==null || password.isEmpty()) {
			throw new Exception("La contraseña no puede estar vacío");
		}
	}
	}
	
	

