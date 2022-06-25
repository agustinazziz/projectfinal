 package com.capa2LogicaNegocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dao.DAOCasilla;
import com.entities.CasillaNueva;
import com.entities.FormularioNuevo;
import com.exception.PersistenciaException;

/**
 * Session Bean implementation class CasillasBean
 */
@Stateless
@LocalBean
public class CasillasBean implements CasillasBeanRemote {
	@PersistenceContext
	private EntityManager em;

	@EJB
	DAOCasilla daoCasillas;

	public CasillasBean() {
		daoCasillas = new DAOCasilla();
	}


	public void altaCasilla(String nom, String desc, String param, String tipoDato, String unuMedida, FormularioNuevo formNuevo) throws Exception {
		try {

			daoCasillas.altaCasilla(nom, desc, param, tipoDato, unuMedida,formNuevo, em);

		} catch (Exception e) {
			throw new Exception("No se puede crear la casilla!");
		}
	}	

	public void ModificarCasilla(Long id,String nom,String des, String par,String uni, String tipDato,FormularioNuevo formNuevo) {
	
		daoCasillas.modificarCasilla( id,nom, des, par,uni, tipDato, formNuevo);
		
	}

	public CasillaNueva buscarCasilla(Long idCas) {
		
		return daoCasillas.buscarCasilla(idCas);
		
	}


	
	public List<CasillaNueva> listarCasillas() {
		return daoCasillas.listarCasillas();
	}

	
	public void eliminarCasilla(Long idCas) throws Exception {
		
		try {
			daoCasillas.eliminarCasilla(idCas);;
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		
	}

}