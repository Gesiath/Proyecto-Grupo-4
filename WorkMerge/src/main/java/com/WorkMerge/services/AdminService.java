package com.WorkMerge.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.WorkMerge.entities.Admin;
import com.WorkMerge.entities.Client;
import com.WorkMerge.entities.Company;
import com.WorkMerge.entities.Job;
import com.WorkMerge.enums.Rol;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.repositories.AdminRepository;
import com.WorkMerge.repositories.ClientRepository;
import com.WorkMerge.repositories.CompanyRepository;
import com.WorkMerge.repositories.JobRepository;

@Service
public class AdminService implements UserDetailsService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	/*Crear ADMIN, Eliminar cliente, Eliminar empresa, Eliminar trabajo*/
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Admin newAdmin(String email, String password, String nickname) throws ServiceException{
		
		validate(email, password, nickname);
		
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
	public void deleteClient(String id) throws ServiceException{
		
		Optional<Client> respuesta = clientRepository.findById(id);
		if(respuesta.isPresent()) {
			/*Elimino cliente*/
			clientRepository.deleteById(id);
		} else {
			throw new ServiceException("No se encontro el usuario a borrar.");
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void deleteAdmin(String id) throws ServiceException{
		
		Optional<Admin> respuesta = adminRepository.findById(id);
		if(respuesta.isPresent()) {
			/*Elimino admin*/
			adminRepository.deleteById(id);
		} else {
			throw new ServiceException("No se encontro el admin a borrar.");
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void deleteCompany(String id) throws ServiceException{
		
		Optional<Company> respuesta = companyRepository.findById(id);
		if(respuesta.isPresent()) {
			/*Elimino empresa*/
			companyRepository.deleteById(id);
		} else {
			throw new ServiceException("No se encontro la compañia a borrar.");
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void deleteJob(String id) throws ServiceException{
		
		Optional<Job> respuesta = jobRepository.findById(id);
		if(respuesta.isPresent()) {
			/*Elimino trabajo*/
			jobRepository.deleteById(id);
		} else {
			throw new ServiceException("No se encontro el trabajo a borrar.");
		}
	}
	
	//OBTENER POR MAIL
		@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
		public Admin obtenerPorMail(String email) throws ServiceException{
			
			return adminRepository.findByEmail(email);
		}
	
	@Transactional(readOnly = true)
	public List<Job> listJobs() {
		return jobRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Company> listCompanyByParam(String name) {
		return companyRepository.findByName(name);
	}

	@Transactional(readOnly = true)
	public List<Company> listCompanies(){
		return companyRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<Client> listClients(){
		return clientRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Admin> listAdmins(){
		return adminRepository.findAll();
	}
	
	private void validate(String email, String password, String nickname) throws ServiceException{
		if(email == null || email.isEmpty()) {
			throw new ServiceException("El email no puede ser nulo o estar vacio.");
		}
		if(password == null || password.isEmpty()) {
			throw new ServiceException("La contraseña no puede ser nula o estar vacia.");
		}
		if(nickname == null || nickname.isEmpty()) {
			throw new ServiceException("El nick no puede ser nulo o estar vacio.");
		}
		if(adminRepository.existByEmail(email)) {
			throw new ServiceException("El administrador con ese email ya existe.");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		
		try {
			Admin admin = adminRepository.findByEmail(mail);
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			authorities.add(new SimpleGrantedAuthority("ROLE_" + admin.getRol()));
			
			return new User(mail, admin.getPassword(), authorities);
		} catch(Exception e) {
			
			throw new UsernameNotFoundException("El admin no existe");
			
		}
	}
	
	/*
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Job registerWork(String id) {
		
	}
	*/
}

