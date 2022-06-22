package com.capa2LogicaNegocio;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dao.DAOActividad;
import com.entities.Actividad;

@Stateless
public class ActividadBean implements ActividadBeanRemote{
	@PersistenceContext
	private EntityManager em;

	
	private static DAOActividad daoActividad;
	
	@Override
	public Actividad agregarActividad (Actividad actividad) throws Exception {
		try {
			return daoActividad.crearActividad(actividad);
		}catch(Exception e){
			
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
