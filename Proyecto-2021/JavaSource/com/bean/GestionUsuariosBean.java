package com.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;	//JEE8
import javax.inject.Named;
import javax.persistence.PersistenceException;

import com.capa2LogicaNegocio.GestionUsuarioService;
import com.capa2LogicaNegocio.Usuario;
import com.entities.UsuarioEmpresa;
import com.exception.PersistenciaException;



@Named(value="gestionUsuarios")		//JEE8
@SessionScoped				        //JEE8
public class GestionUsuariosBean implements Serializable{
	
	@EJB
	GestionUsuarioService gestionUsuarioService;
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String nomUser;
	private String contrasenia;
	private String criterioNombre;
	private String criterioRol;
	private String criterioActivo;
	private String rol;
	private UsuarioEmpresa usuarioEncontrado = new UsuarioEmpresa();
	GestionUsuarioBean gUB= new GestionUsuarioBean();
	
	private List<Usuario> usuariosSeleccionados;
	private Usuario usuarioSeleccionado;
	public GestionUsuariosBean() {
		super();
	}
	/***********************************************************
	************************Acciones****************************
	************************************************************/
	public String seleccionarUsuarios() throws PersistenciaException {
		usuariosSeleccionados=gestionUsuarioService.seleccionarUsuarios(criterioNombre, criterioRol, criterioActivo);
		return "";
	}
	
	
	public String verDatosUsuario() {
		//Navegamos a datos usuario
		return "DatosUsuario";
	}
	
	public String inicioSesion () throws PersistenciaException {
		String redireccion = null;
		
		
		try {
			usuarioEncontrado = gestionUsuarioService.buscarInicioSesion(nomUser, contrasenia);
			
			if (usuarioEncontrado !=null) {
			rol=usuarioEncontrado.getRolUsuario();
			redireccion="principal";
			
			}
			else {
			
			}
		}catch (PersistenceException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}
		return redireccion;
		
	}
	
	
	//Metodos rol
	
	
	
	
	/////////////// ROL ADMIN
	public String rolAdmin () {
		String render="false";
		
		if (rol.equals("Administrador")) {
			render="true";
		}
		
		
		
		
		
		return render;
	}

	/////////////// ROL Experto
	public String rolExperto () {
		String render;
		
		if (rol.equals("Experto") || rol.equals("Administrador")) {
			render="true";
		}
		else {
			render="false";
		}
		
		
		
		
		return render;
	}
	
	// ******** Getters & Setters****************************
	
	
	public String getCriterioNombre() {
		return criterioNombre;
	}

	public String getNomUser() {
		return nomUser;
	}

	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public void setCriterioNombre(String criterioNombre) {
		this.criterioNombre = criterioNombre;
	}
	public String getCriterioRol() {
		return criterioRol;
	}
	public void setCriterioRol (String criterioRol) {
		this.criterioRol = criterioRol;
	}
	public String getCriterioActivo() {
		return criterioActivo;
	}
	public void setCriterioActivo(String criterioActivo) {
		this.criterioActivo = criterioActivo;
	}
	public List<Usuario> getUsuariosSeleccionados() {
		return usuariosSeleccionados;
	}
	public void setUsuariosSeleccionados(List<Usuario> usuariosSeleccionados) {
		this.usuariosSeleccionados = usuariosSeleccionados;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	

	
	
	
}
