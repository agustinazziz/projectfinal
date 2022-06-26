package com.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Actividad;
import com.entities.CasillaNueva;
import com.entities.FormularioNuevo;



@Stateless
@LocalBean

public class DAOActividad {

	@PersistenceContext
	private EntityManager em;
	
	
	public DAOActividad() {
		super();
	}
	
	public Actividad crearActividad(Actividad actividad) throws Exception {

		try {
			Actividad nuevaActividad = new Actividad();
				nuevaActividad.setCaracteristica(actividad.getCaracteristica());
				nuevaActividad.setFechaFin(actividad.getFechaFin());
				nuevaActividad.setFechaIni(actividad.getFechaIni());
				nuevaActividad.setFormActividad(actividad.getFormActividad());
				nuevaActividad.setLatitud(actividad.getLatitud());
				nuevaActividad.setLongitud(actividad.getLongitud());
				nuevaActividad.setMetodoMuestreo(actividad.getMetodoMuestreo());
				nuevaActividad.setTipoMuestreo(actividad.getTipoMuestreo());
				nuevaActividad.setUsuarioCreador(actividad.getUsuarioCreador());
			
				em.persist(nuevaActividad);
			
				return nuevaActividad;		

		}catch (PersistenceException e){
			System.out.println(e.getMessage());
		}
		return actividad;
	}
	
	
	public Actividad modificarActividad (Actividad actividad) throws Exception{
			Actividad actBuscada = this.buscarActividad(actividad.getIdActividad());
			actBuscada = actividad;
			em.merge(actBuscada);
			em.flush();
			
			return actBuscada;
		
	}
	
	public Actividad buscarActividad (Long idActividad) {
		TypedQuery<Actividad> query = em.createQuery("SELECT a FROM Actividad a WHERE a.idActividad=:idActividad", Actividad.class);
		Actividad actBuscada= query.getSingleResult();
		return actBuscada;
	}
	
	public List<Actividad> listarActividades () {
		TypedQuery<Actividad> query = em.createQuery("SELECT a FROM Actividad a", Actividad.class);
		List<Actividad> actBuscadas= query.getResultList();
		return actBuscadas;
	}
	
	public List<CasillaNueva> buscarCasillasFormid (Long idForm){
		TypedQuery<CasillaNueva> query= em.createQuery("SELECT c FROM CasillaNueva c JOIN c.formNuevo f WHERE f.id =:idForm", CasillaNueva.class).setParameter("idForm", idForm);
		List<CasillaNueva> listaCasillas = query.getResultList();
		return listaCasillas;
	}
	
	
}

