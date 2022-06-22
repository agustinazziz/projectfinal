package com.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
@Table(name =  "USUARIO")
public class UsuarioEmpresa implements Serializable {	   

	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="USUARIOS_IDUSUARIOS_GENERATOR", sequenceName="SEQ_ID_USUARIOS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIOS_IDUSUARIOS_GENERATOR")
	private Long id;
	@Column(unique=true)
	private String nombre;
	private String apellido;
	private String cedula;
	private String usuarioCuenta;
	private String contrasenia;
	private String instituto;
	private String profesion;
	private String email;
	private String rolUsuario;
	private String activo;
	
    @OneToMany(fetch= FetchType.EAGER, cascade= CascadeType.ALL, mappedBy="usuarioCreador")
    private List<Actividad> actividad;

	public UsuarioEmpresa() {
		super();
	}

	public UsuarioEmpresa(String nombre, String apellido, String cedula, String usuarioCuenta,
			String contrasenia, String instituto, String profesion, String email, String rolUsuario, String activo) {
		super();
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
