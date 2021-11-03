package com.WorkMerge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.WorkMerge.entities.Admin;
import com.WorkMerge.enums.Rol;
import com.WorkMerge.repositories.AdminRepository;
import com.WorkMerge.repositories.ClientRepository;
import com.WorkMerge.repositories.CompanyRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	/*Crear ADMIN, Eliminar cliente, Eliminar empresa, Aceptar trabajo*/
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Admin newAdmin(String email, String password, String nickname) {
		
		/*Encripto Contraseña*/
		String encryptedKey = new BCryptPasswordEncoder().encode(password);
		
		/*Creo nuevo admin*/
		Admin newAdmin = new Admin();
		
		/*Seteo atributos*/
		newAdmin.setEmail(email);
		newAdmin.setPassword(encryptedKey);
		newAdmin.setNickname(nickname);
		newAdmin.setRol(Rol.ADMIN);

		return adminRepository.save(newAdmin);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void deleteClient(String id) {
		
		/*Elimino cliente*/
		clientRepository.deleteById(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void deleteCompany(String id) {
		
		/*Elimino empresa*/
		companyRepository.deleteById(id);
	}
	/*
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Job registerWork(String id) {
		
	}
	*/
}

