package com.WorkMerge.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.WorkMerge.enums.Rol;

@Entity
public class Client {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	private String id;
	@Enumerated(EnumType.STRING) // permite persistir la enumeración por su nombre, lo que significa que será una columna alfanumérica. 
	private Rol rol;
	private String email;
	private String password;
	@OneToOne
	private Curriculum curriculum;
	@OneToOne
	private Photo photo;
	private boolean active;	
	
	
	public Client() {
		
	}
	
	public Client(String id, Rol rol, String email, String password, Curriculum curriculum, Photo photo,
			boolean active) {
		super();
		this.id = id;
		this.rol = rol;
		this.email = email;
		this.password = password;
		this.curriculum = curriculum;
		this.photo = photo;
		this.active = active;
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
	public Curriculum getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
