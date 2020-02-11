package com.guerreros.clases;

import com.guerreros.excepciones.InvalidLocalizacionException;

//para tener los paises y continentes como constantes y bien ordenados
public class Localizacion implements Localizable {
	private String pais;
	private String continente;

	public Localizacion() {

	}

	public Localizacion(String pais, String continente) throws InvalidLocalizacionException {
		setPais(pais);
		setContinente(continente);
	}

	public boolean comparaPais(String pais) {
		pais = pais.toUpperCase();

		for (String elemento : PAISES) {

			if (pais.equals(elemento) == true)
				;
			return true;
		}
		return false;
	}

	public boolean comparaContinente(String continente) {
		continente = continente.toUpperCase();

		for (String elemento : CONTINENTES) {

			if (continente.equals(elemento) == true)
				;
			return true;
		}
		return false;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) throws InvalidLocalizacionException {
		if (comparaPais(pais) == false)
			throw new InvalidLocalizacionException("Pais erroneo");
		this.pais = pais;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) throws InvalidLocalizacionException {
		if (comparaContinente(continente) == false)
			throw new InvalidLocalizacionException("Continente erroneo");
		this.continente = continente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((continente == null) ? 0 : continente.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Localizacion other = (Localizacion) obj;
		if (continente == null) {
			if (other.continente != null)
				return false;
		} else if (!continente.equals(other.continente))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Localizacion [pais=" + pais + ", continente=" + continente + "]";
	}

}
