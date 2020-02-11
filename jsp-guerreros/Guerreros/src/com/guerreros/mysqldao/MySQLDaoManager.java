package com.guerreros.mysqldao;

import java.sql.Connection;

import com.guerreros.principal.Conexion;

import com.guerreros.dao.*;


public class MySQLDaoManager implements DAOManager {
	private Connection conn;
	private Conexion conexion=new Conexion();
	private ClanDAO clan =null;
	private GuerreroDAO guerrero=null;
	private LocalizacionDAO localizacion=null;
	
	public MySQLDaoManager(Connection conn) {	
		this.conn = conexion.getJdbcConnection();
	}

	@Override
	public ClanDAO getClanDAO() {
		if(clan==null) {
			clan = new MySQLClanDAO(conn);
		}
		return clan;
	}

	@Override
	public GuerreroDAO getGuerreroDAO() {
		if(guerrero==null) {
			guerrero = new MySQLGuerreroDAO(conn);
		}
		return guerrero;
	}

	@Override
	public LocalizacionDAO getLocalizacionDAO() {
		if(localizacion==null) {
			localizacion = new MySQLLocalizacionDAO(conn);
		}
		return localizacion;
	}
	
	
	
}
