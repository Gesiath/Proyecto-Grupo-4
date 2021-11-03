package com.WorkMerge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.WorkMerge.entities.Company;
import com.WorkMerge.entities.Job;
import com.WorkMerge.entities.Photo;
import com.WorkMerge.enums.Rol;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.repositories.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private PhotoService photoService;
	//CREATE
	@Transactional
	public void newCompany(String email, String password, List<Job> job,Photo photo,MultipartFile archive)throws ServiceException {
		Company company = new Company();
		company.setRol(Rol.COMPANY);
		company.setActive(true);
		company.setEmail(email);
		String encript = new BCryptPasswordEncoder().encode(password);
		company.setPassword(encript);
		company.setJob(job);
		photo = photoService.saved(archive);
	    company.setPhoto(photo);
		companyRepository.save(company);
	}
	// UPDATE
	@Transactional
	public void updateCompany(String id,String password, List<Job> job,Photo photo,MultipartFile archive,String email) throws ServiceException{
		validate(email,password);
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
	@Transactional
	public void deleteCompany (String id,String password, List<Job> job,Photo photo,MultipartFile archive) throws ServiceException{
		Optional<Company> compy = companyRepository.findById(id);
		if (compy.isPresent()) {
	            Company company = compy.get();
	            companyRepository.delete(company);
	        } else {
	            throw new Error("No se encontro la compañía.");
	        }
		
	}
	//DOWNGRADE
	@Transactional
	public void downgradeCompany (String id,String password, List<Job> job,Photo photo,MultipartFile archive)throws ServiceException{
		Optional<Company> compy = companyRepository.findById(id);
		if (compy.isPresent()) {
	            Company company = compy.get();
	            company.setActive(false);
	        } else {
	            throw new Error("No se encontro la compañía.");
	        }
		
	}
	public void validate(String email,String password) throws ServiceException {
		if(email==null || email.isEmpty() || email.equals("")) {
			throw new ServiceException ("El email no puede ser nulo/vacío.");
		}
		if(password==null || password.isEmpty()||password.equals("")) {
			throw new ServiceException ("La contraseña no puede ser nula/vacía o 0.");
		}

	}
}
