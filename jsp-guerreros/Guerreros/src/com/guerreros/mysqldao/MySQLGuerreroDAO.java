package com.guerreros.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.guerreros.clases.Guerrero;
import com.guerreros.principal.Conexion;

import com.guerreros.dao.*;
import com.guerreros.excepciones.*;

public class MySQLGuerreroDAO implements GuerreroDAO, AutoCloseable {
	private Connection conn;

	final String SELECT = "SELECT * FROM `GUERRERO` WHERE ID_GUERRERO = ?;";
	final String INSERT = "INSERT INTO `GUERRERO` VALUES (?,?,?,?)";
	final String UPDATE = "UPDATE `GUERRERO` SET  ID_CLAN = ?, NOMBRE_GUERRERO = ?, EDAD= ? " + "WHERE ID_GUERRERO = ?";
	final String DELETE = "DELETE FROM `GUERRERO` WHERE ID_GUERRERO = ?;";
	final String SELECTALL = "SELECT * FROM `GUERRERO`;";

	public MySQLGuerreroDAO(Connection conn) {

		this.conn = conn;
	}

	@Override
	public void insertar(Guerrero x) throws DAOException {

		try (PreparedStatement pSTAT = conn.prepareStatement(INSERT);) {

			pSTAT.setInt(1, x.getId_clan());
			pSTAT.setString(2, x.getNombre_guerrero());
			pSTAT.setInt(3, x.getEdad());
			pSTAT.setInt(4, x.getId_guerrero());

			if (pSTAT.executeUpdate() == 0) {
				throw new DAOException("Es posible que no se haya guardado, �revisalo!");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en sql insertar", e);
		}

	}

	@Override
	public void modificar(Guerrero x) throws DAOException {
		try (PreparedStatement pSTAT = conn.prepareStatement(UPDATE);) {

			pSTAT.setInt(1, x.getId_guerrero());
			pSTAT.setInt(2, x.getId_clan());
			pSTAT.setString(3, x.getNombre_guerrero());
			pSTAT.setInt(4, x.getEdad());
			if (pSTAT.executeUpdate() == 0) {
				throw new DAOException("Es posible que no se haya guardado, �revisalo!");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en sql insertar", e);
		}

	}

	@Override
	public void eliminar(Guerrero x) throws DAOException {

		try (PreparedStatement pSTAT = conn.prepareStatement(DELETE); ResultSet rs = pSTAT.executeQuery()) {

			pSTAT.setInt(1, x.getId_guerrero());

			if (pSTAT.executeUpdate() == 0) {
				throw new DAOException("Es posible que no se haya guardado, �revisalo!");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en sql buscar, clave mal");
		}

	}

	@Override
	public List<Guerrero> obtenerTodo() throws DAOException {
		List<Guerrero> gs = new ArrayList<>();
		try (PreparedStatement pSTAT = conn.prepareStatement(SELECTALL);) {
			
			
			try( ResultSet rs = pSTAT.executeQuery()){
			while (rs.next()) {
				gs.add(convertir(rs));
			}
			}
			
		} catch (SQLException e) {
			throw new DAOException("");
		} catch (OutOfBoundClanException e) {

			System.out.println("Error en el id clan ");
		}
		return gs;
	}

	@Override
	public Guerrero buscar(Integer clave) throws DAOException {
		Guerrero g = null;
		
		
		try (PreparedStatement pSTAT = conn.prepareStatement(SELECT);) {
			pSTAT.setInt(1, clave);
			
			try( ResultSet rs = pSTAT.executeQuery();){
			if (rs.next()) {
				g = convertir(rs);
			} else {
				throw new DAOException("No hay registros ");
			}
			}
			
		} catch (SQLException e) {
			throw new DAOException("Error en sql buscar, clave mal");
		} catch (OutOfBoundClanException e) {

			System.out.println("Error en el id clan ");
		}
		return g;
	}

	private Guerrero convertir(ResultSet rs) throws SQLException, OutOfBoundClanException {
		Integer id_guerrero = rs.getInt("ID_GUERRERO");
		Integer id_clan = rs.getInt("ID_CLAN");
		String nombre = rs.getString("NOMBRE_GUERRERO");
		Integer edad = rs.getInt("EDAD");
		Guerrero g = new Guerrero(id_guerrero, id_clan, nombre, edad);
		return g;
	}

	// Implementado de la interfaz autocloseable
	@Override
	public void close() throws Exception {
		System.out.println("Cerrado");
	}

}
