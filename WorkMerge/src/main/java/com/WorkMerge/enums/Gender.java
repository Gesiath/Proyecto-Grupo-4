package com.WorkMerge.enums;

public enum Gender {
	FEMALE("Femenino"),MALE("Masculino"),NOBINARY("No binario");
	
	private final String value;

	private Gender(String value){
        this.value = value;   
    }
	
	public String getValue() {
		return value;
	}	
	
}
