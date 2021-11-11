package com.WorkMerge.services;

import java.text.ParseException;
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
import org.springframework.web.multipart.MultipartFile;

import com.WorkMerge.entities.Company;
import com.WorkMerge.entities.Job;
import com.WorkMerge.entities.Photo;
import com.WorkMerge.enums.Rol;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.repositories.CompanyRepository;
import com.WorkMerge.repositories.JobRepository;


@Service
public class CompanyService implements UserDetailsService {
	
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired

	private JobRepository jobRepository;
	@Autowired

	private PhotoService photoService;
	//CREATE (crear)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	//Photo photo,MultipartFile archive (CARGAR FOTO)
	public void newCompany(String email, String password, String password2)throws ServiceException {
		validate(email, password, password2);
		Company company = new Company();
		company.setRol(Rol.COMPANY);
		company.setActive(true);
		company.setEmail(email);
		String encript = new BCryptPasswordEncoder().encode(password);
		company.setPassword(encript);

		//photo = photoService.saved(archive); company.setPhoto(photo);
	    
		companyRepository.save(company);

	}
	
	//CARGAR NOMBRE COMPAÑIA
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })//Transactional (se pone porque cambia algo en la base de datos)
	public Company loadData(String id, String name) throws ServiceException, ParseException {
				
		Company c = this.obtenerPorId(id);
		
		c.setName(name);
		
		return companyRepository.save(c);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void uploadJobs(Company company, Job job) {
		Optional<Job> job2 = jobRepository.findById(job.getId());
		company.getJob().add(job2.get());
	}
	
	// UPDATE (actualizar)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void updateCompany(String id,String password, String password2, List<Job> job,Photo photo,MultipartFile archive,String email) throws ServiceException{
		validate(email,password, password2);
		Optional<Company> compy = companyRepository.findById(id);
		Company company = compy.get();
		String encript = new BCryptPasswordEncoder().encode(password);
		company.setPassword(encript);
		company.setJob(job);
		photo = photoService.saved(archive);
	    company.setPhoto(photo);
		companyRepository.save(company);
	}
	//DELETE
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void deleteCompany (String id,String password, List<Job> job,Photo photo,MultipartFile archive) throws ServiceException{
		Optional<Company> compy = companyRepository.findById(id);
		if (compy.isPresent()) {
	            Company company = compy.get();
	            companyRepository.delete(company);
	        } else {
	            throw new ServiceException("No se encontro la compañía.");
	        }
		
	}
	//DOWNGRADE (dar de baja)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void downgradeCompany (String id,String password, List<Job> job,Photo photo,MultipartFile archive)throws ServiceException{
		Optional<Company> compy = companyRepository.findById(id);
		if (compy.isPresent()) {
	            Company company = compy.get();
	            company.setActive(false);
	        } else {
	            throw new ServiceException("No se encontro la compañía.");
	        }
		
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Company obtenerPorId(String id) throws ServiceException{
		
		Optional<Company> result  = companyRepository.findById(id);
		
		if (result.isEmpty()) {
			throw new ServiceException("No se encontró el cliente");
		} else {
			return result.get();
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Company obtenerPorMail(String email) throws ServiceException{
		
		return companyRepository.findByEmail(email);
	}
	
	@Transactional(readOnly = true)
	public List<Job> listMyJobs(Company company){
		return company.getJob();
	}
	
	public void validate(String email,String password, String password2) throws ServiceException {
		if(email==null || email.isEmpty() || email.equals("")) {
			throw new ServiceException ("El email no puede ser nulo/vacío.");
		}
		if(password==null || password.isEmpty()||password.equals("")) {
			throw new ServiceException ("La contraseña no puede ser nula/vacía o 0.");
		}
		if(!password.equals(password2)){
			throw new ServiceException("La contraseñas tienen que coincidir");
		}
		if(companyRepository.existByEmail(email)) {
			throw new ServiceException("Ya existe una compañia con ese email.");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		
		try {
			Company company = companyRepository.findByEmail(mail);
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			authorities.add(new SimpleGrantedAuthority("ROLE_" + company.getRol()));
			
			return new User(mail, company.getPassword(), authorities);
		} catch(Exception e) {
			
			throw new UsernameNotFoundException("El admin no existe");
			
		}
	}
}
