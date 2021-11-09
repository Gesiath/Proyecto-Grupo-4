package deprecated;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import deprecated.Education;

import com.WorkMerge.exceptions.ServiceException;
import deprecated.EducationRepository;

@Service
public class EducationService {
	
	@Autowired
	private EducationRepository educationRepository;
	
	public Education newEducation(String title, String institute, TypeEducation type, Date beginDate, Date finishDate, String description) throws ServiceException {
		
		/*Valido que los parametros traidos no sean null o vacios*/
		validation(title, institute, type, beginDate, description);
		
		/*Creo el objeto*/
		Education education = new Education();
		
		/*Le seteo los parametros traidos*/
		education.setTitle(title);
		education.setInstitute(institute);
		education.setType(type);
		education.setBeginDate(beginDate);
		education.setFinishDate(finishDate);
		education.setFinishDate(finishDate);
		education.setDescription(description);
		
		/*Guardo con el repositorio*/
		return educationRepository.save(education);
	}
	
	public void modifyEducation(String id, String title, String institute, TypeEducation type, Date beginDate, Date finishDate, String description) throws ServiceException {
		
		/*Valido que los parametros traidos no sean null o vacios*/
		validation(title, institute, type, beginDate, description);
		
		/*Uso optional para ver si el id para modificar existe*/
		Optional<Education> answer = educationRepository.findById(id);
		
		/*Si existe procedo a modificarlo sino tiro un exception*/
		if(answer.isPresent()) {
			
			Education education = answer.get();
			
			education.setTitle(title);
			education.setInstitute(institute);
			education.setType(type);
			education.setBeginDate(beginDate);
			education.setFinishDate(finishDate);
			education.setFinishDate(finishDate);
			education.setDescription(description);
			
			/*Guardo con el repositorio*/
			educationRepository.save(education);
		} else {
			throw new ServiceException("No se encontro la educacion a modificar.");
		}
		
	}
	
	public void deleteEducation(String id) throws ServiceException {

		/* Uso optional para ver si el id para borrar existe */
		Optional<Education> answer = educationRepository.findById(id);

		/* Si existe procedo a borrarlo sino tiro un exception */
		if (answer.isPresent()) {
			educationRepository.deleteById(id);
		} else {
			throw new ServiceException("No se encontro la educacion a borrar.");
		}

	}
		
	private void validation(String title, String institute, TypeEducation type, Date beginDate, String description) throws ServiceException{
		
		if(title == null || title.isEmpty()) {
			throw new ServiceException("El titulo no puede estar vacio.");
		}
		
		if(institute == null || institute.isEmpty()) {
			throw new ServiceException("El instituto no puede estar vacio.");
		}
		
		if(type == null) {
			throw new ServiceException("El tipo de educacion no puede ser nulo.");
		}
		
		if(beginDate == null) {
			throw new ServiceException("La fecha de inicio no puede estar vacia.");
		}
		
		if(description == null || description.isEmpty()) {
			throw new ServiceException("La descripcion no puede estar vacia.");
		}
	}
}
