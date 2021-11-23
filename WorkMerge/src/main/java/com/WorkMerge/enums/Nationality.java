package com.WorkMerge.enums;

public enum Nationality {
ARGENTINA("Argentina"),BOLIVIA("Bolivia"),BRASIL("Brasil"),CHILE("Chile"), PERU("Peru"), PARAGUAY("Paraguay"), URUGUAY("Uruguay");
	
	private final String value;

	private Nationality(String value){
        this.value = value;   
    }
	
	public String getValue() {
		return value;
	}	
}
