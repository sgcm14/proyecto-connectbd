package modelo;

import java.util.Date;

public class Usuario {
	private int id;
	private String usuario;
	private String clave;
	private String nombres;
	private String apellidos;
	private Date fecha_ultimo_login;
	
	public Usuario(int id, String usuario, String clave, String nombres, String apellidos, Date fecha_ultimo_login) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.clave = clave;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fecha_ultimo_login = fecha_ultimo_login;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFecha_ultimo_login() {
		return fecha_ultimo_login;
	}

	public void setFecha_ultimo_login(Date fecha_ultimo_login) {
		this.fecha_ultimo_login = fecha_ultimo_login;
	}

}
