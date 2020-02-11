package com.guerreros.mysqldao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.guerreros.clases.Localizacion;

import com.guerreros.dao.*;
import com.guerreros.excepciones.*;


public class MySQLLocalizacionDAO implements LocalizacionDAO, AutoCloseable {
	private Connection conn;

	final String SELECT = "SELECT * FROM `LOCALIZACION` WHERE PAIS = ?;";
	final String INSERT = "INSERT INTO `LOCALIZACION` VALUES (?,?)";
	final String UPDATE = "UPDATE `LOCALIZACION` SET   CONTINENTE = ? " + "WHERE PAIS = ?";
	final String DELETE = "DELETE FROM `LOCALIZACION` WHERE PAIS = ?;";
	final String SELECTALL = "SELECT * FROM `LOCALIZACION`;";

	public MySQLLocalizacionDAO(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void insertar(Localizacion x) throws DAOException {
		try (PreparedStatement pSTAT = conn.prepareStatement(INSERT);) {

			pSTAT.setString(1, x.getPais());
			pSTAT.setString(2, x.getContinente());
			

			if (pSTAT.executeUpdate() == 0) {
				throw new DAOException("Es posible que no se haya guardado, �revisalo!");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en sql insertar", e);
		}
		
	}

	@Override
	public void modificar(Localizacion x) throws DAOException {
		try (PreparedStatement pSTAT = conn.prepareStatement(UPDATE);) {

			
			pSTAT.setString(1, x.getContinente());
			pSTAT.setString(2, x.getPais());

			if (pSTAT.executeUpdate() == 0) {
				throw new DAOException("Es posible que no se haya guardado, �revisalo!");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en sql insertar", e);
		}
		
	}

	@Override
	public void eliminar(Localizacion x) throws DAOException {
		try (PreparedStatement pSTAT = conn.prepareStatement(DELETE); ResultSet rs = pSTAT.executeQuery()) {

			pSTAT.setString(1, x.getPais());

			if (pSTAT.executeUpdate() == 0) {
				throw new DAOException("Es posible que no se haya guardado, �revisalo!");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en sql buscar, clave mal");
		}

		
	}

	@Override
	public List<Localizacion> obtenerTodo() throws DAOException {
		List<Localizacion> paises = new ArrayList<>();
		try (PreparedStatement pSTAT = conn.prepareStatement(SELECTALL); ResultSet rs = pSTAT.executeQuery()) {

			while (rs.next()) {
				paises.add(convertir(rs));
			}

		} catch (SQLException e) {
			throw new DAOException("");
		} catch (OutOfBoundClanException e) {

			System.out.println("Error en el id clan ");
		} catch (InvalidLocalizacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paises;
	}

	@Override
	public Localizacion buscar(String clave) throws DAOException {
		Localizacion l = null;
		try (PreparedStatement pSTAT = conn.prepareStatement(SELECT); ResultSet rs = pSTAT.executeQuery()) {
			if (rs.next()) {
				l = convertir(rs);
			} else {
				throw new DAOException("No hay registros ");
			}

		} catch (SQLException e) {
			throw new DAOException("Error en sql buscar, clave mal");
		} catch (OutOfBoundClanException e) {

			System.out.println("Error en el id clan ");
		} catch (InvalidLocalizacionException e) {
			System.out.println("Localizacion incorrecta");
		}
		return l;
	}
	
	
	private Localizacion convertir(ResultSet rs) throws SQLException, OutOfBoundClanException, InvalidLocalizacionException {
		
		String pais = rs.getString("PAIS");
		String continente = rs.getString("CONTINENTE");

		Localizacion l = new Localizacion(pais, continente);
		return l;
	}
	
	@Override
	public void close() throws Exception {
		System.out.println("Cerrado");
		
	}

}
