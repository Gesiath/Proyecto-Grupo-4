package com.WorkMerge.services;

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
	
	//GUARDANDO CLIENTE
	//@Transactional
	//public Client save(Client p) throws Exception {
		
	//	if(p.getEmail() ==null) { //si existe el mail no te deja registrarte
	//		throw new Exception("ERROR: 'El email no puede estar vacío'");
	//	}
	//	if(p.getPassword() ==null) { //si existe el mail no te deja registrarte
	//		throw new Exception("ERROR: 'La contraseña no puede estar vacía'");
	//	}
		
	//return clientRepository.save(p);
	//}
	
	//GUARDADO
	@Transactional //Transactional (se pone porque cambia algo en la base de datos)
	public Client save(Rol rol,String email, String password,Curriculum curriculum, Photo photo, boolean active) throws Exception {
		Client p = new Client();
		
		p.setRol(Rol.CLIENT);
		p.setEmail(email);
		String encript = new BCryptPasswordEncoder().encode(password); //ENCRIPTANDO PASSWORD
		p.setPassword(encript);
		p.setCurriculum(curriculum);
		p.setPhoto(photo);
		p.setActive(true);
		
		if(p.getEmail() ==null || p.getEmail().isEmpty() || p.getPassword() ==null || p.getPassword().isEmpty()) { //si existe el mail no te deja registrarte
			throw new Exception("ERROR: 'No puede estar vacío el mail o la contraseña'");
		}else {
			return clientRepository.save(p);
		}
			
		
	}
	
	
	@Transactional
	//Eliminar cliente
	public void delete(Client p) {
		clientRepository.delete(p);
	}
	
	@Transactional
	//Dar de baja cliente
	public void DowngradeClient(Client p) {
		p.setActive(false);
	}
	
	
}
