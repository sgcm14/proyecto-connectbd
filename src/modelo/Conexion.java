package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;



public class Conexion {

	public Connection getConexion() {
		// TODO Auto-generated method stub

		final String URLBD ="jdbc:mysql://localhost:3306/tiendamascotas";
		final String USER = "root";
		final String CLAVE = "root";
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URLBD,USER,CLAVE);
	
			} 
		catch (Exception e) {
			System.out.println("conexion BD invalida");
			e.printStackTrace();
		}
		
		
		return conn;
	}
}
