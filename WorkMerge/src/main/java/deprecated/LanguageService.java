package deprecated;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD:WorkMerge/src/main/java/com/WorkMerge/services/LanguageService.java

import com.WorkMerge.entities.Language;
import com.WorkMerge.enums.EnumLanguage;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.repositories.LanguageRepository;

=======
>>>>>>> b87dce88a3e79364a921fa28c6be4b6aa0512b8e:WorkMerge/src/main/java/deprecated/LanguageService.java
@Service
public class LanguageService {
	
	@Autowired
	private LanguageRepository languageRepository;
	
	public Language newLanguage(EnumLanguage enumlanguage) throws ServiceException {
		
		/*Valido que el parametro traido no sea null*/
		validation(enumlanguage);

		/*Creo el objeto*/
		Language language = new Language();
		
		/*Le seteo el parametro traido*/
		language.setName(enumlanguage);

		/*Guardo con el repositorio*/
		return languageRepository.save(language);

	}

	public void deleteLanguage(String id) throws ServiceException{
		
		/*Uso optional para ver si el id para borrar existe*/
		Optional<Language> answer = languageRepository.findById(id);
		
		/*Si existe procedo a borrarlo sino tiro un exception*/
		if(answer.isPresent()) {
			languageRepository.deleteById(id);
		} else {
			throw new ServiceException("No se encontro el lenguaje a borrar.");
		}
		
	}
	
	private void validation(EnumLanguage enumlanguage) throws ServiceException{
		
		/*Verifico que enum language no sea null*/
		if(enumlanguage == null) {
			throw new ServiceException("El lenguaje no puede ser nulo.");
		}
		
	}
}
