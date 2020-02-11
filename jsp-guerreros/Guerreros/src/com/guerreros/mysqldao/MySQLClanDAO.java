package com.guerreros.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.guerreros.clases.Clan;

import com.guerreros.dao.*;
import com.guerreros.excepciones.*;


public class MySQLClanDAO implements ClanDAO, AutoCloseable {
	private Connection conn;

	final String SELECT = "SELECT * FROM `CLAN` WHERE ID_CLAN = ?;";
	final String INSERT = "INSERT INTO `CLAN` VALUES (?,?,?)";
	final String UPDATE = "UPDATE `CLAN` SET   NOMBRE_CLAN = ?, PAIS= ? " + "WHERE ID_CLAN = ?";
	final String DELETE = "DELETE FROM `CLAN` WHERE ID_CLAN = ?;";
	final String SELECTALL = "SELECT * FROM `CLAN`;";

	public MySQLClanDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insertar(Clan x) throws DAOException {
		try (PreparedStatement pSTAT = conn.prepareStatement(INSERT);) {

			pSTAT.setInt(1, x.getId_clan());
			pSTAT.setString(2, x.getNombre_clan());
			pSTAT.setString(3, x.getPais());

			if (pSTAT.executeUpdate() == 0) {
				throw new DAOException("Es posible que no se haya guardado, �revisalo!");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en sql insertar", e);
		}

	}

	@Override
	public void modificar(Clan x) throws DAOException {
		try (PreparedStatement pSTAT = conn.prepareStatement(UPDATE);) {

			pSTAT.setString(1, x.getNombre_clan());
			pSTAT.setString(2, x.getPais());
			pSTAT.setInt(3, x.getId_clan());

			if (pSTAT.executeUpdate() == 0) {
				throw new DAOException("Es posible que no se haya guardado, �revisalo!");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en sql insertar", e);
		}

	}

	@Override
	public void eliminar(Clan x) throws DAOException {
		try (PreparedStatement pSTAT = conn.prepareStatement(DELETE); ResultSet rs = pSTAT.executeQuery()) {

			pSTAT.setInt(1, x.getId_clan());

			if (pSTAT.executeUpdate() == 0) {
				throw new DAOException("Es posible que no se haya guardado, �revisalo!");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en sql buscar, clave mal");
		}

	}

	@Override
	public List<Clan> obtenerTodo() throws DAOException {
		List<Clan> clanes = new ArrayList<>();
		try (PreparedStatement pSTAT = conn.prepareStatement(SELECTALL); ResultSet rs = pSTAT.executeQuery()) {

			while (rs.next()) {
				clanes.add(convertir(rs));
			}

		} catch (SQLException e) {
			throw new DAOException("");
		} catch (OutOfBoundClanException e) {

			System.out.println("Error en el id clan ");
		}
		return clanes;
	}

	@Override
	public Clan buscar(Integer clave) throws DAOException {
		Clan c = null;
		try (PreparedStatement pSTAT = conn.prepareStatement(SELECT); ResultSet rs = pSTAT.executeQuery()) {
			if (rs.next()) {
				c = convertir(rs);
			} else {
				throw new DAOException("No hay registros ");
			}

		} catch (SQLException e) {
			throw new DAOException("Error en sql buscar, clave mal");
		} catch (OutOfBoundClanException e) {

			System.out.println("Error en el id clan ");
		}
		return c;
	}

	private Clan convertir(ResultSet rs) throws SQLException, OutOfBoundClanException {
		Integer id_clan = rs.getInt("ID_CLAN");
		String nombre_clan = rs.getString("NOMBRE_CLAN");
		String pais = rs.getString("PAIS");

		Clan c = new Clan(id_clan, nombre_clan, pais);
		return c;
	}

	@Override
	public void close() throws Exception {
		System.out.println("Cerrado");

	}

}
