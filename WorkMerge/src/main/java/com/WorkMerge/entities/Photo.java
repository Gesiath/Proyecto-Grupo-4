package com.WorkMerge.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Photo {

@Id
@GeneratedValue(generator = "uuid")
@GenericGenerator(name="uuid",strategy = "uuid2")
private String id;
private String name;
private String mime; //El atributo mime asigna el formato del archivo de una foto
// Lob :identifica que el tipo de dato es pesado al motor de persistencia
// Basic: indica que cargue el contenido solo cuando lo pidamos
// fetch=FetchType.LAZY: indica que sea una carga liviana
@Lob @Basic(fetch=FetchType.LAZY)  
private byte[] content;


public String getId() {
    return id;
}


public void setId(String id) {
    this.id = id;
}


public String getName() {
    return name;
}


public void setName(String nombre) {
    this.name = nombre;
}


public String getMime() {
    return mime;
}


public void setMime(String mime) {
    this.mime = mime;
}


public byte[] getContent() {
    return content;
}


public void setContent(byte[] content) {
    this.content = content;
}
}