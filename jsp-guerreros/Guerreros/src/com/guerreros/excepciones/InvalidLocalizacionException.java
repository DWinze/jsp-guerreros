package com.guerreros.excepciones;

public class InvalidLocalizacionException extends Exception {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidLocalizacionException(){
		super("Error en la localizacion");
		
		}
	
	public InvalidLocalizacionException(String m){
		super("Error en la localizacion" + m);
	}
}
