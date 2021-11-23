package com.WorkMerge.enums;

public enum Gender {
	FEMENINO("Femenino"),MASCULINO("Masculino"),NOBINARIO("No binario");
	
	private final String value;

	private Gender(String value){
        this.value = value;   
    }
	
	public String getValue() {
		return value;
	}	
	
}
