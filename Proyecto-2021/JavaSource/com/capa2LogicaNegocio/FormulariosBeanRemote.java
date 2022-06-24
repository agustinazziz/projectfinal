package com.capa2LogicaNegocio;

import java.util.List;

import javax.ejb.Remote;
import com.entities.*;
import com.exception.PersistenciaException;


@Remote
public interface FormulariosBeanRemote {
	List<FormularioNuevo> listarFormularios() throws PersistenciaException;
	void eliminarFormulario(Long id);
	FormularioNuevo buscarFormulario(Long id);
	void EditarFormulario(FormularioNuevo formulario) throws PersistenciaException;
}
