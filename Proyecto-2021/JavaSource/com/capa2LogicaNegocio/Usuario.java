package com.capa2LogicaNegocio;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Usuario {

	@NotNull
	private Long id;
	@NotNull
	@Length(min=3,	max=50)
	private String nombre;
	
	@NotNull
	@Length(min=3,	max=50)
	private String apellido;
	
	@NotNull
	@Digits(fraction = 0, integer = 8)

	private String cedula;
	
	@NotNull
	@Length(min=4,max=50)
	private String usuarioCuenta;
	
	@NotNull
	@Length(min=6,max=100)
	private String contrasenia;
	
	@NotNull
	private String rolUsuario;
	
	@NotNull
	private String activo;
	

	private String instituto;
	
	
	private String profesion;
	

	private String email;
	
	
	

	public Usuario() {
		super();
	}
	

	public Usuario(Long id, String nombre, String apellido, String cedula,
			String usuarioCuenta,
			String contrasenia, String instituto,
			String profesion,String email,
			String rolUsuario,String activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.usuarioCuenta = usuarioCuenta;
		this.contrasenia = contrasenia;
		this.instituto = instituto;
		this.profesion = profesion;
		this.email = email;
		this.rolUsuario = rolUsuario;
		this.activo = activo;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getUsuarioCuenta() {
		return usuarioCuenta;
	}


	public void setUsuarioCuenta(String usuarioCuenta) {
		this.usuarioCuenta = usuarioCuenta;
	}


	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


	public String getInstituto() {
		return instituto;
	}


	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}


	public String getProfesion() {
		return profesion;
	}


	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRolUsuario() {
		return rolUsuario;
	}


	public void setRolUsuario(String rolUsuario) {
		this.rolUsuario = rolUsuario;
	}


	public String getActivo() {
		return activo;
	}


	public void setActivo(String activo) {
		this.activo = activo;
	}




	

}
