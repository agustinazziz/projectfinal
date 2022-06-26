package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.UsuariosEmpresaDAO;
import com.entities.UsuarioEmpresa;
import com.exception.PersistenciaException;



@Stateless
@LocalBean

public class GestionUsuarioService implements Serializable{


	private static final long serialVersionUID = 1L;

	
	@EJB
	UsuariosEmpresaDAO usuariosPersistenciaDAO;
	

	
	public Usuario fromUsuarioEmpresa(UsuarioEmpresa e) {
		Usuario usuario=new Usuario();
		usuario.setId(e.getId().longValue());
		usuario.setActivo(e.getActivo());
		usuario.setApellido(e.getApellido());
		usuario.setNombre(e.getNombre());
		usuario.setCedula(e.getCedula());
		usuario.setContrasenia(e.getContrasenia());
		usuario.setUsuarioCuenta(e.getUsuarioCuenta());
		usuario.setInstituto(e.getInstituto());
		usuario.setProfesion(e.getProfesion());
		usuario.setEmail(e.getEmail());
		usuario.setRolUsuario(e.getRolUsuario());
		return usuario;
	}
	public UsuarioEmpresa toUsuarioEmpresa(Usuario e) {
		UsuarioEmpresa usuarioEmpresa=new UsuarioEmpresa();
		usuarioEmpresa.setId(e.getId()!=null?e.getId().longValue():null);
		usuarioEmpresa.setActivo(e.getActivo());
		usuarioEmpresa.setApellido(e.getApellido());
		usuarioEmpresa.setNombre(e.getNombre());
		usuarioEmpresa.setCedula(e.getCedula());
		usuarioEmpresa.setContrasenia(e.getContrasenia());
		usuarioEmpresa.setUsuarioCuenta(e.getUsuarioCuenta());
		usuarioEmpresa.setInstituto(e.getInstituto());
		usuarioEmpresa.setProfesion(e.getProfesion());
		usuarioEmpresa.setEmail(e.getEmail());
		usuarioEmpresa.setRolUsuario(e.getRolUsuario());
		return usuarioEmpresa;
	}


	
	// servicios para capa de Presentacion

	

	public void modificarUsuarioEmpresa (UsuarioEmpresa usuario) {
		
		try {
			usuariosPersistenciaDAO.modificarUsuario(usuario);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		
	}

	public List<Usuario> seleccionarUsuarios() throws PersistenciaException {
		//buscamos todos los  objetos UsuarioEmpresa
		List<UsuarioEmpresa> listaUsuariosEmpresa = usuariosPersistenciaDAO.buscarUsuarios();
		
		List<Usuario> listaUsuarios=new ArrayList<Usuario>();
		//recorremos listaUsuariosEmpresa y vamos populando listaUsuario (haciendo la conversion requerida)
		for (UsuarioEmpresa usuarioEmpresa : listaUsuariosEmpresa) {
			listaUsuarios.add(fromUsuarioEmpresa(usuarioEmpresa));
		}
		return listaUsuarios;
	}


	public List<Usuario> seleccionarUsuarios(String criterioNombre,String criterioRol,String criterioActivo) throws PersistenciaException {
		//buscamos usuarios segun criterio indicado
		List<UsuarioEmpresa> listaUsuariosEmpresa = usuariosPersistenciaDAO.seleccionarUsuarios(criterioNombre,criterioRol,criterioActivo);
		//lista para devolver la seleccion de usuarios
		List<Usuario> listaUsuarios=new ArrayList<Usuario>();
		//recorremos listaUsuariosEmpresa y vamos populando listaUsuario (haciendo la conversion requerida)
		for (UsuarioEmpresa usuarioEmpresa : listaUsuariosEmpresa) {
			listaUsuarios.add(fromUsuarioEmpresa(usuarioEmpresa));
		}
		return listaUsuarios;
		
	}
	
	
	public Usuario buscarUsuarioEmpresa(Long id) {
		UsuarioEmpresa e = usuariosPersistenciaDAO.buscarUsuario(id);
		return fromUsuarioEmpresa(e);
	}

	public Usuario buscarUsuario(Long i) {
		UsuarioEmpresa e = usuariosPersistenciaDAO.buscarUsuario(i);
		return fromUsuarioEmpresa(e);
	}
	
	public Usuario agregarUsuario(Usuario usuarioSeleccionado) throws PersistenciaException   {
		UsuarioEmpresa e = usuariosPersistenciaDAO.agregarUsuario(toUsuarioEmpresa(usuarioSeleccionado));
		return fromUsuarioEmpresa(e);
	}

	public void actualizarUsuario(Usuario usuarioSeleccionado) throws PersistenciaException   {
		UsuarioEmpresa e = usuariosPersistenciaDAO.modificarUsuario(toUsuarioEmpresa(usuarioSeleccionado));
	}
	
	public UsuarioEmpresa buscarInicioSesion(String user,String contrasenia) throws PersistenciaException {
		UsuarioEmpresa us = usuariosPersistenciaDAO.iniciarSesion(user, contrasenia);
		return us;
		
	}
}

