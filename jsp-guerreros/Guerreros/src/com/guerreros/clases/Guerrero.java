package com.guerreros.clases;

import com.guerreros.excepciones.OutOfBoundClanException;


public class Guerrero implements Localizable{
	private Integer id_guerrero = null;
	private Integer id_clan;
	private String nombre_guerrero;
	private Integer edad;

	public Guerrero() {

	}

	public Guerrero(Integer id, Integer idclan, String nombre, Integer edad) throws OutOfBoundClanException {
		this.id_guerrero=id;
		setId_clan(idclan);
		this.nombre_guerrero=nombre;
		this.edad=edad;
	}

	public Integer getId_guerrero() {
		return id_guerrero;
	}

	public void setId_guerrero(Integer id_guerrero) {
		this.id_guerrero = id_guerrero;
	}

	public Integer getId_clan() {
		return id_clan;
	}

	public void setId_clan(Integer id_clan) throws OutOfBoundClanException {
		if(Clan.compruebaClan(id_clan)==false) {
			throw new OutOfBoundClanException();
		}
		else
		this.id_clan = id_clan;
	}

	public String getNombre_guerrero() {
		return nombre_guerrero;
	}

	public void setNombre_guerrero(String nombre_guerrero) {
		this.nombre_guerrero = nombre_guerrero;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edad == null) ? 0 : edad.hashCode());
		result = prime * result + ((id_clan == null) ? 0 : id_clan.hashCode());
		result = prime * result + ((id_guerrero == null) ? 0 : id_guerrero.hashCode());
		result = prime * result + ((nombre_guerrero == null) ? 0 : nombre_guerrero.hashCode());
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
		Guerrero other = (Guerrero) obj;
		if (edad == null) {
			if (other.edad != null)
				return false;
		} else if (!edad.equals(other.edad))
			return false;
		if (id_clan == null) {
			if (other.id_clan != null)
				return false;
		} else if (!id_clan.equals(other.id_clan))
			return false;
		if (id_guerrero == null) {
			if (other.id_guerrero != null)
				return false;
		} else if (!id_guerrero.equals(other.id_guerrero))
			return false;
		if (nombre_guerrero == null) {
			if (other.nombre_guerrero != null)
				return false;
		} else if (!nombre_guerrero.equals(other.nombre_guerrero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Guerrero [id_guerrero=" + id_guerrero + ", id_clan=" + id_clan + ", nombre_guerrero=" + nombre_guerrero
				+ ", edad=" + edad + "]";
	}
	
	
	
	
}
