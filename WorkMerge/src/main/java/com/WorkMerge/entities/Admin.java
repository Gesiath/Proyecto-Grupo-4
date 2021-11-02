package com.WorkMerge.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.WorkMerge.enums.Rol;

@Entity
public class Admin {

@Id
@GeneratedValue(generator = "uuid")
@GenericGenerator(name="uuid",strategy = "uuid2")
private String id;
@Enumerated(EnumType.STRING)
private Rol rol;
private String email;
private String password;
private String nickname;

public Admin() {
	
}

public Admin(String id, Rol rol, String email, String password, String nickname) {
	super();
	this.id = id;
	this.rol = rol;
	this.email = email;
	this.password = password;
	this.nickname = nickname;
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
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}


}
