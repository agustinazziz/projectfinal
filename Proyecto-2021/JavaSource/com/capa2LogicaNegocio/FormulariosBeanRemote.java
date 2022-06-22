package com.capa2LogicaNegocio;

import java.util.List;

import javax.ejb.Remote;
import com.entities.*;


@Remote
public interface FormulariosBeanRemote {
	void insertarFormulario(String nombre, String resumen) throws Exception;
	List<FormularioNuevo> listarFormularios();
	void eliminarFormulario(Long id);
	FormularioNuevo buscarFormulario(Long id);
	void AgregarCasillasFormulario(FormularioNuevo formulario);
	void EditarFormulario(FormularioNuevo formulario);
}
