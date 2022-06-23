package com.capa2LogicaNegocio;

import javax.ejb.Remote;

import com.entities.Actividad;

@Remote
public interface ActividadBeanRemote {

	Actividad agregarActividad(Actividad actividad) throws Exception;

	Actividad buscarActividad(Actividad actividad) throws Exception;

	Actividad modificarActividad(Actividad actividad) throws Exception;
	

}
