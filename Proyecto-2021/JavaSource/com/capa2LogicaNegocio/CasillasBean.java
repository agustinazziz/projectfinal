 package com.capa2LogicaNegocio;

import java.util.List;

import javax.ejb.EJB;
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
public class CasillasBean implements CasillasBeanRemote {
	@PersistenceContext
	private EntityManager em;

	@EJB
	DAOCasilla daoCasillas;

	public CasillasBean() {
		daoCasillas = new DAOCasilla();
	}


	@Override
	public void altaCasilla(String nom, String desc, String param, String tipoDato, String unuMedida, FormularioNuevo formNuevo) throws Exception {
		try {

			daoCasillas.altaCasilla(nom, desc, param, tipoDato, unuMedida,formNuevo, em);

		} catch (Exception e) {
			throw new Exception("No se puede crear la casilla!");
		}
	}	

	@Override
	public void ModificarCasilla(Long id,String nom,String des, String par,String uni, String tipDato,FormularioNuevo formNuevo) {
	
		daoCasillas.modificarCasilla( id,nom, des, par,uni, tipDato, formNuevo);
		
	}

	@Override
	public CasillaNueva buscarCasilla(Long idCas) {
		
		return daoCasillas.buscarCasilla(idCas);
		
	}


	@Override
	public List<CasillaNueva> listarCasillas() {
		return daoCasillas.listarCasillas();
	}
	
	@Override
	public void eliminarCasilla(Long idCas) {
		
		try {
			daoCasillas.eliminarCasilla(idCas);;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}