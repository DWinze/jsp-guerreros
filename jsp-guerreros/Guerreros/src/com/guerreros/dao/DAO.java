package com.guerreros.dao;

import com.guerreros.excepciones.*;

import java.util.List;



public interface DAO <G,I>{
	
	void insertar(G x) throws DAOException;
	
	void modificar(G x) throws DAOException;
	
	void eliminar(G x) throws DAOException;
	
	List<G> obtenerTodo() throws DAOException;
	
	G buscar(I clave) throws DAOException;

}
