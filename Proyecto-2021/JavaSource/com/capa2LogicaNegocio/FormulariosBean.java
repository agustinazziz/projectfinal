package com.capa2LogicaNegocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dao.DAOFormulario;
import com.entities.CasillaNueva;
import com.entities.FormularioNuevo;
import com.exception.PersistenciaException;

/**
 * Session Bean implementation class FormulariosBean
 */
@Stateless
@LocalBean
public class FormulariosBean implements FormulariosBeanRemote{
	@PersistenceContext
	private EntityManager em;


	
	@EJB
	DAOFormulario daoFormulario;
	
	public FormulariosBean(){
		daoFormulario = new DAOFormulario();
	}
	

	public FormularioNuevo insertarFormularioNuevo(String nombre, String resumen, List<CasillaNueva> casillaNueva) throws Exception {

	
		
		return daoFormulario.insertarFormularioNuevo(nombre,resumen,casillaNueva);
		 
		 
		
	}

	
	public List<FormularioNuevo> listarFormularios() throws PersistenciaException {

		
		
		List <FormularioNuevo> formulariosEncontrados = daoFormulario.listarFormularios();
		
		return formulariosEncontrados;
	}

	
	public void eliminarFormulario(Long id) throws Exception {
	
		
		try {
			daoFormulario.eliminarFormulario(id);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		
		
	}

	
	public FormularioNuevo buscarFormulario(Long id)  {
		
		
			try {
				return daoFormulario.find(id) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		
	}
	
	
	
	/* public void AgregarCasillasFormulario(FormularioNuevo form) {
		
		DAOFormulario daof = new DAOFormulario();	
		DAOFormularioCasilla daofc= new DAOFormularioCasilla();
	
		try {
			//TODO: todo esto se evita si las clases estuvieran bien hechas --> VER
			FormularioNuevo formActual = daof.find(form.getId());
			List<FormularioCasilla> formulariosCasilla = new ArrayList(formActual.getFormulariosCasillas());
			for (FormularioCasilla formularioCasilla : formulariosCasilla) {
				if(!form.contieneCasilla(formularioCasilla.getCasilla()))
					DAOFormularioCasilla.quitarFormularioCasilla(formularioCasilla
			for (FormularioCasilla formularioCasilla : form.getFormulariosCasillas()) {
				//si no existe lo insertamos
				if(formularioCasilla.getId()==null || 
						DAOFormularioCasilla.find(formularioCasilla.getId().getIdFormulario(), formularioCasilla.getId().getIdCasilla(), em)==null) 
					DAOFormularioCasilla.insertarFormularioCasilla(formularioCasilla);	
				//si existe editamos la obligatoriedad
				else
					DAOFormularioCasilla.editarFormularioCasilla(formularioCasilla);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	} */

	
	public void EditarFormulario(FormularioNuevo FormularioNuevo) throws PersistenciaException {
		
	
		daoFormulario.EditarFormulario(FormularioNuevo);
	}

	public List<CasillaNueva> listarCasillas() {
		return daoFormulario.listarCasillas();
	}





	
	
}
