 package com.capa2LogicaNegocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.dao.DAOCasilla;
import com.entities.CasillaNueva;
import com.entities.FormularioNuevo;

/**
 * Session Bean implementation class CasillasBean
 */
@Stateless
public class CasillasBean implements CasillasBeanRemote {
	@PersistenceContext
	private EntityManager em;

	
	private static DAOCasilla daoCasillas;

	/**
	 * Default constructor.
	 */
	public CasillasBean() {
		daoCasillas = new DAOCasilla();
	}


	@Override
	public void altaCasilla(String nom, String desc, String param, String tipoDato, String unuMedida) throws Exception {
		try {

			daoCasillas.altaCasilla(nom, desc, param, tipoDato, unuMedida, em);
			return;

		} catch (Exception e) {
			throw new Exception("No se puede crear la casilla!");
		}
	}	

	@Override
	public void ModificarCasilla(Long id,String nom,String des, String par,String uni, String tipDato,FormularioNuevo formNuevo) {
	
		DAOCasilla.modificarCasilla( id,nom, des, par,uni, tipDato, formNuevo, em);
		
	}

	@Override
	public CasillaNueva buscarCasilla(Long idCas) {
		
		return DAOCasilla.buscarCasilla(idCas, em);
		
	}


	@Override
	public List<CasillaNueva> listarCasillas() {
		return daoCasillas.listarCasillas();
	}


}