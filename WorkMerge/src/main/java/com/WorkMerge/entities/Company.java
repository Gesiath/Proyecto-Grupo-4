package com.WorkMerge.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.WorkMerge.enums.Rol;

@Entity
public class Company {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	private String id;
	@Enumerated(EnumType.STRING)
	private Rol rol;
	private String email;
	private String password;
	@OneToMany
	private List<Job> job;
	private boolean active;
	@OneToOne
	private Photo photo;
	
	public Company() {
		
	}
	
	public Company(String id, Rol rol, String email, String password, List<Job> job, boolean active, Photo photo) {
		super();
		this.id = id;
		this.rol = rol;
		this.email = email;
		this.password = password;
		this.job = job;
		this.active = active;
		this.photo = photo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Job> getJob() {
		return job;
	}

	public void setJob(List<Job> job) {
		this.job = job;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	
}
