package vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import modelo.Conexion;
import modelo.Usuario;

public class Ejecutar {
	String obtenerFecha() {
		Calendar cal = Calendar.getInstance();
		Date Fecha = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaFormateada = format.format(Fecha);
		return fechaFormateada;
	}

	public static void main(String[] args) {
				Ejecutar eje=new Ejecutar();
		String user="", pass="",u_nombre="",fecha=eje.obtenerFecha();
		int contador=0,correcto=0,accesos=0;
		
		Scanner sc=new Scanner(System.in);
		
		Conexion conect=new Conexion();	
		Connection conn = null;
		conn=conect.getConexion();
			
	do {
		System.out.println("Ingresa el usuario:  ");
		user=sc.next();
		System.out.println("Ingresa la clave:  ");
		pass=sc.next();
			   				//"SELECT * from usuario";
							//"SELECT * from usuario WHERE usuario = ? AND clave = ?";
		String sqlListar ="SELECT *,(SELECT COUNT(*) FROM accesos acc WHERE acc.estado_registro=1 AND acc.estado_acceso=0 "
		            + "AND acc.usuario=us.usuario) AS intentos_fallidos FROM usuario us WHERE us.usuario=?;";

		
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sqlListar);
			ps.setString(1,user);
			ResultSet rs = ps.executeQuery();
			
			Usuario usuario=null;
			u_nombre="";
			accesos=0;
		
			while(rs.next()) {
					usuario = new Usuario(rs.getInt(1),rs.getString(2),rs.getString(3),
										rs.getString(4),rs.getString(5),rs.getDate(6));
			
				
				if(pass.equals(usuario.getClave())) {
					u_nombre=(usuario.getNombres().toUpperCase()+" "+usuario.getApellidos().toUpperCase());
					accesos=rs.getInt(7);
					//break;
				}
			}
			
			if(u_nombre.length()>0 && accesos<3) {
				System.out.println("Bienvenido "+u_nombre);
			  //System.out.println(accesos);
				correcto=1;
			//insertar el usuario
			String sqlInsert = "INSERT into accesos(usuario,estado_acceso,estado_registro,fecha) VALUES(?,1,1,?)";
			PreparedStatement ps1 = conn.prepareStatement(sqlInsert);
			ps1.setString(1,user);
			ps1.setString(2,fecha);//aqui fecha
			ps1.executeUpdate();
			}
			
			else if(u_nombre.length()>0 && accesos>=3) {
				System.out.println("Cuenta Bloqueada");
			  //System.out.println(accesos);
				correcto=1;
			}
			else if(usuario==null){
				System.out.println("No existe la Cuenta");
				correcto=0;
			}
			else {
				System.out.println("Usuario incorrecto");
				correcto=0;
			//insertar el usuario
			String sqlInsert = "INSERT into accesos(usuario,estado_acceso,estado_registro,fecha) VALUES(?,0,1,?)";
			PreparedStatement ps2 = conn.prepareStatement(sqlInsert);
			ps2.setString(1,user);
			ps2.setString(2,fecha);//aqui fecha
			ps2.executeUpdate();	
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
			else {
				System.out.println("Te quedan "+(3-contador)+" intentos restantes");
				System.out.println();
				
			}
		}while(contador<3);
	}

}
