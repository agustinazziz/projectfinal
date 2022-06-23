package com.capa2LogicaNegocio;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.dao.DAOActividad;
import com.entities.Actividad;

@Stateless
public class ActividadBean implements ActividadBeanRemote{
	@PersistenceContext
	private EntityManager em;

	@EJB
	DAOActividad daoActividad;
	
	public ActividadBean() {
		daoActividad = new DAOActividad();
	}
	
	@Override
	public Actividad agregarActividad (Actividad actividad) throws Exception {
		try {
			
		Actividad actividadNueva = new Actividad();
		actividadNueva=	daoActividad.crearActividad(actividad);
		
		return actividadNueva;
			
		}catch(PersistenceException e){
			System.out.println(e.getMessage());
		}
		
		return actividad;
	}
	
	@Override
	public Actividad buscarActividad (Actividad actividad) throws Exception {
		return daoActividad.buscarActividad(actividad.getIdActividad());
	}
	
	@Override
	public Actividad modificarActividad (Actividad actividad) throws Exception {
		return daoActividad.modificarActividad(actividad);
	}
}
