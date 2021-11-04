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
import com.WorkMerge.exceptions.ServiceException;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	//Registrar cliente
	public void registerClient(String id, Rol rol,String email, String password,String password2, boolean active) throws ServiceException{ //BUSCAMOS UNA CLIENTE Y DEVOLVEMOS UN OPTIONAL
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
	public Client modifyClient(String id,Rol rol,String email, String password,String password2,Curriculum curriculum, Photo photo, boolean active) throws ServiceException {
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
		throw new ServiceException("No se encontro el cliente solicitado");
	}	
	}
	
	
	@Transactional
	//Eliminar cliente
	public void delete(String id) throws ServiceException {
		Optional<Client> respuesta = clientRepository.findById(id);
		if(respuesta.isPresent()) {
		clientRepository.deleteById(id);
		}else {
			throw new ServiceException("No se encontro el cliente ");
		}
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
	public void validar(String email,String password,String password2)throws ServiceException{
		if(email==null || email.isEmpty()) {
			throw new ServiceException("El email no puede estar vacío");
		}
		if(password==null || password.isEmpty()) {
			throw new ServiceException("La contraseña no puede estar vacía");
		}
		
		if(!password.equals(password2)){
			throw new ServiceException("La contraseñas tienen que coincidir");
		}
	}
	}
	
	

