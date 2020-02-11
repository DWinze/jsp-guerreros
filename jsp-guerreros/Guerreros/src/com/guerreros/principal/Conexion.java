package com.guerreros.principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 

 
public class Conexion {
    private Connection jdbcConnection;
    
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private String driver="com.mysql.jdbc.Driver";
    public Conexion(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
		
	}
 
    public Conexion() {
    	//el auto reconnect y el ssl son cosas que he a�adido, porque me saltaban excepciones
    	
		this.jdbcURL = "jdbc:mysql://localhost:3306/BD_GUERREROS?autoReconnect=true&useSSL=false&characterEncoding=utf8&useUnicode=true";
		this.jdbcUsername = "root";
		this.jdbcPassword = "";
		
		
	}
	public void conectar() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
        System.out.println("Conectadoler");
    }
     
    public void desconectar() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
            System.out.println("Desconectado");
        }
    }
 
	public Connection getJdbcConnection() {
		return jdbcConnection;
	}  
 
}