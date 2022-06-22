package com.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Actividad;



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

		}catch (Exception e){
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
	
	
}

