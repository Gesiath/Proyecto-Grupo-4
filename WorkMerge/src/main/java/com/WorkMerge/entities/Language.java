package com.WorkMerge.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.WorkMerge.enums.EnumLanguage;

@Entity
public class Language {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	private String id;
	@Enumerated(EnumType.STRING)
	private EnumLanguage language ;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EnumLanguage getName() {
		return language;
	}

	public void setName(EnumLanguage name) {
		this.language = name;
	}
}
