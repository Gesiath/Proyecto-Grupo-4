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

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Curriculum newCurriculum(String name, String surname, String dni, String gender, String nationality,
			String address, String city, String birthday, String phone, String education, String workexperience,
			String language, String skills) throws ServiceException, ParseException {
		//validate(name, surname, dni, gender, nationality, address, city, birthday, phone, education, workexperience,language, skills);
		Curriculum curriculum = new Curriculum();
		curriculum.setName(name);
		curriculum.setSurname(surname);
		Long dniLong = Long.parseLong(dni);
		curriculum.setDni(dniLong);
		
		for (Gender genders : Gender.values()) {				
			if (gender.toString().equals(genders.toString())) {					
				curriculum.setGender(genders);
			}
		}
		
		for (Nationality nationalities : Nationality.values()) {				
			if (nationality.toString().equals(nationalities.toString())) {					
				curriculum.setNationality(nationalities);
			}
		}
		
		for (City cities : City.values()) {				
			if (city.toString().equals(cities.toString())) {					
				curriculum.setCity(cities);
			}
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

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Curriculum updateCurriculum(String id, String name, String surname, String dni, String gender,
			String nationality, String address, String city, String birthday, String phone, String education,
			String workexperience, String language, String skills) throws ServiceException, ParseException {
		//validate(name, surname, dni, gender, nationality, address, city, birthday, phone, education, workexperience,language, skills);
		Optional<Curriculum> respuesta = curriculumRepository.findById(id);
		if (respuesta.isPresent()) {
			Curriculum curriculum = respuesta.get();
			curriculum.setName(name);
			curriculum.setSurname(surname);
			Long dniLong = Long.parseLong(dni);
			curriculum.setDni(dniLong);			
			
			for (Gender genders : Gender.values()) {				
				if (gender.toString().equals(genders.toString())) {					
					curriculum.setGender(genders);
				}
			}
			
			for (Nationality nationalities : Nationality.values()) {				
				if (nationality.toString().equals(nationalities.toString())) {					
					curriculum.setNationality(nationalities);
				}
			}
			
			for (City cities : City.values()) {				
				if (city.toString().equals(cities.toString())) {					
					curriculum.setCity(cities);
				}
			}

			
			curriculum.setAddress(address);
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(birthday); 
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

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void deleteCurriculum(String id) throws ServiceException {
		Optional<Curriculum> respuesta = curriculumRepository.findById(id);
		if (respuesta.isPresent()) {
			curriculumRepository.deleteById(id);
		} else {
			throw new ServiceException("No se encontre el curriculum a borrar.");
		}
	}
	

}
