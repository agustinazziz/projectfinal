package com.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.CasillaNueva;
import com.entities.FormularioNuevo;
import com.exception.PersistenciaException;

@Stateless
@LocalBean

public class DAOFormulario {
	
	
	@PersistenceContext
	private EntityManager em;
	
	public DAOFormulario() {
		super();
	}

	public FormularioNuevo insertarFormularioNuevo(String nombre, String resumen, List<CasillaNueva> casillaNueva) throws PersistenciaException {

		FormularioNuevo form = new FormularioNuevo();
		try {
			
			form.setCasillaNueva(casillaNueva);
			form.setNombre(nombre);
			form.setResumen(resumen);
			form.setStatus(1L);
			
			em.persist(form);
			em.flush();
			return form;

		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo crear el FormularioNuevo!!" + e.getMessage(), e);
		}

	}

	public  List<FormularioNuevo> listarFormularios() throws PersistenciaException { // Lista los formularios de la BD con status 1
																			// ("Los que no se han eliminado")
try {

		TypedQuery<FormularioNuevo> query = em.createQuery("SELECT f  from FormularioNuevo f", FormularioNuevo.class); //
		List<FormularioNuevo> resultados = query.getResultList();
		return resultados;
	} catch (PersistenceException e) {
		throw new PersistenciaException("No se pudo realizar la busqueda" + e.getMessage(), e);
	}
}

	public  void eliminarFormulario(Long idForm) throws Exception{ 
		try {
			FormularioNuevo formDel = this.find(idForm);
			em.remove(formDel);
			em.flush();																									// atributo
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
																											// status a
																											// 0.
		return;
	}

	public  FormularioNuevo find(Long idForm) throws Exception {

		FormularioNuevo formBuscado = null;
		TypedQuery<FormularioNuevo> query = em
				.createQuery("SELECT f FROM FormularioNuevo f WHERE f.id LIKE :idF", FormularioNuevo.class).setParameter("idF", idForm);
		formBuscado = (FormularioNuevo) query.getSingleResult();
		return formBuscado;

		 

	}
	
	public void EditarFormulario(FormularioNuevo form) {
		em.merge(form);
		em.flush();
	}

	public  List<CasillaNueva> listarCasillas() throws PersistenceException {
		TypedQuery<CasillaNueva> query = em.createQuery("SELECT c FROM CasillaNueva c order by c.idCasilla", CasillaNueva.class);
		List<CasillaNueva> resultados = query.getResultList();
		return resultados;
	}
	

}
