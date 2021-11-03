package com.WorkMerge.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WorkMerge.entities.WorkExperience;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.repositories.WorkExperienceRepository;

@Service
public class WorkExperienceService {
	
	@Autowired
	private WorkExperienceRepository workExperienceRepository;
	
	public WorkExperience newWorkExperience(String title, String company, Date beginDate, Date finishDate, String description) throws ServiceException{
		
		/*Valido que los parametros traidos no sean null o vacios*/
		validation(title, company, beginDate, description);
		
		/*Creo el objeto*/
		WorkExperience workexperience = new WorkExperience();
		
		/*Le seteo los parametros traidos*/
		workexperience.setTitle(title);
		workexperience.setCompany(company);
		workexperience.setBeginDate(beginDate);
		workexperience.setFinishDate(finishDate);
		workexperience.setDescription(description);
		
		/*Guardo con el repositorio*/
		return workExperienceRepository.save(workexperience);
		
	}
	
	public void modifyEducation(String id, String title, String company, Date beginDate, Date finishDate, String description) throws ServiceException {
		
		/*Valido que los parametros traidos no sean null o vacios*/
		validation(title, company, beginDate, description);
		
		/*Uso optional para ver si el id para modificar existe*/
		Optional<WorkExperience> answer = workExperienceRepository.findById(id);
		
		/*Si existe procedo a modificarlo sino tiro un exception*/
		if(answer.isPresent()) {
			
			WorkExperience workexperience = answer.get();
			
			workexperience.setTitle(title);
			workexperience.setCompany(company);
			workexperience.setBeginDate(beginDate);
			workexperience.setFinishDate(finishDate);
			workexperience.setDescription(description);
			
			/*Guardo con el repositorio*/
			workExperienceRepository.save(workexperience);
			
		} else {
			throw new ServiceException("No se encontro la experiencia laboral a modificar.");
		}
		
	}
	
	public void deleteEducation(String id) throws ServiceException {

		/* Uso optional para ver si el id para borrar existe */
		Optional<WorkExperience> answer = workExperienceRepository.findById(id);

		/* Si existe procedo a borrarlo sino tiro un exception */
		if (answer.isPresent()) {
			workExperienceRepository.deleteById(id);
		} else {
			throw new ServiceException("No se encontro la experiencia laboral a borrar.");
		}

	}
	
	private void validation(String title, String company, Date beginDate, String description) throws ServiceException{
		
		if(title == null || title.isEmpty()) {
			throw new ServiceException("El titulo no puede estar vacio.");
		}
		
		if(company == null || company.isEmpty()) {
			throw new ServiceException("La compañia no puede estar vacia.");
		}
				
		if(beginDate == null) {
			throw new ServiceException("La fecha de inicio no puede estar vacia.");
		}
		
		if(description == null || description.isEmpty()) {
			throw new ServiceException("La descripcion no puede estar vacia.");
		}
	}
}
