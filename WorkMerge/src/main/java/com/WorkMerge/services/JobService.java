package com.WorkMerge.services;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.WorkMerge.entities.Job;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.repositories.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;

		
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Job newJob(String title, Date datepost, String availability, String category, String description,Integer salary, String experienceRequired) throws ServiceException{
		validate(title, datepost, availability, category, description, salary, experienceRequired);

		Job newJob = new Job();
		newJob.setTitle(title);
		newJob.setDatepost(datepost);
		newJob.setAvailability(availability);
		newJob.setCategory(category);
		newJob.setDescription(description);
		newJob.setSalary(salary);
		newJob.setExperienceRequired(experienceRequired);
		return jobRepository.save(newJob);
	}
	



	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Job updateJob(String id, String title, Date datepost, String availability, String category,
			String description, Integer salary, String experienceRequired) throws ServiceException {
		validate(title, datepost, availability, category, description, salary, experienceRequired);
		Optional<Job> respuesta = jobRepository.findById(id);
		if (respuesta.isPresent()) {
			Job job = respuesta.get();
			job.setTitle(title);
			job.setDatepost(datepost);
			job.setAvailability(availability);
			job.setCategory(category);
			job.setDescription(description);
			job.setSalary(salary);
			job.setExperienceRequired(experienceRequired);
			return jobRepository.save(job);
		} else {
			throw new ServiceException("No se encontro el trabajo a modificar.");
		}

	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void deleteJob(String id) throws ServiceException{
		Optional<Job> respuesta = jobRepository.findById(id);
		if (respuesta.isPresent()) {
			Job job = respuesta.get();
			jobRepository.delete(job);
		} else {
			throw new ServiceException("No se encontro el trabajo a eliminar.");
		}

	}
	//dar de alta trabajo
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void upgradeJob(String id) throws ServiceException{
	Optional<Job> respuesta = jobRepository.findById(id);
			if (respuesta.isPresent()) {
		            Job job = respuesta.get();
		            job.setActive(true);
		        } else {
		            throw new ServiceException("No se encontro el trabajo a activar.");
		        }
	}
	
	//dar de baja trabajo
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void downgradeJob(String id)  throws ServiceException{
		Optional<Job> respuesta = jobRepository.findById(id);
		if (respuesta.isPresent()) {
			Job job = respuesta.get();
			job.setActive(false);
		} else {
			throw new ServiceException("No se encontro el trabajo a desactivar.");
		}

	}
	
	//Validación
	public void validate(String title, Date datepost, String availability, String category, String description,Integer salary, String experienceRequired)throws ServiceException{
		if(title==null||title.isEmpty()) {
			throw new ServiceException("El título no puede estar vacío o nulo.");	
		}
		if(datepost==null) {
			throw new ServiceException("La fecha no puede estar vacía, eliga una fecha");	
		}
		if(availability==null|| availability.isEmpty()) {
			throw new ServiceException("La disponibilidad no puede estar vacía, eliga part-time, full-time.");	
		}
		if(category==null|| category.isEmpty()) {
			throw new ServiceException("La categoria no puede estar vacía o nula");	
		}
		if(description==null||description.isEmpty()) {
			throw new ServiceException("La descripción no puede estar vacía o nula");	
		}
		if(salary==null|| salary==0) {
			throw new ServiceException("El salario no puede ser nulo o 0");	
		}
		if(experienceRequired==null||experienceRequired.isEmpty()) {
			throw new ServiceException("La experencia del trabajo no puede ser nula o vacía.");	
		}
}
}

