package com.guerreros.clases;

import com.guerreros.excepciones.OutOfBoundClanException;



public class Clan implements Localizable {
	private Integer id_clan = null;
	private String nombre_clan;
	private String pais;

	public Clan() {

	}

	public Clan(Integer idclan, String nombreclan, String Pais) {
		
	}

	

	public Integer getId_clan() {
		return id_clan;
	}

	public void setId_clan(Integer id_clan) throws OutOfBoundClanException {
		if(compruebaClan(id_clan)==false)
			throw new OutOfBoundClanException("Id del clan incorrecta");
		
		this.id_clan = id_clan;
	}
	protected static boolean compruebaClan(Integer idclan) {
		for (int i = 0; i < IDCLANES.length; i++) {
			if (i == IDCLANES[i])
				return true;
		}

		return false;
	}

	public String getNombre_clan() {
		return nombre_clan;
	}

	public void setNombre_clan(String nombre_clan) {
		this.nombre_clan = nombre_clan;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((id_clan == null) ? 0 : id_clan.hashCode());
		result = prime * result + ((nombre_clan == null) ? 0 : nombre_clan.hashCode());
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
		Clan other = (Clan) obj;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (id_clan == null) {
			if (other.id_clan != null)
				return false;
		} else if (!id_clan.equals(other.id_clan))
			return false;
		if (nombre_clan == null) {
			if (other.nombre_clan != null)
				return false;
		} else if (!nombre_clan.equals(other.nombre_clan))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Clan [id_clan=" + id_clan + ", nombre_clan=" + nombre_clan + ", Pais=" + pais + "]";
	}
	
	
}
