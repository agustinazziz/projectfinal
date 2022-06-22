package com.ws.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import com.capa2LogicaNegocio.GestionUsuarioService;
import com.capa2LogicaNegocio.Usuario;
import com.entities.UsuarioEmpresa;
import com.exception.PersistenciaException;

@Stateless
@LocalBean
public class UsuarioRest implements IUsuariosRest {
		
		@EJB
		private GestionUsuarioService gestionUsuarioService;
		
		@Override
	    public String echo() {
	        return "Servicio Usuarios Disponible";
	    }

		@Override
		public Response getUsuarios() {
			try {
				List<Usuario> ret = gestionUsuarioService.seleccionarUsuarios();				
				return  Response.ok().entity(ret).build();				
				
			} catch (PersistenciaException e) {
				e.printStackTrace();
				return  Response.serverError().build();
			}
			
		}

	

		@Override
		public  String login (Credenciales cred) {
			System.out.println(cred.usuario+" y " + cred.contrasenia );
			String redireccion = null;
			UsuarioEmpresa usuarioEncontrado = new UsuarioEmpresa();
			try {
				usuarioEncontrado = gestionUsuarioService.buscarInicioSesion(cred.usuario, cred.contrasenia);
				if (usuarioEncontrado !=null) {
				redireccion="Usted a ingresado a Rest";
				}
				else {
				redireccion="Usuario Incorrecto";
				}
			}catch (Exception e) {
				
			}
			return redireccion;
			
			
			
		}
	  
}


