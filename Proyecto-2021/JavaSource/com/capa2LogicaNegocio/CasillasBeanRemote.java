package com.capa2LogicaNegocio;

import java.util.List;

import javax.ejb.Remote;

import com.entities.CasillaNueva;
import com.entities.FormularioNuevo;

@Remote
public interface CasillasBeanRemote {
	List<CasillaNueva> listarCasillas();
	void altaCasilla(String nom,String desc, String param, String  tipoDato, String unuMedida) throws Exception ;
	void ModificarCasilla(Long id,String nom,String des, String par,String uni, String tipDato,FormularioNuevo formNuevo);
	CasillaNueva buscarCasilla(Long idCas);
}
