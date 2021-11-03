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
	public void registerClient(String id, Rol rol,String email, String password,String password2, boolean active) throws Exception{ //BUSCAMOS UNA CLIENTE Y DEVOLVEMOS UN OPTIONAL
		validar(email,password,password2);
		
		Client cliente = new Client();
			cliente.setEmail(email);
			String encript = new BCryptPasswordEncoder().encode(password); //ENCRIPTANDO PASSWORD
			cliente.setPassword(encript);
			cliente.setRol(Rol.CLIENT);
			cliente.setActive(true);
			clientRepository.save(cliente);
		}
	
	
	//MODIFICAR CLIENTE
	@Transactional //Transactional (se pone porque cambia algo en la base de datos)
	public Client modifyClient(String id,Rol rol,String email, String password,String password2,Curriculum curriculum, Photo photo, boolean active) throws Exception {
		validar(email,password,password2);
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
	public void delete(String id) {
		clientRepository.deleteById(id);
	}
	
	@Transactional
	//Dar de baja cliente
	public void lowCustomer(String id) {
		Optional<Client> respuesta = clientRepository.findById(id);
		if(respuesta.isPresent()) {
			Client cliente = respuesta.get();
			cliente.setActive(false);
		}
	}
	
	
	@Transactional
	//Dar de alta cliente
	public void hightCustomer(String id) {
		Optional<Client> respuesta = clientRepository.findById(id);
		if(respuesta.isPresent()) {
			Client cliente = respuesta.get();
			cliente.setActive(true);
		}
	}
	
	
	
	//Metodo validación
	public void validar(String email,String password,String password2)throws Exception{
		if(email==null || email.isEmpty()) {
			throw new Exception("El email no puede estar vacío");
		}
		if(password==null || password.isEmpty()) {
			throw new Exception("La contraseña no puede estar vacía");
		}
		
		if(!password.equals(password2)){
			throw new Exception("La contraseñas tienen que coincidir");
		}
	}
	}
	
	

