package com.WorkMerge.services;

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
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Curriculum newCurriculum(String name,String surname,Long dni,Gender gender,Nationality nationality,String address,City city,Date birthday,Integer phone,String education, String workexperience,String language,String skills)throws ServiceException {
	validate(name, surname, dni, gender, nationality, address, city, birthday, phone, education, workexperience, language, skills);
	Curriculum curriculum = new Curriculum();
	curriculum.setName(name);
	curriculum.setSurname(surname);
	curriculum.setDni(dni);
	curriculum.setGender(gender);
	curriculum.setNationality(nationality);
	curriculum.setAddress(address);
	curriculum.setCity(city);
	curriculum.setBirthday(birthday);
	curriculum.setPhone(phone);
	curriculum.setEducation(education);
	curriculum.setWorkexperience(workexperience);
	curriculum.setLanguage(language);
	curriculum.setSkills(skills);
	return curriculumRepository.save(curriculum);
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Curriculum updateCurriculum (String id,String name,String surname,Long dni,Gender gender,Nationality nationality,String address,City city,Date birthday,Integer phone,String education, String workexperience,String language,String skills)throws ServiceException {
	validate(name, surname, dni, gender, nationality, address, city, birthday, phone, education, workexperience, language, skills);
	Optional<Curriculum>respuesta= curriculumRepository.findById(id);
	if(respuesta.isPresent()) {
		Curriculum curriculum = respuesta.get();
		curriculum.setName(name);
		curriculum.setSurname(surname);
		curriculum.setDni(dni);
		curriculum.setGender(gender);
		curriculum.setNationality(nationality);
		curriculum.setAddress(address);
		curriculum.setCity(city);
		curriculum.setBirthday(birthday);
		curriculum.setPhone(phone);
		curriculum.setEducation(education);
		curriculum.setWorkexperience(workexperience);
		curriculum.setLanguage(language);
		curriculum.setSkills(skills);
		return curriculumRepository.save(curriculum);
		
	}else {
		throw new ServiceException("No se encontro el curriculum a modificar.");
	}
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void deleteCurriculum(String id) throws ServiceException{
		Optional<Curriculum>respuesta= curriculumRepository.findById(id);
		if(respuesta.isPresent()) {
			curriculumRepository.deleteById(id);
		}else {
			throw new ServiceException("No se encontre el curriculum a borrar.");
		}
	}
	private void validate(String name,String surname,Long dni,Gender gender,Nationality nationality,String address,City city,Date birthday,Integer phone,String education, String workexperience,String language,String skills)throws ServiceException {
		if(name==null||name.isEmpty()) {
			throw new ServiceException("El nombre no puede estar vacío o nulo.");	
		}
		if(surname==null||surname.isEmpty()) {
			throw new ServiceException("El nombre no puede estar vacío o nulo.");	
		}
		if(dni==null||dni==0) {
			throw new ServiceException("El dni no puede estar vacío o nulo.");	
		}
		if(gender==null) {
			throw new ServiceException("El género no puede estar nulo.");	
		}
		if(nationality==null) {
			throw new ServiceException("La nacionalidad no puede estar nulo.");	
		}
		if(address==null||address.isEmpty()) {
			throw new ServiceException("La dirreción no puede estar vacío o nulo.");	
		}
		if(city==null) {
			throw new ServiceException("La ciudad no puede ser vacío o nulo.");	
		}
		if(birthday==null){
			throw new ServiceException("La fecha de nacimiento no puede ser vacío o nulo.");	
		}
		if(phone==null||phone== 0) {
			throw new ServiceException("El telefono/celular no puede estar vacío o nulo.");	
		}
		if(education==null||education.isEmpty()) {
			throw new ServiceException("La educación no puede ser vacío o nulo.");	
		}
		if(workexperience==null||workexperience.isEmpty()) {
			throw new ServiceException("La experiencia de trabajo no puede ser vacío o nulo.");	
		}
		if(language==null||language.isEmpty()) {
			throw new ServiceException("El lenguaje no puede estar vacío o nulo.");	
		}
		if(skills==null||skills.isEmpty()) {
			throw new ServiceException("Las habilidades no pueden ser vacías o nulas.");	
		}
	}
}
