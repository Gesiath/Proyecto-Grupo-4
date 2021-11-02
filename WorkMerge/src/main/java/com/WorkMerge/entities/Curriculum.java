package com.WorkMerge.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.WorkMerge.enums.City;
import com.WorkMerge.enums.Gender;
import com.WorkMerge.enums.Nationality;

@Entity
public class Curriculum {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	private String id;
	private String name;
	private String surname;
	private Long dni;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Enumerated(EnumType.STRING)
	private Nationality nationality;
	private String address;
	@Enumerated(EnumType.STRING)
	private City city;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	private Integer phone;
	@OneToMany(mappedBy = "curriculum")
	private List<Education> education;
	@OneToMany
	private List<WorkExperience> workexperience;
	@OneToMany
	private List<Language> language;
	private String skills;
	
	public Curriculum() {
		
	}

	public Curriculum(String id, String name, String surname, Long dni, Gender gender, Nationality nationality,
			String address, City city, Date birthday, Integer phone, List<Education> education,
			List<WorkExperience> workexperience, List<Language> language, String skills) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.gender = gender;
		this.nationality = nationality;
		this.address = address;
		this.city = city;
		this.birthday = birthday;
		this.phone = phone;
		this.education = education;
		this.workexperience = workexperience;
		this.language = language;
		this.skills = skills;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}

	public List<WorkExperience> getWorkexperience() {
		return workexperience;
	}

	public void setWorkexperience(List<WorkExperience> workexperience) {
		this.workexperience = workexperience;
	}

	public List<Language> getLanguage() {
		return language;
	}

	public void setLanguage(List<Language> language) {
		this.language = language;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
	

}
