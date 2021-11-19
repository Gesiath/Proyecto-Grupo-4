package com.WorkMerge.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.WorkMerge.entities.Client;
import com.WorkMerge.entities.Curriculum;
import com.WorkMerge.entities.Photo;
import com.WorkMerge.enums.Rol;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.repositories.ClientRepository;



@Service
public class ClientService implements UserDetailsService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PhotoService photoService;
	@Autowired
	private CurriculumService curriculumService;

	//REGISTRAR CLIENTE
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })//Transactional (se pone porque cambia algo en la base de datos)
	public Client registerClient(String email, String password,String password2,MultipartFile file) throws ServiceException{ //BUSCAMOS UNA CLIENTE Y DEVOLVEMOS UN OPTIONAL
		validar(email,password,password2);
		
		Client cliente = new Client();
			cliente.setEmail(email);
			String encript = new BCryptPasswordEncoder().encode(password); //ENCRIPTANDO PASSWORD
			cliente.setPassword(encript);
			cliente.setRol(Rol.CLIENT);
			cliente.setActive(true);
			
			Photo photo = photoService.saved(file);
			cliente.setPhoto(photo);
			
			return clientRepository.save(cliente);
		}
	
	//CARGAR CV
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })//Transactional (se pone porque cambia algo en la base de datos)
	public Client loadData(String id, String name, String surname, String dni, String  gender,
			String nationality, String address, String  city, String birthday, String phone, String education,
			String workexperience, String language, String skills ) throws ServiceException, ParseException {
				
		Curriculum cv = curriculumService.newCurriculum(name, surname, dni, gender, nationality, city, address, birthday, phone, education, workexperience, language, skills);
		
		Client c = this.obtenerPorId(id);
		
		c.setCurriculum(cv);
		
		return clientRepository.save(c);
	}
		
	//MODIFICAR CLIENTE

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })//Transactional (se pone porque cambia algo en la base de datos)

	public Client modifyClient(String id, String email, String password,String password2, Photo photo) throws ServiceException {
		validar(email,password,password2);
		Optional<Client> respuesta = clientRepository.findById(id);
		if(respuesta.isPresent()) {
			Client p = respuesta.get();
			p.setEmail(email);
			String encript = new BCryptPasswordEncoder().encode(password); //ENCRIPTANDO PASSWORD
			p.setPassword(encript);
			//p.setCurriculum(curriculum);
			p.setPhoto(photo);
			p.setActive(true);
			return clientRepository.save(p);
		}else {
			throw new ServiceException("No se encontro el cliente solicitado");
		}	
	}
	
	//ELIMINAR CLIENTE

	public void delete(String id) throws ServiceException {
		Optional<Client> respuesta = clientRepository.findById(id);
		if(respuesta.isPresent()) {
		clientRepository.deleteById(id);
		}else {
			throw new ServiceException("No se encontro el cliente ");
		}
	}
	

	//DAR DE ALTA CLIENTE
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void lowCustomer(String id) {
		Optional<Client> respuesta = clientRepository.findById(id);
		if(respuesta.isPresent()) {
			Client cliente = respuesta.get();
			cliente.setActive(false);
		}
	}
	
	

	//DAR DE BAJA CLIENTE
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void hightCustomer(String id) {
		Optional<Client> respuesta = clientRepository.findById(id);
		if(respuesta.isPresent()) {
			Client cliente = respuesta.get();
			cliente.setActive(true);
		}
	}
	
	//OBTENER POR ID
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Client obtenerPorId(String id) throws ServiceException{
		
		Optional<Client> result  = clientRepository.findById(id);
		
		if (result.isEmpty()) {
			throw new ServiceException("No se encontró el cliente");
		} else {
			return result.get();
		}
	}
	
	//OBTENER POR MAIL
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Client obtenerPorMail(String email) throws ServiceException{
		
		return clientRepository.findByEmail(email);
	}
	
	//METODO VALIDACION
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
		if(clientRepository.existByEmail(email)) {
			throw new ServiceException("Ya existe un usuario registrado con ese email.");
		}
	}

	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		
		try {
			Client client = clientRepository.findByEmail(mail);
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			authorities.add(new SimpleGrantedAuthority("ROLE_" + client.getRol()));
			
			// Se extraen atributos de contexto del navegador -> INVESTIGAR
						ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

			// Se crea la sesion y se agrega el cliente a la misma -> FIUMBA
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("usersession", client);
			
			return new User(mail, client.getPassword(), authorities);
		} catch(Exception e) {
			
			throw new UsernameNotFoundException("El usuario no existe");
			
		}
		
		
		
		
	}
}
	
	

