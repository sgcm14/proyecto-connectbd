package vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import modelo.Conexion;

public class Ejecutar {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		String user="", pass="",u="";
		int contador=0,correcto=0;
		
		Scanner sc=new Scanner(System.in);
		
		Conexion conect=new Conexion();	
		Connection conn = null;
		conn=conect.getConexion();
			
	do {
		System.out.println("Ingresa el usuario: ");
		user=sc.next();
		System.out.println("Ingresa la clave: ");
		pass=sc.next();
			
		
		String sqlListar = "SELECT * from usuario";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sqlListar);
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				String usuario = rs.getString(2);
				String clave = rs.getString(3);
				String nombres = rs.getString(4);
				String apellidos = rs.getString(5);
				
				if(user.equalsIgnoreCase(usuario)&& pass.equalsIgnoreCase(clave)) {
					u=(nombres.toUpperCase()+" "+apellidos.toUpperCase());
					break;
				}
			}
			
			if(u.length()>0) {
				System.out.println("Bienvenido "+u);
				correcto=1;
			}
			else {
				System.out.println("No Ingreso");
				correcto=0;
			}
			ps.close();
		
		} catch (Exception e) {
			System.out.println("conexion BD invalida");
			e.printStackTrace();
		}
		
		contador+=1;
		
			if(correcto==1) {
				break;
			}
			else if(contador==3){
				System.out.println("Cuenta bloqueada");
			}
			else {
				System.out.println("Te quedan "+(3-contador)+" intentos restantes");
				System.out.println();
				
			}
		}while(contador<3);
	}

}
