package com.WorkMerge.enums;

public enum City {
	ACEBAL("Acebal"), ÁLVAREZ("Álvarez"), ALVEAR("Alvear"), ARROYO_SECO("Arroyo Seco"), 
	CARMEN_DEL_SAUCE("Carmen del Sauce"), CAPITÁN_BERMÚDEZ("Capitán Bermúdez"), CORONEL_DOMÍNGUEZ("Coronel Domínguez"), 
	EMPALME("Empalme"),VILLA_CONSTITUCIÓN("Villa Constitución"), FIGHIERA("Fighiera"), FRAY_LUIS_BELTRÁN("Fray Luis Beltrán"), 
	FUNES("Funes"), GENERAL_LAGOS("General Lagos"), GRANADERO_BAIGORRIA("Granadero Baigorria"), IBARLUCEA("Ibarlucea"), 
	PAVÓN("Pavón"), PÉREZ("Pérez"), PIÑERO("Piñero"), PUEBLO_ANDINO("Pueblo Andino"), PUEBLO_ESTHER("Pueblo Esther"), 
	RICARDONE("Ricardone"), ROSARIO("Rosario"), SAN_LORENZO("San Lorenzo"), SOLDINI("Soldini"), 
	VILLA_GOBERNADOR_GÁLVEZ("Villa Gobernador Gálvez"),ZAVALLA("Zavalla");
	
	private final String value;

	private City(String value){
        this.value = value;   
    }
	
	public String getValue() {
		return value;
	}	
	
}
