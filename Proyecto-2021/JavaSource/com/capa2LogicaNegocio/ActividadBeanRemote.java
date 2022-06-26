package com.capa2LogicaNegocio;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Actividad;
import com.entities.CasillaNueva;

@Remote
public interface ActividadBeanRemote {

	Actividad agregarActividad(Actividad actividad) throws Exception;

	Actividad buscarActividad(Actividad actividad) throws Exception;

	Actividad modificarActividad(Actividad actividad) throws Exception;
	
	List<CasillaNueva> buscarCasillaFormId (Long idForm);
	
	List<Actividad> listarActividades();

}
