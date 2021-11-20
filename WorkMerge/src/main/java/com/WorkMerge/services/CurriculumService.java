package com.WorkMerge.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.WorkMerge.entities.Curriculum;
import com.WorkMerge.enums.City;
import com.WorkMerge.enums.Gender;
import com.WorkMerge.enums.Nationality;
import com.WorkMerge.exceptions.ServiceException;
import com.WorkMerge.repositories.CurriculumRepository;

@Service
public class CurriculumService {

	@Autowired
	private CurriculumRepository curriculumRepository;
	
	//CREAR NUEVO CV
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Curriculum newCurriculum(String name, String surname, String dni, String gender, String nationality,
			String address, String city, String birthday, String phone, String education, String workexperience,
			String language, String skills) throws ServiceException, ParseException {
		
		validate(name, surname, dni, gender, nationality, address, city, birthday, phone, education, workexperience,language, skills);
		
		Curriculum curriculum = new Curriculum();
		curriculum.setName(name);
		curriculum.setSurname(surname);
		Long dniLong = Long.parseLong(dni);
		curriculum.setDni(dniLong);
		if (gender.equalsIgnoreCase("female")) {
			curriculum.setGender(Gender.FEMALE);
		} else if (gender.equalsIgnoreCase("male")) {
			curriculum.setGender(Gender.MALE);
		} else if (gender.equalsIgnoreCase("nobinary")) {
			curriculum.setGender(Gender.NOBINARY);
		}

		switch (nationality) {
		case "ARGENTINA":
			curriculum.setNationality(Nationality.ARGENTINA);
			break;
		case "CHILE":
			curriculum.setNationality(Nationality.CHILE);
			break;
		case "PERU":
			curriculum.setNationality(Nationality.PERU);
			break;
		case "URUGUAY":
			curriculum.setNationality(Nationality.URUGUAY);
			break;
		case "BOLIVIA":
			curriculum.setNationality(Nationality.BOLIVIA);
			break;
		case "BRASIL":
			curriculum.setNationality(Nationality.BRASIL);
			break;
		case "PARAGUAY":
			curriculum.setNationality(Nationality.PARAGUAY);
			break;
		}

		switch (city) {
		case "ROSARIO":
			curriculum.setCity(City.ROSARIO);
			break;
		case "BAIGORRIA":
			curriculum.setCity(City.GRANADERO_BAIGORRIA);
			break;
		case "VGG":
			curriculum.setCity(City.VILLA_GOBERNADOR_GÁLVEZ);
			break;
		case "ACEBAL":
			curriculum.setCity(City.ACEBAL);
			break;
		case "ARROYOSECO":
			curriculum.setCity(City.ARROYO_SECO);
			break;
		case "FUNES":
			curriculum.setCity(City.FUNES);
			break;
		case "PUEBLOESTHER":
			curriculum.setCity(City.PUEBLO_ESTHER);
			break;
		}

		curriculum.setAddress(address);
		 Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(birthday); 
		curriculum.setBirthday(date1);
		Long phoneInt = Long.parseLong(phone);
		curriculum.setPhone(phoneInt);
		curriculum.setEducation(education);
		curriculum.setWorkexperience(workexperience);
		curriculum.setLanguage(language);
		curriculum.setSkills(skills);
		return curriculumRepository.save(curriculum);
	}
	
	//EDITAR CV
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Curriculum updateCurriculum(String id, String name, String surname, String dni, String gender,
			String nationality, String address, String city, String birthday, String phone, String education,
			String workexperience, String language, String skills) throws ServiceException, ParseException {
		
		validate(name, surname, dni, gender, nationality, address, city, birthday, phone, education, workexperience,language, skills);
		
		Optional<Curriculum> respuesta = curriculumRepository.findById(id);
		if (respuesta.isPresent()) {
			Curriculum curriculum = respuesta.get();
			curriculum.setName(name);
			curriculum.setSurname(surname);
			Long dniLong = Long.parseLong(dni);
			curriculum.setDni(dniLong);
			if (gender.equalsIgnoreCase("female")) {
				curriculum.setGender(Gender.FEMALE);
			} else if (gender.equalsIgnoreCase("male")) {
				curriculum.setGender(Gender.MALE);
			} else if (gender.equalsIgnoreCase("nobinary")) {
				curriculum.setGender(Gender.NOBINARY);
			}

			switch (nationality) {
			case "ARGENTINA":
				curriculum.setNationality(Nationality.ARGENTINA);
				break;
			case "CHILE":
				curriculum.setNationality(Nationality.CHILE);
				break;
			case "PERU":
				curriculum.setNationality(Nationality.PERU);
				break;
			case "URUGUAY":
				curriculum.setNationality(Nationality.URUGUAY);
				break;
			case "BOLIVIA":
				curriculum.setNationality(Nationality.BOLIVIA);
				break;
			case "BRASIL":
				curriculum.setNationality(Nationality.BRASIL);
				break;
			case "PARAGUAY":
				curriculum.setNationality(Nationality.PARAGUAY);
				break;
			}

			switch (city) {
			case "ROSARIO":
				curriculum.setCity(City.ROSARIO);
				break;
			case "BAIGORRIA":
				curriculum.setCity(City.GRANADERO_BAIGORRIA);
				break;
			case "VGG":
				curriculum.setCity(City.VILLA_GOBERNADOR_GÁLVEZ);
				break;
			case "ACEBAL":
				curriculum.setCity(City.ACEBAL);
				break;
			case "ARROYOSECO":
				curriculum.setCity(City.ARROYO_SECO);
				break;
			case "FUNES":
				curriculum.setCity(City.FUNES);
				break;
			case "PUEBLOESTHER":
				curriculum.setCity(City.PUEBLO_ESTHER);
				break;
			}
			curriculum.setAddress(address);
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(birthday); 
			curriculum.setBirthday(date1);
			Long phoneLong = Long.parseLong(phone);
			curriculum.setPhone(phoneLong);
			curriculum.setEducation(education);
			curriculum.setWorkexperience(workexperience);
			curriculum.setLanguage(language);
			curriculum.setSkills(skills);
			return curriculumRepository.save(curriculum);

		} else {
			throw new ServiceException("No se encontro el curriculum a modificar.");
		}
	}

	//OBETER COMPAÑIA POR ID
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Curriculum obtenerPorId(String id) throws ServiceException{
		
		Optional<Curriculum> result  = curriculumRepository.findById(id);
		
		if (result.isEmpty()) {
			throw new ServiceException("No se encontró la compañia");
		} else {
			return result.get();
		}
	}	
	
	//ELIMINAR CLIENTE
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void deleteCurriculum(String id) throws ServiceException {
		Optional<Curriculum> respuesta = curriculumRepository.findById(id);
		if (respuesta.isPresent()) {
			curriculumRepository.deleteById(id);
		} else {
			throw new ServiceException("No se encontre el curriculum a borrar.");
		}
	}
	
	//VALIDAR DATOS
	private void validate(String name, String surname, String dni, String gender, String nationality, String address,
			String city, String birthday, String phone, String education, String workexperience, String language,
			String skills) throws ServiceException {
		
		if(name==null || name.isEmpty() || name.equals("")) {
			throw new ServiceException ("El nombre no puede ser nulo/vacío.");
		}
		
		if(surname==null || surname.isEmpty() || surname.equals("")) {
			throw new ServiceException ("El apellido no puede ser nulo/vacío.");
		}
		
		if(dni==null || dni.isEmpty() || dni.equals("")) {
			throw new ServiceException ("El dni no puede ser nulo/vacío.");
		}
		
		if(gender==null || gender.isEmpty() || gender.equals("")) {
			throw new ServiceException ("El genero no puede ser nulo/vacío.");
		}
		
		if(nationality==null || nationality.isEmpty() || nationality.equals("")) {
			throw new ServiceException ("La nacionalidad no puede ser nula/vacía.");
		}
		
		if(address==null || address.isEmpty() || address.equals("")) {
			throw new ServiceException ("La dirección no puede ser nula/vacía.");
		}
		
		if(city==null || city.isEmpty() || city.equals("")) {
			throw new ServiceException ("la ciudad no puede ser nula/vacía.");
		}
		
		if(birthday==null || birthday.isEmpty() || birthday.equals("")) {
			throw new ServiceException ("La fecha de nacimiento no puede ser nula/vacía.");
		}
		
		if(phone==null || phone.isEmpty() || phone.equals("")) {
			throw new ServiceException ("El telefono no puede ser nulo/vacío.");
		}
		
		if(education==null || education.isEmpty() || education.equals("")) {
			throw new ServiceException ("La educación no puede ser nula/vacía.");
		}
		
		if(workexperience==null || workexperience.isEmpty() || workexperience.equals("")) {
			throw new ServiceException ("La experiencia laborarl no puede ser nula/vacía.");
		}
		
		if(language==null || language.isEmpty() || language.equals("")) {
			throw new ServiceException ("El lenguaje no puede ser nulo/vacío.");
		}
		
		if(skills==null || skills.isEmpty() || skills.equals("")) {
			throw new ServiceException ("Las habilidades no puede ser nulas/vacías.");
		}
		
	}
	

}
