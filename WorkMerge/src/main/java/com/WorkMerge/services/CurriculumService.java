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
	public Curriculum newCurriculum(String name, String surname, Integer dni, String gender, String nationality,
			String address, String city, Date birthday, Integer phone, String education, String workexperience,
			String language, String skills) throws ServiceException {
		//validate(name, surname, dni, gender, nationality, address, city, birthday, phone, education, workexperience,language, skills);
		Curriculum curriculum = new Curriculum();
		curriculum.setName(name);
		curriculum.setSurname(surname);
		Long dniLong = new Long(dni);
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
		curriculum.setBirthday(birthday);
		curriculum.setPhone(phone);
		curriculum.setEducation(education);
		curriculum.setWorkexperience(workexperience);
		curriculum.setLanguage(language);
		curriculum.setSkills(skills);
		return curriculumRepository.save(curriculum);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Curriculum updateCurriculum(String id, String name, String surname, Integer dni, String gender,
			String nationality, String address, String city, Date birthday, Integer phone, String education,
			String workexperience, String language, String skills) throws ServiceException {
		validate(name, surname, dni, gender, nationality, address, city, birthday, phone, education, workexperience,
				language, skills);
		Optional<Curriculum> respuesta = curriculumRepository.findById(id);
		if (respuesta.isPresent()) {
			Curriculum curriculum = respuesta.get();
			curriculum.setName(name);
			curriculum.setSurname(surname);
			Long dniLong = new Long(dni);
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
			curriculum.setBirthday(birthday);
			curriculum.setPhone(phone);
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
	

	private void validate(String name, String surname, Integer dni, String gender, String nationality, String address,
			String city, Date birthday, Integer phone, String education, String workexperience, String language,
			String skills) throws ServiceException {
		if (name == null || name.isEmpty()) {
			throw new ServiceException("El nombre no puede estar vacío o nulo.");
		}
		if (surname == null || surname.isEmpty()) {
			throw new ServiceException("El apellido no puede estar vacío o nulo.");
		}
		if (dni == null || dni == 0) {
			throw new ServiceException("El dni no puede estar vacío o nulo.");
		}
		if (gender == null) {
			throw new ServiceException("El género no puede ser nulo.");
		}
		if (nationality == null) {
			throw new ServiceException("La nacionalidad no puede ser nula.");
		}
		if (address == null || address.isEmpty()) {
			throw new ServiceException("La dirreción no puede estar vacía o nula.");
		}
		if (city == null) {
			throw new ServiceException("La ciudad no puede ser vacía o nula.");
		}
		if (birthday == null) {
			throw new ServiceException("La fecha de nacimiento no puede ser vacía o nula.");
		}
		if (phone == null || phone == 0) {
			throw new ServiceException("El telefono/celular no puede estar vacío o nulo.");
		}
		if (education == null || education.isEmpty()) {
			throw new ServiceException("La educación no puede ser vacía o nula.");
		}
		if (workexperience == null || workexperience.isEmpty()) {
			throw new ServiceException("La experiencia de trabajo no puede ser vacía o nula.");
		}
		if (language == null || language.isEmpty()) {
			throw new ServiceException("El lenguaje no puede ser vacío o nulo.");
		}
		if (skills == null || skills.isEmpty()) {
			throw new ServiceException("Las habilidades no pueden estar vacías o nulas.");
		}
	}
}
