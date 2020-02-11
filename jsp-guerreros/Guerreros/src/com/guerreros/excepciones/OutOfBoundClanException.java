package com.guerreros.excepciones;

public class OutOfBoundClanException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutOfBoundClanException(String m) {
		super("Id del clan no existente (solo de 1 a 4)" + m);
	}

	public OutOfBoundClanException() {
		super("Id del clan no existente (solo de 1 a 4)");
	}

}
